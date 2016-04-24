/*
 * Copyright (C) 2015 The CyanogenMod Project
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
 * limitations under the License.
 */

package com.cyanogenmod.settings.doze;

import android.app.ActionBar;
<<<<<<< HEAD
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
=======
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
>>>>>>> a4feaa4... oneplus2: add doze package
import android.preference.SwitchPreference;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

<<<<<<< HEAD
public class DozeSettings extends PreferenceActivity implements OnPreferenceChangeListener {

    private Context mContext;
    private SharedPreferences mPreferences;

    private SwitchPreference mAmbientDisplayPreference;
    private SwitchPreference mPickUpPreference;
    private SwitchPreference mTiltAlwaysPreference;
    private SwitchPreference mHandwavePreference;
    private SwitchPreference mPocketPreference;
    private SwitchPreference mProximityAlwaysPreference;
=======
import org.cyanogenmod.internal.util.ScreenType;

public class DozeSettings extends PreferenceActivity {

    private static final String KEY_AMBIENT_DISPLAY_ENABLE = "ambient_display_enable";
    private static final String KEY_HAND_WAVE = "gesture_hand_wave";
    private static final String KEY_GESTURE_POCKET = "gesture_pocket";
    private static final String KEY_PROXIMITY_WAKE = "proximity_wake_enable";

    private SwitchPreference mAmbientDisplayPreference;
    private SwitchPreference mHandwavePreference;
    private SwitchPreference mPocketPreference;
    private SwitchPreference mProximityWakePreference;
>>>>>>> a4feaa4... oneplus2: add doze package

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        addPreferencesFromResource(R.xml.doze_settings);
        mContext = getApplicationContext();
        boolean dozeEnabled = Utils.isDozeEnabled(mContext);

        // get shared preference
        mPreferences = mContext.getSharedPreferences("doze_settings", Activity.MODE_PRIVATE);
        if (savedInstanceState == null && !mPreferences.getBoolean("first_help_shown", false)) {
            showHelp();
        }

        mAmbientDisplayPreference =
            (SwitchPreference) findPreference(Utils.AMBIENT_DISPLAY_KEY);
        // Read from DOZE_ENABLED secure setting
        mAmbientDisplayPreference.setChecked(dozeEnabled);
        mAmbientDisplayPreference.setOnPreferenceChangeListener(this);

        mTiltAlwaysPreference =
            (SwitchPreference) findPreference(Utils.TILT_ALWAYS_KEY);
        mTiltAlwaysPreference.setOnPreferenceChangeListener(this);

        mPickUpPreference =
            (SwitchPreference) findPreference(Utils.PICK_UP_KEY);
        mPickUpPreference.setOnPreferenceChangeListener(this);

        mHandwavePreference =
            (SwitchPreference) findPreference(Utils.GESTURE_HAND_WAVE_KEY);
        mHandwavePreference.setOnPreferenceChangeListener(this);

        mPocketPreference =
            (SwitchPreference) findPreference(Utils.GESTURE_POCKET_KEY);
        mPocketPreference.setOnPreferenceChangeListener(this);

        mProximityAlwaysPreference =
            (SwitchPreference) findPreference(Utils.PROXIMITY_ALWAYS_KEY);
        mProximityAlwaysPreference.setOnPreferenceChangeListener(this);

        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        updateAlwaysEnabledPreference();
=======
        addPreferencesFromResource(R.xml.gesture_panel);
        boolean dozeEnabled = isDozeEnabled();
        mAmbientDisplayPreference =
            (SwitchPreference) findPreference(KEY_AMBIENT_DISPLAY_ENABLE);
        // Read from DOZE_ENABLED secure setting
        mAmbientDisplayPreference.setChecked(dozeEnabled);
        mAmbientDisplayPreference.setOnPreferenceChangeListener(mAmbientDisplayPrefListener);
        mHandwavePreference =
            (SwitchPreference) findPreference(KEY_HAND_WAVE);
        mHandwavePreference.setEnabled(dozeEnabled);
        mHandwavePreference.setOnPreferenceChangeListener(mProximityListener);
        mPocketPreference =
            (SwitchPreference) findPreference(KEY_GESTURE_POCKET);
        mPocketPreference.setEnabled(dozeEnabled);
        mProximityWakePreference =
            (SwitchPreference) findPreference(KEY_PROXIMITY_WAKE);
        mProximityWakePreference.setOnPreferenceChangeListener(mProximityListener);

        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
>>>>>>> a4feaa4... oneplus2: add doze package
    }

