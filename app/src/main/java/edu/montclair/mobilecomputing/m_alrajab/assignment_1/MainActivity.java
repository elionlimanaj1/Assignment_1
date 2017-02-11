package edu.montclair.mobilecomputing.m_alrajab.assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_forgot;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Add Listeners to buttons
        btn_forgot = (Button) findViewById(R.id.btn_forget_password_MA);
        btn_forgot.setOnClickListener(new MyListener());
        btn_register = (Button) findViewById(R.id.btn_register_MA);
        btn_register.setOnClickListener(new MyListener());
    }

    class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //Create a different Intent according to the button clicked
            if (view.getId() == btn_forgot.getId()) {
                Intent intent = new Intent(MainActivity.this, ForgetPassword.class);
                startActivity(intent);
            } else if (view.getId() == R.id.btn_register_MA) {
                Intent intent = new Intent(MainActivity.this, RegistrationPage.class);
                startActivity(intent);
            }
        }
    }
}