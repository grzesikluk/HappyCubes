package order;

import java.util.ArrayList;
import java.util.List;

/**
 * cubes.Element of ordered tree.
 */
public class OrderTreeElement {

    private ElementOrder level;
    private ElementOrder order;
    private OrderTreeElement parent;
    private List<OrderTreeElement> childList;

    public ElementOrder getLevel() {
        return level;
    }

    public ElementOrder getOrder() {
        return order;
    }

    public OrderTreeElement getParent() {
        return parent;
    }

    public List<OrderTreeElement> getChildList() {
        return childList;
    }

    public void addChild(List<OrderTreeElement> childList) {
        this.childList.addAll(childList);
    }

    public OrderTreeElement addParent(OrderTreeElement parent) {
        this.parent = parent;
        return this;
    }

    public OrderTreeElement addLevel(ElementOrder level) {
        this.level = level;
        return this;
    }

    public OrderTreeElement(ElementOrder order) {
        this.order = order;
        childList = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\nL:").append(level).append("=").append(order).append(" ->");

        for(OrderTreeElement nextelements: childList) {
            sb.append(nextelements.order);
        }

        for(OrderTreeElement nextelements: childList) {
            sb.append(nextelements.toString());
        }


        return sb.toString();
    }
}
