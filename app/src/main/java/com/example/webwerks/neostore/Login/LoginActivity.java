package com.example.webwerks.neostore.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webwerks.neostore.Dashboard.DashboardActivity;
import com.example.webwerks.neostore.Login.forgotPassword.ForgotPasswordActivity;
import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.SignUp.SignUpActivity;

import com.example.webwerks.neostore.Utils.SPManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends Activity implements LoginView {
    @BindView(R.id.img_signUp)
    ImageView img_signUp;
    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.txt_signup)
    TextView txt_signup;
    @BindView(R.id.forgot_password)
    TextView forgot_password;
    LoginPresenter loginPresenter;
    String username, password;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!firstTimeCheck()) {
            setContentView(R.layout.activity_login);
            ButterKnife.bind(this);
            handler = new Handler(LoginActivity.this.getMainLooper());
            loginPresenter = new LoginPresenterImplementation(LoginActivity.this
                    , SPManager.getInstance(this)
            );
            } else {
                Intent intent = new Intent(this, DashboardActivity.class);
                startActivity(intent);
            }
    }

    private boolean firstTimeCheck() {
        return SPManager.getInstance(this).retriveBool("is_active");
    }

    private void getDataFromEditText() {
        username = et_username.getText().toString().trim();
        password = et_password.getText().toString().trim();
    }

    @OnClick(R.id.forgot_password)
    void forgot_password_Click() {
        forgotPassword();

    }
    @OnClick(R.id.img_signUp)
    void imgSignUpClick() {
        performSignUp();
    }
    @OnClick(R.id.txt_signup)
    void txtSignUpClick() {
        performSignUp();
    }

    @OnClick(R.id.btnLogin)
    void btnLoginClick() {
        getDataFromEditText();
        loginValidations();
        loginPresenter.preformLogin(username, password);
    }

    @Override
    public void loginSuccess(String userMsg) {
        Toast.makeText(getApplicationContext(), userMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void loginValidations() {
        if (username.equals("")) {
            et_username.setError("Enter Username");
            et_username.requestFocus();
            return;
        } else if (password.equals("")) {
            et_password.setError("Enter Password");
            et_password.requestFocus();
            return;
        }
    }

    @Override
    public void performSignUp() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void forgotPassword() {
        Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void startNewActivity() {
        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
