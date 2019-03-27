package ro.pub.cs.systems.eim.practicaltest01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01MainActivity extends AppCompatActivity {

    EditText text1 = null;
    EditText text2 = null;

    Button button0 = null;
    Button button1 = null;
    Button button2 = null;

    /* [B1] Button Listener */
    private my_ButtonClickListener my_buttonClickListener = new my_ButtonClickListener();
    private class my_ButtonClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            int value1 = Integer.parseInt(text1.getText().toString());
            int value2 = Integer.parseInt(text2.getText().toString());
            switch (view.getId()) {
                case R.id.button0:
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
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_main);

        text1 = (EditText)findViewById(R.id.text1);
        text2 = (EditText)findViewById(R.id.text2);

        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);

        /* [B1] Button Listener */
        button0.setOnClickListener(my_buttonClickListener);
        button1.setOnClickListener(my_buttonClickListener);
        button2.setOnClickListener(my_buttonClickListener);
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
}
