package com.ttcsolutons.demologinmvp.model;

import com.ttcsolutons.demologinmvp.presenter.ModelPresenterListener;

public class ModelLogin {
    ModelPresenterListener callBack;

    public ModelLogin(ModelPresenterListener callBack){
        this.callBack = callBack;
    }

    public void checkLogin(String un, String pw) {
        if(un.equals("admin") && pw.equals("123")){
            callBack.loginSuccess();
            return;
        }
        callBack.loginFailed();
    }
}
