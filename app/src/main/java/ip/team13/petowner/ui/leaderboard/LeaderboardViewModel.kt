package ip.team13.petowner.ui.leaderboard

import androidx.lifecycle.ViewModel
import ip.team13.petowner.data.dto.register.leaderboard.LeaderBoardDataModel

class LeaderboardViewModel : ViewModel() {

    val person1 = LeaderBoardDataModel.newInstance(
        profilePictureUrl = "https://9b16f79ca967fd0708d1-2713572fef44aa49ec323e813b06d2d9.ssl.cf2.rackcdn.com/1140x_a10-7_cTC/Jimmy-Fontaine-jpg-1594219793.jpg"
    )
    val person2 = LeaderBoardDataModel.newInstance(
        profilePictureUrl = "https://images.actionnetwork.com/blog/2020/04/michaeljordan-1.jpg"
    )
    val person3 = LeaderBoardDataModel.newInstance(
        profilePictureUrl = "https://s7d2.scene7.com/is/image/TWCNews/jimmy_fallon_ap_jpg"
    )

}