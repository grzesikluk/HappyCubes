package order;

import cubes.Element;
import cubes.Position;
import cubesets.CubePrimarySetFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class TopologyTest {

    private final List<Element> blue = CubePrimarySetFactory.getBlueSet();
    private final List<Element> red = CubePrimarySetFactory.getRedSet();

    @Test
    public void equals_NullElements() throws Exception {
        Topology topoBlue = new Topology();
        Assert.assertEquals(topoBlue, topoBlue);
    }

    @Test
    public void equals_SameSet() throws Exception {
        Topology topoBlue = new Topology();


        for(int i=0;i<blue.size();i++) {
            topoBlue.putWithoutChecking(ElementOrder.values()[i], blue.get(i));
        }

        Topology copyBlue = new Topology(topoBlue);
        Assert.assertEquals(copyBlue, topoBlue);

    }

    @Test
    public void equals_OtherSets() throws Exception {
        Topology topoBlue = new Topology();
        Topology topoRed = new Topology();

        for(int i=0;i<blue.size();i++) {
            topoBlue.putWithoutChecking(ElementOrder.values()[i], blue.get(i));
            topoRed.putWithoutChecking(ElementOrder.values()[i], red.get(i));
        }

        Assert.assertNotEquals(topoRed, topoBlue);

    }

    @Test
    public void equals_TwoSolutionsNotEqual() throws Exception {
        Topology topoSolution1 = new Topology();

        //One of the solutions
        Assert.assertTrue(topoSolution1.putWithChecking(ElementOrder.EL_1,blue.get(0)));
        Assert.assertTrue(topoSolution1.putWithChecking(ElementOrder.EL_2,blue.get(5).getReverted()));
        Assert.assertTrue(topoSolution1.putWithChecking(ElementOrder.EL_3,blue.get(3)));
        Assert.assertTrue(topoSolution1.putWithChecking(ElementOrder.EL_4,blue.get(2)));
        Assert.assertTrue(topoSolution1.putWithChecking(ElementOrder.EL_5,blue.get(1).getRotated(Position.W)));
        Assert.assertTrue(topoSolution1.putWithChecking(ElementOrder.EL_6,blue.get(4).getReverted()));


        Topology topoSolution2 = new Topology();

        //Second of the solutions
        Assert.assertTrue(topoSolution2.putWithChecking(ElementOrder.EL_1,blue.get(0)));
        Assert.assertTrue(topoSolution2.putWithChecking(ElementOrder.EL_2,blue.get(4).getRotated(Position.S)));
        Assert.assertTrue(topoSolution2.putWithChecking(ElementOrder.EL_3,blue.get(1).getRotated(Position.W)));
        Assert.assertTrue(topoSolution2.putWithChecking(ElementOrder.EL_4,blue.get(2).getRotated(Position.S)));
        Assert.assertTrue(topoSolution2.putWithChecking(ElementOrder.EL_5,blue.get(3).getReverted()));
        Assert.assertTrue(topoSolution2.putWithChecking(ElementOrder.EL_6,blue.get(5).getReverted().getRotated(Position.S)));

        Assert.assertNotEquals(topoSolution1, topoSolution2);

    }

    @Test
    public void equals_TwoSolutionsEqualRotated() throws Exception {
        Topology topoSolution1 = new Topology();

        //One of the solutions
        Assert.assertTrue(topoSolution1.putWithChecking(ElementOrder.EL_1,blue.get(0)));
        Assert.assertTrue(topoSolution1.putWithChecking(ElementOrder.EL_2,blue.get(5).getReverted()));
        Assert.assertTrue(topoSolution1.putWithChecking(ElementOrder.EL_3,blue.get(3)));
        Assert.assertTrue(topoSolution1.putWithChecking(ElementOrder.EL_4,blue.get(2)));
        Assert.assertTrue(topoSolution1.putWithChecking(ElementOrder.EL_5,blue.get(1).getRotated(Position.W)));
        Assert.assertTrue(topoSolution1.putWithChecking(ElementOrder.EL_6,blue.get(4).getReverted()));


        Topology topoSolution2 = new Topology();

        //Second of the solutions is the same but rotated W in the view of EL_1
        Assert.assertTrue(topoSolution2.putWithChecking(ElementOrder.EL_1,blue.get(0).getRotated(Position.W)));
        Assert.assertTrue(topoSolution2.putWithChecking(ElementOrder.EL_2,blue.get(3).getRotated(Position.W)));
        Assert.assertTrue(topoSolution2.putWithChecking(ElementOrder.EL_3,blue.get(4).getReverted().getRotated(Position.W)));
        Assert.assertTrue(topoSolution2.putWithChecking(ElementOrder.EL_4,blue.get(2).getRotated(Position.E)));
        Assert.assertTrue(topoSolution2.putWithChecking(ElementOrder.EL_5,blue.get(5).getReverted().getRotated(Position.W)));
        Assert.assertTrue(topoSolution2.putWithChecking(ElementOrder.EL_6,blue.get(1).getRotated(Position.S)));

        Assert.assertEquals(topoSolution1, topoSolution2);

    }


    @Test
    public void equals_TwoSolutionsEqualReversed() throws Exception {
        Topology topoSolution1 = new Topology();

        //One of the solutions
        Assert.assertTrue(topoSolution1.putWithChecking(ElementOrder.EL_1,blue.get(0)));
        Assert.assertTrue(topoSolution1.putWithChecking(ElementOrder.EL_2,blue.get(5).getReverted()));
        Assert.assertTrue(topoSolution1.putWithChecking(ElementOrder.EL_3,blue.get(3)));
        Assert.assertTrue(topoSolution1.putWithChecking(ElementOrder.EL_4,blue.get(2)));
        Assert.assertTrue(topoSolution1.putWithChecking(ElementOrder.EL_5,blue.get(1).getRotated(Position.W)));
        Assert.assertTrue(topoSolution1.putWithChecking(ElementOrder.EL_6,blue.get(4).getReverted()));
        Assert.assertTrue(topoSolution1.checkAllElementsRelations());


        Topology topoSolution2 = new Topology();

        //Second of the solutions is the same but reversed - inside out
        Assert.assertTrue(topoSolution2.putWithChecking(ElementOrder.EL_1,blue.get(0).getReverted()));
        Assert.assertTrue(topoSolution2.putWithChecking(ElementOrder.EL_2,blue.get(4)));
        Assert.assertTrue(topoSolution2.putWithChecking(ElementOrder.EL_3,blue.get(3).getReverted()));
        Assert.assertTrue(topoSolution2.putWithChecking(ElementOrder.EL_4,blue.get(2).getReverted()));
        Assert.assertTrue(topoSolution2.putWithChecking(ElementOrder.EL_5,blue.get(1).getRotated(Position.W).getReverted()));
        Assert.assertTrue(topoSolution2.putWithChecking(ElementOrder.EL_6,blue.get(5)));
        Assert.assertTrue(topoSolution2.checkAllElementsRelations());

        System.out.println(topoSolution1);
        System.out.println(topoSolution2);

        Assert.assertEquals(topoSolution1, topoSolution2);

    }

    @Test
    public void testPutWithChecking_Success() throws Exception {
        List<Element> blue = CubePrimarySetFactory.getBlueSet();
        Topology topo = new Topology();

        //One of the solutions
        Assert.assertTrue(topo.putWithChecking(ElementOrder.EL_1,blue.get(0)));
        Assert.assertTrue(topo.putWithChecking(ElementOrder.EL_2,blue.get(5).getReverted()));
        Assert.assertTrue(topo.putWithChecking(ElementOrder.EL_3,blue.get(3)));
        Assert.assertTrue(topo.putWithChecking(ElementOrder.EL_4,blue.get(2)));
        Assert.assertTrue(topo.putWithChecking(ElementOrder.EL_5,blue.get(1).getRotated(Position.W)));
        Assert.assertTrue(topo.putWithChecking(ElementOrder.EL_6,blue.get(4).getReverted()));

    }

    @Test
    public void testPutWithChecking_Failures() throws Exception {
        List<Element> blue = CubePrimarySetFactory.getBlueSet();
        Topology topo = new Topology();

        //One of the solutions
        Assert.assertTrue(topo.putWithChecking(ElementOrder.EL_1,blue.get(0)));
        Assert.assertFalse(topo.putWithChecking(ElementOrder.EL_2,blue.get(5)));
        Assert.assertTrue(topo.putWithChecking(ElementOrder.EL_2,blue.get(5).getReverted()));
        Assert.assertFalse(topo.putWithChecking(ElementOrder.EL_3,blue.get(3).getReverted()));
        Assert.assertTrue(topo.putWithChecking(ElementOrder.EL_3,blue.get(3)));
        Assert.assertFalse(topo.putWithChecking(ElementOrder.EL_4,blue.get(2).getReverted()));
        Assert.assertTrue(topo.putWithChecking(ElementOrder.EL_4,blue.get(2)));
        Assert.assertFalse(topo.putWithChecking(ElementOrder.EL_5,blue.get(1)));
        Assert.assertTrue(topo.putWithChecking(ElementOrder.EL_5,blue.get(1).getRotated(Position.W)));
        Assert.assertFalse(topo.putWithChecking(ElementOrder.EL_6,blue.get(4)));
        Assert.assertTrue(topo.putWithChecking(ElementOrder.EL_6,blue.get(4).getReverted()));

    }

    @Test
    public void testCheckAllElementsRelations() throws Exception {

            List<Element> blue = CubePrimarySetFactory.getBlueSet();
            Topology topo = new Topology();

            topo.putWithChecking(ElementOrder.EL_1,blue.get(0));
            topo.putWithChecking(ElementOrder.EL_2,blue.get(5).getReverted());
            topo.putWithChecking(ElementOrder.EL_3,blue.get(3));
            topo.putWithChecking(ElementOrder.EL_4,blue.get(2));
            topo.putWithChecking(ElementOrder.EL_5,blue.get(1).getRotated(Position.W));
            topo.putWithChecking(ElementOrder.EL_6,blue.get(4).getReverted());

             Assert.assertTrue(topo.checkAllElementsRelations());

    }

    @Test
    public void testCheckAllElementsRelations_Failure() throws Exception {

        List<Element> blue = CubePrimarySetFactory.getBlueSet();
        Topology topo = new Topology();

        topo.putWithoutChecking(ElementOrder.EL_1,blue.get(0));
        topo.putWithoutChecking(ElementOrder.EL_2,blue.get(5).getReverted());
        topo.putWithoutChecking(ElementOrder.EL_3,blue.get(3));
        topo.putWithoutChecking(ElementOrder.EL_4,blue.get(2));
        topo.putWithoutChecking(ElementOrder.EL_5,blue.get(1).getRotated(Position.W));
        topo.putWithoutChecking(ElementOrder.EL_6,blue.get(4)); //this is wrong

        Assert.assertFalse(topo.checkAllElementsRelations());

    }

}
