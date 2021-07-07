package com.habidhossen.cgpacalculator21;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView CgpaTextView, SerialTextview, CreditTextView, GradeTextView;
    private Button Add, Reset;
    Spinner CreditSpinner, GradeSpinner;

    private int i = 0;
    private double FinalResult;
    private double GradeSum = 0;
    private double CreditSum = 0;
    double gradesValue;
    int creditsValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CgpaTextView = findViewById(R.id.CgpaTextViewID);
        SerialTextview = findViewById(R.id.SerialTextViewId);
        CreditTextView = findViewById(R.id.CreditTextViewId);
        GradeTextView = findViewById(R.id.GradeTextViewId);

        Add = findViewById(R.id.AddBtnId);
        Reset = findViewById(R.id.ResetBtnId);

        CreditSpinner = findViewById(R.id.CreditSpinnerId);
        GradeSpinner = findViewById(R.id.GradeSpinnerId);

        //Spinner create
        final String[] credit = {"Select Credit", "1", "2", "3", "4", "5"};
        final String[] grades = {"Select Grade", "A+ (4.00)", "A (3.75)", "A- (3.50)", "B+ (3.25)", "B (3.00)", "B- (2.75)", "C+ (2.50)", "C (2.25)", "D (2.00)", "F (0.00)"};

        //for credit spinner
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, credit);
        CreditSpinner.setAdapter(adapter1);
        CreditSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {

                String selectedCredit = (String) parent.getItemAtPosition(i);

                if (selectedCredit.equals("1"))
                    creditsValue = 1;
                else if (selectedCredit.equals("2"))
                    creditsValue = 2;
                else if (selectedCredit.equals("3"))
                    creditsValue = 3;
                else if (selectedCredit.equals("4"))
                    creditsValue = 4;
                else if (selectedCredit.equals("5"))
                    creditsValue = 5;
                else {
                    creditsValue = 8;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //for garde spinner
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, grades);
        GradeSpinner.setAdapter(adapter2);
        GradeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {

                String selectedGrade = (String) parent.getItemAtPosition(i);

                if (selectedGrade.equals("A+ (4.00)"))
                    gradesValue = 4.00;
                else if (selectedGrade.equals("A (3.75)"))
                    gradesValue = 3.75;
                else if (selectedGrade.equals("A- (3.50)"))
                    gradesValue = 3.50;
                else if (selectedGrade.equals("B+ (3.25)"))
                    gradesValue = 3.25;
                else if (selectedGrade.equals("B (3.00)"))
                    gradesValue = 3.00;
                else if (selectedGrade.equals("B- (2.75)"))
                    gradesValue = 2.75;
                else if (selectedGrade.equals("C+ (2.50)"))
                    gradesValue = 2.50;
                else if (selectedGrade.equals("C (2.25)"))
                    gradesValue = 2.25;
                else if (selectedGrade.equals("D (2.00)"))
                    gradesValue = 2.00;
                else if (selectedGrade.equals("F (0.00)"))
                    gradesValue = 0;
                else {
                    gradesValue = 8;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Add button listener
        Add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (gradesValue == 8 || creditsValue == 8) {

                    Toast.makeText(MainActivity.this, "Enter Value", Toast.LENGTH_SHORT).show();
                } else {
                    GradeSum = GradeSum + gradesValue * creditsValue;
                    CreditSum = CreditSum + creditsValue;
                    FinalResult = GradeSum / CreditSum;
                    i++;
                    CgpaTextView.setText(String.format("%.2f", FinalResult));
                    SerialTextview.setText(SerialTextview.getText() + "\n" + i + ".");
                    CreditTextView.setText(CreditTextView.getText() + "\n" + creditsValue);
                    GradeTextView.setText(GradeTextView.getText() + "\n" + gradesValue);
                    GradeSpinner.setSelection(0);
                    CreditSpinner.setSelection(0);
                    Toast.makeText(MainActivity.this, "Added", Toast.LENGTH_SHORT).show();
                }
            }

        });

        //Reset button listener
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                i = 0;
                GradeSum = 0;
                CreditSum = 0;
                FinalResult = 0;
                GradeSpinner.setSelection(0);
                CreditSpinner.setSelection(0);
                CgpaTextView.setText("0.00");
                SerialTextview.setText("Course");
                GradeTextView.setText("Grade");
                CreditTextView.setText("Credit");
                Toast.makeText(MainActivity.this, "Reset", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //This coding is for creating menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //This coding is for menu item selection
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        /*if (item.getItemId() == R.id.ShareId) {

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            startActivity(Intent.createChooser(intent, "Share app via"));
        }*/

        if (item.getItemId() == R.id.GradingSystemId) {
            Intent intent = new Intent(MainActivity.this, GradingSystemActivity.class);
            startActivity(intent);

        }
        if (item.getItemId() == R.id.AboutId) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}
