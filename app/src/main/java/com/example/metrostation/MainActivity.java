package com.example.metrostation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;


import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mumayank.com.airlocationlibrary.AirLocation;

public class MainActivity extends AppCompatActivity implements AirLocation.Callback {
    Spinner lines;
    SearchableSpinner autostation1Text,autostation2Text;
    TextView textView,textView2,clearText,textView5;
    String station1, station2;
    Button button;
    ArrayList<String> stations = new ArrayList<>();
    ArrayList<String> lineslist = new ArrayList<>();
ImageView history_icon,sweepIcon,swaapText,locationMark1,locationMark2,loc1Bottom,loc2Bottom;

    AirLocation airLocation;
    double latitude,longitude;

    List<String> l;
    String LIST,ln;
    Switch switchLang;
    MediaPlayer player_start;
int eorA=2;
    ArrayAdapter adapter;



    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       
        switchLang=findViewById(R.id.switchLang);
        textView=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);
        button=findViewById(R.id.button);
        clearText=findViewById(R.id.clearText);
        lines =findViewById(R.id.lines);
        textView5 =findViewById(R.id.textView5);
        history_icon =findViewById(R.id.history_icon);
        sweepIcon =findViewById(R.id.sweepIcon);
         swaapText=findViewById(R.id.swaapText);
        autostation1Text=findViewById(R.id.autostation1Text);
        autostation2Text = findViewById(R.id.autostation2Text);
        clearText = findViewById(R.id.clearText);
        locationMark1 = findViewById(R.id.locationMark1);
        locationMark2 = findViewById(R.id.locationMark2);
        loc1Bottom = findViewById(R.id.loc1Bottom);
        loc2Bottom = findViewById(R.id.loc2Bottom);


//        history_icon.setEnabled(false);
        YoYo.with(Techniques.Shake)
            .duration(900)
                    .repeat(1)
                            .playOn(findViewById(R.id.textView5));



//        loc1Bottom.setEnabled(false);
//        locationMark1.setEnabled(false);


//        YoYo.with(Techniques.FadeIn)
//                        .duration(900)
//                                .repeat(2)
//                                        .playOn(findViewById(R.id.lines));

     Collections.addAll(lineslist,"Line__","Line 1 (Helwan - El Marg)","Line 2 (El Mounib - Shubra)","Line 3 (Adly Mansour)");
//        Collections.addAll(lineslist,"");

        ArrayAdapter adapterlines = new ArrayAdapter(this, android.R.layout.simple_list_item_1,lineslist);
        lines.setAdapter(adapterlines);
        ln =lineslist.get(0);

     Collections.addAll(stations, "");

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stations);
        autostation1Text.setAdapter(adapter);
        autostation2Text.setAdapter(adapter);

   autostation1Text.setEnabled(false);
        autostation2Text.setEnabled(false);




        //                    ---- SWITCH ENG/AR -----
        switchLang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                 ln= lines.getSelectedItem().toString();
                button.setEnabled(false);
                autostation1Text.setEnabled(false);
                autostation2Text.setEnabled(false);
                swaapText.setEnabled(false);


                if (isChecked){


                       YoYo.with(Techniques.Shake)
                           .duration(900)
                                   .repeat(1)
                                           .playOn(findViewById(R.id.textView5));


                    switchLang.setText("AR");
                    textView.setText("من فضلك اختر محطه البدايه");
                    textView2.setText("من فضلك اختر محطه النهايه");
                    button.setText("بـحـث");
                    clearText.setText("محو الكل");
                    textView5.setText("اختر خطـك");



//                   autostation1Text.setSelection(0);
//                   autostation2Text.setSelection(0);

                    lines.setSelection(0);

                    eorA=1;

                    lineslist.clear();

                    Collections.addAll(lineslist,"الخــط__   ","الخط الأول (حلوان - المرج الجديده)","الخط الثاني (المنيب - شبرا الخيمه)","الخط الثالث (عدلي منصور)");

                     adapterlines.remove("الخــط__   ");
                     adapterlines.insert("الخــط__   ",0);
                    stations.clear();
                    adapter.remove(" اختر خطك أولا ");
                    adapter.insert(" اختر خطك أولا ",0);
//
                }
                else {

                    YoYo.with(Techniques.Shake)
                            .duration(900)
                            .repeat(1)
                            .playOn(findViewById(R.id.textView5));

                    switchLang.setText("EN");
                    textView.setText("Please Enter Frist Station");
                    textView2.setText("Please Enter Last Station");
                    button.setText("Search");
                    clearText.setText("Rest All");
                    textView5.setText("Chose Line");

//                    autostation1Text.setSelection(0);
//                    autostation2Text.setSelection(0);

                    lines.setSelection(0);

                    eorA=2;

                    lineslist.clear();

                    Collections.addAll(lineslist,"Line__","Line 1 (Helwan - El Marg)","Line 2 (El Mounib - Shubra)","Line 3 (Adly Mansour)");
                    adapterlines.remove("Line__");
                    adapterlines.insert("Line__",0);

                    stations.clear();
                    adapter.remove(" Chose Your Line Frist ");
                    adapter.insert(" Chose Your Line Frist ",0);



                }

            }
        });


