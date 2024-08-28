//q10 WAP to create map instance and store values with State, City pair. Also print all value using loop. (Hashmap)

fun main()
{
    val statesToCities= hashMapOf(
        "Gujarat" to "Ahmedabad",
        "Maharashtra" to "Mumbai",
        "Rajasthan" to "Jaipur"
    )

    for ((state, city) in statesToCities)
    {
        println("State: $state, City: $city")
    }
}
