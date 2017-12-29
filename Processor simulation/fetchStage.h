 //
//  fetchStage.h
//  A2
//
//  Created by Donald  Acton on 2017-10-01.
//  Copyright © 2017 Donald  Acton. All rights reserved.
//

#ifndef fetchStage_h
#define fetchStage_h
#include <stdio.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/uio.h>
#include <unistd.h>
#include <inttypes.h>
#include <stdlib.h>
#include <errno.h>
#include "printInternalReg.h"

struct fetchStateStruct {
  unsigned char *instBase; // Where the program starts in memory
  unsigned char *lastAddr; // The last valid address in memory of the program

  uint64_t PC;   // The program counter associated with this instruction
                 // it is first set when initializeFetchStage is called
    nibble icode;
    nibble ifun;
    nibble rA;
    nibble rB; //whether registers are used or not
    int regsValid;
    uint64_t valC;
    int valCValid; //whether ValC exists or not
    uint8_t byte0, byte1, byte2, byte3, byte4, byte5, byte6, byte7;
    int64_t valP;
    
    char *instr;
	char *stage;
    int tick;
  // Add fields here to capture stage registers and state associated
  // with processing and managing the stage.

} ;

void updateFetch(int stage);

void processFetchStage(int tick);

int initializeFetchState(int memoryFileFD, uint64_t initialPC);

#endif /* fetchStage_h */
