               Your program, named vmm,  will take 4 command line parameters as indicated below

vmm BackingStore.bin addresses  PhysicalAddressSize  PageSize

where

BackingStore.bin - this represents the backing store on disk where the "image" of the running process is stored.  This is provided in the Assignment 4 module area.

addresses - a file containing, one per line, the logical address to be looked up. A sample is provided in the Assignment 4 module area.

PhysicalAddressSize - the number of bits in a physical address. This value must be less than or equal to 16. For example if the physical address space is 2^{16} bytes then the value would be 16

PageSize - the number of bits that make up the page size. (Recall that the page size is the same for both the virtual addresses and physical addresses.)

The program is to read the virtual addresses from the file name provided on the command line. The file will contain one address per line. Your program will then simulate reading one byte from the given address in a virtual memory system  (You do not have to worry about writing to pages and the subsequent processing that would entail.)  Here is quick summary of the system specifics:

The number of page table entries is determined by dividing the size of the virtual address space by the size of a page.
The page size is specified on the command line.  Recall that the page size and physical frame size are always the same.
The TLB is to be fully associative with 16 entries. The TLB cache line replacement strategy is to be a strict least recently used one.
Provided Files

coverpage.txt - The coverpage file that you are to fill out when the assignment is handed in.
main.c -  Where the main program starts
main.h - Some defines that could be useful across the system, feel free to add more stuff
Makefile - The makefile to build vmm - DO NOT BREAK THIS
paging.c - Put code associated with the paging functions in this file . You must use the pagein() function provided here to bring in a page from the backing store and into the area representing physical memory
paging.h - Put constants relevant to paging and any structure and paging related typedefs in here.
tlb.c -Put code associated with the TLB functions in this file .
tlb.h - Put constants relevant to TLB operation and paging and any structure and TLB related typedefs in here. 