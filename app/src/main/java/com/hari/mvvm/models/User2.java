package com.hari.mvvm.models;

import android.util.Patterns;

public class User2 {

    private String mEmail;
    private String mPassword;


    public User2(String email, String password) {
        mEmail = email;
        mPassword = password;
    }

    public String getEmail() {
        if (mEmail == null) {
            return "";
        }
        return mEmail;
    }


    public String getPassword() {

        if (mPassword == null) {
            return "";
        }
        return mPassword;
    }

    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }


    public boolean isPasswordLengthGreaterThan5() {
        return getPassword().length() > 5;
    }

}