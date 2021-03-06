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
import ip.team13.petowner.ui.tips.TipsViewModel
import ip.team13.petowner.ui.user.UserProfileViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { ActivitiesRepository(get()) }
    single { AuthRepository(get()) }
    single { GroupRepository(get()) }
    single { LeaderboardRepository(get()) }
    single { PetRepository(get(), get()) }
    single { UserRepository(get(), get()) }
    single { CostTrackerRepository(get(), get()) }
    single { TipsRepository(get()) }


    viewModel { TipsViewModel(get()) }
    viewModel { (onAddActivity: () -> Unit) ->
        ActivitiesViewModel(
            onAddActivity = onAddActivity,
            petRepository = get(),
            activityRepository = get(),
            userRepository = get()
        )
    }
    viewModel { (petId: Int) ->
        ActivityDetailsViewModel(petId)
    }
    viewModel { CostDetailsViewModel(get()) }
    viewModel { CostTrackerViewModel(get()) }
    viewModel { GroupViewModel(get(), get(), get()) }
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
    viewModel { (petId: Int) ->
        PetDetailsViewModel(petId, get(), get(), get())
    }
    viewModel { (petId: Int) ->
        PetProfileViewModel(petId, get(), get())
    }
    viewModel { RegisterViewModel(get(), get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { (isOwnUserProfile: Boolean, userId: Int) ->
        UserProfileViewModel(isOwnUserProfile, userId, get(), get(), get())
    }

    single { StringResources(androidContext()) }
}