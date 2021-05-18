package com.ceid.project;

import android.content.Intent;
import android.os.StrictMode;
import android.util.Log;
import android.util.Patterns;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity {

    JSONArray services;
    JSONArray categories;
    JSONObject user;
    EditText email, password;
    Button login;
    TextView register,messageView;
    boolean isEmailValid, isPasswordValid;
    TextInputLayout emailError, passError;
    String message="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            message = extras.getString("services");
            Log.i( "Debug",message);
            messageView=(TextView) findViewById(R.id.message);
            messageView.setText(message);
            messageView.setVisibility(View.VISIBLE);
        }
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);

        emailError = (TextInputLayout) findViewById(R.id.emailError);
        passError = (TextInputLayout) findViewById(R.id.passError);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetValidation();
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("email", email.getText().toString());
                params.put("password", password.getText().toString());
                params.put("type", "login");

                //returing the response
                String returnValue=requestHandler.sendPostRequest(params);
                Log.i( "Debug",returnValue);
                try {

                    JSONObject jsonObj = new JSONObject(returnValue);
                    String error = jsonObj.getString("error");
                    Log.i("Debug", error);
                    if (error.equals("true")) {
                        Toast.makeText(getApplicationContext(), "Email or Password is incorrect", Toast.LENGTH_SHORT).show();
                    } else {

                        categories = jsonObj.getJSONArray("categories");
                        services = jsonObj.getJSONArray("services");
                        user= jsonObj.getJSONObject("user");
                        //services.toString();
                        //JSONObject c = services.getJSONObject(0);
                        //String image = c.getString("image");
                        Log.i("Debug", services.toString());
                        Log.i("Debug", categories.toString());
                        Intent intent = new Intent(getApplicationContext(), CategoriesTable.class);
                        intent.putExtra("categories", "{\"categories\":"+categories.toString()+"}");
                        intent.putExtra("services", "{\"services\":"+services.toString()+"}");
                        intent.putExtra("user", user.toString());
                        startActivity(intent);
                        finish();
                    }
                    } catch( final JSONException e){
                        Log.e("Debug", "Json parsing error: " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Json parsing error: " + e.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        });

                    }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);

            }
        });
        /*test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), ServiceTable.class);
                intent.putExtra("services", "\"services\":"+services.toString());
                startActivity(intent);
            }
        });*/
    }

    public void SetValidation() {
        // Check for a valid email address.
        if (email.getText().toString().isEmpty()) {
            emailError.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            emailError.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else  {
            isEmailValid = true;
            emailError.setErrorEnabled(false);
        }

        // Check for a valid password.
        if (password.getText().toString().isEmpty()) {
            passError.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (password.getText().length() < 6) {
            passError.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
            passError.setErrorEnabled(false);
        }

        if (isEmailValid && isPasswordValid) {
            Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
        }

    }

}