package com.example.m_7_junit_testing

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest {

    @Test
    fun `empty username returns false`(){

        val result = RegistrationUtil.validRegistrationInput(
            "",
            "123",
            "123"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `username and correctly repeated password returns true`() {

        val result = RegistrationUtil.validRegistrationInput(
            "Kapil",
            "123",
            "123"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `username already taken returns false`() {

        val result = RegistrationUtil.validRegistrationInput(
            "Rohan",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `incorrect confirm password returns false`() {

        val result = RegistrationUtil.validRegistrationInput(
            "Kapil",
            "123",
            "1234"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `less than two digit password return false`() {

        val result = RegistrationUtil.validRegistrationInput(
            "Kapil",
            "abcd1",
            "abcd1"
        )
        assertThat(result).isFalse()
    }
}
