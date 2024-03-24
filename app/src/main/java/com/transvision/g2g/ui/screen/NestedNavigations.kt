package com.transvision.g2g.ui.screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.transvision.g2g.ui.screen.approva.ApprovalScreen
import com.transvision.g2g.ui.screen.dashboard.DashboardScreen
import com.transvision.g2g.ui.screen.dashboard.misdashboard.MISDashBoardScreen
import com.transvision.g2g.ui.screen.dashboard.tabs.ApprovalTab
import com.transvision.g2g.ui.screen.dashboard.tabs.HomeContent
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
    }
}