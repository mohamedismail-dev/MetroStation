package com.example.metrostation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class map_view extends AppCompatActivity {

ImageView mapView,helwan_arrow,elmonib_arrow,adly_arrow,shubra_arrow,marg_arrow,kitkat_arrow,imageView14;
    TextView s1,s2,lineText,textView19,textView20,textView21,textView18,map_label;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

        s1 =findViewById(R.id.s1);
        s2 =findViewById(R.id.s2);
        lineText =findViewById(R.id.lineText);
        textView19 =findViewById(R.id.textView19);
        textView18 =findViewById(R.id.textView18);
        textView20=findViewById(R.id.textView20);
        textView21 =findViewById(R.id.textView21);
        mapView =findViewById(R.id.mapView);
        helwan_arrow =findViewById(R.id.helwan_arrow);
        elmonib_arrow =findViewById(R.id.elmonib_arrow);
        adly_arrow =findViewById(R.id.adly_arrow);
        shubra_arrow =findViewById(R.id.shubra_arrow);
        marg_arrow =findViewById(R.id.marg_arrow);
        kitkat_arrow =findViewById(R.id.kitkat_arrow);
        imageView14 =findViewById(R.id.imageView14);
        map_label =findViewById(R.id.map_label);

        YoYo.with(Techniques.FadeIn)
                .duration(3000)
                .repeat(9999)
                .playOn(findViewById(R.id.imageView14));


        Intent in= getIntent();


                String d = in.getStringExtra("Destination");
                String station1=in.getStringExtra("st1");
                String station2=in.getStringExtra("st2");
                String first_st =in.getStringExtra("first_sta");
                String last_st =in.getStringExtra("last_sta");
                String EorA =in.getStringExtra("EorA");



                if (EorA.equals("1")){
                    textView18.setText("هذه خريطه مبسطه توضح لك الخطوط الثلاث لمترو القاهره وتظهر الواجهات الخاص به.");
                    textView20.setText("مــن");
                    textView21.setText("الـي");
                    textView19.setText("واجهتك هي");
                    textView19.setTextSize(28);
                    map_label.setText("خريطه المترو");
                    map_label.setTextSize(32);
                    mapView.setImageResource(R.drawable.metro_map_arabic);


                }else {
                    textView18.setText("This is a simple map showing you the 3 lines of cairo metro stations and thier distractions.");
                    textView20.setText("From");
                    textView21.setText("To");
                    textView19.setText("Your Line Is");
                    mapView.setImageResource(R.drawable.metro_map_eng);

                }




                if (first_st.equalsIgnoreCase(" Ain Helwan ") || first_st.equalsIgnoreCase(" عين حلوان ")) {
                    lineText.setTextColor(Color.parseColor("#435AE4"));

                    if (EorA.equals("1")) {
                        first_st = " حلوان ";
                        lineText.setTextSize(18);
                    } else {
                        first_st = "Helwan ";
                        last_st = " New Marg ";
                        lineText.setTextSize(19);
                    }

                  helwan_arrow.setImageTintList(ColorStateList.valueOf(Color.parseColor("#CDB365")));
                  marg_arrow.setImageTintList(ColorStateList.valueOf(Color.parseColor("#CDB365")));
//                    helwan_arrow.
//                    marg_arrow.

                    YoYo.with(Techniques.BounceInRight)
                            .duration(2000)
                            .repeat(9999)
                            .playOn(findViewById(R.id.helwan_arrow));

                    YoYo.with(Techniques.BounceInLeft)
                            .duration(2000)
                            .repeat(9999)
                            .playOn(findViewById(R.id.marg_arrow));


                }else if (first_st.equalsIgnoreCase(" El Mounib ") || first_st.equalsIgnoreCase(" المنيب ")){
                    lineText.setTextColor(Color.parseColor("#A04343"));


                    if (first_st.equalsIgnoreCase(" El Mounib ")) {
                        lineText.setTextSize(15);
                    }else {
                        lineText.setTextSize(18);

                    }

                    elmonib_arrow.setImageTintList(ColorStateList.valueOf(Color.parseColor("#CDB365")));
                    shubra_arrow.setImageTintList(ColorStateList.valueOf(Color.parseColor("#CDB365")));
                    YoYo.with(Techniques.BounceInRight)
                            .duration(2000)
                            .repeat(9999)
                            .playOn(findViewById(R.id.elmonib_arrow));

                    YoYo.with(Techniques.BounceInLeft)
                            .duration(2000)
                            .repeat(9999)
                            .playOn(findViewById(R.id.shubra_arrow));

                }else{

                    lineText.setTextColor(Color.parseColor("#389A3C"));
                    adly_arrow.setImageTintList(ColorStateList.valueOf(Color.parseColor("#CDB365")));
                    kitkat_arrow.setImageTintList(ColorStateList.valueOf(Color.parseColor("#CDB365")));

                    YoYo.with(Techniques.BounceInRight)
                            .duration(2000)

                            .repeat(9999)
                            .playOn(findViewById(R.id.adly_arrow));

                    YoYo.with(Techniques.BounceInRight)
                            .duration(2000)

                            .repeat(9999)
                            .playOn(findViewById(R.id.kitkat_arrow));
                }


          s1.setText(station1);
          s2.setText(station2);
         lineText.setText(first_st +"-"+ last_st);

    }
}