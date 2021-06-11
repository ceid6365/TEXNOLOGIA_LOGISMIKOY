package com.ceid.project;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

public class RandevouActivity extends AppCompatActivity {

    // Define the variable of CalendarView type
    // and TextView type;
    CalendarView calender;
    TextView date_view;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_randevou);

        //get the spinner from the xml.

        final DatePicker DatePicker= (android.widget.DatePicker) findViewById(R.id.datePicker1);
        Button save = (Button) findViewById(R.id.save);
        final TimePicker timePicker= (TimePicker) findViewById(R.id.timePicker1);

        save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                String category_id="0";
                /*Intent intent = new Intent(getApplicationContext(), ServiceSelection.class);
                intent.putExtra("subcategory", "{\"subcategory\":"+category_id+"}");
                //intent.putExtra("services", services);
                startActivity(intent);*/
                System.out.println("Selected Date: "+ DatePicker.getDayOfMonth()+"/"+ (DatePicker.getMonth() + 1)+"/"+DatePicker.getYear());
                int h=timePicker.getHour();
                int m=timePicker.getMinute();
                System.out.println("Selected Date: "+h+" "+m);
            }
        });


    }
}