//                                 --- SWITCH LINES -----

        lines.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


if (eorA==1) {




    if (position == 1) {


        button.setEnabled(false);
        autostation1Text.setEnabled(true);
        autostation2Text.setEnabled(true);
        swaapText.setEnabled(true);


        autostation1Text.setSelection(0);
        autostation2Text.setSelection(0);

        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(1)
                .playOn(findViewById(R.id.autostation1Text));

        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(1)
                .playOn(findViewById(R.id.autostation2Text));

        stations.clear();
        Collections.addAll(stations," اختر محطتك من هنا "," حلوان " , " عين حلوان ", " جامعه حلوان ", " وادي حوف ", " حدائق حلوان ", " المعصرة ", " طرة الأسمنت ",
                " كوتسيكا ", " طرة البلد ", " ثكنات المعادي ", " المعادي ", " حدائق المعادي ", " دار السلام ", " الزهراء ", " مار جرجس ", " الملك الصالح ",
                " السيدة زينب ", " سعد زغلول ", " أنور السادات ", " جمال عبد الناصر ", " أحمد عرابي ", " الشهداء ", " غمرة ", " الدمرداش ", " منشية الصدر ", " كوبري القبة ", " حمامات القبة ",
                " سراي القبة ", " حدائق الزيتون ", " حلمية الزيتون ", " المطرية ", " عين شمس ", " عزبة النخل ", " المرج ", " المرج الجديدة ");

//adapter.remove(" عين حلوان ");
//adapter.insert(" عين حلوان ",0);


//adapter.addAll( " عين حلوان ", " جامعه حلوان ", " وادي حوف ", " حدائق حلوان ", " المعصرة ", " طرة الأسمنت ",
//        " كوتسيكا ", " طرة البلد ", " ثكنات المعادي ", " المعادي ", " حدائق المعادي ", " دار السلام ", " الزهراء ", " مار جرجس ", " الملك الصالح ",
//        " السيدة زينب ", " سعد زغلول ", " أنور السادات ", " جمال عبد الناصر ", " أحمد عرابي ", " الشهداء ", " غمرة ", " الدمرداش ", " منشية الصدر ", " كوبري القبة ", " حمامات القبة ",
//        " سراي القبة ", " حدائق الزيتون ", " حلمية الزيتون ", " المطرية ", " عين شمس ", " عزبة النخل ", " المرج ", " المرج الجديدة ");

    } else if (position == 2) {




        button.setEnabled(false);
        autostation1Text.setEnabled(true);
        autostation2Text.setEnabled(true);
        swaapText.setEnabled(true);


        autostation1Text.setSelection(0);
        autostation2Text.setSelection(0);

        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(1)
                .playOn(findViewById(R.id.autostation1Text));

        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(1)
                .playOn(findViewById(R.id.autostation2Text));

        stations.clear();
        Collections.addAll(stations ," اختر محطتك من هنا "," المنيب " , " ساقية مكي " , " أم المصريين " , " الجيزة " , " فيصل " ,
                " جامعة القاهرة " , " البحوث " , " الدقي " , " الأوبرا " , " أنور السادات " , " محمد نجيب " , " العتبة " , " الشهداء " , " مسرة " ,
                " روض الفرج " , " سانتا تريزا " , " الخلفاوي " , " المظلات " , " كلية الزراعة " , " شبرا الخيمة " );

//        adapter.remove(" المنيب ");
//        adapter.insert(" المنيب ",0);


    } else if (position == 3) {


        button.setEnabled(false);
        autostation1Text.setEnabled(true);
        autostation2Text.setEnabled(true);
        swaapText.setEnabled(true);


        autostation1Text.setSelection(0);
        autostation2Text.setSelection(0);

        stations.clear();

        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(1)
                .playOn(findViewById(R.id.autostation1Text));

        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(1)
                .playOn(findViewById(R.id.autostation2Text));


        stations.clear();
        Collections.addAll(stations," اختر محطتك من هنا ", " عدلي منصور " , " الهايكستب " , " عمر بن الخطاب " , " قباء " , " هشام بركات " ,  " النزهة " ,
                " نادي الشمس " , " ألف مسكن ", " هليوبوليس " , " هارون " ,  " الأهرام " , " كلية البنات " , " الاستاد " , " أرض المعارض " , " العباسية " ,
                " عبده باشا " , " الجيش " , " باب الشعرية " , " العتبة " , " جمال عبد الناصر " , " ماسبيرو " , " صفاء حجازي " , " الكيت كات " );

//        adapter.remove(" عدلي منصور ");
//        adapter.insert(" عدلي منصور ",0);


    } else {

        button.setEnabled(false);
        autostation1Text.setEnabled(false);
        autostation2Text.setEnabled(false);
        swaapText.setEnabled(false);

        autostation1Text.setSelection(0);
        autostation2Text.setSelection(0);

        stations.clear();
        Collections.addAll(stations," اختر خطك أولا ");
        adapter.remove(" اختر خطك أولا ");
        adapter.insert(" اختر خطك أولا ",0);

    }

}else {

    if (position == 1){


        button.setEnabled(false);
        autostation1Text.setEnabled(true);
        autostation2Text.setEnabled(true);
        swaapText.setEnabled(true);

        autostation1Text.setSelection(0);
        autostation2Text.setSelection(0);

        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(1)
                .playOn(findViewById(R.id.autostation1Text));

        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(1)
                .playOn(findViewById(R.id.autostation2Text));


        stations.clear();

        Collections.addAll(stations," Please Select a Station ", " Helwan ", " Ain Helwan ", " Helwan university ", " wadi hof ",
                " Hadayeq Helwan ", " El massara ", " tora El Asmant ", " Kozzika ", " Tora El Balad ", " Sakanat El Maadi ", " Maadi ",
                " Hadayeq El madi ", " Dar El salam ", " El zahraa ", " Mar Girgis ", " El Malek El Saleh ", " Sayyeda Zeinab ", " Saad Zaghloul ",
                " Sadat ", " Nasser ", " Orabi ", " Al Shohadaa ", " Ghamra "," El demerdash ", " Manshiet El Sadr ", " Kobri El Qobba ",
                " Hammamat El Qobba ", " Saray El Qobba ", " Hadayeq El Qobba ", " Helmiet El Zaitoun ", " El Matareyya ", " Ain Shams ",
                " Ezbet El Nakhl ", " El Marg ", " New El Marg ");

//        adapter.remove(" Ain Helwan ");
//        adapter.insert(" Ain Helwan ",0);

    }else if(position == 2){

        button.setEnabled(false);
        autostation1Text.setEnabled(true);
        autostation2Text.setEnabled(true);
        swaapText.setEnabled(true);

        autostation1Text.setSelection(0);
        autostation2Text.setSelection(0);

        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(1)
                .playOn(findViewById(R.id.autostation1Text));

        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(1)
                .playOn(findViewById(R.id.autostation2Text));


        stations.clear();
        Collections.addAll(stations," Please Select a Station "," El Mounib " , " Sakiat Mekky " , " Omm El Masryeen " , " El Giza " , " Faisal " , " Cairo University " , " El Bohoth " , " Dokki "
                , " Opera " , " Sadat " , " Mohamed Naguib " , " Attaba " , " Al Shohadaa " , " Masarra " , " Road El Farag " , " Santa Teresa "
                ,  " Khalafawy " , " Mezallat " , " Kolleyyet El Zeraa " , " Shubra Al Kheima " );

//        adapter.remove(" El Mounib ");
//        adapter.insert(" El Mounib ",0);

    }else if (position == 3){

        button.setEnabled(false);
        autostation1Text.setEnabled(true);
        autostation2Text.setEnabled(true);
        swaapText.setEnabled(true);


        autostation1Text.setSelection(0);
        autostation2Text.setSelection(0);

        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(1)
                .playOn(findViewById(R.id.autostation1Text));

        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(1)
                .playOn(findViewById(R.id.autostation2Text));

        stations.clear();
        Collections.addAll(stations," Please Select a Station ", " Adly Mansour " , " El Haykestep " , " Omar Ibn El Khattab " , " Qobaa " , " Hesham Barakat " , " El Nozha "
                , " Nadi El Shams " , " Alf Maskan " , " Heliopolis Square " , " Haroun " , " Al Ahram " , " Koleyet El Banat " , " Stadium " , " Fair Zone " 
                ,  " Abbassia " , " Abdou Pasha " , " El Geish " , " Bab El Shaaria " , " Attaba " , " Nasser " , " Maspero " ,
                 " Zamalek " , " Kit Kat " );

//        adapter.remove(" Adly Mansour ");
//        adapter.insert(" Adly Mansour ",0);

    }else{


        button.setEnabled(false);
        autostation1Text.setEnabled(false);
        autostation2Text.setEnabled(false);
        swaapText.setEnabled(false);

        autostation1Text.setSelection(0);
        autostation2Text.setSelection(0);



stations.clear();
Collections.addAll(stations," Chose Your Line Frist ");

        adapter.remove(" Chose Your Line Frist ");
       adapter.insert(" Chose Your Line Frist ",0);


    }

}

adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


 //                    ----- Listener for THE 2 lISTS ------

        autostation1Text.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    button.setEnabled(false);
                    clearText.setEnabled(false);
//                    loc1Bottom.setEnabled(false);
//                    locationMark1.setEnabled(false);


                    locationMark1.setVisibility(View.INVISIBLE);
                    loc1Bottom.setVisibility(View.INVISIBLE);
                } else {
//                    loc1Bottom.setEnabled(true);
//                    locationMark1.setEnabled(true);


                    locationMark1.setVisibility(View.VISIBLE);
                    loc1Bottom.setVisibility(View.VISIBLE);

                    YoYo.with(Techniques.BounceInRight)
                            .duration(1000)
                            .repeat(0)
                            .playOn(findViewById(R.id.locationMark1));

                     YoYo.with(Techniques.BounceInRight)
                            .duration(1000)
                            .repeat(0)
                            .playOn(findViewById(R.id.loc1Bottom));

                    clearText.setEnabled(true);
                    if (autostation2Text.getSelectedItem().equals(" Please Select a Station ") || autostation2Text.getSelectedItem().equals(" اختر محطتك من هنا ")) {
                        button.setEnabled(false);


                        YoYo.with(Techniques.FadeIn)
                                .duration(1000)
                                .repeat(1)
                                .playOn(findViewById(R.id.autostation2Text));


                    } else {
                        button.setEnabled(true);
                        YoYo.with(Techniques.FadeIn)
                                .duration(500)
                                .repeat(0)
                                .playOn(findViewById(R.id.button));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        autostation2Text.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    button.setEnabled(false);
                    clearText.setEnabled(false);

                    locationMark2.setVisibility(View.INVISIBLE);
                    loc2Bottom.setVisibility(View.INVISIBLE);

                } else {



                    locationMark2.setVisibility(View.VISIBLE);
                    loc2Bottom.setVisibility(View.VISIBLE);

                    YoYo.with(Techniques.BounceInRight)
                            .duration(1000)
                            .repeat(0)
                            .playOn(findViewById(R.id.locationMark2));

                    YoYo.with(Techniques.BounceInRight)
                            .duration(1000)
                            .repeat(0)
                            .playOn(findViewById(R.id.loc2Bottom));

                    clearText.setEnabled(true);
                    if (autostation1Text.getSelectedItem().equals(" Please Select a Station ") || autostation1Text.getSelectedItem().equals(" اختر محطتك من هنا ")){
                        button.setEnabled(false);
                        YoYo.with(Techniques.FadeIn)
                                .duration(1000)
                                .repeat(1)
                                .playOn(findViewById(R.id.autostation1Text));



                    }else{
                        button.setEnabled(true);}
                    YoYo.with(Techniques.FadeIn)
                            .duration(500)
                            .repeat(0)
                            .playOn(findViewById(R.id.button));
                }
                }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

