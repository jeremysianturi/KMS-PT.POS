package com.pos.lms.kms_pt_pos_indonesia.utils

import java.util.*

object ErrorMessageSplit {

    fun message(msg: String): String {
        val result: String

        val tokens = StringTokenizer(msg, ":")
        val first: String = tokens.nextToken() // this will contain "Fruit"
        val second: String = tokens.nextToken()
        val third: String = tokens.nextToken()
        result = second

        return result
    }

    fun code(msg: String): String {
        val result: String

        val tokens = StringTokenizer(msg, ":")
        val first: String = tokens.nextToken() // this will contain "Fruit"
        val second: String = tokens.nextToken()
        val third: String = tokens.nextToken()
        result = third

        return result
    }
}