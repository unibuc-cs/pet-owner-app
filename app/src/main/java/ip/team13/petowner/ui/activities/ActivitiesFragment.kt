package ip.team13.petowner.ui.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import ip.team13.petowner.data.dto.activities.ActivityDataModel
import ip.team13.petowner.data.dto.pet.PetDataModel
import ip.team13.petowner.databinding.ActivitiesScreenBinding
import ip.team13.petowner.ui.activities.adapters.ActivityAdapter
import ip.team13.petowner.ui.activities.models.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActivitiesFragment : BaseFragment() {

    override val layout: Int
        get() = R.layout.activities_screen

    override val viewModel: ActivitiesViewModel by viewModel()

    private lateinit var binding: ActivitiesScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.activities_screen,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.adapter = ActivityAdapter(getMockData())
        binding.viewModel = viewModel
    }

    // TODO remove mock data with real data from server
    private fun getMockData() =
        arrayListOf(
            ActivitySelectPet(),
            ActivityPets(
                arrayListOf(
                    PetDataModel.getPlaceholder(),
                    PetDataModel.getPlaceholder(),
                    PetDataModel.getPlaceholder(),
                    PetDataModel.getPlaceholder(),
                    PetDataModel.getPlaceholder()
                )
            ),
            ActivityAdd({}),
            ActivityDate(date = "21 Oct 2020"),
            ActivityItem(ActivityDataModel.getPlaceholder()),
            ActivityItem(ActivityDataModel.getPlaceholder()),
            ActivityDate(date = "25 Oct 2020"),
            ActivityItem(ActivityDataModel.getPlaceholder()),
            ActivityDate(date = "26 Oct 2020"),
            ActivityItem(ActivityDataModel.getPlaceholder()),
            ActivityItem(ActivityDataModel.getPlaceholder()),
            ActivityItem(ActivityDataModel.getPlaceholder()),
            ActivityItem(ActivityDataModel.getPlaceholder()),
            ActivityItem(ActivityDataModel.getPlaceholder()),
            ActivityItem(ActivityDataModel.getPlaceholder()),
            ActivityItem(ActivityDataModel.getPlaceholder()),
            ActivityItem(ActivityDataModel.getPlaceholder()),
            ActivityDate(date = "28 Oct 2020"),
            ActivityItem(ActivityDataModel.getPlaceholder()),
            ActivityItem(ActivityDataModel.getPlaceholder()),
            ActivityDate(date = "29 Oct 2020"),
            ActivityItem(ActivityDataModel.getPlaceholder()),
        )

}