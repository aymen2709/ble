package com.example.sample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class configuration extends AppCompatActivity {
    private Intent mIntent;
    private Button bt_demarrer;
    WifiManager wifiManager;
    Switch wifiSwitch;
    //device list
    private static final String TAG = "configuration";
    BluetoothAdapter mBluetoothAdapter;
    public ArrayList<BluetoothDevice> mBTDevices = new ArrayList<>();
    //public DeviceListAdapter mDeviceListAdapter;
    ListView lvNewDevices;
//bluetooth
    Switch bleswitch;
    BluetoothAdapter bluetoothAdapter;
    public static final int REQUEST_ENABLE_BT=1;

    public void setBluetoothEnable(Boolean enable) {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter != null){
            if (enable) {
                if (!mBluetoothAdapter.isEnabled()) {
                    mBluetoothAdapter.enable();
                }
            } else {
                if (mBluetoothAdapter.isEnabled()) {
                    mBluetoothAdapter.disable();
                }
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        //button demarrer
        bt_demarrer=(Button) findViewById(R.id.demarrer2);
        bt_demarrer.setOnClickListener(view -> {
            mIntent = new Intent(configuration.this, MainActivity2.class);

            configuration.this.startActivity(mIntent);

        });
        //defining variables wifi
        wifiManager=(android.net.wifi.WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiSwitch=(Switch) findViewById(R.id.wifi_switch);
        //def de variable bluetooth
        bleswitch=(Switch) findViewById(R.id.bluetooth_switch);
        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter != null && !bluetoothAdapter.isEnabled()){
            bleswitch.setChecked(true);
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        }else{
            bleswitch.setChecked(false);
//            bluetoothAdapter.disable();
        }
        /*check wifi state
        if wifi is enabled set toggle to on position
        if wifi is disabled set toggle to off position
         */
        if(wifiManager.isWifiEnabled()){
            wifiSwitch.setChecked(true);
        }else {
            wifiSwitch.setChecked(false);
        }
        /*add listener to toggle button
        if toggle is checked ,wifi on
        if toggle is unchecked ,wifi off
         */
        wifiSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    wifiManager.setWifiEnabled(true);
                }else {
                    wifiManager.setWifiEnabled(false);
                }

            }
        });
        //de m pour bluetooth
        bleswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    setBluetoothEnable(true);
                    //partie de
//                    AlertDialog.Builder builder = new AlertDialog.Builder(configuration.this);
//
//                    builder.setMessage("are u sure").setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                        }
//                    }).setNegativeButton("cancel",null);
//                    AlertDialog dialog = builder.create();
//                    dialog.show();
//                   BroadcastReceiver mBroadcastReceiver3 = new BroadcastReceiver() {
//                        @Override
//                        public void onReceive(Context context, Intent intent) {
//                            final String action = intent.getAction();
//                            Log.d(TAG, "onReceive: ACTION FOUND.");
//
//                            if (action.equals(BluetoothDevice.ACTION_FOUND)){
//                                BluetoothDevice device = intent.getParcelableExtra (BluetoothDevice.EXTRA_DEVICE);
//                                mBTDevices.add(device);
//                                Log.d(TAG, "onReceive: " + device.getName() + ": " + device.getAddress());
//                                mDeviceListAdapter = new DeviceListAdapter(context, R.layout.device_adapter_view, mBTDevices);
//                                lvNewDevices.setAdapter( mDeviceListAdapter);
//                            }
//                        }
//                    };

                }else {
                    setBluetoothEnable(false);
                }}});}}


