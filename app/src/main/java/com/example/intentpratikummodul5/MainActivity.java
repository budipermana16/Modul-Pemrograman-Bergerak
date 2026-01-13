package com.example.intentpratikummodul5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private double input1 = Double.NaN;
    private double input2;
    private char currentOp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);

        // Menghubungkan semua tombol angka
        int[] numericButtons = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDot};
        View.OnClickListener numListener = v -> {
            Button b = (Button) v;
            if (tvDisplay.getText().toString().equals("0")) tvDisplay.setText(b.getText());
            else tvDisplay.append(b.getText());
        };
        for (int id : numericButtons) findViewById(id).setOnClickListener(numListener);

        // Tombol Operasi
        findViewById(R.id.btnAdd).setOnClickListener(v -> prepareOp('+'));
        findViewById(R.id.btnSub).setOnClickListener(v -> prepareOp('-'));
        findViewById(R.id.btnMult).setOnClickListener(v -> prepareOp('*'));
        findViewById(R.id.btnDiv).setOnClickListener(v -> prepareOp('/'));

        findViewById(R.id.btnEqual).setOnClickListener(v -> calculate());
        findViewById(R.id.btnC).setOnClickListener(v -> {
            input1 = Double.NaN;
            tvDisplay.setText("0");
        });
        findViewById(R.id.btnDel).setOnClickListener(v -> {
            String str = tvDisplay.getText().toString();
            if (str.length() > 1) tvDisplay.setText(str.substring(0, str.length() - 1));
            else tvDisplay.setText("0");
        });
    }

    private void prepareOp(char op) {
        input1 = Double.parseDouble(tvDisplay.getText().toString());
        currentOp = op;
        tvDisplay.setText("0");
    }

    private void calculate() {
        if (!Double.isNaN(input1)) {
            input2 = Double.parseDouble(tvDisplay.getText().toString());
            switch (currentOp) {
                case '+': input1 += input2; break;
                case '-': input1 -= input2; break;
                case '*': input1 *= input2; break;
                case '/': input1 /= input2; break;
            }
            tvDisplay.setText(String.valueOf(input1));
            input1 = Double.NaN;
        }
    }
}