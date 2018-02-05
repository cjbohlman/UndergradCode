
.text

.globl main
main:

.data
LETTER_TREE_MSG1:		.asciiz "letterTree() returned: "
.text

	addi    $a0, $zero,3234            # arg1 = 0
	jal     letterTree                 # make the call
	add     $t0, $v0,$zero             # save the retval
	
	addi    $v0, $zero,4               # print_str(MSG1)
	la      $a0, LETTER_TREE_MSG1
	syscall
	
	addi    $v0, $zero,1               # print_int(retval)
	add     $a0, $t0,$zero
	syscall
	
	addi    $v0, $zero,11              # print_char('\n')
	addi    $a0, $zero,0xa
	syscall


	addi    $a0, $zero,19               # arg1 = 100
	jal     letterTree                 # make the call
	add     $t0, $v0,$zero             # save the retval
	
	addi    $v0, $zero,4               # print_str(MSG1)
	la      $a0, LETTER_TREE_MSG1
	syscall
	
	addi    $v0, $zero,1               # print_int(retval)
	add     $a0, $t0,$zero
	syscall
	
	addi    $v0, $zero,11              # print_char('\n')
	addi    $a0, $zero,0xa
	syscall



	addi    $a0, $zero,30               # arg1 = 3456
	jal     letterTree                 # make the call
	add     $t0, $v0,$zero             # save the retval
	
	addi    $v0, $zero,4               # print_str(MSG1)
	la      $a0, LETTER_TREE_MSG1
	syscall
	
	addi    $v0, $zero,1               # print_int(retval)
	add     $a0, $t0,$zero
	syscall
	
	addi    $v0, $zero,11              # print_char('\n')
	addi    $a0, $zero,0xa
	syscall


	addi $v0, $zero, 10
	syscall

.globl getNextLetter
getNextLetter:
	# standard prologue
	addiu  $sp, $sp, -24
	sw     $fp, 0($sp)
	sw     $ra, 4($sp)
	addiu  $fp, $sp, 20
	
.data
getNextLetter_BUF:	.asciiz "abcdefghijklmnopqrstuvwxyz"
			.word   0    # make sure we never overrun the buffer!
			.word   0    # make sure we never overrun the buffer!
			.word   0    # make sure we never overrun the buffer!
			.word   0    # make sure we never overrun the buffer!
			.word   0    # make sure we never overrun the buffer!
			.word   0    # make sure we never overrun the buffer!
			.word   0    # make sure we never overrun the buffer!
			.word   0    # make sure we never overrun the buffer!
			.word   0    # make sure we never overrun the buffer!
			.word   0    # make sure we never overrun the buffer!
			.word   0    # make sure we never overrun the buffer!
.text
	# no matter what input they give us, we will modulo by 32, so that positions
	# "wrap around" over time.
	andi   $a0, $a0,0x1f        # this mask has 5 bits turned on!
	
	# read the address of the string
	la     $v0, getNextLetter_BUF
	
	# offset by the parameter
	add    $v0, $v0,$a0
	
	# end then read the character there, as the value we'll return.
	lb     $v0, 0($v0)
	
	# standard epilogue
	lw     $ra, 4($sp)
	lw     $fp, 0($sp)
	addiu  $sp, $sp, 24
	jr     $ra	
