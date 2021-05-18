package com.pos.lms.kms_pt_pos_indonesia.utils

import java.io.IOException

class ApiException(message: String) : IOException(message)
class ApiExceptionCode(code: String) : IOException(code)
class NoInternetException(message: String) : IOException(message)