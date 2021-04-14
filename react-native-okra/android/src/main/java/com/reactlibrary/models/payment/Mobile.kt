package com.reactlibrary.models.payment

data class Mobile(
    val exists: Boolean?,
    val recurring: Boolean?,
    val save_bene: Boolean?,
    val sso: Boolean?
)