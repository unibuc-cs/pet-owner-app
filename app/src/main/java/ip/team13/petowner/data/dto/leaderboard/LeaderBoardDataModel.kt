package ip.team13.petowner.data.dto.register.leaderboard

import kotlin.random.Random

data class LeaderBoardDataModel(
    val id: String,
    val name: String,
    val points: Int,
    val profilePictureUrl: String,
    val position: Int
) {
    companion object {
        fun newInstance(
            profilePictureUrl: String? = null
        ): LeaderBoardDataModel {
            val random = Random.nextInt() % 100
            return LeaderBoardDataModel(
                id = random.toString(),
                name = "Alison $random",
                points = random * 3,
                profilePictureUrl = profilePictureUrl ?: "",
                position = random
            )
        }
    }
}