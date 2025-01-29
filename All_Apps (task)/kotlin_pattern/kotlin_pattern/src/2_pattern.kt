fun main()
{
    for (i in 1..5)
    {
        var j = (5-i)
        while (j>0)
        {
            print(" ")
            j--
        }
        for (k in 1..i)
        {
            print("* ")
        }
        println()
    }
}