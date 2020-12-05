package ip.team13.petowner.core

import ip.team13.petowner.ui.activities.ActivitiesViewModel
import ip.team13.petowner.ui.activities.details.ActivityDetailsViewModel
import ip.team13.petowner.ui.cost.CostTrackerViewModel
import ip.team13.petowner.ui.cost.details.CostDetailsViewModel
import ip.team13.petowner.ui.group.GroupViewModel
import ip.team13.petowner.ui.pet.details.PetDetailsViewModel
import ip.team13.petowner.ui.home.HomeViewModel
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

    viewModel { ActivitiesViewModel() }
    viewModel { ActivityDetailsViewModel() }
    viewModel { CostDetailsViewModel() }
    viewModel { CostTrackerViewModel() }
    viewModel { GroupViewModel() }
    viewModel { HomeViewModel() }
    viewModel { LeaderboardViewModel() }
    viewModel { LoginViewModel() }
    viewModel { MainViewModel() }
    viewModel { PetDetailsViewModel() }
    viewModel { PetProfileViewModel() }
    viewModel { RegisterViewModel() }
    viewModel { SplashViewModel() }
    viewModel { UserProfileViewModel() }
}