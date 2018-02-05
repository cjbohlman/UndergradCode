# File: proj_sw05.s
# Author: Chris Bohlman
# Details the functions strlen, countLetters, and subsCipher

.data
MSG1:  .asciiz "----------------"
MSG2:  .asciiz "<other>: "
MSG3:  .asciiz ": "
SPACE: .asciiz " "
NL_END: .asciiz "\n"


.text
###########################################################
# void countLetters(char* str):
#
# Given a string, count the number of times every letter 
# appears in the string. Uses an array stored on the stack 
# in order to keep track of letter counts
###########################################################
.globl countLetters
countLetters:
	addiu  $sp, $sp, -24				# increment stack pointer
	sw     $fp, 0($sp)				# store old frame pointer
	sw     $ra, 4($sp)				# store return address
	addiu  $fp, $sp, 20				# advance frame pointer
	
	addiu  $sp, $sp, -12				# decrement stack pointer by -12
	sw     $s0, 0($sp)				# store s0 on stack
	sw     $s1, 4($sp)				# store s1 on stack
	sw     $s2, 8($sp)				# store s2 on stack
	
	add    $t0, $zero, $a0				# str = &args[0]
	addi   $t1, $zero, 0				# other = 0
	
	addi   $t2, $zero, 0				# i = 0
	addi   $t3, $zero, 26				# t3 = 26
	
FILL_ARRAY:
	beq    $t2, $t3, FILL_ARRAY_END     		# branch if i == 26
	addiu  $sp, $sp, -4				# increment stack pointer
	sw     $zero, 0($sp)				# store 0 at sp
	addi   $t2, $t2, 1				# i++
	j      FILL_ARRAY
	
FILL_ARRAY_END:
	addi   $v0, $zero, 4				# print_str
	la     $a0, MSG1                    		# print ----------------
	syscall                           		# do it
	
	addi   $v0, $zero, 4				# print_str
	la     $a0, NL_END                    		# print newline
	syscall                           		# do it
	
	addi   $v0, $zero, 4				# print_str
	addi   $a0, $t0, 0                    		# print str
	syscall                           		# do it
	
	addi   $v0, $zero, 4				# print_str
	la     $a0, NL_END                    		# print newline
	syscall                           		# do it
	
	addi   $v0, $zero, 4				# print_str
	la     $a0, MSG1                    		# print ----------------
	syscall                           		# do it
	
	addi   $v0, $zero, 4				# print_str
	la     $a0, NL_END                    		# print newline
	syscall                           		# do it

	addi   $t4, $zero, 0x00				# t4 = '\0'
	addi   $t5, $zero, 0x41				# t5 = 'A'
	addi   $t6, $zero, 0x5A				# t6 = 'Z'
	addi   $t7, $zero, 0x61				# t7 = 'a'
	addi   $s0, $zero, 0x7A				# s0 = 'z'
	
	add    $t2, $t0, $zero				# &cur = &str

L_COUNTING:
	lb     $t3, 0($t2)				# cur = *&cur	
	beq    $t3, $t4, L_COUNTING_END			# if cur == '\0', branch

	slt    $s1, $t3, $t7				# s1 = *cur < 'a'
	bne    $s1, $zero, L_AND_2A			# if s1 == 1, branch
	j      L_AND_1B
	
L_AND_1B:
	slt    $s1, $s0, $t3				# s1 = 'z' < '*cur
	bne    $s1, $zero, L_AND_2A			# if s1 == 1, branch
	sub    $s1, $t3, $t7				# s1 = cur - 'a'
	sll    $s1, $s1, 2				# s1 = s1 * 4
	add    $sp, $sp, $s1				# increment stack by cur - 'a'
	lw     $s2, 0($sp)				# s2 = sp[cur - 'a']
	addi   $s2, $s2, 1				# s2++
	sw     $s2, 0($sp)				# store s2 back on stack
	sub    $sp, $sp, $s1				# decrement stack by cur - 'a'
	j      L_COUNTING_INC

L_AND_2A:
	slt    $s1, $t3, $t5				# s1 = *cur < 'A'
	bne    $s1, $zero, L_ELSE			# if s1 == 1, branch
	j      L_AND_2B
	
L_AND_2B:
	slt    $s1, $t6, $t3				# s1 = 'Z' < '*cur
	bne    $s1, $zero, L_ELSE			# if s1 == 1, branch
	sub    $s1, $t3, $t5				# s1 = cur - 'A'
	sll    $s1, $s1, 2				# s1 = s1 * 4
	add    $sp, $sp, $s1				# increment stack by cur - 'A'
	lw     $s2, 0($sp)				# s2 = sp[cur - 'A']
	addi   $s2, $s2, 1				# s2++
	sw     $s2, 0($sp)				# store s2 back on stack
	sub    $sp, $sp, $s1				# decrement stack by cur - 'A'
	j      L_COUNTING_INC

L_ELSE:
	addi   $t1, $t1, 1				# other++
	
L_COUNTING_INC:
	addi   $t2, $t2, 1				# *cur++
	j L_COUNTING

