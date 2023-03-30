package com.example.tictac;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;



public class CelebrationView  extends RelativeLayout implements View.OnClickListener {



    public CelebrationView(Context context) {
        super(context);
        init();
    }

    public CelebrationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CelebrationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.celebrate_dialog, this);


    }

    @Override
    public void onClick(View view) {

    }


}


