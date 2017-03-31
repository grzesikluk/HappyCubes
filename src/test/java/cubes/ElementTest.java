package cubes;

import cubesets.CubePrimarySetFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ElementTest {


    @Test
    public void testIsConsistent() throws Exception {
        Element element = new Element();
        element.addEdge(Position.S, new Edge(Arrays.asList(new Boolean[]{true, false, true, true, true})));
        element.addEdge(Position.W, new Edge(Arrays.asList(new Boolean[]{true, false, true, false, true})));
        element.addEdge(Position.N, new Edge(Arrays.asList(new Boolean[]{true, true, true, false, true})));
        element.addEdge(Position.E, new Edge(Arrays.asList(new Boolean[]{true, true, true, false, true})));

        Assert.assertTrue(element.isConsistent());

    }

    @Test
    public void testIsNotConsistent() throws Exception {
        Element element = new Element();
        element.addEdge(Position.S, new Edge(Arrays.asList(new Boolean[]{true, false, true, true, true})));
        element.addEdge(Position.W, new Edge(Arrays.asList(new Boolean[]{true, false, true, false, true})));
        element.addEdge(Position.N, new Edge(Arrays.asList(new Boolean[]{false, true, true, false, true})));
        element.addEdge(Position.E, new Edge(Arrays.asList(new Boolean[]{true, true, true, false, true})));

        Assert.assertFalse(element.isConsistent());

    }

    @Test
    public void testIsProducible() throws Exception {
        Element element = new Element();
        element.addEdge(Position.S, new Edge(Arrays.asList(new Boolean[]{true, false, true, false, true})));
        element.addEdge(Position.W, new Edge(Arrays.asList(new Boolean[]{true, true, true, false, true})));
        element.addEdge(Position.N, new Edge(Arrays.asList(new Boolean[]{true, true, false, true, true})));
        element.addEdge(Position.E, new Edge(Arrays.asList(new Boolean[]{true, false, true, true, true})));

        Assert.assertTrue(element.isProducible());

    }

    @Test
    public void testIsNotProducible() throws Exception {
        Element element = new Element();
        element.addEdge(Position.S, new Edge(Arrays.asList(new Boolean[]{true, false, true, false, true})));
        element.addEdge(Position.W, new Edge(Arrays.asList(new Boolean[]{true, true, true, false, true})));
        element.addEdge(Position.N, new Edge(Arrays.asList(new Boolean[]{true, true, false, false, true})));
        element.addEdge(Position.E, new Edge(Arrays.asList(new Boolean[]{true, false, true, true, true})));

        Assert.assertFalse(element.isProducible());

    }

    @Test
    public void testGetReverted_cube1() throws Exception {
        Element element = CubePrimarySetFactory.getBlueSet().get(0);
        Element revertedElement = CubePrimarySetFactory.getBlueSet().get(0);

        Assert.assertEquals(element, revertedElement.getReverted());
    }

    @Test
    public void testGetReverted_cube2() throws Exception {
        Element element = CubePrimarySetFactory.getBlueSet().get(1);
        Element revertedElement = CubePrimarySetFactory.getBlueSet().get(1);

        Assert.assertEquals(element, revertedElement.getReverted());
    }

    @Test
    public void testGetReverted_cube3() throws Exception {
        Element element = CubePrimarySetFactory.getBlueSet().get(3);
        Element revertedElement = new Element(" o o ", " o oo", "oo o ", "  o  ");

        System.out.println(element + "\n-----\n");
        System.out.println(revertedElement + "\n-----\n");
        Assert.assertEquals(revertedElement, element.getReverted());


    }


    @Test
    public void testGetRotated() throws Exception {
        Element element = new Element();
        element.addEdge(Position.S, new Edge(Arrays.asList(new Boolean[]{true, false, true, false, true})));
        element.addEdge(Position.W, new Edge(Arrays.asList(new Boolean[]{true, true, true, false, true})));
        element.addEdge(Position.N, new Edge(Arrays.asList(new Boolean[]{true, true, false, false, true})));
        element.addEdge(Position.E, new Edge(Arrays.asList(new Boolean[]{true, false, true, true, true})));

        Element revertedElement = element.getRotated(Position.S);

        Assert.assertEquals(element.getEdge(Position.N), revertedElement.getEdge(Position.S));
        Assert.assertEquals(element.getEdge(Position.S), revertedElement.getEdge(Position.N));
        Assert.assertEquals(element.getEdge(Position.E), revertedElement.getEdge(Position.W));
        Assert.assertEquals(element.getEdge(Position.W), revertedElement.getEdge(Position.E));


        revertedElement = element.getRotated(Position.E);

        Assert.assertEquals(element.getEdge(Position.N), revertedElement.getEdge(Position.E));
        Assert.assertEquals(element.getEdge(Position.S), revertedElement.getEdge(Position.W));
        Assert.assertEquals(element.getEdge(Position.E), revertedElement.getEdge(Position.S));
        Assert.assertEquals(element.getEdge(Position.W), revertedElement.getEdge(Position.N));
    }

    @Test
    public void testEqualsWithRotation() throws Exception {
        Element element = CubePrimarySetFactory.getBlueSet().get(0); //specific one, rotation or reverting equals to original one

        Element revertedElement = element.getReverted();
        Assert.assertTrue(element.equalsWithRotation(revertedElement));

        revertedElement = element.getRotated(Position.E);
        Assert.assertTrue(element.equalsWithRotation(revertedElement));
    }


    @Test
    public void testToString() throws Exception {
        List<Element> blueList = CubePrimarySetFactory.getBlueSet();

        Assert.assertEquals(
                        "\n" +
                        "  o  \n" +
                        " ooo \n" +
                        "ooooo\n" +
                        " ooo \n" +
                        "  o  " +
                        "\n",
                blueList.get(0).toString());

        Assert.assertEquals(
                        "\n" +
                        "o o o\n" +
                        "ooooo\n" +
                        " ooo \n" +
                        "ooooo\n" +
                        "o o o" +
                        "\n",
                blueList.get(1).toString());

        Assert.assertEquals(
                        "\n" +
                        "  o  \n" +
                        " oooo\n" +
                        "oooo \n" +
                        " oooo\n" +
                        "  o  " +
                        "\n",
                blueList.get(2).toString());

        Assert.assertEquals(
                        "\n" +
                        " o o \n" +
                        "oooo \n" +
                        " oooo\n" +
                        "oooo \n" +
                        "oo o " +
                        "\n",
                blueList.get(3).toString());

        Assert.assertEquals(
                        "\n" +
                        " o o \n" +
                        "ooooo\n" +
                        " ooo \n" +
                        "ooooo\n" +
                        "o o  " +
                        "\n",
                blueList.get(4).toString());

        Assert.assertEquals(
                        "\n" +
                        " o o \n" +
                        " oooo\n" +
                        "oooo \n" +
                        " oooo\n" +
                        "oo oo" +
                        "\n",
                blueList.get(5).toString());

    }


}