package com.example.foodreciepesapp.model

data class User(
    var id: Int=0,
    var username: String?=null,
    var email: String="",
    var address: Address?=null,
    var phone: String?="",
    var website: String?=null,
    var company: Company?=null
)


data class Address(
    var street: String?,
    var suite: String?,
    var city: String,
    var zipcode: String,
    var geo: Geo
)

data class Geo(
    var lat: Double,
    var longi: Double
)

data class Company(
    var name: String,
    var catchPhrase: String?,
    var bs: String?
)