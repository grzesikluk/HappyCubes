package order;

import cubes.Element;
import cubes.Position;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*  order.Topology of cube elements for solution:

 EL_6 - EL_1 - EL_2
         |
       EL_3
         |
       EL_4
         |
       EL_5
*/
public class Topology {

    private static Map<ElementOrder, Map<ElementOrder, Pair<Position, Position>>> relationsMap;
    Map<ElementOrder, Element> listOfCubes;

    static {
        /* Initiate relations map*/
        relationsMap = new HashMap<>();

        for (ElementOrder order : ElementOrder.values())
            relationsMap.put(order, new HashMap<>());

        relationsMap.get(ElementOrder.EL_1).put(ElementOrder.EL_2, new Pair<>(Position.E, Position.W));
        relationsMap.get(ElementOrder.EL_1).put(ElementOrder.EL_3, new Pair<>(Position.S, Position.N));
        relationsMap.get(ElementOrder.EL_1).put(ElementOrder.EL_5, new Pair<>(Position.N, Position.S));
        relationsMap.get(ElementOrder.EL_1).put(ElementOrder.EL_6, new Pair<>(Position.W, Position.E));

        relationsMap.get(ElementOrder.EL_2).put(ElementOrder.EL_1, new Pair<>(Position.W, Position.E));
        relationsMap.get(ElementOrder.EL_2).put(ElementOrder.EL_3, new Pair<>(Position.S, Position.E));
        relationsMap.get(ElementOrder.EL_2).put(ElementOrder.EL_4, new Pair<>(Position.E, Position.E));
        relationsMap.get(ElementOrder.EL_2).put(ElementOrder.EL_5, new Pair<>(Position.N, Position.E));

        relationsMap.get(ElementOrder.EL_3).put(ElementOrder.EL_1, new Pair<>(Position.N, Position.S));
        relationsMap.get(ElementOrder.EL_3).put(ElementOrder.EL_2, new Pair<>(Position.E, Position.S));
        relationsMap.get(ElementOrder.EL_3).put(ElementOrder.EL_4, new Pair<>(Position.S, Position.N));
        relationsMap.get(ElementOrder.EL_3).put(ElementOrder.EL_6, new Pair<>(Position.W, Position.S));

        relationsMap.get(ElementOrder.EL_4).put(ElementOrder.EL_3, new Pair<>(Position.N, Position.S));
        relationsMap.get(ElementOrder.EL_4).put(ElementOrder.EL_2, new Pair<>(Position.E, Position.E));
        relationsMap.get(ElementOrder.EL_4).put(ElementOrder.EL_5, new Pair<>(Position.S, Position.N));
        relationsMap.get(ElementOrder.EL_4).put(ElementOrder.EL_6, new Pair<>(Position.W, Position.W));

        relationsMap.get(ElementOrder.EL_5).put(ElementOrder.EL_4, new Pair<>(Position.N, Position.S));
        relationsMap.get(ElementOrder.EL_5).put(ElementOrder.EL_2, new Pair<>(Position.E, Position.N));
        relationsMap.get(ElementOrder.EL_5).put(ElementOrder.EL_1, new Pair<>(Position.S, Position.N));
        relationsMap.get(ElementOrder.EL_5).put(ElementOrder.EL_6, new Pair<>(Position.W, Position.N));

        relationsMap.get(ElementOrder.EL_6).put(ElementOrder.EL_5, new Pair<>(Position.N, Position.W));
        relationsMap.get(ElementOrder.EL_6).put(ElementOrder.EL_1, new Pair<>(Position.E, Position.W));
        relationsMap.get(ElementOrder.EL_6).put(ElementOrder.EL_3, new Pair<>(Position.S, Position.W));
        relationsMap.get(ElementOrder.EL_6).put(ElementOrder.EL_4, new Pair<>(Position.W, Position.W));
    }

    public Topology() {
        listOfCubes = new HashMap<>();
    }

    public Topology(Topology other) {
        listOfCubes = new HashMap<>(other.listOfCubes);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Topology topology = (Topology) o;

        if (listOfCubes != null) {

            if (listOfCubes.equals(topology.listOfCubes)) {
                return true;
            } else {
                /* Not obvious equation relation. Must check all variants of 3D mirroring*/
                if (equalsToRotated(o))
                    return true;

                if (equalsToReversed(o))
                    return true;
            }

        } else {
            return topology.listOfCubes == null;
        }

        return false;

    }

