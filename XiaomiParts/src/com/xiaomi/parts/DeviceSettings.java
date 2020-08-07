/*
 * Copyright (C) 2018 The Asus-SDM660 Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.xiaomi.parts;

import android.content.Intent;
import android.content.pm.PackageManager;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.os.Handler;
import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;

import android.content.SharedPreferences;
import androidx.preference.SwitchPreference;
import android.util.Log;

import com.xiaomi.parts.preferences.SecureSettingListPreference;
import com.xiaomi.parts.preferences.SecureSettingSwitchPreference;


public class DeviceSettings extends PreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    private static final String TAG = "XiaomiParts";

	public static final String PREF_CAMERA = "camera";
    public static final String CAMERA_SYSTEM_PROPERTY = "persist.camera.profile";

    private SecureSettingListPreference mCamera;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences_xiaomi_parts, rootKey);

        String device = FileUtils.getStringProp("ro.build.product", "unknown");

       	// HAL3|HAL1 Switch button profiles
        mCamera = (SecureSettingListPreference) findPreference(PREF_CAMERA);
        mCamera.setValue(FileUtils.getStringProp(CAMERA_SYSTEM_PROPERTY, "0"));
        mCamera.setSummary(mCamera.getEntry());
        mCamera.setOnPreferenceChangeListener(this);
		}


    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        final String key = preference.getKey();
        switch (key) {

            case PREF_CAMERA:
                mCamera.setValue((String) value);
               	mCamera.setSummary(mCamera.getEntry());
                FileUtils.setStringProp(CAMERA_SYSTEM_PROPERTY, (String) value);
                break;

            default:
                break;
        }
        return true;
    }
}
