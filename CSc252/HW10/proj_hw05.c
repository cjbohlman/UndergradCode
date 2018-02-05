/* File: proj_hw05.c
 * Author: Chris Bohlman
 * Purpose: Implement a pipelined CPU with many functions to symbolize the pipeline processes
*/
#include "proj_hw05.h"

/******************
 * Func: void extract_instructionFields(WORD instruction, InstructionFields *fieldsOut)
 * extracts various instruction fields from the instruction word into a struct.
 * extracts opcode, rs, rt, rd, shamt, funct, imm16, and address, even if the fields aren't used.
 * sign extends imm16 and puts result in imm32.
******************/
void extract_instructionFields(WORD instruction, InstructionFields *fieldsOut) {
	//just fill it all in with bit masking and shifting
	fieldsOut->opcode = (instruction & 0xfc000000) >> 26;
        fieldsOut->rs = (instruction & 0x03e00000) >> 21;
        fieldsOut->rt = (instruction & 0x001f0000) >> 16;
        fieldsOut->rd = (instruction & 0x0000f800) >> 11;
        fieldsOut->shamt = (instruction & 0x000007c0) >> 6;
        fieldsOut->funct = (instruction & 0x0000003f);
        fieldsOut->imm16 = (instruction & 0x0000ffff);
	fieldsOut->imm32 = signExtend16to32(fieldsOut->imm16);
        fieldsOut->address = (instruction & 0x03ffffff);
}

/******************
 * Func: int IDtoIF_get_stall(InstructionFields *fields, ID_EX *old_idex)
 * determines if a stall is needed for the next instruction cycle.
 * if old inst is a lw and a dependency is detected, it will need to stall, so return 1
 * else return 0
******************/
int IDtoIF_get_stall(InstructionFields *fields, ID_EX *old_idex) {
	if (old_idex->memRead == 0) {
		return 0;
	}
	// just rs
	if (fields->opcode == 0x0d || fields->opcode == 0x08 || fields->opcode == 0x09 || fields->opcode == 0x0a || fields->opcode == 0x0c || fields->opcode == 0x2b || fields->opcode == 0x23 ) {
		if (old_idex->rt == fields->rs) {
                        return 1;
                }
                else {
                        return 0;
                }
	}
	
	// r format
	if (fields->opcode == 0x04 || fields->opcode == 0x05 || fields->opcode == 0x00 && (fields->funct == 32 || fields->funct == 33 || fields->funct == 34 || fields->funct == 35 || fields->funct == 36 || fields->funct == 37 || fields->funct == 39 || fields->funct == 42)) {
		if (old_idex->rt == fields->rt || old_idex->rt == fields->rs) {
                	return 1;
        	}
        	else {
                	return 0;
        	}	
	}
	else return 0;
}

/******************
 * Func: int IDtoIF_get_branchControl(InstructionFields *fields, WORD rsVal, WORD rtVal)
 * determines what to do if instruction is a branch or jump
 * if you do need to branch then return 1
 * if you need to jump, then return 2
 * else return 0
******************/
int IDtoIF_get_branchControl(InstructionFields *fields, WORD rsVal, WORD rtVal) {
	//beq
	if (fields->opcode == 0x04) {
		if (rsVal == rtVal) return 1;
		else return 0;
	}
	//bne
	else if (fields->opcode == 0x05) {
		if (rsVal != rtVal) return 1;
                else return 0;
	}
	//jump
	else if (fields->opcode == 0x02) {
		return 2;
	}
	//no
	else return 0;	
}

/******************
 * Func: WORD calc_branchAddr
 * determines where to branch to, and returns that relative address
******************/
WORD calc_branchAddr(WORD pcPlus4, InstructionFields *fields) {
	//branch
	WORD tmp = fields->imm32;
        tmp = tmp << 2;
        tmp = tmp + pcPlus4;
        return tmp;
}

/******************
 * Func: WORD calc_jumpAddr
 * determines where to jump to, and returns that absolute address
******************/
WORD calc_jumpAddr(WORD pcPlus4, InstructionFields *fields) {
	//jump
        WORD tmp = fields->address << 2;
        tmp = tmp + (pcPlus4 & 0xf0000000);
        return tmp;
}

