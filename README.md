Solving a maze
==============
The idea here is to write a program to solve simple mazes. The mazes are given in 
a file and the program must read in the file, solve the maze and output the solution.


Maze input/output formats
=========================

The input is a maze description file in plain text.  
* 1 - denotes walls
* 0 - traversable passage way

INPUT:
```
<WIDTH> <HEIGHT><CR>
<START_X> <START_Y><CR>		(x,y) location of the start. (0,0) is upper left and (width-1,height-1) is lower right
<END_X> <END_Y><CR>		    (x,y) location of the end
<HEIGHT> rows where each row has <WIDTH> {0,1} integers space delimited
```

OUTPUT:

The maze with a path from start to end
walls marked by '#', passages marked by ' ', path marked by 'X', start/end marked by 'S'/'E'

Example file:
```
10 10
1 1
8 8
1 1 1 1 1 1 1 1 1 1
1 0 0 0 0 0 0 0 0 1
1 0 1 0 1 1 1 1 1 1
1 0 1 0 0 0 0 0 0 1
1 0 1 1 0 1 0 1 1 1
1 0 1 0 0 1 0 1 0 1
1 0 1 0 0 0 0 0 0 1
1 0 1 1 1 0 1 1 1 1
1 0 1 0 0 0 0 0 0 1
1 1 1 1 1 1 1 1 1 1
```

OUTPUT:
```
##########
#SXX     #
# #X######
# #XX    #
# ##X# ###
# # X# # #
# # XX   #
# ###X####
# #  XXXE#
##########
```


Movement
========
Valid moves are N, S, E, W only, with no diagonal movement allowed. Moves in any of these directions will be blocked by maze walls, which are
identified as 1's in the input file. The edges of the grid should not be considered as walls unless specified, and allow for wrapping movement.

Example of horizontal wrapping:
```
10 10
1 1
8 8
1 1 1 1 1 1 1 1 1 1 
0 0 1 0 0 0 0 0 0 0 
0 0 1 0 0 1 1 1 1 1 
1 0 1 0 0 0 0 0 0 1 
1 0 1 1 0 1 0 1 1 1 
1 0 1 0 0 1 0 1 0 1 
1 0 1 0 0 0 0 0 0 1 
1 0 1 0 0 0 1 1 1 1 
1 0 1 0 0 0 0 0 0 1 
1 1 1 1 1 1 1 1 1 1 
```
OUTPUT:
```

##########
XS# XXXXXX
  # X#####
# # X    #
# ##X# ###
# # X# # #
# # X    #
# # X ####
# # XXXXE#
##########
```

Example of vertical wrapping:
```
10 10
1 1
8 8
1 0 1 1 1 1 1 1 1 1 
1 0 1 0 0 0 0 0 0 0 
1 0 1 0 1 1 1 1 1 1 
1 1 1 0 0 0 0 0 0 1 
1 0 1 1 0 1 0 1 1 1 
1 0 1 0 0 1 0 1 0 1 
1 0 1 0 0 0 0 0 0 1 
1 0 1 1 1 0 1 1 1 1 
1 0 0 0 0 0 0 0 0 1 
1 0 1 1 1 1 1 1 1 1 
```
OUTPUT:
```
#X########
#S#       
# # ######
###      #
# ## # ###
# #  # # #
# #      #
# ### ####
#XXXXXXXE#
#X########
```

To run the program:
===================

1. you need to run java -jar MazeSolver.jar in cmd.

2. Then a dialog window will appear for you to select the file.

3. After selecting the file, the program will automatically give the answer if the path is found or not. 

For example:
If the file contains a solvable maze, the program will print "Path Found" and print the maze with the path.
However, if the maze is not solvable, then the program will just display "Path not Found".
