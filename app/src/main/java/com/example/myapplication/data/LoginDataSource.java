package com.example.myapplication.data;

import androidx.annotation.NonNull;

import com.example.myapplication.data.model.Account;
import com.example.myapplication.data.model.LoggedInUser;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.digest.DigestUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            ArrayList<Account> accounts = new ArrayList<>();
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds: snapshot.getChildren()) {
                        Account account = ds.getValue(Account.class);
                        accounts.add(account);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    throw error.toException();
                }
            });

            String encript = DigestUtils.md5Hex(password);
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getName().equals(username) && accounts.get(i).getPassword().equals(encript)) {
                    LoggedInUser newUser =
                            new LoggedInUser(
                                    java.util.UUID.randomUUID().toString(),
                                    username);
                    return new Result.Success<>(newUser);
                }
            }
            Exception e = new Exception();
            return new Result.Error(new IOException("User or password incorrect", e));
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}