package com.projects.luguu.modules;

public final class MainApp {

    private static final MainApp INSTANCE = new MainApp();

    private Account activeAccount;
    FirebaseHandler firebaseHandler;

    private MainApp(){
        this.activeAccount = null;
        firebaseHandler = new FirebaseHandler();
    }

    public void getAccount(String accountID){
        firebaseHandler.getAccount(accountID);
    }

    public void addAccount(Account account){
        firebaseHandler.addAccount(account);
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