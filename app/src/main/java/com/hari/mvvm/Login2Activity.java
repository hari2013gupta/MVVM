package com.hari.mvvm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.hari.mvvm.common.BaseActivity;
import com.hari.mvvm.databinding.ActivityLogin2Binding;
import com.hari.mvvm.models.User2;
import com.hari.mvvm.viewmodels.LoginViewModel2;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class Login2Activity extends BaseActivity {

    static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;

        ActivityLogin2Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_login2);
        LoginViewModel2 loginViewModel = ViewModelProviders.of(this).get(LoginViewModel2.class);

        binding.setLoginViewModel(loginViewModel);
        binding.setLifecycleOwner(this);

        loginViewModel.getUser().observe(this, new Observer<User2>() {
            @Override
            public void onChanged(@Nullable User2 user) {
                if (user.getEmail().length() > 0 || user.getPassword().length() > 0) {
                    showToast("email : " + user.getEmail() + " password " + user.getPassword());
                }
            }
        });
        loginViewModel.getLoginSuccess().observe(this, count -> {
            showToast("Login Success! " + count);
            startActivity(new Intent(activity, MainActivity.class));
        });
    }

}
