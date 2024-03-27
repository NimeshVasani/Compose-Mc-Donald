package com.example.composemcdonald

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.window.OnBackInvokedCallback
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composemcdonald.ui.cart.CartScreen
import com.example.composemcdonald.ui.details.DetailsScreen
import com.example.composemcdonald.ui.home.HomeScreen
import com.example.composemcdonald.ui.login.LoginScreen
import com.example.composemcdonald.ui.menu.MenuScreen
import com.example.composemcdonald.ui.menu.MenuViewModel
import com.example.composemcdonald.ui.theme.McComposeTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setContent {


            McComposeTheme() {

                val navController = rememberNavController()
                val viewModel: MenuViewModel = viewModel()

                NavHost(navController, startDestination = "login") {

                    composable("home") {
                        HomeScreen(
                            onCategoryClick = { navController.navigate("menu") },
                            onMenuItemClick = { navController.navigate("menu") },

                            )
                    }

                    composable("login") {
                        LoginScreen(onButtonClick = {
                            navController.navigate("home")
                        })
                    }

                    composable("menu") {
                        Box {
                            MenuScreen(
                                onBackClick = { navController.navigateUp() },
                                onMenuItemClick = { menuItemId ->
                                    navController.navigate("details/$menuItemId")
                                },
                                viewModel = viewModel,
                                onCartButtonClick = {
                                    navController.navigate("cart")
                                }
                            )
                        }
                    }

                    composable(
                        "details/{menuItemId}",
                        arguments = listOf(navArgument("menuItemId") { type = NavType.LongType })
                    ) { backStackEntry ->
                        DetailsScreen(
                            viewModel = viewModel,
                            menuItemId = backStackEntry.arguments!!.getLong("menuItemId"),
                            onBackClick = { navController.navigateUp() }
                        )
                    }
                    composable(
                        "cart",
                    ) {

                        CartScreen(
                            viewModel = viewModel,
                            onMenuItemClick = { menuItemId -> navController.navigate("details/$menuItemId") },
                            onBackClick = {

                                navController.navigate("menu")
                            }
                        )
                    }


                }

            }
        }
    }
}

