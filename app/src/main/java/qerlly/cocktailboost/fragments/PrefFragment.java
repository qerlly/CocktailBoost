package qerlly.cocktailboost.fragments;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import qerlly.cocktailboost.R;


public class PrefFragment extends PreferenceFragment {

    CheckBoxPreference mCheckPreference;
    CheckBoxPreference mGreenCheck;
    CheckBoxPreference mBrownCheck;
    CheckBoxPreference mRedCheck;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        mCheckPreference = (CheckBoxPreference) findPreference("checkboxPref");
        mGreenCheck = (CheckBoxPreference) findPreference("Theme1");
        mBrownCheck = (CheckBoxPreference) findPreference("Theme2");
        mRedCheck = (CheckBoxPreference) findPreference("Theme3");

        mGreenCheck.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                getActivity().finish();
                startActivity(getActivity().getIntent());
                getActivity().overridePendingTransition(0, 0);
                if (mGreenCheck.isChecked()) {
                    mBrownCheck.setChecked(false);
                    mRedCheck.setChecked(false);
                } else {
                    mGreenCheck.setChecked(true);
                }
                return false;
            }
        });

        mBrownCheck.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                getActivity().finish();
                startActivity(getActivity().getIntent());
                getActivity().overridePendingTransition(0, 0);
                if (mBrownCheck.isChecked()) {
                    mGreenCheck.setChecked(false);
                    mRedCheck.setChecked(false);
                } else {
                    mBrownCheck.setChecked(true);
                }
                return false;
            }
        });

        mRedCheck.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                getActivity().finish();
                startActivity(getActivity().getIntent());
                getActivity().overridePendingTransition(0, 0);
                if (mRedCheck.isChecked()) {
                    mGreenCheck.setChecked(false);
                    mBrownCheck.setChecked(false);
                } else {
                    mRedCheck.setChecked(true);
                }
                return false;
            }
        });
    }
}
