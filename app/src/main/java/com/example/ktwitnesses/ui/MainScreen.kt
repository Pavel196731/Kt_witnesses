package com.example.ktwitnesses.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.MaterialTheme
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Surface
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ktwitnesses.BooksViewModel
import com.example.ktwitnesses.R

@Composable
fun MainScreen() {
	val navController = rememberNavController()
	Column(Modifier.padding(8.dp)) {
		NavHost(navController, startDestination = NavRoutes.Home.route, modifier = Modifier.weight(1f)) {
			composable(NavRoutes.Home.route) { val booksViewModel: BooksViewModel =
				viewModel(factory = BooksViewModel.Factory)

				Scaffold(
					modifier = Modifier.fillMaxSize(),
					topBar = {
						TopAppBar (
							title = {
								Text(text = stringResource(id = R.string.app_name))
							}
						)
					}

				) {
					Surface(modifier = Modifier
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
				} }
			composable(NavRoutes.Favorite.route) { FavouriteScreen()  }
			composable(NavRoutes.Cart.route) { CartScreen() }
			composable(NavRoutes.Profile.route) { ProfileScreen() }
		}
		BottomNavigationBar(navController = navController)
	}
}