package com.projects.luguu.modules;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.v1.WriteResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class MainApp {
    private static final MainApp INSTANCE = new MainApp();

    private Account activeAccount;
    FirebaseFirestore db;

    private MainApp(){
        this.activeAccount = null;
        db = FirebaseFirestore.getInstance();
    }

    public void addAccount(Account account){
        this.db.collection("users").document(account.getId()).set(account)
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    System.out.println("Added user with id: " + account.getId());
                }
            });

    }

    public static MainApp getInstance() {
        return INSTANCE;
    }

    public Account getActiveAccount() {
        return activeAccount;
    }

    public void setActiveAccount(Account activeAccount) {
        this.activeAccount = activeAccount;
    }


}
