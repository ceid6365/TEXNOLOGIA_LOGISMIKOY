package com.ceid.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class ServiceSelection extends AppCompatActivity {
    JSONObject json = new JSONObject();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkbox);
        CheckBox CheckBox6 = (CheckBox) findViewById(R.id.checkBox6);
        //CheckBox6.setText("test");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            final String subcategory = extras.getString("subcategory");
            System.out.println(subcategory);
            RequestHandler requestHandler = new RequestHandler();
            try {
                json = new JSONObject(subcategory);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String subcat= null;
            try {
                subcat = json.getString("subcategory");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println("subcat "+subcat);
            JSONObject json = new JSONObject();
            //creating request parameters
            HashMap<String, String> params = new HashMap<>();
            params.put("subcategories", subcat);
            params.put("type", "service");

            //returing the response
            String returnValue = requestHandler.sendPostRequest(params);
            System.out.println(" returnValue "+returnValue);
            String test="R.id.checkBox1";

            try {
                json = new JSONObject(returnValue);
                JSONArray servicesjson = json.getJSONArray("categories");
                for (int i = 0; i < servicesjson.length(); i++) {
                    JSONObject c = servicesjson.getJSONObject(i);
                    String decr = c.getString("decr");
                    int id1 = c.getInt("id");
                    System.out.println("decr "+decr);
                    CheckBox CheckBox;
                    switch(id1) {
                        case 1:
                            CheckBox = (CheckBox) findViewById(R.id.checkBox1);
                            CheckBox.setText(decr);
                            break;
                        case 2:
                            CheckBox = (CheckBox) findViewById(R.id.checkBox2);
                            CheckBox.setText(decr);
                            break;
                        case 3:
                            CheckBox = (CheckBox) findViewById(R.id.checkBox3);
                            CheckBox.setText(decr);
                            break;
                        case 4:
                            CheckBox = (CheckBox) findViewById(R.id.checkBox4);
                            CheckBox.setText(decr);
                            break;
                        case 5:
                            CheckBox = (CheckBox) findViewById(R.id.checkBox5);
                            CheckBox.setText(decr);
                            break;
                        case 6:
                            CheckBox = (CheckBox) findViewById(R.id.checkBox6);
                            CheckBox.setText(decr);
                            break;


                    }
                    //CheckBox CheckBox = (CheckBox) findViewById(R.id.checkBox1);
                    //CheckBox.setText(decr);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        final String[] servicesString = {""};
        Button okbutton;
        okbutton = (Button) findViewById(R.id.buttonok);
        okbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout layout = (RelativeLayout)findViewById(R.id.RelativeLayout);
                for (int i = 0; i < layout.getChildCount(); i++) {
                    v = layout.getChildAt(i);
                    if (v instanceof CheckBox) {
                        if (((CheckBox) v).isChecked()){
                            String text = String.valueOf(((CheckBox) v).getText());

                            servicesString[0] = servicesString[0] +" "+ text;

                        }

                    }

                }
                System.out.println(servicesString[0]);
                //Log.d("@@@@@@@@@" , "somethinng" +text);
                Intent intent = new Intent(getApplicationContext(), RandevouActivity.class);
                intent.putExtra("subcategory", "{\"subcategory\":"+servicesString[0]+"}");
                //intent.putExtra("services", services);
                startActivity(intent);
            }
        });
    }
}
