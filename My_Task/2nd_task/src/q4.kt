// 4. write a program you have to find the factorial of given number. ex. 5!=5*4*3*2*1

fun main()
{
    var fact=1

    println("Enter Number : ")
    var n = readLine()!!.toInt()

    for (i in fact..n )
    {
        fact *= i
    }
    println(fact)
}

