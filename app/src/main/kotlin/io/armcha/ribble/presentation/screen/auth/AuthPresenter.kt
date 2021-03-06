package io.armcha.ribble.presentation.screen.auth

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Intent
import android.net.Uri
import io.armcha.ribble.data.network.ApiConstants
import io.armcha.ribble.di.scope.PerActivity
import io.armcha.ribble.domain.interactor.UserInteractor
import io.armcha.ribble.presentation.base_mvp.api.ApiPresenter
import javax.inject.Inject

/**
 * Created by Chatikyan on 10.08.2017.
 */
@PerActivity
class AuthPresenter @Inject constructor(private val userInteractor: UserInteractor)
    : ApiPresenter<AuthContract.View>(), AuthContract.Presenter {

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onStart() {
        if (AUTH statusIs SUCCESS)
            view?.showLoading()
    }

    override fun makeLogin() {
        view?.startOAuthIntent(Uri.parse(ApiConstants.LOGIN_OAUTH_URL))
    }

    override fun checkLogin(resultIntent: Intent?) {
        val userCode: String? = resultIntent?.data?.getQueryParameter("code")
        userCode?.let {
            fetch(userInteractor.getUser(it), AUTH) {
                view?.hideLoading()
                view?.openHomeActivity()
            }
        }
    }

    override fun onRequestStart() {
        view?.showLoading()
    }

    override fun onRequestError(errorMessage: String?) {
        view?.apply {
            hideLoading()
            showError(errorMessage)
        }
    }
}