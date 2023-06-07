package com.example.cinticket.accounts.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.cinticket.accounts.entities.AccountDbEntity
import com.example.cinticket.accounts.entities.AccountUpdateCardInfoTuple

@Dao
interface AccountsDao {
    @Query("SELECT * FROM accounts WHERE email = :accountEmail")
    suspend fun getAccountByEmail(accountEmail: String) : AccountDbEntity?

    @Insert(entity = AccountDbEntity::class)
    suspend fun insertNewAccount(account: AccountDbEntity)

    @Update(entity = AccountDbEntity::class)
    suspend fun updateAccountCardInfo(updatedAccountCardInfo: AccountUpdateCardInfoTuple)
}