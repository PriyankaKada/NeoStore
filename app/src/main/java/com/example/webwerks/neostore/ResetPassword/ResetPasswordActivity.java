package com.example.webwerks.neostore.ResetPassword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.Utils.SPManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPasswordActivity extends AppCompatActivity implements ResetPasswordView {
    @BindView(R.id.current_password)
    EditText current_password;
    @BindView(R.id.et_password)
    EditText new_password;
    @BindView(R.id.btnReset)
    Button btnReset;
    @BindView(R.id.Confirm_password)
    EditText conf_password;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.imgback)
    ImageView imgback;
    ResetPasswordPresenter resetPasswordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
        toolbar_title.setText("Reset Password");
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        resetPasswordPresenter = new ResetPasswordPresenterImplementation(ResetPasswordActivity.this);


    }

    @OnClick(R.id.btnReset)
    public void btn_resetClick() {
        resetPasswordPresenter.changePassword(SPManager.getInstance(getApplicationContext()).retriveString("access_token"),
                current_password.getText().toString().trim(),
                new_password.getText().toString().trim(),
                conf_password.getText().toString().trim());
    }

    @Override
    public void showSuccess(String userMsg) {
        Toast.makeText(ResetPasswordActivity.this, userMsg, Toast.LENGTH_LONG).show();
    }
}
