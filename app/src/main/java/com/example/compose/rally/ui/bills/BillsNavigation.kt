package com.example.compose.rally.ui.bills

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.compose.rally.Bills
import com.example.compose.rally.SingleBill
import com.example.compose.rally.navigationSingleTopTo

private const val BILLS_ROUTE = "BILLS_ROUTE"


fun NavGraphBuilder.billsScreen(
    onBillClick: (String) -> Unit = {}
) {
    navigation(startDestination = Bills.route, route = BILLS_ROUTE) {

    composable(route = Bills.route) {
        val viewModel: BillsViewModel = viewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        BillsScreen(
            uiState = uiState,
            onBillClick = onBillClick
        )
    }
        composable(
            route = SingleBill.routeWithArgs,
            arguments = SingleBill.arguments,
            deepLinks = SingleBill.deepLinks
        ) { navStackEntry ->
            val billType =
                navStackEntry.arguments?.getString(SingleBill.billTypeArg)

            val viewModel: SingleBillViewModel = viewModel(
                factory = SingleBillViewModel.Factory(billType)
            )
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()


            SingleBillScreen(uiState = uiState, onAlertDismiss = {
                viewModel.onAlertDismiss()
            })
        }

    }
}

fun NavHostController.navigateToSingleBill(billType: String) {
    navigationSingleTopTo("${SingleBill.route}/${billType}")
}