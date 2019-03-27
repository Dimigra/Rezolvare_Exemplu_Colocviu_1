package ro.pub.cs.systems.eim.practicaltest01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01SecondaryActivity extends AppCompatActivity {

    EditText text3 = null;

    Button button3 = null;
    Button button4 = null;

    private my_ButtonClickListener my_buttonClickListener = new my_ButtonClickListener();
    private class my_ButtonClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button3:
                    setResult(RESULT_OK, null);
                    finish();
                    break;
                case R.id.button4:
                    setResult(RESULT_CANCELED, null);
                    finish();
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_secondary);

        text3 = (EditText)findViewById(R.id.text3);

        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);

        button3.setOnClickListener(my_buttonClickListener);
        button4.setOnClickListener(my_buttonClickListener);

        Intent intent = getIntent();
        int data1 = intent.getIntExtra("value1", -1);
        int data2 = intent.getIntExtra("value2", -1);
        int value = data1 + data2;
        text3.setText(String.valueOf(value));
    }
}
