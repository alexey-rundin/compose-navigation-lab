package com.example.compose.rally.ui.bills

import androidx.lifecycle.ViewModel
import com.example.compose.rally.data.Bill
import com.example.compose.rally.data.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


data class BillsUiState(
    val bills: List<Bill> = listOf(),
    val amountTotal: Float = 0f
)


class BillsViewModel: ViewModel() {

    private val _uiState =
        MutableStateFlow(BillsUiState())

    val uiState: StateFlow<BillsUiState> = _uiState

    init{
        val bills = UserData.bills
        val amountTotal = bills.map { bill -> bill.amount }.sum()

        _uiState.update { currentState ->
            currentState.copy(
                bills = bills,
                amountTotal = amountTotal
            )
        }
    }
}