/******************
 * Func: int execute_ID
 * determines what to put in each of ID's fields based off of the opcode and funct code
 * if invalid function, return 0
 * else, return 1
 * handles support for nop, as well as lui and nor
******************/
int execute_ID(int IDstall,
               InstructionFields *fieldsIn,
               WORD rsVal, WORD rtVal,
               ID_EX *new_idex) {
	
	//check for nop
        if (fieldsIn->opcode == 0 && fieldsIn->funct == 0 &&  fieldsIn->rs == 0 && fieldsIn->rt == 0 && fieldsIn->rd == 0 && fieldsIn-> shamt == 0 && fieldsIn->imm16 == 0) {
		//if (fieldsIn->opcode == 0 && fieldsIn->funct == 0 &&  fieldsIn->rs == 0 && fieldsIn->rt == 0 && fieldsIn->rd == 0 && fieldsIn-> shamt == 0 && fieldsIn->imm16 == 0) {
                new_idex->rs = 0;
                new_idex->rt = 0;
                new_idex->rd = 0;
                new_idex->rsVal = 0;
                new_idex->rtVal = 0;
                new_idex->ALUsrc = 0;
                new_idex->ALU.op = 4;
                new_idex->ALU.bNegate = 0;
                new_idex->regDst = 1;
                new_idex->regWrite = 1;
                new_idex->memWrite = 0;
                new_idex->memRead = 0;
                new_idex->memToReg = 0;
                new_idex->imm16 = 0;
                new_idex->imm32 = 0;
                new_idex->extra1 = 0;
                new_idex->extra2 = 0;
                new_idex->extra3 = 0;
		return 1;
        }

	//check for invalid instruction
        if (!((fieldsIn->opcode == 0 && fieldsIn->funct == 0) || fieldsIn->opcode == 0x02 || fieldsIn->opcode == 0x0d  || fieldsIn->opcode == 0x0f || fieldsIn->opcode == 0x04 ||  fieldsIn->opcode == 0x05 || fieldsIn->opcode == 0x08 || fieldsIn->opcode == 0x09 || fieldsIn->opcode == 0x0a || fieldsIn->opcode == 0x0c || fieldsIn->opcode == 0x23 || fieldsIn->opcode == 0x2b || fieldsIn->opcode == 0x00 && (fieldsIn->funct == 32 || fieldsIn->funct == 33 || fieldsIn->funct == 34 || fieldsIn->funct == 35 || fieldsIn->funct == 36 || fieldsIn->funct == 37 || fieldsIn->funct == 39 || fieldsIn->funct == 42))) return 0;
	
	// check for stupid data hazard??
	if (IDstall == 1) {
		new_idex->rs = 0;
                new_idex->rt = 0;
                new_idex->rd = 0;
		new_idex->rsVal = 0;
                new_idex->rtVal = 0;
		new_idex->ALUsrc = 0;
		new_idex->ALU.op = 0;
		new_idex->ALU.bNegate = 0;
		new_idex->regDst = 0;
		new_idex->regWrite = 0;
		new_idex->memWrite = 0;
		new_idex->memRead = 0;
		new_idex->memToReg = 0;
		new_idex->imm16 = 0;
		new_idex->imm32 = 0;
		new_idex->extra1 = 0;
		new_idex->extra2 = 0;
		new_idex->extra3 = 0;
		return 1;
	}
	
	// regiser setting	
	if (fieldsIn->opcode == 0x02 || fieldsIn->opcode == 0x04 ||  fieldsIn->opcode == 0x05) {
		new_idex->rs = 0;
        	new_idex->rt = 0;
        	new_idex->rd = 0;
	}
	else {
		new_idex->rs = fieldsIn->rs;
        	new_idex->rt = fieldsIn->rt;
        	new_idex->rd = fieldsIn->rd;
	}	

        if (fieldsIn->opcode == 0x02 || fieldsIn->opcode == 0x04 ||  fieldsIn->opcode == 0x05) {
		new_idex->rsVal = 0;
                new_idex->rtVal = 0;
	}	
	else {	
		new_idex->rsVal = rsVal;
		new_idex->rtVal = rtVal;
	}
	
	// immediate set
        new_idex->imm16 = fieldsIn->imm16;
	new_idex->imm32 = fieldsIn->imm32;
	
	// ALU src
        if (fieldsIn->opcode == 0x08 || fieldsIn->opcode == 0x09 || fieldsIn->opcode == 0x0a || fieldsIn->opcode == 0x23 || fieldsIn->opcode == 0x2b || fieldsIn->opcode == 0x0f) {
		new_idex->ALUsrc = 1;
        }
	else if (fieldsIn->opcode == 0x0c || fieldsIn->opcode == 0x0d) {
		new_idex->ALUsrc = 2;
	}
        else {
                new_idex->ALUsrc = 0;
        }
 	
	//alu op
        if ((fieldsIn->opcode == 0x00 && fieldsIn->funct == 37) || fieldsIn->opcode == 0x0d || (fieldsIn->opcode == 0x00 && fieldsIn->funct == 39)) {
                new_idex->ALU.op = 1;
        }
        else if (fieldsIn->opcode == 0x08 || fieldsIn->opcode == 0x23 || fieldsIn->opcode == 0x2b || fieldsIn->opcode == 0x09) {
                new_idex->ALU.op = 2;
        }
        else if (fieldsIn->opcode == 0x00 && (fieldsIn->funct == 32 || fieldsIn->funct == 34 || fieldsIn->funct == 35  || fieldsIn->funct == 33)) {
                new_idex->ALU.op = 2;
        }
        else if (fieldsIn->opcode == 0x0a) {
                new_idex->ALU.op = 3;
        }
        else if (fieldsIn->opcode == 0x00 && fieldsIn->funct == 42) {
                new_idex->ALU.op = 3;
        }
        else {
                new_idex->ALU.op = 0;
        }

        //bnegate
        if (fieldsIn->opcode == 0x0a) {
                new_idex->ALU.bNegate = 1;
        }
        else if (fieldsIn->opcode == 0x00 && (fieldsIn->funct == 34 || fieldsIn->funct == 35 || fieldsIn->funct == 42)) {
                new_idex->ALU.bNegate = 1;
        }
        else {
                new_idex->ALU.bNegate = 0;
        }

	//memwrite
        if (fieldsIn->opcode == 0x2b) {
                new_idex->memWrite = 1;
        }
        else {
                new_idex->memWrite = 0;
        }

        //memread
        if (fieldsIn->opcode == 0x23) {
                new_idex->memRead = 1;
        }
        else {
                new_idex->memRead = 0;
        }

        //mem to reg
        if (fieldsIn->opcode == 0x23) {
                new_idex->memToReg = 1;
        }
        else {
                new_idex->memToReg = 0;
        }

	 //regdst
        if (fieldsIn->opcode == 0x00 && (fieldsIn->funct == 32 || fieldsIn->funct == 35 || fieldsIn->funct == 33 || fieldsIn->funct == 34 || fieldsIn->funct == 36 || fieldsIn->funct == 37 || fieldsIn->funct == 39 || fieldsIn->funct == 42)) {
                new_idex->regDst = 1;
        }
        else {
                new_idex->regDst = 0;
        }

        //regwrite
        if (fieldsIn->opcode == 0x08 || fieldsIn->opcode == 0x0a || fieldsIn->opcode == 0x0c || fieldsIn->opcode == 0x0d || fieldsIn->opcode == 0x23 || fieldsIn->opcode == 0x09 || fieldsIn->opcode == 0x0f) {
                new_idex->regWrite = 1;
        }
        else if (fieldsIn->opcode == 0x00 && (fieldsIn->funct == 32 || fieldsIn->funct == 33 || fieldsIn->funct == 35 || fieldsIn->funct == 34 || fieldsIn->funct == 36 || fieldsIn->funct == 37 || fieldsIn->funct == 39 || fieldsIn->funct == 42 )) {
                new_idex->regWrite = 1;
        }
        else {
                new_idex->regWrite = 0;
        }

	//extra inst: nor
        if (fieldsIn->opcode == 0x00 && fieldsIn->funct == 39) {
                new_idex->extra1 = 1;
        }
        else {
                new_idex->extra1 = 0;
        }
	
	//extra inst: lui
        if (fieldsIn->opcode == 0x0f) {
                new_idex->extra2 = 1;
        }
        else {
                new_idex->extra2 = 0;
        }


	return 1;
}

