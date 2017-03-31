package cubes;

public enum Position {
    N,E,S,W;

    public static Position getNextClockwise(Position position) {
        switch (position) {
            case S: return W;
            case W: return N;
            case N: return E;
            case E: return S;

            default:
                return S;
        }
    }
}
