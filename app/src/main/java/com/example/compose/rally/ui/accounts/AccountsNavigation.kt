package com.example.compose.rally.ui.accounts

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.compose.rally.Accounts
import com.example.compose.rally.SingleAccount
import com.example.compose.rally.navigationSingleTopTo

private const val ACCOUNTS_ROUTE = "ACCOUNTS_ROUTE"


fun NavGraphBuilder.accountsScreen(
    onAccountClick: (String) -> Unit = {},
) {

    navigation(
        startDestination = Accounts.route, route = ACCOUNTS_ROUTE){
        composable(route = Accounts.route) {
            val viewModel: AccountsViewModel = viewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            AccountsScreen(
                uiState = uiState,
                onAccountClick = onAccountClick
            )
        }

        composable(
            route = SingleAccount.routeWithArgs,
            arguments = SingleAccount.arguments,
            deepLinks = SingleAccount.deepLinks
        ) { navStackEntry ->
            val accountType =
                navStackEntry.arguments?.getString(SingleAccount.accountTypeArg)

            val viewModel: SingleAccountViewModel = viewModel(
                factory = SingleAccountViewModel.Factory(accountType)
            )
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            SingleAccountScreen(
                uiState = uiState,
                onAlertDismiss = {
                    viewModel.onAlertDismiss()
                })
        }

    }

}

fun NavHostController.navigateToSingleAccount(accountType: String) {
    navigationSingleTopTo("${SingleAccount.route}/${accountType}")
}