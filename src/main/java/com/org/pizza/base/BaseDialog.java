package com.org.pizza.base;
/***
 * The BaseDialog class which extends to DialogFragment. It also contains the way messages displays.
 * @author Mark Sturman, Alva Bandy
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class BaseDialog extends DialogFragment implements View.OnClickListener{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        return super.getView();
    }
    @Override
    public void onClick(View view){

    }
    public void showMessage(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
    public void startIntent(Class<?> cls){
        Intent intent = new Intent(getContext(), cls);
        startActivity(intent);
    }
}
