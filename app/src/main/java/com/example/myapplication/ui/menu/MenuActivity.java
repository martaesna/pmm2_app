package com.example.myapplication.ui.menu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMenuBinding;
import com.example.myapplication.ui.add.AddActivity;
import com.example.myapplication.ui.chooseuser.ChooseUserActivity;
import com.example.myapplication.ui.configuration.ConfigurationActivity;
import com.example.myapplication.ui.delete.DeleteActivity;
import com.example.myapplication.ui.edit.EditActivity;
import com.example.myapplication.ui.help.HelpActivity;
import com.example.myapplication.ui.login.LoginActivity;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuActivity extends AppCompatActivity {
    private ActivityMenuBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());

        String info = getIntent().getExtras().getString("user_info");
        String account = getIntent().getExtras().getString("account");
        int num_users = getIntent().getExtras().getInt("num_users");
        int accountID = getIntent().getExtras().getInt("accountID");


        final Button exitButton = findViewById(R.id.sortir);
        final ImageButton configButton = findViewById(R.id.ButtonConfig);
        final ImageButton editButton = findViewById(R.id.BotonEdit);
        final ImageButton deleteButton = findViewById(R.id.BotonDelete);
        final ImageButton addButton = findViewById(R.id.BotonAdd);

        ImageButton helpButton = findViewById(R.id.help);

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentButton = new Intent(MenuActivity.this, HelpActivity.class);
                intentButton.putExtra("num_users", num_users);
                intentButton.putExtra("user_info", info);
                intentButton.putExtra("account", account);
                intentButton.putExtra("accountID", accountID);
                intentButton.putExtra("back", "menu");
                MenuActivity.this.startActivity(intentButton);
            }
        });


        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
                MenuActivity.this.startActivity(intent);
            }
        });
        configButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ConfigurationActivity.class);
                intent.putExtra("num_users", num_users);
                intent.putExtra("user_info", info);
                intent.putExtra("account", account);
                intent.putExtra("accountID", accountID);
                MenuActivity.this.startActivity(intent);
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, EditActivity.class);
                intent.putExtra("num_users", num_users);
                intent.putExtra("user_info", info);
                intent.putExtra("account", account);
                intent.putExtra("accountID", accountID);
                MenuActivity.this.startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, DeleteActivity.class);
                intent.putExtra("num_users", num_users);
                intent.putExtra("user_info", info);
                intent.putExtra("account", account);
                intent.putExtra("accountID", accountID);
                MenuActivity.this.startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task<Void> mDatabase = FirebaseDatabase.getInstance().getReference("data").child("startListening").setValue(true);
                Intent intent = new Intent(MenuActivity.this, AddActivity.class);
                intent.putExtra("num_users", num_users);
                intent.putExtra("user_info", info);
                intent.putExtra("account", account);
                intent.putExtra("accountID", accountID);
                MenuActivity.this.startActivity(intent);
            }
        });

    }
}
