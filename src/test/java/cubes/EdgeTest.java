package cubes;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by grzesikl on 25/01/2017.
 */
public class EdgeTest {
    @Test
    public void testMatches() throws Exception {
        Assert.assertFalse(new Edge(Arrays.asList(new Boolean[]{false,false,true,true,false})).matches(new Edge(Arrays.asList(new Boolean[]{false,false,true,true,false}))));
        Assert.assertTrue(new Edge(Arrays.asList(new Boolean[]{false,false,true,true,false})).matches(new Edge(Arrays.asList(new Boolean[]{true,false,false,true,true}))));
        Assert.assertTrue(new Edge(Arrays.asList(new Boolean[]{false,false,false,false,false})).matches(new Edge(Arrays.asList(new Boolean[]{true,true,true,true,true}))));
        Assert.assertTrue(new Edge(Arrays.asList(new Boolean[]{false,false,true,false,true})).matches(new Edge(Arrays.asList(new Boolean[]{false,true,false,true,true}))));
        Assert.assertTrue(new Edge(Arrays.asList(new Boolean[]{false,false,true,false,true})).matches(new Edge(Arrays.asList(new Boolean[]{false,true,false,true,false}))));
    }


    @Test
    public void testGetReversed() throws  Exception {
        Assert.assertEquals(new Edge(Arrays.asList(new Boolean[]{false,true,false,true})),new Edge(Arrays.asList(new Boolean[]{true,false,true,false})).getReversed() );
    }

}