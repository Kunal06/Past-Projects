//
//  decodeStage.h
//  A2
//
//  Created by Donald  Acton on 2017-10-01.
//  Copyright © 2017 Donald  Acton. All rights reserved.
//

#ifndef decodeStage_h
#define decodeStage_h
#include <sys/types.h>
#include "instructionSpecifications.h"


struct decodeStateStruct {

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
	
	nibble regsValid;
	nibble valCValid;
	nibble valPValid;
	nibble srcValid;
	nibble dstValid;
	char *stage;
	int tick;
    
};

nibble getDecodeICode();

nibble getDecodeRA();
nibble getDecodeRB();

void updateDecode(int stage);

void setDecode(uint64_t PC, nibble icode, nibble ifun,
              nibble regsValid, nibble rA, nibble rB,
              nibble srcValid, nibble srcA, nibble srcB,
              nibble dstValid, nibble destE, nibble destM,
              nibble valCValid, uint64_t valC,
              nibble valPValid, int64_t valP);


void processDecodeStage(int tick);

#endif /* decodeStage_h */
