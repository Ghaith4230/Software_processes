package com.example.myapplication.MainMenu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.myapplication.sendChatRequest

class MainMenuViewModel {
    var text by mutableStateOf("")

    var response by mutableStateOf("")


    fun updateText(newText: String) {
       text = newText
    }

    fun gettext():String{return text}


    fun updateResponse(newText: String) {
        response = newText
    }

    fun getAnswer():String{
        return response
    }


}