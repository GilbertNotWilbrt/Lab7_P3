import java.util.Arrays;

/**
 * Model a 1D elementary cellular automaton.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 7.1
 */
public class Automaton
{
    // The number of cells.
    private final int numberOfCells;
    // The state of the cells.
    private int[] state;
    
    /**
     * Create a 1D automaton consisting of the given number of cells.
     * @param numberOfCells The number of cells in the automaton.
     */
    public Automaton(int numberOfCells)
    {
        this.numberOfCells = numberOfCells;
        state = new int[numberOfCells];
        // Seed the automaton with a single 'on' cell in the middle.
        state[numberOfCells / 2] = 1;
    }
    
    /**
     * Print the current state of the automaton.
     */
    public void print()
    {
        for(int cellValue : state) {
            if(cellValue == 1) {
                System.out.print("*");
            }
            else {
                System.out.print(" ");
            }
        }
        System.out.println();
    }   
    
    /**
     * Update the automaton to its next state.
     */
    public void update()
    {
        // Build the new state in a separate array.
        int[] nextState = new int[state.length];
        // Naively update the state of each cell
        // based on the state of its two neighbors.
        for(int i = 0; i < state.length; i++) {
            int left, center, right;
            // Replaced if-else with conditional operator for left neighbor.
            // If i == 0 (first cell), left is 0, otherwise it's state[i - 1].
            left = (i == 0) ? 0 : state[i - 1];

            center = state[i];
            
            // Replaced if-else with conditional operator for right neighbor.
            // If i + 1 is out of bounds (last cell), right is 0, otherwise it's state[i + 1].
            right = (i + 1 < state.length) ? state[i + 1] : 0;
            
            nextState[i] = (left + center + right) % 2;
            
            /*
             * // EXERCISE 32: Avoids the 'nextState' array by using one temporary variable.
        int tempLeft = 0; // Holds the old value of the cell just processed (state[i-1]).
        
        for(int i = 0; i < state.length; i++) {
            //  Get the current old values for calculation
            int left = tempLeft;
            int center = state[i];
            int right = (i + 1 < state.length) ? state[i + 1] : 0;
            
            // 2. Save the value that will become the 'left' neighbor for the next cell (i+1).
            // This must be the old value of the current center cell (state[i]).
            int nextLeft = center;
            
            // 3. Calculate and write the new value back to the array immediately.
            state[i] = (left + center + right) % 2;
            
            // 4. Update tempLeft for the next iteration (i+1).
            tempLeft = nextLeft;
        }
    }
             */
        }
        state = nextState;
    }
    
    private int calculateNextState(int left, int center, int right)
    {
    // currently, next state is 1 if an odd number of neighbors are 1 (on)    
    return (left + center + right) % 2;
    }
       
    /**
     * Reset the automaton.
     */
    public void reset()
    {
        Arrays.fill(state, 0);
        // Seed the automaton with a single 'on' cell.
        //Center Seed
        state[numberOfCells / 2] = 1;
        // Left seed (10 cells from the left edge)
        state[10] = 1;
        // Right seed (10 cells from the right edge)
        state[numberOfCells - 11] = 1; // if numberOfCells is say 30, it is at index 19.
    }
}
