package com.beautyplus.ui.booking

import android.annotation.SuppressLint
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.beautyplus.R
import com.beautyplus.routing.Screen
import com.beautyplus.ui.theme.BeautyPlusTheme
import com.beautyplus.ui.theme.appColor
import com.beautyplus.ui.theme.white
import com.beautyplus.utils.OutlineFormField
import com.beautyplus.utils.RoundedButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookingScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
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
                            text = "Detail Screen", color = Color.White,
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
                            Icon(imageVector = Icons.Rounded.ArrowBack,
                                tint = Color.White,
                                contentDescription = "Back")
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = appColor,
                        titleContentColor = Color.White
                    )
                )

                Column(modifier = Modifier.background(color = white)) {
                    Card(
                        modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp),
                        shape = RoundedCornerShape(25.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(5.dp),
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(20.dp)
                        ) {

                            Spacer(modifier = Modifier.height(10.dp))

                            OutlineFormField(
                                value = name,
                                onValueChange = { text ->
                                    name = text
                                },
                                placeholder = "Enter name",
                                keyboardType = KeyboardType.Text,
                            )

                            Spacer(modifier = Modifier.height(5.dp))
                            OutlineFormField(
                                value = email,
                                onValueChange = { text ->
                                    email = text
                                },
                                placeholder = "Enter email",
                                keyboardType = KeyboardType.Email,
                            )

                            Spacer(modifier = Modifier.height(5.dp))
                            OutlineFormField(
                                value = mobileNumber,
                                onValueChange = { text ->
                                    if (text.length <= 10)
                                        mobileNumber = text
                                },
                                placeholder = "Enter Mobile Number",
                                keyboardType = KeyboardType.Phone,
                            )

                            Spacer(Modifier.height(10.dp))

                            OutlineFormField(
                                value = address,
                                onValueChange = { text ->
                                    address = text
                                },
                                placeholder = "Enter address",
                                keyboardType = KeyboardType.Text,
                            )

                            Spacer(modifier = Modifier.height(5.dp))
                            RoundedButton(
                                text = "Submit",
                                onClick = {
                                    if (name.isNotEmpty()) {
                                        if (email.isNotEmpty()) {
                                            if (mobileNumber.isNotEmpty()) {
                                                if (address.isNotEmpty()) {
                                                    Toast.makeText(
                                                        context,
                                                        "Booking successfully.",
                                                        Toast.LENGTH_LONG
                                                    ).show()
                                                    navController.navigateUp()
                                                } else {
                                                    Toast.makeText(
                                                        context,
                                                        "Please enter address.",
                                                        Toast.LENGTH_LONG
                                                    ).show()
                                                }
                                            } else {
                                                Toast.makeText(
                                                    context,
                                                    "Please enter mobile number.",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Please enter email.",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Please enter name.",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                    }
                    Spacer(modifier = Modifier.height(10.dp))

                }
            }
        }


    }
}