package com.example.ktwitnesses.ui

import AddressViewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ktwitnesses.BooksViewModel
import com.example.ktwitnesses.R

@Composable
fun MainScreen() {
    val context = LocalContext.current
    val addressViewModel: AddressViewModel = viewModel()

    LaunchedEffect(Unit) {
        addressViewModel.loadAddresses(context)
    }

    val navController = rememberNavController()
    val currentBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = currentBackStackEntry?.destination?.route
    val screensWithBottomNav = listOf(
        NavRoutes.Home.route,
        NavRoutes.Favorite.route,
        NavRoutes.Cart.route,
        NavRoutes.Profile.route
    )

    Scaffold(
        bottomBar = {
            if (currentRoute in screensWithBottomNav) {
                BottomNavigationBar(navController = navController)
            }
        }

    )
    { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = NavRoutes.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(NavRoutes.Home.route) {
                val booksViewModel: BooksViewModel =
                    viewModel(factory = BooksViewModel.Factory)
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = stringResource(id = R.string.app_name))
                            }
                        )
                    }
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        color = MaterialTheme.colors.background
                    ) {
                        HomeScreen(
                            booksUiState = booksViewModel.booksUiState,
                            retryAction = { booksViewModel.getBooks() },
                            modifier = Modifier
                        )
                    }
                }
            }
            composable(NavRoutes.Favorite.route) { FavouriteScreen() }
            composable(NavRoutes.Cart.route) {
                CartScreen(
                    onProceedToOrder = { navController.navigate(NavRoutes.Order.route) }
                )
            }
            composable(NavRoutes.Order.route) {

                OrderScreen(
                    navController = navController,
                    viewModel1 = addressViewModel,
                    onBackPressed = { navController.popBackStack() },
                    onAddressChange = { navController.navigate("address_selection") }
                )
            }
            composable("address_selection") {
                val addresses = addressViewModel.addresses.collectAsState(initial = emptyList())
                val selectedAddress =
                    addressViewModel.selectedAddress.collectAsState(initial = null)

                AddressSelectionScreen(
                    addresses = addresses.value,
                    selectedAddress = selectedAddress.value,
                    onAddressSelected = { address ->
                        addressViewModel.selectAddress(address)
                        navController.popBackStack()
                    },
                    onAddAddress = { /* Реализация добавления адреса */ },
                    onSave = { navController.popBackStack() },
                    onBack = { navController.popBackStack() },
                    onBackPressed = { true }
                )
            }
            composable(NavRoutes.Profile.route) { ProfileScreen() }
        }
    }
}
