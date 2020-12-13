package ip.team13.petowner.core

import ip.team13.petowner.data.domain.LeaderboardType
import ip.team13.petowner.data.repository.ActivitiesRepository
import ip.team13.petowner.data.repository.AuthRepository
import ip.team13.petowner.data.repository.LeaderboardRepository
import ip.team13.petowner.data.repository.PetRepository
import ip.team13.petowner.ui.activities.ActivitiesViewModel
import ip.team13.petowner.ui.activities.details.ActivityDetailsViewModel
import ip.team13.petowner.ui.cost.CostTrackerViewModel
import ip.team13.petowner.ui.cost.details.CostDetailsViewModel
import ip.team13.petowner.ui.group.GroupViewModel
import ip.team13.petowner.ui.pet.details.PetDetailsViewModel
import ip.team13.petowner.ui.home.HomeViewModel
import ip.team13.petowner.ui.home.leaderboard.HomeLeaderboardTabViewModel
import ip.team13.petowner.ui.leaderboard.LeaderboardViewModel
import ip.team13.petowner.ui.login.LoginViewModel
import ip.team13.petowner.ui.main.MainViewModel
import ip.team13.petowner.ui.pet.PetProfileViewModel
import ip.team13.petowner.ui.register.RegisterViewModel
import ip.team13.petowner.ui.splash.SplashViewModel
import ip.team13.petowner.ui.user.UserProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { AuthRepository() }
    single { LeaderboardRepository() }
    single { PetRepository() }
    single { ActivitiesRepository() }

    viewModel { (onAddActivity: () -> Unit) ->
        ActivitiesViewModel(
            onAddActivity = onAddActivity,
            petRepository = get(),
            activityRepository = get()
        )
    }
    viewModel { ActivityDetailsViewModel() }
    viewModel { CostDetailsViewModel() }
    viewModel { CostTrackerViewModel() }
    viewModel { GroupViewModel() }
    viewModel { HomeViewModel() }
    viewModel { (leaderboardType: LeaderboardType) ->
        HomeLeaderboardTabViewModel(
            leaderboardType,
            get()
        )
    }
    viewModel { LeaderboardViewModel() }
    viewModel { LoginViewModel(get()) }
    viewModel { MainViewModel() }
    viewModel { PetDetailsViewModel() }
    viewModel { PetProfileViewModel() }
    viewModel { RegisterViewModel(get()) }
    viewModel { SplashViewModel() }
    viewModel { UserProfileViewModel() }
}