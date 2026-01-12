package com.example.winerrorhandling.core.domain

sealed interface DataError: Error {
    enum class Remote: DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        SERVER,
        SERIALIZATION,
        UNKNOWN,


        SUCCESS,

        PARTNER_ALREADY_EXISTS,
        ALREADY_IN_WISHLIST,
    }


    enum class Local: DataError {
        DISK_FULL,
        UNKNOWN
    }
}