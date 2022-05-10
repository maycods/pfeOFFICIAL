package com.example.fronttttttttttttttttttt;

import static android.service.controls.ControlsProviderService.TAG;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//TODO ON COMPLETE + GPS TESTER SI CA MARCHE QD CARTE BANCAIRE PRETE
public class Rendez_vous extends Activity implements AdapterView.OnItemSelectedListener {
    private ImageButton retour;
    private EditText mSearchText;
    private Button comfirmer;
    private  TextView gps;
    private FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rdv);
        //mSearchText = (EditText)findViewById(R.id.positionvacc) ;
       // gps = (TextView) findViewById(R.id.mapos) ;//////////////////
        retour=(ImageButton)findViewById(R.id.retourR);
        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.vaccines, R.layout.spinn);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    retour.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View view) {
                                      startActivity(new Intent(Rendez_vous.this, Menu.class));
                                  }
                              });
      /*gps.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              askGalleryPermissionLocation();
          }
      });
      comfirmer.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            //  init();
              // + spinner machin
          }
      });*/

   }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void init(){

        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER){

                    geoLocate();
                }

                return false;
            }
        });
    }
    private void geoLocate(){
        String searchString = mSearchText.getText().toString();

        Geocoder geocoder = new Geocoder(Rendez_vous.this);
        List<Address> list = new ArrayList<>();
        try{
            list = geocoder.getFromLocationName(searchString, 1);
        }catch (IOException e){
            Log.e(TAG, "geoLocate: IOException: " + e.getMessage() );
        }

        if(list.size() > 0){
            Address address = list.get(0);
           LatLng Position  =new LatLng( address.getLatitude(),address.getLongitude());

        }else{
            Toast.makeText(getApplicationContext(),"invalid location",Toast.LENGTH_LONG).show();
        }
    }
    private void askGalleryPermissionLocation() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        String rationale = "Please provide location permission so that you can use the app or smthg";
        Permissions.Options options = new Permissions.Options()
                .setRationaleDialogTitle("Permission required")
                .setSettingsDialogTitle("big Warning");

        Permissions.check(this/*context*/, permissions, null, null, new PermissionHandler() {
            @Override
            public void onGranted() {
                // do your task.
                getCurrentLocationn();
                Toast.makeText(getApplicationContext(), "permission granted", Toast.LENGTH_LONG).show();

            }
        });


    }
    @SuppressLint("MissingPermission")
    private void getCurrentLocationn(){

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ) {

            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(Rendez_vous.this);
            try {
                Task location0 = fusedLocationProviderClient.getLastLocation();

                location0.addOnCompleteListener(this,
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                if (task.isSuccessful()) {
                                    Location location = task.getResult();
                                    if (location != null) {
                                        LatLng position =new LatLng( location.getLatitude(), location.getLongitude());

                                    } else {
                                        Toast.makeText(getApplicationContext(), "no location", Toast.LENGTH_LONG).show();
                                    }

                                }else{ Toast.makeText(getApplicationContext(), "no success", Toast.LENGTH_LONG).show();}


                            }
                        });

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "catch", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"", Toast.LENGTH_LONG).show();
        }
    }

}