package com.android.hmsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String[] module = new String[]{"DeviceApplicationManager", "DeviceCommunicationManager",
            "DeviceControlManager", "DeviceRestrictionManager","ScanManager"};
    private ListView mListview;
    private ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListview = findViewById(R.id.listview);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, module);
        mListview.setAdapter(mAdapter);
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View child, int position, long id) {
                String title = (String) mAdapter.getItem(position);
                switch (title) {
                    case "DeviceApplicationManager":
                        startActivity(ApplicationManagerActivity.class);
                        break;
                    case "DeviceCommunicationManager":
                        startActivity(CommunicationManagerActivity.class);
                        break;
                    case "DeviceControlManager":
                        startActivity(ControlManagerActivity.class);
                        break;
                    case "DeviceRestrictionManager":
                        startActivity(RestrictionManagerActivity.class);
                        break;
                    case "ScanManager":
                        startActivity(ScanManagerActivity.class);
                        break;

                    default:
                        break;
                }
            }
        });

    }

    public void startActivity(Class T) {
        startActivity(new Intent(this, T));
    }
}
