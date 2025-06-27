package com.ruhlan.dynamicappicon.extensions

import android.app.Activity
import android.content.ComponentName
import android.content.pm.PackageManager

/**
 * Created on 28 Jun 2025,00:43
 * @author Ruhlan Usubov
 */

fun Activity.changeEnabledComponent(
    enabled: String,
    disabled: String,
) {
    packageManager.setComponentEnabledSetting(
        ComponentName(
            this,
            enabled
        ),
        PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
        PackageManager.DONT_KILL_APP
    )

    packageManager.setComponentEnabledSetting(
        ComponentName(
            this,
            disabled
        ),
        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
        PackageManager.DONT_KILL_APP
    )
}
