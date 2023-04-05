package com.example.tictac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Dashborad extends AppCompatActivity {
    private Button WithAFriendBtn , WithRobot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_game_menu);
        WithAFriendBtn = (Button) findViewById(R.id.friend);
        WithRobot = (Button) findViewById(R.id.robot);
        WithAFriendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashborad.this,FriendMode.class);
                startActivity(i);
            }
        });

        WithRobot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(Dashborad.this,ChooseSymbolActivity.class);
                startActivity(j);
            }
        });

    }

}
