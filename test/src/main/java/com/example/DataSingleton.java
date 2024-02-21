package com.example;

public class DataSingleton {

    private static final DataSingleton instance = new DataSingleton();

    private String player1;
    private String player2;

    private DataSingleton(){}

    public static DataSingleton getInstance(){
        return instance;
    }

    public String getPlayer1(){
        return player1;
    }

    public void setPlayer1(String player1){
        this.player1 = player1;
    }
    
    public String getPlayer2(){
        return player2;
    }

    public void setPlayer2(String player2){
        this.player2 = player2;
    }
    
}