/******************
 * Func: WORD EX_getALUinput1
 * determines what first alu input is based off of dependencies
 * returns it
******************/
WORD EX_getALUinput1(ID_EX *in, EX_MEM *old_exMem, MEM_WB *old_memWb) {
	// if old pipelines needs to wb to a register
	if (old_exMem->regWrite) {
		//checks dependencies
		if (in->rs == old_exMem->writeReg) return old_exMem->aluResult;
	}
	if (old_memWb->regWrite) {
		//checks dependencies
                if (in->rs == old_memWb->writeReg) return old_memWb->aluResult;
        }
	return in->rsVal;
}

/******************
 * Func: WORD EX_getALUinput2
 * determines what second alu input is based off of dependencies or the opcode of the instruction
 * returns it
******************/
WORD EX_getALUinput2(ID_EX *in, EX_MEM *old_exMem, MEM_WB *old_memWb) {
	if (in->ALUsrc == 1) return in->imm32;
        if (in->ALUsrc == 2) return in->imm16;

	if (old_exMem->regWrite) {
                if (in->rt == old_exMem->writeReg) return old_exMem->aluResult;
        }
        if (old_memWb->regWrite) {
                if (in->rt == old_memWb->writeReg) return old_memWb->aluResult;
        }
        return in->rtVal;
}

