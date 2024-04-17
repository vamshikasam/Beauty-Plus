package com.beautyplus.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.beautyplus.R
import com.beautyplus.routing.Screen
import com.beautyplus.ui.model.SaloonModel
import com.beautyplus.ui.theme.BeautyPlusTheme
import com.beautyplus.ui.theme.appColor
import com.beautyplus.ui.theme.white
import com.beautyplus.utils.RoundedButton

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val list = ArrayList<SaloonModel>().apply {
        add(
            SaloonModel(
                id = "",
                image = "",
                name = "Test Saloon",
                address = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
            )
        )
        add(
            SaloonModel(
                id = "",
                image = "",
                name = "Test Saloon",
                address = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
            )
        )
        add(
            SaloonModel(
                id = "",
                image = "",
                name = "Test Saloon",
                address = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
            )
        )
        add(
            SaloonModel(
                id = "",
                image = "",
                name = "Test Saloon",
                address = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
            )
        )
        add(
            SaloonModel(
                id = "",
                image = "",
                name = "Test Saloon",
                address = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
            )
        )
        add(
            SaloonModel(
                id = "",
                image = "",
                name = "Test Saloon",
                address = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
            )
        )
        add(
            SaloonModel(
                id = "",
                image = "",
                name = "Test Saloon",
                address = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
            )
        )
        add(
            SaloonModel(
                id = "",
                image = "",
                name = "Test Saloon",
                address = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
            )
        )
        add(
            SaloonModel(
                id = "",
                image = "",
                name = "Test Saloon",
                address = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
            )
        )
        add(
            SaloonModel(
                id = "",
                image = "",
                name = "Test Saloon",
                address = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
            )
        )
        add(
            SaloonModel(
                id = "",
                image = "",
                name = "Test Saloon",
                address = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
            )
        )
        add(
            SaloonModel(
                id = "",
                image = "",
                name = "Test Saloon",
                address = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
            )
        )
        add(
            SaloonModel(
                id = "",
                image = "",
                name = "Test Saloon",
                address = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
            )
        )
        add(
            SaloonModel(
                id = "",
                image = "",
                name = "Test Saloon",
                address = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
            )
        )
        add(
            SaloonModel(
                id = "",
                image = "",
                name = "Test Saloon",
                address = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
            )
        )
        add(
            SaloonModel(
                id = "",
                image = "",
                name = "Test Saloon",
                address = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
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
                            text = "Home Screen", color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                        )
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = appColor,
                        titleContentColor = Color.White
                    )
                )
                Spacer(Modifier.height(10.dp))
                Column {
                    list.forEachIndexed { index, saloonModel ->
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
                                        navController.navigate(Screen.DetailScreen.route)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }


    }

}