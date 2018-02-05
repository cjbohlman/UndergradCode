# proj_sw01.s
# Details the four operations SORT, COMPARE, SWAP, and PRINT
.data
	MSG:    .asciiz "Sort Results: "
	MSG2:   .asciiz "Cur Values: "
	LESS:   .asciiz "LESS\n"
	SAME:   .asciiz "SAME\n"
	MORE:   .asciiz "MORE\n"
	NL_END: .asciiz "\n"

.text
.globl studentMain
studentMain:
	addiu $sp, $sp, -24   # allocate stack space -- default of 24 here
	sw    $fp, 0($sp)     # save caller’s frame pointer
	sw    $ra, 4($sp)     # save return address
	addiu $fp, $sp, 20    # setup main’s frame pointer

SORT_CALL:
	la    $s4, sort                   #s4 = &sort
	lw    $s4, 0($s4)                 #s4 = sort
	bne   $s4, $zero, SORT_BEGIN      #if sort != zero, go to sort function

COMPARE_CALL:
	la    $s5, compare                #s5 = &compare
	lw    $s5, 0($s5)                 #s5 = compare
	bne   $s5, $zero, COMPARE_BEGIN   #if compare != zero, go to compare function

SWAP_CALL:
	la    $s6, swap                   #s6 = &swap
	lw    $s6, 0($s6)                 #s6 = swap
	bne   $s6, $zero, SWAP_BEGIN      #if swap != zero, go to swap function

PRINT_CALL:
	la    $s7, print                  #s7 = &print
	lw    $s7, 0($s7)                 #s7 = print
	bne   $s7, $zero, PRINT_BEGIN     #if print != zero, go to print function

	j END_OF_PROG
	
#--------------sort begins-------------------#
SORT_BEGIN:
	la    $s0, alpha                  #s0 = &alpha
	lw    $s0, 0($s0)                 #s0 = alpha

	la    $s1, bravo                  #s1 = &bravo
	lw    $s1, 0($s1)                 #s1 = bravo

	la    $s2, charlie                #s2 = &charlie
	lw    $s2, 0($s2)                 #s2 = charlie

	la    $s3, delta                  #s3 = &delta
	lw    $s3, 0($s3)                 #s3 = delta

LOOP_BEGIN:
	slt   $t0, $s0, $s1               #if alpha is less than bravo, t0 = 1
	bne   $t0, $zero, ELSEIF1         #if alpha <= bravo, go to ELSEIF1

	add   $t1, $zero, $s0             #tmp = alpha
	add   $s0, $zero, $s1             #alpha = bravo
	add   $s1, $zero, $t1             #bravo = tmp

	j LOOP_BEGIN                      #jump to loop beginning

ELSEIF1:
	slt   $t0, $s1, $s2               #if bravo is less than charlie, t0 = 1
	bne   $t0, $zero, ELSEIF2         #if bravo <= charlie, go to ELSEIF2

	add   $t1, $zero, $s1             #tmp = bravo
	add   $s1, $zero, $s2             #bravo = charlie
	add   $s2, $zero, $t1             #charlie = tmp

	j LOOP_BEGIN                      #jump to loop beginning

ELSEIF2:
	slt   $t0, $s2, $s3               #if charlie is less than delta, t0 = 1
	bne   $t0, $zero, ELSE            #if charlie <= delta, go to ELSE

	add   $t1, $zero, $s2             #tmp = charlie
	add   $s2, $zero, $s3             #charlie = delta
	add   $s3, $zero, $t1             #delta = tmp

	j LOOP_BEGIN                      #jump to loop beginning

ELSE:
	j SORT_PRINT                      #jump to sort printing

SORT_PRINT:                               #print("Sort Results: %d %d %d %d\n", val1,val2,val3,val4);
	addi  $v0, $zero, 4               #print_str
	la    $a0, MSG                    #print "Sort Results: "
	syscall                           #do it

	addi  $v0, $zero, 1               #print_int
	add   $a0, $zero, $s0             #print alpha
	syscall                           #do it

	addi  $v0, $zero, 11              #print_char
	addi  $a0, $zero, 0x20            #print ASCII space
	syscall                           #do it

	addi  $v0, $zero, 1               #print_int
	add   $a0, $zero, $s1             #print bravo
	syscall                           #do it

	addi  $v0, $zero, 11              #print_char
	addi  $a0, $zero, 0x20            #print ASCII space
	syscall                           #do it

	addi  $v0, $zero, 1               #print_int
	add   $a0, $zero, $s2             #print charlie
	syscall                           #do it

	addi  $v0, $zero, 11              #print_char
	addi  $a0, $zero, 0x20            #print ASCII space
	syscall                           #do it

	addi  $v0, $zero, 1               #print_int
	add   $a0, $zero, $s3             #print delta
	syscall                           #do it

	addi  $v0, $zero, 4               #print_str
	la    $a0, NL_END                 #print END
	syscall                           #do it

