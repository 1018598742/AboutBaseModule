package com.fta.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.View
import android.widget.Button
import com.fta.base.finger.FingerAc
import com.fta.base.finger.FingerBioAc
import com.fta.base.sdcard.SdCardAc
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.nestedScrollView
import org.jetbrains.anko.startActivity as jumpAc

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(MainAcUi().createView(AnkoContext.create(this,this)))
//        initView()
    }

    private fun initView() {
        val sdCardBt = Button(this)
        sdCardBt.setText("SD卡信息")
        sdCardBt.setOnClickListener {
            jumpAc<SdCardAc>()
        }
        rootMain.addView(sdCardBt, -1, -1)
    }

    class MainAcUi : AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>): View = ui.apply {
            nestedScrollView {
                verticalLayout {
                    button{
                        text = "SD卡信息"
                    }.onClick {
                        jumpAc<SdCardAc>()
                    }

                    val showDeviceInfo = textView{
                        gravity = Gravity.CENTER
                    }

                    button{
                        text = "设备信息"
                    }.onClick {
                        showDeviceInfo.text = Settings.Secure.getString(
                            it.context.contentResolver,
                            Settings.Secure.ANDROID_ID
                        )
                    }

                    button{
                        text= "指纹"
                    }.onClick {
                        jumpAc<FingerAc>()
                    }


                    button{
                        text= "指纹(sdk>=28)"
                    }.onClick {
                        jumpAc<FingerBioAc>()
                    }
                }

            }

        }.view
    }
}
