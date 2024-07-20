package com.transvision.g2g.ui.screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.transvision.g2g.ui.screen.approva.ApprovalScreen
import com.transvision.g2g.ui.screen.dashboard.DashboardScreen
import com.transvision.g2g.ui.screen.dashboard.misdashboard.MISDashBoardScreen
import com.transvision.g2g.ui.screen.dashboard.RTI.RTIDashBoardScreen
import com.transvision.g2g.ui.screen.dashboard.dss.DssDashboard
import com.transvision.g2g.ui.screen.dashboard.eidashboard.EIDashboard
import com.transvision.g2g.ui.screen.dashboard.openaccess.OpenAccessDashBoard
import com.transvision.g2g.ui.screen.dashboard.rcdashboard.RCDashboardScreen
import com.transvision.g2g.ui.screen.dashboard.rnddashboard.RNDScreen
import com.transvision.g2g.ui.screen.dashboard.rtdashboard.RTDashBoard
import com.transvision.g2g.ui.screen.dashboard.tabs.HomeContent
import com.transvision.g2g.ui.screen.dashboard.vendor.VendorDashBoard
import com.transvision.g2g.ui.screen.dashboard.wheelingbanking.wheelingBankingDashBoard
import com.transvision.g2g.ui.screen.loginscreens.LoginScreen
import com.transvision.g2g.ui.screen.registration.state.RegistrationScreen

/**
 * Login, registration, forgot password screens nav graph builder
 * (Unauthenticated user)
 */
fun NavGraphBuilder.unauthenticatedGraph(navController: NavController) {

    navigation(
        route = NavigationRoutes.Unauthenticated.NavigationRoute.route,
        startDestination = NavigationRoutes.Unauthenticated.Login.route
    ) {

        // Login
        composable(route = NavigationRoutes.Unauthenticated.Login.route) {
            LoginScreen(
                onNavigateToRegistration = {
                    navController.navigate(route = NavigationRoutes.Unauthenticated.Registration.route)
                },
                onNavigateToForgotPassword = {},
                onNavigateToAuthenticatedRoute = {
                    navController.navigate(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                        popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                            inclusive = true
                        }
                    }
                },
            )
        }

        // Registration
        composable(route = NavigationRoutes.Unauthenticated.Registration.route) {
            RegistrationScreen(
                onNavigateBack = {
                    navController.navigateUp()
                },
                onNavigateToAuthenticatedRoute = {
                    navController.navigate(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                        popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}

/**
 * Authenticated screens nav graph builder
 */
fun NavGraphBuilder.authenticatedGraph(navController: NavController) {
    navigation(
        route = NavigationRoutes.Authenticated.NavigationRoute.route,
        startDestination = NavigationRoutes.Authenticated.Dashboard.route
    ) {
        // Dashboard
        composable(route = NavigationRoutes.Authenticated.Dashboard.route) {
            DashboardScreen(navController)
        }
        composable(route = NavigationRoutes.Authenticated.Approval.route + "/{item}"){
            val item = it.arguments?.getString("item")
            ApprovalScreen(item,navController)
        }
        composable(route = NavigationRoutes.Authenticated.MISDashBoard.route){
            MISDashBoardScreen(navController)
        }
        composable(route = NavigationRoutes.Authenticated.Home.route){
            HomeContent(navController)
        }
        composable(route = NavigationRoutes.Authenticated.RNDDashBoard.route){
            RNDScreen(navController)
        }
        composable(route = NavigationRoutes.Authenticated.VendorBoard.route){
            VendorDashBoard(navController)
        }
        composable(route = NavigationRoutes.Authenticated.RTIBoard.route){
            RTIDashBoardScreen(navController)
        }
        composable(route = NavigationRoutes.Authenticated.OpenAccessBoard.route){
            OpenAccessDashBoard(navController)
        }
        composable(route = NavigationRoutes.Authenticated.DSSBoard.route){
            DssDashboard(navController)
        }
        composable(route = NavigationRoutes.Authenticated.RCBoard.route){
            RCDashboardScreen(navController)
        }
        composable(route = NavigationRoutes.Authenticated.EIBoard.route){
            EIDashboard(navController)
        }
        composable(route = NavigationRoutes.Authenticated.WheelingAndBankingBoard.route){
            wheelingBankingDashBoard()
        }
        composable(route = NavigationRoutes.Authenticated.RTBoard.route){
            RTDashBoard(navController)
        }
    }
}