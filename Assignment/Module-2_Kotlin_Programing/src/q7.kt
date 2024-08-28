// q7 Kotlin Program to Find the Frequency of Character in a String

fun main()
{
    print("Enter Any String : ")
    var str = readLine()!!.toString()

    print("Enter a Character to Find Frequency : ")
    var ch = readLine()!!.single()

    var f = str.count(){it == ch}
    println("The Frequency is : $f")
}