package com.example.as.flappybird;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by as on 2016/5/31.
 */
public class scoreview extends ViewGroup{
    int score = 0;
    int[] pic = new int[10];
    private TextView t1,t2 = null;
    int wid = getWidth();
    int hei = getHeight();
    public scoreview(Context context){
        super(context);
    }
    public scoreview(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        pic[0] = R.drawable.font_048;
        pic[1] = R.drawable.font_049;
        pic[2] = R.drawable.font_050;
        pic[3] = R.drawable.font_051;
        pic[4] = R.drawable.font_052;
        pic[5] = R.drawable.font_053;
        pic[6] = R.drawable.font_054;
        pic[7] = R.drawable.font_055;
        pic[8] = R.drawable.font_056;
        pic[9] = R.drawable.font_057;
    }
    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int wid = getWidth();
        int hei = getHeight();
        if(score<10) {
            TextView t1 = (TextView) getChildAt(0);
            t1.layout(wid / 4, hei / 8, wid * 3 / 4, hei * 7 / 8);
            t1.setBackgroundResource(pic[score]);
        }
    }
    public void relayout(){
        if(score<10){
            t1 = (TextView)getChildAt(0);
            t1.layout(wid/4,hei/8,wid*3/4,hei*7/8);
            t1.setBackgroundResource(pic[score]);
        }else if(score>=10){
            t1 = (TextView)getChildAt(0);
            t2 = (TextView)getChildAt(1);
            t1.layout(0, hei / 8, wid / 2 - 3, hei * 7 / 8);
            t1.setBackgroundResource(pic[score / 10]);
            t2.layout(wid/2+3,hei/8,wid,hei*7/8);
            t2.setBackgroundResource(pic[score%10]);
        }
    }
}
