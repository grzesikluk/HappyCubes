package cubesets;

import cubes.Element;

import java.util.LinkedList;
import java.util.List;

public class CubePrimarySetFactory {

    public static List<Element> getBlueSet() {
        List<Element> resultSet = new LinkedList<>();
        /*                           N        E        S        W   */
        resultSet.add(new Element("  o  ", "  o  ", "  o  ", "  o  "));
        resultSet.add(new Element("o o o", "oo oo", "o o o", "oo oo"));
        resultSet.add(new Element("  o  ", " o o ", "  o  ", "  o  "));
        resultSet.add(new Element(" o o ", "  o  ", " o oo", "oo o "));
        resultSet.add(new Element(" o o ", " o o ", "  o o", "oo o "));
        resultSet.add(new Element(" o o ", " o oo", "oo oo", "o o  "));

        checkElements(resultSet);

        return resultSet;

    }

    public static List<Element> getRedSet() {
        List<Element> resultSet = new LinkedList<>();

        resultSet.add(new Element("   oo", "o o o", "oo o ", "  o  "));
        resultSet.add(new Element(" o o ", "  o  ", "   o ", " o o "));
        resultSet.add(new Element(" oo o", "oo oo", "oo  o", "oo o "));
        resultSet.add(new Element("  o  ", "  o  ", "  o  ", " o o "));
        resultSet.add(new Element("  oo ", " o o ", "  o o", "oo o "));
        resultSet.add(new Element(" oo  ", "  o o", "oo oo", "o o  "));

        checkElements(resultSet);

        return resultSet;

    }

    public static List<Element> getPurpleSet() {
        List<Element> resultSet = new LinkedList<>();

        resultSet.add(new Element("oo o ", "   o ", "  o  ", "  ooo"));
        resultSet.add(new Element("   oo", "o o  ", " o o ", "  oo "));
        resultSet.add(new Element(" o   ", "  o  ", "  o  ", " o o "));
        resultSet.add(new Element("oo oo", "oo   ", " o o ", "  o o"));
        resultSet.add(new Element("  o o", "ooo  ", " oo o", "ooo  "));
        resultSet.add(new Element(" o oo", "o o  ", " o oo", "oo   "));

        checkElements(resultSet);

        return resultSet;

    }

    public static List<Element> getYellowSet() {
        List<Element> resultSet = new LinkedList<>();

        resultSet.add(new Element("  o  ", "  o  ", " o o ", " o o "));
        resultSet.add(new Element("  o o", "oo   ", " o o ", " o o "));
        resultSet.add(new Element("  o o", "oo o ", "  o o", "ooo  "));
        resultSet.add(new Element("o o o", "oo o ", "  o o", "oo oo"));
        resultSet.add(new Element("  o  ", " o o ", " o oo", "o o  "));
        resultSet.add(new Element(" o o ", "  o o", "oo o ", "  o  "));

        checkElements(resultSet);

        return resultSet;

    }

    public static List<Element> getSet(SetColor color) {
        switch (color) {
            case BLUE:
                return getBlueSet();
            case RED:
                return getRedSet();
            case YELLOW:
                return getYellowSet();
            case PURPLE:
                return getPurpleSet();
        }
        return getBlueSet();
    }

    private static void checkElements(List<Element> list) {

        /* Check if elements are producible and consistent*/
        for (Element element : list) {
            if (!element.isConsistent() || !element.isProducible())
                throw new InternalError("Wrong definition of cube elements " + element);
        }


        /*Check that no element is equal to other after reverting*/
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).equalsWithRotation(list.get(j)) || list.get(i).getReverted().equalsWithRotation(list.get(j)))
                    throw new InternalError("Rotated element is equal to other element " + list.get(i) + " " + list.get(j));

            }

        }


    }
}

