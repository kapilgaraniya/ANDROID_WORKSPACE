package task_2// 16. Write a Program to check the given year is leap year or not.

fun main()
{
    print("Enter year number : ")
    var n= readLine()!!.toInt()

    if (n % 4 == 0)
    {
        println("the year is leap year")
    }
    else
    {
        println("the year is not leap year")

    }

}