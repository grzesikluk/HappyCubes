package cubesets;

import cubes.Element;
import cubes.Position;
import order.ElementOrder;

import java.util.*;

/**
 * Holds all cubes in variants of rotation and reverting.
 * It is initiated for given color set.
 */
public class ElementSet {

    private Map<ElementOrder, Set<Element>> cubeElements;
    private Map<ElementOrder, Element> orderToParent;

    public ElementSet(List<Element> cubesSet) {
        cubeElements = new HashMap<>();
        orderToParent = new HashMap<>();

        for(int i=0; i<cubesSet.size();i++) {
            orderToParent.put(ElementOrder.values()[i],cubesSet.get(i));
        }

        for (ElementOrder order : ElementOrder.values()) {
            addAll(order, orderToParent.get(order));
        }

    }

    private void add(ElementOrder order, Element child) {

        if (cubeElements.containsKey(order)) {
            cubeElements.get(order).add(child);
        } else {
            Set<Element> initialSet = new HashSet<>();
            initialSet.add(child);
            cubeElements.put(order, initialSet);
        }
    }

    public Set<Element> getSet(ElementOrder order) {
        return cubeElements.get(order);
    }

    private void addAll(ElementOrder order, Element parent) {

        for (Position pos : Position.values()) {
            add(order, parent.getRotated(pos));
        }

        Element reverted = parent.getReverted();

        for (Position pos : Position.values()) {
            add(order, reverted.getRotated(pos));
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(ElementOrder order: ElementOrder.values())
            sb.append(order).append(" ").append(cubeElements.get(order));
        return sb.toString();
    }

}
