
//
//  tlb.c
//  A4
//
//  Created by Donald  Acton on 2017-09-30.
//  Copyright © 2017 Donald  Acton. All rights reserved.
//
#include "tlb.h"

// The following are a few hints to get you going. Feel free
// to change names, to add more functions, to change parameter types,
// to change the number of parameters, to change the return value, or to
// not use any of these functions at all. 
// 

//typedef or struct???

unsigned int num_tlb_entries= 16;
tlb_entry *tlb;


// Add code here to implement the TLB functionality
void TLBinit(uint32_t pageSize) {
    tlb = malloc(num_tlb_entries * sizeof(tlb_entry));
    for (int i = 0; i < 16; i++ ) {
        tlb[i].VPN = -1;
        tlb[i].access = 0;
    }
}

// Check to see if the virtual page number is in the in the TLB
// If it is return the physical page number otherwise return TLBFAULT;
uint32_t checkTLB(int accessCnt, uint32_t virtualPageNumber) {
    for(int i=0; i< num_tlb_entries; i++){
        if(tlb[i].VPN == virtualPageNumber){
            return tlb[i].PPN;
            
            // INCREMENT
            tlb[i].access++;

        }
    }
    return TLBFAULT;
}


// Add this mapping to the TLB
void updateTLB(int accessCnt, uint32_t virtualPageNumber, 
	       uint32_t physicalPageNumber) {
    int leastcount=0;
    int entrytochange;
    
    for(int i = 0; i < 16; i++){
        if(leastcount == 0 || tlb[i].access < 0){
                leastcount = tlb[i].access;
                entrytochange=i;
        }
    }
    /*
     for(int i=0; i< num_tlb_entries; i++){
     if(tlb[i].access < leastcount){
     leastcount= tlb[i].access;
     entrytochange = i;
     }
     }
     */
    
	// Update the LRU entry with new values
	tlb[entrytochange].VPN = virtualPageNumber;
	tlb[entrytochange].PPN = physicalPageNumber;
}

