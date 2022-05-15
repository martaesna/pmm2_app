package com.example.myapplication.ui.menu;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.model.Account;
import com.example.myapplication.data.model.Device;
import com.example.myapplication.databinding.ActivityMenuBinding;
import com.example.myapplication.ui.add.AddActivity;
import com.example.myapplication.ui.chooseuser.ChooseUserActivity;
import com.example.myapplication.ui.configuration.ConfigurationActivity;
import com.example.myapplication.ui.delete.DeleteActivity;
import com.example.myapplication.ui.edit.EditActivity;
import com.example.myapplication.ui.help.HelpActivity;
import com.example.myapplication.ui.login.LoginActivity;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuActivity extends AppCompatActivity {
    private ActivityMenuBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());

        String info = getIntent().getExtras().getString("user_info");
        String[] user_info = info.split("/");
        String username = user_info[1];
        String account = getIntent().getExtras().getString("account");
        int num_users = getIntent().getExtras().getInt("num_users");
        int accountID = getIntent().getExtras().getInt("accountID");


        final Button exitButton = findViewById(R.id.sortir);
        final ImageButton configButton = findViewById(R.id.ButtonConfig);
        final ImageButton editButton = findViewById(R.id.BotonEdit);
        final ImageButton deleteButton = findViewById(R.id.BotonDelete);
        final ImageButton addButton = findViewById(R.id.BotonAdd);


        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("accounts");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()) {
                    Account accountToFind = ds.getValue(Account.class);
                    assert accountToFind != null;
                    if (accountToFind.getName().equals(account)) {
                        getIntent().putExtra("accountID", accountToFind.getAccountID());
                        getIntent().putExtra("numDevices", accountToFind.getUsers().size());
                        for (int i = 0; i < accountToFind.getUsers().size(); i++) {
                            if (accountToFind.getUsers().get(i).getName().equals(username)) {
                                getIntent().putExtra("userID", i);
                                for (Device device: accountToFind.getUsers().get(i).getDevices()) {
                                    if (device != null) {
                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                                ViewGroup.LayoutParams.WRAP_CONTENT
                                        );
                                        params.setMargins(200, 100, 0, 0);

                                        TextView textView = new TextView(getApplicationContext());
                                        textView.setWidth(700);
                                        textView.setHeight(240);
                                        textView.setText(device.getObjectName());
                                        textView.setBackgroundColor(Color.WHITE);
                                        textView.setGravity(Gravity.CENTER);
                                        textView.setTextColor(Color.BLUE);
                                        textView.setTextSize(30);

                                        LinearLayout ll = (LinearLayout)findViewById(R.id.menull);
                                        ll.addView(textView, params);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }
        });







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