L_COUNTING_END:
	addi   $t2, $zero, 0				# i = 0
	addi   $t3, $zero, 26				# t3 = 26
	
PRINT_ARRAY:
	beq    $t2, $t3, LETTERS_END     		# branch if i == 26
	add    $t2, $t2, $t7				# t2 = 'a' + i
	
	addi   $v0, $zero, 11				# print_char
	addi   $a0, $t2, 0                    		# print 'a' + i
	syscall                           		# do it
	
	sub    $t2, $t2, $t7				# t2 = i
	sll    $t2, $t2, 2				# i = i*4
	addu   $sp, $sp, $t2				# sp = sp + i*4
	
	addi   $v0, $zero, 4				# print_str
	la     $a0, MSG3                    		# print MSG3
	syscall                           		# do it
	
	addi   $v0, $zero, 1				# print_int
	lw     $a0, 0($sp)                    		# print letters[i]
	syscall                           		# do it
	
	addi   $v0, $zero, 4				# print_str
	la     $a0, NL_END                    		# print newline
	syscall                           		# do it
	
	sub   $sp, $sp, $t2				# sp = sp - i*4
	srl   $t2, $t2, 2				# t2 = i
	
	addi   $t2, $t2, 1				# i++
	j      PRINT_ARRAY
	
LETTERS_END:
	addi   $v0, $zero, 4				# print_str
	la     $a0, MSG2                    		# print MSG2
	syscall                           		# do it
	
	addi   $v0, $zero, 1				# print_int
	addi   $a0, $t1, 0                    		# print other
	syscall                           		# do it
	
	addi   $v0, $zero, 4				# print_str
	la     $a0, NL_END                    		# print newline
	syscall                           		# do it
	
	addiu  $sp, $sp, 104				# increment stack up by 26 words
	

	lw     $s0, 0($sp)				# load back s0
	lw     $s1, 4($sp)				# load back s1
	lw     $s2, 8($sp)				# load back s2
	addiu  $sp, $sp, 12				# increment stack pointer up by 4 words
	
	lw     $ra, 4($sp)				# load back return address
	lw     $fp, 0($sp)				# load back frame pointer
	addiu  $sp, $sp, 24				# increment stack pointer
	jr     $ra					# jump to register
	

###########################################################
# void subsCipher(char* str, char* map):
#
# Given a string and a character map, performs a 
# substitution cipher on the string based off of the map. 
# Stores encoded message in char array on stack.
###########################################################
.globl subsCipher
subsCipher:
	addiu  $sp, $sp, -24				# increment stack pointer
	sw     $fp, 0($sp)				# store old frame pointer
	sw     $ra, 4($sp)				# store return address
	addiu  $fp, $sp, 20				# advance frame pointer
	
	
	jal    strlen					# strlen(str*)
	addi   $t7, $a0, 0				# t7 = *str
	addi   $t0, $v0, 1				# len = strlen(str) + 1
	addi   $t1, $a1, 0				# t1 = &map
	
	addi   $t2, $zero, 0x3				# t2 = 0x3
	nor    $t2, $t2, $t2				# t2 = ~0x3
	addi   $t3, $t0, 3				# len_roundUp = len + 3
	and    $t3, $t3, $t2				# round len_roundUp to multiple of 4
	
	sub    $sp, $sp, $t3				# decrement stack by len_roundUp
	
	addi   $t2, $zero, 0				# i = 0
	addi   $t0, $t0, -1				# t0 = len - 1
	
CIPHER_FOR: 
	beq    $t2, $t0, CIPHER_FOR_END			# if i = len -1, branch
	add    $t4, $t7, $t2				# t4 = &str[i]
	lb     $t4, 0($t4)				# t4 = str[i]
	
	add    $t4, $t1, $t4				# t4 = &maps[str[i]]
	lb     $t4, 0($t4)				# t4 = maps[str[i]]
	
	add    $t5, $sp, $t2				# t5 = &dup[i]
	
	sb     $t4, 0($t5)				# dup[i] = map[(int)str[i]]
	
	addi   $t2, $t2, 1				# i++
	j CIPHER_FOR
	
CIPHER_FOR_END:
	add    $t5, $sp, $t0				# t5 = &dup[len -1]
	addi   $t4, $zero, 0x0				# t4 = 0x0
	sb     $t4, 0($t5)				# dup[len-1] = '\0'
	
	add    $a0, $zero, $sp				# add &dup to first argument
	addiu  $sp, $sp, -4				# decrement stack by a word
	sw     $t3, 0($sp)				# store len_roundUp
	
	jal printSubstitutedString
	
	lw     $t3, 0($sp)				# load back len_roundUp
	addiu  $sp, $sp, 4				# increment stack by a word
	
	addu   $sp, $sp, $t3				# dup[len_roundUp]

	lw     $ra, 4($sp)				# load back return address
	lw     $fp, 0($sp)				# load back frame pointer
	addiu  $sp, $sp, 24				# increment stack pointer
	jr     $ra					# jump to register
	
###########################################################
# int strlen(char* str):
#
# Given a string, returns length of string
###########################################################
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
	
