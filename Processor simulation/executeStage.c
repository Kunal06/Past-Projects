//
//  executeStage.c
//  A2
//
//  Created by Donald  Acton on 2017-10-01.
//  Copyright © 2017 Donald  Acton. All rights reserved.
//

#include "executeStage.h"
#include "memoryStage.h"
#include "printInternalReg.h"
#include "instructionSpecifications.h"


// Use this struct to store state information for this stage. By making it 
// static the structure and its fields can be accessed in this file
// but not in the reset of the program. 
static struct executeStateStruct es;

void setExecute(uint64_t PC, nibble icode, nibble ifun,
              int regsValid, nibble rA, nibble rB,
              int srcValid, nibble srcA, nibble srcB,
              int dstValid,  nibble destE, nibble destM,
              int valCValid, uint64_t valC,
              int valPvalid, int64_t valP) {
	es.PC = PC;
	es.icode = icode;
	es.ifun = ifun;
	es.regsValid = 0;
	es.srcValid = srcValid;
	es.srcA = srcA;
	es.srcB = srcB;
	es.dstValid = dstValid;
	es.valCValid = 0;
	es.valPValid = 0;
	es.destE = destE;
	es.destM = destM;
}

nibble getExecuteICode() {return es.icode;}

nibble getExecuteDestE() {return es.destE;}
nibble getExecuteSrcA() {return es.srcA;}
nibble getExecuteSrcB() {return es.srcB;}

void updateExecute(int stage) {
    es.stage = "E";
	setMemory(es.PC,es.icode,es.ifun,
	           es.regsValid,es.rA,es.rB,
			   es.srcValid,es.srcA,es.srcB,
			   es.dstValid,es.destE,es.destM,
			   es.valCValid,es.valC,
			   es.valPValid,es.valP);
	printReg(es.stage, es.tick,
	         es.PC-getInstructionSize(es.icode), es.icode, es.ifun,
			 es.regsValid, UNNEEDED_REG, UNNEEDED_REG,
			 es.srcValid, es.srcA, es.srcB,
			 es.dstValid, es.destE, es.destM,
			 es.valCValid, UNNEEDED_ADDR,
			 es.valPValid, UNNEEDED_ADDR,
			 getInstructionMnemonic(es.icode, es.ifun));
}

void processExecuteStage(int tick) {
    es.tick = tick;
  // This function probably remains empty but here is sample of 
  // printing

  //printReg("E", 10, 8, 2, 2, 0, UNNEEDED_REG, UNNEEDED_REG,
	//     1, 3, 4,
	//   1, 4, 0xF, 0, UNNEEDED_ADDR, 0, UNNEEDED_ADDR, getInstructionMnemonic(2,2));
}

