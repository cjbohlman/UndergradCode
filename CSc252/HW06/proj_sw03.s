# File: proj_sw03.s
# Author: Chris Bohlman
# Details the functions collatzLine, collatz, percentSearch, and letterTree

.data
MSG1: .asciiz "collatz("
MSG2: .asciiz ") completed after "
MSG3: .asciiz " calls to collatz_line()."
MSG4: .asciiz "letterTree(): ended after "
MSG5: .asciiz " steps"
SPACE: .asciiz " "
NL_END: .asciiz "\n"


.text
.globl collatz_line
collatz_line:
	addiu  $sp, $sp, -24				# increment stack pointer
	sw     $fp, 0($sp)				# store old frame pointer
	sw     $ra, 4($sp)				# store return address
	addiu  $fp, $sp, 20				# advance frame pointer

	add    $t0, $zero, $a0				# val = args[0]
	
WHILE_BEGIN:
	andi   $t1, $t0, 1				# Mask val w/ 1 to get end bit
	addi   $t2, $zero, 1				# t2 = 1
	beq    $t1, $t2, RETURN_INT			# if val is odd, return int
	
	addi   $v0, $zero, 1				# print_int
	add    $a0, $zero, $t0				# print val
	syscall						# do it
	
	addi   $v0, $zero, 4				# print_str
	la     $a0, SPACE                    		# print SPACE
	syscall                           		# do it
	
	sra    $t0, $t0, 1					# val = val/2
	
	j WHILE_BEGIN					# jump back to beginning of while loop
	
	
RETURN_INT:
	addi   $v0, $zero, 1				# print_int
	add    $a0, $zero, $t0				# print val
	syscall						# do it
	
	addi   $v0, $zero, 4				# print_str
	la     $a0, NL_END                    		# print newline
	syscall                           		# do it
	
	add    $v0, $zero, $t0				# return val from the function
	
	lw     $ra, 4($sp)				# load back return address
	lw     $fp, 0($sp)				# load back frame pointer
	addiu  $sp, $sp, 24				# increment stack pointer
	jr     $ra					# jump to register
	
.globl collatz
collatz:
	addiu  $sp, $sp, -24				# increment stack pointer
	sw     $fp, 0($sp)				# store old frame pointer
	sw     $ra, 4($sp)				# store return address
	addiu  $fp, $sp, 20				# advance frame pointer

	add    $t0, $zero, $a0				# val = a1
	add    $t3, $zero, $a0				# val = a1
	addi   $t7, $zero, 0				# calls = 0
	
	add    $a0, $zero, $t0				# add val as argument
	jal    collatz_line				# jump and link to collatz_line
	
	addi   $t0, $v0, 0				# add returned value to val
	
SECOND_WHILE_BEGIN:
	addi   $t2, $zero, 1				# t2 = 1
	beq    $t0, $t2, EXIT_WHILE			# if val is 1, exit while loop
	
	sll    $t1, $t0, 1				# cur = cur * 2
	add    $t0, $t0, $t1				# cur = cur * 3
	addi   $t0, $t0, 1				# cur = cur * 3 + 1
	
	add    $a0, $zero, $t0				# add cur to args[0]
	jal    collatz_line				# jump and link to collatz_line
	
	addi   $t0, $v0, 0				# cur is equal to return value from function
	
	addi   $t7, $t7, 1				# increment calls by 1
	
	j SECOND_WHILE_BEGIN				# jump to beginning of while loop
	
	
