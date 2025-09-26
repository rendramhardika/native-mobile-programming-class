package com.example.mobile_layout.navigation

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object LinearLayoutScreen : Screen("linear_layout")
    object RelativeLayoutScreen : Screen("relative_layout")
    object ConstraintLayoutScreen : Screen("constraint_layout")
}
