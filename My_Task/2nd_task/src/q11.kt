// 11. Write Program use when statement. Display Monday to Sunday

fun main()
{

    println("1 = Monday")
    println("2 = Tuesday")
    println("3 = Wednesday")
    println("4 = Thursday")
    println("5 = Friday")
    println("6 = Saturday")
    println("7 = Sunday")

    print("Enter Your Choice : ")
    var ch = readLine()!!.toInt()

    when(ch)
    {
        1-> println("Monday")
        2-> println("Tuesday")
        3-> println("Wednesday")
        4-> println("Thursday")
        5-> println("Friday")
        6-> println("Saturday")
        7-> println("Sunday")
    }
}