package com.example.myapplication.ui.editprofile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityEditprofileBinding;
import com.example.myapplication.ui.configuration.ConfigurationActivity;
import com.example.myapplication.ui.editaccount.EditAccountActivity;
import com.example.myapplication.ui.help.HelpActivity;

public class EditProfileActivity extends AppCompatActivity {
    private ActivityEditprofileBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_editprofile);
        binding = ActivityEditprofileBinding.inflate(getLayoutInflater());

        final Button editProfileButton = findViewById(R.id.editProfileButton);
        final Button backButton = findViewById(R.id.sortir2);

        String info = getIntent().getExtras().getString("user_info");
        String[] user_info = info.split("/");
        String account = user_info[0];
        String username = user_info[1];
        int num_users = getIntent().getExtras().getInt("num_users");
        int accountID = getIntent().getExtras().getInt("accountID");

        ImageButton helpButton = findViewById(R.id.help);

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentButton = new Intent(EditProfileActivity.this, HelpActivity.class);
                intentButton.putExtra("num_users", num_users);
                intentButton.putExtra("user_info", info);
                intentButton.putExtra("account", account);
                intentButton.putExtra("accountID", accountID);
                intentButton.putExtra("back", "editprofile");
                EditProfileActivity.this.startActivity(intentButton);
            }
        });


        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: change profile firebase
                Intent intent = new Intent(EditProfileActivity.this, ConfigurationActivity.class);
                intent.putExtra("num_users", num_users);
                intent.putExtra("user_info", info);
                intent.putExtra("account", account);
                intent.putExtra("accountID", accountID);
                EditProfileActivity.this.startActivity(intent);            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this, ConfigurationActivity.class);
                intent.putExtra("num_users", num_users);
                intent.putExtra("user_info", info);
                intent.putExtra("account", account);
                intent.putExtra("accountID", accountID);
                EditProfileActivity.this.startActivity(intent);
            }
        });
    }
}
