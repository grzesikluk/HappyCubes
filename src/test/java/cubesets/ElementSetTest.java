package cubesets;

import order.ElementOrder;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Lukasz on 2017-02-11.
 */
public class ElementSetTest {

    @Test
    public void testInitiationFromBlueSet() throws Exception {

        ElementSet setOfAllElements = new ElementSet(CubePrimarySetFactory.getBlueSet());
        Assert.assertEquals(1,setOfAllElements.getSet(ElementOrder.EL_1).size());
        Assert.assertEquals(2,setOfAllElements.getSet(ElementOrder.EL_2).size());
        Assert.assertEquals(4,setOfAllElements.getSet(ElementOrder.EL_3).size());
        Assert.assertEquals(8,setOfAllElements.getSet(ElementOrder.EL_4).size());
        Assert.assertEquals(8,setOfAllElements.getSet(ElementOrder.EL_5).size());
        Assert.assertEquals(8,setOfAllElements.getSet(ElementOrder.EL_6).size());

        System.out.println(setOfAllElements);
    }

}