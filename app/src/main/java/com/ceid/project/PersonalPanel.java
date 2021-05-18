package com.ceid.project;

import android.content.Intent;
import android.util.Log;
import android.util.Patterns;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class PersonalPanel extends AppCompatActivity {

    EditText name, email, password,lastname,username,bio;
    Button update;
    TextView login,messageView;
    boolean isNameValid, isEmailValid, isPhoneValid, isPasswordValid;
    TextInputLayout nameError, emailError, passError;
    Spinner rank;
    String emailString="";
    JSONObject jsonObj = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_panel);

        name = (EditText) findViewById(R.id.name);
        lastname = (EditText) findViewById(R.id.lastname);
        username= (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        bio = (EditText) findViewById(R.id.bio);
        login = (Button) findViewById(R.id.login);
        update = (Button) findViewById(R.id.update);
        nameError = (TextInputLayout) findViewById(R.id.nameError);
        emailError = (TextInputLayout) findViewById(R.id.emailError);

        passError = (TextInputLayout) findViewById(R.id.passError);
        Bundle extras = getIntent().getExtras();
        Log.i("Debug", extras.toString());
        if (extras != null) {
            final String userString = extras.getString("user");
            Log.i("Debug", userString);

            try {
                jsonObj = new JSONObject(userString);
                //Log.i("Debug", jsonObj.toString());
                String nameString = jsonObj.getString("name");
                Log.i("Debug", nameString);
                String lastnameString = jsonObj.getString("lastname");
                String usernameString = jsonObj.getString("username");
                emailString=jsonObj.getString("email");
                name.setText(nameString);
                lastname.setText(lastnameString);
                username.setText(usernameString);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetValidation();
                RequestHandler requestHandler = new RequestHandler();
                //System.out.println(rank.getSelectedItem().toString());
                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                //params.put("email", email.getText().toString());
                params.put("password", password.getText().toString());
                params.put("name", name.getText().toString());
                params.put("lastname", lastname.getText().toString());
                params.put("username", username.getText().toString());
                params.put("bio", bio.getText().toString());
                params.put("email", emailString);
                //params.put("rank", "1");
                params.put("type", "update");

                //returing the response
                String returnValue = requestHandler.sendPostRequest(params);
                Log.i( "Debug",returnValue);
                try {

                    JSONObject jsonObj = new JSONObject(returnValue);
                    String error = jsonObj.getString("error");
                    String message = jsonObj.getString("error");
                    Log.i("Debug", error);
                    if (error.equals("true")) {
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    } else {

                        messageView = (TextView) findViewById(R.id.message);
                        messageView.setVisibility(View.VISIBLE);
                    }
                    Log.i("Debug", returnValue);
                } catch (
                        JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to LoginActivity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void SetValidation() {
        // Check for a valid name.
        if (name.getText().toString().isEmpty()) {
            nameError.setError(getResources().getString(R.string.name_error));
            isNameValid = false;
        } else  {
            isNameValid = true;
            nameError.setErrorEnabled(false);
        }




        // Check for a valid password.
        /*if (password.getText().toString().isEmpty()) {
            passError.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (password.getText().length() < 6) {
            passError.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
            passError.setErrorEnabled(false);
        }

        if (isNameValid && isEmailValid && isPhoneValid && isPasswordValid) {
            Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
        }
        */
    }

}