package com.example.cinticket.accounts.entities

data class AccountUpdateCardInfoTuple(
    val accountId: Long,
    val cardNumber: String,
    val dateOfCard: String,
    val CVV_code: Long
)