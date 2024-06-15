package ru.mixail_akulov.a50_51_hilt.app.screens.main.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.mixail_akulov.a50_51_hilt.app.R
import ru.mixail_akulov.a50_51_hilt.app.model.EmptyFieldException
import ru.mixail_akulov.a50_51_hilt.app.model.Field
import ru.mixail_akulov.a50_51_hilt.app.model.InvalidCredentialsException
import ru.mixail_akulov.a50_51_hilt.app.model.accounts.AccountsRepository
import ru.mixail_akulov.a50_51_hilt.app.screens.base.BaseViewModel
import ru.mixail_akulov.a50_51_hilt.app.utils.*
import ru.mixail_akulov.a50_51_hilt.app.utils.logger.Logger
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    accountsRepository: AccountsRepository,
    logger: Logger
) : BaseViewModel(accountsRepository, logger) {

    private val _state = MutableLiveData(State())
    val state = _state.share()

    private val _clearPasswordEvent = MutableUnitLiveEvent()
    val clearPasswordEvent = _clearPasswordEvent.share()

    private val _showAuthErrorToastEvent = MutableLiveEvent<Int>()
    val showAuthToastEvent = _showAuthErrorToastEvent.share()

    private val _navigateToTabsEvent = MutableUnitLiveEvent()
    val navigateToTabsEvent = _navigateToTabsEvent.share()

    fun signIn(email: String, password: String) = viewModelScope.safeLaunch {
        showProgress()
        try {
            accountsRepository.signIn(email, password)
            launchTabsScreen()
        } catch (e: EmptyFieldException) {
            processEmptyFieldException(e)
        } catch (e: InvalidCredentialsException) {
            processInvalidCredentialsException()
        } finally {
            hideProgress()
        }
    }

    private fun processEmptyFieldException(e: EmptyFieldException) {
        _state.value = _state.requireValue().copy(
            emptyEmailError = e.field == Field.Email,
            emptyPasswordError = e.field == Field.Password
        )
    }

    private fun processInvalidCredentialsException() {
        clearPasswordField()
        showAuthErrorToast()
    }

    private fun showProgress() {
        _state.value = State(signInInProgress = true)
    }

    private fun hideProgress() {
        _state.value = _state.requireValue().copy(signInInProgress = false)
    }

    private fun clearPasswordField() = _clearPasswordEvent.publishEvent()

    private fun showAuthErrorToast() = _showAuthErrorToastEvent.publishEvent(R.string.invalid_email_or_password)

    private fun launchTabsScreen() = _navigateToTabsEvent.publishEvent()

    data class State(
        val emptyEmailError: Boolean = false,
        val emptyPasswordError: Boolean = false,
        val signInInProgress: Boolean = false
    ) {
        val showProgress: Boolean get() = signInInProgress
        val enableViews: Boolean get() = !signInInProgress
    }
}