/******************
 * Func: void execute_EX
 * executes ALU depending on instruction.
 * Also handles support for lui and nor
******************/
void execute_EX(ID_EX *in, WORD input1, WORD input2,
                EX_MEM *new_exMem) {
	
	if (in->ALU.op == 4) { 
		new_exMem->writeReg = 0;
		new_exMem->memRead = in->memRead;
        	new_exMem->memWrite = in->memWrite;
        	new_exMem->memToReg = in->memToReg;      // 1 bit each
        	new_exMem->regWrite = in->regWrite;
		new_exMem->rtVal = 0; 
		new_exMem->aluResult = 0;
		return;
	}

	if (in->regDst == 0) {
		new_exMem->writeReg = in->rt;   // 1 bit
	}
	else {
		new_exMem->writeReg = in->rd;
	}

	new_exMem->rtVal = in->rtVal;      // useful for SW

	new_exMem->memRead = in->memRead;
	new_exMem->memWrite = in->memWrite;
	new_exMem->memToReg = in->memToReg;      // 1 bit each

	new_exMem->regWrite = in->regWrite;   // 1 bit

	//bitwise and
	if ( in->ALU.op == 0 ) {
		new_exMem->aluResult = input1 & input2;
	}
	//bitwise or
	else if ( in->ALU.op == 1 ) {
                new_exMem->aluResult = input1 | input2;
        }
	//bitwise add (or if binvert is on, sub)
	else if ( in->ALU.op == 2 ) {
		if (in->ALU.bNegate == 0) {
			new_exMem->aluResult = input1 + input2;
		}
		else {
			new_exMem->aluResult = input1 - input2;
		}
        }
	//less than
	else if ( in->ALU.op == 3 ) {
                new_exMem->aluResult = input1 < input2;
        }
	//sanity check
	else {
		new_exMem->aluResult = 0;
	}
	
	//nor
	if (in->extra1) {
		new_exMem->aluResult = ~(new_exMem->aluResult);
	}
	
	//lui
	if (in->extra2) {
		new_exMem->aluResult = (input2 << 16);
	}
}

/******************
 * Func: void execute_MEM
 * executes memory step based off of instruction
 * loads, stores, or just does nothing.
******************/
void execute_MEM(EX_MEM *in, WORD *mem, MEM_WB *new_memwb) {
	new_memwb->memToReg = in->memToReg;
        new_memwb->aluResult = in->aluResult;

        new_memwb->writeReg = in->writeReg;    // 5 bits
        new_memwb->regWrite = in->regWrite;    // 1 bit

	//lw
	if (in->memToReg) {
		new_memwb->memResult = mem[in->aluResult >> 2];
	}
	//sw
	else if (in->memWrite) {
		mem[in->aluResult >> 2] = in->rtVal;
		new_memwb->memResult = 0;
	}
	//everything else doesn't need to access memory
	else {
		new_memwb->memResult = 0;
	}
}

/******************
 * Func: void execute_WB
 * executes write back step based off of instruction
 * writes to a register mem output or alu output
******************/
void execute_WB (MEM_WB *in, WORD *regs) {
	if (in->regWrite) {
		//lw
		if (in->memToReg) {
			 regs[in->writeReg] = in->memResult;
		}
		//everything else gets output from alu
		else {
			regs[in->writeReg] = in->aluResult;
		}	
	}
}

