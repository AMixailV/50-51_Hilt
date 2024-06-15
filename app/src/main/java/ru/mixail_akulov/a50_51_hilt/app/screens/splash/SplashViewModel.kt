package ru.mixail_akulov.a50_51_hilt.app.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.mixail_akulov.a50_51_hilt.app.model.accounts.AccountsRepository
import ru.mixail_akulov.a50_51_hilt.app.utils.MutableLiveEvent
import ru.mixail_akulov.a50_51_hilt.app.utils.publishEvent
import ru.mixail_akulov.a50_51_hilt.app.utils.share
import javax.inject.Inject

/**
 * SplashViewModel checks whether user is signed-in or not.
 */
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountsRepository: AccountsRepository
) : ViewModel() {

    private val _launchMainScreenEvent = MutableLiveEvent<Boolean>()
    val launchMainScreenEvent = _launchMainScreenEvent.share()

    init {
        viewModelScope.launch {
            _launchMainScreenEvent.publishEvent(accountsRepository.isSignedIn())
        }
    }
}