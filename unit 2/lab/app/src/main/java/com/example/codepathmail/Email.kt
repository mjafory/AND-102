package com.example.codepathmail

data class Email(
    val sender: String,
    val date: String,
    val title: String,
    val summary: String,
    val isRead: Boolean
) {
}