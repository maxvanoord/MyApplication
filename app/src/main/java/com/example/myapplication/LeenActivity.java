package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class LeenActivity extends Activity{

    CalendarView calendar;
    String getDatum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datakiezerbestelling);

        calendar = findViewById(R.id.calendarView);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                getDatum = dayOfMonth + "/" + (month+1) + "/" + year;
            }
        });

        Button bevestigbutton = findViewById(R.id.leenbevestigingbutton);

        bevestigbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeenActivity.this, LeenBevestiging.class);
                intent.putExtra("DATUM", getDatum);
                startActivity(intent);
            }
        });



    }
}