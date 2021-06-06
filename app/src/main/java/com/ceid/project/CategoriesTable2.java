package com.ceid.project;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class CategoriesTable2 extends AppCompatActivity {
    Drawable d;
    JSONObject json = new JSONObject();
    private TableLayout tableLayout;
    private TableRow tableRow;
    Button panel;
    String user="";
    //ArrayList<Button> buttons = new ArrayList<Button>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);

        //textView=(TextView)findViewById(R.id.simpleTextView2);
        tableLayout=(TableLayout)findViewById(R.id.tableLayoutSelection);
        tableRow=(TableRow)findViewById(R.id.tableRow2);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            final String categories = extras.getString("categories");
            final String services = extras.getString("services");
            user = extras.getString("user");
            //The key argument here must match that used in the other activity
            Log.i("Debug categories", categories);
            Log.i("Debug user", user);
            try {
                json = new JSONObject(categories);
                JSONArray servicesjson = json.getJSONArray("categories");

                for (int i=0;i<servicesjson.length();i++){
                    TableRow tableRow1 = new TableRow(this);
                    //tableRow1.setLayoutParams(new TableRow.LayoutParams((TableRow)findViewById(R.id.tableRow)));
                    tableRow1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                    //tableRow.setLa
                    JSONObject c = servicesjson.getJSONObject(i);
                    int categories_id = c.getInt("id");
                    String image = c.getString("image");
                    Log.i("Debug", image);
                    Log.i("Debug", "categories_id "+categories_id);
            /*View tableRow = LayoutInflater.from(this).inflate(R.layout.table_item,null,false);
            ImageView imageView  = (ImageView) tableRow.findViewById(R.id.tv_no);*/
                    InputStream is = null;
                    try {
                        is = (InputStream) new URL(image).getContent();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    d = Drawable.createFromStream(is, "src name");
                    //imageView.setImageResource(R.drawable.d);

                    //tableLayout.addView(tableRow);
                    final Button btn1 = new Button(this);
                    //btn1.setText("Button_text");
                    btn1.setBackground(d);
                    //btn1.setWidth();
                    btn1.setHeight(500);
                    //btn1.set


                    btn1.setId(categories_id);
                    //textView.setText("fff");
                    //tableRow.addView(btn1);
                    //tableRow.addView(btn1);
                    tableLayout.addView(tableRow1);
                    //tableLayout.setPadding(0,5,0,50);
                    btn1.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v) {
                            int category_id=btn1.getId();
                            Intent intent = new Intent(getApplicationContext(), OffersTable.class);
                            intent.putExtra("category", "{\"category\":"+category_id+"}");
                            intent.putExtra("services", services);
                            startActivity(intent);

                        }
                    });
                }
                //Log.i("Debug", image);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        panel = (Button) findViewById(R.id.panel);
        panel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), PersonalPanel.class);
                intent.putExtra("user", user);
                startActivity(intent);

            }
        });

    }
}