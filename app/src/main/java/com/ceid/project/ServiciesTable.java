package com.ceid.project;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ServiciesTable extends AppCompatActivity {
    Drawable d;
    JSONObject json = new JSONObject();
    private TableLayout tableLayout; //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_service);

        tableLayout=(TableLayout)findViewById(R.id.tableLayoutSelection);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String services = extras.getString("services");
            String category = extras.getString("category");
            //The key argument here must match that used in the other activity
            Log.i("Debug", services);
            Log.i("Debug", category);
            try {
                json = new JSONObject(category);
                //JSONObject categoryjson = json.getJSONObject("category");
                int category_id = json.getInt("category");
                System.out.println("category_id " + category_id);

                json = new JSONObject(services);
                JSONArray servicesjson = json.getJSONArray("services");

                for (int i = 0; i < servicesjson.length(); i++) {
                    JSONObject c = servicesjson.getJSONObject(i);
                    int Idcategory = c.getInt("categories");
                    if (Idcategory == category_id) {
                        String image = c.getString("image");
                        Log.i("Debug", image);
            /*View tableRow = LayoutInflater.from(this).inflate(R.layout.table_item,null,false);
            ImageView imageView  = (ImageView) tableRow.findViewById(R.id.tv_no);*/
                        InputStream is = null;
                        try {
                            is = (InputStream) new URL(image).getContent();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        d = Drawable.createFromStream(is, "src name");
            /*imageView.setImageResource(R.drawable.d);

            tableLayout.addView(tableRow);*/


                        Button btn1 = new Button(this);
                        //btn1.setText("Button_text");
                        btn1.setBackground(d);
                        btn1.setPadding(20, 20, 20, 40);
                        tableLayout.addView(btn1);

                        btn1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // put code on click operation
                            }
                        });
                    }
                }
                    //Log.i("Debug", image);
                } catch(JSONException e){
                    e.printStackTrace();
                }

        }
    }
}