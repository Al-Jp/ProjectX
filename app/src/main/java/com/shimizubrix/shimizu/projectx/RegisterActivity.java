package com.shimizubrix.shimizu.projectx;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText edtFirstName = (EditText) findViewById(R.id.edtFirstName);
        final EditText edtLastName = (EditText) findViewById(R.id.edtLastName);
        final EditText edtEmail = (EditText) findViewById(R.id.edtEmail);
        final EditText edtUsername = (EditText) findViewById(R.id.edtUsername);
        final EditText edtPassword = (EditText) findViewById(R.id.edtPassword);
        final EditText edtPasswordConfirm = (EditText) findViewById(R.id.edtConfirmPassword);

        final String password = edtPassword.getText().toString().trim();
        final String passwordConfirm = edtPasswordConfirm.getText().toString().trim();

        Button btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allFieldsComplete(edtFirstName, edtLastName, edtEmail, edtUsername, edtPassword) || passwordsIdentical(password, passwordConfirm)) {
                    String firstName = edtFirstName.getText().toString();
                    String lastName = edtLastName.getText().toString();
                    String email = edtEmail.getText().toString();
                    String username = edtUsername.getText().toString();
                    String password = edtPassword.getText().toString();

                    Response.Listener<String> listener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if (success) {
                                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                } else {
                                    Snackbar.make(findViewById(R.id.coordinatorLayoutRegister), "Registration failed!", Snackbar.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    RegisterRequest registerRequest = new RegisterRequest(firstName, lastName, email, username, password, listener);
                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(registerRequest);
                } else {
                    Snackbar.make(findViewById(R.id.coordinatorLayoutRegister), "Incomplete fields/passwords do not match!", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean allFieldsComplete(EditText edtFirstName, EditText edtLastName, EditText edtEmail,
                                      EditText edtUsername, EditText edtPassword) {
      return !(edtFirstName.getText().toString().trim().isEmpty() || edtLastName.getText().toString().trim().isEmpty() || edtEmail.getText().toString().trim().isEmpty() ||
                edtUsername.getText().toString().trim().isEmpty() || edtPassword.getText().toString().trim().isEmpty());

    }

    private boolean passwordsIdentical(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