    private boolean equalsToRotated(Object o) {

        Map<Position, Map<ElementOrder, ElementOrder>> rotatedOrderOfCubeFaces;
        rotatedOrderOfCubeFaces = new HashMap<>();
        Map<ElementOrder, ElementOrder> mapToBeAdded = new HashMap<>();
        boolean result = true;

        mapToBeAdded.put(ElementOrder.EL_1, ElementOrder.EL_1);
        mapToBeAdded.put(ElementOrder.EL_2, ElementOrder.EL_2);
        mapToBeAdded.put(ElementOrder.EL_3, ElementOrder.EL_3);
        mapToBeAdded.put(ElementOrder.EL_4, ElementOrder.EL_4);
        mapToBeAdded.put(ElementOrder.EL_5, ElementOrder.EL_5);
        mapToBeAdded.put(ElementOrder.EL_6, ElementOrder.EL_6);
        rotatedOrderOfCubeFaces.put(Position.N, mapToBeAdded);

        mapToBeAdded = new HashMap<>();
        mapToBeAdded.put(ElementOrder.EL_1, ElementOrder.EL_1);
        mapToBeAdded.put(ElementOrder.EL_2, ElementOrder.EL_3);
        mapToBeAdded.put(ElementOrder.EL_3, ElementOrder.EL_6);
        mapToBeAdded.put(ElementOrder.EL_4, ElementOrder.EL_4);
        mapToBeAdded.put(ElementOrder.EL_5, ElementOrder.EL_2);
        mapToBeAdded.put(ElementOrder.EL_6, ElementOrder.EL_5);
        rotatedOrderOfCubeFaces.put(Position.W, mapToBeAdded);

        mapToBeAdded = new HashMap<>();
        mapToBeAdded.put(ElementOrder.EL_1, ElementOrder.EL_1);
        mapToBeAdded.put(ElementOrder.EL_2, ElementOrder.EL_6);
        mapToBeAdded.put(ElementOrder.EL_3, ElementOrder.EL_5);
        mapToBeAdded.put(ElementOrder.EL_4, ElementOrder.EL_4);
        mapToBeAdded.put(ElementOrder.EL_5, ElementOrder.EL_3);
        mapToBeAdded.put(ElementOrder.EL_6, ElementOrder.EL_2);
        rotatedOrderOfCubeFaces.put(Position.S, mapToBeAdded);

        mapToBeAdded = new HashMap<>();
        mapToBeAdded.put(ElementOrder.EL_1, ElementOrder.EL_1);
        mapToBeAdded.put(ElementOrder.EL_2, ElementOrder.EL_5);
        mapToBeAdded.put(ElementOrder.EL_3, ElementOrder.EL_2);
        mapToBeAdded.put(ElementOrder.EL_4, ElementOrder.EL_4);
        mapToBeAdded.put(ElementOrder.EL_5, ElementOrder.EL_6);
        mapToBeAdded.put(ElementOrder.EL_6, ElementOrder.EL_3);
        rotatedOrderOfCubeFaces.put(Position.E, mapToBeAdded);

        for (Position position : Position.values()) {
            result = true;

            for (ElementOrder order : ElementOrder.values()) {
                if (!this.get(order).equalsWithRotation(((Topology) o).get(rotatedOrderOfCubeFaces.get(position).get(order)))) {
                    result = false;
                    break;
                }
            }
            if(result)
                return true;

        }

        return false;
    }

