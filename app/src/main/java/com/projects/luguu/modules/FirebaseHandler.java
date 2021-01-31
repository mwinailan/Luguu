package com.projects.luguu.modules;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class FirebaseHandler {
    private FirebaseFirestore db;
    private AccountsContainer accountsContainer;
    private PostsContainer postsContainer;

    public FirebaseHandler(){
        db = FirebaseFirestore.getInstance();
        accountsContainer = AccountsContainer.getInstance();
        postsContainer = PostsContainer.getInstance();
    }

    public void getAllAccount(){
        CollectionReference collectionReference = db.collection("users");
        new Thread(){
            public void run(){
                System.out.println("Getting all account from firebase");
                collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            System.out.println("Task is successfull");
                            for(QueryDocumentSnapshot document: task.getResult()){
                                System.out.println("Document: " + document.getData());
                                accountsContainer.addAccount(Account.parseFromMapData(document.getData()));
                            }
                            accountsContainer.print();
                        }else {
                            System.out.println("Error in updating document");
                        }
                    }
                });
            }
        }.start();
    }

    public void addHelpPost(HelpPost helpPost){
        new Thread(){
            public void run(){
                db.collection("helpPosts").document(helpPost.getPostid().toString()).set(helpPost)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                System.out.println("Added helppost with id: " + helpPost.getPostid());
                                postsContainer.addPost(helpPost);
                            }
                        });
            }
        }.start();
    }

    public AccountsContainer getAccountsContainer() {
        return accountsContainer;
    }

    public void getAllHelpPost(){
        CollectionReference collectionReference = db.collection("helpPosts");
        new Thread(){
            public void run(){
                collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document: task.getResult()){
                                postsContainer.addPost(HelpPost.parseFromMapData(document.getData()));
                            }
                        }else {
                            System.out.println("Error in updating document");
                        }
                    }
                });
            }
        }.start();
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

    public void update(){
        getAllAccount();
        getAllHelpPost();
    }

}
