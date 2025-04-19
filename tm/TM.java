package tm;

public class TM implements TMInterface {
    private final int numStates;
    private final int[][][] transitionTable;
    private int[] tape;
    private int tapeSize;

    public TM(int numStates, int numSymbols) {
        this.numStates = numStates;
        this.tape = new int[1];
        this.tapeSize = 1;
        // Initialize the transition table
        this.transitionTable = new int[numStates][numSymbols+1][3];
    }

    @Override
    public void addTransition(int currentState, int currentSymbol, int nextState, int writeSymbol, int direction) {
        transitionTable[currentState][currentSymbol][0] = nextState;
        transitionTable[currentState][currentSymbol][1] = writeSymbol;
        transitionTable[currentState][currentSymbol][2] = direction;
    }

    /**
     * Grows the tape by 1 cell
     * @param offset the offset to copy the tape to (Used to grow the tape to the left)
     */
    private void growTape(int offset) {
        // This naive solution to only grow by 1 cell is normally slow,
        // but the alternative uses many if statements to check the size of the tape,
        // which is slower
        int[] newTape = new int[tape.length+1];
        System.arraycopy(tape, 0, newTape, offset, tape.length);
        tape = newTape;
    }

    @Override
    public void runMachine() {
        int currentState = 0;
        int headPosition = 0;

        while (currentState < numStates - 1) {
            int currentSymbol = tape[headPosition];

            // Get the transition for the current state and symbol
            int[] transition = transitionTable[currentState][currentSymbol];
            int nextState = transition[0];
            int writeSymbol = transition[1];
            int direction = transition[2];

            // Update the tape
            tape[headPosition] = writeSymbol;

            // Move the head
            headPosition += direction;
            if (headPosition < 0) {
                // Grow the tape
                growTape(1);
                headPosition = 0; // Reset head position to the first cell
            } else if (headPosition >= tape.length) {
                growTape(0);
            }

            // Update the current state
            currentState = nextState;
        }
    }

    public String getTapeString(int head) {
        StringBuilder sb = new StringBuilder();
        sb.append("Tape: |");
        for (int j : tape) {
            sb.append(j).append("|");
        }
        // Add the head pointer
        sb.append("\n       ");
        sb.append("  ".repeat(Math.max(0, head)));
        sb.append("^");
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int j : tape) {
            sb.append(j);
        }
        return sb.toString();
    }
}
