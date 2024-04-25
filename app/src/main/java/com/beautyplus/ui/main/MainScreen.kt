package com.beautyplus.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.beautyplus.R
import com.beautyplus.routing.Screen
import com.beautyplus.ui.beautyPlusPreference.BeautyPlusPreference
import com.beautyplus.ui.model.SaloonModel
import com.beautyplus.ui.theme.BeautyPlusTheme
import com.beautyplus.ui.theme.appColor
import com.beautyplus.ui.theme.white
import com.beautyplus.utils.RoundedButton
import com.beautyplus.ui.drawer.DrawerBody
import com.beautyplus.ui.drawer.DrawerHeader
import com.beautyplus.ui.drawer.TopBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val preference = remember {
        BeautyPlusPreference(context)
    }
    val scrollState = rememberScrollState()
    val dataList = MutableLiveData<ArrayList<SaloonModel>>(ArrayList())
    val scope = rememberCoroutineScope()
    var isLogout by remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()
    dataList.value!!.apply {
        add(
            SaloonModel(
                id = "",
                image = "",
                name = "Sally Beauty (Middlesbrough, England)",
                address = "Industrial Estate, Unit 10 Newport Way, Cannon Park Way, Middlesbrough TS1 5JW, UK"
            )
        )
        add(
            SaloonModel(
                id = "",
                image = "",
                name = "Aspire Hairdressing & Beauty",
                address = "159 Linthorpe Rd, Middlesbrough TS1 4AG, United Kingdom"
            )
        )
        add(
            SaloonModel(
                id = "",
                image = "",
                name = "Salon Services",
                address = "1 Douglas Cl, Preston Farm Industrial Estate, Stockton-on-Tees TS18 3SB, UK"
            )
        )
        add(
            SaloonModel(
                id = "",
                image = "",
                name = "Trade Hair Supplies (Ray & Co)",
                address = "3 Newport Way, Middlesbrough TS1 5JW, UK"
            )
        )
        add(
            SaloonModel(
                id = "",
                image = "",
                name = "A&S Beauty",
                address = "67 Newport Rd., Middlesbrough TS1 1LA, United Kingdom"
            )
        )
        add(
            SaloonModel(
                id = "",
                image = "",
                name = "Manha's Hair And Beauty Salon",
                address = "25 Princes Rd, Middlesbrough TS1 4BE, United Kingdom"
            )
        )
        add(
            SaloonModel(
                id = "",
                image = "",
                name = "MARIAM HAIR AND BEAUTY LIMITED",
                address = "United Kingdom"
            )
        )
        add(
            SaloonModel(
                id = "",
                image = "",
                name = "Cosmetology Middlesbrough Ltd",
                address = "5 Acklam Rd, Middlesbrough TS5 5HE, United Kingdom"
            )
        )

    }
    BeautyPlusTheme {
        androidx.compose.material.Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopBar(
                    navController = navController,
                    onNavigationIconClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                )
            },
            modifier = Modifier.background(color = appColor),
            drawerContent = {
                DrawerHeader()
                DrawerBody(onHistory = {
                    navController.navigate(Screen.BookingHistory.route)
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                },onLogout = {
                    isLogout = true
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                })
            },
            backgroundColor = appColor,
            contentColor = appColor,
            drawerBackgroundColor = appColor
        ) { paddingValues ->
            Modifier.padding(
                bottom = paddingValues.calculateBottomPadding()
            )
            Column(
                modifier = Modifier
                    .background(color = appColor)
                    .padding(top = 40.dp)
                    .verticalScroll(scrollState)
            ) {

                Column {
                    dataList.value!!.forEachIndexed { index, saloonModel ->
                        Card(
                            modifier = Modifier
                                .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                                .fillMaxWidth()
                                .height(350.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
                        ) {
                            Spacer(Modifier.height(10.dp))
                            Image(
                                painter = painterResource(id = R.drawable.ic_saloon),
                                contentDescription = "Image",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                            )
                            Text(
                                saloonModel.name ?: "",
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(vertical = 5.dp, horizontal = 10.dp)
                            )
                            Text(
                                "Address : ${saloonModel.address ?: ""}",
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(vertical = 5.dp, horizontal = 10.dp)
                            )
                            Box(Modifier.padding(15.dp)) {
                                RoundedButton(
                                    text = "Show Detail",
                                    onClick = {
                                        navController.navigate(Screen.DetailScreen.route+ "/${saloonModel.name}"+"/${saloonModel.address}")
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
        if (isLogout) {
            AlertDialog(
                onDismissRequest = {
                    isLogout = false
                },
                title = { Text(stringResource(id = R.string.app_name)) },
                text = { Text("Are you sure you want to logout ?") },
                confirmButton = {
                    RoundedButton(
                        text = "Cancel",
                        textColor = white,
                        onClick = { isLogout = false }
                    )
                },
                dismissButton = {

                    RoundedButton(
                        text = "Logout",
                        textColor = white,
                        onClick = {
                            preference.saveData("isLogin", false)
                            navController.navigate(
                                Screen.LoginScreen.route
                            ) {
                                popUpTo(Screen.MainScreen.route) {
                                    inclusive = true
                                }
                            }
                            isLogout = false
                        }
                    )

                }
            )
        }


    }

}