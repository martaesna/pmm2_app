package com.example.myapplication.ui.chooseuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.model.Account;
import com.example.myapplication.databinding.ActivityChooseuserBinding;
import com.example.myapplication.ui.menu.MenuActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChooseUserActivity extends AppCompatActivity {

    private ActivityChooseuserBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chooseuser);
        binding = ActivityChooseuserBinding.inflate(getLayoutInflater());

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("accounts");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()) {
                    Account account = ds.getValue(Account.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }
        });

        final Button martaButton = findViewById(R.id.martaButton);
        martaButton.setEnabled(true);
        final Button marioButton = findViewById(R.id.marioButton);
        marioButton.setEnabled(true);
        final Button laiaButton = findViewById(R.id.laiaButton);
        laiaButton.setEnabled(true);
        final Button silviaButton = findViewById(R.id.silviaButton);
        silviaButton.setEnabled(true);


        martaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseUserActivity.this.startActivity(new Intent(ChooseUserActivity.this, MenuActivity.class));
            }
        });

        marioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseUserActivity.this.startActivity(new Intent(ChooseUserActivity.this, MenuActivity.class));
            }
        });

        laiaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseUserActivity.this.startActivity(new Intent(ChooseUserActivity.this, MenuActivity.class));
            }
        });

        silviaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseUserActivity.this.startActivity(new Intent(ChooseUserActivity.this, MenuActivity.class));
            }
        });

    }

}
