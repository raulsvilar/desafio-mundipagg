package raulsvilar.desafiomundipagg.view.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import raulsvilar.desafiomundipagg.R;
import raulsvilar.desafiomundipagg.databinding.FragmentSignUpBinding;
import raulsvilar.desafiomundipagg.viewmodel.UserViewModel;

public class SignUpFragment extends Fragment {

    private FragmentSignUpBinding mBinding;

    public SignUpFragment() {
        // Required empty public constructor
    }

    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false);
        mBinding.setUserVM(new UserViewModel());
        return mBinding.getRoot();
    }
}
