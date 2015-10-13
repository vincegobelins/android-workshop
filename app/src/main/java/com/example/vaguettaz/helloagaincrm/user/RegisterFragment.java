package com.example.vaguettaz.helloagaincrm.user;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vaguettaz.helloagaincrm.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {


    private static final String TAG = "RegisterFragment";
    private RegisterListener mListener;

    @Bind(R.id.registerName) TextView mRegisterName;
    @Bind(R.id.registerPassword) TextView mRegisterPassword;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // context -> activity
        try {
            mListener = (RegisterListener) context;
        } catch(ClassCastException exception){
            throw new ClassCastException(context.toString() + "must implement RegisterFragment.RegisterListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.registerButton)
    void OnClick(View v) {
        mListener.onRegisterClicked(mRegisterName.getText(), mRegisterPassword.getText());
    }

    public interface RegisterListener {
        void onRegisterClicked(CharSequence registerName, CharSequence registerPassword);
    }


}