EXIT_WHILE:
	addi   $v0, $zero, 4				# print_str
	la     $a0, MSG1                    		# print newline
	syscall  
	
	addi   $v0, $zero, 1				# print_int
	add    $a0, $zero, $t3				# print val
	syscall	
						
	addi   $v0, $zero, 4				# print_str
	la     $a0, MSG2                    		# print newline
	syscall
	
	addi   $v0, $zero, 1				# print_int
	add    $a0, $zero, $t7				# print val
	syscall	
	
	addi   $v0, $zero, 4				# print_str
	la     $a0, MSG3                    		# print newline
	syscall
	
	addi   $v0, $zero, 4				# print_str
	la     $a0, NL_END                    		# print newline
	syscall  
	
	addi   $v0, $zero, 4				# print_str
	la     $a0, NL_END                    		# print newline
	syscall                           		# do it
	
	lw     $ra, 4($sp)				# load back return address
	lw     $fp, 0($sp)				# load back frame pointer
	addiu  $sp, $sp, 24				# increment stack pointer
	jr     $ra					# jump to register
	

.globl percentSearch
percentSearch:
	addiu  $sp, $sp, -24				# increment stack pointer
	sw     $fp, 0($sp)				# store old frame pointer
	sw     $ra, 4($sp)				# store return address
	addiu  $fp, $sp, 20				# advance frame pointer

	add    $t0, $zero, $a0				# &val[0] = a1
	addi   $t1, $zero, 0				# count = 0
	addi   $t3, $zero, 0x00				# t3 = '\0'
	addi   $t4, $zero, 0x25				# t4 = ' '
	
PERC_WHILE_BEGIN:
	lb     $t2, 0($t0)				# t2 = value at val[i]
	beq    $t2, $t4, END_PERC			# if val is a percent, branch
	beq    $t2, $t3, END_NO				# if val is a null space, branch
	
	addi   $t1, $t1, 1				# count++
	addi   $t0, $t0, 1				# &val++
	
	j PERC_WHILE_BEGIN				# jump to beginning of while loop
	
END_PERC:
	add    $v0, $zero, $t1				# return index of character
	j FIN_PERC
	
END_NO:
	addi   $v0, $zero, -1				# return -1
	j FIN_PERC

FIN_PERC:
	lw     $ra, 4($sp)				# load back return address
	lw     $fp, 0($sp)				# load back frame pointer
	addiu  $sp, $sp, 24				# increment stack pointer
	jr     $ra					# jump to register

.globl letterTree
letterTree:
	addiu  $sp, $sp, -24				# increment stack pointer
	sw     $fp, 0($sp)				# store old frame pointer
	sw     $ra, 4($sp)				# store return address
	addiu  $fp, $sp, 20				# advance frame pointer

	add    $t0, $zero, $a0				# step = a1
	addi   $t1, $zero, 0				# count = 0
	addi   $t2, $zero, 0				# pos = 0
	
LETTER_WHILE:
	add    $a0, $zero, $t2				# args[0] = pos
	jal    getNextLetter				# jump and link getNextLetter
	addi   $t5, $v0, 0				# char c = getNextLetter(pos)
	beq    $t5, $zero, LETTER_END			# if c = '\0', branch
	
	addi   $t3, $zero, 0				# i = 0
	addi   $t7, $zero, 1				# t7 = 1
	
LETTER_FOR:
	slt    $t4, $t1, $t3				# t4 = count < i
	beq    $t4, $t7, LETTER_FOR_END			# if i is greater than count, branch
	
	addi   $v0, $zero, 11				# print_int
	add    $a0, $zero, $t5				# print val
	syscall
	
	addi   $t3, $t3, 1				# i++
	
	j LETTER_FOR

LETTER_FOR_END:
	addi   $v0, $zero, 4				# print_str
	la     $a0, NL_END                    		# print newline
	syscall
	
	addi   $t1, $t1, 1				# count++
	add    $t2, $t2, $t0				# pos = pos + step
	
	j LETTER_WHILE					# jump to letter_while
	
LETTER_END:
	add    $v0, $zero, $t2
	
	lw     $ra, 4($sp)				# load back return address
	lw     $fp, 0($sp)				# load back frame pointer
	addiu  $sp, $sp, 24				# increment stack pointer
	jr     $ra					# jump to register
	
