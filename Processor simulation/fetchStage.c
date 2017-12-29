//
//  fetchStage.c
//  A2
//
//  Created by Donald  Acton on 2017-10-01.
//  Copyright © 2017 Donald  Acton. All rights reserved.
//

#include "fetchStage.h"
#include "instructionSpecifications.h"
#include <sys/stat.h>
#include <sys/mman.h>
#include "decodeStage.h"

// Use this struct to store state information for this stage. By making it 
// static the structure and its fields can be accessed in this file
// but not in the reset of the program. 
static struct fetchStateStruct fs;

// This maps the file into main memory so that pointers can be used to access
// the instruction directly

int initializeFetchState(int memoryFileFD, uint64_t initialPC) {
    struct stat statBuff;
    if (fstat(memoryFileFD, &statBuff)) {
        return -2;
    }
    
    // Do the actual mapping.
    fs.instBase =  mmap(0, statBuff.st_size, PROT_READ,
                                MAP_PRIVATE, memoryFileFD, 0);
    if (fs.instBase == MAP_FAILED) {
        return -3;
    }
    fs.lastAddr = fs.instBase + statBuff.st_size;
    fs.PC = initialPC;
    
    return 0;
}

void updateFetch(int stage) {
	if (stage == 0) {
		fs.stage = "F";
		fs.PC = fs.valP;
		setDecode(fs.PC, fs.icode, fs.ifun, fs.regsValid, fs.rA, fs.rB, 0, UNNEEDED_REG, UNNEEDED_REG, 0, UNNEEDED_REG, UNNEEDED_REG, fs.valCValid,fs.valC, 1,fs.valP);
	}
	else {
		if (stage == 2) {
			fs.stage = "F D";
		}
		else if (stage == 3) {
			fs.stage = "F E";
		}
		else if (stage == 4) {
			fs.stage = "F M";
		}
		else if (stage == 5) {
			fs.stage = "F W";
		}
		else {
			// stalling for no reason?
		}
		//printf("update fetch fail\n");
	}
	printReg(fs.stage, fs.tick , fs.PC-getInstructionSize(fs.icode), fs.icode, fs.ifun, fs.regsValid, fs.rA, fs.rB, 0, UNNEEDED_REG,UNNEEDED_REG , 0, UNNEEDED_REG,UNNEEDED_REG, fs.valCValid, fs.valC,
             1,fs.valP, fs.instr);
}

