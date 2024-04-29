package com.beautyplus.ui.sign_up

import android.annotation.SuppressLint
import android.app.Dialog
import android.util.Log
import android.util.Patterns
import android.webkit.WebView
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.beautyplus.MainActivity
import com.beautyplus.R
import com.beautyplus.routing.Screen
import com.beautyplus.ui.beautyPlusPreference.BeautyPlusPreference
import com.beautyplus.ui.theme.BeautyPlusTheme
import com.beautyplus.ui.theme.appColor
import com.beautyplus.ui.theme.white
import com.beautyplus.utils.BeautyPlusField
import com.beautyplus.utils.RoundedButton
import com.beautyplus.utils.isValidEmail
import com.beautyplus.utils.isValidText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpScreen(navController: NavController) {
    val context = LocalContext.current
    val preference = remember {
        BeautyPlusPreference(context)
    }
    var beautySignup by remember { mutableStateOf(false) }
    var backEnabled by remember { mutableStateOf(true) }
    var dialog: Dialog? = null
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val db = Firebase.firestore
    val firebaseAuth = FirebaseAuth.getInstance()
    BeautyPlusTheme {
        Scaffold {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.appColor))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_saloon),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6F)
                        .clip(
                            shape = RoundedCornerShape(
                                bottomStart = 30.dp, bottomEnd = 30.dp
                            )
                        )
                )

            }
        }

        Dialog(
            onDismissRequest = {

            },
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                dismissOnBackPress = true, dismissOnClickOutside = false
            ),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Box(Modifier.padding(bottom = 60.dp)) {
                    Card(
                        modifier = Modifier.padding(top = 30.dp, start = 30.dp, end = 30.dp),
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

                            Text(
                                "Registration",
                                style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold),
                                textAlign = TextAlign.Center,
                            )
                            Spacer(modifier = Modifier.height(10.dp))

                            BeautyPlusField(
                                value = name,
                                onValueChange = { text ->
                                    name = text
                                },
                                placeholder = "Enter Name",
                                keyboardType = KeyboardType.Text,
                            )

                            Spacer(Modifier.height(10.dp))
                            BeautyPlusField(
                                value = email,
                                onValueChange = { text ->
                                    email = text
                                },
                                placeholder = "Enter email",
                                keyboardType = KeyboardType.Email,
                            )

                            Spacer(Modifier.height(10.dp))
                            BeautyPlusField(
                                value = password,
                                onValueChange = { text ->
                                    password = text
                                },
                                placeholder = "Enter Password",
                                keyboardType = KeyboardType.Password,
                                visualTransformation = PasswordVisualTransformation(),
                            )

                            Spacer(modifier = Modifier.height(5.dp))

                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    Row(
                        modifier = Modifier
                            .padding(start = 50.dp, end = 50.dp)
                            .align(BottomCenter)
                            .offset(0.dp, 32.dp)
                    ) {
                        RoundedButton(
                            text = "Register",
                            onClick = {
                                if (name.isEmpty()) {
                                    Toast.makeText(
                                        context,
                                        "Please enter name.",
                                        Toast.LENGTH_LONG
                                    ).show()

                                } else if (!isValidText(name.trim())) {
                                    Toast.makeText(
                                        context,
                                        "Please enter valid name.",
                                        Toast.LENGTH_LONG
                                    ).show()

                                }else if (email.isEmpty()) {
                                    Toast.makeText(
                                        context,
                                        "Please enter mobile.",
                                        Toast.LENGTH_LONG
                                    ).show()

                                }else  if (isValidEmail(email.trim())) {
                                    Toast.makeText(
                                        context,
                                        "Please enter valid email.",
                                        Toast.LENGTH_LONG
                                    ).show()

                                } else if (password.isEmpty()) {
                                    Toast.makeText(
                                        context,
                                        "Please enter password.",
                                        Toast.LENGTH_LONG
                                    ).show()

                                } else {
                                    beautySignup = true
                                    firebaseAuth.createUserWithEmailAndPassword(
                                        email.lowercase(),
                                        password
                                    )
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                preference.saveData(
                                                    "isLogin", true
                                                )
                                                Toast.makeText(
                                                    context,
                                                    "Register successfully.",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                navController.navigate(
                                                    Screen.MainScreen.route
                                                ) {
                                                    popUpTo(Screen.SignUpScreen.route) {
                                                        inclusive = true
                                                    }
                                                }
                                                beautySignup = false
                                            } else {
                                                Toast.makeText(
                                                    context,
                                                    task.exception?.message.toString(),
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                beautySignup = false
                                            }
                                        }
                                }

                            }
                        )
                    }

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Already have an account?",
                        textAlign = TextAlign.End,
                        style = TextStyle(color = colorResource(id = R.color.white))
                    )

                    Text(
                        " Login", modifier = Modifier
                            .clickable {
                                navController.navigate(Screen.LoginScreen.route)
                            }, textAlign = TextAlign.End,
                        style = TextStyle(color = colorResource(id = R.color.white))
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))
                if (beautySignup) {
                    Dialog(
                        onDismissRequest = { },
                        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(100.dp)
                                .background(white, shape = RoundedCornerShape(8.dp))
                        ) {
                            CircularProgressIndicator(color = appColor)
                        }
                    }
                }

            }
        }
        BackHandler {
            navController.navigateUp()
        }
    }
}