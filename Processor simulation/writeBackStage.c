//
//  writeBackStage.c
//  A2
//
//  Created by Donald  Acton on 2017-10-01.
//  Copyright © 2017 Donald  Acton. All rights reserved.
//

#include "writeBackStage.h"
#include "instructionSpecifications.h"
#include "printInternalReg.h"

// Use this struct to store state information for this stage. By making it 
// static the structure and its fields can be accessed in this file
// but not in the reset of the program. 
static struct writeBackStateStruct wbs;

void setWriteBack(uint64_t PC, nibble icode, nibble ifun,
              int regsValid, nibble rA, nibble rB,
              int srcValid, nibble srcA, nibble srcB,
              int dstValid,  nibble destE, nibble destM,
              int valCValid, uint64_t valC,
              int valPvalid, int64_t valP) {
	wbs.PC = PC;
	wbs.icode = icode;
	wbs.ifun = ifun;
	wbs.regsValid = 0;
	wbs.srcValid = 0;
	wbs.srcA = srcA;
	wbs.srcB = srcB;
	wbs.dstValid = dstValid;
	wbs.valCValid = 0;
	wbs.valPValid = 0;
	wbs.destE = destE;
	wbs.destM = destM;
}

nibble getWriteBackICode() {return wbs.icode;}

nibble getWriteBackDestE() {return wbs.destE;}
nibble getWriteBackDestM() {return wbs.destM;}

void updateWriteBack(int stage) {
    wbs.stage = "W";
	printReg(wbs.stage, wbs.tick,
	         wbs.PC-getInstructionSize(wbs.icode), wbs.icode, wbs.ifun,
			 wbs.regsValid, UNNEEDED_REG, UNNEEDED_REG,
			 wbs.srcValid, UNNEEDED_REG, UNNEEDED_REG,
			 wbs.dstValid, wbs.destE, wbs.destM,
			 wbs.valCValid, UNNEEDED_ADDR,
			 wbs.valPValid, UNNEEDED_ADDR,
			 getInstructionMnemonic(wbs.icode, wbs.ifun));
}

void processWriteBackStage(int tick) {
    wbs.tick = tick;
  // Probably empty, but here is an example of printing
  //printReg("W", 82, 0x299, 7, 2, 0, UNNEEDED_REG, UNNEEDED_REG,
	//   0, UNNEEDED_REG, UNNEEDED_REG,
	//   1, 0xF, 0xF, 0, UNNEEDED_ADDR, 0, UNNEEDED_ADDR, 
	//   getInstructionMnemonic(7, 2));
    return;
}
