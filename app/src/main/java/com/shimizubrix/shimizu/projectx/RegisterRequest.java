package com.shimizubrix.shimizu.projectx;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by shimizu on 4/18/16.
 */
public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://shimigiran.comli.com/user_register.php";
    private Map<String, String> params;

    public RegisterRequest(String firstName, String lastName, String email, String username, String password, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("firstname", firstName);
        params.put("lastname", lastName);
        params.put("email", email);
        params.put("username", username);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
