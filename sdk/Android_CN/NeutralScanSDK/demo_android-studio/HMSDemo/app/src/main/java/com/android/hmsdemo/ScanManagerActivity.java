package com.android.hmsdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.hdms.HDMSManager;
import com.android.hdms.configuration.ScannerConstant;
import com.android.hdms.scanner.ScanManager;
import com.android.scanner.DecodeCallback;


public class ScanManagerActivity extends AppCompatActivity {

    private static final String TAG = "ScanManagerActivity";
    private ScanManager mScanManager;
    private ListView mListview;
    private ListAdapter mAdapter;
    private static final String[] scannerItems = new String[]{"open", "close", "setDecodeMode", "getDecodeMode",
            "startDecode", "stopDecode","disableScanKey","getScanKeyStatus", "reset", "setScanMode", "getScanMode","setParameterInt",
            "getParameterInt", "setParameterString", "getParameterString", "setParameters", "getParameters","decodeSound","decodeVibration",
            "broadcastSetting","barcodePrefix","barcodeSuffix","setDecodeCallback"};

    private Toast toast;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListview = findViewById(R.id.listview);
        mScanManager = (ScanManager) HDMSManager.getInstance().getModuleManager(HDMSManager.MODULE_TYPE.SCANNER);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, scannerItems);
        mListview.setAdapter(mAdapter);
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String) mAdapter.getItem(i);
                switch (item) {
                    case "open":
                        mScanManager.open();
                        break;
                    case "close":
                        mScanManager.close();
                        break;
                    case "setDecodeMode":
                        mScanManager.setDecodeMode(ScannerConstant.DecodeMode.BROADCAST);
                        break;
                    case "getDecodeMode":
                        int decodeMode = mScanManager.getDecodeMode();
                        showShortToast("decode mode = "+decodeMode);
                        break;
                    case "startDecode":
                        mScanManager.startDecode();
                        break;
                    case "stopDecode":
                        mScanManager.stopDecode();
                        break;
                    case "disableScanKey":
                        mScanManager.disableScanKey(true);
                        break;
                    case "getScanKeyStatus":
                        showShortToast("current scan key status"+mScanManager.getScanKeyStatus());
                        break;
                    case "reset":
                        mScanManager.reset();
                        break;
                    case "setScanMode":
                        mScanManager.setScanMode(ScannerConstant.ScannerMode.MODE_CONTINUE);
                        break;
                    case "getScanMode":
                        int mode = mScanManager.getScanMode();
                        showShortToast("scan mode = "+mode);
                        break;
                    case "setParameterInt":
                        mScanManager.setParameterInt(ScannerConstant.ParamKey.DECODE_PICKLIST_MODE,0);
                        break;
                    case "getParameterInt":
                        int parameterInt = mScanManager.getParameterInt(ScannerConstant.ParamKey.DECODE_PICKLIST_MODE, -1);
                        showShortToast("get parameter = "+parameterInt);
                        break;
                    case "setParameterString":
                        mScanManager.setParameterString(ScannerConstant.ParamKey.BROADCAST_INTENT_ACTION_NAME,"com.android.scanner");
                        break;
                    case "getParameterString":
                        showShortToast("get intent = "+ mScanManager.getParameterString(ScannerConstant.ParamKey.BROADCAST_INTENT_ACTION_NAME));
                        break;
                    case "setParameters":
                        break;
                    case "getParameters":
                        break;
                    case "decodeSound":
                        mScanManager.decodeSound(0);
                        break;
                    case "decodeVibration":
                        mScanManager.decodeVibration(true);
                        break;
                    case "broadcastSetting":
                        mScanManager.broadcastSetting("com.test.barcode","data");
                        break;
                    case "barcodePrefix":
                        mScanManager.barcodePrefix("pre");
                        break;
                    case "barcodeSuffix":
                        mScanManager.barcodeSuffix("suf");
                        break;
                    case "setDecodeCallback":
                        mScanManager.setDecodeCallback(callback);
                        break;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ScanManager.BROADCAST_BARCODE_ACTION);
        registerReceiver(mDecodeBroadcast,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mDecodeBroadcast);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)
            return false;
        return super.dispatchKeyEvent(event);
    }

    public void showShortToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    private DecodeCallback callback = new DecodeCallback.Stub() {
        @Override
        public void onDecodeComplete(Bundle bundle) throws RemoteException {
            String barcode = bundle.getString(ScanManager.BROADCAST_BARCODE_EXTRA_DATA);
            int length = bundle.getInt(ScanManager.BROADCAST_BARCODE_EXTRA_LENGTH);
            int type = bundle.getInt(ScanManager.BROADCAST_BARCODE_EXTRA_TYPE);
            Log.d(TAG,"onDecodeComplete barcode = "+ barcode+"  length = "+ length + "   type = "+type);
        }
    };

    private BroadcastReceiver mDecodeBroadcast =  new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String barcode = intent.getStringExtra(ScanManager.BROADCAST_BARCODE_EXTRA_DATA);
            int length = intent.getIntExtra(ScanManager.BROADCAST_BARCODE_EXTRA_LENGTH,0);
            int type = intent.getIntExtra(ScanManager.BROADCAST_BARCODE_EXTRA_TYPE,0);
            Log.d(TAG,"mDecodeBroadcast barcode = "+ barcode+"  length = "+ length + "   type = "+type);
        }
    };
}
