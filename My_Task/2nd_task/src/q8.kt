// 8. Write a program to find out the max from given number (E.g. No: -1562 Max number is 6 )

fun main()
{
    var max = 0

    print("Enter Numbers : ")
    var num = readLine()!!.toInt()

    while(num>0)
    {
        var rem = num%10
        if(rem>max)
        {
            max = rem

        }
        num/=10
    }
    println("max digit is : $max")
}