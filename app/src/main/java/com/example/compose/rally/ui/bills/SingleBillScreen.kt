package com.example.compose.rally.ui.bills

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import com.example.compose.rally.ui.components.BillRow
import com.example.compose.rally.ui.components.RallyAlertDialog
import com.example.compose.rally.ui.components.StatementBody
import java.util.Locale

@Composable
fun SingleBillScreen(
    uiState: SingleBillUiState,
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
        modifier = Modifier.clearAndSetSemantics { contentDescription = "Single Bill" },
        items = listOf(uiState.bill),
        colors = { uiState.bill.color },
        amounts = { uiState.bill.amount },
        amountsTotal = uiState.bill.amount,
        circleLabel = uiState.bill.name,
    ) { row ->
        BillRow(
            name = row.name,
            due = row.due,
            amount = row.amount,
            color = row.color
        )
    }
}
