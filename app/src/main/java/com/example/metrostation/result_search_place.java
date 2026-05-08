package com.example.metrostation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class result_search_place extends AppCompatActivity {

    String Destination;
    String st1,st2,first_s,last_s,EorA,frist_st,sec_st;
    TextView stationsDisplay, numStations2, timeStations2, priceStations2,numStations_text,time_text,ticketprice_text,lable1,tv_frist_st,tv_sec_st,km_frist_st,km_sec_st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_search_place);


        Intent in = getIntent();
        Destination = in.getStringExtra("Destination");
        String Num_Stations = in.getStringExtra("Num_Stations");
        String Time = in.getStringExtra("Time");
        String Price = in.getStringExtra("Price");
        EorA = in.getStringExtra("EngOrAra");
        st1 = in.getStringExtra("st1");
        st2 = in.getStringExtra("st2");
        first_s = in.getStringExtra("first_s");
        last_s = in.getStringExtra("last_s");
        frist_st=in.getStringExtra("frist station");
        sec_st=in.getStringExtra("sec station");


        YoYo.with(Techniques.FadeIn)
                .duration(800)
                .playOn(findViewById(R.id.stationsDisplay));

        stationsDisplay = findViewById(R.id.stationsDisplay);
        numStations2 = findViewById(R.id.numStations2);
        priceStations2 = findViewById(R.id.priceStations2);
        timeStations2 = findViewById(R.id.timeStations2);
        numStations_text = findViewById(R.id.numStations_text);
        time_text = findViewById(R.id.time_text);
        ticketprice_text = findViewById(R.id.ticketprice_text);
        lable1 = findViewById(R.id.lable1);
        tv_frist_st = findViewById(R.id.tv_frist_st);
        tv_sec_st = findViewById(R.id.tv_sec_st);
        km_frist_st = findViewById(R.id.km_frist_st);
        km_sec_st = findViewById(R.id.km_sec_st);

        stationsDisplay.setText(Destination + "\n");
        numStations2.setText(Num_Stations + " St");
        timeStations2.setText(Time + " min");
        priceStations2.setText(Price + " EL");

        if (EorA.equals("1")) {
            lable1.setText("واجهتك هي _________");
            numStations_text.setText("عدد المحطات");
            time_text.setText("مده الرحله");
            ticketprice_text.setText("ثمن التذكره");

        } else {
            lable1.setText("Your Destination Is __________");
            numStations_text.setText("Num of Stations");
            time_text.setText("Time it Takes");
            ticketprice_text.setText("Ticket Price");
            tv_frist_st.setText(frist_st);
            tv_sec_st.setText(sec_st);

        }

    }
}