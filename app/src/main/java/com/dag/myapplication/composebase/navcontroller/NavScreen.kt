package com.dag.myapplication.composebase.navcontroller

sealed class NavScreen(val route:String) {
    data object WelcomeScreen : NavScreen("welcome_screen")
    data object IntroScreen : NavScreen("intro_screen")
    data object WalletScreen : NavScreen("wallet_connection")
    data object LoginScreen: NavScreen("login_screen")
    data object RegisterScreen: NavScreen("register_screen")
    data object HomeScreen: NavScreen("home_screen")
    data object HomeListScreen: NavScreen("homelist_screen")
    data object MainActivity: NavScreen("mainactivity_screen")

}

fun NavScreen.addData(data:Any):String{
    return this.route.plus(data)
}

fun NavScreen.addPath(path:String):String{
    return this.route.plus("/").plus(path)
}