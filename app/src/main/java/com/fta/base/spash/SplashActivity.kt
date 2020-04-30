package com.fta.base.spash

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fta.base.MainActivity
import org.jetbrains.anko.startActivity as jumpAc
import pub.devrel.easypermissions.EasyPermissions

class SplashActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    companion object {
        const val PERMISSION_CODE = 1
        val REQUEST_PERMISSIONS = arrayOf<String>(
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        EasyPermissions.requestPermissions(this, "请给予权限!", PERMISSION_CODE, *REQUEST_PERMISSIONS)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        jumpAc<MainActivity>()
        finish()
    }
}