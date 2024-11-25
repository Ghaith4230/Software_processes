package com.example.myapplication

import kotlinx.serialization.Serializable

@Serializable
data class ChatGPTRequest(
    val model: String,
    val messages: List<Message>
)

@Serializable
data class Message(
    val role: String,
    val content: String
)

@Serializable
data class ChatGPTResponse(
    val choices: List<Choice>
)

@Serializable
data class Choice(
    val message: Message
)