
//
//  paging.c
//  A4
//
//  Created by Donald  Acton on 2017-09-30.
//  Copyright © 2017 Donald  Acton. All rights reserved.
//

#include "main.h"
#include "paging.h"
#include <sys/types.h>
#include <sys/uio.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

// You must use this function to pagein a page from the
// backing store . The function takes the
// virtualPageNumber (not the virtual address) and reads the page in 
// from that location and puts it into the page location specified 
// by the physical page number. Access to the backing store (i.e. paging file)
// is through the file descriptor passed in the parameter bakingStorefd. 
// The size of the page is given in pageSize. (Hint pass in the the page size in bytes)
// The value returned is 1 if it worked and 0 if it failed. Note that one
// possible failure is the result of a read error, so be sure to check for
// that.

struct PageTable table;

int pagein(uint32_t virtualPageNumber,
	   uint32_t physicalPageNumber,
	   char * physicalMemoryStart,
	   uint32_t  pageSize,
	   int  backingStorefd ) {
    
//    offset in memory is calculated by multiplying the physical page number and page size
    int offset= physicalPageNumber * pageSize;
    char* writememory = &physicalMemoryStart[offset];
    
//    if lseek returns -1 then there was an error moving the pointer
    if (lseek(backingStorefd, (virtualPageNumber* pageSize), SEEK_SET) < 0) {
        return 0;
    }
//  read returns number of bytes read. If the number of bytes read is less than pageSize then data is not present
    if (read(backingStorefd, writememory, pageSize) != pageSize)  {
        return 0;
    }
    return 1;

}
void initializePageSystem(uint32_t pageSize) {
    // #PageTable entries = Sizeof(Virtual Address)/ Sizeof(Page)
    table.pt_num_entries = 1 << (16 - pageSize);
    table.entry = malloc(table.pt_num_entries * sizeof(pagetable_entry));
    
    // Assign 0 to present and access for all entries
    for (int i = 0; i < table.pt_num_entries; i++ ) {
        table.entry[i].present = 0;
        table.entry[i].access = 0;
    }
}

uint32_t checkPageTable(uint32_t VPN) {
    pagetable_entry entry = table.entry[VPN];
    
    //Check if data is present
    if (entry.present==1) {
        return entry.page_frame;
    } else {
        return MISS;
    }
}

uint32_t LRUpage(){
    
    int leastCount = 1000000;
    int entrytochange = -1;
    
    for ( int i = 0; i < table.pt_num_entries; i++ ) {
        if (table.entry[i].access < leastCount && table.entry[i].present) {
            entrytochange = i;
            leastCount = table.entry[i].access;
        }
    }
    table.entry[entrytochange].present = 0;
    return table.entry[entrytochange].page_frame;
}

void updateTable(uint32_t VPN, uint32_t PPN, int count){
    
    table.entry[VPN].page_frame = PPN;
    table.entry[VPN].present = 1;
    table.entry[VPN].access = count;
}
