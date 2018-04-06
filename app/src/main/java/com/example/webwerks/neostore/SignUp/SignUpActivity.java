package com.example.webwerks.neostore.SignUp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.webwerks.neostore.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends Activity implements SignupView {
    @BindView(R.id.name)
    EditText et_name;
    @BindView(R.id.last_name)
    EditText et_last_name;
    @BindView(R.id.email)
    EditText et_email;
    @BindView(R.id.password)
    EditText et_password;
    @BindView(R.id.Confirm_password)
    EditText et_Confirm_password;
    @BindView(R.id.radioGroup)
    RadioGroup radio_group;
    @BindView(R.id.phone_number)
    EditText et_phone_number;
    @BindView(R.id.terms_and_conditions)
    CheckBox terms_and_condtions;
    @BindView(R.id.btn_signup)
    Button btn_signup;
    String name, email, last_name, password, conf_password, gender;
    String TAG = "SignupActivity";
    int phone_no;
    SignupPresenter signUpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        signUpPresenter = new SignUpPresenterImplementation(SignUpActivity.this);
    }

    private void getDatafromEdittext() {
        name = et_name.getText().toString().trim();
        last_name = et_last_name.getText().toString().trim();
        email = et_email.getText().toString().trim();
        password = et_password.getText().toString().trim();
        conf_password = et_Confirm_password.getText().toString().trim();
        try {
            phone_no = Integer.parseInt(et_phone_number.getText().toString().trim());

        } catch (NumberFormatException ex) {
            // handle your exception
            Log.d(TAG, "getDatafromEdittext: " + ex.getMessage());
        }
        gender = getGender();

    }

    private String getGender() {
        // find the radiobutton by returned id
        String gender_temp = ((RadioButton) findViewById(radio_group.getCheckedRadioButtonId())).getText().toString();
        Log.d(TAG, "Gender: " + gender);
        if (gender_temp.equals("Male")) {
            return "M";
        } else {
            return "F";
        }
    }

    @OnClick(R.id.btn_signup)
    void clickSignup() {
      performSignUp();
    }

    @Override
    public void validateData() {
        if (name.equals("")) {
            et_name.setError("Please Enter the name");
            et_name.requestFocus();
            return;
        } else if (last_name.equals("")) {
            et_last_name.setError("Please Enter the Last Name");
            et_last_name.requestFocus();
            return;
        } else if (email.equals("")) {
            et_email.setError("Please Enter the Email");
            et_email.requestFocus();
            return;
        } else if (password.equals("")) {
            et_password.setError("Please Enter the password");
            et_password.requestFocus();
            return;
        } else if (conf_password.equals("")) {
            et_Confirm_password.setError("Please Re enter password");
            et_Confirm_password.requestFocus();
            return;
        } else if (!password.equals(conf_password)) {
            et_Confirm_password.setError("Password Does not Match");
            et_Confirm_password.requestFocus();
            return;
        } else if (!terms_and_condtions.isChecked()) {
            Toast.makeText(this, "Please Accept Terms and Condition", Toast.LENGTH_LONG).show();
           return;
        }

    }

    @Override
    public void performSignUp() {
        getDatafromEdittext();
        signUpPresenter.preformSignUp(name, last_name, email, password,conf_password, gender, phone_no);
        finish();
    }

    @Override
    public void success(String message) {
        Toast.makeText(SignUpActivity.this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void error(String message) {
        Toast.makeText(SignUpActivity.this,message,Toast.LENGTH_LONG).show();

    }
}
