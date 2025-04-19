package tm;

import java.util.ArrayList;

public class TM implements TMInterface {
    private final int numStates;
    private final int numSymbols;
    private final ArrayList<ArrayList<ArrayList<Integer>>> transitionTable;
    private String tape;

    public TM(int numStates, int numSymbols, String initialString) {
        this.numStates = numStates;
        this.numSymbols = numSymbols;
        this.tape = initialString;

        // Initialize the transition table
        this.transitionTable = new ArrayList<>(numStates);
        for (int i = 0; i < numStates-1; i++) {
            transitionTable.add(new ArrayList<>(numSymbols));
            for (int j = 0; j <= numSymbols; j++) {
                transitionTable.get(i).add(new ArrayList<>());
            }
        }
    }

    @Override
    public void addTransition(int currentState, int currentSymbol, int nextState, int writeSymbol, int direction) {
        ArrayList<Integer> transition = new ArrayList<>();
        transition.add(nextState);
        transition.add(writeSymbol);
        transition.add(direction);
        transitionTable.get(currentState).get(currentSymbol).addAll(transition);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        // Add the transition table to the string
        sb.append("Transition Table:\n");
        for (int i = 0; i < numStates-1; i++) {
            sb.append("State ").append(i).append(":\n");
            for (int j = 0; j <= numSymbols; j++) {
                sb.append("\tSymbol ").append(j).append(": ");
                ArrayList<Integer> transition = transitionTable.get(i).get(j);
                sb.append("Next State: ").append(transition.get(0))
                  .append(", Write Symbol: ").append(transition.get(1))
                  .append(", Direction: ").append(transition.get(2) == -1 ? "L" : "R").append("\n");
            }
        }

        // Add the tape to the string
        sb.append("Tape: |");
        for (int i = 0; i < tape.length(); i++) {
            sb.append(tape.charAt(i)).append("|");
        }
        return sb.toString();
    }
}
