package com.example.cinticket.entities

import com.example.cinticket.accounts.entities.AccountDbEntity

data class Account(
    val accountId: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    var cardNumber: String,
    var dateOfCard:String,
    var CVV_code:Long
    )
{
    fun toAccountDbEntity(): AccountDbEntity = AccountDbEntity(
        accountId = accountId,
        firstName = firstName,
        lastName = lastName,
        email = email,
        password = password,
        cardNumber = cardNumber,
        dateOfCard=dateOfCard,
        CVV_code = CVV_code
    )
}
