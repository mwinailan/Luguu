package com.projects.luguu.modules;

import java.util.ArrayList;

public final class AccountsContainer {
    private static final AccountsContainer INSTANCE = new AccountsContainer();

    private static ArrayList<Account> accounts;
    private FirebaseHandler firebaseHandler;

    private AccountsContainer(){
        if (accounts == null) {
            accounts = new ArrayList<>();
        }
        firebaseHandler = new FirebaseHandler();
    }

    public void updateContent(){

    }

    public void addAccount(Account account){
        accounts.add(account);
        System.out.println("        Contents in accountsContainer: \n");
        for(Account acc: accounts){
            System.out.println(acc.toString() + "\n");
        }
    }

    public Account getAccount(String accountID){
        for(Account acc: accounts){
            if(acc.getId() == accountID){
                return acc;
            }
        }
        return null;
    }

    public static AccountsContainer getInstance() {
        return INSTANCE;
    }
}
