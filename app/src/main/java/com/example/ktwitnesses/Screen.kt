package com.example.ktwitnesses

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

@Suppress("ConvertObjectToDataObject")
sealed class NavRoutes(
	val route: String,
	val image: ImageVector? = null,
	val title: String? = null
) {
	object Home : NavRoutes(
		route = "home_screen",
		image = Icons.Filled.Home,
		title = "Home")
	object Favorite : NavRoutes(
		route = "favourite_screen",
		image = Icons.Filled.Favorite,
		title = "Favorite")
	object Cart : NavRoutes(
		route = "cart_screen",
		image = Icons.Filled.ShoppingCart,
		title = "Cart")
	data object Profile : NavRoutes(
		route = "profile_screen",
		image = Icons.Filled.Person,
		title = "Profile")
	data object Order : NavRoutes(
		route = "checkout_screen",
		image = Icons.Filled.ShoppingCart,
		title = "Checkout")
	data object DeliveryDetails  : NavRoutes(
		route = "delivery_details_screen",
		image = Icons.Filled.Info,
		title = "Delivery Details")
}

