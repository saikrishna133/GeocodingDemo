package com.example.srikanth.geocodingdemo;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.srikanth.geocodingdemo.R.id.parent;

public class MainActivity extends Activity {
    Button button;
    double latitude;
    double longtitude;
    String address;
    private String countryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //addressTv = (TextView) findViewById(R.id.textView);
        //latLang = (TextView) findViewById(R.id.textView2);
        //onclick action
        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditText editText = (EditText) findViewById(R.id.edit);
                //accessing edittext value to address variable
                //   address = editText.getText().toString();
              //  Log.d("test2", " " + address);//debuging

                Spinner spinner = (Spinner) findViewById(R.id.spin);
                String countryName=spinner.getSelectedItem().toString();
                Log.d("test2", "country:" + countryName);
                spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());


                Geocoder geoCoder = new Geocoder(MainActivity.this, Locale.getDefault());
                Log.d("test2", "value :" + geoCoder);
                try
                {
                    List<Address> addresses = geoCoder.getFromLocationName(countryName, 1);


                    Log.d("test2", "addres:  " + addresses);
                    if (addresses.size() > 0) {
                        latitude = addresses.get(0).getLatitude();

                        Log.d("test2", "latit: " + latitude);
                        longtitude = addresses.get(0).getLongitude();
                        Log.d("test2", "longi" + longtitude);
                        //finding latlong values
                        LatLng fromPostion1 = new LatLng(latitude, longtitude);
                      //  String address = editText.getText().toString();
                        //Bundle args1 = new Bundle();
                        //invoking intent to maps activity
                        //args1.putParcelable("longLat_dataPrivider", fromPostion1);

                        Intent i = new Intent(MainActivity.this, MapsActivity.class);
                        i.putExtra("longLat_dataPrivider", fromPostion1);
                        i.putExtra("country_name", countryName);
                        //Log.d("test2","country :"+address);
                        // i.putExtras(args1);
                        startActivity(i);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });


    }

    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {


        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            Toast.makeText(parent.getContext(),
                    "Selected country : " + parent.getItemAtPosition(pos).toString(),
                    Toast.LENGTH_SHORT).show();

            //  countryName = parent.getItemAtPosition(pos).toString();
           // Log.d("test2","least :"+countryName);

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub\
             Toast.makeText(MainActivity.this, "Select country",
                    Toast.LENGTH_LONG).show();
        }


    }
}






