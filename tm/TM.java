package tm;

public class TM implements TMInterface {
    private final int numStates;
    private final int numSymbols;
    private final int[] transitionTable;
    private int[] tape;

    public TM(int numStates, int numSymbols) {
        this.numStates = numStates;
        this.numSymbols = numSymbols;
        this.tape = new int[1];
        // Initialize the transition table as a flattened array for performance
        this.transitionTable = new int[numStates*(numSymbols+1)*3];
    }


    @Override
    public void addTransition(int currentState, int currentSymbol, int nextState, int writeSymbol, int direction) {
        // Get index to flattened transition table
        int index = currentState * (transitionTable.length / numStates) + currentSymbol * 3;
        transitionTable[index] = nextState;
        transitionTable[index + 1] = writeSymbol;
        transitionTable[index + 2] = direction;
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
        long startTime = System.currentTimeMillis();
        int currentState = 0;
        int headPosition = 0;

        while (currentState < numStates - 1) {
            int currentSymbol = tape[headPosition];

            // Get the transition for the current state and symbol
            int base = (currentState * (numSymbols + 1) + currentSymbol) * 3;
            int nextState = transitionTable[base];
            int writeSymbol = transitionTable[base + 1];
            int direction = transitionTable[base + 2];

            // Update the tape
            tape[headPosition] = writeSymbol;

            // Move the head
            headPosition += direction;
            if (headPosition < 0) {
                // Grow the tape
                growTape(1);
                headPosition = 0; // Reset head position to the first cell
            } else if (headPosition >= tape.length) {
                // Grow the tape
                growTape(0);
            }

            // Update the current state
            currentState = nextState;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + " ms");

        // Baseline: 1190 ms
        // Flat transition table: 1046 ms
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
