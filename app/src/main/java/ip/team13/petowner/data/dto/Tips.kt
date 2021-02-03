package ip.team13.petowner.data.dto

import com.squareup.moshi.Json
import ip.team13.petowner.data.domain.Tip
import kotlin.random.Random

data class TipModel(
    @field:Json(name = "tipId")
    val tipId: Int,
    @field:Json(name = "title")
    val title: String?,
    @field:Json(name = "description")
    val description: String?,
    @field:Json(name = "race")
    val race: String?,
    @field:Json(name = "species")
    val species: String?,
    @field:Json(name = "category")
    val category: String?
) {
    companion object {
        fun getPlaceholder(): TipModel {
            val random = Random.nextInt() % 1000
            val random2 = Random.nextInt() % 3
            return TipModel(
                tipId = random,
                title = "title $random",
                description = "description $random",
                species = "species $random2",
                race = "race $random2",
                category = "category $random2"
            )
        }

        fun getList(size: Int = 20): ArrayList<TipModel> {
            val items = arrayListOf<TipModel>()
            for (index in 1..20) {
                items.add(getPlaceholder())
            }
            return items
        }
    }
}

fun TipModel.toTip() =
    Tip(tipId, title?.trim(), description?.trim(), race?.trim(), species?.trim(), category?.trim())