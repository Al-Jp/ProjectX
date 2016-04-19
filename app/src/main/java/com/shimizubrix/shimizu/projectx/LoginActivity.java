package com.shimizubrix.shimizu.projectx;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnSignin;
    private TextView txtRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);

        btnSignin = (Button) findViewById(R.id.buttonSignIn);
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fieldsComplete(edtUsername, edtPassword)) {
                    String username = edtUsername.getText().toString();
                    String password = edtPassword.getText().toString();

                    Response.Listener<String> listener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");
                                if (success) {
                                    String firstname = jsonObject.getString("firstname");
                                    String lastname = jsonObject.getString("lastname");
                                    String username = jsonObject.getString("username");
                                    String email = jsonObject.getString("email");

                                    SharedPreferences mPrefs = getSharedPreferences("USER_INFO", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = mPrefs.edit();
                                    editor.putString("firstname", firstname);
                                    editor.putString("lastname", lastname);
                                    editor.putString("username", username);
                                    editor.putString("email", email);
                                    editor.commit();

                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                } else {
                                    //TODO: when auth fails, alert user for incorrect username and/or password, or no registered account.
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    LoginRequest request = new LoginRequest(username, password, listener);
                    RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                    queue.add(request);
                } else {
                    Snackbar.make(findViewById(R.id.coordinatorLayoutLogin), "Incomplete fields", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean fieldsComplete(EditText edtUsername, EditText edtPassword) {
        return  !(edtUsername.getText().toString().trim().isEmpty() || edtUsername.getText().toString().trim().equals("") || edtPassword.getText().toString().isEmpty() ||
                edtPassword.getText().toString().trim().equals(""));
    }
}
