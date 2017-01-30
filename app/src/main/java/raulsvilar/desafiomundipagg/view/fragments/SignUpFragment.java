package raulsvilar.desafiomundipagg.view.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import raulsvilar.desafiomundipagg.R;
import raulsvilar.desafiomundipagg.Utils;
import raulsvilar.desafiomundipagg.databinding.FragmentSignUpBinding;
import raulsvilar.desafiomundipagg.viewmodel.UserViewModel;

public class SignUpFragment extends Fragment implements UserViewModel.OnUserListener {

    private static final int RC_SAVE = 903;
    private final String TAG = getClass().getSimpleName();

    private FragmentSignUpBinding mBinding;
    private Credential credential;
    private GoogleApiClient mCredentialsClient;

    public SignUpFragment() {
        // Required empty public constructor
    }

    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false);
        mBinding.setUserVM(new UserViewModel());
        mBinding.getUserVM().setOnUserListener(this);
        return mBinding.getRoot();
    }

    @Override
    public void onFailed(int code) {
        switch (code) {
            case 400:
                Utils.showAlert(getActivity(), "Falha ao criar", "Email já registrado ou campos em branco");
                break;
            default:
                Utils.showAlert(getActivity(), "Falha no login", "Ocorreu um erro inesperado, por favor tente" +
                        " novamente mais tarde ou entre em contato com o suporte.");
                break;
        }
    }

    @Override
    public void onSuccess(String customerKey, String accessToken) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, ListMerchantsFragment.newInstance(customerKey, accessToken))
                .commit();
    }
}
