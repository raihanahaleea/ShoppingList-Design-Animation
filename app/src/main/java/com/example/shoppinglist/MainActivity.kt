package com.example.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglist.ui.theme.ShoppingListTheme
import kotlinx.coroutines.launch



class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingListTheme {
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val currentScreen = when (currentRoute) {
                    Screen.Home.route -> Screen.Home
                    Screen.Profile.route -> Screen.Profile
                    Screen.Setting.route -> Screen.Setting
                    else -> Screen.Home
                }

                val bottomBarItems = listOf(
                    Screen.Home,
                    Screen.Profile
                )

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                            Text("Menu Aplikasi", modifier = Modifier.padding(16.dp))
                            Divider()
                            NavigationDrawerItem(
                                label = { Text(Screen.Setting.title) },
                                selected = currentRoute == Screen.Setting.route,
                                onClick = {
                                    scope.launch { drawerState.close() }
                                    navController.navigate(Screen.Setting.route)
                                },
                                icon = { Icon(Screen.Setting.icon, contentDescription = Screen.Setting.title) }
                            )
                        }
                    }
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text(currentScreen.title) },
                                navigationIcon = {
                                    IconButton(onClick = {
                                        scope.launch { drawerState.open() }
                                    }) {
                                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                                    }
                                }
                            )
                        },
                        bottomBar = {
                            NavigationBar {
                                bottomBarItems.forEach { screen ->
                                    NavigationBarItem(
                                        selected = currentRoute == screen.route,
                                        onClick = {
                                            navController.navigate(screen.route) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        },
                                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                                        label = { Text(screen.title) },

                                        // Warna diubah agar lebih kontras dan interaktif di dark mode
                                        colors = NavigationBarItemDefaults.colors(
                                            // Warna ikon & teks lebih cerah agar kontras di dark mode
                                            selectedIconColor = Color(0xFF81C784),
                                            selectedTextColor = Color(0xFF81C784),
                                            // Latar belakang indikator (glow effect) yang lembut
                                            indicatorColor = Color(0xFF4CAF50).copy(alpha = 0.2f),
                                            // Warna saat tidak terpilih
                                            unselectedIconColor = Color.Gray,
                                            unselectedTextColor = Color.Gray
                                        )
                                    )
                                }
                            }
                        }
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = Screen.Home.route,
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable(
                                Screen.Home.route,
                                enterTransition = { fadeIn(animationSpec = tween(300)) },
                                exitTransition = { fadeOut(animationSpec = tween(300)) }
                            ) {
                                HomeScreen(navController)
                            }
                            composable(
                                Screen.Profile.route,
                                enterTransition = { fadeIn(animationSpec = tween(300)) },
                                exitTransition = { fadeOut(animationSpec = tween(300)) }
                            ) {
                                ProfileScreen()
                            }
                            composable(
                                Screen.Setting.route,
                                enterTransition = { fadeIn(animationSpec = tween(300)) },
                                exitTransition = { fadeOut(animationSpec = tween(300)) }
                            ) {
                                SettingScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String, val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    object Home : Screen("home", "Shopping List", Icons.Default.Home)
    object Profile : Screen("profile", "Profile", Icons.Default.Person)
    object Setting : Screen("setting", "Settings", Icons.Default.Settings)
}