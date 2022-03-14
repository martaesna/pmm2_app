package com.example.myapplication.ui.edit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityEditBinding;
import com.example.myapplication.databinding.ActivityMenuBinding;
import com.example.myapplication.ui.delete.DeleteActivity;
import com.example.myapplication.ui.menu.MenuActivity;

public class EditActivity extends AppCompatActivity {
    private ActivityEditBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit);
        binding = ActivityEditBinding.inflate(getLayoutInflater());

        final Button exitButton = findViewById(R.id.sortir5);
        final Button editButton = findViewById(R.id.editButton);


        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditActivity.this.startActivity(new Intent(EditActivity.this, MenuActivity.class));
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditActivity.this.startActivity(new Intent(EditActivity.this, MenuActivity.class));
            }
        });
    }
}
