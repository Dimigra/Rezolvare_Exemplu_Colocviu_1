package ro.pub.cs.systems.eim.practicaltest01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01MainActivity extends AppCompatActivity {

    EditText text1 = null;
    EditText text2 = null;
    EditText textDebug = null;

    Button button0 = null;
    Button button1 = null;
    Button button2 = null;

    /* [C] */
    private final static int secondActivityCode = 1;

    /* [D1] */
    private boolean serviceRunning = false;
    private final static int NUMBER_OF_CLICKS_THRESHOLD = 10;

    /* [D2] */
    private IntentFilter startedServiceIntentFilter;
    private StartedServiceBroadcastReceiver startedServiceBroadcastReceiver;
    private class StartedServiceBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            textDebug.setText(intent.getStringExtra("message"));
        }
    }

    /* [B1] Button Listener */
    private my_ButtonClickListener my_buttonClickListener = new my_ButtonClickListener();
    private class my_ButtonClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            int value1 = Integer.parseInt(text1.getText().toString());
            int value2 = Integer.parseInt(text2.getText().toString());
            switch (view.getId()) {
                case R.id.button0:
                    /* [C1] Start Second Activity */
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01SecondaryActivity.class);
                    intent.putExtra("value1", value1);
                    intent.putExtra("value2", value2);
                    startActivityForResult(intent, secondActivityCode);
                    break;
                case R.id.button1:
                    value1++;
                    text1.setText(String.valueOf(value1));
                    break;
                case R.id.button2:
                    value2++;
                    text2.setText(String.valueOf(value2));
                    break;
            }

            /* [D1] Call service */
            if (value1 + value2 > NUMBER_OF_CLICKS_THRESHOLD && serviceRunning == false) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Service.class);
                intent.putExtra("val1", value1);
                intent.putExtra("val2", value2);
                getApplicationContext().startService(intent);
                serviceRunning = true;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_main);

        text1 = (EditText)findViewById(R.id.text1);
        text2 = (EditText)findViewById(R.id.text2);
        textDebug = (EditText)findViewById(R.id.textDebug);

        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);

        /* [B1] Button Listener */
        button0.setOnClickListener(my_buttonClickListener);
        button1.setOnClickListener(my_buttonClickListener);
        button2.setOnClickListener(my_buttonClickListener);

        /* [D2] Filters for receiving broadcast */
        startedServiceBroadcastReceiver = new StartedServiceBroadcastReceiver();
        startedServiceIntentFilter = new IntentFilter();
        startedServiceIntentFilter.addAction(String.valueOf(0));
        startedServiceIntentFilter.addAction(String.valueOf(1));
        startedServiceIntentFilter.addAction(String.valueOf(2));
    }

    /* [B2] Save text */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString(String.valueOf(R.id.text1), text1.getText().toString());
        savedInstanceState.putString(String.valueOf(R.id.text2), text2.getText().toString());
    }

    /* [B2] Restore text */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey(String.valueOf(R.id.text1))) {
            text1.setText(savedInstanceState.getString(String.valueOf(R.id.text1)));
        }
        if (savedInstanceState.containsKey(String.valueOf(R.id.text2))) {
            text2.setText(savedInstanceState.getString(String.valueOf(R.id.text2)));
        }
    }

    /* [C1] Get Second Activity result */
    /* [ACTIVITY] Print the result of the child activity */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == secondActivityCode) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

    /* [D1] Close service */
    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Service.class);
        stopService(intent);
        super.onDestroy();
    }

    /* [D2] */
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(startedServiceBroadcastReceiver, startedServiceIntentFilter);
    }

    /* [D2] */
    @Override
    protected void onPause() {
        unregisterReceiver(startedServiceBroadcastReceiver);
        super.onPause();
    }
}
