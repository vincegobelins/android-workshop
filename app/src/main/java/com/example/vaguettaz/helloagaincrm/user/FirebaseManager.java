package com.example.vaguettaz.helloagaincrm.user;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.example.vaguettaz.helloagaincrm.R;
import com.example.vaguettaz.helloagaincrm.UserActivity;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

/**
 * Created by vaguettaz on 13/10/2015.
 */
public class FirebaseManager {

    private static FirebaseManager ourInstance = new FirebaseManager();
    private final Firebase myFirebaseRef;
    private FirebaseListener mListener;

    public static FirebaseManager getInstance() {
        return ourInstance;
    }

    private FirebaseManager() {
        myFirebaseRef = new Firebase("https://chatandroidgobelins.firebaseio.com");
    }

    public void authenticate(CharSequence registerName, CharSequence registerPassword, FirebaseListener listener){
        /*
        // verifier le cast :: raf
        try {
            mListener = (LoginListener) context;
        } catch(ClassCastException exception){
            throw new ClassCastException(context.toString() + "must implement LoginFragment.LoginListener");
        }*/

        mListener = listener;

        // try to authenticate to firebase
        myFirebaseRef.authWithPassword(registerName.toString(), registerPassword.toString(), new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                mListener.onSuccess(authData.getUid());
            }
            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                mListener.onError();
            }
        });
    }

    public void register(CharSequence registerName, CharSequence registerPassword, FirebaseListener listener){
        mListener = listener;

        myFirebaseRef.createUser(registerName.toString(), registerPassword.toString(), new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                mListener.onSuccess((String) result.get("uid"));
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                mListener.onError();
            }
        });
    }

    public void logout(){
        myFirebaseRef.unauth();
    }

    public interface FirebaseListener {
        void onSuccess(String userID);
        void onError();
    }
}
