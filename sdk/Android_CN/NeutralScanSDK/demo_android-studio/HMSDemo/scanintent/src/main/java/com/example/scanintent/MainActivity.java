package com.example.scanintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.ParcelFileDescriptor;
import android.os.UserHandle;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    private TextView mbarcode_text;
    public static final String BROADCAST_BARCODE_ACTION = "com.android.scanner.broadcast";
    public static final String BROADCAST_BARCODE_EXTRA_DATA = "scandata";
    public static final String BROADCAST_BARCODE_EXTRA_BYTES = "scandata_array";
    public static final String BROADCAST_BARCODE_EXTRA_TYPE = "barcode_type";
    public static final String BROADCAST_BARCODE_EXTRA_LENGTH = "length";

    private BroadcastReceiver mScanReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            Log.d(TAG, "action :" + intent.getAction());
            int barcodelen = intent.getIntExtra(BROADCAST_BARCODE_EXTRA_LENGTH, 0);
            String result = intent.getStringExtra(BROADCAST_BARCODE_EXTRA_DATA);
            StringBuilder stringBuilder = new StringBuilder();
            if(result != null) {
                stringBuilder.append("Data: " + result +
                        "\nLength: " + barcodelen);
            }
            if(intent.hasExtra(BROADCAST_BARCODE_EXTRA_BYTES)) {
                byte[] barcode = intent.getByteArrayExtra(BROADCAST_BARCODE_EXTRA_BYTES);
                if(barcode != null) {
                    Log.d(TAG, "barcode:" + new String(barcode));
                }
            }
            if(intent.hasExtra("scandata_symbology")) {
                String symName = intent.getStringExtra("scandata_symbology");
                stringBuilder.append("\nsymName: " + symName);
            }
            if(intent.hasExtra(BROADCAST_BARCODE_EXTRA_TYPE)) {
                int symId = intent.getByteExtra(BROADCAST_BARCODE_EXTRA_TYPE, (byte)0x30);
                stringBuilder.append("\nsymId: " + symId);
            }

            if(intent.hasExtra("aimCodeId")) {
                byte[] AimID = intent.getByteArrayExtra("aimCodeId");
                if(AimID != null)
                stringBuilder.append("\nAimID: " + String.format("]%c%c (0x%02x%02x)", AimID[1], AimID[2], AimID[1], AimID[2]));
            }
            if(intent.hasExtra("decodeTime")) {
                long time = intent.getLongExtra("decodeTime", 0L);
                stringBuilder.append("\ndecodeTime(ms): " + time);
            }
            mbarcode_text.setText(stringBuilder.toString());
            try {
                Bundle resultBundle = intent.getExtras();
                Set<String> keys = resultBundle.keySet();
                for(String key: keys) {
                    Object object = resultBundle.get(key);
                    if(object instanceof String) {
                        Log.d(TAG, "key: " + key + " value: " + object);
                    } else if(object instanceof String[]) {
                        String[] data = (String[])object;
                        for(String code:data) {
                            Log.d(TAG, "key: " + key + " value: " + code);
                        }
                    } else if(object instanceof byte[]) {
                        byte[] data = (byte[])object;
                        Log.d(TAG, "key: " + key + " value: " + (new String(data)));
                    } else {
                        Log.d(TAG, "key: " + key + " object: " + object);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    };
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbarcode_text = (TextView) findViewById(R.id.barcode_text);
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        unregisterReceiver(mScanReceiver);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        //switch to BROADCAST out
        Intent intent = new Intent("com.android.scanner_settings");
        intent.putExtra("barcode_send_mode","BROADCAST");
        sendBroadcast(intent);

        IntentFilter filter = new IntentFilter();
        filter.addAction(BROADCAST_BARCODE_ACTION);
        registerReceiver(mScanReceiver, filter);
    }
}
