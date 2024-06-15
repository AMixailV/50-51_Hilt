package ru.mixail_akulov.a50_51_hilt.app.screens.main.auth

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.mixail_akulov.a50_51_hilt.app.R
import ru.mixail_akulov.a50_51_hilt.app.model.AccountAlreadyExistsException
import ru.mixail_akulov.a50_51_hilt.app.model.EmptyFieldException
import ru.mixail_akulov.a50_51_hilt.app.model.Field
import ru.mixail_akulov.a50_51_hilt.app.model.PasswordMismatchException
import ru.mixail_akulov.a50_51_hilt.app.model.accounts.AccountsRepository
import ru.mixail_akulov.a50_51_hilt.app.model.accounts.entities.SignUpData
import ru.mixail_akulov.a50_51_hilt.app.screens.base.BaseViewModel
import ru.mixail_akulov.a50_51_hilt.app.utils.*
import ru.mixail_akulov.a50_51_hilt.app.utils.logger.Logger
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    accountsRepository: AccountsRepository,
    logger: Logger
) : BaseViewModel(accountsRepository, logger) {

    private val _goBackEvent = MutableUnitLiveEvent()
    val goBackEvent = _goBackEvent.share()

    private val _showToastEvent = MutableLiveEvent<Int>()
    val showToastEvent = _showToastEvent.share()

    private val _state = MutableLiveData(State())
    val state = _state.share()

    fun signUp(signUpData: SignUpData) = viewModelScope.safeLaunch {
        showProgress()
        try {
            accountsRepository.signUp(signUpData)
            showSuccessSignUpMessage()
            goBack()
        } catch (e: EmptyFieldException) {
            processEmptyFieldException(e)
        } catch (e: PasswordMismatchException) {
            processPasswordMismatchException()
        } catch (e: AccountAlreadyExistsException) {
            processAccountAlreadyExistsException()
        } finally {
            hideProgress()
        }
    }

    private fun processEmptyFieldException(e: EmptyFieldException) {
        _state.value = when (e.field) {
            Field.Email -> _state.requireValue()
                .copy(emailErrorMessageRes = R.string.field_is_empty)
            Field.Username -> _state.requireValue()
                .copy(usernameErrorMessageRes = R.string.field_is_empty)
            Field.Password -> _state.requireValue()
                .copy(passwordErrorMessageRes = R.string.field_is_empty)
        }
    }

    private fun processPasswordMismatchException() {
        _state.value = _state.requireValue()
            .copy(repeatPasswordErrorMessageRes = R.string.password_mismatch)
    }

    private fun processAccountAlreadyExistsException() {
        _state.value = _state.requireValue()
            .copy(emailErrorMessageRes = R.string.account_already_exists)
    }

    private fun showProgress() {
        _state.value = State(signUpInProgress = true)
    }

    private fun hideProgress() {
        _state.value = _state.requireValue().copy(signUpInProgress = false)
    }

    private fun showSuccessSignUpMessage() = _showToastEvent.publishEvent(R.string.sign_up_success)

    private fun goBack() = _goBackEvent.publishEvent()

    data class State(
        @StringRes val emailErrorMessageRes: Int = NO_ERROR_MESSAGE,
        @StringRes val passwordErrorMessageRes: Int = NO_ERROR_MESSAGE,
        @StringRes val repeatPasswordErrorMessageRes: Int = NO_ERROR_MESSAGE,
        @StringRes val usernameErrorMessageRes: Int = NO_ERROR_MESSAGE,
        val signUpInProgress: Boolean = false,
    ) {
        val showProgress: Boolean get() = signUpInProgress
        val enableViews: Boolean get() = !signUpInProgress
    }

    companion object {
        const val NO_ERROR_MESSAGE = 0
    }

}