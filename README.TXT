Project: automaton
Aothors: David J. Barnes and Michael Kölling

This project is part of the material of the book

   Objects First with Java - A Practical Introduction using BlueJ
   Seventh edition
   David J. Barnes and Michael Kölling

It is discussed in chapter 7.

27. Yes, the same patterns emerge. 

28. It is used to clear the automaton's current state. It sets 
every element in the state array to 0

29. 
This was done in the Automaton class as well
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

30. We replaced the multi-line if-else blocks with single-line assignments usign conditional
operator.

// Replaced if-else with conditional operator for left neighbor.
            // If i == 0 (first cell), left is 0, otherwise it's state[i - 1].
            left = (i == 0) ? 0 : state[i - 1];

            center = state[i];
            
            // Replaced if-else with conditional operator for right neighbor.
            // If i + 1 is out of bounds (last cell), right is 0, otherwise it's state[i + 1].
            right = (i + 1 < state.length) ? state[i + 1] : 0;
            
            nextState[i] = (left + center + right) % 2;
        }
        state = nextState;
    }
    
31. Avoiding a new array is possible by saving the old cell value (state[i])
into a temporary variable before overwriting it. This acts as the left
neighbor for the next iteration. however the dedicated nextState array is better.
It models the synchronization of the automaton, which makes the code much
easier to verify.

32.

The minimal solution retains the old value of the current cell in 
a temporary variable to put into the next cell's left neight, but
nextState array is still better as it shows the in-sync update.

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

33.

// Sliding window initialization: 'left' is 0 for the first cell, 'center' is the first cell's state.
int left = 0;
int center = state[0];

for (int i=0; i<state.length; i++){
    // Calculate the right neighbor. This is the only part that needs check.
    int right = i + 1 < state.length ? state[i+1] : 0;
    
    // Calculate the new state using the current window
    nextState[i] = (left + center + right) % 2;
    
    // slide the window for the next iteration (i+1):
    // old center becomes the new left.
    // old right becomes the new center.
    left = center;
    center = right;
}

34. 

private int calculateNextState(int left, int center, int right)
    {
    // currently, next state is 1 if an odd number of neighbors are 1 (on)    
    return (left + center + right) % 2;
    }
    
35. there are 256 unique rules for automaton. This number is not infinite
as the cell's next state depends on three binary neighbors ( left, center, right).
This results in eight possible input combinations, since each must map to 0 or 1
total number of rules is 2^8, which is 256, the amount of unique rules.

36.


       
