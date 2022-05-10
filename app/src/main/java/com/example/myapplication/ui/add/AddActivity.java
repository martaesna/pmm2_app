package com.example.myapplication.ui.add;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.model.Account;
import com.example.myapplication.data.model.User;
import com.example.myapplication.databinding.ActivityAddBinding;
import com.example.myapplication.databinding.ActivityMenuBinding;
import com.example.myapplication.ui.chooseuser.ChooseUserActivity;
import com.example.myapplication.ui.configuration.ConfigurationActivity;
import com.example.myapplication.ui.delete.DeleteActivity;
import com.example.myapplication.ui.edit.EditActivity;
import com.example.myapplication.ui.login.LoginActivity;
import com.example.myapplication.ui.menu.MenuActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddActivity extends AppCompatActivity {
    private ActivityAddBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add);
        binding = ActivityAddBinding.inflate(getLayoutInflater());

        final Button exitButton = findViewById(R.id.sortir4);
        final Button addButton = findViewById(R.id.addButton);


        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddActivity.this.startActivity(new Intent(AddActivity.this, MenuActivity.class));
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




    }
}
