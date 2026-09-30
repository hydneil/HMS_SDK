package com.android.hmsdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.hdms.HDMSManager;
import com.android.hdms.device.DeviceRestrictionManager;

public class RestrictionManagerActivity extends AppCompatActivity {

    private DeviceRestrictionManager deviceRestrictionManager;
    private ListView mListview;
    private ListAdapter mAdapter;
    private static final String[] restrictionItems = new String[]{"setWifiEnabled", "isWifiEnabled", "setBluetoothEnabled",
            "isBluetoothEnabled", "setWifiApEnabled", "isWifiApEnabled", "setNfcEnabled", "isNfcEnabled", "setDataConnectivityEnabled",
            "isDataConnectivityEnabled", "setUsbEnabled", "isUsbEnabled", "setStatusBarEnabled", "isStatusBarEnabled", "setAdbEnabled",
            "isAdbEnabled", "setTaskButtonEnabled", "isTaskButtonEnabled", "setHomeButtonEnabled", "isHomeButtonEnabled",
            "setBackButtonEnabled", "isBackButtonEnabled", "setGPSEnabled", "isGPSEnabled", "setDevelopModeEnabled",
            "isDevelopModeEnabled", "setInstallPackageEnable", "isInstallSourceEnabled","setScreenshotEnable"};

    private Toast toast;
    private boolean ret;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HDMSManager hdmsManager = HDMSManager.getInstance();
        deviceRestrictionManager = (DeviceRestrictionManager) hdmsManager.getModuleManager(HDMSManager.MODULE_TYPE.RESTRICTION);

