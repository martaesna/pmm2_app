package com.example.myapplication.ui.edit;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.example.myapplication.ui.add.AddActivity;
import com.example.myapplication.ui.chooseuser.ChooseUserActivity;
import com.example.myapplication.ui.delete.DeleteActivity;
import com.example.myapplication.ui.help.HelpActivity;
import com.example.myapplication.ui.menu.MenuActivity;
import com.google.android.gms.tasks.Task;
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
        int num_users = getIntent().getExtras().getInt("num_users");
        int accountID = getIntent().getExtras().getInt("accountID");

        ImageButton helpButton = findViewById(R.id.help);

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentButton = new Intent(EditActivity.this, HelpActivity.class);
                intentButton.putExtra("num_users", num_users);
                intentButton.putExtra("user_info", info);
                intentButton.putExtra("account", account);
                intentButton.putExtra("accountID", accountID);
                intentButton.putExtra("back", "edit");
                EditActivity.this.startActivity(intentButton);
            }
        });

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
                        getIntent().putExtra("accountID", accountToFind.getAccountID());
                        for (int i = 0; i < accountToFind.getUsers().size(); i++) {
                            if (accountToFind.getUsers().get(i).getName().equals(username)) {
                                getIntent().putExtra("userID", i);
                                for (Device device: accountToFind.getUsers().get(i).getDevices()) {
                                    if (device != null) {
                                        devices.add(device.getObjectName());
                                        getIntent().putExtra(device.getObjectName(), device.getDeviceID());
                                    }
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
                intent.putExtra("num_users", num_users);
                intent.putExtra("user_info", info);
                intent.putExtra("account", account);
                intent.putExtra("accountID", accountID);
                EditActivity.this.startActivity(intent);
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDeviceName();
                Toast.makeText(getApplicationContext(), "Editat correctament", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EditActivity.this, MenuActivity.class);
                intent.putExtra("num_users", num_users);
                intent.putExtra("user_info", info);
                intent.putExtra("account", account);
                intent.putExtra("accountID", accountID);
                EditActivity.this.startActivity(intent);
            }

            private void editDeviceName() {
                Spinner spinnerDevices = findViewById(R.id.spinner_devices);
                String deviceToDelete = spinnerDevices.getSelectedItem().toString();
                int deviceID = getIntent().getExtras().getInt(deviceToDelete);
                int userID = getIntent().getExtras().getInt("userID");
                int accountID = getIntent().getExtras().getInt("accountID");
                EditText myEditText =  (EditText) findViewById(R.id.edit_device_name);
                String newObjectName = myEditText.getText().toString();
                Task<Void> mDatabase = FirebaseDatabase.getInstance().getReference("accounts").child(String.valueOf(accountID)).child("users").child(String.valueOf(userID)).child("devices").child(String.valueOf(deviceID)).child("objectName").setValue(newObjectName);
            }
        });
    }
}
