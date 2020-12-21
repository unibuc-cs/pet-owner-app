package ip.team13.petowner.data.repository

class UserRepository {

    val imageUrl: String
        get() = "https://picsum.photos/200/200"

    var name: String = "John Doe"

    val level: String
        get() = 30.toString()

    val weeklyExp: String
        get() = 65.toString()

    val isVip: Boolean
        get() = true
}