    private boolean equalsToReversed(Object o) {
        Map<Position, Map<ElementOrder, ElementOrder>> rotatedAndReversedOrderOfCubeFaces;
        rotatedAndReversedOrderOfCubeFaces = new HashMap<>();
        Map<ElementOrder, ElementOrder> mapToBeAdded = new HashMap<>();
        boolean result = true;

        mapToBeAdded.put(ElementOrder.EL_1, ElementOrder.EL_1);
        mapToBeAdded.put(ElementOrder.EL_2, ElementOrder.EL_6);
        mapToBeAdded.put(ElementOrder.EL_3, ElementOrder.EL_3);
        mapToBeAdded.put(ElementOrder.EL_4, ElementOrder.EL_4);
        mapToBeAdded.put(ElementOrder.EL_5, ElementOrder.EL_5);
        mapToBeAdded.put(ElementOrder.EL_6, ElementOrder.EL_2);
        rotatedAndReversedOrderOfCubeFaces.put(Position.N, mapToBeAdded);

        mapToBeAdded = new HashMap<>();
        mapToBeAdded.put(ElementOrder.EL_1, ElementOrder.EL_1);
        mapToBeAdded.put(ElementOrder.EL_2, ElementOrder.EL_3);
        mapToBeAdded.put(ElementOrder.EL_3, ElementOrder.EL_2);
        mapToBeAdded.put(ElementOrder.EL_4, ElementOrder.EL_4);
        mapToBeAdded.put(ElementOrder.EL_5, ElementOrder.EL_6);
        mapToBeAdded.put(ElementOrder.EL_6, ElementOrder.EL_3);
        rotatedAndReversedOrderOfCubeFaces.put(Position.W, mapToBeAdded);

        mapToBeAdded = new HashMap<>();
        mapToBeAdded.put(ElementOrder.EL_1, ElementOrder.EL_1);
        mapToBeAdded.put(ElementOrder.EL_2, ElementOrder.EL_2);
        mapToBeAdded.put(ElementOrder.EL_3, ElementOrder.EL_5);
        mapToBeAdded.put(ElementOrder.EL_4, ElementOrder.EL_4);
        mapToBeAdded.put(ElementOrder.EL_5, ElementOrder.EL_3);
        mapToBeAdded.put(ElementOrder.EL_6, ElementOrder.EL_6);
        rotatedAndReversedOrderOfCubeFaces.put(Position.S, mapToBeAdded);

        mapToBeAdded = new HashMap<>();
        mapToBeAdded.put(ElementOrder.EL_1, ElementOrder.EL_1);
        mapToBeAdded.put(ElementOrder.EL_2, ElementOrder.EL_5);
        mapToBeAdded.put(ElementOrder.EL_3, ElementOrder.EL_6);
        mapToBeAdded.put(ElementOrder.EL_4, ElementOrder.EL_4);
        mapToBeAdded.put(ElementOrder.EL_5, ElementOrder.EL_2);
        mapToBeAdded.put(ElementOrder.EL_6, ElementOrder.EL_3);
        rotatedAndReversedOrderOfCubeFaces.put(Position.E, mapToBeAdded);

        for (Position position : Position.values()) {
            result = true;

            for (ElementOrder order : ElementOrder.values()) {
                if (!this.get(order).getReverted().equalsWithRotation(((Topology) o).get(rotatedAndReversedOrderOfCubeFaces.get(position).get(order)))) {
                    result = false;
                    break;
                }
            }
            if(result)
                return true;

        }

        return false;
    }

    @Override
    public int hashCode() {
        return listOfCubes != null ? listOfCubes.hashCode() : 0;
    }

    public boolean putWithChecking(ElementOrder elOrder, Element element) {
        if (checkElementRelationWhenAdding(elOrder, element)) {
            listOfCubes.put(elOrder, element);
            return true;
        }
        return false;

    }

    public void putWithoutChecking(ElementOrder elOrder, Element element) {
        listOfCubes.put(elOrder, element);
    }

    public Element get(ElementOrder elOrder) {
        return listOfCubes.get(elOrder);
    }

    private static Pair<Position, Position> getMutualPositionsToCheck(ElementOrder el1, ElementOrder el2) {
        return relationsMap.get(el1).get(el2);
    }

    public static boolean checkMutualRelation(Element element1, Element element2, ElementOrder el1, ElementOrder el2) {
        return element1.matches(element2, getMutualPositionsToCheck(el1, el2));
    }

    public boolean checkMutualRelation(ElementOrder el1, ElementOrder el2) {
        return get(el1).matches(get(el2), getMutualPositionsToCheck(el1, el2));
    }

