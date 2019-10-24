package com.ttcsolutons.demologinmvp.model;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.ttcsolutons.demologinmvp.database.DatabaseHelper;
import com.ttcsolutons.demologinmvp.presenter.ModelPresenterListener;

import java.util.List;

public class ModelLogin {
    ModelPresenterListener callBack;

    public ModelLogin(ModelPresenterListener callBack) {
        this.callBack = callBack;
    }

    public void checkLogin(String un, String pw, Context context) {
        String email = null;
        String passWord = null;
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        Cursor res = databaseHelper.getAllPersons();
        while (res.moveToNext()) {
           email= res.getString(1);
           passWord= res.getString(2);
            Log.d("people", res.getString(0));
            Log.d("people", res.getString(1));
            Log.d("people", res.getString(2));
        }


        if (un.equals(email) && pw.equals(passWord)) {
            callBack.loginSuccess();
            return;
        }
        callBack.loginFailed();
    }
}
