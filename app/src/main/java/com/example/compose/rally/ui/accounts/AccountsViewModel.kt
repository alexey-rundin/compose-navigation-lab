package com.example.compose.rally.ui.accounts

import androidx.lifecycle.ViewModel
import com.example.compose.rally.data.Account
import com.example.compose.rally.data.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

private const val DEFAULT_AMOUNT_TOTAL = 0f

data class AccountUiState(
    val amountsTotal: Float = DEFAULT_AMOUNT_TOTAL,
    val accounts: List<Account> = listOf()
)

class AccountsViewModel: ViewModel(){
    private val _uiState =
        MutableStateFlow(AccountUiState())
    val uiState: StateFlow<AccountUiState> = _uiState

    init {
        val accounts = UserData.accounts
        val amountsTotal = accounts.map { account -> account.balance }.sum()
        _uiState.update { currentState ->
            currentState.copy(
                amountsTotal = amountsTotal,
                accounts = accounts
            )
        }
    }
}