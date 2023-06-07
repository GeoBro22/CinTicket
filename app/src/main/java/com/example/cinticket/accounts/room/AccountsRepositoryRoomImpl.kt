package com.example.cinticket.accounts.room

import android.util.Log
import com.example.cinticket.accounts.AccountsRepository
import com.example.cinticket.accounts.entities.AccountDbEntity
import com.example.cinticket.accounts.entities.AccountUpdateCardInfoTuple
import com.example.cinticket.entities.Account

class AccountsRepositoryRoomImpl(private val accountsDao: AccountsDao):AccountsRepository {
    override suspend fun getAccountByEmail(accountEmail: String): Account? {
        return accountsDao.getAccountByEmail(accountEmail)?.toAccount()
    }

    override suspend fun insertNewAccount(account: AccountDbEntity) {
        accountsDao.insertNewAccount(account)
    }

    override suspend fun updateAccountCardInfo(updatedAccountCardInfo: AccountUpdateCardInfoTuple) {
        accountsDao.updateAccountCardInfo(updatedAccountCardInfo)
    }

}