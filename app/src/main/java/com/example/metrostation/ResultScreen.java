package com.example.metrostation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class ResultScreen extends AppCompatActivity {

    TextView stationsDisplay, numStations, timeStations, priceStations,textView4,textView6,textView9,textView12,viewmap;
ImageView imageView8;

    String Destination;
    String st1,st2,first_s,last_s,EorA;
    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);


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

        YoYo.with(Techniques.FadeIn)
                .duration(800)
                .playOn(findViewById(R.id.stationsDisplay));

        stationsDisplay = findViewById(R.id.stationsDisplay);
        numStations = findViewById(R.id.numStations);
        priceStations = findViewById(R.id.priceStations);
        timeStations = findViewById(R.id.timeStations);
        textView4 = findViewById(R.id.textView4);
        textView6 = findViewById(R.id.textView6);
        textView9 = findViewById(R.id.textView9);
        textView12 = findViewById(R.id.textView12);
        imageView8 = findViewById(R.id.imageView8);
        viewmap = findViewById(R.id.viewmap);

        stationsDisplay.setText(Destination + "\n");
        numStations.setText(Num_Stations + " St");
        timeStations.setText(Time + " min");
        priceStations.setText(Price + " EL");

        if (EorA.equals("1")) {
            textView4.setText("واجهتك هي _________");
            textView6.setText("عدد المحطات");
            textView9.setText("مده الرحله");
            textView12.setText("ثمن التذكره");
            viewmap.setText("خريطه المترو");
        } else {
            textView4.setText("Your Destination Is __________");
            textView6.setText("Num of Stations");
            textView9.setText("Time it Takes");
            textView12.setText("Ticket Price");
            viewmap.setText("Metro Map");
        }


    }

    public void reurntomainscreen(View view) {
//        imageView8.setImageTintList(ColorStateList.valueOf((Color.WHITE)));
//Intent in = new Intent(this,MainActivity.class);
//startActivity(in);
//finish();
    }

    public void viewfullmap(View view) {
        Intent in = new Intent(ResultScreen.this,map_view.class);

        player =MediaPlayer.create(this, R.raw.openmap);
        player.start();

        in.putExtra("Destination",Destination);
        in.putExtra("st1",st1);
        in.putExtra("st2",st2);
        in.putExtra("first_sta",first_s);
        in.putExtra("last_sta",last_s);
        in.putExtra("EorA",EorA);

        startActivity(in);
    }
}
