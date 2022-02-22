# NxNQueens
## Dependencies:
JavaSE-14
## Explanation:
This program is designed to practice the Constraint Satisfaction techniques 
especially Discrete Constraint Satisfaction problems. 
This Assignment requires you to formulate and solve the 8-Queens Problem as a CSP 
(Constraint Satisfaction Problem). A computer implementation of two algorithms 
(specified below) is to be developed and a comparison of the performance of the two 
algorithms is to be carried out.
## Algorithms:
(a) Forward checking 
(b) Directional Arc consistency Look-ahead 
The program accepts the position of the first queen, the queen in row 1, as 
input (for example queen 1 in 1A, or in 1B ...), and generates the positions for the 
remaining queens, if there is a solution to the problem consistent with this 
assignment. It counts the number of backtracks made to the assigned 
value to the queen in each row until a solution is found. For the counting purposes, 
there is a backtrack (and the counter is incremented) each time the value currently 
assigned to the queen in a given row is changed (for example from 2A to 2B, or from 
2B to 2C, and so on) until all consistent solutions are generated. 
## Documentation:
### Driver
The program is run with the Driver.java file which has the main function in it.
Enter the input of the position of the first queen in the format "1Column" (1A, 1B,
1C, 1D, 1E...1H). 
The program prints a list of all the possible solutions consistent with the position entered
and a count of all the backtracks to get to that solution. Run the driver again to get the 
solutions of some other position.
### DAC
The Directional Arc Consistency Look Ahead Algorithm is paired up with Back Tracking to generate all possible
solutions consistent with the position entered.
The DAC.java file runs this algorithm.
### Forward Checking
The Forward Checking Algorithm is paired up with Back Tracking to generate all possible
solutions consistent with the position entered.
The ForwardChecking.java file runs this algorithm.
### Node
The Node.java generates any check on the board with a variable, a list of possible values (domain)
and possibly a column assigned to it.
## Example Output

```
Please enter the position of a queen: 
1A

Queen 1 in column 1A:


Solution 1 with queen 1 in 1A: 
The positions of the Queens are: 

Row1: 1A
Row2: 2E
Row3: 3H
Row4: 4F
Row5: 5C
Row6: 6G
Row7: 7B
Row8: 8D
Total numbers of backtracks before this solution was found:
Forward Checking: 80
Directional Look Ahead: 38

Solution 2 with queen 1 in 1A: 
The positions of the Queens are: 

Row1: 1A
Row2: 2F
Row3: 3H
Row4: 4C
Row5: 5G
Row6: 6D
Row7: 7B
Row8: 8E
Total numbers of backtracks before this solution was found:
Forward Checking: 109
Directional Look Ahead: 54

Solution 3 with queen 1 in 1A: 
The positions of the Queens are: 

Row1: 1A
Row2: 2G
Row3: 3D
Row4: 4F
Row5: 5H
Row6: 6B
Row7: 7E
Row8: 8C
Total numbers of backtracks before this solution was found:
Forward Checking: 131
Directional Look Ahead: 66

Solution 4 with queen 1 in 1A: 
The positions of the Queens are: 

Row1: 1A
Row2: 2G
Row3: 3E
Row4: 4H
Row5: 5B
Row6: 6D
Row7: 7F
Row8: 8C
Total numbers of backtracks before this solution was found:
Forward Checking: 146
Directional Look Ahead: 75

Total numbers of backtracks before all solutions having a queen in 1A was found (or found there are no solutions): 
Forward Checking: 184
Directional Look Ahead: 97
```

```
Please enter the position of a queen: 
1H

Queen 1 in column 1H:


Solution 1 with queen 1 in 1H: 
The positions of the Queens are: 

Row1: 1H
Row2: 2B
Row3: 3D
Row4: 4A
Row5: 5G
Row6: 6E
Row7: 7C
Row8: 8F
Total numbers of backtracks before this solution was found:
Forward Checking: 31
Directional Look Ahead: 15

Solution 2 with queen 1 in 1H: 
The positions of the Queens are: 

Row1: 1H
Row2: 2B
Row3: 3E
Row4: 4C
Row5: 5A
Row6: 6G
Row7: 7D
Row8: 8F
Total numbers of backtracks before this solution was found:
Forward Checking: 46
Directional Look Ahead: 24

Solution 3 with queen 1 in 1H: 
The positions of the Queens are: 

Row1: 1H
Row2: 2C
Row3: 3A
Row4: 4F
Row5: 5B
Row6: 6E
Row7: 7G
Row8: 8D
Total numbers of backtracks before this solution was found:
Forward Checking: 68
Directional Look Ahead: 36

Solution 4 with queen 1 in 1H: 
The positions of the Queens are: 

Row1: 1H
Row2: 2D
Row3: 3A
Row4: 4C
Row5: 5F
Row6: 6B
Row7: 7G
Row8: 8E
Total numbers of backtracks before this solution was found:
Forward Checking: 97
Directional Look Ahead: 52

Total numbers of backtracks before all solutions having a queen in 1H was found (or found there are no solutions): 
Forward Checking: 184
Directional Look Ahead: 97
```
