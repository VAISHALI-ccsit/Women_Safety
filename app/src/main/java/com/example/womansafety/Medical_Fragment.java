package com.example.womansafety;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.animation.content.Content;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Medical_Fragment extends Fragment {

    String title_send;
    String message_send;
    FusedLocationProviderClient fusedLocationProviderClient;
    //Pixel Run=> Token: NMAXJ6iQo-wN4F7vemv7Y:APA91bEuOFKMvKcNjkSnmY5E8XCxj_kEcWpDVhiDIw54IfLSJemRKvdEXgAi4e_HMMm4gwGrnh2R98Vn_oTxOGTMPuYC9JOLuPI0FY0xLqXbixMjBgEKVcbY7Nzy1rsYVaBqxnD3TfJ9
    //Vaishali => Token:  dq-Q8GJZTV2nfEPJarg00d:APA91bG9RxB7wHnC1tUOMg-vCJpkOoenrfJFBpi_acRZaIIxrB0VF9r_rENV7564VRZnPscadRhu1VwIL67NHzcQ4_gA-DmVKog7dXU7SsDGWC8_9RzvEJFZq3JIE8JQDWj4_cm41-Zx
    //Vaishali2 => Token:  efF2mCBxTcu4zrOMIiD12h:APA91bHvCn09tpq1rNMLlDYP9PWNkniV1CpBRfRyIIxlehQVobZDvM3-kVAtxQJUgP6zMnhpb291eOboVQ7_Z_boHzzVHSQMRb3kDLdjYYz4k8VOelP9FTaK3MyEqOOBSO-UcvS0gO6q
    Button Medical_help_button_in_fragment;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText title, msg;
    Context context;
    public Medical_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medical_, container, false);
        /*title = view.findViewById(R.id.title);
        msg = view.findViewById(R.id.message);*/

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
       // fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(context.getApplicationContext());

        Medical_help_button_in_fragment=view.findViewById(R.id.Medical_help_button_in_fragment);
        Medical_help_button_in_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getLastLocation();
                savedata();
                /*String title_send = title.getText().toString().trim();
                String message_send = msg.getText().toString().trim();
                if(!title.equals("") && !msg.equals("")){
                    FCMsend.pushNotification(
                            Medical_Fragment.this,
                            "NMAXJ6iQo-wN4F7vemv7Y:APA91bEuOFKMvKcNjkSnmY5E8XCxj_kEcWpDVhiDIw54IfLSJemRKvdEXgAi4e_HMMm4gwGrnh2R98Vn_oTxOGTMPuYC9JOLuPI0FY0xLqXbixMjBgEKVcbY7Nzy1rsYVaBqxnD3TfJ9",
                            title_send,
                            message_send
                    );
                }*/
                /*FirebaseMessaging.getInstance().getToken()
                        .addOnCompleteListener(new OnCompleteListener<String>() {
                            @Override
                            public void onComplete(@NonNull Task<String> task) {
                                if (!task.isSuccessful()) {
                                    //Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                                    return;
                                }

                                // Get new FCM registration token
                                String token = task.getResult();

                                System.out.println("token"+token);
                                // Log.d("token",token);
                            }
                        });*/
            }
        });
        return view;
    }
    private void getLastLocation() {
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)==
                PackageManager.PERMISSION_GRANTED){

            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if(location!=null){
                                Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                                List<Address> addresses = null;
                                try {
                                    addresses = geocoder.getFromLocation(location.getLatitude(),
                                            location.getLongitude(), 1);
                                    /*latitude.setText("Latitude"+addresses.get(0).getLatitude());
                                    longitude.setText(("Longitude"+addresses.get(0).getLongitude()));
                                    address.setText("Address" + addresses.get(0).getAddressLine(0));
                                    city.setText("City" + addresses.get(0).getLocality());
                                    country.setText("Country" + addresses.get(0).getCountryName());*/

                                    /*String title_send = title.getText().toString().trim();
                                    String message_send = msg.getText().toString().trim();*/

                                    String title_send;
                                    String message_send;
                                    message_send = "Address:" + addresses.get(0).getAddressLine(0);
                                            //+"City:" + addresses.get(0).getLocality()+",Country:" +
                                            //addresses.get(0).getCountryName();
                                    title_send="Medical help," + "Lati:"+addresses.get(0).getLatitude()+
                                            "Longi:"+addresses.get(0).getLongitude();

                                    if(!title_send.equals("") && !message_send.equals("")){
                                        FCMsend.pushNotification(
                                                Medical_Fragment.this,
                                                "NMAXJ6iQo-wN4F7vemv7Y:APA91bEuOFKMvKcNjkSnmY5E8XCxj_kEcWpDVhiDIw54IfLSJemRKvdEXgAi4e_HMMm4gwGrnh2R98Vn_oTxOGTMPuYC9JOLuPI0FY0xLqXbixMjBgEKVcbY7Nzy1rsYVaBqxnD3TfJ9",
                                                title_send,
                                                message_send
                                        );


                                    }


                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                            }
                        }
                    });
        }else {

            askPermission();
        }

    }

    private void savedata() {

        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)==
                PackageManager.PERMISSION_GRANTED){

            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if(location!=null){
                                Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                                List<Address> addresses = null;
                                try {
                                    addresses = geocoder.getFromLocation(location.getLatitude(),
                                            location.getLongitude(), 1);

                                    String title_send;
                                    String message_send;
                                    message_send = "Address:" + addresses.get(0).getAddressLine(0);
                                    //+"City:" + addresses.get(0).getLocality()+",Country:" +
                                      //      addresses.get(0).getCountryName();
                                    title_send="Medical help," + "Lati:"+addresses.get(0).getLatitude()+
                                            "Longi:"+addresses.get(0).getLongitude();

                                    final Handler handler = new Handler(Looper.getMainLooper());
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                            firebaseDatabase = FirebaseDatabase.getInstance();

                                            WomenLocationSendHelperClass helperClassss = new  WomenLocationSendHelperClass(title_send, message_send);
                                            databaseReference = firebaseDatabase.getReference("Locations");
                                            databaseReference.child("Address").setValue(helperClassss);
                                            Toast.makeText(getContext(), "Data Send", Toast.LENGTH_LONG).show();
                                        }
                                    },50);



                                           /* .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(getContext(), "Successfully Submitted", Toast.LENGTH_LONG).show();
                                               // Intent intent = new Intent(getContext(), DashboardActivity.class);
                                               // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                //startActivity(intent);
                                            }
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getContext(), "Not Submitted", Toast.LENGTH_LONG).show();
                                        }
                                    });*/


                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                            }
                        }

                    });
        }else {

            askPermission();
        }


    }

    private void askPermission() {
        ActivityCompat.requestPermissions((Activity) getContext(), new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION},1001);
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1001){
            if (grantResults.length > 0 && grantResults[0] ==PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }else {
                Toast.makeText(getContext(), "Required Permission", Toast.LENGTH_SHORT).show();

            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}