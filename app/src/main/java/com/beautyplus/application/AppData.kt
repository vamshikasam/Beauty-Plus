package com.beautyplus.application

import android.app.Application
import com.beautyplus.ui.model.BookingModel

class AppData : Application() {
    companion object{
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
    }
}