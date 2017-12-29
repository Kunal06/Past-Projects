//
//  main.c
//  A2
//
//  Created by Donald  Acton on 2017-09-30.
//  Copyright © 2017 Donald  Acton. All rights reserved.
//

#include <stdio.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/uio.h>
#include <unistd.h>
#include <inttypes.h>
#include <stdlib.h>
#include <errno.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include "printInternalReg.h"
#include "fetchStage.h"
#include "instructionSpecifications.h"
#include "decodeStage.h"
#include "executeStage.h"
#include "memoryStage.h"
#include "writeBackStage.h"

int main(int argc, char **argv) {
    
    int machineCodeFD = -1;       // File descriptor of file with object code
    
    // Verify that the command line has an appropriate number
    // of arguments
    
    if (argc < 2 || argc > 4) {
        printf("Usage: %s InputFilename [startingOffset] [ClockTicks]\n", argv[0]);
        return ERROR_RETURN;
    }
    
    // First argument is the file to open, attempt to open it
    // for reading and verify that the open did occur.
    machineCodeFD = open(argv[1], O_RDONLY);
    
    if (machineCodeFD < 0) {
        printf("Failed to open: %s\n", argv[1]);
        return ERROR_RETURN;
    }
    
    // Get the initial offset into the file
    
    uint64_t PC = 0;
    if (3 <= argc) {
        // See man page for strtol() as to why
        // we check for errors by examining errno
        errno = 0;
        PC = strtol(argv[2], NULL, 0);
        if (errno != 0) {
            perror("Invalid offset on command line");
            return ERROR_RETURN;
        }
    }
    
    // Get the count of how many clock ticks to run for.
    int endTime = -1;
    if (4 == argc) {
        errno = 0;
        endTime = strtol(argv[3], NULL, 0);
        if (errno != 0) {
            perror("Invalid end time on command line");
            return ERROR_RETURN;
        }
    }
    
    if (initializeFetchState(machineCodeFD, PC)) {
        printf("Initialization of fetch stage failed\n");
        return -99;
    };
   
    printf("Opened %s, starting offset 0x%016llX stop time = %d\n",
	   argv[1], PC, endTime);

    int OK = 1;
	
	setDecode(1,1,0,
				   0,UNNEEDED_REG,UNNEEDED_REG,
				   0,UNNEEDED_REG,UNNEEDED_REG,
				   0,UNNEEDED_REG,UNNEEDED_REG,
				   0,UNNEEDED_ADDR,
				   0,UNNEEDED_ADDR);
	setExecute(1,1,0,
				   0,UNNEEDED_REG,UNNEEDED_REG,
				   0,UNNEEDED_REG,UNNEEDED_REG,
				   0,UNNEEDED_REG,UNNEEDED_REG,
				   0,UNNEEDED_ADDR,
				   0,UNNEEDED_ADDR);
	setMemory(1,1,0,
				   0,UNNEEDED_REG,UNNEEDED_REG,
				   0,UNNEEDED_REG,UNNEEDED_REG,
				   0,UNNEEDED_REG,UNNEEDED_REG,
				   0,UNNEEDED_ADDR,
				   0,UNNEEDED_ADDR);
	setWriteBack(1,1,0,
				   0,UNNEEDED_REG,UNNEEDED_REG,
				   0,UNNEEDED_REG,UNNEEDED_REG,
				   0,UNNEEDED_REG,UNNEEDED_REG,
				   0,UNNEEDED_ADDR,
				   0,UNNEEDED_ADDR);

    for (int clock = 0; OK ; clock++) {
      processWriteBackStage(clock);
      processMemoryStage(clock);
      processExecuteStage(clock);
      processDecodeStage(clock);
      processFetchStage(clock);      
      printf("\n");
      // Here might be a good spot to add code or make function calls 
      // to check for hazards cause stage registers to be updated etc.
	  
	  /*if (ds.icode == 2) {
        if (es.icode == 2 || es.icode == 3 || es.icode == 6)
		  if (ds.rA == es.dstE)
		    //stall for E
		if (es.icode == 5 || es.icode == 11)
		  if (ds.rA == es.destM)
		    //stall for M
	  }*/
        
	  // DECODE STALLS
      if (getDecodeICode() == 2) {
          if (getExecuteICode() == 2 || getExecuteICode() == 3 || getExecuteICode() == 6 ||
		      getExecuteICode() == 5 || getExecuteICode() == 11){
              
              if(getDecodeRA() == getExecuteDestE() || getDecodeRA() == getExecuteSrcB() ||
			     getDecodeRB() == getExecuteSrcA() || getDecodeRB() == getExecuteSrcB() ){
                  updateWriteBack(0);
				  updateMemory(0);
				  updateExecute(0);
				  updateDecode(3);
				  updateFetch(3);
				  printf("\n");
              }
          }
          if (getMemoryICode() == 2 || getMemoryICode() == 3 || getMemoryICode() == 6 ||
		      getMemoryICode() == 5 || getMemoryICode() == 11){

              if(getDecodeRA() == getMemoryDestE() || getDecodeRA() == getMemoryDestM() ||
			     getDecodeRB() == getMemoryDestE() || getDecodeRB() == getMemoryDestM() ){
                  updateWriteBack(0);
				  updateMemory(0);
				  updateExecute(0);
				  updateDecode(4);
				  updateFetch(4);
				  printf("\n");
              }
          }
          if (getWriteBackICode() == 2 || getWriteBackICode() == 3 || getWriteBackICode() == 6 ||
		      getWriteBackICode() == 5 || getWriteBackICode() == 11){

              if(getDecodeRA() == getWriteBackDestE() || getDecodeRA() == getWriteBackDestM() ||
			     getDecodeRB() == getWriteBackDestE() || getDecodeRB() == getWriteBackDestM() ){
                  updateWriteBack(0);
				  updateMemory(0);
				  updateExecute(0);
				  updateDecode(5);
				  updateFetch(5);
				  printf("\n");
              }
          }
      }
	  else if (getDecodeICode() == 4) {
          if (getExecuteICode() == 2 || getExecuteICode() == 3 || getExecuteICode() == 6 ||
		      getExecuteICode() == 5 || getExecuteICode() == 11){
              
              if(getDecodeRA() == getExecuteDestE() || getDecodeRA() == getExecuteSrcB() ||
			     getDecodeRB() == getExecuteSrcA() || getDecodeRB() == getExecuteSrcB() ){
                  updateWriteBack(0);
				  updateMemory(0);
				  updateExecute(0);
				  updateDecode(3);
				  updateFetch(3);
				  printf("\n");
              }
          }
          if (getMemoryICode() == 2 || getMemoryICode() == 3 || getMemoryICode() == 6 ||
		      getMemoryICode() == 5 || getMemoryICode() == 11){

              if(getDecodeRA() == getMemoryDestE() || getDecodeRA() == getMemoryDestM() ||
			     getDecodeRB() == getMemoryDestE() || getDecodeRB() == getMemoryDestM() ){
                  updateWriteBack(0);
				  updateMemory(0);
				  updateExecute(0);
				  updateDecode(4);
				  updateFetch(4);
				  printf("\n");
              }
          }
          if (getWriteBackICode() == 2 || getWriteBackICode() == 3 || getWriteBackICode() == 6 ||
		      getWriteBackICode() == 5 || getWriteBackICode() == 11){

              if(getDecodeRA() == getWriteBackDestE() || getDecodeRA() == getWriteBackDestM() ||
			     getDecodeRB() == getWriteBackDestE() || getDecodeRB() == getWriteBackDestM() ){
                  updateWriteBack(0);
				  updateMemory(0);
				  updateExecute(0);
				  updateDecode(5);
				  updateFetch(5);
				  printf("\n");
              }
          }
	  }
	  else if (getDecodeICode() == 6) {
          if (getExecuteICode() == 2 || getExecuteICode() == 3 || getExecuteICode() == 6 ||
		      getExecuteICode() == 5 || getExecuteICode() == 11){
              
              if(getDecodeRA() == getExecuteDestE() || getDecodeRA() == getExecuteSrcB() ||
			     getDecodeRB() == getExecuteSrcA() || getDecodeRB() == getExecuteSrcB() ){
                  updateWriteBack(0);
				  updateMemory(0);
				  updateExecute(0);
				  updateDecode(3);
				  updateFetch(3);
				  printf("\n");
              }
          }
          if (getMemoryICode() == 2 || getMemoryICode() == 3 || getMemoryICode() == 6 ||
		      getMemoryICode() == 5 || getMemoryICode() == 11){

              if(getDecodeRA() == getMemoryDestE() || getDecodeRA() == getMemoryDestM() ||
			     getDecodeRB() == getMemoryDestE() || getDecodeRB() == getMemoryDestM() ){
                  updateWriteBack(0);
				  updateMemory(0);
				  updateExecute(0);
				  updateDecode(4);
				  updateFetch(4);
				  printf("\n");
              }
          }
          if (getWriteBackICode() == 2 || getWriteBackICode() == 3 || getWriteBackICode() == 6 ||
		      getWriteBackICode() == 5 || getWriteBackICode() == 11){

              if(getDecodeRA() == getWriteBackDestE() || getDecodeRA() == getWriteBackDestM() ||
			     getDecodeRB() == getWriteBackDestE() || getDecodeRB() == getWriteBackDestM() ){
                  updateWriteBack(0);
				  updateMemory(0);
				  updateExecute(0);
				  updateDecode(5);
				  updateFetch(5);
				  printf("\n");
              }
          }
	  }
	  else if (getDecodeICode() == 10) {
          if (getExecuteICode() == 2 || getExecuteICode() == 3 || getExecuteICode() == 6 ||
		      getExecuteICode() == 5 || getExecuteICode() == 11){
              
              if(getDecodeRA() == getExecuteDestE() || getDecodeRA() == getExecuteSrcB() ||
			     getDecodeRB() == getExecuteSrcA() || getDecodeRB() == getExecuteSrcB() ){
                  updateWriteBack(0);
				  updateMemory(0);
				  updateExecute(0);
				  updateDecode(3);
				  updateFetch(3);
				  printf("\n");
              }
          }
          if (getMemoryICode() == 2 || getMemoryICode() == 3 || getMemoryICode() == 6 ||
		      getMemoryICode() == 5 || getMemoryICode() == 11){

              if(getDecodeRA() == getMemoryDestE() || getDecodeRA() == getMemoryDestM() ||
			     getDecodeRB() == getMemoryDestE() || getDecodeRB() == getMemoryDestM() ){
                  updateWriteBack(0);
				  updateMemory(0);
				  updateExecute(0);
				  updateDecode(4);
				  updateFetch(4);
				  printf("\n");
              }
          }
          if (getWriteBackICode() == 2 || getWriteBackICode() == 3 || getWriteBackICode() == 6 ||
		      getWriteBackICode() == 5 || getWriteBackICode() == 11){

              if(getDecodeRA() == getWriteBackDestE() || getDecodeRA() == getWriteBackDestM() ||
			     getDecodeRB() == getWriteBackDestE() || getDecodeRB() == getWriteBackDestM() ){
                  updateWriteBack(0);
				  updateMemory(0);
				  updateExecute(0);
				  updateDecode(5);
				  updateFetch(5);
				  printf("\n");
              }
          }
	  }
	  // MEMORY STALLS
      if (getDecodeICode() == 5) {
          if (getExecuteICode() == 4 || getExecuteICode() == 10){
              
              if(getDecodeRA() == getExecuteDestE() || getDecodeRA() == getExecuteSrcB() ||
			     getDecodeRB() == getExecuteSrcA() || getDecodeRB() == getExecuteSrcB() ){
                  updateWriteBack(0);
				  updateMemory(0);
				  updateExecute(0);
				  updateDecode(3);
				  updateFetch(3);
				  printf("\n");
              }
          }
          if (getMemoryICode() == 4 || getMemoryICode() == 10){

              if(getDecodeRA() == getMemoryDestE() || getDecodeRA() == getMemoryDestM() ||
			     getDecodeRB() == getMemoryDestE() || getDecodeRB() == getMemoryDestM() ){
                  updateWriteBack(0);
				  updateMemory(0);
				  updateExecute(0);
				  updateDecode(4);
				  updateFetch(4);
				  printf("\n");
              }
          }
          if (getWriteBackICode() == 4 || getWriteBackICode() == 10){

              if(getDecodeRA() == getWriteBackDestE() || getDecodeRA() == getWriteBackDestM() ||
			     getDecodeRB() == getWriteBackDestE() || getDecodeRB() == getWriteBackDestM() ){
                  updateWriteBack(0);
				  updateMemory(0);
				  updateExecute(0);
				  updateDecode(5);
				  updateFetch(5);
				  printf("\n");
              }
          }
      }
	  else if (getDecodeICode() == 11) {
          if (getExecuteICode() == 4 || getExecuteICode() == 10){
              
              if(getDecodeRA() == getExecuteDestE() || getDecodeRA() == getExecuteSrcB() ||
			     getDecodeRB() == getExecuteSrcA() || getDecodeRB() == getExecuteSrcB() ){
                  updateWriteBack(0);
				  updateMemory(0);
				  updateExecute(0);
				  updateDecode(3);
				  updateFetch(3);
				  printf("\n");
              }
          }
          if (getMemoryICode() == 4 || getMemoryICode() == 10){

              if(getDecodeRA() == getMemoryDestE() || getDecodeRA() == getMemoryDestM() ||
			     getDecodeRB() == getMemoryDestE() || getDecodeRB() == getMemoryDestM() ){
                  updateWriteBack(0);
				  updateMemory(0);
				  updateExecute(0);
				  updateDecode(4);
				  updateFetch(4);
				  printf("\n");
              }
          }
          if (getWriteBackICode() == 4 || getWriteBackICode() == 10){

              if(getDecodeRA() == getWriteBackDestE() || getDecodeRA() == getWriteBackDestM() ||
			     getDecodeRB() == getWriteBackDestE() || getDecodeRB() == getWriteBackDestM() ){
                  updateWriteBack(0);
				  updateMemory(0);
				  updateExecute(0);
				  updateDecode(5);
				  updateFetch(5);
				  printf("\n");
              }
          }
	  }
	  // NO STALLS
      //else {
		  updateWriteBack(0);
		  updateMemory(0);
		  updateExecute(0);
		  updateDecode(0);
		  updateFetch(0);
	  //}
        
      if (endTime != -1) {
	if (clock >= endTime) {
	  printf("Time expired %d %d \n", clock, endTime);
	  OK = 0;
	}
      }
    }
    printf("Normal termination\n");
    return SUCCESS;
}


