package com.example.tictac;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;


        import android.content.Context;
        import android.util.AttributeSet;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.RelativeLayout;
        import android.widget.Toast;

public class GrilleJeuCustomView extends RelativeLayout implements View.OnClickListener {

    private ImageView[] mCases;

    public GrilleJeuCustomView(Context context) {
        super(context);
        init();
    }

    public GrilleJeuCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GrilleJeuCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.grille_jeu, this);

        mCases = new ImageView[9];
        mCases[0] = findViewById(R.id.btnn1);
        mCases[1] = findViewById(R.id.btn2);
        mCases[2] = findViewById(R.id.btn3);
        mCases[3] = findViewById(R.id.btn4);
        mCases[4] = findViewById(R.id.btn5);
        mCases[5] = findViewById(R.id.btn6);
        mCases[6] = findViewById(R.id.btn7);
        mCases[7] = findViewById(R.id.btn8);
        mCases[8] = findViewById(R.id.btn9);

        for (ImageView mCase : mCases) {
            mCase.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {

    }

    public ImageView[] getmCases() {
        return mCases;
    }
}

