package com.example.metrostation;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mumayank.com.airlocationlibrary.AirLocation;

public class MainActivity2 extends AppCompatActivity implements AirLocation.Callback {

    TextView fristStation,secStation,placeTitel,fromTitel,toTitel;
    TextInputEditText namePlace;
    String name_of_Place;
    ImageView swaapButton;
    Button searchButton,goButton;
    AirLocation airLocation;
    double latitude_myloc,longitude_myloc;
    double place_lat,place_long;
    List<Address> addressList;
    List<String> l;
    String LIST;
    ArrayList<String> stations;
    String eorA;
    boolean locationReady = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        fristStation=findViewById(R.id.fristStation);
        secStation=findViewById(R.id.secStation);
        placeTitel=findViewById(R.id.placeTitel);
        fromTitel=findViewById(R.id.fromTitel);
        toTitel=findViewById(R.id.toTitel);
        namePlace=findViewById(R.id.namePlace);
        swaapButton=findViewById(R.id.swaapButton);
        searchButton=findViewById(R.id.searchButton);
        goButton=findViewById(R.id.goButton);

         name_of_Place =namePlace.getText().toString();
         airLocation  = new AirLocation(this,this, true, 0, "");
         airLocation.start();
        searchButton.setEnabled(false);
        goButton.setEnabled(false);

        Intent inn = getIntent();
        eorA = inn.getStringExtra("EngOrAra");

