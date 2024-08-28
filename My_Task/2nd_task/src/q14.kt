// 14. Write a Program to check the given number is prime or not prime.

fun main()
{
    print("Enter Any Number : ")
    var n = readLine()!!.toInt()

    if (n % 2 == 0)
    {

        println("The number is not prime")
    }
    else
    {
        println("The number is prime")
    }
}