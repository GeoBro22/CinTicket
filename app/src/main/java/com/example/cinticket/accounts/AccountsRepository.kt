package com.example.cinticket.accounts

import com.example.cinticket.accounts.entities.AccountDbEntity
import com.example.cinticket.accounts.entities.AccountUpdateCardInfoTuple
import com.example.cinticket.entities.Account
import com.example.cinticket.entities.Movie

interface AccountsRepository {
    suspend fun getAccountByEmail(accountEmail: String): Account?
    suspend fun insertNewAccount(account: AccountDbEntity)
    suspend fun updateAccountCardInfo(updatedAccountCardInfo: AccountUpdateCardInfoTuple)


}