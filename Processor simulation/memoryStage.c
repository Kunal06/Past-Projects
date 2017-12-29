//
//  memoryStage.c
//  A2
//
//  Created by Donald  Acton on 2017-10-01.
//  Copyright © 2017 Donald  Acton. All rights reserved.
//

#include "memoryStage.h"
#include "writeBackStage.h"
#include "printInternalReg.h"
#include "instructionSpecifications.h"

// Use this struct to store state information for this stage. By making it 
// static the structure and its fields can be accessed in this file
// but not in the reset of the program. 
static struct memoryStateStuct ms;

void setMemory(uint64_t PC, nibble icode, nibble ifun,
              int regsValid, nibble rA, nibble rB,
              int srcValid, nibble srcA, nibble srcB,
              int dstValid,  nibble destE, nibble destM,
              int valCValid, uint64_t valC,
              int valPvalid, int64_t valP) {
	ms.PC = PC;
	ms.icode = icode;
	ms.ifun = ifun;
	ms.regsValid = 0;
	ms.srcValid = 0;
	ms.srcA = srcA;
	ms.srcB = srcB;
	ms.dstValid = dstValid;
	ms.valCValid = 0;
	ms.valPValid = 0;
	ms.destE = destE;
	ms.destM = destM;
}

nibble getMemoryICode() {return ms.icode;}

nibble getMemoryDestE() {return ms.destE;}
nibble getMemoryDestM() {return ms.destM;}

void updateMemory(int stage) {
    ms.stage = "M";
	setWriteBack(ms.PC,ms.icode,ms.ifun,
	           ms.regsValid,ms.rA,ms.rB,
			   ms.srcValid,ms.srcA,ms.srcB,
			   ms.dstValid,ms.destE,ms.destM,
			   ms.valCValid,ms.valC,
			   ms.valPValid,ms.valP);
	printReg(ms.stage, ms.tick,
	         ms.PC-getInstructionSize(ms.icode), ms.icode, ms.ifun,
			 ms.regsValid, UNNEEDED_REG, UNNEEDED_REG,
			 ms.srcValid, UNNEEDED_REG, UNNEEDED_REG,
			 ms.dstValid, ms.destE, ms.destM,
			 ms.valCValid, UNNEEDED_ADDR,
			 ms.valPValid, UNNEEDED_ADDR,
			 getInstructionMnemonic(ms.icode, ms.ifun));
}

void processMemoryStage(int tick) {
    ms.tick = tick;
  // probably empty as well but here is sample of printing
   //printReg("M", 10, 45, 1, 0, 0, UNNEEDED_REG, UNNEEDED_REG,
	//     0, UNNEEDED_REG, UNNEEDED_REG,
    //         1, 0xf, 0xf, 0, UNNEEDED_ADDR, 0, UNNEEDED_ADDR, "NOP");
  return;
}
