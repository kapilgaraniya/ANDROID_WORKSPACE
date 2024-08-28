package task_3// * pattern left to right

fun main()
{
    var i =1

    while (i<=5)
    {
        var j=1

        while (j<=i)
        {
            print("*")
            j++
        }
        i++
        println()
    }
}