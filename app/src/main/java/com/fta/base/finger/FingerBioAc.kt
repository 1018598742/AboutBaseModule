package com.fta.base.finger

import android.content.DialogInterface
import android.hardware.biometrics.BiometricManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import androidx.appcompat.app.AppCompatActivity
import com.common.basemodule.util.LogUtil
import com.fta.base.R
import org.jetbrains.anko.toast

class FingerBioAc : AppCompatActivity() {

    private lateinit var mBiometricPrompt: BiometricPrompt

    private lateinit var mCancellationSignal: CancellationSignal

    private lateinit var mAuthenticationCallback: BiometricPrompt.AuthenticationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baseroot)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            mBiometricPrompt =  BiometricPrompt.Builder(this)
                .setTitle("指纹验证")
                .setDescription("描述")
                .setNegativeButton("取消", mainExecutor,
                    DialogInterface.OnClickListener { dialog, which ->
                        toast("取消了！")
                    })
                .build()
        }

        mCancellationSignal = CancellationSignal()
        mCancellationSignal.setOnCancelListener {
            toast("取消 signal!")
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            mAuthenticationCallback = object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                    super.onAuthenticationError(errorCode, errString)
                    LogUtil.d("FingerBioAc-onAuthenticationError: ${errorCode}  ==errString=${errString}");
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                    super.onAuthenticationSucceeded(result)
                    LogUtil.d("FingerBioAc-onAuthenticationSucceeded: ${result}");
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    LogUtil.d("FingerBioAc-onAuthenticationFailed: ");
                }
            }

            mBiometricPrompt.authenticate(
                mCancellationSignal,
                mainExecutor,
                mAuthenticationCallback
            )
        }

    }
}