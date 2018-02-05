# File: proj_sw02.s
# Author: Chris Bohlman
# Details the operations printInts, printWords, and bubbleSort in studentMain function
.data
	MSG1:   .asciiz  "printInts: About to print "
	MSG2:   .asciiz  " elements.\n"
	MSG3:   .asciiz  "printInts: About to print an unknown number of elements.  Will stop at a zero element.\n"
	MSG4:   .asciiz  "printWords: There were "
	MSG5:   .asciiz " words.\n"
	MSG6:   .asciiz "Swap at: "
	NL_END: .asciiz "\n"

.text
.globl studentMain
studentMain:
	addiu $sp, $sp, -24   				# allocate stack space -- default of 24 here
	sw    $fp, 0($sp)     				# save caller’s frame pointer
	sw    $ra, 4($sp)     				# save return address
	addiu $fp, $sp, 20   		 		# setup main’s frame pointer

PRINT_INTS_CALL:
	la    $s4, printInts                   		# s4 = &sort
	lb    $s4, 0($s4)                 		# s4 = sort
	bne   $s4, $zero, INTS_BEGIN      		# if sort != zero, go to sort function

PRINT_WORDS_CALL:
	la    $s5, printWords                		# s5 = &compare
	lb    $s5, 0($s5)                 		# s5 = compare
	bne   $s5, $zero, WORDS_BEGIN   		# if compare != zero, go to compare function

BUBBLE_SORT_CALL:
	la    $s6, bubbleSort                   	# s6 = &swap
	lb    $s6, 0($s6)                 		# s6 = swap
	bne   $s6, $zero, SORT_BEGIN      		# if swap != zero, go to swap function


	j END_OF_PROG					# jump to end of file
		
#-----------------printInts begins-------------------------------------#
INTS_BEGIN:
	la    $s0, printInts_howToFindLen 		# s0 = &printInts_howToFindLen 
	lh    $s0, 0($s0)                 		# s1 = printInts_howToFindLen 

	addi  $t0, $zero, 2               		# t0 = 2	
	beq   $s0, $t0, PI_ELSE				# if printInts_howToFindLen != 2, goto PI_ELSE
	
	bne $s0, $zero, PI_OTHER			# if printInts_howToFindLen != 0, goto PI_OTHER
	
	la $s1, intsArray_len				# s1 = &intsArray_len
	lw $s1, 0($s1)					# count = intsArray_len
	
	j PI_PRINT
	
PI_OTHER:
	la $t1, intsArray_END				# load address of intsArray_END
	la $t2, intsArray				# load address of intsArray
	
	sub $s1, $t1, $t2				# count = &intsArray_END - &intsArray
	sra $s1, $s1, 2                    		# divide count by 4
	
PI_PRINT:
	addi $v0, $zero, 4				# print_str
	la   $a0, MSG1                    		# print MSG1
	syscall                           		# do it
	
	addi $v0, $zero, 1				# print_int
	add $a0, $zero, $s1				# print count
	syscall						# do it
	
	addi $v0, $zero, 4				# print_str
	la   $a0, MSG2                    		# print MSG2
	syscall                           		# do it
	
	addi $t3, $zero, 0				# i = 0
	
	la $t4, intsArray				# t4 = &intsArray
	
PI_LOOP_BEGIN:
	beq $t3, $s1, PI_END				# if i = count. goto PI_END
	
	lw $t5, 0($t4)					# t5 = intsArray[i]
	
	addi $v0, $zero, 1				# print_int
	add $a0, $zero, $t5				# print intsArray[i]
	syscall						# do it
	
	addi $v0, $zero, 4				# print_str
	la   $a0, NL_END                   		# print newline
	syscall                           		# do it
	
	addi $t3, $t3, 1				# i++
	addi $t4, $t4, 4				# t4 = &intsArray[i]
	
	j PI_LOOP_BEGIN					# goto PI_LOOP_BEGIN

PI_ELSE:

	addi $v0, $zero, 4				# print_str
	la   $a0, MSG3                   		# print MSG3
	syscall                           		# do it
	
	la $t4, intsArray				# t4 = &intsArray
	
PI_WHILE_BEGIN:
	lw $t5, 0($t4)					# t5 = *intsArray
	
	beq $t5, $zero, PI_END				# if *intsArray == 0, goto PI_END
	
	addi $v0, $zero, 1				# print_int
	add $a0, $zero, $t5				# print *intsArray
	syscall						# do it
	
	addi $v0, $zero, 4				# print_str
	la   $a0, NL_END                   		# print new line
	syscall                           		# do it
	
	addi $t4, $t4, 4				# *intsArray++
	
	j PI_WHILE_BEGIN				# goto PI_WHILE_BEGIN
PI_END:

#-----------------printInts ends-------------------------------------#

	j PRINT_WORDS_CALL                      	# jump to printWords call
	
#-----------------printWords begins-------------------------------------#
WORDS_BEGIN:
	la $s0, theString				# cur = &theString
	la $t0, theString				# start = &theString
	
	addi $s2, $zero, 1				# count = 1
	
	addi $t6, $zero, 0x00				# t0 = '\0'
	addi $t7, $zero, 0x20				# t1 = ' '
	
