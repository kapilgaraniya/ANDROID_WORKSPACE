package task_2// 15. Write a Program to check the given number is Positive, Negative.

fun main()
{
    print("Enter Any number : ")
    var n= readLine()!!.toInt()

    if (n > 0 )
    {
        println("Number is Positive")
    }
    else if (n == 0 )
    {
        println("Number is 0")
    }
    else
    {
        println("Number is Negative")
    }
}