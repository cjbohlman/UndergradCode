/* File: proj_hw04.c
 * Author: Chris Bohlman
 * Purpose: Implement a single cycle CPU with many functions
*/
#include "proj_hw04.h"

/******************
 * Func: WORD getInstruction(WORD curPC, WORD *instructionMemory)
 * returns the next instruction by finding the correct vaue in instruction memory from the current program counter shifted left by 2
 * 
******************/ 
WORD getInstruction(WORD curPC, WORD *instructionMemory){
	return instructionMemory[curPC >> 2];	
}

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
 * Func: int  fill_CPUControl(InstructionFields *fields, CPUControl *controlOut)
 * fills cpu control struct based off of the instruction fields struct
 * extracts aluop, alusrc, binvert, branch, memwrite, memread, memtoreg, regdst, regwrite, and jump
 * returns 1 for valid instructuons, returns 0 for invalid ones
******************/
int  fill_CPUControl(InstructionFields *fields, CPUControl *controlOut) {
	//check for invalid instruction
	if (!(fields->opcode == 0x02 || fields->opcode == 0x0d || fields->opcode == 0x04 ||  fields->opcode == 0x05 || fields->opcode == 0x08 || fields->opcode == 0x09 || fields->opcode == 0x0a || fields->opcode == 0x0c || fields->opcode == 0x23 || fields->opcode == 0x2b || fields->opcode == 0x00 && (fields->funct == 32 || fields->funct == 33 || fields->funct == 34 || fields->funct == 35 || fields->funct == 36 || fields->funct == 37 || fields->funct == 42)))
		return 0;
	// ALU src
        if (fields->opcode == 0x08 || fields->opcode == 0x09 || fields->opcode == 0x0a || fields->opcode == 0x0d || fields->opcode == 0x0c || fields->opcode == 0x23 || fields->opcode == 0x2b) {
		controlOut->ALUsrc = 1;
	}
	else {
		controlOut->ALUsrc = 0;
	}
	
	//alu op
	if ((fields->opcode == 0x00 && fields->funct == 37) || fields->opcode == 0x0d) {
		controlOut->ALU.op = 1;
	}
	else if (fields->opcode == 0x08 || fields->opcode == 0x05 || fields->opcode == 0x23 || fields->opcode == 0x2b || fields->opcode == 0x04 || fields->opcode == 0x09) {
		controlOut->ALU.op = 2;
	}
	else if (fields->opcode == 0x00 && (fields->funct == 32 || fields->funct == 34 || fields->funct == 35  || fields->funct == 33)) {
                controlOut->ALU.op = 2;
        }
	else if (fields->opcode == 0x0a) {
		controlOut->ALU.op = 3;
	}
	else if (fields->opcode == 0x00 && fields->funct == 42) {
                controlOut->ALU.op = 3;
        }
	else {
		controlOut->ALU.op = 0;
	}
	
	//bnegate
	if (fields->opcode == 0x0a || fields->opcode == 0x04 || fields->opcode == 0x05) {
                controlOut->ALU.bNegate = 1;
        }
        else if (fields->opcode == 0x00 && (fields->funct == 34 || fields->funct == 35 || fields->funct == 42)) {
                controlOut->ALU.bNegate = 1;
        }
	else {
		 controlOut->ALU.bNegate = 0;
	}

	//branch
        if (fields->opcode == 0x04 || fields->opcode == 0x05) {
                controlOut->branch = 1;
        }
	else {
		controlOut->branch = 0;
	}

	//memwrite
	if (fields->opcode == 0x2b) {
                controlOut->memWrite = 1;
        }
	else {
		controlOut->memWrite = 0;
	}

	//memread
        if (fields->opcode == 0x23) {
                controlOut->memRead = 1;
        }
	else {
		controlOut->memRead = 0;
	}

	//mem to reg
        if (fields->opcode == 0x23) {
                controlOut->memToReg = 1;
        }
	else {
		controlOut->memToReg = 0;
	}

	//regdst
	if (fields->opcode == 0x00 && (fields->funct == 32 || fields->funct == 35 || fields->funct == 33 || fields->funct == 34 || fields->funct == 36 || fields->funct == 37 || fields->funct == 42)) {
                controlOut->regDst = 1;
        }
	else {
		controlOut->regDst = 0;
	}

	//regwrite
	if (fields->opcode == 0x08 || fields->opcode == 0x0a || fields->opcode == 0x0c || fields->opcode == 0x0d || fields->opcode == 0x23 || fields->opcode == 0x09) {
		controlOut->regWrite = 1;
        }
        else if (fields->opcode == 0x00 && (fields->funct == 32 || fields->funct == 33 || fields->funct == 35 || fields->funct == 34 || fields->funct == 36 || fields->funct == 37 || fields->funct == 42 )) {
                controlOut->regWrite = 1;
        }
	else {
		controlOut->regWrite = 0;
	}

	//jump
        if (fields->opcode == 0x02) {
                controlOut->jump = 1;
        }
	else {
		controlOut->jump = 0;
	}
	//extra inst: bne
	if (fields->opcode == 0x05) {
                controlOut->extra1 = 1;
        }
        else {
                controlOut->extra1 = 0;
        }

	//extra inst: andi
	if (fields->opcode == 0x0c) {
                controlOut->extra2 = 1;
        }
        else {
                controlOut->extra2 = 0;
        }

	//extra inst: ori
        if (fields->opcode == 0x0d) {
                controlOut->extra3 = 1;
        }
        else {
                controlOut->extra3 = 0;
        }
	return 1;
}
/******************
 * Func: WORD getALUinput1(CPUControl *controlIn,
                  InstructionFields *fieldsIn,
                  WORD rsVal, WORD rtVal)
 * returns first alu input
 * in the functions I implemented, it's always rs
******************/
WORD getALUinput1(CPUControl *controlIn,
                  InstructionFields *fieldsIn,
                  WORD rsVal, WORD rtVal) {
	return rsVal;
}

