package com.example.webwerks.neostore.login.forgotPassword;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.webwerks.neostore.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPasswordActivity extends Activity implements forgotPassView {
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.btnReset)
    Button reset;
    String email;
    ForgotPassPresenter fpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        email= getEmail();
        fpPresenter = new ForgotPassImplementation(ForgotPasswordActivity.this);
    }

    private String getEmail() {
       return et_email.getText().toString().trim();
    }
    @OnClick(R.id.btnReset)
    public void BtnResetClick(){
        fpPresenter.SendEmail(email);


    }


    @Override
    public void loginSuccess(String userMsg) {
        Toast.makeText(getApplicationContext(),userMsg,Toast.LENGTH_LONG).show();

    }

    @Override
    public void loginValidations() {

    }

    @Override
    public void loginError(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

    }

    @Override
    public void startNewActivity() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fpPresenter.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        fpPresenter.onBackPRessed();
    }
}
