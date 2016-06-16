package com.example.as.flappybird;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by as on 2016/5/30.
 */
public class tube extends ViewGroup {
    int up ;
    int down ;
    int flag = 0;
    public tube(Context context){
        super(context);
    }
    public tube(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
    }

    public void setUp(int up){
        this.up = up;
    }
    public void setDown(int down){
        this.down = down;
    }
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        TextView t1 = (TextView) getChildAt(0);
        TextView t2 = (TextView) getChildAt(1);
        t1.layout(0,0,getWidth(),up);
        t2.layout(0,down,getWidth(),getHeight());
        t1.setBackgroundResource(R.drawable.pipe_down);
        t2.setBackgroundResource(R.drawable.pipe_up);
    }

}
