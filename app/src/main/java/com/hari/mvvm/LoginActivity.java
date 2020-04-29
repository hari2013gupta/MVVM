package com.hari.mvvm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.hari.mvvm.common.BaseActivity;
import com.hari.mvvm.databinding.ActivityLoginBinding;
import com.hari.mvvm.viewmodels.LoginViewModel;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

public class LoginActivity extends BaseActivity {

    static Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;

        ActivityLoginBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityMainBinding.setViewModel(new LoginViewModel());

        activityMainBinding.executePendingBindings();

    }

    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        if (message != null) {
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
            if(message.equals("Login was successful")){
                activity.startActivity(new Intent(activity, MainActivity.class));
                activity.finish();
            }
        }
    }
}
