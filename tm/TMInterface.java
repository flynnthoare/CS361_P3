package tm;

import java.util.ArrayList;

public interface TMInterface {
    // The states are given by a single number n, where the states are numbered from 0 to n-1
    // The first state is the start state, and the last state is the accept state
    // I don't think there's a need to have a state class, these can just be used as indices in the transition table

    // The TM's alphabet is given by a single number m, where the alphabet is numbered from 0 to m-1
    // 0 is the blank symbol, and 1 to m are the input symbols

    // The TM's transition table has the current state and the current symbol as the key
    // The transition table will hold the next state, the symbol to write, and the direction to move
    // Each state should have a transition to every other state, except the accept state, which will have no transitions

    /**
     * Adds a transition to the TM
     * @param currentState the current state
     * @param currentSymbol the current symbol
     * @param nextState the next state
     * @param writeSymbol the symbol to write
     * @param direction the direction to move (-1 for L or 1 for R)
     */
    public void addTransition(int currentState, int currentSymbol, int nextState, int writeSymbol, int direction);

    /**
     * Returns a string representation of the TM
     * @return string representation of the TM
     */
    public String toString();
}
