package com.example.hm1application;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerOpp;
    private Button btnCalc;
    private TextView txtResult;
    private String selectedOperation = "+";
    private EditText num1;
    private EditText num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spinnerOpp = findViewById(R.id.spinnerOpp);
        btnCalc = findViewById(R.id.btnCalc);
        txtResult = findViewById(R.id.txtResult);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);

        spinnerOpp.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"+", "-", "*", "**", "/"}));
        spinnerOpp.setSelection(0);
        spinnerOpp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedOperation = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
    }

    private void calculate() {
        String num1Str = num1.getText().toString();
        String num2Str = num2.getText().toString();

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            txtResult.setText("Enter both numbers");
            return;
        }

        int num1Value, num2Value;
        try {
            num1Value = Integer.parseInt(num1Str);
            num2Value = Integer.parseInt(num2Str);
        } catch (NumberFormatException e) {
            txtResult.setText("Invalid number format");
            return;
        }
        try{
        int result = 0;
        switch (selectedOperation) {
            case "+":
                result = num1Value + num2Value;
                break;
            case "-":
                result = num1Value - num2Value;
                break;
            case "*":
                result = num1Value * num2Value;
                break;
            case "**":
                result = (int) Math.pow(num1Value, num2Value);
                break;
            case "/":
                if (num2Value == 0) {
                    txtResult.setText("ERROR - divide by 0");
                    return;
                }
                result = num1Value / num2Value;
                break;
        }
            txtResult.setText("Result: " + String.valueOf(result));

        }catch(Exception er) {
            txtResult.setText("ERROR");
            return;
        }

    }

}
