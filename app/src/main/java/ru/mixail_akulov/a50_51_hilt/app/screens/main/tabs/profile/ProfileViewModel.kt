package ru.mixail_akulov.a50_51_hilt.app.screens.main.tabs.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.mixail_akulov.a50_51_hilt.app.model.Result
import ru.mixail_akulov.a50_51_hilt.app.model.accounts.AccountsRepository
import ru.mixail_akulov.a50_51_hilt.app.model.accounts.entities.Account
import ru.mixail_akulov.a50_51_hilt.app.screens.base.BaseViewModel
import ru.mixail_akulov.a50_51_hilt.app.utils.logger.Logger
import ru.mixail_akulov.a50_51_hilt.app.utils.share
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    accountsRepository: AccountsRepository,
    logger: Logger
) : BaseViewModel(accountsRepository, logger) {

    private val _account = MutableLiveData<Result<Account>>()
    val account = _account.share()

    init {
        viewModelScope.launch {
            accountsRepository.getAccount().collect {
                _account.value = it
            }
        }
    }

    fun reload() {
        accountsRepository.reloadAccount()
    }

}