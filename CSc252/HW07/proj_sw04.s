# File: proj_sw04.s
# Author: Chris Bohlman
# Details the functions strlen, multiply, multiply4, multiply8, and sumN 

.data
SPACE: .asciiz " "
NL_END: .asciiz "\n"


.text
.globl strlen
strlen:
	addiu  $sp, $sp, -24				# increment stack pointer
	sw     $fp, 0($sp)				# store old frame pointer
	sw     $ra, 4($sp)				# store return address
	addiu  $fp, $sp, 20				# advance frame pointer

	add    $t0, $zero, $a0				# str = &args[0]
	add    $t1, $zero, $t0				# cur = &str
	
STRLEN_WHILE_BEGIN:
	lb     $t7, 0($t1)				# t7 = cur
	beq    $t7, $zero, STRLEN_WHILE_END		# branch if cur = '\0'
	addi   $t1, $t1, 1				# *cur++
	j STRLEN_WHILE_BEGIN				# jump to STR_WHILE_BEGIN

STRLEN_WHILE_END:
	sub    $t2, $t1, $t0				# t2 = cur - str
	addi   $v0, $t2, 0				# return = cur - str
	
	lw     $ra, 4($sp)				# load back return address
	lw     $fp, 0($sp)				# load back frame pointer
	addiu  $sp, $sp, 24				# increment stack pointer
	jr     $ra					# jump to register
	
.globl multiply
multiply:
	addiu  $sp, $sp, -24				# increment stack pointer
	sw     $fp, 0($sp)				# store old frame pointer
	sw     $ra, 4($sp)				# store return address
	addiu  $fp, $sp, 20				# advance frame pointer
	
	addi   $t0, $a0, 0				# t0 = a
	addi   $t1, $a1, 0				# t1 = b
	
	slt    $t2, $t1, $zero 				# t2 = b < 0
	beq    $t2, $zero, MULTIPLY_NEXT		# if t2 = 1, branch
	addi   $t7, $zero, 0				# t7 = 0
	sub    $a0, $t7, $a0				# a = ~a
	sub    $a1, $t7, $a1				# b = ~b
	
	addiu  $sp, $sp, -8				# decrement stack pointer
	sw     $t1, 4($sp)				# put b on stack
	sw     $t0, 0($sp)				# put a on stack
	jal    multiply
	lw     $t0, 0($sp)				# restore a
	lw     $t1, 4($sp)				# restore b
	addiu  $sp, $sp, 8				# increment stack pointer
	j MULTIPLY_END

MULTIPLY_NEXT:
	addi   $t7, $zero, 0				# total = 0
	addi   $t6, $zero, 0				# i = 0

MULTIPLY_FOR:
	slt    $t5, $t6, $t1				# t5 = i < b
	beq    $t5, $zero, MULTIPLY_FOR_END		# branch if i => b
	
	add    $t7, $t7, $t0				# total = total + a
	addi   $t6, $t6, 1				# i++
	j MULTIPLY_FOR
	
MULTIPLY_FOR_END:
	addi   $v0, $t7, 0				# return = total
MULTIPLY_END:
	lw     $ra, 4($sp)				# load back return address
	lw     $fp, 0($sp)				# load back frame pointer
	addiu  $sp, $sp, 24				# increment stack pointer
	jr     $ra					# jump to register
	

.globl multiply4
multiply4:
	addiu  $sp, $sp, -24				# increment stack pointer
	sw     $fp, 0($sp)				# store old frame pointer
	sw     $ra, 4($sp)				# store return address
	addiu  $fp, $sp, 20				# advance frame pointer
	
	jal multiply
	
	addi   $t0, $v0, 0				# t0 = multiply(arg1, arg2)
	
	addi    $a0, $a2, 0				# arg1 = arg3
	addi    $a1, $a3, 0				# arg2 = arg4
	
	addiu  $sp, $sp, -4				# decrement stack pointer
	sw     $t0, 0($sp)				# put multiply(arg1, arg2) on stack
	jal    multiply
	lw     $t0, 0($sp)				# restore t0 = multiply(arg1, arg2)
	addiu  $sp, $sp, 4				# increment stack pointer
	
	addi   $t1, $v0, 0				# t1 = multiply(arg3, arg4)
	addi   $a0, $t0, 0				# arg1 = multiply(arg1, arg2)
	addi   $a1, $t1, 0				# arg2 = multiply(arg3, arg4)
	
	jal    multiply

	lw     $ra, 4($sp)				# load back return address
	lw     $fp, 0($sp)				# load back frame pointer
	addiu  $sp, $sp, 24				# increment stack pointer
	jr     $ra					# jump to register

