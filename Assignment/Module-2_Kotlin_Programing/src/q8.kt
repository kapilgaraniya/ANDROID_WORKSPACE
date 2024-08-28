// q8 WAP to find max value using function as express

fun maxnum(num1:Int,num2:Int)
{
        if(num1>num2)
        {
            println(num1)
        }
        else
        {
            println(num2)
        }
}

fun main()
{
    print("Enter Number 1 : ")
    var num1 = readLine()!!.toInt()

    print("Enter number 2 : ")
    var num2 = readLine()!!.toInt()

    var max = maxnum(num1,num2)
    println("The Max number is : $max")
}