void processFetchStage(int tick) {
    fs.tick = tick;
  // Add code to perform fetch stage processing.
    fs.icode = 0;
    fs.ifun = 0;
    fs.rA = 0;
    fs.rB = 0;
    fs.regsValid = 0;
    fs.valC = 0;
    fs.valCValid = 0;
    fs.byte0 = 0;
	fs.byte1 = 0;
	fs.byte2 = 0;
	fs.byte3 = 0;
	fs.byte4 = 0;
	fs.byte5 = 0;
	fs.byte6 = 0;
	fs.byte7 = 0;
    fs.valP = 0;
    
	//int haltCount = 0;
    
    fs.icode = fs.instBase[fs.PC]>>4;
    fs.ifun = fs.instBase[fs.PC] & 15 ;
    
    if(validInstruction(fs.icode)== 1 || validFunctionCode(fs.icode, fs.ifun) == 1){
        
        
        if (fs.icode != 0){
            //haltCount = 0;
        }
        int inslen= getInstructionSize(fs.icode);
        fs.instr = getInstructionMnemonic(fs.icode,fs.ifun);
        fs.valP = fs.PC + inslen;
        
        // halt
        if (fs.icode == 0) {
            //haltCount++;
            // nop
        } else if (fs.icode == 1) {
            
            // rrmovq rA, rB
        } else if (fs.icode == 2) {
            fs.regsValid=1;
            
            // irmovq V, rB
        } else if (fs.icode == 3) {
            fs.valCValid = 10;
            fs.regsValid= 1;
            
            // rmmovq rA, D(rB)
        } else if (fs.icode == 4) {
            fs.valCValid = 10;
            fs.regsValid= 1;
            
            // mrmovq D(rB), rA
        } else if (fs.icode == 5) {
            fs.valCValid = 10;
            fs.regsValid= 1;
            
            // OPI rA, rB
        } else if (fs.icode == 6) {
            fs.regsValid= 1;
            // jXX Dest
        } else if (fs.icode == 7) {
            fs.valCValid = 9;
            
            
            // call Dest
        } else if (fs.icode == 8) {
            fs.valCValid = 9;
            
            // ret
        } else if (fs.icode == 9) {
            
            // pushq rA
        } else if (fs.icode == 10) {
            fs.regsValid= 1;
            
            // popq rA
        } else if (fs.icode == 11) {
            fs.regsValid= 1;
        }
        if (fs.regsValid > 0) {
            fs.rA = fs.instBase[fs.PC]>>4;
            fs.rB = fs.instBase[fs.PC] & 15 ;
        }
        // valCValid = 9: Start reading bytes from Op with 9 bytes, call Dest
        // valCValid = 10: Start reading bytes from Op with 10 bytes, irmovq V, rB
        if (fs.valCValid > 0) {
            int c = 1;
            int start= fs.valCValid-8;
            
            if (c > fs.valCValid-2) {
                fs.byte0 = 0;
            } else {
                fs.byte0 = fs.instBase[start++];
            }
            c+=1;
            if (c > fs.valCValid-2) {
                fs.byte1 = 0;
            } else {
                fs.byte1 = fs.instBase[start++];
            }
            c+=1;
            if (c > fs.valCValid-2) {
                fs.byte2 = 0;
            } else {
                fs.byte2 = fs.instBase[start++];
            }
            c+=1;
            if (c > fs.valCValid-2) {
                fs.byte3 = 0;
            } else {
                fs.byte3 = fs.instBase[start++];
            }
            c+=1;
            if (c > fs.valCValid-2) {
                fs.byte4 = 0;
            } else {
                fs.byte4 = fs.instBase[start++];
            }
            c+=1;
            if (c > fs.valCValid-2) {
                fs.byte5 = 0;
            } else {
                fs.byte5 = fs.instBase[start++];
            }
            c+=1;
            if (c > fs.valCValid-2) {
                fs.byte6 = 0;
            } else {
                fs.byte6 = fs.instBase[start++];
            }
            c+=1;
            if (c > fs.valCValid-2) {
                fs.byte7 = 0;
            } else {
                fs.byte7 = fs.instBase[start++];
            }
            
            fs.valC = (uint64_t)fs.byte0 | (uint64_t)fs.byte1 << 8 | (uint64_t)fs.byte2 << 16 | (uint64_t)fs.byte3 << 32 | (uint64_t)fs.byte4 << 40 | (uint64_t)fs.byte5 << 48 | (uint64_t)fs.byte6 << 54 | (uint64_t)fs.byte7 << 62;
        }
        
    }
    else{
        printf("opcode %2X%2X at 0x%08llX is invalid", fs.icode, fs.ifun, fs.PC);
    }
  // The following line illustrates how to print information for this stage. 
  // This is an example only and you should delete it or replace it with an 
  // appropriate one. 

  // This example is for a stall
  //printReg("F W", 10, 0x100, 6,  2, 1, 10, 11,
	//   1, 10, 11,
	//   0, UNNEEDED_REG, UNNEEDED_REG, 1, 0, 1, 12, "andq");
  // Something that uses valC
  //printReg("F", 12, 100, 7,  0, 1, 0xf , 0xF,
	//   0, UNNEEDED_REG, UNNEEDED_REG, 
	//   0, UNNEEDED_REG, UNNEEDED_REG, 1, 0x1234567811, 1, 109, 
    //       getInstructionMnemonic(7, 0));
}
