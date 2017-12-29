//
//  writeBackStage.h
//  A2
//
//  Created by Donald  Acton on 2017-10-01.
//  Copyright © 2017 Donald  Acton. All rights reserved.
//

#ifndef writeBackStage_h
#define writeBackStage_h

#include <stdio.h>
#include "instructionSpecifications.h"
struct writeBackStateStruct {
    uint64_t PC;  // The program counter associated with this instruction

  // Add fields here to capture stage registers and state associated with 
  // processing and managing the stage. Note that when the simulation starts
  // the stage registers are initailized to indicate that on the first 
  // clock tick that this stage is working on a NOP instruction from address 
  // 0.
	nibble icode;
	nibble ifun;
	nibble rA;
	nibble rB;
	nibble destE;
	nibble destM;
	uint64_t valC;
	int64_t valP;
	nibble srcA;
	nibble srcB;
	
	nibble regsValid;
	nibble srcValid;
	nibble dstValid;
	nibble valCValid;
	nibble valPValid;
	char *stage;
    int tick;
} ;

nibble getWriteBackICode();

nibble getWriteBackDestE();
nibble getWriteBackDestM();

void updateWriteBack(int stage);

void setWriteBack(uint64_t PC, nibble icode, nibble ifun,
              int regsValid, nibble rA, nibble rB,
              int srcValid, nibble srcA, nibble srcB,
              int dstValid,  nibble destE, nibble destM,
              int  valCValid, uint64_t valC,
              int valPvalid, int64_t valP);

void processWriteBackStage(int tick) ;

#endif /* writeBackStage_h */
