package order;

import cubes.Element;
import cubesets.ElementSet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderTree {

    private ElementSet setOfCubes;
    private OrderTreeElement top;

    public OrderTree(ElementSet set) {
        setOfCubes = set;
        buildTree();
    }

    private void buildTree() {
        /*Add top element*/
        top = new OrderTreeElement(ElementOrder.values()[0]).addLevel(ElementOrder.EL_1);
        buildRecursively(top, 1);
        optimiseRecursively(top);

    }

    private boolean optimiseRecursively(OrderTreeElement parent) {

        if (parent.getLevel() == ElementOrder.EL_6) {
            return true;

        } else {
            if (parent.getChildList().isEmpty())
                return false;
            else {
                List<OrderTreeElement> listToRemove = parent.getChildList().stream().filter(child -> !optimiseRecursively(child)).collect(Collectors.toList());

                parent.getChildList().removeAll(listToRemove);
                return !parent.getChildList().isEmpty();

            }
        }
    }

    private void buildRecursively(OrderTreeElement parent, int orderIx) {

        if (orderIx >= ElementOrder.values().length)      //end condition
            return;

        addChildsToElement(parent, ElementOrder.values()[orderIx]);

        for (OrderTreeElement child : parent.getChildList()) {
            buildRecursively(child, orderIx + 1);
        }

    }

    private void addChildsToElement(OrderTreeElement element, ElementOrder order) {
        List<ElementOrder> childs = getPossibleChilds(element.getOrder());
        List<ElementOrder> excludedChilds = getExcludedChilds(element);

        childs.removeAll(excludedChilds);

        List<OrderTreeElement> childsElements = childs.stream().map(childOrder -> new OrderTreeElement(childOrder).addParent(element).addLevel(order)).collect(Collectors.toList());

        element.addChild(childsElements);

    }

    private List<ElementOrder> getPossibleChilds(ElementOrder parent) {
        List<ElementOrder> result = new ArrayList<>();

        for (ElementOrder order : ElementOrder.values()) {
            if (isChild(parent, order))
                result.add(order);

        }

        return result;
    }

    private List<ElementOrder> getExcludedChilds(OrderTreeElement element) {
        List<ElementOrder> result = new ArrayList<>();

        while (element != null) {
            result.add(element.getOrder());
            element = element.getParent();
        }
        return result;
    }

    private boolean isChild(ElementOrder parentOrder, ElementOrder otherOrder) {

        if (!otherOrder.equals(parentOrder)) {
            for (Element elementOther : setOfCubes.getSet(otherOrder)) {
                for (Element elementParent : setOfCubes.getSet(parentOrder)) {
                    if (Topology.checkMutualRelation(elementParent, elementOther, parentOrder, otherOrder))
                        return true;
                }
            }

        }


        return false;

    }

    public OrderTreeElement getTop() {
        return top;
    }

    @Override
    public String toString() {
        return top.toString();
    }
}
