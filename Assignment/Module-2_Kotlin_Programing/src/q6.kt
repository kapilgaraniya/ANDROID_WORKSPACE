// q6 Kotlin Program to Check Whether a Number is Even or Odd

fun main()
{
    print("Enter Number : ")
    var n = readLine()!!.toInt()

    if(n%2 == 0)
    {
        println("$n =  is even number")
    }
    else
    {
        println("$n = is odd number")
    }
}