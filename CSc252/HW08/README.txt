bne
andi
ori

I implemented bne by saying that if cpucontrolstruct->extra1 was on, then aluresult->zero was turned on if the results subtracted did not equal zero. I also implemented the opcodes and other control bits as necessary
I implemented andi by saying in ALUInput2 that if cpucontrolstruct->extra2 was on, then set the second alu input value to a 0 extended immediate value, which ended up being imm16.
I implemented ori by saying in ALUInput2 that if cpucontrolstruct->extra3 was on, then set the second alu input value to a 0 extended immediate value, which ended up being imm16.
  
