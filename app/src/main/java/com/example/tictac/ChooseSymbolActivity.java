package com.example.tictac;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseSymbolActivity extends AppCompatActivity implements View.OnTouchListener {
    private ImageView BackBtn , CrossImg , CrossRadioImg , CircleImg , CircleRadioImg;
    private Button ContinueBtn;

    int PICK_SIDE ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_choose_symbol);


        BackBtn= (ImageView) findViewById(R.id.ai_pick_side_back_btn);
        CrossImg= (ImageView) findViewById(R.id.ai_pick_side_cross_img);
        CircleImg= (ImageView) findViewById(R.id.ai_pick_side_circle_img);
        CrossRadioImg= (ImageView) findViewById(R.id.ai_pick_side_cross_radio);
        CircleRadioImg= (ImageView) findViewById(R.id.ai_pick_side_circle_radio);

        ContinueBtn = (Button) findViewById(R.id.ai_pick_side_continue_btn);

        CrossRadioImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PICK_SIDE = 0;
                CrossRadioImg.setImageResource(R.drawable.radio_button_checked);
                CircleRadioImg.setImageResource(R.drawable.radio_button_unchecked);
                CircleImg.setAlpha(0.3f);
                CrossImg.setAlpha(1.0f);
            }
        });

        CircleRadioImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PICK_SIDE= 1;
                CircleRadioImg.setImageResource(R.drawable.radio_button_checked);
                CrossRadioImg.setImageResource(R.drawable.radio_button_unchecked);
                CrossImg.setAlpha(0.3f);
                CircleImg.setAlpha(1.0f);
            }
        });

        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ContinueBtn.setOnTouchListener(this);
        ContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(  ChooseSymbolActivity.this, RobotMode.class);
                if(PICK_SIDE==0) {
                    intent.putExtra("ps", "x");
                }else {
                    intent.putExtra("ps", "o");
                }
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v == ContinueBtn) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                v.setAlpha(0.5f);
            } else {
                v.setAlpha(1f);
            }
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

