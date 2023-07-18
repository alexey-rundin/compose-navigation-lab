package com.example.compose.rally.ui.overview

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.compose.rally.Overview

fun NavGraphBuilder.overviewScreen(
    onClickSeeAllAccounts: () -> Unit = {},
    onClickSeeAllBills: () -> Unit = {},
    onAccountClick: (String) -> Unit = {},
    onBillClick: (String) -> Unit = {},
){
    composable(route = Overview.route) {
//        val viewModel: OverviewViewModel = viewModel()
//        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        OverviewScreen(
            onClickSeeAllAccounts = onClickSeeAllAccounts,
            onClickSeeAllBills = onClickSeeAllBills,
            onAccountClick = onAccountClick,
            onBillClick = onBillClick
        )
    }
}