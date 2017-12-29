//
//  decodeStage.c
//  A2
//
//  Created by Donald  Acton on 2017-10-01.
//  Copyright © 2017 Donald  Acton. All rights reserved.
//

#include "decodeStage.h"
#include "instructionSpecifications.h"
#include "executeStage.h"
#include "printInternalReg.h"

// Use this struct to store state information for this stage. By making it 
// static the structure and its fields can be accessed in this file
// but not in the reset of the program. 

static struct decodeStateStruct ds;

void setDecode(uint64_t PC, nibble icode, nibble ifun,
              nibble regsValid, nibble rA, nibble rB,
              nibble srcValid, nibble srcA, nibble srcB,
              nibble dstValid, nibble destE, nibble destM,
              nibble valCValid, uint64_t valC,
              nibble valPValid, int64_t valP) {
	ds.PC = PC;
	ds.icode = icode;
	ds.ifun = ifun;
	ds.rA = rA;
	ds.rB = rB;
	ds.valC = valC;
	ds.valP = valP;
	ds.regsValid = regsValid;
	ds.valCValid = valCValid;
	ds.valPValid = valPValid;
	srcValid = 0;
	dstValid = 0;
}

nibble getDecodeICode() {return ds.icode;}

nibble getDecodeRA() {return ds.rA;}
nibble getDecodeRB() {return ds.rB;}

void updateDecode(int stage) {
	if (stage == 0) {
	    ds.stage = "D";
		setExecute(ds.PC,ds.icode,ds.ifun,
				   ds.regsValid,ds.rA,ds.rB,
				   ds.srcValid,ds.rA,ds.rB,
				   ds.dstValid,ds.destE,ds.destM,
				   ds.valCValid,ds.valC,
				   ds.valPValid,ds.valP);
	}
	if (stage == 3) {
		ds.stage = "D E";
		setExecute(ds.PC,1,0,
				   0,UNNEEDED_REG,UNNEEDED_REG,
				   0,UNNEEDED_REG,UNNEEDED_REG,
				   0,UNNEEDED_REG,UNNEEDED_REG,
				   0,UNNEEDED_ADDR,
				   0,UNNEEDED_ADDR);
	}
	else if (stage == 4) {
		ds.stage = "D M";
		setExecute(ds.PC,1,0,
				   0,UNNEEDED_REG,UNNEEDED_REG,
				   0,UNNEEDED_REG,UNNEEDED_REG,
				   0,UNNEEDED_REG,UNNEEDED_REG,
				   0,UNNEEDED_ADDR,
				   0,UNNEEDED_ADDR);
	}
	else if (stage == 5) {
		ds.stage = "D W";
		setExecute(ds.PC,1,0,
				   0,UNNEEDED_REG,UNNEEDED_REG,
				   0,UNNEEDED_REG,UNNEEDED_REG,
				   0,UNNEEDED_REG,UNNEEDED_REG,
				   0,UNNEEDED_ADDR,
				   0,UNNEEDED_ADDR);
	}
	else {
		// stalling for no reason?
	}
	printReg(ds.stage, ds.tick,
	         ds.PC-getInstructionSize(ds.icode), ds.icode, ds.ifun,
			 ds.regsValid, UNNEEDED_REG, UNNEEDED_REG,
			 ds.srcValid, ds.rA, ds.rB,
			 ds.dstValid, ds.destE, ds.destM,
			 ds.valCValid, ds.valC,
			 ds.valPValid, ds.valP,
			 getInstructionMnemonic(ds.icode, ds.ifun));
}

void processDecodeStage(int tick) {
    ds.tick = tick;
	if (ds.icode == 2 || ds.icode == 6 || ds.icode == 3)
	    ds.destE = ds.rB;
	    ds.dstValid = 1;
	if (ds.icode == 5 || ds.icode == 11)
		ds.destM = ds.rA;
	    ds.dstValid = 1;
	
	if (ds.icode == 0, ds.icode == 1, ds.icode == 7, ds.icode == 8, ds.icode == 9)
	    ds.srcValid = 0;
	else
	    ds.srcValid = 1;
    //printReg("D W", 10, 11, 3, 0, 1, 15, 7,
	//         1, 15, 15, 
	//         1, 7, 15, 1, 0x1473817134381 , 1, 10+10, "irmovq" );

}