WORDS_LOOP_BEGIN:
	lb  $s1, 0($s0)					# s1 = theString[0]
	beq $s1, $t6, WORDS_LOOP_END			# branch if theString[0] is '\0'
	
	bne $s1, $t7, NEXT				# branch if theString[0] is not a space
	sb $t6, 0($s0)					# store '\0' in theString[0]
	addi $s2, $s2, 1				# count++
	
NEXT:
	addi $s0, $s0, 1				# theString[i++]
	
	j WORDS_LOOP_BEGIN				# goto WORDS_LOOP_BEGIN
	
WORDS_LOOP_END:
	addi $v0, $zero, 4				# print_str
	la   $a0, MSG4                    		# print MSG4
	syscall                           		# do it
	
	addi $v0, $zero, 1				# print_int
	add $a0, $zero, $s2				# print count
	syscall						# do it
	
	addi $v0, $zero, 4				# print_str
	la   $a0, MSG5                    		# print MSG5
	syscall                           		# do it
	
	addi $s0, $s0, -1				# move cur back 1
	
WORDS_WHILE_LOOP:
	lb $s1, 0($s0)					# s1 = theString[0]
	slt $t4, $s0, $t0				# t4 = cur < start
	bne $t4, $zero, WORDS_END       		# branch if cur < start
	
	sub $t5, $s0, $t0				# t5 = cur - start
	beq $t5, $zero, WORDS_PRINT			# branch if cur == start
	
	addi $s0, $s0, -1				# move cur back
	lb $s1, 0($s0)					# load cur[-1]
	addi $s0, $s0, 1				# reset cur
	sub $t5, $s1, $t6				# t5 = cur - '\0'
	beq $t5, $zero, WORDS_PRINT			# branch if cur[-1] == ’\0'
	
	addi $s0, $s0, -1				# move cur back 1
	j WORDS_WHILE_LOOP

WORDS_PRINT:	
	addi $v0, $zero, 4				# print_str
	add $a0, $zero, $s0				# print *cur
	syscall						# do it
	
	addi $v0, $zero, 4				# print_str
	la   $a0, NL_END                   		# print newline
	syscall                           		# do it
	
	addi $s0, $s0, -1				# move cur back 1
	
	j WORDS_WHILE_LOOP
	
WORDS_END:
	
	j BUBBLE_SORT_CALL
	
#-----------------sort begins-------------------------------------#
	
SORT_BEGIN:
	la $s0, intsArray_len           		# s0 = &intsArrayLen
	lw $s0, 0($s0)					# s0 = intsArrayLen
	
	la $s2, intsArray_len           		# s2 = &intsArrayLen
	lw $s1, 0($s2)					# s1 = instArray_len	
	addi $s1, $s1, -1				# s1 = instArray_len - 1
	
	la $s2, intsArray				# s2 = &intsArray[0]
	
	addi $t0, $zero, 0              		# i = 0
	
	
SORT_FOR_BEGIN:
	slt $t2, $t0, $s0				# t2 = i < intsArrayLen
	beq $t2, $zero, SORT_END			# if $i >= instArray_len, jump to SORT_END
	
	addi $t1, $zero, 0             			# j = 0
	
SORT_FOR_SECOND_LOOP:
	
	slt $t2, $t1, $s1				# t2 = j < instArray_len - 1
	beq $t2, $zero, SORT_LOOP_END			# if j >= instArray_len - 1, jump to SORT_LOOP_END
	
	sll $t4, $t1, 2					# t4 = j * 4
	add $t3, $s2, $t4				# t3 = &array[0 + j*4]
	lw  $s3, 0($t3)					# s3 = array[j]		
	
	addi $t4, $t3, 4				# t4 = &array[j+1]
	lw  $s4, 0($t4)					# s4 = array[j+1]
	
	slt $t6, $s3, $s4		    		# t6 = array[j] < array[j + 1]
	bne $t6, $zero, SORT_NEXT_IN_LOOP   		# if array[j] < array[j + 1], branch
	
	addi $v0, $zero, 4				# print_str
	la   $a0, MSG6                   		# print MSG6
	syscall                           		# do it
	
	addi $v0, $zero, 1				# print_int
	add $a0, $zero, $t1				# print intsArray[j]
	syscall						# do it
	
	addi $v0, $zero, 4				# print_str
	la   $a0, NL_END                   		# print new line
	syscall                          		# do it
	
	
	add $t7, $s3, $zero				# t0 = intsArray[j]
	add $s3, $s4, $zero				# intsArray[j] = intsArray[j+1]
	sw $s3, 0($t3)					# store intsArray[j+1] into &array[j]
	sw $t7, 0($t4)					# store intsArray[j] into &intsArray[j+1]
	
SORT_NEXT_IN_LOOP:
	addi $t1, $t1, 1				# j++
	j SORT_FOR_SECOND_LOOP
	
SORT_LOOP_END:
	addi $t0, $t0, 1				# i++
	j SORT_FOR_BEGIN
	
	
SORT_END:

END_OF_PROG:
	lw    $ra, 4($sp)                 		# get return address from stack
	lw    $fp, 0($sp)                 		# restore the caller’s frame pointer
	addiu $sp, $sp, 24                		# restore the caller’s stack pointer
	jr    $ra                         		# return to caller’s code
