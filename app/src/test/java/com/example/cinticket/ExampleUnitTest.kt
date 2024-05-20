package com.example.cinticket

import com.example.cinticket.Service.Companion.isValidDate
import com.example.cinticket.Service.Companion.isValidEmail
import org.junit.Assert
import org.junit.Test

class EmailValidatorTest {
    @Test
    fun isValidEmail_ValidEmail_ReturnsTrue() {
        val email = "test@gmail.com"

        val isValid = isValidEmail(email)

        Assert.assertTrue(isValid)
    }

    @Test
    fun isValidEmail_InvalidEmail_ReturnsFalse() {

        val email = "invalidemail.com"


        val isValid = isValidEmail(email)

        Assert.assertFalse(isValid)
    }
}

class DateValidatorTest {

    @Test
    fun isValidDate_ValidDate_ReturnsTrue() {

        val date = "12/31"

        val isValid = isValidDate(date)

        Assert.assertTrue(isValid)
    }

    @Test
    fun isValidDate_InvalidDate_ReturnsFalse() {

        val date = "1231"

        val isValid = isValidDate(date)

        Assert.assertFalse(isValid)
    }
}