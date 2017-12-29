#include <stdio.h>
#include "life.h"

// You are only allowed to change the contents of this file with respect 
// to improving the performance of this program



// If you need to initialize some data structures for your implementation
// of life then make changes to the following function, otherwise 
// do not change this function.

void initLife() {
}



// When completed this file will contain several versions of the life() function and 
// conditional compilation will be used to determine which version runs.  Each 
// version will be surrounded with  an ifdef, undef, and endif as illustrated 
// immediately below this comment block. To select the version of the life function 
// to use, put the #define USE USE immediately infront of the version of the life() 
// to compile.  Your version of life() must produce a correct result. 

// You are required to document the changes you make in the README.txt file. For each entry
// in the README.txt file there is to be a version of the matching  life() function here such
// that the markers can see, and run if needed,  the actual code that you used 
// to produce your results for the logged change to the life function.  


#ifdef USE
int life(long oldWorld[N][N], long newWorld[N][N]) {
  return 0;
}
#undef USE
#endif






// For each version of life you are testing duplicate the code between the 
// ifdef and endif and make your changes. To use the new version, move the the #define 
// just about this comment to be just in front of the ifdef for the new version.

/*

Entry 0: Baseline
Measure: 1902960,1898175,1906269,1902183,1909062,1905496,1909642,1906939,1901805,1897189
Average: 1903972 (1903972)

*/
//#define USE
#ifdef USE

int life(long oldWorld[N][N], long newWorld[N][N]) {

  int i, j, k, l;
  
  //clear the new world
  for (i = 0; i < N; i++)
    for (j = 0; j < N; j++) {
      newWorld[j][i] =  0;
    }

  int col, row;
  int q = -1;


  // Count the cells to the top left
  for (i = 0; i < N; i++) {
    col = (i -1 + N) % N;
    for (j = 0; j < N; j++) {
      row = (j - 1 + N ) % N;
      newWorld[j][i] += oldWorld[row][col];
    }
  }
  
  // Count the cells immediately above
  for (i = 0; i < N; i++) {
    for (j = 0; j < N; j++) {
      row = (j - 1 + N ) % N;
      newWorld[j][i] += oldWorld[row][i];
    }
  }

  // Count the cells to the top right
  for (i = 0; i < N; i++) {
    col = (i + 1 + N) % N;
    for (j = 0; j < N; j++) {
      row = (j - 1  + N ) % N;
      newWorld[j][i] += oldWorld[row][col];
    }
  }

 // Count the cells to the immediate left
  for (i = 0; i < N; i++) {
    col = (i -1 + N) % N;
    for (j = 0; j < N; j++) {
       newWorld[j][i] += oldWorld[j][col];
    }
  }
  
  // Count the cells to the immediate right
  for (i = 0; i < N; i++) {
    col = (i + 1 + N) % N;
    for (j = 0; j < N; j++) {
      newWorld[j][i] += oldWorld[j][col];
    }
  }

// Count the cells to the bottom left
  for (i = 0; i < N; i++) {
    col = (i - 1 + N) % N;
    for (j = 0; j < N; j++) {
      row = (j + 1 + N ) % N;
      newWorld[j][i] += oldWorld[row][col];
    }
  }

  // Count the cells immediately below
  for (i = 0; i < N; i++) {
    for (j = 0; j < N; j++) {
      row = (j + 1  + N ) % N;
      newWorld[j][i] += oldWorld[row][i];
    }
  }

  // Count the cells to the bottom right
  for (i = 0; i < N; i++) {
    col = (i + 1 + N) % N;
    for (j = 0; j < N; j++) {
      row = (j + 1  + N ) % N;
      newWorld[j][i] += oldWorld[row][col];
    }
  }

  // Check each cell to see if it should come to life, continue to live, or die
  int alive = 0;
  
  for (i = 0; i < N; i++)
    for (j = 0; j < N; j++) {
      newWorld[j][i] = checkHealth(newWorld[j][i], oldWorld[j][i]);
      alive += newWorld[j][i] ? 1:0;
    }

  return alive;
}
#undef USE
#endif

/*

Entry 1: In the baseline, we are iterating through the cells by their "columns" such that we are skipping a large amount of data each time. Iterating through each row instead should give us superior performance by leveraging spatial locality in our array accesses.
Measure: 225568,238059,235236,229700,225670,226337,225487,225397,225109,228509
Average: 228507 (228507.2)

*/
//#define USE
#ifdef USE

