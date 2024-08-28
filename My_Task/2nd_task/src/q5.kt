// 5. Write a program you have to print the Fibonacci series up to user given number

fun main()
{
    print("Enter Number : ")
    var n = readLine()!!.toInt()

    var a = 0
    var b = 1

    println("$a\n$b")

    for (i in 3..n)
    {
        val sum = a + b
        println("$sum")
        a = b
        b = sum
    }
}