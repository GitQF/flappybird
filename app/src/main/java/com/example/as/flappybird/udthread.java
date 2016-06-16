package com.example.as.flappybird;

import android.os.Handler;
import android.os.Message;

/**
 * Created by as on 2016/5/30.
 */
public class udthread extends Thread{
    private Handler handler = null;
    int y = 0;
    int ud = 0;
    int speeddown = 8;
    int speedup = 25;
    public udthread(Handler handler){
        this.handler = handler;
    }
    public void setY(int y){
        this.y = y;
    }
    public void run(){
        while (true) {
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Message msg = new Message();
            if(ud == 0) {
                speeddown+=2;
                y =  (y + speeddown);
                msg.arg1 = y;
                handler.sendMessage(msg);
            }else if(ud == 1){
                speedup -=2;
                speeddown = 8;
                y = (y - speedup);
                msg.arg1 = y;
                handler.sendMessage(msg);
                if(speedup < 8){
                    ud = 0;
                    speedup = 25;
                }
            }
            if(y<0){
                y = 0;
            }
            if(y>900){
                msg = new Message();
                msg.arg1 = 900;
                handler.sendMessage(msg);
                break;
            }
        }
    }
}
