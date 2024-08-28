// 7. Write a program to print the number in reverse order.

fun main() {
    var lastdigit: Int
    var rnum = 0

    print("Enter a number: ")
    var num = readLine()!!.toInt()

    while (num > 0) {
        lastdigit = num % 10
        rnum = (rnum * 10) + lastdigit
        num = num / 10
    }

    println("Reverse number is : $rnum")
}