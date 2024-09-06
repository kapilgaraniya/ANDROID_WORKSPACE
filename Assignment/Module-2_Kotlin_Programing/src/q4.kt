// q4 Kotlin Program to Multiply two Floating Point Numbers and Program to Find ASCII value of a character

fun main()
{
    print("Enter First Number : ")
    val n1 = readLine()!!.toFloat()
    print("Enter Second Number : ")
    val n2 = readLine()!!.toFloat()

    val ans = n1 * n2
    println("The Multiplication is : $ans\n")
//------------------------------------------------

    print("Enter a character: ")
    val ch = readLine()!!.single()

    val asciiValue = ch.code
    println("The ASCII value of $ch is $asciiValue") 
}
