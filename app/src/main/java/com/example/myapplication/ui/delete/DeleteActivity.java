package com.example.myapplication.ui.delete;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.model.Account;
import com.example.myapplication.data.model.Device;
import com.example.myapplication.data.model.User;
import com.example.myapplication.databinding.ActivityDeleteBinding;
import com.example.myapplication.databinding.ActivityMenuBinding;
import com.example.myapplication.ui.configuration.ConfigurationActivity;
import com.example.myapplication.ui.edit.EditActivity;
import com.example.myapplication.ui.editaccount.EditAccountActivity;
import com.example.myapplication.ui.menu.MenuActivity;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DeleteActivity extends AppCompatActivity {
    private ActivityDeleteBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_delete);
        binding = ActivityDeleteBinding.inflate(getLayoutInflater());

        final Button exitButton = findViewById(R.id.sortir3);
        final Button deleteButton = findViewById(R.id.deleteButton);

        String info = getIntent().getExtras().getString("user_info");
        String[] user_info = info.split("/");
        String account = user_info[0];
        String username = user_info[1];

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
                        getIntent().putExtra("numDevices", accountToFind.getUsers().size());
                        for (int i = 0; i < accountToFind.getUsers().size(); i++) {
                            if (accountToFind.getUsers().get(i).getName().equals(username)) {
                                getIntent().putExtra("userID", i);
                                for (Device device: accountToFind.getUsers().get(i).getDevices()) {
                                    if (device != null) {
                                        devices.add(device.getObjectName());
                                        getIntent().putExtra(device.getObjectName(), device.getDeviceID());
                                    }
                                }
                                Spinner spinnerDevices = findViewById(R.id.spinner_devices2);

                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(DeleteActivity.this,
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
                Intent intent = new Intent(DeleteActivity.this, MenuActivity.class);
                intent.putExtra("user_info", info);
                DeleteActivity.this.startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(DeleteActivity.this)
                        .setTitle("Confirmació")
                        .setMessage("Segur que vols eliminar el dispositiu?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Spinner spinnerDevices = findViewById(R.id.spinner_devices2);
                                String deviceToDelete = spinnerDevices.getSelectedItem().toString();
                                int deviceID = getIntent().getExtras().getInt(deviceToDelete);
                                int userID = getIntent().getExtras().getInt("userID");
                                int accountID = getIntent().getExtras().getInt("accountID");
                                Task<Void> mDatabase = FirebaseDatabase.getInstance().getReference("accounts").child(String.valueOf(accountID)).child("users").child(String.valueOf(userID)).child("devices").child(String.valueOf(deviceID)).removeValue();
                                updateDeviceIDs(deviceID, userID, accountID);
                                Task<Void> mDatabase3 = FirebaseDatabase.getInstance().getReference("accounts").child(String.valueOf(accountID)).child("users").child(String.valueOf(userID)).child("numDevices").setValue(getIntent().getExtras().getInt("numDevices"));
                                Toast.makeText(getApplicationContext(), "Eliminat correctament", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(DeleteActivity.this, MenuActivity.class);
                                intent.putExtra("user_info", info);
                                DeleteActivity.this.startActivity(intent);
                            }

                            private void updateDeviceIDs(int deviceID, int userID, int accountID) {
                                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("accounts");
                                mDatabase.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for (DataSnapshot ds: snapshot.getChildren()) {
                                            Account accountToFind = ds.getValue(Account.class);
                                            assert accountToFind != null;
                                            if (accountToFind.getName().equals(account)) {
                                                for (int i = 0; i < accountToFind.getUsers().size(); i++) {
                                                    if (accountToFind.getUsers().get(i).getName().equals(username)) {
                                                        for (Device device: accountToFind.getUsers().get(i).getDevices()) {
                                                            if (device != null) {
                                                                if (device.getDeviceID() > deviceID) {
                                                                    Task<Void> mDatabase2 = FirebaseDatabase.getInstance().getReference("accounts").child(String.valueOf(accountID)).child("users").child(String.valueOf(userID)).child("devices").child(String.valueOf(device.getDeviceID())).removeValue();
                                                                    device.setDeviceID(device.getDeviceID() - 1);
                                                                    Task<Void> mDatabase = FirebaseDatabase.getInstance().getReference("accounts").child(String.valueOf(accountID)).child("users").child(String.valueOf(userID)).child("devices").child(String.valueOf(device.getDeviceID())).setValue(device);

                                                                }
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
                            }
                        })
                        .setNegativeButton(R.string.cancelar, null).show();

            }

        });
    }
}
