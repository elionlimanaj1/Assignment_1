package edu.montclair.mobilecomputing.m_alrajab.assignment_1;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegistrationPage extends AppCompatActivity {
    private EditText editText_DoB;
    private String[] majorItems;
    private ListView listView;
    Calendar myCalendar = Calendar.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        //Filling the ListView with the pre-defined list of majors
        majorItems = getResources().getStringArray(R.array.major_list);
        listView = (ListView) findViewById(R.id.Reg_MajorList);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, majorItems));

        listView.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        editText_DoB = (EditText) findViewById(R.id.Reg_DoB);
        editText_DoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To show current date in the DatePicker
                new DatePickerDialog(RegistrationPage.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            //To set selected date to EditText_DoB
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    private void updateLabel() {
        //To format the date and write it to EditText_DoB
        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editText_DoB.setText(sdf.format(myCalendar.getTime()));
    }

    public void register(View view) {
        //When Register button is clicked validate Password and Email
        EditText textPassword = (EditText) findViewById(R.id.Reg_pass1);
        if (textPassword.getText().length() < 6)
            textPassword.setError(getString(R.string.error_password));
        EditText textEmail = (EditText) findViewById(R.id.Reg_Email);
        if (!textEmail.getText().toString().contains("montclair.edu")) {
            textEmail.setError(getString(R.string.error_email));
        }
    }
}