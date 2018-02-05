
# test_03

.data
M:     .asciiz "**********************************************************************\n\n"

.text

.globl	main
main:
	# fill the sX registers (and fp) with junk.  Each testcase will use a different
	# set of values.
	lui   $fp,      0xffff
	ori   $fp, $fp, 0xffff
	lui   $s0,      0x1234
	ori   $s0, $s0, 0x5678
	lui   $s1,      0x9abc
	ori   $s1, $s1, 0xdef0
	lui   $s2,      0xdead
	ori   $s2, $s2, 0xbeef
	lui   $s3,      0xc0d4
	ori   $s3, $s3, 0xf00d
	lui   $s4,      0x0c00
	ori   $s4, $s4, 0x10ff
	lui   $s5,      0xcafe
	ori   $s5, $s5, 0xbabe
	lui   $s6,      0x2468
	ori   $s6, $s6, 0xace0
	lui   $s7,      0x1357
	ori   $s7, $s7, 0x9bdf

.data
M1:     .ascii  "Test1: Input is 987654\n\n"
       .asciiz "Outputs are: \n"

.text
	addi $v0, $zero, 4 # print string
	la   $a0, M1
	syscall

	# collatz(987654)
	addi  $a0, $zero, 987654
	jal   collatz
	
	addi $v0, $zero, 4 # print string
	la   $a0, M
	syscall

.data
M2:     .ascii  "Test2: Input is 12345678\n\n"
       .asciiz "Outputs are: \n"


.text
	addi $v0, $zero, 4 # print string
	la   $a0, M2
	syscall

	# collatz(12345678)
	addi  $a0, $zero, 12345678
	jal   collatz

	addi $v0, $zero, 4 # print string
	la   $a0, M
	syscall

.data
M3:     .ascii  "Test3: Input is 127345252\n\n"
       .asciiz "Outputs are: \n"

.text
	addi $v0, $zero, 4 # print string
	la   $a0, M3
	syscall

	# collatz(127345252)
	addi  $a0, $zero, 127345252
	jal   collatz

	addi $v0, $zero, 4 # print string
	la   $a0, M
	syscall

.data
M4:     .ascii  "Test4: Input is 123456789\n\n"
       .asciiz "Outputs are: \n"

.text
	addi $v0, $zero, 4 # print string
	la   $a0, M3
	syscall

	# collatz(123456789)
	addi  $a0, $zero, 123456789
	jal   collatz

	addi $v0, $zero, 4 # print string
	la   $a0, M
	syscall
	
.data
M5:     .ascii  "Test5: Input is 98765431\n\n"
       .asciiz "Outputs are: \n"

.text
	addi $v0, $zero, 4 # print string
	la   $a0, M5
	syscall

	# collatz(98765431)
	addi  $a0, $zero, 98765431
	jal   collatz

	addi $v0, $zero, 4 # print string
	la   $a0, M
	syscall

# the codes below is copied and pasted from test_01.s written by Russ

	add	$a0, $sp, $zero
	jal     printHex
	add	$a0, $fp, $zero
	jal     printHex
	add	$a0, $s0, $zero
	jal     printHex
	add	$a0, $s1, $zero
	jal     printHex
	add	$a0, $s2, $zero
	jal     printHex
	add	$a0, $s3, $zero
	jal     printHex
	add	$a0, $s4, $zero
	jal     printHex
	add	$a0, $s5, $zero
	jal     printHex
	add	$a0, $s6, $zero
	jal     printHex
	add	$a0, $s7, $zero
	jal     printHex

# exit:
	addi $v0, $zero, 10
	syscall

# void printHex(int val)
# {
#     printHex_recurse(val, 8);
#     printf("\n");
# }
printHex:
	# standard prologue
	addiu  $sp, $sp, -24
	sw     $fp, 0($sp)
	sw     $ra, 4($sp)
	addiu  $fp, $sp, 20
	
	# printHex(val, 8)
	addi   $a1, $zero, 8
	jal    printHex_recurse
	
	addi   $v0, $zero, 11      # print_char('\n')
	addi   $a0, $zero, 0xa
	syscall

	# standard epilogue
	lw     $ra, 4($sp)
	lw     $fp, 0($sp)
	addiu  $sp, $sp, 24
	jr     $ra
	
	
	
printHex_recurse:
	# standard prologue
	addiu  $sp, $sp, -24
	sw     $fp, 0($sp)
	sw     $ra, 4($sp)
	addiu  $fp, $sp, 20
	
	# if (len == 0) return;    // base case (NOP)
	beq    $a1, $zero, printHex_recurse_DONE

	# recurse first, before we print this character.
	#
	# The reason for this is because the easiest way to break up
	# a long integer is using a small shift and modulo; so *this*
	# call will be responsible for the *last* hex digit, and we'll
	# use recursion to handle the things which come *before* it.
	#
	# As we've seen just above, if the current len==1, then the
	# recursive call will be the base case, and a NOP.
	
	# of course, we have to save a0 before we recurse.  We do *NOT*
	# need to save a1, since we'll never need it again.
	sw     $a0, 8($sp)
	
	# printHex_recurse(val >> 4, len-1)
	srl    $a0, $a0,4
	addi   $a1, $a1,-1
	jal    printHex_recurse
	
	# restore a0
	lw     $a0, 8($sp)
	
	# the value we will print is (val & 0xf), interpreted as hex.
	andi   $t0, $a0,0x0f      # digit = (val & 0xf)
	
	slti   $t1, $t0,10        # t1 = (digit < 10)
	beq    $t1, $zero, printHex_recurse_LETTER
	
	# if we get here, then $t0 contains an integer from 0 to 9, inclusive.
	addi   $v0, $zero, 11     # print_char(digit+'0')
	addi   $a0, $t0, '0'
	syscall
	
	j      printHex_recurse_DONE
	
printHex_recurse_LETTER:
	# if we get here, then $t0 contains an integer from 10 to 15, inclusive.
	# convert to the equivalent letter.
	addi   $t0, $t0,-10        # digit -= 10
	
	addi   $v0, $zero, 11     # print_char(digit+'a')
	addi   $a0, $t0, 'a'
	syscall
	
	# intentional fall-through to the epilogue	

printHex_recurse_DONE:
	# standard epilogue
	lw     $ra, 4($sp)
	lw     $fp, 0($sp)
	addiu  $sp, $sp, 24
	jr     $ra

.globl getNextLetter
getNextLetter:
