package com.transvision.g2g.ui.screen

sealed class NavigationRoutes {

    // Unauthenticated Routes
    sealed class Unauthenticated(val route: String) : NavigationRoutes() {
        object NavigationRoute : Unauthenticated(route = "unauthenticated")
        object Login : Unauthenticated(route = "login")
        object Registration : Unauthenticated(route = "registration")
    }

    // Authenticated Routes
    sealed class Authenticated(val route: String) : NavigationRoutes() {
        object NavigationRoute : Authenticated(route = "authenticated")
        object Dashboard : Authenticated(route = "Dashboard")
        object Approval : Authenticated(route = "Approval")
        object Home : Authenticated(route = "Home")
        object MISDashBoard : Authenticated(route = "MisDashBoard")

        object RNDDashBoard : Authenticated(route = "RNDDashBoard")
        object VendorBoard : Authenticated(route = "VendorBoard")
        object RTIBoard : Authenticated(route = "RTIDashBoard")
        object OpenAccessBoard : Authenticated(route = "OpenAccessDashBoard")
        object DSSBoard : Authenticated(route = "DSSDashBoard")
        object RCBoard : Authenticated(route = "RCDashBoard")
        object EIBoard : Authenticated(route = "EIDashBoard")
    }
}
