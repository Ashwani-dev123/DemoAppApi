package com.ashwani.demoapp.Model

data class ResponseModel(
    val `data`: ArrayList<Data>,
    val pagination: Pagination
)

data class Data(
    val __v: Int,
    val _id: String,
    val airline: List<Airline>,
    val name: String,
    val trips: Int
)

data class Airline(
    val country: String,
    val established: String,
    val head_quaters: String,
    val id: Int,
    val logo: String,
    val name: String,
    val slogan: String,
    val website: String
)

data class Pagination(
    val currentItems: Int,
    val currentPage: Int,
    val links: List<Link>,
    val totalItems: Int,
    val totalPages: Int
)

data class Link(
    val href: String,
    val page: Int,
    val rel: String
)