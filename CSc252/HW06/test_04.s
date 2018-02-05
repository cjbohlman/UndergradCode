# this is testing "find %"

.data
TESTCASE_SEARCH1:	.asciiz	"01234567%9"
TESTCASE_SEARCH2:	.asciiz	"%123456789"
TESTCASE_SEARCH3:	.asciiz	"0%23456789"
TESTCASE_SEARCH4:	.asciiz	"01%3456789"
TESTCASE_SEARCH5:	.asciiz	"012%456789"
TESTCASE_SEARCH6:	.asciiz	"0123%56789"
TESTCASE_SEARCH7:	.asciiz	"01234%6789"
TESTCASE_SEARCH8:	.asciiz	"012345%789"
TESTCASE_SEARCH9:	.asciiz	"0123456%89"
TESTCASE_SEARCH10:	.asciiz	"012345678%"
TESTCASE_SEARCH11:	.asciiz	"0123456789" # -1
TESTCASE_SEARCH12:	.asciiz	"%aaaaaa%%aaaaaa%%%%%aaaaaa%%aaaaaa" # 0
TESTCASE_SEARCH13:	.asciiz	"aaccvvddfqwedqdewsftg%%%%%%%"  # 20

.text
.globl main
main:
	
	.text
	# percentSearch(SEARCH1)
	la      $a0, TESTCASE_SEARCH1     # arg1
	jal     percentSearch             # make the call!
	add     $t0, $v0,$zero            # t0 = retval
	
