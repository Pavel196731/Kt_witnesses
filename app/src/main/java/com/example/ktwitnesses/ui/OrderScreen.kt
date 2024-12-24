@file:Suppress("PreviewAnnotationInFunctionWithParameters")

package com.example.ktwitnesses.ui

import AddressViewModel
import OrderViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ktwitnesses.R

@Preview(showBackground = true)
@Composable
fun OrderScreen(
    navController: NavController,
    viewModel: OrderViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    viewModel1: AddressViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onBackPressed: () -> Unit,
    onChangeAddress: () -> Unit = {},
    onUserClick: () -> Unit = {},
    onAddressChange: () -> Unit = {},
    onCommentClick: () -> Unit = {}
) {
    val selectedOption by viewModel.selectedOption
    val isChecked by viewModel.isChecked
    val address by viewModel1.selectedAddress.collectAsState()
    Scaffold(topBar = {
        TopAppBar(title = { stringResource(id = R.string.order_screen_title) }, navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(Icons.Filled.ArrowBack, contentDescription = stringResource(id = R.string.back_button))
            }
        })
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                listOf(
                    stringResource(id = R.string.pickup_option),
                    stringResource(id = R.string.courier_option)
                ).forEach { option ->
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 4.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .border(2.dp, Color.Black, RoundedCornerShape(12.dp))
                            .selectable(selected = (option == selectedOption),
                                onClick = { viewModel.setSelectedOption(option) })
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .clip(RoundedCornerShape(50))
                                .background(
                                    if (option == selectedOption) Color.Green else Color.Transparent
                                )
                                .border(1.dp, Color.Black, RoundedCornerShape(50))
                        )
                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = option, fontWeight = FontWeight.Bold, fontSize = 16.sp
                        )
                    }
                }
            }
            if (selectedOption == stringResource(id = R.string.pickup_option)) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Filled.Place, contentDescription = stringResource(id = R.string.pickup_point), modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(stringResource(id = R.string.pickup_point), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                    Text(
                        text = String.format(stringResource(id = R.string.address_label), address),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Text(stringResource(id = R.string.storage_period), modifier = Modifier.padding(bottom = 16.dp))
                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable(onClick = onUserClick)
                    ) {
                        Icon(imageVector = Icons.Filled.Person, contentDescription = stringResource(id = R.string.user_name))
                        Spacer(modifier = Modifier.width(8.dp))
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(stringResource(id = R.string.user_name))
                            Text(stringResource(id = R.string.user_phone))
                        }
                    }
                    Button(
                        onClick = { navController.navigate("address_selection") },
                        modifier = Modifier.fillMaxWidth()
                    ) { Text(stringResource(id = R.string.change_address_button)) }
                }
            } else {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = stringResource(id = R.string.courier_address))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(stringResource(id = R.string.courier_address), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                    Text(
                        text = String.format(stringResource(id = R.string.address_label), address),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Button(
                        onClick = { navController.navigate("address_selection") },
                        modifier = Modifier.fillMaxWidth()
                    ) { Text(stringResource(id = R.string.change_address_button)) }
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable(onClick = onUserClick)
                    ) {
                        Icon(imageVector = Icons.Filled.Person, contentDescription = stringResource(id = R.string.user_name))
                        Spacer(modifier = Modifier.width(8.dp))
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(stringResource(id = R.string.user_name))
                            Text(stringResource(id = R.string.user_phone))
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable(onClick = onCommentClick)
                    ) {
                        Icon(imageVector = Icons.Filled.Info, contentDescription = stringResource(id = R.string.courier_comments))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(stringResource(id = R.string.courier_comments))
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.toggleChecked() }
                            .padding(vertical = 8.dp)
                    ) {
                        Checkbox(
                            checked = isChecked,
                            onCheckedChange = { viewModel.toggleChecked() }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(stringResource(id = R.string.leave_at_door))
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = stringResource(id = R.string.how_it_works),
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.clickable { }
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { /* Логика оплаты */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(2.dp, Color.Black, RoundedCornerShape(12.dp)),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                )
            ) {
                Text(
                    text = stringResource(id = R.string.place_order), color = Color.Black, fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
