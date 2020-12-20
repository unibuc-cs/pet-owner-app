package ip.team13.petowner.data.repository

class UserRepository {

    val imageUrl: String
        get() = "https://picsum.photos/200/200"

    val name: String
        get() = "John Doe"

    val level: String
        get() = 30.toString()

    val weeklyExp: String
        get() = 65.toString()
}