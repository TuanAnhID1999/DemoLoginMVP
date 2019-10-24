package com.ttcsolutons.demologinmvp.model;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.ttcsolutons.demologinmvp.database.DatabaseHelper;
import com.ttcsolutons.demologinmvp.presenter.ModelPresenterListener;

public class ModelLogin {
    ModelPresenterListener callBack;

    public ModelLogin(ModelPresenterListener callBack){
        this.callBack = callBack;
    }

    public void checkLogin(String un, String pw, Context context) {
        String email       = null;
        String passWord    = null;
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        Cursor cursorGet = databaseHelper.getData();
        if (cursorGet.getCount() == 0) {
            Log.d("people", "initInformData: null people");
        }else {
             email    = cursorGet.getString(cursorGet.getColumnIndex(DatabaseHelper.email));
             passWord = cursorGet.getString(cursorGet.getColumnIndex(DatabaseHelper.passWord));
            Log.d("people", email + " và " + passWord);
        }
        if(un.equals(email) && pw.equals(passWord)){
            callBack.loginSuccess();
            return;
        }
        callBack.loginFailed();
    }
}
