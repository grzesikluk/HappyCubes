import cubes.Element;
import cubesets.ElementSet;
import order.ElementOrder;
import order.OrderTree;
import order.OrderTreeElement;
import order.Topology;

import java.util.HashSet;
import java.util.Set;

public class SolutionHelper {

    private ElementSet setOfElements;

    private Topology topo;

    /**
     * Generates one soluton for given set.
     * @param set
     * @return topology of solution.
     */
    Topology getFirstSolution(ElementSet set) {

        setOfElements = set;
        topo = new Topology();

        OrderTree orderTree = new OrderTree(setOfElements);

        if (getFirstSolutionRecursion(orderTree.getTop())) {
            if (topo.checkAllElementsRelations())
                return topo;
        }
        return null;

    }

    private boolean getFirstSolutionRecursion(OrderTreeElement treeElement) {
        Set<Element> elementElementSet = setOfElements.getSet(treeElement.getOrder());

        for (Element element : elementElementSet) {

            if (topo.putWithChecking(treeElement.getLevel(), element)) {

                if (treeElement.getLevel() == ElementOrder.EL_6)
                    return true; //finished

                for (OrderTreeElement child : treeElement.getChildList()) {

                    if (getFirstSolutionRecursion(child))
                        return true;

                }
            }
        }

        return false;
    }


    /**
     * Placeholder for next challenge implementation.
     *
     * @param set
     * @return
     */
    Set<Topology> getAllSolutions(ElementSet set) {
        setOfElements = set;
        topo = new Topology();
        OrderTree orderTree = new OrderTree(setOfElements);
        Set<Topology> resultSet = new HashSet<>();

        getAllSolutionsRecursive(resultSet, orderTree.getTop());

        return resultSet;
    }

    private void getAllSolutionsRecursive(Set<Topology> resultSet, OrderTreeElement treeElement) {
        Set<Element> elementElementSet = setOfElements.getSet(treeElement.getOrder());

        for (Element element : elementElementSet) {

            if (topo.putWithChecking(treeElement.getLevel(), element)) {

                if (treeElement.getLevel() == ElementOrder.EL_6)
                    resultSet.add(new Topology(topo));


                for (OrderTreeElement child : treeElement.getChildList()) {
                    getAllSolutionsRecursive(resultSet,child);

                }
            }
        }

    }


}
