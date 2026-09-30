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
import com.android.hdms.device.DeviceCommunicationManager;

import java.util.ArrayList;
import java.util.List;

public class CommunicationManagerActivity extends AppCompatActivity {
    private DeviceCommunicationManager mDeviceCommunicationManager;
    private ListView mListview;
    private ListAdapter mAdapter;
    private static final String[] communicationItems = new String[]{ "addApn", "deleteApn", "queryApn", "getApnInfo",
            "setPreferApn", "addNetworkAccessWhitelist", "removeNetworkAccessWhitelist", "getNetworkAccessWhitelist",
            "addNetworkAccessBlackList", "removeNetworkAccessBlackList", "getNetworkAccessBlackList", "addSSIDToWhiteList",
            "removeSSIDFromWhiteList", "getSSIDWhiteList","addSSIDToBlackList",
            "removeSSIDFromBlackList", "getSSIDBlackList", "addDefaultWLANConfig", "setVpn", "deleteVpn",
            "getVpnList", "getVpnProfile", "connectToVpn", "disconnectVpn",};

    private Toast toast;
    private boolean ret;
    private List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HDMSManager hdmsManager = HDMSManager.getInstance();
        mDeviceCommunicationManager = (DeviceCommunicationManager) hdmsManager.getModuleManager(HDMSManager.MODULE_TYPE.COMMUNICATION);
        mListview = findViewById(R.id.listview);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, communicationItems);
        mListview.setAdapter(mAdapter);
        list.add("huawei532_2.4G");
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String) mAdapter.getItem(i);
                switch (item) {
                    case "addApn":
                        break;
                    case "deleteApn":
                        break;
                    case "queryApn":
                        break;
                    case "getApnInfo":
                        break;
                    case "setPreferApn":
                        break;
                    case "addNetworkAccessWhitelist":
                        break;
                    case "removeNetworkAccessWhitelist":
                        break;
                    case "getNetworkAccessWhitelist":
                        break;
                    case "addNetworkAccessBlackList":
                        break;
                    case "removeNetworkAccessBlackList":
                        break;
                    case "getNetworkAccessBlackList":
                        break;
                    case "addSSIDToWhiteList":
                        mDeviceCommunicationManager.addSSIDToWhiteList(list);
                        break;
                    case "removeSSIDFromWhiteList":
                        mDeviceCommunicationManager.removeSSIDFromWhiteList(list);
                        break;
                    case "getSSIDWhiteList":
                        List<String> ssidWhiteList = mDeviceCommunicationManager.getSSIDWhiteList();
                        showShortToast(listToString(ssidWhiteList));
                        break;
                    case "addSSIDToBlackList":
                        mDeviceCommunicationManager.addSSIDToBlackList(list);
                        break;
                    case "removeSSIDFromBlackList":
                        mDeviceCommunicationManager.removeSSIDFromBlackList(list);
                        break;
                    case "getSSIDBlackList":
                        List<String> ssidBlackList = mDeviceCommunicationManager.getSSIDBlackList();
                        showShortToast(listToString(ssidBlackList));
                        break;
                    case "addDefaultWLANConfig":
                        Bundle bundle = new Bundle();
                        bundle.putString("ssid","huawei532_2.4G");
                        bundle.putString("password","532huawei");
                        bundle.putInt("type",3);
                        mDeviceCommunicationManager.addDefaultWLANConfig(bundle);
                        break;
                    case "setVpn":
                        break;
                    case "deleteVpn":
                        break;
                    case "getVpnList":
                        break;
                    case "getVpnProfile":
                        break;
                    case "connectToVpn":
                        break;
                    case "disconnectVpn":
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

