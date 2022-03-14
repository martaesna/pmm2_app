package com.example.myapplication.ui.delete;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityDeleteBinding;
import com.example.myapplication.databinding.ActivityMenuBinding;
import com.example.myapplication.ui.configuration.ConfigurationActivity;
import com.example.myapplication.ui.editaccount.EditAccountActivity;
import com.example.myapplication.ui.menu.MenuActivity;

public class DeleteActivity extends AppCompatActivity {
    private ActivityDeleteBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_delete);
        binding = ActivityDeleteBinding.inflate(getLayoutInflater());

        final Button exitButton = findViewById(R.id.sortir3);
        final Button deleteButton = findViewById(R.id.deleteButton);


        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteActivity.this.startActivity(new Intent(DeleteActivity.this, MenuActivity.class));
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteActivity.this.startActivity(new Intent(DeleteActivity.this, MenuActivity.class));
            }
        });
    }
}
