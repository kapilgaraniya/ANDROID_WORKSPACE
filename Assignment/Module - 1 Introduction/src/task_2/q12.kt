package task_2// 12. Write a Program of Addition, Subtraction ,Multiplication and Division using When (Must Be Menu Driven)

fun main()
{
    print("Enter Two Number : ")
    var a = readLine()!!.toInt()
    var b = readLine()!!.toInt()

    println("1 = Addition")
    println("2 = Subtraction")
    println("3 = Multiplication")
    println("4 = Division")

    print("Enter Your Choice : ")
    var ch = readLine()!!.toInt()

    var result =when(ch)
                {
                    1-> "The addition is : ${a+b}"

                    2-> "The Subtraction is : ${a-b}"

                    3-> "The Multiplication is : ${a*b}"

                    4-> "The Division is : ${a/b}"

                    else-> "Plz Enter valid Choice..!"
                }
    println(result)
}