package com.beautyplus.ui.booking_history

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.beautyplus.ui.model.BookingModel
import com.beautyplus.ui.theme.BeautyPlusTheme
import com.beautyplus.ui.theme.appColor

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookingHistoryScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val list = arrayListOf<BookingModel>().apply {
        add(
            BookingModel(
                id = "",
                name = "Sally Beauty (Middlesbrough, England)",
                email = "demo@gmail.com",
                slot = "10 AM to 11 AM",
                address = "Industrial Estate, Unit 10 Newport Way, Cannon Park Way, Middlesbrough TS1 5JW, UK"
            )
        )
        add(
            BookingModel(
                id = "",
                email = "test@gmail.com",
                slot = "11 AM to 12 AM",
                name = "Aspire Hairdressing & Beauty",
                address = "159 Linthorpe Rd, Middlesbrough TS1 4AG, United Kingdom"
            )
        )
        add(
            BookingModel(
                id = "",
                email = "lorem@gmail.com",
                slot = "12 AM to 1 PM",
                name = "Salon Services",
                address = "1 Douglas Cl, Preston Farm Industrial Estate, Stockton-on-Tees TS18 3SB, UK"
            )
        )
        add(
            BookingModel(
                id = "",
                email = "demo1@gmail.com",
                slot = "1 PM to 2 PM",
                name = "Trade Hair Supplies (Ray & Co)",
                address = "3 Newport Way, Middlesbrough TS1 5JW, UK"
            )
        )
        add(
            BookingModel(
                id = "",
                email = "demo2@gmail.com",
                slot = "2 PM to 3 PM",
                name = "A&S Beauty",
                address = "67 Newport Rd., Middlesbrough TS1 1LA, United Kingdom"
            )
        )
        add(
            BookingModel(
                id = "",
                email = "test1@gmail.com",
                slot = "3 PM to 4 PM",
                name = "Manha's Hair And Beauty Salon",
                address = "25 Princes Rd, Middlesbrough TS1 4BE, United Kingdom"
            )
        )
        add(
            BookingModel(
                id = "",
                email = "test2@gmail.com",
                slot = "4 PM to 5 PM",
                name = "MARIAM HAIR AND BEAUTY LIMITED",
                address = "United Kingdom"
            )
        )
    }
    BeautyPlusTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .background(color = appColor)
                    .padding(top = 40.dp)
                    .verticalScroll(scrollState)
            ) {
                SmallTopAppBar(
                    title = {
                        Text(
                            text = "Booking History", color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.navigateUp()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                tint = Color.White,
                                contentDescription = "Back"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = appColor,
                        titleContentColor = Color.White
                    )
                )
                Spacer(Modifier.height(10.dp))
                list.forEachIndexed { index, bookModel ->
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .height(300.dp)
                            .clickable {
                            },
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
                    ) {

                        Text(
                            "Salon Name : ${bookModel.name}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(color = Color.Black)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            "Email : ${bookModel.email}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(color = Color.Black)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            "Slot : ${bookModel.slot}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(color = Color.Black)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            "Address : ${bookModel.address}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(color = Color.Black)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }

            }
        }


    }

}