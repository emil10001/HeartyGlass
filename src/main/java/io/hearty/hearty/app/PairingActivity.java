package io.hearty.hearty.app;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import io.hearty.ble.lib.BleScanner;
import io.hearty.ble.lib.data.BleConnect;
import io.hearty.ble.lib.data.DeviceFound;
import io.hearty.ble.lib.data.StoredBluetoothDevice;
import io.hearty.ble.lib.heart.BleHeartService;
import io.hearty.ble.lib.utils.BlePrefs;
import io.hearty.witness.Reporter;
import io.hearty.witness.Witness;
import com.google.android.glass.app.Card;
import com.google.android.glass.widget.CardScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ejf3 on 5/16/14.
 */
public class PairingActivity extends Activity implements Reporter {
    private static final String TAG = "PairingActivity";

    private Handler handler = new Handler();

    private List<Card> mCards;
    private CardScrollView mCardScrollView;
    private PairingScrollAdapter mCardScrollAdapter;
    private BlePrefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        Witness.register(DeviceFound.class, this);

        prefs = new BlePrefs(this);
        if (null != prefs.getConnectedDevice()) {
            finish();
            return;
        }

        startService(new Intent(this, BleHeartService.class));
        mCards = new ArrayList<Card>();

        for (String deviceAddress : BleScanner.getDevices().keySet()) {
            Log.d(TAG, "add cards");
            cardGen(
                    BleScanner.getDevices().get(deviceAddress).getDevice());
        }

        mCardScrollView = new CardScrollView(this);
        mCardScrollAdapter = new PairingScrollAdapter(mCards, mCardScrollView, this);
        mCardScrollView.setAdapter(mCardScrollAdapter);

        mCardScrollView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String address = ((Card) mCardScrollAdapter.getItem(position)).getFootnote().toString();
                StoredBluetoothDevice device = BleScanner.getDevices().get(address);
                Witness.notify(new BleConnect(device.getDevice()));
                prefs.setConnectedDevice(device.getDevice());
                startService(new Intent(PairingActivity.this, HeartyLiveCardService.class));
                finish();
            }
        });


        mCardScrollView.activate();

        setContentView(mCardScrollView);
    }

    @Override
    public void onDestroy() {
        Witness.remove(DeviceFound.class, this);
        super.onDestroy();
    }

    private void cardGen(final BluetoothDevice device) {
        if (null == device)
            return;

        Log.d(TAG, "cardGen " + device.getAddress());
        Card c = new Card(this);
        String text = (null == device.getName()) ? device.getAddress() : device.getName();
        c.setText(text);
        c.setFootnote(device.getAddress());

        mCards.add(c);
        mCardScrollAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyEvent(final Object o) {
        handler.post(new Runnable() {
            @Override
            public void run() {

                if (o instanceof DeviceFound) {
                    Log.d(TAG, "DeviceFound");
                    cardGen(((DeviceFound) o).device);
                }

            }
        });
    }
}