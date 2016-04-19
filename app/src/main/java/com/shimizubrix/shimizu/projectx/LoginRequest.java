package com.shimizubrix.shimizu.projectx;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by shimizu on 4/19/16.
 */
public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://shimigiran.comli.com/user_login.php";
    Map<String, String> params;

    LoginRequest(String username, String password, Response.Listener listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
