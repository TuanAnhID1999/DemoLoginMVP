package com.ttcsolutons.demologinmvp.presenter;

import com.ttcsolutons.demologinmvp.model.ModelLogin;
import com.ttcsolutons.demologinmvp.model.ViewLoginListener;

public class PresenterLogin implements ModelPresenterListener {

    ModelLogin modelLogin;

    ViewLoginListener callBack;
    public PresenterLogin(ViewLoginListener callBack){
        this.callBack = callBack;
    }

    public void receiverLogin(String email, String pass){
        modelLogin = new ModelLogin(this);
        modelLogin.checkLogin(email,pass);
    }


    @Override
    public void loginSuccess() {
        callBack.loginSuccess();
    }

    @Override
    public void loginFailed() {
        callBack.loginFailed();
    }
}
