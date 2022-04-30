package com.dicoding.retroaudio

import android.Manifest
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE), 111)
        }

        val audioRecord = OnlyAudioRecorder.instance

        val filePcm = getFilePath2("RawAudio.pcm")
        val fileWav = getFilePath2("FinalAudio.wav")
        val stringPcm = getFilePath("RawAudio.pcm")
        val stringWav = getFilePath("FinalAudio.wav")

        audioRecord.setPath(stringWav, stringPcm, fileWav, filePcm)

        findViewById<Button>(R.id.btn_rec).setOnClickListener {
            audioRecord.startRecord()
            findViewById<TextView>(R.id.tv_rekam).text = "Mulai Rekam"
        }
        findViewById<Button>(R.id.btn_stop).setOnClickListener {
            audioRecord.stopRecord()
            findViewById<TextView>(R.id.tv_rekam).text = "Rekaman Berhenti"
        }
    }
    private fun getFilePath(filename:String):String{
        val contextWrapper = ContextWrapper(this)
        val musicDir: File? = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC)
        val file = File(musicDir,filename)
        return file.path
    }
    private fun getFilePath2(filename:String): File {
        val contextWrapper = ContextWrapper(this)
        val musicDir: File? = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC)
        return File(musicDir,filename)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode ==111 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

        }
    }
}