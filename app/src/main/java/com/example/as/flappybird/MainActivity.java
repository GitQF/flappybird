package com.example.as.flappybird;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int l1 = 700;int l2 = l1+getrandom(300,700);int l3 = l2+getrandom(300,700);
    private int mindis = 220;int maxdis = 320;
    private int score = 0;
    private int speed = 20;
    private TextView bird = null;
    private TextView scoreview = null;
    private tube tube1,tube2,tube3 = null;
    private birdthread flybt = null;
    private udthread udbt = null;
    private tubethread tubethread1 = null;
    private tubethread tubethread2 = null;
    private Handler flyhandler = null;
    private Handler udhandler = null;
    private Handler tubehandler = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bird = (TextView)findViewById(R.id.bird);
        tube1 = (tube)findViewById(R.id.tube);
        tube2 = (tube)findViewById(R.id.tube2);
        tube3 = (tube)findViewById(R.id.tube3);
        tube1.setUp(getrandom(100,700));
        tube1.setDown(tube1.up+getrandom(mindis,maxdis));
        tube2.setUp(getrandom(100,700));
        tube2.setDown(tube2.up+getrandom(mindis,maxdis));
        tube3.setUp(getrandom(100,700));
        tube3.setDown(tube3.up+getrandom(mindis,maxdis));
        flyhandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.arg1){
                    case 0:
                        bird.setBackgroundResource(R.drawable.bird1_0);
                        break;
                    case 1:
                        bird.setBackgroundResource(R.drawable.bird1_1);
                        break;
                    case 2:
                        bird.setBackgroundResource(R.drawable.bird1_2);
                        break;
                }
            }
        };
        udhandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.arg1 == 900){
                    Toast.makeText(MainActivity.this,"Game over",Toast.LENGTH_SHORT).show();
                }else {
                    setbirdy(msg.arg1);
                }
            }
        };
        tubehandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(!isAlive(bird,tube1)||!isAlive(bird,tube2)||!isAlive(bird,tube3)){
                    flybt.count = -100;
                    tubethread1.flag = 1;
                }
                speed = speed+msg.arg1/100;
                l1 -= speed;
                l2 -= speed;
                l3 -= speed;
                if (l1 + tube1.getWidth() < 0) {
                    l1 = l3 + getrandom(300, 700);
                    tube1.setUp(getrandom(100, 700));
                    tube1.setDown(tube1.up + getrandom(mindis, maxdis));
                    tube1.flag = 0;
                }
                if (l2 + tube2.getWidth() < 0) {
                    l2 = l1 + getrandom(300, 700);
                    tube2.setUp(getrandom(100, 700));
                    tube2.setDown(tube2.up + getrandom(mindis, maxdis));
                    tube2.flag = 0;
                }
                if (l3 + tube3.getWidth() < 0) {
                    l3 = l2 + getrandom(300, 700);
                    tube3.setUp(getrandom(100, 700));
                    tube3.setDown(tube3.up + getrandom(mindis, maxdis));
                    tube3.flag = 0;
                }
                    tube1.layout(l1 , 0, l1 + tube1.getWidth(), tube1.getHeight());
                    tube2.layout(l2 , 0, l2 + tube2.getWidth(), tube2.getHeight());
                    tube3.layout(l3 , 0, l3 + tube3.getWidth(), tube3.getHeight());
                }
        };

        flybt = new birdthread(flyhandler);
        flybt.start();
        udbt = new udthread(udhandler);
        udbt.setY((int)bird.getY());
        udbt.start();
        tubethread1 = new tubethread(tubehandler);
        tubethread1.start();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && flybt.count > 0){
            udbt.ud = 1;
            udbt.speedup = 25;
        }
        return super.onTouchEvent(event);
    }
    public void setbirdy(int y){
        int x = (int)bird.getX();
        int w = bird.getWidth();
        int h = bird.getHeight();
        bird.layout(x, y, x + w, y + h);
    }
    private int getrandom(int a,int b){
        int n = (int)(b*Math.random());
        if(n < a){
            return getrandom(a,b);
        }else{
            return n;
        }
    }
    private boolean isAlive(TextView bird,tube tube){
        if(tube.getX() + 25 < bird.getX() + bird.getWidth() && tube.getX() + tube.getWidth() - 25 > bird.getX()){
            if(bird.getY() + 25 < tube.up || bird.getY() + bird.getHeight() - 25 > tube.down){
                return false;
            }
        }
        return true;
    }
    private boolean pass(TextView bird,tube tube){
        if (tube.getX() + tube.getWidth() < bird.getX() && tube.flag == 0) {
            score++;
            tube1.flag = 1;
            scoreview.setText(String.valueOf(score));
            return true;
        }
        return false;
    }
}
