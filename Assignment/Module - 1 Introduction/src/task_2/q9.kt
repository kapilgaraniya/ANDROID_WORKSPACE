package task_2// 9. Write a program make a summation of given number(E.g. 1523 ans :- 11)

fun main()
{
    print("Enter Numbers : ")
    var num = readLine()!!.toInt()

    var sum = 0

    while (num > 0)
    {
        var rem = num % 10
        sum+=rem
        num = num/10
    }
    print("summation of given num is $sum")
}