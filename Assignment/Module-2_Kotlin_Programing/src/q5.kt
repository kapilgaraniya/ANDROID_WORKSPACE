// q5 Kotlin Program to Compute Quotient and Remainder

fun main()
{
    print("Enter dividend : ")
    var dvd = readLine()!!.toInt()

    print("Enter divisor : ")
    var dvr = readLine()!!.toInt()

    var q = dvd / dvr
    var r = dvd % dvr

    println("\nQuotient is : $q")
    println("Remainder is : $r")
}