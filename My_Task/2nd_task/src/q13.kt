// 13. Write a program of to find out the Area of Triangle, Rectangle and Circle using If Condition.(Must Be Menu Driven)

fun main()
{
    println("1 = Area of Triangle")
    println("2 = Area of Rectangle ")
    println("3 = Area of Circle")

    print("Enter Your Choice : ")
    var ch = readLine()!!.toInt()

    if (ch==1)
    {
        print("Enter Two Number : ")
        var base = readLine()!!.toInt()
        var height = readLine()!!.toInt()

        print("The Area of Triangle : ")
        println(base * height / 2)
    }

    else if (ch==2)
    {
        print("Enter Two Number : ")
        var l = readLine()!!.toInt()
        var w = readLine()!!.toInt()

        print("The Area of Rectangle : ")
        println(l * w )
    }
    else if(ch==3)
    {
        val pi = 3.14
        print("Enter Number : ")
        var radius = readLine()!!.toInt()

        print("The Area of Circle is : ")
        println(pi * radius * radius)
    }
    else
    {
        println("Error, Plz Enter Valid Choice")
    }
}