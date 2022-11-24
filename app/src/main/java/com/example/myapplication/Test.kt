package com.example.myapplication


import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintStream
import java.net.Socket
import java.net.URL
import java.security.KeyStore
import java.security.cert.CertificateFactory
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory


@Volatile
var a = 1

var _b: Int? = null
val b: Int
    get() = _b!!

fun main() {

    val socket = Socket("wikidocs.net", 443)
    val inputStreamReader = BufferedReader(InputStreamReader(socket.getInputStream()))
    val outStream = PrintStream(socket.getOutputStream())

    with(outStream) {
        println("GET /images/page/49159/png-2702691_1920_back.png / HTTPs/1.1")
        println("Host: wikidocs.net")
        println()
    }

    var line: String? = "start"
    while (line != null) {
        line = inputStreamReader.readLine()
        println(line)
    }

    inputStreamReader.close()
    outStream.close()
    socket.close()

}

