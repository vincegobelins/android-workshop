package com.example.vaguettaz.helloagaincrm.user;


import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.vaguettaz.helloagaincrm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    private static final String TAG = "LoginFragment";
    private LoginListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // context -> activity
        try {
            mListener = (LoginListener) context;
        } catch(ClassCastException exception){
            throw new ClassCastException(context.toString() + "must implement LoginFragment.LoginListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        Button loginButton = (Button) view.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClickListener");
                mListener.onLoginClicked();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    public interface LoginListener {
        void onLoginClicked();
    }


}
