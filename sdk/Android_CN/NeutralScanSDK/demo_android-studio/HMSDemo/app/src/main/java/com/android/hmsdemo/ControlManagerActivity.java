package com.android.hmsdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.hdms.HDMSManager;
import com.android.hdms.configuration.TimeZoneConstant;
import com.android.hdms.device.DeviceControlManager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class ControlManagerActivity extends AppCompatActivity {
    private static final String TAG = "ControlManagerActivity";
    private DeviceControlManager mDeviceControlManager;
    private ListView mListview;
    private ListAdapter mAdapter;
    private String testPkgName = "com.netease.cloudmusic";
    private List<String> mList = new ArrayList<>();
    private static final String[] controlItems = new String[]{"powerOff","restart","wipeData","isRooted",
            "getDeviceSN","setGpsMode","setDefaultLauncher","clearDefaultLauncher",
            "captureScreen","setScreenLockNon","setScreenLockPIN","setScreenLockPassword","setScreenLockPattern",
            "setHideAppIcon","deleteHideAppIcon","getHideAppIcon","setNtpServerAdd","setTimeZone","setSystemTime","setTorchEnable",
            "writeToPogo","readFromPogo"};

    private Toast toast;
    private boolean ret;
    private boolean torch = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HDMSManager hdmsManager = HDMSManager.getInstance();
        mDeviceControlManager = (DeviceControlManager) hdmsManager.getModuleManager(HDMSManager.MODULE_TYPE.DEVICE_CONTROL);
        mListview = findViewById(R.id.listview);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, controlItems);
        mListview.setAdapter(mAdapter);
        mList.add(testPkgName);
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String) mAdapter.getItem(i);
                switch (item) {
                    case "powerOff":
                        mDeviceControlManager.powerOff();
                        break;
                    case "restart":
                        mDeviceControlManager.restart();
                        break;
                    case "wipeData":
                        mDeviceControlManager.wipeData();
                        break;
                    case "isRooted":
                        mDeviceControlManager.isRooted();
                        break;
                    case "getDeviceSN":
                        showShortToast(mDeviceControlManager.getDeviceSN());
                        Log.d(TAG, mDeviceControlManager.getDeviceSN());
                        break;
                    case "setGpsMode":
                        mDeviceControlManager.setGpsMode(0);
                        break;
                    case "setDefaultLauncher":
                        mDeviceControlManager.setDefaultLauncher("com.gau.go.launcherex","");
                        break;
                    case "clearDefaultLauncher":
                        mDeviceControlManager.clearDefaultLauncher();
                        break;
                    case "captureScreen":
                        mDeviceControlManager.captureScreen();
                        break;
                    case "setScreenLockNon":
                        mDeviceControlManager.setScreenLockNone(true);
                        break;
                    case "setScreenLockPIN":
                        mDeviceControlManager.setScreenLockPIN("1234");
                        break;
                    case "setScreenLockPassword":
                        mDeviceControlManager.setScreenLockPassword("123123");
                        break;
                    case "setScreenLockPattern":
                        mDeviceControlManager.setScreenLockPattern("12369");
                        break;
                    case "setHideAppIcon":
                        mDeviceControlManager.setHideAppIcon(mList);
                        break;
                    case "deleteHideAppIcon":
                        mDeviceControlManager.deleteHideAppIcon(mList);
                        break;
                    case "getHideAppIcon":
                        List<String> hideAppIcon = mDeviceControlManager.getHideAppIcon();
                        showShortToast(listToString(hideAppIcon));
                        break;
                    case "setNtpServerAdd":
                        mDeviceControlManager.setNtpServerAdd("ntp-sz.chl.la");
                        break;
                    case "setTimeZone":
                        mDeviceControlManager.setTimeZone(TimeZoneConstant.Amman);
                        break;
                    case "setSystemTime":
                        mDeviceControlManager.setSystemTime(System.currentTimeMillis()+360000);
                        break;
                    case "setTorchEnable":
                        torch = !torch;
                        mDeviceControlManager.setTorchEnable(torch);
                        break;
                    case "writeToPogo":
                        mDeviceControlManager.writeToPogo("123456");
                        break;
                    case "readFromPogo":
                        String s = mDeviceControlManager.readFromPogo();
                        showShortToast("read from pogo : ["+s+"]");
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
    public String listToString(List<String> list) {
        StringBuilder builder = new StringBuilder();
        for (String bean:list){
            builder.append(bean);
            builder.append(",");
        }
        return builder.toString();
    }
}