.data
TESTCASE_MSG1:		.asciiz "percentSearch() returned: "
.text
	addi    $v0, $zero,4              # print_str(MSG1)
	la      $a0, TESTCASE_MSG1
	syscall
	
	addi    $v0, $zero,1              # print_int(retval)
	add     $a0, $t0,$zero
	syscall
	
	addi    $v0, $zero,11             # print_str('\n');
	addi    $a0, $zero,0xa
	syscall
	
	# percentSearch(SEARCH2)
	la      $a0, TESTCASE_SEARCH2     # arg1
	jal     percentSearch             # make the call!
	add     $t0, $v0,$zero            # t0 = retval
	
	addi    $v0, $zero,4              # print_str(MSG1)
	la      $a0, TESTCASE_MSG1
	syscall
	
	addi    $v0, $zero,1              # print_int(retval)
	add     $a0, $t0,$zero
	syscall
	
	addi    $v0, $zero,11             # print_str('\n');
	addi    $a0, $zero,0xa
	syscall
	
	# percentSearch(SEARCH3)
	la      $a0, TESTCASE_SEARCH3     # arg1
	jal     percentSearch             # make the call!
	add     $t0, $v0,$zero            # t0 = retval
	
	addi    $v0, $zero,4              # print_str(MSG1)
	la      $a0, TESTCASE_MSG1
	syscall
	
	addi    $v0, $zero,1              # print_int(retval)
	add     $a0, $t0,$zero
	syscall
	
	addi    $v0, $zero,11             # print_str('\n');
	addi    $a0, $zero,0xa
	syscall

	# percentSearch(SEARCH1)
	la      $a0, TESTCASE_SEARCH1     # arg1
	jal     percentSearch             # make the call!
	add     $t0, $v0,$zero            # t0 = retval

	addi    $v0, $zero,4              # print_str(MSG1)
	la      $a0, TESTCASE_MSG1
	syscall
	
	addi    $v0, $zero,1              # print_int(retval)
	add     $a0, $t0,$zero
	syscall
	
	addi    $v0, $zero,11             # print_str('\n');
	addi    $a0, $zero,0xa
	syscall
	
	# percentSearch(SEARCH2)
	la      $a0, TESTCASE_SEARCH2     # arg1
	jal     percentSearch             # make the call!
	add     $t0, $v0,$zero            # t0 = retval
	
	addi    $v0, $zero,4              # print_str(MSG1)
	la      $a0, TESTCASE_MSG1
	syscall
	
	addi    $v0, $zero,1              # print_int(retval)
	add     $a0, $t0,$zero
	syscall
	
	addi    $v0, $zero,11             # print_str('\n');
	addi    $a0, $zero,0xa
	syscall
	
	# percentSearch(SEARCH3)
	la      $a0, TESTCASE_SEARCH3     # arg1
	jal     percentSearch             # make the call!
	add     $t0, $v0,$zero            # t0 = retval
	
	addi    $v0, $zero,4              # print_str(MSG1)
	la      $a0, TESTCASE_MSG1
	syscall
	
	addi    $v0, $zero,1              # print_int(retval)
	add     $a0, $t0,$zero
	syscall
	
	addi    $v0, $zero,11             # print_str('\n');
	addi    $a0, $zero,0xa
	syscall
	
	# percentSearch(SEARCH4)
	la      $a0, TESTCASE_SEARCH4     # arg1
	jal     percentSearch             # make the call!
	add     $t0, $v0,$zero            # t0 = retval
	
	addi    $v0, $zero,4              # print_str(MSG1)
	la      $a0, TESTCASE_MSG1
	syscall
	
	addi    $v0, $zero,1              # print_int(retval)
	add     $a0, $t0,$zero
	syscall
	
	addi    $v0, $zero,11             # print_str('\n');
	addi    $a0, $zero,0xa
	syscall

	# percentSearch(SEARCH5)
	la      $a0, TESTCASE_SEARCH5     # arg1
	jal     percentSearch             # make the call!
	add     $t0, $v0,$zero            # t0 = retval
	
	addi    $v0, $zero,4              # print_str(MSG1)
	la      $a0, TESTCASE_MSG1
	syscall
	
	addi    $v0, $zero,1              # print_int(retval)
	add     $a0, $t0,$zero
	syscall
	
	addi    $v0, $zero,11             # print_str('\n');
	addi    $a0, $zero,0xa
	syscall

	# percentSearch(SEARCH6)
	la      $a0, TESTCASE_SEARCH6     # arg1
	jal     percentSearch             # make the call!
	add     $t0, $v0,$zero            # t0 = retval
	
	addi    $v0, $zero,4              # print_str(MSG1)
	la      $a0, TESTCASE_MSG1
	syscall
	
	addi    $v0, $zero,1              # print_int(retval)
	add     $a0, $t0,$zero
	syscall
	
	addi    $v0, $zero,11             # print_str('\n');
	addi    $a0, $zero,0xa
	syscall

	# percentSearch(SEARCH7)
	la      $a0, TESTCASE_SEARCH7     # arg1
	jal     percentSearch             # make the call!
	add     $t0, $v0,$zero            # t0 = retval
	
	addi    $v0, $zero,4              # print_str(MSG1)
	la      $a0, TESTCASE_MSG1
	syscall
	
	addi    $v0, $zero,1              # print_int(retval)
	add     $a0, $t0,$zero
	syscall
	
	addi    $v0, $zero,11             # print_str('\n');
	addi    $a0, $zero,0xa
	syscall

	# percentSearch(SEARCH8)
	la      $a0, TESTCASE_SEARCH8     # arg1
	jal     percentSearch             # make the call!
	add     $t0, $v0,$zero            # t0 = retval
	
	addi    $v0, $zero,4              # print_str(MSG1)
	la      $a0, TESTCASE_MSG1
	syscall
	
	addi    $v0, $zero,1              # print_int(retval)
	add     $a0, $t0,$zero
	syscall
	
	addi    $v0, $zero,11             # print_str('\n');
	addi    $a0, $zero,0xa
	syscall

	# percentSearch(SEARCH9)
	la      $a0, TESTCASE_SEARCH9     # arg1
	jal     percentSearch             # make the call!
	add     $t0, $v0,$zero            # t0 = retval
	
	addi    $v0, $zero,4              # print_str(MSG1)
	la      $a0, TESTCASE_MSG1
	syscall
	
	addi    $v0, $zero,1              # print_int(retval)
	add     $a0, $t0,$zero
	syscall
	
	addi    $v0, $zero,11             # print_str('\n');
	addi    $a0, $zero,0xa
	syscall

	# percentSearch(SEARCH10)
	la      $a0, TESTCASE_SEARCH10     # arg1
	jal     percentSearch             # make the call!
	add     $t0, $v0,$zero            # t0 = retval
	
	addi    $v0, $zero,4              # print_str(MSG1)
	la      $a0, TESTCASE_MSG1
	syscall
	
	addi    $v0, $zero,1              # print_int(retval)
	add     $a0, $t0,$zero
	syscall
	
	addi    $v0, $zero,11             # print_str('\n');
	addi    $a0, $zero,0xa
	syscall

	# percentSearch(SEARCH11)
	la      $a0, TESTCASE_SEARCH11     # arg1
	jal     percentSearch             # make the call!
	add     $t0, $v0,$zero            # t0 = retval
	
	addi    $v0, $zero,4              # print_str(MSG1)
	la      $a0, TESTCASE_MSG1
	syscall
	
	addi    $v0, $zero,1              # print_int(retval)
	add     $a0, $t0,$zero
	syscall
	
	addi    $v0, $zero,11             # print_str('\n');
	addi    $a0, $zero,0xa
	syscall

	# percentSearch(SEARCH12)
	la      $a0, TESTCASE_SEARCH12     # arg1
	jal     percentSearch             # make the call!
	add     $t0, $v0,$zero            # t0 = retval
	
	addi    $v0, $zero,4              # print_str(MSG1)
	la      $a0, TESTCASE_MSG1
	syscall
	
	addi    $v0, $zero,1              # print_int(retval)
	add     $a0, $t0,$zero
	syscall
	
	addi    $v0, $zero,11             # print_str('\n');
	addi    $a0, $zero,0xa
	syscall

	# percentSearch(SEARCH13)
	la      $a0, TESTCASE_SEARCH13     # arg1
	jal     percentSearch             # make the call!
	add     $t0, $v0,$zero            # t0 = retval
	
	addi    $v0, $zero,4              # print_str(MSG1)
	la      $a0, TESTCASE_MSG1
	syscall
	
	addi    $v0, $zero,1              # print_int(retval)
	add     $a0, $t0,$zero
	syscall
	
	addi    $v0, $zero,11             # print_str('\n');
	addi    $a0, $zero,0xa
	syscall

	# exit:
	addi $v0, $zero, 10
	syscall


.globl getNextLetter
getNextLetter:
