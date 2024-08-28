package task_2// 10. Write a program you have to make a summation of first and last Digit (E.g. 1234 ans:-5) using while loop and if else.

fun main() {
    print("Enter Number: ")
    var num = readLine()!!.toInt()

    val lastdigit = num % 10
    var firstdigit = num

    while (num > 0) {
        if (num >= 10) {
            num /= 10
        } else {
            firstdigit = num
            break
        }
    }

    val ans = firstdigit + lastdigit
    println("Sum of the first and last digit is: $ans")
}