        namePlace.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                  name_of_Place =namePlace.getText().toString();
                if (name_of_Place.length()>4) {

                    searchButton.setEnabled(true);
                    goButton.setEnabled(false);

                    fristStation.setText("----");
                    secStation.setText("----");
                }else {
                    searchButton.setEnabled(false);
                    goButton.setEnabled(true);
                }


//                }

            }
        });


    }
    
    
    @Override
    public void onFailure(@NonNull AirLocation.LocationFailedEnum locationFailedEnum) {
        Toast.makeText(this, "location problem", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(@NonNull ArrayList<Location> arrayList) {

        latitude_myloc = arrayList.get(0).getLatitude();
        longitude_myloc = arrayList.get(0).getLongitude();
        locationReady = true;

        Geocoder geocoder = new Geocoder(this);
        try {
          addressList = geocoder.getFromLocation(latitude_myloc, longitude_myloc, 1);



        } catch (IOException e) {
            Toast.makeText(this, "connection problem", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }


    
    
//------------------------START BUTTON-------------------------------    

    public void search(View view) {
        goButton.setEnabled(true);
        searchButton.setEnabled(false);


        if (!locationReady) {
            Toast.makeText(this, "الموقع الحالي غير متاح بعد، حاول مرة أخرى", Toast.LENGTH_SHORT).show();
            return;
        }

        name_of_Place = namePlace.getText().toString() + "+القاهره";
        Geocoder geocoder = new Geocoder(this);
        try {
            List<Address> addressList = geocoder.getFromLocationName(name_of_Place, 1);
            if (addressList.size() > 0) {
                place_lat = addressList.get(0).getLatitude();
                place_long = addressList.get(0).getLongitude();

                if (place_lat == 30.0444196 && place_long == 31.2357116) {
                    YoYo.with(Techniques.Shake)
                            .duration(1000)
                            .repeat(1)
                            .playOn(findViewById(R.id.textInputLayout));
                    Toast.makeText(this, "WRONG PLACE!", Toast.LENGTH_SHORT).show();
                    return;
                }
            } else {
                Toast.makeText(this, "WRONG ADDRESS", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (IOException e) {
            Toast.makeText(this, "CONNECTION ERROR", Toast.LENGTH_SHORT).show();
            return;
        }

        // قوائم المحطات والإحداثيات (كما هي بدون تغيير)
        ArrayList<String> all_stations = new ArrayList<>();
        ArrayList<Double> all_stations_lat = new ArrayList<>();
        ArrayList<Double> all_stations_long = new ArrayList<>();

        Collections.addAll(all_stations,
                " Helwan ", " Ain Helwan ", " Helwan university ", " wadi hof ",
                " Hadayeq Helwan ", " El massara ", " tora El Asmant ", " Kozzika ", " Tora El Balad ", " Sakanat El Maadi ", " Maadi ",
                " Hadayeq El madi ", " Dar El salam ", " El zahraa ", " Mar Girgis ", " El Malek El Saleh ", " Sayyeda Zeinab ", " Saad Zaghloul ",
                " Sadat ", " Nasser ", " Orabi ", " Al Shohadaa ", " Ghamra ", " El demerdash ", " Manshiet El Sadr ", " Kobri El Qobba ",
                " Hammamat El Qobba ", " Saray El Qobba ", " Hadayeq El Qobba ", " Helmiet El Zaitoun ", " El Matareyya ", " Ain Shams ",
                " Ezbet El Nakhl ", " El Marg ", " New El Marg ",
                " El Mounib ", " Sakiat Mekky ", " Omm El Masryeen ", " El Giza ", " Faisal ", " Cairo University ",
                " El Bohoth ", " Dokki ", " Opera ", " Sadat ", " Mohamed Naguib ", " Attaba ", " Al Shohadaa ", " Masarra ", " Road El Farag ",
                " Santa Teresa ", " Khalafawy ", " Mezallat ", " Kolleyyet El Zeraa ", " Shubra Al Kheima ",
                " Adly Mansour ", " El Haykestep ", " Omar Ibn El Khattab ", " Qobaa ",
                " Hesham Barakat ", " El Nozha ", " Nadi El Shams ", " Alf Maskan ", " Heliopolis Square ", " Haroun ", " Al Ahram ",
                " Koleyet El Banat ", " Stadium ", " Fair Zone ", " Abbassia ", " Abdou Pasha ", " El Geish ", " Bab El Shaaria ",
                " Attaba ", " Nasser ", " Maspero ", " Zamalek ", " Kit Kat "
        );

        Collections.addAll(all_stations_lat,
                29.848982, 29.8626524, 29.8694514, 29.8790824, 29.897136, 29.9060784, 29.9259651, 29.936259, 29.9467633, 29.9533009,
                29.9603028, 29.9701432, 29.9819865, 29.9954817, 30.0060988, 30.0177109, 30.029287, 30.0370333, 30.043622, 30.0528454,
                30.0566885, 30.0610714, 30.069029, 30.07678, 30.0819852, 30.087197, 30.0912364, 30.097646, 30.1058896, 30.1132548,
                30.1253988, 30.1310257, 30.139318, 30.1520807, 30.1578646,
                29.9810944, 29.9954938, 30.0056539, 30.0130557, 30.0170425, 30.0273481, 30.0359549, 30.0384395, 30.0414695, 30.043622,
                30.045362, 30.052346, 30.0610714, 27.4913734, 30.0805881, 30.0879569, 30.0978361, 30.1041743, 30.0935894, 30.1224317,
                30.1461983, 30.1449475, 30.1413813, 30.1355159, 30.1302226, 30.1073554, 26.820553, 30.118676, 30.1080936, 30.1259843,
                30.091716, 30.0830217, 30.0732068, 30.073257, 30.071985, 30.064781, 30.061752, 30.054144, 30.052346, 30.0528454,
                30.0623509, 30.0622466, 30.0665411
        );

        Collections.addAll(all_stations_long,
                31.3342309, 31.3250527, 31.3200669, 31.3135831, 31.3039662, 31.2995158, 31.2875444, 31.2818206, 31.27298, 31.262956,
                31.2576431, 31.2506075, 31.2422244, 31.2311768, 31.2296288, 31.2310717, 31.2354308, 31.238362, 31.2358609, 31.2393861,
                31.2420542, 31.2460506, 31.2646168, 31.27727, 31.2875345, 31.2941041, 31.2989112, 31.3045631, 31.3104833, 31.3139647,
                31.3181252, 31.3190913, 31.3244222, 31.335682, 31.3413923,
                31.2123247, 31.208656, 31.2081202, 31.2088526, 31.2039734, 31.2088051, 31.199134, 31.2122279, 31.2257914, 31.2358609,
                31.244032, 31.246803, 31.2460506, 30.8376271, 31.2454069, 31.2454927, 31.2450099, 31.2456414, 31.2896574, 31.2445379,
                31.4206179, 31.4045436, 31.3959698, 31.3838224, 31.372567, 31.3884671, 30.802498, 31.3398505, 31.3382408, 31.3493383,
                31.3263157, 31.3295009, 31.3172461, 31.30098, 31.283375, 31.27475, 31.266882, 31.255877, 31.246803, 31.2393861,
                31.214817, 31.2232855, 31.2130764
        );

        // العثور على أقرب محطة لموقعي الحالي
        ArrayList<Double> contener1 = new ArrayList<>();
        for (int i = 0; i < all_stations.size(); i++) {
            Location loc1 = new Location("");
            loc1.setLatitude(latitude_myloc);
            loc1.setLongitude(longitude_myloc);
            Location loc2 = new Location("");
            loc2.setLatitude(all_stations_lat.get(i));
            loc2.setLongitude(all_stations_long.get(i));
            double distance1 = loc1.distanceTo(loc2) / 1000.0; // تحويل مباشر إلى كيلومترات دون فقد كسور
            contener1.add(distance1);
        }
        double min1 = Collections.min(contener1);
        int index1 = contener1.indexOf(min1);
        fristStation.setText(all_stations.get(index1));

        // العثور على أقرب محطة للمكان المدخل
        ArrayList<Double> contener2 = new ArrayList<>();
        for (int j = 0; j < all_stations.size(); j++) {
            Location loc1 = new Location("");
            loc1.setLatitude(place_lat);
            loc1.setLongitude(place_long);
            Location loc2 = new Location("");
            loc2.setLatitude(all_stations_lat.get(j));
            loc2.setLongitude(all_stations_long.get(j));
            double distance2 = loc1.distanceTo(loc2) / 1000.0;
            contener2.add(distance2);
        }
        double min2 = Collections.min(contener2);
        int index2 = contener2.indexOf(min2);
        secStation.setText(all_stations.get(index2));
    }


    public void GO(View view) {

        ArrayList<String> stations_ln1 = new ArrayList<>();
        ArrayList<String> stations_ln2 = new ArrayList<>();
        ArrayList<String> stations_ln3 = new ArrayList<>();
        ArrayList<String> stations_H_M = new ArrayList<>();
        ArrayList<String> stations_H_K = new ArrayList<>();
        ArrayList<String> stations_H_A = new ArrayList<>();
        ArrayList<String> stations_H_S = new ArrayList<>();
        ArrayList<String> stations_M_K = new ArrayList<>();
        ArrayList<String> stations_M_MA = new ArrayList<>();
        ArrayList<String> stations_M_A = new ArrayList<>();
        ArrayList<String> stations_A_MA = new ArrayList<>();
        ArrayList<String> stations_A_S = new ArrayList<>();
        ArrayList<String> stations_MA_S = new ArrayList<>();
        ArrayList<String> stations_MA_K = new ArrayList<>();



        Collections.addAll(stations_ln1, " Helwan ", " Ain Helwan ", " Helwan university ", " wadi hof ",
                " Hadayeq Helwan ", " El massara ", " tora El Asmant ", " Kozzika ", " Tora El Balad ", " Sakanat El Maadi ", " Maadi ",
                " Hadayeq El madi ", " Dar El salam ", " El zahraa ", " Mar Girgis ", " El Malek El Saleh ", " Sayyeda Zeinab ", " Saad Zaghloul ",
                " Sadat ", " Nasser ", " Orabi ", " Al Shohadaa ", " Ghamra "," El demerdash ", " Manshiet El Sadr ", " Kobri El Qobba ",
                " Hammamat El Qobba ", " Saray El Qobba ", " Hadayeq El Qobba ", " Helmiet El Zaitoun ", " El Matareyya ", " Ain Shams ",
                " Ezbet El Nakhl ", " El Marg ", " New El Marg ");

        Collections.addAll(stations_ln2," El Mounib " , " Sakiat Mekky " , " Omm El Masryeen " , " El Giza " , " Faisal " ,
                " Cairo University " , " El Bohoth " , " Dokki ", " Opera " , " Sadat " , " Mohamed Naguib " , " Attaba " , " Al Shohadaa " , " Masarra " ,
                " Road El Farag " , " Santa Teresa ",  " Khalafawy " , " Mezallat " , " Kolleyyet El Zeraa " , " Shubra Al Kheima " );

        Collections.addAll(stations_ln3, " Adly Mansour " , " El Haykestep " , " Omar Ibn El Khattab " , " Qobaa " , " Hesham Barakat " ,
                " El Nozha ", " Nadi El Shams " , " Alf Maskan " , " Heliopolis Square " , " Haroun " , " Al Ahram " , " Koleyet El Banat " , " Stadium " , " Fair Zone "
                ,  " Abbassia " , " Abdou Pasha " , " El Geish " , " Bab El Shaaria " , " Attaba " , " Nasser " , " Maspero " ,
                " Zamalek " , " Kit Kat " );

                                              //------------------OTHER BRANCH LINES--------------------//

        Collections.addAll(stations_H_M, " Helwan ", " Ain Helwan ", " Helwan university ", " wadi hof ",
                " Hadayeq Helwan ", " El massara ", " tora El Asmant ", " Kozzika ", " Tora El Balad ", " Sakanat El Maadi ", " Maadi ",
                " Hadayeq El madi ", " Dar El salam ", " El zahraa ", " Mar Girgis ", " El Malek El Saleh ", " Sayyeda Zeinab ", " Saad Zaghloul ",
                " Sadat "," Opera "," Dokki "," El Bohoth " , " Cairo University ", " Faisal ", " El Giza " , " Omm El Masryeen ", " Sakiat Mekky "," El Mounib " );

        Collections.addAll(stations_H_K, " Helwan ", " Ain Helwan ", " Helwan university ", " wadi hof ",
                " Hadayeq Helwan ", " El massara ", " tora El Asmant ", " Kozzika ", " Tora El Balad ", " Sakanat El Maadi ", " Maadi ",
                " Hadayeq El madi ", " Dar El salam ", " El zahraa ", " Mar Girgis ", " El Malek El Saleh ", " Sayyeda Zeinab ", " Saad Zaghloul ",
                " Sadat ", " Nasser ", " Maspero " , " Zamalek " , " Kit Kat " );

        Collections.addAll(stations_H_A, " Helwan ", " Ain Helwan ", " Helwan university ", " wadi hof ",
                " Hadayeq Helwan ", " El massara ", " tora El Asmant ", " Kozzika ", " Tora El Balad ", " Sakanat El Maadi ", " Maadi ",
                " Hadayeq El madi ", " Dar El salam ", " El zahraa ", " Mar Girgis ", " El Malek El Saleh ", " Sayyeda Zeinab ", " Saad Zaghloul ",
                " Sadat ", " Nasser ", " Attaba ", " Bab El Shaaria ", " El Geish ", " Abdou Pasha ",  " Abbassia ", " Hesham Barakat ", " Qobaa ", " Omar Ibn El Khattab "
                , " El Haykestep "," Adly Mansour " );

        Collections.addAll(stations_H_S, " Helwan ", " Ain Helwan ", " Helwan university ", " wadi hof ",
                " Hadayeq Helwan ", " El massara ", " tora El Asmant ", " Kozzika ", " Tora El Balad ", " Sakanat El Maadi ", " Maadi ",
                " Hadayeq El madi ", " Dar El salam ", " El zahraa ", " Mar Girgis ", " El Malek El Saleh ", " Sayyeda Zeinab ", " Saad Zaghloul ",
                " Sadat ", " Nasser ", " Orabi ", " Al Shohadaa ", " Masarra " , " Road El Farag " , " Santa Teresa ",  " Khalafawy " , " Mezallat " ,
                " Kolleyyet El Zeraa " , " Shubra Al Kheima " );

        Collections.addAll(stations_M_K," El Mounib " , " Sakiat Mekky " , " Omm El Masryeen " , " El Giza " , " Faisal " ,
                " Cairo University " , " El Bohoth " , " Dokki ", " Opera " , " Sadat " , " Mohamed Naguib " , " Attaba " , " Nasser " , " Maspero " ,
                " Zamalek " , " Kit Kat "  );

        Collections.addAll(stations_M_MA, " El Mounib " , " Sakiat Mekky " , " Omm El Masryeen " , " El Giza " , " Faisal " ,
                " Cairo University " , " El Bohoth " , " Dokki ", " Opera " , " Sadat " , " Mohamed Naguib " , " Attaba " , " Al Shohadaa " , " Ghamra "," El demerdash ",
                " Manshiet El Sadr ", " Kobri El Qobba ", " Hammamat El Qobba ", " Saray El Qobba ", " Hadayeq El Qobba ", " Helmiet El Zaitoun ", " El Matareyya ",
                " Ain Shams ", " Ezbet El Nakhl ", " El Marg ", " New El Marg ");

        Collections.addAll(stations_M_A," El Mounib " , " Sakiat Mekky " , " Omm El Masryeen " , " El Giza " , " Faisal " ,
                " Cairo University " , " El Bohoth " , " Dokki ", " Opera " , " Sadat " , " Mohamed Naguib " , " Attaba " , " Bab El Shaaria ", " El Geish ", " Abdou Pasha "
                ," Abbassia ", " Hesham Barakat ", " Qobaa ", " Omar Ibn El Khattab ", " El Haykestep "," Adly Mansour " );

        Collections.addAll(stations_A_MA," Adly Mansour " , " El Haykestep " , " Omar Ibn El Khattab " , " Qobaa " , " Hesham Barakat " ,
                " El Nozha ", " Nadi El Shams " , " Alf Maskan " , " Heliopolis Square " , " Haroun " , " Al Ahram " , " Koleyet El Banat " , " Stadium " , " Fair Zone "
                ,  " Abbassia " , " Abdou Pasha " , " El Geish " , " Bab El Shaaria " , " Attaba " , " Nasser " ," Orabi ", " Al Shohadaa " , " Ghamra "," El demerdash ",
                " Manshiet El Sadr ", " Kobri El Qobba ", " Hammamat El Qobba ", " Saray El Qobba ", " Hadayeq El Qobba ", " Helmiet El Zaitoun ", " El Matareyya ",
                " Ain Shams ", " Ezbet El Nakhl ", " El Marg ", " New El Marg " );

        Collections.addAll(stations_A_S," Adly Mansour " , " El Haykestep " , " Omar Ibn El Khattab " , " Qobaa " , " Hesham Barakat " ,
                " El Nozha ", " Nadi El Shams " , " Alf Maskan " , " Heliopolis Square " , " Haroun " , " Al Ahram " , " Koleyet El Banat " , " Stadium " , " Fair Zone "
                ,  " Abbassia " , " Abdou Pasha " , " El Geish " , " Bab El Shaaria " , " Attaba " ," Al Shohadaa " , " Masarra " ,
                " Road El Farag " , " Santa Teresa ",  " Khalafawy " , " Mezallat " , " Kolleyyet El Zeraa " , " Shubra Al Kheima "  );

        Collections.addAll(stations_MA_S,  " New El Marg ", " El Marg "," Ezbet El Nakhl "," Ain Shams "," El Matareyya ", " Helmiet El Zaitoun ",
                " Hadayeq El Qobba "," Saray El Qobba "," Hammamat El Qobba "," Kobri El Qobba "," Manshiet El Sadr "," El demerdash ", " Ghamra "," Al Shohadaa " ," Masarra " ,
                " Road El Farag " , " Santa Teresa ",  " Khalafawy " , " Mezallat " , " Kolleyyet El Zeraa " , " Shubra Al Kheima " );

        Collections.addAll(stations_MA_K, " New El Marg ", " El Marg "," Ezbet El Nakhl "," Ain Shams "," El Matareyya ", " Helmiet El Zaitoun ",
                " Hadayeq El Qobba "," Saray El Qobba "," Hammamat El Qobba "," Kobri El Qobba "," Manshiet El Sadr "," El demerdash ", " Ghamra "," Al Shohadaa "," Orabi "
                , " Nasser "," Maspero " ,  " Zamalek " , " Kit Kat ");

        
                                                            //---------------GO---------------//
        ArrayList<ArrayList<String>> all_stations = new ArrayList<>();
        Collections.addAll(all_stations,stations_ln1,stations_ln2,stations_ln3,stations_A_S,stations_H_S,stations_MA_S,stations_A_MA,stations_H_A,stations_H_K,stations_H_M,
                stations_M_K,stations_MA_K,stations_M_MA,stations_M_A);

        String frist_st = fristStation.getText().toString();
        String sec_st = secStation.getText().toString();

        for (int i = 0; i < all_stations.size(); i++) {


            stations = all_stations.get(i);


            if (stations.contains(frist_st) && stations.contains(sec_st)) {


             
                if (stations.indexOf(frist_st) < stations.indexOf(sec_st)) {
                    l = stations.subList((stations.indexOf(frist_st)), stations.indexOf(sec_st) + 1);
                    LIST = l.toString();


                } else {

                    Collections.reverse(stations.subList(1, stations.size()));
                    l = stations.subList((stations.indexOf(frist_st)), stations.indexOf(sec_st) + 1);
                    LIST = l.toString();
                    Collections.reverse(stations.subList(1, stations.size()));


                }
            } }

                int snum = l.size();
                int time = (l.size()) * 2;
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



        Intent in2 = new Intent(this,result_search_place.class);
        in2.putExtra("Destination",LIST);
        in2.putExtra("Num_Stations",snum+"");
        in2.putExtra("Time", time+"");
        in2.putExtra("Price",price+"");
        in2.putExtra("EngOrAra",eorA+"");
        in2.putExtra("st1",frist_st);
        in2.putExtra("st2",sec_st);
        in2.putExtra("first_s",stations.get(1));
        int sz=stations.size()-1;
        in2.putExtra("last_s",stations.get(sz));
        in2.putExtra("frist station",frist_st);
         in2.putExtra("sec station",sec_st);


        startActivity(in2);



        
        
        
        
        
        
        
        
        
        
        }
    }
