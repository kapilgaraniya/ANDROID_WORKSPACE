package task_3// 12345 pattern  right to left

fun main()
{
    var i=1

    while (i<=5)
    {
        var j=1

        while (j<=i)
        {
            print(j)
            j++
        }
        println()
        i++
    }
}