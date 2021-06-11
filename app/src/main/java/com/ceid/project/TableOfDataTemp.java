package com.ceid.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
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
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class TableOfDataTemp extends AppCompatActivity {

    private TableLayout mTableLayout;
    ProgressDialog mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_main);

        //mProgressBar = new ProgressDialog(this);

        // setup the table
        mTableLayout = (TableLayout) findViewById(R.id.tableInvoices);

        mTableLayout.setStretchAllColumns(true);


        try {
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData() throws IOException {

        int leftRowMargin = 0;
        int topRowMargin = 0;
        int rightRowMargin = 0;
        int bottomRowMargin = 0;
        int textSize = 0, smallTextSize = 0, mediumTextSize = 0;
        String user = "";
        JSONObject json = new JSONObject();
        textSize = (int) getResources().getDimension(R.dimen.font_size_verysmall);
        smallTextSize = (int) getResources().getDimension(R.dimen.font_size_small);
        mediumTextSize = (int) getResources().getDimension(R.dimen.font_size_medium);
        String image= null,decription = null,count= null;
        int categories_id=0;


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        //int rows = 20;
        //getSupportActionBar().setTitle("Invoices (" + String.valueOf(rows) + ")");
        TextView textSpacer = null;

        mTableLayout.removeAllViews();

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

                for (int i = -1; i < servicesjson.length(); i++) {
                    TableRow tableRow1 = new TableRow(this);
                    //tableRow1.setLayoutParams(new TableRow.LayoutParams((TableRow)findViewById(R.id.tableRow)));
                    tableRow1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                    //tableRow.setLa
                    if (i>-1) {
                        JSONObject c = servicesjson.getJSONObject(i);
                        categories_id = c.getInt("id");
                        image = c.getString("image");
                        decription = c.getString("decr");
                        count = c.getString("count");
                        Log.i("Debug", image);
                        Log.i("Debug", "categories_id " + categories_id);
                        Log.i("Debug", "count " + count);
            /*View tableRow = LayoutInflater.from(this).inflate(R.layout.table_item,null,false);
            ImageView imageView  = (ImageView) tableRow.findViewById(R.id.tv_no);*/

                        /*InputStream is = null;
                        try {
                            is = (InputStream) new URL(image).getContent();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*/
                    }
                    /*InvoiceData row = null;
                    if (i > -1)
                        row = data[i];
                    else {
                        textSpacer = new TextView(this);
                        textSpacer.setText("");

                    }*/
                    // data columns
            /*final TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            tv.setGravity(Gravity.LEFT);

            tv.setPadding(5, 15, 0, 15);
            if (i == -1) {
                tv.setText("A/A");
                tv.setBackgroundColor(Color.parseColor("#f0f0f0"));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                tv.setBackgroundColor(Color.parseColor("#f8f8f8"));
                tv.setText(String.valueOf(row.invoiceNumber));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }*/

                    final TextView tv2 = new TextView(this);
                    tv2.setGravity(Gravity.CENTER);
                    if (i == -1) {
                        tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                    } else {
                        tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.MATCH_PARENT));
                        tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                    }



                    tv2.setPadding(5, 15, 0, 15);
                    if (i == -1) {
                        tv2.setText("Offered by Companies");
                        //tv2.setA;
                        tv2.setBackgroundColor(Color.parseColor("#f7f7f7"));
                    } else {
                        tv2.setBackgroundColor(Color.parseColor("#ffffff"));
                        tv2.setTextColor(Color.parseColor("#000000"));
                        tv2.setText(count);
                    }


                    final LinearLayout layCustomer = new LinearLayout(this);
                    layCustomer.setOrientation(LinearLayout.VERTICAL);
                    layCustomer.setPadding(0, 10, 0, 10);
                    layCustomer.setBackgroundColor(Color.parseColor("#f8f8f8"));
                    //layCustomer.set
                    final TextView tv3 = new TextView(this);
                    tv3.setGravity(Gravity.CENTER);
                    if (i == -1) {
                        tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.MATCH_PARENT));
                        tv3.setPadding(5, 5, 0, 5);
                        tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                    } else {
                        tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.MATCH_PARENT));
                        tv3.setPadding(5, 0, 0, 5);
                        tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                    }

                    tv3.setGravity(Gravity.CENTER);


                    if (i == -1) {
                        tv3.setText("Offers");
                        tv3.setBackgroundColor(Color.parseColor("#f0f0f0"));
                    } else {
                        tv3.setBackgroundColor(Color.parseColor("#f8f8f8"));
                        tv3.setTextColor(Color.parseColor("#000000"));
                        tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                        tv3.setText(decription);
                    }
                    layCustomer.addView(tv3);


            /*if (i > -1) {
                final TextView tv3b = new TextView(this);
                tv3b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

                tv3b.setGravity(Gravity.RIGHT);
                tv3b.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                tv3b.setPadding(5, 1, 0, 5);
                tv3b.setTextColor(Color.parseColor("#aaaaaa"));
                tv3b.setBackgroundColor(Color.parseColor("#f8f8f8"));
                tv3b.setText(row.customerAddress);
                layCustomer.addView(tv3b);
            }*/
                    if (i > -1) {
                        final ImageView imageView = new ImageView(this);
                        AssetManager assetManager = getAssets();
                        //InputStream is = assetManager.open("img/image1.jpg");
                        //Bitmap bitmap = BitmapFactory.decodeStream(is);

                        final Button button = new Button(this);
                        button.setId(categories_id);
                        InputStream is1 = (InputStream) new URL(image).getContent();
                        Drawable d = Drawable.createFromStream(is1, "src name");
                        button.setBackground(d);
                        button.setHeight(500);
                        button.setWidth(500);
                        button.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                int category_id=button.getId();
                                Intent intent = new Intent(getApplicationContext(), TableOfDataTemp2.class);
                                intent.putExtra("category", "{\"category\":"+category_id+"}");
                                intent.putExtra("services", services);
                                startActivity(intent);
                            }
                        });
                        layCustomer.addView(button);
                    }
                    //imageView.setImageBitmap(bitmap);
                    //layCustomer.addView(imageView);

                    final LinearLayout layAmounts = new LinearLayout(this);
                    layAmounts.setOrientation(LinearLayout.VERTICAL);
                    layAmounts.setGravity(Gravity.RIGHT);
                    layAmounts.setPadding(0, 10, 0, 10);
                    layAmounts.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT));


                    final TextView tv4 = new TextView(this);
                    if (i == -1) {
                        tv4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.MATCH_PARENT));
                        tv4.setPadding(5, 5, 1, 5);
                        layAmounts.setBackgroundColor(Color.parseColor("#f7f7f7"));
                    } else {
                        tv4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv4.setPadding(5, 0, 1, 5);
                        layAmounts.setBackgroundColor(Color.parseColor("#ffffff"));
                    }

                    tv4.setGravity(Gravity.RIGHT);

                    if (i == -1) {
                        tv4.setText("Inv.Amount");
                        tv4.setBackgroundColor(Color.parseColor("#f7f7f7"));
                        tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                    } else {
                        tv4.setBackgroundColor(Color.parseColor("#ffffff"));
                        tv4.setTextColor(Color.parseColor("#000000"));
                        //tv4.setText(decimalFormat.format(row.invoiceAmount));
                        tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                    }

                    layAmounts.addView(tv4);


                    if (i > -1) {
                        final TextView tv4b = new TextView(this);
                        tv4b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.WRAP_CONTENT));

                        tv4b.setGravity(Gravity.RIGHT);
                        tv4b.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                        tv4b.setPadding(2, 2, 1, 5);
                        tv4b.setTextColor(Color.parseColor("#00afff"));
                        tv4b.setBackgroundColor(Color.parseColor("#ffffff"));

                        String due = "";
                        /*if (row.amountDue.compareTo(new BigDecimal(0.01)) == 1) {
                            due = "Due:" + decimalFormat.format(row.amountDue);
                            due = due.trim();
                        }*/
                        tv4b.setText(due);
                        layAmounts.addView(tv4b);
                    }


                    // add table row
                    final TableRow tr = new TableRow(this);
                    tr.setId(i + 1);
                    TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT);
                    trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
                    tr.setPadding(0, 0, 0, 0);
                    tr.setLayoutParams(trParams);


                    //tr.addView(tv);
                    tr.addView(tv2);
                    tr.addView(layCustomer);
                    //tr.addView(layAmounts);

                    if (i > -1) {

                        tr.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                TableRow tr = (TableRow) v;
                                //do whatever action is needed

                            }
                        });


                    }
                    mTableLayout.addView(tr, trParams);

                    if (i > -1) {

                        // add separator row
                        final TableRow trSep = new TableRow(this);
                        TableLayout.LayoutParams trParamsSep = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                                TableLayout.LayoutParams.WRAP_CONTENT);
                        trParamsSep.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);

                        trSep.setLayoutParams(trParamsSep);
                        TextView tvSep = new TextView(this);
                        TableRow.LayoutParams tvSepLay = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.WRAP_CONTENT);
                        tvSepLay.span = 4;
                        tvSep.setLayoutParams(tvSepLay);
                        tvSep.setBackgroundColor(Color.parseColor("#d9d9d9"));
                        tvSep.setHeight(1);

                        trSep.addView(tvSep);
                        mTableLayout.addView(trSep, trParamsSep);
                    }


                    //tableLayout.addView(tableRow);
                    //final Button btn1 = new Button(this);
                    //btn1.setText("Button_text");
                    //btn1.setBackground(d);
                    //btn1.setWidth();
                    //btn1.setHeight(500);
                    //btn1.set


                    //btn1.setId(categories_id);
                    //textView.setText("fff");
                    //tableRow.addView(btn1);
                    //tableRow.addView(btn1);
                    //tableLayout.addView(tableRow1);
                    //tableLayout.setPadding(0,5,0,50);
                    /*btn1.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v) {
                            int category_id=btn1.getId();
                            Intent intent = new Intent(getApplicationContext(), OffersTable.class);
                            intent.putExtra("category", "{\"category\":"+category_id+"}");
                            intent.putExtra("services", services);
                            startActivity(intent);

                        }
                    });*/
                }
                } catch (JSONException e) {
                    e.printStackTrace();
                 }


        }
    }
        // -1 means heading row


        //////////////////////////////////////////////////////////////////////////////

        //
        // The params are dummy and not used
        //
        class LoadDataTask extends AsyncTask<Integer, Integer, String> {
            @Override
            protected String doInBackground(Integer... params) {

                try {
                    Thread.sleep(2000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return "Task Completed.";
            }

            @Override
            protected void onPostExecute(String result) {
                mProgressBar.hide();
                try {
                    loadData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onPreExecute() {
            }

            @Override
            protected void onProgressUpdate(Integer... values) {

            }
        }

}