#-----------------sort ends-------------------------------------#
	j COMPARE_CALL                    #jump to COMPARE call
#-----------------compare begins-------------------------------------#
COMPARE_BEGIN:
	la    $s1, bravo                  #s1 = &bravo
	lw    $s1, 0($s1)                 #s1 = bravo

	la    $s2, charlie                #s2 = &charlie
	lw    $s2, 0($s2)                 #s2 = charlie

	slt   $t0, $s2, $s1               #if charlie is less than bravo, t0 = 1
	bne   $t0, $zero, ELSEIF          #if charlie >= bravo, go to ELSEIF1
	
	addi  $v0, $zero, 4               #print_str
	la    $a0, LESS                   #print LESS
	syscall                           #do it

	j COMPARE_END                     #jump to end

ELSEIF:
	slt   $t0, $s1, $s2               #if bravo is less than charlie, t0 = 1
	bne   $t0, $zero, EQUAL           #if alpha <= bravo, go to ELSEIF1

	addi  $v0, $zero, 4               #print_str
	la    $a0, MORE                   #print MORE
	syscall                           #do it

	j COMPARE_END                     #jump to end

EQUAL:
	addi  $v0, $zero, 4               #print_str
	la    $a0, SAME                   #print SAME
	syscall                           #do it

	j COMPARE_END                     #jump to end

COMPARE_END:

#-----------------compare ends-------------------------------------#
	j SWAP_CALL                       #jump to swap call
#-----------------swap begins----------------#
SWAP_BEGIN:
	la    $s0, alpha                  #s0 = &alpha
	lw    $s0, 0($s0)                 #s0 = alpha

	la    $s1, bravo                  #s1 = &bravo
	lw    $s1, 0($s1)                 #s1 = bravo

	la    $s2, charlie                #s2 = &charlie
	lw    $s2, 0($s2)                 #s2 = charlie

	la    $s3, delta                  #s3 = &delta
	lw    $s3, 0($s3)                 #s3 = delta
	
	la    $t4, alpha                  #t4 = &alpha
	la    $t5, bravo                  #t5 = &bravo
	la    $t6, charlie                #t6 = &charlie
	la    $t7, delta                  #t7 = &delta

	add   $t1, $zero, $s0             #t1 = s0
	add   $t2, $zero, $s1             #t2 = s1

	sw    $t2, 0($t4)                 #store bravo value in alpha
	sw    $t1, 0($t5)                 #store alpha value in bravo

	add   $t1, $zero, $s2             #t1 = s2
	add   $t2, $zero, $s3             #t2 = s3
    
	sw    $t2, 0($t6)                 #store delta value in charlie
	sw    $t1, 0($t7)                 #store charlie value in delta

#-----------------swap ends-------------------------------------#
	j PRINT_CALL                      #jump to print call
#-----------------print begins----------------#
PRINT_BEGIN:
	la    $s0, alpha                  #s0 = &alpha
	lw    $s0, 0($s0)                 #s0 = alpha

	la    $s1, bravo                  #s1 = &bravo
	lw    $s1, 0($s1)                 #s1 = bravo

	la    $s2, charlie                #s2 = &charlie
	lw    $s2, 0($s2)                 #s2 = charlie

	la    $s3, delta                  #s3 = &delta
	lw    $s3, 0($s3)                 #s3 = delta

	addi  $v0, $zero, 4               #print_str
	la    $a0, MSG2                   #print MSG2
	syscall                           #do it

	addi  $v0, $zero, 1               #print_int
	add   $a0, $zero, $s0             #print alpha
	syscall                           #do it

	addi  $v0, $zero, 11              #print_char
	addi  $a0, $zero, 0x20            #print ASCII space
	syscall                           #do it

	addi  $v0, $zero, 1               #print_int
	add   $a0, $zero, $s1             #print bravo
	syscall                           #do it

	addi  $v0, $zero, 11              #print_char
	addi  $a0, $zero, 0x20            #print ASCII space
	syscall                           #do it

	addi  $v0, $zero, 1               #print_int
	add   $a0, $zero, $s2             #print charlie
	syscall                           #do it
 
	addi  $v0, $zero, 11              #print_char
	addi  $a0, $zero, 0x20            #print ASCII space
	syscall                           #do it

	addi  $v0, $zero, 1               #print_int
	add   $a0, $zero, $s3             #print delta
	syscall                           #do it

	addi  $v0, $zero, 4               #print_str
	la    $a0, NL_END                 #print END
	syscall                           #do it

END_OF_PROG:
	lw    $ra, 4($sp)                 # get return address from stack
	lw    $fp, 0($sp)                 # restore the caller’s frame pointer
	addiu $sp, $sp, 24                # restore the caller’s stack pointer
	jr    $ra                         # return to caller’s code