        mListview = findViewById(R.id.listview);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, restrictionItems);
        mListview.setAdapter(mAdapter);
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String) mAdapter.getItem(i);
                switch (item) {
                    case "setWifiEnabled":
                        deviceRestrictionManager.setWifiEnabled(!deviceRestrictionManager.isWifiEnabled());
                        showShortToast("current wifi enable status " + deviceRestrictionManager.isWifiEnabled());
                        break;
                    case "isWifiEnabled":
                        ret =  deviceRestrictionManager.isWifiEnabled();
                        showShortToast("current wifi enable status " + ret);
                        break;
                    case "setBluetoothEnabled":
                        deviceRestrictionManager.setBluetoothEnabled(!deviceRestrictionManager.isBluetoothEnabled());
                        showShortToast("current bluetooth enable status " + deviceRestrictionManager.isBluetoothEnabled());
                        break;
                    case "isBluetoothEnabled":
                        ret =  deviceRestrictionManager.isBluetoothEnabled();
                        showShortToast("current bluetooth enable status " + ret);
                        break;
                    case "setWifiApEnabled":
                        deviceRestrictionManager.setWifiApEnabled(!deviceRestrictionManager.isWifiApEnabled());
                        showShortToast("current wifi ap enable status " + deviceRestrictionManager.isWifiApEnabled());
                        break;
                    case "isWifiApEnabled":
                        ret =  deviceRestrictionManager.isWifiApEnabled();
                        showShortToast("current wifi ap enable status " + ret);
                        break;
                    case "setNfcEnabled":
                        deviceRestrictionManager.setNfcEnabled(!deviceRestrictionManager.isNfcEnabled());
                        showShortToast("current nfc enable status " + deviceRestrictionManager.isNfcEnabled());
                        break;
                    case "isNfcEnabled":
                        ret =  deviceRestrictionManager.isNfcEnabled();
                        showShortToast("current nfc enable status " + ret);
                        break;
                    case "setDataConnectivityEnabled":
                        deviceRestrictionManager.setDataConnectivityEnabled(!deviceRestrictionManager.isDataConnectivityEnabled());
                        showShortToast("current data connectivity status " + deviceRestrictionManager.isDataConnectivityEnabled());
                        break;
                    case "isDataConnectivityEnabled":
                        ret =  deviceRestrictionManager.isDataConnectivityEnabled();
                        showShortToast("current data connectivity enable status " + ret);
                        break;
                    case "setUsbEnabled":
                        deviceRestrictionManager.setUsbEnabled(!deviceRestrictionManager.isUsbEnabled());
                        showShortToast("current usb status " + deviceRestrictionManager.isUsbEnabled());
                        break;
                    case "isUsbEnabled":
                        ret =  deviceRestrictionManager.isUsbEnabled();
                        showShortToast("current usb enable status " + ret);
                        break;
                    case "setStatusBarEnabled":
                        deviceRestrictionManager.setStatusBarEnabled(!deviceRestrictionManager.isStatusBarEnabled());
                        showShortToast("current status bar status " + deviceRestrictionManager.isStatusBarEnabled());
                        break;
                    case "isStatusBarEnabled":
                        ret =  deviceRestrictionManager.isStatusBarEnabled();
                        showShortToast("current status bar enable status " + ret);
                        break;
                    case "setAdbEnabled":
                        deviceRestrictionManager.setAdbEnabled(!deviceRestrictionManager.isAdbEnabled());
                        showShortToast("current adb status " + deviceRestrictionManager.isAdbEnabled());
                        break;
                    case "isAdbEnabled":
                        ret =  deviceRestrictionManager.isAdbEnabled();
                        showShortToast("current adb enable status " + ret);
                        break;
                    case "setTaskButtonEnabled":
                        deviceRestrictionManager.setTaskButtonEnabled(!deviceRestrictionManager.isTaskButtonEnabled());
                        showShortToast("current task btn status " + deviceRestrictionManager.isTaskButtonEnabled());
                        break;
                    case "isTaskButtonEnabled":
                        ret =  deviceRestrictionManager.isTaskButtonEnabled();
                        showShortToast("current task btn status " + ret);
                        break;
                    case "setHomeButtonEnabled":
                        deviceRestrictionManager.setHomeButtonEnabled(!deviceRestrictionManager.isHomeButtonEnabled());
                        showShortToast("current home btn status " + deviceRestrictionManager.isHomeButtonEnabled());
                        break;
                    case "isHomeButtonEnabled":
                        ret =  deviceRestrictionManager.isHomeButtonEnabled();
                        showShortToast("current home btn enable status " + ret);
                        break;
                    case "setBackButtonEnabled":
                        deviceRestrictionManager.setBackButtonEnabled(!deviceRestrictionManager.isBackButtonEnabled());
                        showShortToast("current back btn status " + deviceRestrictionManager.isBackButtonEnabled());
                        break;
                    case "isBackButtonEnabled":
                        ret =  deviceRestrictionManager.isBackButtonEnabled();
                        showShortToast("current back btn enable status " + ret);
                        break;
                    case "setGPSEnabled":
                        deviceRestrictionManager.setGPSEnabled(!deviceRestrictionManager.isGPSEnabled());
                        showShortToast("current gps status " + deviceRestrictionManager.isGPSEnabled());
                        break;
                    case "isGPSEnabled":
                        ret =  deviceRestrictionManager.isGPSEnabled();
                        showShortToast("current gps enable status " + ret);
                        break;
                    case "setDevelopModeEnabled":
                        deviceRestrictionManager.setDevelopModeEnabled(!deviceRestrictionManager.isDevelopModeEnabled());
                        showShortToast("current develop mode status " + deviceRestrictionManager.isDevelopModeEnabled());
                        break;
                    case "isDevelopModeEnabled":
                        ret =  deviceRestrictionManager.isDevelopModeEnabled();
                        showShortToast("current develop mode enable status " + ret);
                        break;
                    case "setInstallPackageEnable":
                        deviceRestrictionManager.setInstallPackageEnable(!deviceRestrictionManager.isInstallSourceEnabled());
                        showShortToast("current install source status " + deviceRestrictionManager.isInstallSourceEnabled());
                        break;
                    case "isInstallSourceEnabled":
                        ret =  deviceRestrictionManager.isInstallSourceEnabled();
                        showShortToast("current install source enable status " + ret);
                        break;
                    case "setScreenshotEnable":
                        deviceRestrictionManager.setScreenshotEnable(true);
                        break;

                }
            }
        });
    }

    public void showShortToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();

    }
}