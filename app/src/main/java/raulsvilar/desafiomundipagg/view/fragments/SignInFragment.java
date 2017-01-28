package raulsvilar.desafiomundipagg.view.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvingResultCallbacks;
import com.google.android.gms.common.api.Status;

import raulsvilar.desafiomundipagg.R;
import raulsvilar.desafiomundipagg.databinding.FragmentSignInBinding;
import raulsvilar.desafiomundipagg.viewmodel.UserViewModel;

public class SignInFragment extends Fragment implements UserViewModel.OnAuthenticateUserListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final int RC_RETRIVE = 904;
    private FragmentSignInBinding mBinding;
    private GoogleApiClient mCredentialsApiClient;
    private CredentialRequest mCredentialRequest;

    public SignInFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCredentialsApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .enableAutoManage(getActivity(), this)
                .addApi(Auth.CREDENTIALS_API)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        retrieveCredentials();
    }

    private void onCredentialRetrieved(Credential credential) {
        String accountType = credential.getAccountType();
        if (accountType == null) {
            // Sign the user in with information from the Credential.
            mBinding.getUserVM().setUserEmail(credential.getId());
            mBinding.getUserVM().setUserPassword(credential.getPassword());
            mBinding.getUserVM().authenticateUser();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RC_RETRIVE:
                if (resultCode == Activity.RESULT_OK) {
                    Credential c = data.getParcelableExtra(Credential.EXTRA_KEY);
                    onCredentialRetrieved(c);
                }
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false);
        mBinding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new SignUpFragment())
                        .commit();
            }
        });
        mBinding.setUserVM(new UserViewModel());
        mBinding.getUserVM().setOnAuthenticateUserListener(this);
        return mBinding.getRoot();
    }

    @Override
    public void authenticateUserFailed(int code) {
        switch (code) {

            case 400:
                showAlert("Falha no login", "Usuário e senha são campos obrigatórios.");
                break;
            case 401:
                showAlert("Falha no login", "Usuário e/ou senha inválidos.");
                break;
            default:
                showAlert("Falha no login", "Ocorreu um erro inesperado, por favor tente" +
                        " novamente mais tarde ou entre em contato com o suporte.");
                break;
        }
    }

    @Override
    public void authenticateUserSuccess(int code) {
        showAlert("Sucesso", "LOGOU "+code);
    }

    private void showAlert(String title, String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setPositiveButton("OK", null);
        dialog.show();
    }

    private void retrieveCredentials() {
        mCredentialRequest = new CredentialRequest.Builder()
                .setPasswordLoginSupported(true)
                .build();

        Auth.CredentialsApi.request(mCredentialsApiClient, mCredentialRequest).setResultCallback(
                new ResolvingResultCallbacks<CredentialRequestResult>(getActivity(), RC_RETRIVE) {
                    @Override
                    public void onSuccess(@NonNull CredentialRequestResult credentialRequestResult) {
                        onCredentialRetrieved(credentialRequestResult.getCredential());
                    }

                    @Override
                    public void onUnresolvableFailure(@NonNull Status status) {
                        Toast.makeText(getActivity(), "Retrieve failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
