import cubesets.CubePrimarySetFactory;
import cubesets.ElementSet;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Lukasz on 2017-02-11.
 */
public class SolutionHelperTest {
    @Test
    public void testGetFirstSolutionBlue() throws Exception {
        SolutionHelper helper = new SolutionHelper();
        System.out.println(helper.getFirstSolution(new ElementSet(CubePrimarySetFactory.getBlueSet())));
    }

    @Test
    public void testGetFirstSolutionRed() throws Exception {
        SolutionHelper helper = new SolutionHelper();
        System.out.println(helper.getFirstSolution(new ElementSet(CubePrimarySetFactory.getRedSet())));
    }

    @Test
    public void testGetFirstSolutionPurple() throws Exception {
        SolutionHelper helper = new SolutionHelper();
        System.out.println(helper.getFirstSolution(new ElementSet(CubePrimarySetFactory.getPurpleSet())));
    }

    @Test
    public void testGetFirstSolutionYellow() throws Exception {
        SolutionHelper helper = new SolutionHelper();
        System.out.println(helper.getFirstSolution(new ElementSet(CubePrimarySetFactory.getYellowSet())));
    }

    @Test
    public void testGetAllSolutionsBlue() throws Exception {
        SolutionHelper helper = new SolutionHelper();
        Assert.assertEquals(6, helper.getAllSolutions(new ElementSet(CubePrimarySetFactory.getBlueSet())).size());
    }

    @Test
    public void testGetAllSolutionsYellow() throws Exception {
        SolutionHelper helper = new SolutionHelper();
        Assert.assertEquals(14, helper.getAllSolutions(new ElementSet(CubePrimarySetFactory.getYellowSet())).size());
    }


    @Test
    public void testGetAllSolutionsRed() throws Exception {
        SolutionHelper helper = new SolutionHelper();
        System.out.println( helper.getAllSolutions(new ElementSet(CubePrimarySetFactory.getRedSet())));
    }

    @Test
    public void testGetAllSolutionsPurple() throws Exception {
        SolutionHelper helper = new SolutionHelper();
        Assert.assertEquals(8, helper.getAllSolutions(new ElementSet(CubePrimarySetFactory.getPurpleSet())).size());
    }

}