    @Override
    protected void onResume() {
        super.onResume();
<<<<<<< HEAD
        updateAlwaysEnabledPreference();
=======

        // If running on a phone, remove padding around the listview
        if (!ScreenType.isTablet(this)) {
            getListView().setPadding(0, 0, 0, 0);
        }
>>>>>>> a4feaa4... oneplus2: add doze package
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

<<<<<<< HEAD
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        final String key = preference.getKey();
        final boolean value = (Boolean) newValue;
        if (Utils.AMBIENT_DISPLAY_KEY.equals(key)) {
            mAmbientDisplayPreference.setChecked(value);
            Utils.enableDoze(value, mContext);
            return true;
        } else if (Utils.PICK_UP_KEY.equals(key)) {
            mPickUpPreference.setChecked(value);
            updateAlwaysEnabledPreference();
            Utils.startService(mContext);
            return true;
        } else if (Utils.TILT_ALWAYS_KEY.equals(key)) {
            mTiltAlwaysPreference.setChecked(value);
            return true;
        } else if (Utils.GESTURE_HAND_WAVE_KEY.equals(key)) {
            mHandwavePreference.setChecked(value);
            updateAlwaysEnabledPreference();
            Utils.startService(mContext);
            return true;
        } else if (Utils.GESTURE_POCKET_KEY.equals(key)) {
            mPocketPreference.setChecked(value);
            updateAlwaysEnabledPreference();
            Utils.startService(mContext);
            return true;
        } else if (Utils.PROXIMITY_ALWAYS_KEY.equals(key)) {
            mProximityAlwaysPreference.setChecked(value);
            return true;
        }
        return false;
    }

    public static class HelpDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.doze_settings_help_title)
                    .setMessage(R.string.doze_settings_help_text)
                    .setNegativeButton(R.string.dlg_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .create();
        }

        @Override
        public void onCancel(DialogInterface dialog) {
            getActivity().getSharedPreferences("doze_settings", Activity.MODE_PRIVATE)
                    .edit()
                    .putBoolean("first_help_shown", true)
                    .commit();
        }
    }

    private void showHelp() {
        HelpDialogFragment fragment = new HelpDialogFragment();
        fragment.show(getFragmentManager(), "help_dialog");
    }

    private void updateAlwaysEnabledPreference() {
        boolean tiltEnabled = Utils.pickUpEnabled(mContext);
        boolean proximityEnabled = Utils.handwaveGestureEnabled(mContext)
                || Utils.pocketGestureEnabled(mContext);
        mTiltAlwaysPreference.setEnabled(tiltEnabled);
        mProximityAlwaysPreference.setEnabled(proximityEnabled);
    }
=======
    private boolean enableDoze(boolean enable) {
        return Settings.Secure.putInt(getContentResolver(),
                Settings.Secure.DOZE_ENABLED, enable ? 1 : 0);
    }

    private boolean isDozeEnabled() {
        return Settings.Secure.getInt(getContentResolver(),
                Settings.Secure.DOZE_ENABLED, 1) != 0;
    }

    private Preference.OnPreferenceChangeListener mAmbientDisplayPrefListener =
        new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            boolean enable = (boolean) newValue;
            boolean ret = enableDoze(enable);
            if (ret) {
                mHandwavePreference.setEnabled(enable);
                mPocketPreference.setEnabled(enable);
            }
            return ret;
        }
    };

    private Preference.OnPreferenceChangeListener mProximityListener =
        new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            if ((boolean) newValue) {
                if (preference.getKey().equals(KEY_HAND_WAVE)) {
                    mProximityWakePreference.setChecked(false);
                } else if (preference.getKey().equals(KEY_PROXIMITY_WAKE)) {
                    mHandwavePreference.setChecked(false);
                }
            }
            return true;
        }
    };
>>>>>>> a4feaa4... oneplus2: add doze package
}
