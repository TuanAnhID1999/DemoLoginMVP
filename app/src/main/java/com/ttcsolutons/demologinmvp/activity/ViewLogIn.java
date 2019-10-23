package com.ttcsolutons.demologinmvp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ttcsolutons.demologinmvp.presenter.PresenterLogin;
import com.ttcsolutons.demologinmvp.R;
import com.ttcsolutons.demologinmvp.model.ViewLoginListener;

public class ViewLogIn extends AppCompatActivity implements ViewLoginListener {
    EditText edUS, edPW;
    Button btnLogin;
    PresenterLogin loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        init();
    }

    public void init(){
        edUS = findViewById(R.id.input_email);
        edPW = findViewById(R.id.input_password);
        btnLogin = findViewById(R.id.btn_login);
        loginPresenter = new PresenterLogin(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUS.getText().toString();
                String password = edPW.getText().toString();
                loginPresenter.receiverLogin(username,password);
            }
        });
    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(this, ActivitySecon.class);
        startActivity(intent);
    }

    @Override
    public void loginFailed() {
        Toast.makeText(this,"Tài khoản or mật khâu không đồng vui lòng thử lại!", Toast.LENGTH_LONG).show();
    }
}
