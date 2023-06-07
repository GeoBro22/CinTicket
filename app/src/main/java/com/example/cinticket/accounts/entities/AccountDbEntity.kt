package com.example.cinticket.accounts.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cinticket.entities.Account

@Entity(
    tableName = "accounts",
)
data class AccountDbEntity(
    @PrimaryKey(autoGenerate = true) val accountId: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val cardNumber: String,
    val dateOfCard: String,
    val CVV_code: Long
) {

    fun toAccount(): Account = Account(
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