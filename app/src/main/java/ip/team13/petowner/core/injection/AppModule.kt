package ip.team13.petowner.core.injection

import ip.team13.petowner.data.domain.LeaderboardType
import ip.team13.petowner.data.dto.ActivityEntry
import ip.team13.petowner.data.repository.*
import ip.team13.petowner.ui.activities.ActivitiesViewModel
import ip.team13.petowner.ui.activities.details.ActivityDetailsViewModel
import ip.team13.petowner.ui.cost.CostTrackerViewModel
import ip.team13.petowner.ui.cost.details.CostDetailsViewModel
import ip.team13.petowner.ui.group.GroupViewModel
import ip.team13.petowner.ui.home.HomeViewModel
import ip.team13.petowner.ui.home.leaderboard.HomeLeaderboardTabViewModel
import ip.team13.petowner.ui.leaderboard.LeaderboardViewModel
import ip.team13.petowner.ui.login.LoginViewModel
import ip.team13.petowner.ui.main.MainViewModel
import ip.team13.petowner.ui.pet.PetProfileViewModel
import ip.team13.petowner.ui.pet.details.PetDetailsViewModel
import ip.team13.petowner.ui.register.RegisterViewModel
import ip.team13.petowner.ui.splash.SplashViewModel
import ip.team13.petowner.ui.user.UserProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { ActivitiesRepository(get()) }
    single { AuthRepository(get(), get()) }
    single { LeaderboardRepository(get()) }
    single { PetRepository(get(), get()) }
    single { UserRepository() }

    viewModel { (onAddActivity: () -> Unit) ->
        ActivitiesViewModel(
            onAddActivity = onAddActivity,
            petRepository = get(),
            activityRepository = get()
        )
    }
    viewModel { (petId: Int) ->
        ActivityDetailsViewModel(
            petId
        )
    }
    viewModel { CostDetailsViewModel() }
    viewModel { CostTrackerViewModel() }
    viewModel { GroupViewModel() }
    viewModel { HomeViewModel(get()) }
    viewModel { (leaderboardType: LeaderboardType) ->
        HomeLeaderboardTabViewModel(
            leaderboardType,
            get()
        )
    }
    viewModel { LeaderboardViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { MainViewModel() }
    viewModel { PetDetailsViewModel() }
    viewModel { (petId: Int) ->
        PetProfileViewModel(
            get(),
            petId
        )
    }
    viewModel { RegisterViewModel(get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { (isOwnUserProfile: Boolean) ->
        UserProfileViewModel(isOwnUserProfile, get(), get())
    }
}