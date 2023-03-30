package com.example.tictac;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Dialog quitdialog;

    String pick_side;
    int[] arr = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    int i;
    int countX, countO, countD;
    TextView txt1, txt2, txt3;
    Button btnreset;
    Switch sound_switch;
    ImageView playerTurn;
    ImageView[] mCases;
    private boolean botEnabled = false;
    private boolean soundEnabled = false;

    private MediaPlayer xPlayer,oPlayer,launcherPlayer,tada;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        quitdialog = new Dialog(this);
        View view = getLayoutInflater().inflate(R.layout.quit_dialog, null);
        builder.setView(view);
        GrilleJeuCustomView grilleJeuCustomView = findViewById(R.id.grilleJeu);
        mCases = grilleJeuCustomView.getmCases();

        for (ImageView mCase : mCases) {
            mCase.setOnClickListener(this);
        }




        btnreset = (Button) findViewById(R.id.btnreset);
        sound_switch = (Switch) findViewById(R.id.sound_switch);

        txt1 = (TextView) findViewById(R.id.txtXWins);
        txt2 = (TextView) findViewById(R.id.txtOWins);
        playerTurn = findViewById(R.id.playerTurn);
        Button quitBtn = findViewById(R.id.quit);
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Appliquer l'animation au bouton
                Animation animation = getAnimation(3);
              //  animation.setRepeatCount(Animation.ABSOLUTE);
                grilleJeuCustomView.startAnimation(animation);

                //Animation animation2 = getAnimation(5);
              //  grilleJeuCustomView.startAnimation(animation2);

                Animation bounceAnimation = getAnimation(3);
                bounceAnimation.setRepeatCount(Animation.ABSOLUTE);

                resetGame();
                countX = 0;
                countO = 0;
                countD = 0;
                txt1.setText(String.valueOf(countX));
                txt2.setText(String.valueOf(countO));
               // txt3.setText(String.valueOf(countD));

            }
        });

        Intent j = getIntent();
        String val =j.getStringExtra("ps");


        if(val.equals("x")){
            //Toast.makeText(MainActivity.this, "Your message here", Toast.LENGTH_SHORT).show();
            pick_side="X";
            playerTurn.setImageResource(R.drawable.cross);
        }else{
            pick_side="O";
            playerTurn.setImageResource(R.drawable.circle);
        }

        botEnabled = true;


        tada = MediaPlayer.create(this, R.raw.tada);
        xPlayer = MediaPlayer.create(this, R.raw.x);
        oPlayer = MediaPlayer.create(this, R.raw.o);


        launcherPlayer = MediaPlayer.create(this, R.raw.music);
        launcherPlayer.setLooping(true);
        launcherPlayer.start();

        sound_switch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view) {
                if (!soundEnabled){
                    launcherPlayer.pause();
                    soundEnabled = true;
               }else{
                    launcherPlayer.start();
                    soundEnabled = false;
                }

            }
        });

        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = builder.create();
                dialog.show();
                Button confirmButton = view.findViewById(R.id.quit_btn);
                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        launcherPlayer.pause();
                        soundEnabled = true;
                        Intent intent = new Intent(MainActivity.this, Dashborad.class);
                        startActivity(intent);

                    }
                });

                Button cancelButton = view.findViewById(R.id.continue_btn);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        launcherPlayer.start();

                        dialog.dismiss();
                    }
                });
                /*
                quitdialog.dismiss();
                launcherPlayer.pause();
                soundEnabled = true;
                Intent intent = new Intent(FriendMode.this, Dashborad.class);
                startActivity(intent);

                 */
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (launcherPlayer != null) {
            launcherPlayer.stop();
            launcherPlayer.release();
        }
    }






    public Boolean winningGame() {


        if ((arr[0] == 1 && arr[1] == 1 && arr[2] == 1) || (arr[3] == 1 && arr[4] == 1 && arr[5] == 1) || (arr[6] == 1 && arr[7] == 1 && arr[8] == 1) || (arr[0] == 1 && arr[3] == 1 && arr[6] == 1) || (arr[1] == 1 && arr[4] == 1 && arr[7] == 1) || (arr[2] == 1 && arr[5] == 1 && arr[8] == 1) || (arr[0] == 1 && arr[4] == 1 && arr[8] == 1) || (arr[2] == 1 && arr[4] == 1 && arr[6] == 1)) {

            AlertFragment alertFragment = new AlertFragment("Player X Wins", 1);

            alertFragment.setPositiveButton(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    resetGame();
                }
            });
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Affichage de la fenêtre de dialogue
                    FragmentManager fragmentManager = getSupportFragmentManager();

                    alertFragment.setCancelable(false);
                    alertFragment.show(fragmentManager, "AlertFragment");
                   // launcherPlayer.pause();
                    tada.start();
                }
            }, 3000);

            endGame();
            countX++;
            txt1.setText(String.valueOf(countX));

            return true;
        } else if ((arr[0] == 2 && arr[1] == 2 && arr[2] == 2) || (arr[3] == 2 && arr[4] == 2 && arr[5] == 2) || (arr[6] == 2 && arr[7] == 2 && arr[8] == 2) || (arr[0] == 2 && arr[3] == 2 && arr[6] == 2) || (arr[1] == 2 && arr[4] == 2 && arr[7] == 2) || (arr[2] == 2 && arr[5] == 2 && arr[8] == 2) || (arr[0] == 2 && arr[4] == 2 && arr[8] == 2) || (arr[2] == 2 && arr[4] == 2 && arr[6] == 2)) {

            AlertFragment alertFragment = new AlertFragment("Player O Wins", 2);
            alertFragment.setPositiveButton(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Code à exécuter lorsque le bouton positif est cliqué

                    resetGame();
                }
            });
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Affichage de la fenêtre de dialogue
                    FragmentManager fragmentManager = getSupportFragmentManager();

                    alertFragment.setCancelable(false);
                    alertFragment.show(fragmentManager, "AlertFragment");
                   // launcherPlayer.pause();
                    tada.start();
                }
            }, 3000);

            endGame();
            countO++;
            txt2.setText(String.valueOf(countO));
            return true;
        } else if ((arr[0] != 0 && arr[1] != 0 && arr[2] != 0 && arr[3] != 0 && arr[4] != 0 && arr[5] != 0 && arr[6] != 0 && arr[7] != 0 && arr[8] != 0)) {
            AlertFragment alertFragment = new AlertFragment("The Game is Draw !!!", 1);
            alertFragment.setPositiveButton(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Code à exécuter lorsque le bouton positif est cliqué

                    resetGame();
                }
            });
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Affichage de la fenêtre de dialogue
                    FragmentManager fragmentManager = getSupportFragmentManager();

                    alertFragment.setCancelable(false);
                    alertFragment.show(fragmentManager, "AlertFragment");
                  //  launcherPlayer.pause();
                    tada.start();
                }
            }, 3000);

            endGame();
            countD++;
           // txt3.setText(String.valueOf(countD));
            return true;
        }
        return false;
    }

    public void resetGame() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }
        for (int j = 0; j < 9; j++) {

            mCases[j].setImageDrawable(null);
            mCases[j].setClickable(true);

        }
        btnreset.setClickable(true);

    }

    public void endGame() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }
        for (int j = 0; j < 9; j++) {
            mCases[j].setClickable(false);

        }
        btnreset.setClickable(false);

    }

    @Override
    public void onClick(View view) {

        for (int i = 0; i < 9; i++) {
            mCases[i].clearAnimation();
        }


            if (pick_side.equals("O")) {

               // StartGame = "O";
                playerTurn.setImageResource(R.drawable.circle);
                xPlayer.start();
            }else{
              //  StartGame = "X";
                playerTurn.setImageResource(R.drawable.cross);
                oPlayer.start();
            }

            for (int i = 0; i < 9; i++) {
                if (mCases[i] == view) {
                    if(pick_side=="X") {
                        Bitmap xBitmap = createXBitmap(100, 100, Color.BLUE, Color.CYAN);
                        mCases[i].setImageBitmap(xBitmap);
                    }else {
                        Bitmap oBitmap = createOBitmap(100, 100, 40, Color.RED);
                        mCases[i].setImageBitmap(oBitmap);
                    }

                   // mCases[i].setImageBitmap(xBitmap);
                    //ajouter une animation
                    Animation animation = getAnimation(i % 3);
                    animation.setRepeatCount(Animation.INFINITE);
                    mCases[i].startAnimation(animation);
                    mCases[i].setClickable(false);
                    // mCases[i].setImageResource(R.drawable.picx);
                    arr[i] = 1;
                    i++;
                    if(!winningGame()){


                        for (int j = 0; j < 9; j++) {
                            mCases[j].clearAnimation(); // Stopper l'animation en cours sur chaque ImageView
                        }
                        // Le bot est activé, donc il joue automatiquement un coup aléatoire
                        int index = new Random().nextInt(9);
                        while (arr[index] != 0) {
                            index = new Random().nextInt(9);
                        }
                        if(pick_side=="X") {
                            Bitmap oBitmap = createOBitmap(100, 100, 40, Color.RED);
                            mCases[index].setImageBitmap(oBitmap);
                        }else{
                            Bitmap xBitmap = createXBitmap(100, 100, Color.BLUE, Color.CYAN);
                            mCases[index].setImageBitmap(xBitmap);
                        }
                        //ajouter une animation
                        animation = getAnimation(index % 3);
                        animation.setRepeatCount(Animation.INFINITE);
                        mCases[index].startAnimation(animation);
                        mCases[index].setClickable(false);
                        // mCases[i].setImageResource(R.drawable.circle);
                        arr[index] = 2;

                    }

                }

                winningGame();
            }

    }

    private Animation getAnimation(int animationType) {
        switch (animationType) {
            case 0:
                return AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
            case 1:
                return AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in);
            case 2:
                return AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
            case 3:
                return AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
            case 4:
                return AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out);
            default:
                return null;
        }
    }

    public static Bitmap createXBitmap(int width, int height, int startColor, int endColor) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);


        Paint paint = new Paint();

        paint.setStrokeWidth(8);
        //paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setShadowLayer(5.0f, 0.0f, 2.0f, Color.BLUE);
        paint.setColor(Color.BLUE);

        // Dessiner la première ligne diagonale
        canvas.drawLine(0, 0, width, height, paint);

        // Dessiner la deuxième ligne diagonale
        canvas.drawLine(width, 0, 0, height, paint);

        // Renvoyer le bitmap
        return bitmap;
    }

    public static Bitmap createOBitmap(int width, int height, int radius, int color) {
        // Créer un Bitmap de taille width x height
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        // Créer un Canvas à partir du Bitmap
        Canvas canvas = new Canvas(bitmap);

        // Dessiner un cercle blanc rempli au centre du Canvas
        Paint paint = new Paint();
        paint.setColor(Color.TRANSPARENT);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width / 2, height / 2, radius, paint);

        // Dessiner un cercle noir autour du cercle blanc pour créer le contour
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);

        // Ajouter l'effet Material Design au cercle noir
        paint.setShadowLayer(5.0f, 0.0f, 2.0f, Color.BLACK);


        canvas.drawCircle(width / 2, height / 2, radius, paint);

        return bitmap;
    }


}