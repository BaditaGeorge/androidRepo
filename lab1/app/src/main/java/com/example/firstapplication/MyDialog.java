package com.example.firstapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class MyDialog extends DialogFragment {

    public FragmentActivity acv;
    public Context cntx;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(acv);
        LayoutInflater infl = acv.getLayoutInflater();
        View view = infl.inflate(R.layout.dialog,null);
        final EditText mEdit = (EditText)view.findViewById(R.id.editText);
        builder.setView(view)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(cntx,mEdit.getText(),Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(cntx,"Clear",Toast.LENGTH_LONG).show();
                    }
                });
        return builder.create();
//        builder.setMessage("Are you good?")
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(cntx,"Ok",Toast.LENGTH_LONG).show();
//                    }
//                })
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(cntx,"Ok",Toast.LENGTH_LONG).show();
//                    }
//                });
//        return builder.create();
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setMessage("You clicked the button, right?")
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
////                        Toast.makeText(getContext(),"That's true!",Toast.LENGTH_LONG).show();
//                    }
//                })
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
////                        Toast.makeText(getContext(),"Telling lies, Johny?",Toast.LENGTH_LONG).show();
//                    }
//                });
//        return builder.create();
    }

    public void setContext(Context cnt){
        cntx = cnt;
    }

    public void setActivitiy(FragmentActivity frm){
        acv = frm;
    }
}
