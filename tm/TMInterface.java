package tm;

/**
 * Defines the interface for a deterministic Turing Machine.
 * Specifies the required operations for adding transitions, 
 * running the machine, and retrieving its tape state or string output.
 *
 * Implemented by the TM class to perform actual TM logic and simulation.
 *
 * @author Nick Bortz & Flynn Hoare
 * @since 2025-04-21
 */
public interface TMInterface {

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
     * Runs the TM on the input string
     */ 
    public void runMachine();

    /**
     * Returns the formatted tape of the machine with the head position
     * @param head The current index of the tape head
     * @return the tape string
     */
    public String getTapeString(int head);

    /**
     * Returns a string representation of the TM
     * @return string representation of the TM
     */
    public String toString();
}
