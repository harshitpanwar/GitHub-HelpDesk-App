package com.example.githubhelpdesk.network

data class RecyclerList(val items: List<RecyclerData>)
data class RecyclerData(val name: String?, val descripiton: String?, val owner: Owner?)
data class Owner(val avatar_url: String?)
