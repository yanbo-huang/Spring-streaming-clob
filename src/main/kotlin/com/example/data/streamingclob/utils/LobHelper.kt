package com.example.data.streamingclob.utils

import org.hibernate.engine.jdbc.ClobProxy
import java.io.Reader

object LobHelper {
    fun getClob(reader: Reader, length: Long) = ClobProxy.generateProxy(reader, length)
}