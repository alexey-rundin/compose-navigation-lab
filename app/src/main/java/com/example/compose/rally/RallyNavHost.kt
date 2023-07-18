package com.example.compose.rally

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.compose.rally.ui.accounts.accountsScreen
import com.example.compose.rally.ui.accounts.navigateToSingleAccount
import com.example.compose.rally.ui.bills.billsScreen
import com.example.compose.rally.ui.bills.navigateToSingleBill
import com.example.compose.rally.ui.overview.overviewScreen

@Composable
fun RallyNavHost(
    navController : NavHostController,
    modifier: Modifier = Modifier
    ) {
    NavHost(
        navController = navController,
        startDestination = Overview.route,
        modifier = modifier
    ) {

        overviewScreen(
            onClickSeeAllAccounts = {
                navController.navigationSingleTopTo(Accounts.route)
            },
            onClickSeeAllBills = {
                navController.navigationSingleTopTo(Bills.route)
            },
            onAccountClick = {accountType ->
                navController.navigateToSingleAccount(accountType)
            },
            onBillClick = {billType ->
                navController.navigateToSingleBill(billType)
            }
        )

        accountsScreen(
            onAccountClick = {accountType ->
                navController.navigateToSingleAccount(accountType)
            }
        )

        billsScreen(
            onBillClick = {billType ->
                navController.navigateToSingleBill(billType)
            }
        )
    }
}

fun NavHostController.navigationSingleTopTo(route: String) {
    navigate(route) {
        popUpTo(this@navigationSingleTopTo.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