int life(long oldWorld[N][N], long newWorld[N][N]) {

  int i, j, k, l;
  
  //clear the new world
  for (i = 0; i < N; i++)
    for (j = 0; j < N; j++) {
      newWorld[i][j] =  0;
    }

  int col, row;
  int q = -1;


  // Count the cells to the top left
  for (i = 0; i < N; i++) {
    row = (i - 1 + N) % N;
    for (j = 0; j < N; j++) {
      col = (j - 1 + N ) % N;
      newWorld[i][j] += oldWorld[row][col];
    }
  }
  
  // Count the cells immediately above
  for (i = 0; i < N; i++) {
    row = (i - 1 + N ) % N;
    for (j = 0; j < N; j++) {
      newWorld[i][j] += oldWorld[row][j];
    }
  }

  // Count the cells to the top right
  for (i = 0; i < N; i++) {
    row = (i - 1 + N) % N;
    for (j = 0; j < N; j++) {
      col = (j + 1  + N ) % N;
      newWorld[i][j] += oldWorld[row][col];
    }
  }

  // Count the cells to the immediate left
  for (i = 0; i < N; i++) {
    for (j = 0; j < N; j++) {
	  col = (j - 1 + N) % N;
      newWorld[i][j] += oldWorld[i][col];
    }
  }
  
  // Count the cells to the immediate right
  for (i = 0; i < N; i++) {
    for (j = 0; j < N; j++) {
	  col = (j + 1 + N) % N;
      newWorld[i][j] += oldWorld[i][col];
    }
  }

  // Count the cells to the bottom left
  for (i = 0; i < N; i++) {
    row = (i + 1 + N) % N;
    for (j = 0; j < N; j++) {
      col = (j - 1 + N ) % N;
      newWorld[i][j] += oldWorld[row][col];
    }
  }

  // Count the cells immediately below
  for (i = 0; i < N; i++) {
    row = (i + 1  + N ) % N;
    for (j = 0; j < N; j++) {
      newWorld[i][j] += oldWorld[row][j];
    }
  }

  // Count the cells to the bottom right
  for (i = 0; i < N; i++) {
    row = (i + 1 + N) % N;
    for (j = 0; j < N; j++) {
      col = (j + 1  + N ) % N;
      newWorld[i][j] += oldWorld[row][col];
    }
  }

  // Check each cell to see if it should come to life, continue to live, or die
  int alive = 0;
  
  for (i = 0; i < N; i++)
    for (j = 0; j < N; j++) {
      newWorld[i][j] = checkHealth(newWorld[i][j], oldWorld[i][j]);
      alive += newWorld[i][j] ? 1:0;
    }

  return alive;
}
#undef USE
#endif

/*

Entry 2: The checks we do in the baseline require us to iterate through the array of cells 8 times. By performing all of our operations in one pass-through, we can take advantage of good temporal locality of reads to improve performance.
Measure: 173215,172846,172738,172870,172831,173331,173476,172851,173337,173177
Average: 173067 (173067.2)

*/
//#define USE
#ifdef USE

int life(long oldWorld[N][N], long newWorld[N][N]) {

  int i, j, k, l;
  
  //clear the new world
  for (i = 0; i < N; i++)
    for (j = 0; j < N; j++) {
      newWorld[i][j] =  0;
    }

  int coll, rowl, colg, rowg;
  int q = -1;


  // Iterate through the array
  for (i = 0; i < N; i++) {
    rowl = (i - 1 + N) % N;
	rowg = (i + 1 + N) % N;
    for (j = 0; j < N; j++) {
      coll = (j - 1 + N ) % N;
	  colg = (j + 1 + N ) % N;
      newWorld[i][j] += oldWorld[rowl][coll];
	  newWorld[i][j] += oldWorld[rowl][j];
	  newWorld[i][j] += oldWorld[rowl][colg];
	  newWorld[i][j] += oldWorld[i][coll];
	  newWorld[i][j] += oldWorld[i][colg];
	  newWorld[i][j] += oldWorld[rowg][coll];
	  newWorld[i][j] += oldWorld[rowg][j];
	  newWorld[i][j] += oldWorld[rowg][colg];
    }
  }

  // Check each cell to see if it should come to life, continue to live, or die
  int alive = 0;
  
  for (i = 0; i < N; i++)
    for (j = 0; j < N; j++) {
      newWorld[i][j] = checkHealth(newWorld[i][j], oldWorld[i][j]);
      alive += newWorld[i][j] ? 1:0;
    }

  return alive;
}
#undef USE
#endif

/*

Entry 3: Instead of iterating through the positions in the new world that we want to write to, we can iterate through the positions we want to read from. This way, we can guarantee that we will only read each location once, and that we will read them exactly in order, giving us superior temporal and spatial locality respectively.
Measure: 164948,165197,165248,165109,164962,166119,166217,164948,165515,165637
Average: 165390

*/
#define USE
#ifdef USE

int life(long oldWorld[N][N], long newWorld[N][N]) {

  int i, j, k, l;
  
  //clear the new world
  for (i = 0; i < N; i++)
    for (j = 0; j < N; j++) {
      newWorld[i][j] =  0;
    }

  int coll, rowl, colg, rowg, st;
  int q = -1;
  int collv, rowlv, colgv, rowgv;


  // Iterate through the array
  for (i = 0; i < N; i++) {
    rowl = (i - 1 + N) % N;
	rowg = (i + 1 + N) % N;
	rowlv = rowl >= 0;
	rowgv = rowg < N;
    for (j = 0; j < N; j++) {
      coll = (j - 1 + N ) % N;
	  colg = (j + 1 + N ) % N;
	  collv = coll >= 0;
	  colgv = colg < N;
	  st = oldWorld[i][j];
	  // top row
	  if (rowlv) {
	    if (collv) {
	      newWorld[rowl][coll] += st;
		}
	    newWorld[rowl][j] += st;
		if (colgv) {
	      newWorld[rowl][colg] += st;
		}
	  }
	  // mid row
	  if (collv) {
	    newWorld[i][coll] += st;
      }
	  if (colgv) {
	    newWorld[i][colg] += st;
	  }
	  // bottom row
	  if (rowgv) {
	    if (collv) {
	      newWorld[rowg][coll] += st;
		}
	    newWorld[rowg][j] += st;
		if (colgv) {
	      newWorld[rowg][colg] += st;
		}
	  }
    }
  }

  // Check each cell to see if it should come to life, continue to live, or die
  int alive = 0;
  
    for (i = 0; i < N; i++){
        for (j = 0; j < N; j++) {
            newWorld[i][j] = checkHealth(newWorld[i][j], oldWorld[i][j]);
            alive += newWorld[i][j] ? 1:0;
        }
    }
  return alive;
}
#undef USE
#endif


