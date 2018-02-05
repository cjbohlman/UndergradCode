# this is testing collatz_line

.data
FOO:
	.word 1
BAR:
	.word 9
.text

.globl	main
main:

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


	# collatz_line(-123)
	addi  $a0, $zero, -123
	jal   collatz_line

	# collatz_line(-345)
	addi  $a0, $zero, -345
	jal   collatz_line

	# collatz_line(123456)
	addi  $a0, $zero, 123456
	jal   collatz_line


	# collatz_line(1000)
	addi  $a0, $zero, 1000
	jal   collatz_line


	# collatz_line(2000)
	addi  $a0, $zero, 2000
	jal   collatz_line

	# collatz_line(456)
	addi  $a0, $zero, 456
	jal   collatz_line

	# collatz_line(1)
	addi  $a0, $zero, 1
	jal   collatz_line

	# collatz_line(2)
	addi  $a0, $zero, 2
	jal   collatz_line

	# collatz_line(9)
	addi  $a0, $zero, 9
	jal   collatz_line

	# collatz_line(8)
	addi  $a0, $zero, 8
	jal   collatz_line

	# collatz_line(-64)
	addi  $a0, $zero, -64
	jal   collatz_line

	# collatz_line(1024)
	addi  $a0, $zero, 1024
	jal   collatz_line

	# collatz_line(-1024)
	addi  $a0, $zero, -1024
	jal   collatz_line
	
	# collatz_line(323232323232)
	addi  $a0, $zero, 323232323232
	jal   collatz_line
	
	# collatz_line(1024124124)
	addi  $a0, $zero, 1024124124
	jal   collatz_line
	
	# collatz_line(24)
	addi  $a0, $zero, 24
	jal   collatz_line
	
	# collatz_line(1004)
	addi  $a0, $zero, 1004
	jal   collatz_line


.globl getNextLetter
getNextLetter:


	# terminate the program	
	addi	$v0, $zero, 10		# syscall_exit
	syscall
