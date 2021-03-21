package com.arunprashanna.health_made_simple;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity {
    TextView farenheitval,celsiusval;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        farenheitval=(TextView)findViewById(R.id.farenheitval);
        celsiusval=(TextView)findViewById(R.id.celsiusval);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String c = snapshot.child("celsius").getValue().toString();
                String f = snapshot.child("farenheit").getValue().toString();
                celsiusval.setText(c+"  C");
                farenheitval.setText(f+"  F");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}