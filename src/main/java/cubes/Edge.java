package cubes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a cube edge. Performs all actions of checking, reversing.
 */
public class Edge {

    private List<Boolean> edgeVals;

    public Edge(List<Boolean> initList) {
        edgeVals = new ArrayList<>(initList);
    }

    public Edge(String initList) {
        edgeVals = getBooleanListFromString(initList);
    }

    public boolean matches(Edge other) {

        if (other.edgeVals.size() != edgeVals.size())
            return false;

        /*All interior bits must fit*/
        for (int i = 1; i < edgeVals.size() - 1; i++)
            if (!(edgeVals.get(i) ^ other.edgeVals.get(edgeVals.size() - i - 1)))
                return false;

        /*First and last must not be both true*/
        for (int i = 0; i < edgeVals.size(); i += edgeVals.size() - 1)
            if ((edgeVals.get(i) & other.edgeVals.get(edgeVals.size() - i - 1)))
                return false;


        return true;

    }

    public Edge getReversed() {
        List<Boolean> reversedList = new ArrayList<>(edgeVals);
        Collections.reverse(reversedList);
        return new Edge(reversedList);
    }

    public boolean get(int k) {
        return edgeVals.get(k);
    }

    public int size() {
        return edgeVals.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge cubeEdge = (Edge) o;

        return edgeVals != null ? edgeVals.equals(cubeEdge.edgeVals) : cubeEdge.edgeVals == null;

    }

    @Override
    public int hashCode() {
        return edgeVals != null ? edgeVals.hashCode() : 0;
    }

    @Override
    public String toString() {
        return getEdgeString();
    }

    private List<Boolean> getBooleanListFromString(String input) {
        List<Boolean> result = new ArrayList<>();

        for (Character c : input.toCharArray()) {
            if (c.equals(' '))
                result.add(false);
            else
                result.add(true);
        }

        return result;
    }

    private String getEdgeString() {
        StringBuilder sb = new StringBuilder();

        for (Boolean b : edgeVals) {
            if (b)
                sb.append("o");
            else
                sb.append(" ");
        }
        return sb.toString();
    }
}
