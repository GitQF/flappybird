package com.example.as.flappybird;

import android.os.Handler;
import android.os.Message;

public class birdthread extends Thread{
    int count = 0;
    private Handler handler = null;
    birdthread(Handler handler){
        this.handler = handler;
    }
    public void run(){
        while (true) {
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
            count++;
            Message msg = new Message();
            msg.arg1 = count % 3;
            handler.sendMessage(msg);
            if(count < 0){
                break;
            }
        }
    }
}
