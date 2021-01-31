package com.projects.luguu.modules;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class FirebaseHandler {
    private FirebaseFirestore db;
    private AccountsContainer accountsContainer;

    public FirebaseHandler(){
        db = FirebaseFirestore.getInstance();
        accountsContainer = AccountsContainer.getInstance();
    }

    public void getAccount(String accountID){
        DocumentReference docRef = db.collection("users").document(accountID);
        new Thread(){
            public void run(){
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        if(task.isSuccessful()){
                            System.out.println("Got data: " + document.getData());
                            accountsContainer.addAccount(Account.parseFromMapData(document.getData()));
                        }
                    }
                });
            }
        }.start();
    }

    public void addAccount(Account account){
//        Map<String, Object> accMap = new HashMap <>();
//        accMap.put("currentActivity", )


        new Thread(){
          public void run(){
              db.collection("users").document(account.getId()).set(account)
                      .addOnSuccessListener(new OnSuccessListener<Void>() {
                          @Override
                          public void onSuccess(Void aVoid) {
                              System.out.println("Added user with id: " + account.getId());
                          }
                      });
          }
        }.start();
    }

}