.globl multiply8
multiply8:
	addiu  $sp, $sp, -40				# increment stack pointer
	sw     $fp, 0($sp)				# store old frame pointer
	sw     $ra, 4($sp)				# store return address
	addiu  $fp, $sp, 36				# advance frame pointer
	
	jal multiply4
	addi   $t0, $v0, 0				# t0 = multiply4(arg1, arg2, arg3, arg4)
	
	addiu  $sp, $sp, -4				# decrement stack pointer
	sw     $t0, 0($sp)				# put t0 on stack
	
	lw      $t1, 28($sp)				# t1 = arg5
	addi    $a0, $t1, 0				# arg1 = t1
	lw      $t1, 32($sp)				# t2 = arg6
	addi    $a1, $t1, 0				# arg2 = t2
	lw      $t1, 36($sp)				# t3 = arg7
	addi    $a2, $t1, 0				# arg3 = t3
	lw      $t1, 40($sp)				# t4 = arg8
	addi    $a3, $t1, 0				# arg4 = t4
	
	jal multiply4
	
	addi    $t1, $v0, 0				# t1 = multiply4(arg5, arg6, arg7, arg8)
	lw      $t0, 0($sp)				# load multiply4(arg1, arg2, arg3, arg4)
	addiu  $sp, $sp, 4				# decrement stack pointer
	addi    $a0, $t0, 0				# arg1 = multiply4(arg1, arg2, arg3, arg4)
	addi    $a1, $t1, 0				# arg2 = multiply4(arg5, arg6, arg7, arg8)
	
	jal multiply

	lw     $ra, 4($sp)				# load back return address
	lw     $fp, 0($sp)				# load back frame pointer
	addiu  $sp, $sp, 40				# increment stack pointer
	jr     $ra					# jump to register
	
.globl sumN
sumN:
	addiu  $sp, $sp, -24				# increment stack pointer
	sw     $fp, 0($sp)				# store old frame pointer
	sw     $ra, 4($sp)				# store return address
	addiu  $fp, $sp, 20				# advance frame pointer
	
	addi   $t6, $a0, 0				# t6 = &arr[0]
	lw     $t0, 0($a0)				# t0 = arr[0]
	addi   $t1, $a1, 0				# t1 = len
	
	addiu  $sp, $sp, -12				# decrement stack pointer
	sw     $t0, 0($sp)				# put arr[0] on stack
	sw     $t1, 4($sp)				# put len on stack
	sw     $t6, 8($sp)				# put &arr[0] on stack
	
	addi   $a0, $t1, 0				# a0 = len
	addi   $a1, $t0, 0				# a1 = array[0]
	
	jal sumN_DEBUG
	
	lw     $t0, 0($sp)				# t0 = arr[0]
	lw     $t1, 4($sp)				# t1 = len
	lw     $t6, 8($sp)				# &arr[0] on stack
	addiu  $sp, $sp, 12
	
	addi  $t7, $zero, 1				# t7 = 1
	bne   $t1, $t7, SUM_REST			# if len != 1, goto SUM_REST
	
	addi  $v0, $t0, 0				# return arr[0]
	j SUM_END
	
SUM_REST:
	srl   $t2, $t1, 1				# left(t2) = len >> 1
	sub   $t3, $t1, $t2				# right(t3) = len - left
	addi   $a0, $t6, 0				# a0 = *arr
	addi   $a1, $t2, 0				# a1 = left
	
	addiu  $sp, $sp, -12				# decrement stack pointer
	sw     $t6, 0($sp)				# store *arr
	sw     $t2, 4($sp)				# store left
	sw     $t3, 8($sp)				# store right
	jal sumN
	lw     $t6, 0($sp)				# load *arr
	lw     $t2, 4($sp)				# load left
	lw     $t3, 8($sp)				# load right
	addiu  $sp, $sp, 12				# increment stack
	
	addi   $t1, $v0, 0				# t1 = return SumN(arr,left)
	addiu  $sp, $sp, -4				# decrement stack
	sw     $t1, 0($sp)				# store t1 on stack
	
	sll    $t2, $t2, 2				
	add    $t6, $t6, $t2				# t0 = arr+left
	addi   $a0, $t6, 0				# a0 = t0 + left
	addi   $a1, $t3, 0				# a1 = right
	jal sumN
	lw 	$t1, 0($sp)				# load t1 from stack
	addiu  $sp, $sp, 4				# increment t1
	add $v0, $v0, $t1				# return t1

SUM_END:
	lw     $ra, 4($sp)				# load back return address
	lw     $fp, 0($sp)				# load back frame pointer
	addiu  $sp, $sp, 24				# increment stack pointer
	jr     $ra					# jump to register
	
