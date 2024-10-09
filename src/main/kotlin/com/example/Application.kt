package com.example

import com.example.plugins.*
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import io.ktor.server.application.*
import java.io.FileInputStream

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureMonitoring()
    configureRouting()


    val serviceAccountFilePath = System.getenv("FIREBASE_CREDENTIALS")
        ?: throw IllegalStateException("FIREBASE_CREDENTIALS_PATH environment variable not set.")

    val serviceAccountStream = FileInputStream(serviceAccountFilePath)
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
        .build()

    FirebaseApp.initializeApp(options)
}
