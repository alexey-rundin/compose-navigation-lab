package com.example.compose.rally.ui.accounts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.compose.rally.data.Account
import com.example.compose.rally.data.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class SingleAccountUiState(val account: Account, val errorMessage: String = "")

class SingleAccountViewModel(accountType: String?): ViewModel() {

    private val defaultAccount = UserData.accounts.first()

    private val _uiState =
        MutableStateFlow(SingleAccountUiState(account = defaultAccount))
    val uiState: StateFlow<SingleAccountUiState> = _uiState

    init {
        runCatching {
            UserData.getAccount(accountType)
        }.onSuccess { account ->
            _uiState.update { currentState ->
                currentState.copy(
                    account = account,
                    errorMessage = ""
                )
            }
        }.onFailure {
            _uiState.update { currentState ->
                currentState.copy(
                    errorMessage = "Account not found"
                )
            }
        }
    }

    fun onAlertDismiss() {
        _uiState.update {currentState ->
            currentState.copy(
                errorMessage = ""
            )
        }
    }

    internal class Factory(private val accountType: String?) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            SingleAccountViewModel(accountType) as T
    }
}