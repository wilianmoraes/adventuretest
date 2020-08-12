**OBJECTIVE**

Design and write a Java program that runs on a 1.8+ JVM and implements the specification below.

The written code will allow identifying different quality criteria:

- Object conception
- Naming quality
- Documentation quality and conciseness
- Tests
- …



_A hero adventures himself in a dangerous world fighting is way through the dark woods._

**Description**

The exercise aims to reproduce the movements of the hero on a map.

**Map**

The map is modelled using characters in a text file in UTF-8 format (see attachment)

Example:
<pre>
###    ######    ###
###      ##      ###
##     ##  ##     ##
#      ##  ##      #
##                ##
#####          #####
###### ##  ##  #####
 #     ######     # 
     ########       
    ############    
    ############    
     ########      #
 #     ######     ##
###### ##  ## ######
#####          #####
##                ##
#   ## #    # ##   #
##   ##      ##   ##
###    #    #    ###
###    ######    ###

</pre>


**Legend**

'#' impenetrable woods

[' '] (space character): box where the hero can move

**Moving the hero**

The movements of the hero are defined by a file with the following characteristics:

- encoding: UTF-8
- First line:
  - Contains the initial coordinates of the hero in the form &quot;x, y&quot;
  - The coordinates (0,0) correspond to the upper left corner of the map
- Second line:
  - Movement of the hero defined as a succession of characters representing the cardinal directions (N, S, E and O, note that O accounts for West)
  - Each character corresponds to the displacement of a box

**Interaction with map elements**

The hero cannot go beyond the edges of the map.

The hero cannot go on the squares occupied by the impenetrable woods.

**Test**

First test

The following file is provided as input:

3.0

SSSSEEEEEENN

Expected result:

The hero must finish at (9.2)

Second test

The following file is provided as input:

9,6

OONOOOSSO

Expected result:

The hero must finish at (7,5)
