package cubes;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

import static cubes.Position.*;


/**
 * Happy cube element containing oriented edges on S, E, W, N sides.
 */
public class Element {

    private Map<Position, Edge> edges;

    public Element() {
        edges = new HashMap<>();
    }

    public Element(String... strings) {
        edges = new HashMap<>();
        for(int i = 0; i< values().length; i++) {
            edges.put(values()[i],new Edge(strings[i]));
        }

    }

    /**
     * Get cube element reverted on Y axis.
     * @return
     */
    public Element getReverted() {
        Element reverted = new Element();

        reverted.addEdge(W, this.edges.get(E).getReversed());
        reverted.addEdge(E, this.edges.get(W).getReversed());
        reverted.addEdge(N, this.edges.get(N).getReversed());
        reverted.addEdge(S, this.edges.get(S).getReversed());


        return reverted;
    }

    /**
     * Get rotated element. cubes.Position N will become pos.
     *
     * @param pos - rotation intication.
     * @return new element rotated.
     */
    public Element getRotated(Position pos) {
        Element newElement = new Element();

        switch (pos) {

            case S:
                newElement.addEdge(S, this.getEdge(N));
                newElement.addEdge(N, this.getEdge(S));
                newElement.addEdge(E, this.getEdge(W));
                newElement.addEdge(W, this.getEdge(E));
                break;
            case N:
                newElement.addEdge(S, this.getEdge(S));
                newElement.addEdge(N, this.getEdge(N));
                newElement.addEdge(E, this.getEdge(E));
                newElement.addEdge(W, this.getEdge(W));
                break;
            case W:
                newElement.addEdge(W, this.getEdge(N));
                newElement.addEdge(N, this.getEdge(E));
                newElement.addEdge(E, this.getEdge(S));
                newElement.addEdge(S, this.getEdge(W));
                break;
            case E:
                newElement.addEdge(E, this.getEdge(N));
                newElement.addEdge(W, this.getEdge(S));
                newElement.addEdge(N, this.getEdge(W));
                newElement.addEdge(S, this.getEdge(E));
                break;
        }

        return newElement;
    }

    public boolean matches(Element other, Pair<Position, Position> elementPositions) {
        return elementPositions != null && this.getEdge(elementPositions.getKey()).matches(other.getEdge(elementPositions.getValue()));

    }

    public boolean isConsistent() {

        Position pos = S;
        Position nextPos = getNextClockwise(pos);
        int k = 0;

        while (k++ < edges.size()) {

            if (edges.get(pos).get(edges.get(pos).size() - 1) != edges.get(nextPos).get(0))
                return false;
            pos = nextPos;
            nextPos = getNextClockwise(pos);
        }

        return true;
    }

    public boolean isProducible() {

        Position pos = S;
        Position nextPos = getNextClockwise(pos);
        int k = 0;

        while (k++ < edges.size()) {

            if (isProducibleConditionAtEndOfEdge(pos) && isProducibleConditionAtBeginningOfEdge(nextPos))
                return false;
            pos = nextPos;
            nextPos = getNextClockwise(pos);
        }

        return true;
    }

    private boolean isProducibleConditionAtEndOfEdge(Position pos) {
        int size = edges.get(pos).size();
        return (edges.get(pos).get(size - 1) & !edges.get(pos).get(size - 2));
    }

    private boolean isProducibleConditionAtBeginningOfEdge(Position pos) {
        return (edges.get(pos).get(0) & !edges.get(pos).get(1));
    }

    public void addEdge(Position position, Edge edge) {
        edges.put(position, edge);
    }

    public Edge getEdge(Position pos) {
        return edges.get(pos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element that = (Element) o;

        return edges != null ? this.edges.equals(that.edges) : that.edges == null;
    }

    public boolean equalsWithRotation(Element other) {

        return this.equals(other) ||
                this.equals(other.getRotated(E)) ||
                this.equals(other.getRotated(W)) ||
                this.equals(other.getRotated(S));

    }

    @Override
    public int hashCode() {
        return (edges != null ? edges.hashCode() : 0);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (String s : toStringList())
            result.append(s);

        return result.toString();
    }

    private String[] toStringList() {
        int edgeSize = edges.get(N).size();
        String[] strings = new String[edgeSize];
        StringBuilder interior = new StringBuilder();

        for (int i = 0; i < edgeSize - 2; i++)
            interior.append("o");

        strings[0] = "\n" + edges.get(N).toString() + "\n";

        for (int i = 1; i < edgeSize - 1; i++) {
            strings[i] = edges.get(W).toString().charAt(edgeSize - 1 - i) + interior.toString() + edges.get(E).toString().charAt(i) + "\n";
        }

        strings[edgeSize - 1] = edges.get(S).getReversed().toString()+"\n";

        return strings;
    }

}
