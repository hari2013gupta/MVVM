package com.hari.mvvm.viewmodels;

import android.os.Handler;

import com.hari.mvvm.models.User2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel2 extends ViewModel {

    public MutableLiveData<String> errorPassword = new MutableLiveData<>();
    public MutableLiveData<String> errorEmail = new MutableLiveData<>();

    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<Integer> busy;

    public MutableLiveData<Integer> getBusy() {

        if (busy == null) {
            busy = new MutableLiveData<>();
            busy.setValue(8);
        }

        return busy;
    }

    public LoginViewModel2() {
    }

    private MutableLiveData<User2> userMutableLiveData;
    private MutableLiveData successLiveData;

    public LiveData<User2> getUser() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }

        return userMutableLiveData;
    }
    public LiveData<Integer> getLoginSuccess() {
        if (successLiveData == null) {
            successLiveData = new MutableLiveData<>();
        }

        return successLiveData;
    }

    public void onLoginClicked() {

        getBusy().setValue(0); //View.VISIBLE
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                int successCount = 0;
                User2 user = new User2(email.getValue(), password.getValue());

                if (!user.isEmailValid()) {
                    errorEmail.setValue("Enter a valid email address");
                } else {
                    successCount++;
                    errorEmail.setValue(null);
                }

                if (!user.isPasswordLengthGreaterThan5())
                    errorPassword.setValue("Password Length should be greater than 5");
                else {
                    successCount++;
                    errorPassword.setValue(null);
                }

                userMutableLiveData.setValue(user);
                busy.setValue(8); //8 == View.GONE

                if(successCount > 1){
                    successLiveData.setValue(successCount);
                }

            }
        }, 1500);
    }
}