    private boolean checkElementRelationWhenAdding(ElementOrder elementOrder, Element element) {
        switch (elementOrder) {
            case EL_1:
                return true;

            case EL_2:
                return get(ElementOrder.EL_1).matches(element, getMutualPositionsToCheck(ElementOrder.EL_1, elementOrder));

            case EL_3:
                return get(ElementOrder.EL_1).matches(element, getMutualPositionsToCheck(ElementOrder.EL_1, elementOrder)) &&
                        get(ElementOrder.EL_2).matches(element, getMutualPositionsToCheck(ElementOrder.EL_2, elementOrder));

            case EL_4:
                return get(ElementOrder.EL_2).matches(element, getMutualPositionsToCheck(ElementOrder.EL_2, elementOrder)) &&
                        get(ElementOrder.EL_3).matches(element, getMutualPositionsToCheck(ElementOrder.EL_3, elementOrder));

            case EL_5:
                return get(ElementOrder.EL_1).matches(element, getMutualPositionsToCheck(ElementOrder.EL_1, elementOrder)) &&
                        get(ElementOrder.EL_2).matches(element, getMutualPositionsToCheck(ElementOrder.EL_2, elementOrder)) &&
                        get(ElementOrder.EL_4).matches(element, getMutualPositionsToCheck(ElementOrder.EL_4, elementOrder));


            case EL_6:
                return get(ElementOrder.EL_1).matches(element, getMutualPositionsToCheck(ElementOrder.EL_1, elementOrder)) &&
                        get(ElementOrder.EL_3).matches(element, getMutualPositionsToCheck(ElementOrder.EL_3, elementOrder)) &&
                        get(ElementOrder.EL_4).matches(element, getMutualPositionsToCheck(ElementOrder.EL_4, elementOrder)) &&
                        get(ElementOrder.EL_5).matches(element, getMutualPositionsToCheck(ElementOrder.EL_5, elementOrder));

        }
        return false;
    }

    private boolean checkElementRelation(ElementOrder element) {
        switch (element) {

            case EL_1:
                return checkMutualRelation(element, ElementOrder.EL_2) &&
                        checkMutualRelation(element, ElementOrder.EL_3) &&
                        checkMutualRelation(element, ElementOrder.EL_5) &&
                        checkMutualRelation(element, ElementOrder.EL_6);

            case EL_2:
                return checkMutualRelation(element, ElementOrder.EL_1) &&
                        checkMutualRelation(element, ElementOrder.EL_3) &&
                        checkMutualRelation(element, ElementOrder.EL_4) &&
                        checkMutualRelation(element, ElementOrder.EL_5);

            case EL_3:
                return checkMutualRelation(element, ElementOrder.EL_1) &&
                        checkMutualRelation(element, ElementOrder.EL_2) &&
                        checkMutualRelation(element, ElementOrder.EL_4) &&
                        checkMutualRelation(element, ElementOrder.EL_6);
            case EL_4:
                return checkMutualRelation(element, ElementOrder.EL_3) &&
                        checkMutualRelation(element, ElementOrder.EL_2) &&
                        checkMutualRelation(element, ElementOrder.EL_5) &&
                        checkMutualRelation(element, ElementOrder.EL_6);
            case EL_5:
                return checkMutualRelation(element, ElementOrder.EL_4) &&
                        checkMutualRelation(element, ElementOrder.EL_2) &&
                        checkMutualRelation(element, ElementOrder.EL_1) &&
                        checkMutualRelation(element, ElementOrder.EL_6);

            case EL_6:
                return checkMutualRelation(element, ElementOrder.EL_5) &&
                        checkMutualRelation(element, ElementOrder.EL_1) &&
                        checkMutualRelation(element, ElementOrder.EL_3) &&
                        checkMutualRelation(element, ElementOrder.EL_4);
        }
        return false;
    }

    public boolean checkAllElementsRelations() {

        for (ElementOrder order : ElementOrder.values()) {
            if (!checkElementRelation(order))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        Map<ElementOrder, List<String>> cubeToString = new HashMap<>();
        String emptyLine = "     ";
        String spaceBetween = " ";

        for (ElementOrder order : ElementOrder.values())
            cubeToString.put(order, Arrays.asList(get(order).toString().split("\n")));

        StringBuilder sb = new StringBuilder(200);

        for (int i = 0; i < cubeToString.get(ElementOrder.EL_1).size(); i++)
            sb.append(cubeToString.get(ElementOrder.EL_6).get(i)).append(spaceBetween).append(cubeToString.get(ElementOrder.EL_1).get(i)).append(spaceBetween).append(cubeToString.get(ElementOrder.EL_2).get(i)).append("\n");

        for (int i = 0; i < cubeToString.get(ElementOrder.EL_1).size(); i++)
            sb.append(emptyLine).append(spaceBetween).append(cubeToString.get(ElementOrder.EL_3).get(i)).append("\n");

        for (int i = 0; i < cubeToString.get(ElementOrder.EL_1).size(); i++)
            sb.append(emptyLine).append(spaceBetween).append(cubeToString.get(ElementOrder.EL_4).get(i)).append("\n");

        for (int i = 0; i < cubeToString.get(ElementOrder.EL_1).size(); i++)
            sb.append(emptyLine).append(spaceBetween).append(cubeToString.get(ElementOrder.EL_5).get(i)).append("\n");

        return sb.toString();

    }


}
