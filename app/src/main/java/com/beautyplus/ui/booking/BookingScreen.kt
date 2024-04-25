package com.beautyplus.ui.booking

import android.annotation.SuppressLint
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import com.beautyplus.R
import com.beautyplus.application.AppData
import com.beautyplus.routing.Screen
import com.beautyplus.ui.model.BookingModel
import com.beautyplus.ui.theme.BeautyPlusTheme
import com.beautyplus.ui.theme.appColor
import com.beautyplus.ui.theme.white
import com.beautyplus.utils.BeautyPlusField
import com.beautyplus.utils.RoundedButton
import com.beautyplus.utils.isValidEmail
import com.beautyplus.utils.isValidText
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
    var slotDropDown by remember { mutableStateOf("") }
    var slots by rememberSaveable { mutableStateOf(false) }
    var isSubmit by rememberSaveable { mutableStateOf(false) }
    val slotsList =
        listOf(
            "10 AM to 11 AM",
            "11 AM to 12 PM",
            "12 PM to 1 PM",
            "1 PM to 2 PM",
            "2 PM to 3 PM",
            "3 PM to 4 PM",
            "4 PM to 5 PM",
            "5 PM to 6 PM",
            "6 PM to 7 PM"
        )

    val icon = if (slots)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
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
                            text = "Book Screen", color = Color.White,
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

                            BeautyPlusField(
                                value = name,
                                onValueChange = { text ->
                                    name = text
                                },
                                placeholder = "Enter name",
                                keyboardType = KeyboardType.Text,
                            )

                            Spacer(modifier = Modifier.height(5.dp))
                            BeautyPlusField(
                                value = email,
                                onValueChange = { text ->
                                    email = text
                                },
                                placeholder = "Enter email",
                                keyboardType = KeyboardType.Email,
                            )

                            Spacer(modifier = Modifier.height(5.dp))
                            BeautyPlusField(
                                value = mobileNumber,
                                onValueChange = { text ->
                                    if (text.length <= 10)
                                        mobileNumber = text
                                },
                                placeholder = "Enter Mobile Number",
                                keyboardType = KeyboardType.Phone,
                            )

                            Spacer(Modifier.height(10.dp))

                            BeautyPlusField(
                                value = address,
                                onValueChange = { text ->
                                    address = text
                                },
                                placeholder = "Enter address",
                                keyboardType = KeyboardType.Text,
                            )

                            Spacer(modifier = Modifier.height(5.dp))
                            OutlinedTextField(
                                value = if (slotDropDown != "") slotDropDown else "Select slot",
                                onValueChange = { slotDropDown = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                                    .clickable { slots = !slots },
                                enabled = false,
                                trailingIcon = {
                                    Icon(
                                        icon, "contentDescription",
                                        tint = Color.Black
                                    )
                                },
                                textStyle = TextStyle(color = Color.Black),
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = appColor,
                                    unfocusedBorderColor = appColor,
                                    disabledBorderColor = appColor
                                ),
                                shape = RoundedCornerShape(10.dp),
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                if (slots) {
                                    Popup(
                                        alignment = Alignment.TopCenter,
                                        properties = PopupProperties(
                                            excludeFromSystemGesture = true,
                                        ),
                                        onDismissRequest = { slots = false }
                                    ) {

                                        Column(
                                            modifier = Modifier
                                                .heightIn(max = 220.dp)
                                                .verticalScroll(state = scrollState)
                                                .padding(10.dp)
                                                .border(width = 1.dp, color = Color.Gray),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                        ) {

                                            slotsList.onEachIndexed { index, item ->
                                                if (index != 0) {
                                                    Divider(
                                                        thickness = 1.dp,
                                                        color = Color.LightGray
                                                    )
                                                }
                                                Box(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .background(white)
                                                        .padding(10.dp)
                                                        .clickable {
                                                            slotDropDown = item
                                                            slots = !slots
                                                        },
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    Text(
                                                        text = item,
                                                        style = TextStyle(color = Color.Black)
                                                    )
                                                }
                                            }

                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            RoundedButton(
                                text = "Submit",
                                onClick = {
                                    if (name.isNotEmpty()) {
                                        if (!isValidText(name.trim())) {
                                            if (email.isNotEmpty()) {
                                                if (!isValidEmail(email.toString().trim())) {
                                                    if (mobileNumber.isNotEmpty()) {
                                                        if (mobileNumber.length < 10) {
                                                            if (address.isNotEmpty()) {
                                                                if (slotDropDown.isNotEmpty()) {
                                                                    isSubmit = true
                                                                } else {
                                                                    Toast.makeText(
                                                                        context,
                                                                        "Please select slot.",
                                                                        Toast.LENGTH_LONG
                                                                    ).show()
                                                                }
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
                                                                "Please enter valid mobile number.",
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
                                                        "Please enter valid email.",
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
                                                "Please enter valid name.",
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
        if (isSubmit) {
            AlertDialog(
                onDismissRequest = {
                    isSubmit = false
                },
                title = { Text(stringResource(id = R.string.app_name)) },
                text = { Text("You have successfully booked the service.") },
                confirmButton = {
                    RoundedButton(
                        text = "Ok",
                        textColor = white,
                        onClick = {
                            AppData.list.apply {
                                add(
                                    BookingModel(
                                        id = "",
                                        name = name,
                                        email = email,
                                        slot = slotDropDown,
                                        address = address
                                    )
                                )
                            }
                            navController.navigateUp()
                            isSubmit = false
                        }
                    )
                },
                dismissButton = {}
            )
        }


    }
}