package ip.team13.petowner.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import ip.team13.petowner.core.BaseFragment
import ip.team13.petowner.core.helpers.logErrorAndToast


abstract class GoogleLoginFragment<B : ViewDataBinding> : BaseFragment<B>() {

    companion object {
        const val REQUEST_CODE_SIGN_IN = 3471
    }

    private lateinit var mGoogleSignInClient: GoogleSignInClient

    abstract fun loginWithGoogleAccount(googleId: String, email: String, username: String)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_CODE_SIGN_IN -> {
                val task: Task<GoogleSignInAccount> =
                    GoogleSignIn.getSignedInAccountFromIntent(data)
                handleSignInResult(task)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupGoogleSignInClient()
    }

    override fun onStart() {
        super.onStart()

        checkAlreadySignedIn()
    }

    protected fun signInWithGoogleAccount() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, REQUEST_CODE_SIGN_IN)
    }

    private fun setupGoogleSignInClient() = activity?.run {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private fun checkAlreadySignedIn() = context?.run {
        val googleAccount = GoogleSignIn.getLastSignedInAccount(this) ?: return@run

        loginWithGoogleAccount(
            googleId = googleAccount.id ?: return@run,
            email = googleAccount.email ?: return@run,
            username = googleAccount.displayName ?: return@run
        )
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            completedTask.getResult(ApiException::class.java)?.let { googleAccount ->

                loginWithGoogleAccount(
                    googleId = googleAccount.id ?: return,
                    email = googleAccount.email ?: return,
                    username = googleAccount.displayName ?: return
                )
            } ?: kotlin.run {
                "Google Sign in get account failed".logErrorAndToast(context ?: return)
            }
        } catch (e: ApiException) {
            "Google Sign In failed code=${e.statusCode}".logErrorAndToast(context ?: return)
        }
    }
}