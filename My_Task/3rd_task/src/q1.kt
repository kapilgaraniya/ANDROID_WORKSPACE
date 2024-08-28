// * pattern  right to left

fun main()
{
    var i = 1

    while (i<=5)
    {
        var j=5

        while (j>=1)
        {
            if (j>i)
            {
                print(" ")
            }
            else
            {
                print("*")
            }
            j--
        }

        println()
        i++
    }
}