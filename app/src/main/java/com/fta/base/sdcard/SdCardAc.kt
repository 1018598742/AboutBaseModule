package com.fta.base.sdcard

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.storage.StorageManager
import android.os.storage.StorageVolume
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.common.basemodule.util.LogUtil
import com.fta.base.R
import kotlinx.android.synthetic.main.activity_baseroot.*
import java.lang.StringBuilder

class SdCardAc : AppCompatActivity() {

    private var showSdcardInfo: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baseroot)
        initView()
    }

    private fun initView() {
        val sdCardInfoBt = Button(this)
        sdCardInfoBt.setText("获取SD卡信息")
        sdCardInfoBt.setOnClickListener {
            updateSdInfo()
        }
        rootBase.addView(sdCardInfoBt, -1, -1)

        showSdcardInfo = TextView(this)
        rootBase.addView(showSdcardInfo,-1,-1)
    }

    private fun updateSdInfo() {
        val storageManager = getSystemService(Context.STORAGE_SERVICE) as StorageManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val storageVolumes = storageManager.storageVolumes
            storageVolumes.forEach {
                val uuid = it.uuid
                val removable = it.isRemovable
                val description = it.getDescription(this)
                val state = it.state
                val emulated = it.isEmulated
                val primary = it.isPrimary
                val showLog = """
                    SdCardAc-updateSdInfo: 
                        uuid=${uuid}                   
                        removeable=${removable}
                        description=${description}
                        state=${state}
                        emulated=${emulated}
                        primary=${primary}
                """.trimIndent()
                LogUtil.d(showLog);
            }
            updateSdcardInfo(storageVolumes)

            val primaryStorageVolume = storageManager.primaryStorageVolume
            val uuid = primaryStorageVolume.uuid
            val removable = primaryStorageVolume.isRemovable
            val description = primaryStorageVolume.getDescription(this)
            val state = primaryStorageVolume.state
            val emulated = primaryStorageVolume.isEmulated
            val primary = primaryStorageVolume.isPrimary
            val showLog = """
                    SdCardAc-updateSdInfo-primary: 
                        uuid=${uuid}                   
                        removeable=${removable}
                        description=${description}
                        state=${state}
                        emulated=${emulated}
                        primary=${primary}
                """.trimIndent()
            LogUtil.d(showLog);
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun updateSdcardInfo(storageVolumes : List<StorageVolume> ){
        showSdcardInfo?.let {
            val sb = StringBuilder()
            sb.append("SDCARD信息：\n")
            storageVolumes.forEachIndexed { index, storageVolume ->
                sb.append(index).append("\n")
                sb.append("removeable:").append(storageVolume.isRemovable).append("\n")
                sb.append("description:").append(storageVolume.getDescription(this)).append("\n")
                sb.append("uuid:").append(storageVolume.uuid).append("\n")
                sb.append("state:").append(storageVolume.state).append("\n")
                sb.append("emulated:").append(storageVolume.isEmulated).append("\n")
                sb.append("primary:").append(storageVolume.isPrimary).append("\n")
            }

            it.text = sb.toString()
        }
    }
}