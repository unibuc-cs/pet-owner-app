package ip.team13.petowner.core.injection

import ip.team13.petowner.core.helpers.StringResources
import ip.team13.petowner.data.domain.LeaderboardType
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
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { ActivitiesRepository(get()) }
    single { AuthRepository(get()) }
    single { LeaderboardRepository(get()) }
    single { PetRepository(get(), get()) }
    single { UserRepository(get(), get()) }
    single { CostTrackerRepository(get(), get()) }

    viewModel { (onAddActivity: () -> Unit) ->
        ActivitiesViewModel(
            onAddActivity = onAddActivity,
            petRepository = get(),
            activityRepository = get()
        )
    }
    viewModel { (petId: Int) ->
        ActivityDetailsViewModel(petId)
    }
    viewModel { CostTrackerViewModel(get()) }
    viewModel { CostDetailsViewModel(get()) }
    viewModel { GroupViewModel() }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { (leaderboardType: LeaderboardType) ->
        HomeLeaderboardTabViewModel(
            leaderboardType,
            get()
        )
    }
    viewModel { LeaderboardViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { MainViewModel() }
    viewModel { (groupId: String) ->
        PetDetailsViewModel(groupId, get())
    }
    viewModel { (petId: Int) ->
        PetProfileViewModel(get(), petId)
    }
    viewModel { RegisterViewModel(get(), get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { (isOwnUserProfile: Boolean) ->
        UserProfileViewModel(isOwnUserProfile, get(), get(), get())
    }

    single { StringResources(androidContext()) }
}