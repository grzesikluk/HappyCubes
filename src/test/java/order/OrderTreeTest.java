package order;

import cubesets.CubePrimarySetFactory;
import cubesets.ElementSet;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Lukasz on 2017-02-12.
 */
public class OrderTreeTest {
    @Test
    public void testBuildTree() throws Exception {
        OrderTree tree = new OrderTree(new ElementSet(CubePrimarySetFactory.getBlueSet()));
        Assert.assertEquals(122, tree.toString().split("\n").length);

    }

}