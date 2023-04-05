package com.example.tictac;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class AlertFragment extends DialogFragment {

    private final String mContentText;
    private final int mAnimationType;
    private DialogInterface.OnClickListener positiveButtonListener;

    public AlertFragment(String contentText, int animationType) {
        mContentText = contentText;
        mAnimationType = animationType;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_alert, null);
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_alert, null);
        TextView textView = view.findViewById(R.id.alert_text_view);
        textView.setText(mContentText);
        Animation animation = getAnimation(mAnimationType);
        textView.startAnimation(animation);

        view.findViewById(R.id.alert_ok_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (positiveButtonListener != null) {
                    positiveButtonListener.onClick(getDialog(), DialogInterface.BUTTON_POSITIVE);
                }
                dismiss();
            }
        });

        view.findViewById(R.id.offline_game_quit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), Dashborad.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);


        builder.setPositiveButton(null, null);
        builder.setNegativeButton(null, null);
        builder.setTitle(null);
        builder.setCancelable(false);
        return builder.create();
    }


    private Animation getAnimation(int animationType) {
        switch (animationType) {
            case 1:
                return AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
            case 2:
                return AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
            case 3:
                return AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
            default:
                return null;
        }
    }

    public void setPositiveButton(DialogInterface.OnClickListener listener) {
        positiveButtonListener = listener;
    }

}