/******************
 * Func: WORD getALUinput2(CPUControl *controlIn,
                  InstructionFields *fieldsIn,
                  WORD rsVal, WORD rtVal)
 * returns seconds alu input
 * if instruction was i fornat, I returned imm32 (or imm16 for andi and ori)
 * if instruction was r format, I returned rt
******************/
WORD getALUinput2(CPUControl *controlIn,
                  InstructionFields *fieldsIn,
                  WORD rsVal, WORD rtVal) {
	// if i instruction
	if (controlIn->ALUsrc) {
		if (controlIn->extra2 || controlIn->extra3)
			return fieldsIn->imm16;
		else
			return fieldsIn->imm32;
	}
	// if r instruction
	else {
		return rtVal;
	}
}

/******************
 * Func: void execute_ALU(CPUControl *controlIn,
                 WORD input1, WORD input2,
                 ALUResult  *aluResultOut)
 * executed alu based off of alu.op, and set zero output of alu
 * 0 = and, 1 = or, 2 = add, 3 = less than
 * for bne (extra1), also reversed zero value
******************/
void execute_ALU(CPUControl *controlIn,
                 WORD input1, WORD input2,
                 ALUResult  *aluResultOut) {
	//bitwise and
	if ( controlIn->ALU.op == 0 ) {
		aluResultOut->result = input1 & input2;
	}
	//bitwise or
	else if ( controlIn->ALU.op == 1 ) {
                aluResultOut->result = input1 | input2;
        }
	//bitwise add (or if binvert is on, sub)
	else if ( controlIn->ALU.op == 2 ) {
		if (controlIn->ALU.bNegate == 0) {
			aluResultOut->result = input1 + input2;
		}
		else {
			aluResultOut->result = input1 - input2;
		}
        }
	//less than
	else if ( controlIn->ALU.op == 3 ) {
                aluResultOut->result = input1 < input2;
        }
	//sanity check
	else {
		aluResultOut->result = 0;
	}
	//if bne, reverse alu's zero output, if not, set it to result
	if (controlIn->extra1 == 1)
		aluResultOut->zero = (aluResultOut->result);
	else
		aluResultOut->zero = !(aluResultOut->result);
}

/******************
 * Func: void execute_MEM(CPUControl *controlIn,
                 ALUResult  *aluResultIn,
                 WORD        rsVal, WORD rtVal,
                 WORD       *memory,
                 MemResult  *resultOut)
 * executes memory reading/writing if instruction is lw or sw (coded through control bits)
 * for all other instructions, set result out to 0
******************/
void execute_MEM(CPUControl *controlIn,
                 ALUResult  *aluResultIn,
                 WORD        rsVal, WORD rtVal,
                 WORD       *memory,
                 MemResult  *resultOut) {
	//lw
	if (controlIn->memRead && controlIn->memToReg) {
		resultOut->readVal = memory[aluResultIn->result >> 2];
	}
	//sw
	else if (controlIn->memWrite) {
		memory[aluResultIn->result >> 2] = rtVal;
		resultOut->readVal = 0;
	}
	//everything else doesn't need to access memory
	else {
		resultOut->readVal = 0;
	}
}

/******************
 * Func: WORD getNextPC(InstructionFields *fields, CPUControl *controlIn, int aluZero,
               WORD rsVal, WORD rtVal,
               WORD oldPC)
 * returns the next program counter
 * if it was a jump instruction, got address to jump to, shifted left by 2, added top 4 PC bits, and returned that address
 * if branch instruction, used immediate field to extract address, multiplied by 4, added to PC plus 4, and returned that address
******************/
WORD getNextPC(InstructionFields *fields, CPUControl *controlIn, int aluZero,
               WORD rsVal, WORD rtVal,
               WORD oldPC) {
	WORD pcplus4 = oldPC + 4;
	//jump
	if (controlIn->jump) {
		WORD tmp = fields->address << 2;
		tmp = tmp + (pcplus4 & 0xf0000000);
		return tmp;	
	}
	//branch
	if (controlIn->branch && aluZero) {
		WORD tmp = fields->imm32;
		tmp = tmp << 2;
		tmp = tmp + pcplus4;
		return tmp;
	}
	//everything else just needs pc + 4
	else {
		return pcplus4;
	}
}

/******************
 * Func: void execute_updateRegs(InstructionFields *fields, CPUControl *controlIn,
                        ALUResult   *aluResultIn, MemResult *memResultIn,
                        WORD       *regs) 
 * updates the registers based off of contol bits
 * if regwrite is on, chooses between lw and all other instructions
 * if lw, update register to memory result
 * if not lw, update register to alu result
******************/
void execute_updateRegs(InstructionFields *fields, CPUControl *controlIn,
                        ALUResult   *aluResultIn, MemResult *memResultIn,
                        WORD       *regs) {
	if (controlIn->regWrite) {
		//lw
		if (controlIn->memToReg) {
			 regs[fields->rt] = memResultIn->readVal;
		}
		//everything else gets output from alu
		else {
			//r instruction: update rd
			if (controlIn->regDst) {
				regs[fields->rd] = aluResultIn->result;
			}
			//i instruction: fill in rt
			else {
				regs[fields->rt] = aluResultIn->result;
 			}
		}	
	}	
}

