package com.example.kotlinpractice2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.client.Socket.EVENT_CONNECT_ERROR
import io.socket.engineio.client.EngineIOException
import java.io.FileDescriptor.err

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val socket = IO.socket("서버 url집어 넣기")
        socket.connect()

        socket.on(io.socket.client.Socket.EVENT_CONNECT){
            //소켓이 서버에 연결이 되면 호출이 됨!
            Log.i("Socket","Connect")
        }.on(io.socket.client.Socket.EVENT_DISCONNECT){
            //소켓 서버 연결이 끊어질 떄 호출
        }.on(EVENT_CONNECT_ERROR){args->
            var errorMessage = ""
            if(args[0] is EngineIOException){
                errorMessage = "code : ${err.hashCode()}"
            }
            val data = "안녕하세요!"
            socket?.emit("message",data)
        }
    }
}