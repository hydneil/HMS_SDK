package com.android.hmsdemo;

import android.content.Intent;
import android.content.pm.IPackageDeleteObserver;
import android.content.pm.IPackageInstallObserver2;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.hdms.HDMSManager;
import com.android.hdms.device.DeviceApplicationManager;

import java.util.ArrayList;
import java.util.List;

public class ApplicationManagerActivity extends AppCompatActivity {

    private DeviceApplicationManager mDeviceApplicationManager;
    private ListView mListview;
    private ListAdapter mAdapter;
    private PackageInstallObserver mPackageInstallObserver;
    private PackageDeleteObserver mPackageDeleteObserver;
//    private String testPkgName = "com.netease.cloudmusic";
    private String testPkgName = "com.qmd.junzi.qmd";
    private List<String> mList = new ArrayList<>();
    private static final String[] applicationItems = new String[]{"installPackage", "uninstallPackage", "clearPackageData",
             "addInstallPackageWhiteList", "removeInstallPackageWhiteList",
            "getInstallPackageWhiteList", "addInstallPackageBlackList", "removeInstallPackageBlackList", "getInstallPackageBlackList",
            "addDisallowedUninstallPackages", "removeDisallowedUninstallPackages", "getDisallowedUninstallPackageList",
            "addPersistentApp", "removePersistentApp", "getPersistentApp", "addDisallowedRunningApp", "removeDisallowedRunningApp",
            "getDisallowedRunningApp", "killApplicationProcess", "addSingleApp", "clearSingleApp", "setSelfRunningApp",
            "deleteSelfRunningApp", "getSelfRunningApp","enableInstallSource","disableInstallSource"};//"disableInstallSource", "getInstallPackageSourceWhiteList",

    private Toast toast;
    private boolean ret;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HDMSManager hdmsManager = HDMSManager.getInstance();
        mDeviceApplicationManager = (DeviceApplicationManager) hdmsManager.getModuleManager(HDMSManager.MODULE_TYPE.APPLICATION);
        mListview = findViewById(R.id.listview);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, applicationItems);
        mListview.setAdapter(mAdapter);
        mList.add(testPkgName);
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String) mAdapter.getItem(i);
                switch (item) {
                    case "installPackage":
                        mDeviceApplicationManager.installPackage("/sdcard/test.apk",0,mPackageInstallObserver);
                        break;
                    case "uninstallPackage":
                        mDeviceApplicationManager.uninstallPackage(testPkgName,false,mPackageDeleteObserver);
                        break;
                    case "clearPackageData":
                        mDeviceApplicationManager.clearPackageData(testPkgName,0);
                        break;
//                    case "disableInstallSource":
//                        mDeviceApplicationManager.disableInstallSource(mList);
//                        break;
//                    case "getInstallPackageSourceWhiteList":
//                        mDeviceApplicationManager
//                        break;
                    case "addInstallPackageWhiteList":
                        mDeviceApplicationManager.addInstallPackageWhiteList(mList);
                        break;
                    case "removeInstallPackageWhiteList":
                        mDeviceApplicationManager.removeInstallPackageWhiteList(mList);
                        break;
                    case "getInstallPackageWhiteList":
                        List<String> installPackageWhiteList = mDeviceApplicationManager.getInstallPackageWhiteList();
                        showShortToast(listToString(installPackageWhiteList));
                        break;
                    case "addInstallPackageBlackList":
                        mDeviceApplicationManager.addInstallPackageBlackList(mList);
                        break;
                    case "removeInstallPackageBlackList":
                        mDeviceApplicationManager.removeInstallPackageBlackList(mList);
                        break;
                    case "getInstallPackageBlackList":
                        List<String> installPackageBlackList = mDeviceApplicationManager.getInstallPackageBlackList();
                        showShortToast(listToString(installPackageBlackList));
                        break;
                    case "addDisallowedUninstallPackages":
                        mDeviceApplicationManager.addDisallowedUninstallPackages(mList);
                        break;
                    case "removeDisallowedUninstallPackages":
                        mDeviceApplicationManager.removeDisallowedUninstallPackages(mList);
                        break;
                    case "getDisallowedUninstallPackageList":
                        List<String> disallowedUninstallPackageList = mDeviceApplicationManager.getDisallowedUninstallPackageList();
                        showShortToast(listToString(disallowedUninstallPackageList));
                        break;
                    case "addPersistentApp":
                        mDeviceApplicationManager.addPersistentApp(mList);
                        break;
                    case "removePersistentApp":
                        mDeviceApplicationManager.removePersistentApp(mList);
                        break;
                    case "getPersistentApp":
                        List<String> persistentApp = mDeviceApplicationManager.getPersistentApp();
                        showShortToast(listToString(persistentApp));
                        break;
                    case "addDisallowedRunningApp":
                        mDeviceApplicationManager.addDisallowedRunningApp(mList);
                        break;
                    case "removeDisallowedRunningApp":
                        mDeviceApplicationManager.removeDisallowedRunningApp(mList);
                        break;
                    case "getDisallowedRunningApp":
                        List<String> disallowedRunningApp = mDeviceApplicationManager.getDisallowedRunningApp();
                        showShortToast(listToString(disallowedRunningApp));
                        break;
                    case "killApplicationProcess":
                        mDeviceApplicationManager.killApplicationProcess(testPkgName);
                        break;
                    case "addSingleApp":
                        mDeviceApplicationManager.addSingleApp(testPkgName);
                        break;
                    case "clearSingleApp":
                        mDeviceApplicationManager.clearSingleApp(testPkgName);
                        break;
                    case "setSelfRunningApp":
                        mDeviceApplicationManager.setSelfRunningApp("com.lgzn.yjm","com.lgzn.yjm.LoginActivity");
                        break;
                    case "deleteSelfRunningApp":
                        mDeviceApplicationManager.deleteSelfRunningApp("com.lgzn.yjm","com.lgzn.yjm.LoginActivity");
                        break;
                    case "getSelfRunningApp":
                        List<String> selfRunningApp = mDeviceApplicationManager.getSelfRunningApp();
                        showShortToast(listToString(selfRunningApp));
                        break;
                    case "enableInstallSource":
                        List<String> enableList = new ArrayList<>();
                        enableList.add("com.tencent.android.qqdownloader");
                        mDeviceApplicationManager.disableInstallSource(enableList);
                        mDeviceApplicationManager.enableInstallSource(enableList);
                        break;
                    case "disableInstallSource":
                        List<String> disableList = new ArrayList<>();
                        disableList.add("com.tencent.android.qqdownloader");
                        break;
                }
            }
        });
    }
    public class PackageInstallObserver extends IPackageInstallObserver2.Stub{

        @Override
        public void onUserActionRequired(Intent intent) throws RemoteException {

        }

        @Override
        public void onPackageInstalled(String s, int i, String s1, Bundle bundle) throws RemoteException {
            showShortToast("packageName = "+ s+ " | result code = "+ i + " | msg = "+s1);
        }
    }
    public class PackageDeleteObserver extends IPackageDeleteObserver.Stub{

        @Override
        public void packageDeleted(String s, int i) throws RemoteException {
            showShortToast("packageName = "+ s+ " | result code = "+ i );
        }
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

