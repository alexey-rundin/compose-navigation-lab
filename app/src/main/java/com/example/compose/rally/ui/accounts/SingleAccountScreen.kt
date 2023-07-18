package com.example.compose.rally.ui.accounts

import androidx.compose.runtime.Composable
import com.example.compose.rally.ui.components.AccountRow
import com.example.compose.rally.ui.components.RallyAlertDialog
import com.example.compose.rally.ui.components.StatementBody
import java.util.Locale

/**
 * Detail screen for a single account.
 */
@Composable
fun SingleAccountScreen(
    uiState: SingleAccountUiState,
    onAlertDismiss: () -> Unit = {},
) {
    if (uiState.errorMessage.isNotBlank()) {
        RallyAlertDialog(
            onDismiss = onAlertDismiss,
            bodyText = uiState.errorMessage,
            buttonText = "Dismiss".uppercase(Locale.getDefault())
        )
    }
    StatementBody(
        items = listOf(uiState.account),
        colors = { uiState.account.color },
        amounts = { uiState.account.balance },
        amountsTotal = uiState.account.balance,
        circleLabel = uiState.account.name,
    ) { row ->
        AccountRow(
            name = row.name,
            number = row.number,
            amount = row.balance,
            color = row.color
        )
    }
}
