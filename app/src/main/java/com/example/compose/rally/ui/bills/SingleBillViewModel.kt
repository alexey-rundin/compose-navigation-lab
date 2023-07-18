package com.example.compose.rally.ui.bills

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.compose.rally.data.Bill
import com.example.compose.rally.data.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class SingleBillUiState(
    val bill: Bill,
    val errorMessage: String = ""
)

class SingleBillViewModel(billType: String?): ViewModel(){

    private val defaultBill = UserData.bills.first()
    private val _uiState = MutableStateFlow(
        SingleBillUiState(bill = defaultBill)
    )
    val uiState: StateFlow<SingleBillUiState> = _uiState

    init {
        runCatching {
            UserData.getBill(billType)
        }.onSuccess { bill ->
            _uiState.update { currentState ->
                currentState.copy(
                    bill = bill,
                    errorMessage = ""
                )
            }
        }.onFailure {
            _uiState.update { currentState ->
                currentState.copy(
                    errorMessage = "Bill not found"
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

    internal class Factory(private val billType: String?) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            SingleBillViewModel(billType) as T
    }
}