package com.example.as.flappybird;

import android.os.Handler;
import android.os.Message;

/**
 * Created by as on 2016/5/30.
 */
public class tubethread extends Thread{
    Handler handler = null;
    int flag = 0;
    public tubethread(Handler handler){
        this.handler = handler;
    }
    public void run(){
        int n = 0;
        while (true) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            n++;
            Message msg = new Message();
            msg.arg1 = n;
            handler.sendMessage(msg);
            if(flag == 1){
                break;
            }
        }
    }

}