//                                 ---- START -----

        public void GO (View view){


            station1 = autostation1Text.getSelectedItem().toString();
            station2 = autostation2Text.getSelectedItem().toString();




            if (station1.equals(" Please Select a Station ") || station2.equals(" Please Select a Station ")) {

                YoYo.with(Techniques.FadeIn)
                        .duration(1000)
                        .repeat(1)
                        .playOn(findViewById(R.id.autostation1Text));

                YoYo.with(Techniques.FadeIn)
                        .duration(1000)
                        .repeat(1)
                        .playOn(findViewById(R.id.autostation2Text));

                Toast.makeText(this, "Please Select a Station", Toast.LENGTH_SHORT).show();
                return;
            }
                if (station1.equals(" اختر محطتك من هنا ") || station2.equals(" اختر محطتك من هنا ")) {

                    YoYo.with(Techniques.FadeIn)
                            .duration(1000)
                            .repeat(1)
                            .playOn(findViewById(R.id.autostation1Text));

                    YoYo.with(Techniques.FadeIn)
                            .duration(1000)
                            .repeat(1)
                            .playOn(findViewById(R.id.autostation2Text));

                    Toast.makeText(this, "من فضلك اختر محطتك", Toast.LENGTH_SHORT).show();
                    return;
                }

            if (station1.isEmpty() || station2.isEmpty()) {

                YoYo.with(Techniques.FadeIn)
                        .duration(1000)
                        .repeat(1)
                        .playOn(findViewById(R.id.autostation1Text));

                YoYo.with(Techniques.FadeIn)
                        .duration(1000)
                        .repeat(1)
                        .playOn(findViewById(R.id.autostation2Text));

                Toast.makeText(this, "Please Enter a Station", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!stations.contains(station1) || !stations.contains(station2)) {

                YoYo.with(Techniques.FadeIn)
                        .duration(1000)
                        .repeat(1)
                        .playOn(findViewById(R.id.autostation1Text));

                YoYo.with(Techniques.FadeIn)
                        .duration(1000)
                        .repeat(1)
                        .playOn(findViewById(R.id.autostation2Text));

                Toast.makeText(this, "WRONG STATION", Toast.LENGTH_SHORT).show();
                return;
            }

            if (station1.equalsIgnoreCase(station2) ||station2.equalsIgnoreCase(station1)) {

                YoYo.with(Techniques.Shake)
                        .duration(1000)
                        .repeat(1)
                        .playOn(findViewById(R.id.autostation1Text));

                YoYo.with(Techniques.Shake)
                        .duration(1000)
                        .repeat(1)
                        .playOn(findViewById(R.id.autostation2Text));

                Toast.makeText(this, "Duplicated Station", Toast.LENGTH_SHORT).show();
                return;
            }

            player_start = MediaPlayer.create(this,R.raw.metro_door);
            player_start.start();

                if (stations.indexOf(station1) < stations.indexOf(station2)) {
                    l = stations.subList((stations.indexOf(station1)), stations.indexOf(station2)+1);
                    LIST = l.toString();

                } else {

                    Collections.reverse(stations.subList(1,stations.size()));
                    l = stations.subList((stations.indexOf(station1)), stations.indexOf(station2)+1);
                    LIST = l.toString();
                    Collections.reverse(stations.subList(1,stations.size()));

                }

                int snum = l.size();
                int time = (l.size())*2;
            int price;
            if(snum > 23){
                price = 20;
            } else if (snum >= 17) {
                price = 15;
            } else if (snum >= 10) {
                price = 12;
            } else {
                price = 10;
            }


//            resultText.setText("*______ YOUR LINE IS ______*\n" + LIST + "\n**________**" + "\n" + "\n" + "NUMBER OF STATIONS = " + snum + "\n" + "TICKET PRICE = " + price + " EL" + "\n" + "TIME = " + time+ " min");
            Intent in = new Intent(this,ResultScreen.class);
            in.putExtra("Destination",LIST);
            in.putExtra("Num_Stations",snum+"");
            in.putExtra("Time", time+"");
            in.putExtra("Price",price+"");
            in.putExtra("EngOrAra",eorA+"");
            in.putExtra("st1",station1);
            in.putExtra("st2",station2);
            in.putExtra("first_s",stations.get(1));
           int sz=stations.size()-1;
          in.putExtra("last_s",stations.get(sz));


            startActivity(in);


        }



 //                                 ---- CLEAR -----

        public void clear (View view) {


            autostation1Text.setSelection(0);
            autostation2Text.setSelection(0);


            YoYo.with(Techniques.FadeIn)
                    .duration(1000)
                    .repeat(1)
                    .playOn(findViewById(R.id.autostation1Text));

            YoYo.with(Techniques.FadeIn)
                    .duration(1000)
                    .repeat(1)
                    .playOn(findViewById(R.id.autostation2Text));

            if (eorA == 2) {
                Toast.makeText(this, "All Cleared", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, "تـم المحـو", Toast.LENGTH_SHORT).show();
            }
            button.setEnabled(false);
            adapter.notifyDataSetChanged();
        }


    //                                 ---- SWAP -----

        public void swaap (View view) {
            station1 = autostation1Text.getSelectedItem().toString();
            station2 = autostation2Text.getSelectedItem().toString();

            if (station1.isEmpty() || station2.isEmpty()) {
                Toast.makeText(this, "Please Enter a Station", Toast.LENGTH_SHORT).show();
                return;
            }

            if (station1.equals(" Please Select a Station ") || station2.equals(" Please Select a Station ")) {

                YoYo.with(Techniques.FadeIn)
                        .duration(1000)
                        .repeat(1)
                        .playOn(findViewById(R.id.autostation1Text));

                YoYo.with(Techniques.FadeIn)
                        .duration(1000)
                        .repeat(1)
                        .playOn(findViewById(R.id.autostation2Text));

                Toast.makeText(this, "Please Select a Station", Toast.LENGTH_SHORT).show();
                return;
            }
            if (station1.equals(" اختر محطتك من هنا ") || station2.equals(" اختر محطتك من هنا ")) {

                YoYo.with(Techniques.FadeIn)
                        .duration(1000)
                        .repeat(1)
                        .playOn(findViewById(R.id.autostation1Text));

                YoYo.with(Techniques.FadeIn)
                        .duration(1000)
                        .repeat(1)
                        .playOn(findViewById(R.id.autostation2Text));

                Toast.makeText(this, "من فضلك اختر محطتك", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!stations.contains(station1) || !stations.contains(station2)) {

                YoYo.with(Techniques.FadeIn)
                        .duration(1000)
                        .repeat(1)
                        .playOn(findViewById(R.id.autostation1Text));

                YoYo.with(Techniques.FadeIn)
                        .duration(1000)
                        .repeat(1)
                        .playOn(findViewById(R.id.autostation2Text));

                Toast.makeText(this, "WRONG STATION", Toast.LENGTH_SHORT).show();
                return;
            }

                if (station1.isEmpty() || station2.isEmpty()) {

                    YoYo.with(Techniques.FadeIn)
                            .duration(1000)
                            .repeat(1)
                            .playOn(findViewById(R.id.autostation1Text));

                    YoYo.with(Techniques.FadeIn)
                            .duration(1000)
                            .repeat(1)
                            .playOn(findViewById(R.id.autostation2Text));

                    Toast.makeText(this, "Please Enter a Station", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (station1.equalsIgnoreCase(station2)) {

                    YoYo.with(Techniques.Shake)
                            .duration(1000)
                            .repeat(1)
                            .playOn(findViewById(R.id.autostation1Text));

                    YoYo.with(Techniques.Shake)
                            .duration(1000)
                            .repeat(1)
                            .playOn(findViewById(R.id.autostation2Text));

                    Toast.makeText(this, "Duplicated Station", Toast.LENGTH_SHORT).show();
                    return;
                }

                autostation1Text.setSelection(stations.indexOf(station2));
                autostation2Text.setSelection(stations.indexOf(station1));


                YoYo.with(Techniques.BounceInUp)
                        .duration(1000)
//                    .repeat(1)
                        .playOn(findViewById(R.id.autostation1Text));

                YoYo.with(Techniques.BounceInDown)
                        .duration(1000)
//                    .repeat(1)
                        .playOn(findViewById(R.id.autostation2Text));


                if (eorA == 2) {
                    Toast.makeText(this, "Swapped Succeeded", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(this, "تـم التبديل بنجاح", Toast.LENGTH_SHORT).show();
                }

            adapter.notifyDataSetChanged();

            }

        public void viewhistory(View view){

            Toast.makeText(this, "This feature is not available yet :(", Toast.LENGTH_SHORT).show();

        }



    public void station1loca(View view) {

//        String[] s = autostation1Text.getSelectedItem().toString().split(" ");
//        String n="";
//        for (int i = 0; i < s.length; i++) {
//            n=n+s[i]+"+";
//        }
//
//        station1 = n + "metro+station+egypt";
        station1 = autostation1Text.getSelectedItem().toString()+"+metro+station";

        Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + station1));
        startActivity(in);



//        Geocoder geocoder = new Geocoder(this);
//        try {
//            List<Address> addressList = geocoder.getFromLocationName(station1, 1);
//            Location loc1 = new Location("");
//            loc1.setLatitude(addressList.get(0).getLatitude());
//            loc1.setLongitude(addressList.get(0).getLongitude());
//
//            Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + addressList.get(0).getLatitude() + "," + addressList.get(0).getLongitude()));
//            startActivity(in);
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        }




    }


        @Override
        protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            airLocation.onActivityResult(requestCode, resultCode, data);
        }

        @Override
        public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
        @NonNull int[] grantResults){
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            airLocation.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

        @Override
        public void onFailure (@NonNull AirLocation.LocationFailedEnum locationFailedEnum){
            Toast.makeText(this, "location problem", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSuccess (@NonNull ArrayList < Location > arrayList) {


            latitude = arrayList.get(0).getLatitude();
            longitude = arrayList.get(0).getLongitude();

            Geocoder geocoder = new Geocoder(this);
            try {
                List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);



            } catch (IOException e) {
                Toast.makeText(this, "connection problem", Toast.LENGTH_SHORT).show();
            }



        }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    public void station2loca(View view) {

        station2 = autostation2Text.getSelectedItem().toString() + "+metro+station+egypt";

        Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + station2));
        startActivity(in);



//
//        airLocation = new AirLocation(this, this, true, 0, "");
//        airLocation.start();
//
//
//        Geocoder geocoder=new Geocoder(this);
//        try {
//            List<Address> addressList = geocoder.getFromLocationName(station2, 1);
//            Location loc1=new Location("");
//            loc1.setLatitude(addressList.get(0).getLatitude());
//            loc1.setLongitude(addressList.get(0).getLongitude());
//
//            Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + addressList.get(0).getLatitude() + "," + addressList.get(0).getLongitude()));
//            startActivity(in);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }


    public void tosearchbyplace(View view) {
        Intent inn = new Intent(this,MainActivity2.class);
        startActivity(inn);
        inn.putExtra("EngOrAra",eorA+"");
    }
}



