package com.example.myapplication.ui.edit;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.model.Account;
import com.example.myapplication.data.model.Device;
import com.example.myapplication.data.model.User;
import com.example.myapplication.databinding.ActivityEditBinding;
import com.example.myapplication.databinding.ActivityMenuBinding;
import com.example.myapplication.ui.chooseuser.ChooseUserActivity;
import com.example.myapplication.ui.delete.DeleteActivity;
import com.example.myapplication.ui.menu.MenuActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity {
    private ActivityEditBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit);
        binding = ActivityEditBinding.inflate(getLayoutInflater());

        String info = getIntent().getExtras().getString("user_info");
        String[] user_info = info.split("/");
        String account = user_info[0];
        String username = user_info[1];

        final Button exitButton = findViewById(R.id.sortir5);
        final Button editButton = findViewById(R.id.editButton);

        List<String> devices = new ArrayList<String>();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("accounts");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()) {
                    Account accountToFind = ds.getValue(Account.class);
                    assert accountToFind != null;
                    if (accountToFind.getName().equals(account)) {
                        for (User user: accountToFind.getUsers()) {
                            if (user.getName().equals(username)) {
                                for (Device device: user.getDevices()) {
                                    devices.add(device.getObjectName());
                                }
                                Spinner spinnerDevices = findViewById(R.id.spinner_devices);

                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(EditActivity.this,
                                        android.R.layout.simple_spinner_item,
                                        devices); //selected item will look like a spinner set from XML
                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                                        .simple_spinner_dropdown_item);
                                spinnerDevices.setAdapter(spinnerArrayAdapter);
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



        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditActivity.this, MenuActivity.class);
                intent.putExtra("user_info", info);
                EditActivity.this.startActivity(intent);
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: CANVIAR VALOR BBDD
                Toast.makeText(getApplicationContext(), "Editat correctament", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EditActivity.this, MenuActivity.class);
                intent.putExtra("user_info", info);
                EditActivity.this.startActivity(intent);
            }
        });
    }
}
