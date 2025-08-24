package org.example

/**
 * Represents a library member.
 * @property memberId The unique ID of the member.
 * @property name The name of the member.
 * @property email The email address of the member.
 */
class Member(private val memberId: String, private val name: String, private val email: String) {
    fun getMemberId(): String = memberId
    fun getName(): String = name
    fun getEmail(): String = email
}
