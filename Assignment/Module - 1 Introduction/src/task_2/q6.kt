package task_2// 6. write a program you have to print the table of given number.

fun main()
{
    print("Enter Number to Create Table: ")
    val n = readLine()!!.toInt()

    var total:Int

    for (i in 1..10)
    {
        total = n * i
        println("$n * $i = $total")
    }
}