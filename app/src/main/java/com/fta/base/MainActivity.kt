package com.fta.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.fta.base.sdcard.SdCardAc
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity as jumpAc

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        val sdCardBt = Button(this)
        sdCardBt.setText("SD卡信息")
        sdCardBt.setOnClickListener {
            jumpAc<SdCardAc>()
        }
        rootMain.addView(sdCardBt, -1, -1)
    }
}
