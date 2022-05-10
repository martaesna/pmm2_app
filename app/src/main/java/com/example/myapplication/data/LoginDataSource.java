package com.example.myapplication.data;

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
            System.out.println("hola");
            ArrayList<Account> accounts = new ArrayList<>();
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference("accounts");

            ValueEventListener accountsListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    System.out.println("hola");
                    for (DataSnapshot ds: dataSnapshot.getChildren()) {
                        Account account = ds.getValue(Account.class);
                        accounts.add(account);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    throw databaseError.toException();
                }
            };
            reference.addValueEventListener(accountsListener);

            String encrypt = DigestUtils.md5Hex(password);
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getName().equals(username) && accounts.get(i).getPassword().equals(encrypt)) {
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