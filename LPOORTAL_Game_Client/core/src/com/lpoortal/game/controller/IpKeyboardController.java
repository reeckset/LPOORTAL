package com.lpoortal.game.controller;

import com.lpoortal.game.LPOORTAL_Game;
import com.lpoortal.game.network.MessageManager;

public class IpKeyboardController {

    private String input;
    private long ip;

    public IpKeyboardController(){
        input = "";
    }

    public void addKey(int i){
        if(input.length() < 8) {
            ip += i * Math.pow(16, 7-input.length());
            input += Integer.toHexString(i).toUpperCase();
        }
    }

    public void erase(){
        if(input.length() > 0) {
            input = input.substring(0, input.length() - 1);
            ip -= ip % Math.pow(16, 8-input.length());
        }
    }

    public void submit(){
        if(input.length() == 8) {
            String hostname = "";
            for (int i = 0; i < 4; i++) {
                long tmp = ip;
                tmp >>>= i * 8;
                tmp &= 0xFF;
                hostname = tmp + ((hostname == "") ? "" : ("." + hostname));
            }
            MessageManager.getInstance().start(hostname);
        }else{
            System.out.println("Hostname must be input fully");
        }
    }

    public int getValAt(int i){
        long tmp = ip;
        tmp >>>= (7-i) * 4;
        tmp &= 0xF;
        return (int) tmp;
    }

    @Override
    public String toString(){
        return input;
    }
}
