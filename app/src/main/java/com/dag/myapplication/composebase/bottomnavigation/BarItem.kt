package com.dag.myapplication.composebase.bottomnavigation

import com.dag.myapplication.R
import com.dag.myapplication.composebase.navcontroller.NavScreen

enum class BarItem(var title:String,var icon:Int, var route:String){
    HOME_SCREEN("Home Screen",R.drawable.ic_baseline_house, NavScreen.HomeScreen.route),
    PROFILE_SCREEN("Profile",R.drawable.ic_baseline_person, NavScreen.HomeListScreen.route),
}
