package com.kirara.mealzapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kirara.mealzapp.ui.detail.MealDetailsScreen
import com.kirara.mealzapp.ui.detail.MealDetailsViewModel
import com.kirara.mealzapp.ui.meals.MealCategoriesScreen
import com.kirara.mealzapp.ui.theme.MealzAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealzAppTheme {
                FoodzApp()
            }
        }
    }
}

@Composable
private fun FoodzApp(){
    val navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = "destination_meals_list" ){
        composable(route = "destination_meals_list"){
            MealCategoriesScreen(){ navigationMealId ->
                navHostController.navigate("destination_meals_details/$navigationMealId")
            }
        }
        composable(
            route = "destination_meals_details/{meal_category_id}",
            arguments = listOf(navArgument("meal_category_id"){
                type = NavType.StringType
            })
        ){
            val viewModel: MealDetailsViewModel = viewModel()
            MealDetailsScreen(viewModel.mealState.value)
        }
    }
}