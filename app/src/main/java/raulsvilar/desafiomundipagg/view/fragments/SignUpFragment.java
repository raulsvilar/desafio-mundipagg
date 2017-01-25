package raulsvilar.desafiomundipagg.view.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.google.android.gms.common.api.ResolvingResultCallbacks;
import com.google.android.gms.common.api.Status;

import raulsvilar.desafiomundipagg.R;
import raulsvilar.desafiomundipagg.databinding.FragmentSignUpBinding;
import raulsvilar.desafiomundipagg.viewmodel.UserViewModel;

public class SignUpFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, UserViewModel.OnRegisterUserListener {

    public static final int RC_SAVE = 903;
    private final String TAG = getClass().getSimpleName();

    private FragmentSignUpBinding mBinding;
    private Credential credential;
    private GoogleApiClient mCredentialsClient;

    public SignUpFragment() {
        // Required empty public constructor
    }

    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCredentialsClient =  new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .enableAutoManage(getActivity(), this)
                .addApi(Auth.CREDENTIALS_API)
                .build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false);
        mBinding.setUserVM(new UserViewModel());
        mBinding.getUserVM().setOnRegisterUserListener(this);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SAVE) {
            if (resultCode == Activity.RESULT_OK) {
                Log.d(TAG, "SAVE: OK");
                Toast.makeText(getActivity(), "Credentials saved", Toast.LENGTH_SHORT).show();
            } else {
                Log.e(TAG, "SAVE: Canceled by user");
            }
        }

    }

    private void saveCredentialsGoogle() {

        credential = new Credential.Builder(mBinding.getUserVM().getUserEmail())
                .setPassword(mBinding.getUserVM().getUserPassword())
                .build();


        Auth.CredentialsApi.save(mCredentialsClient, credential).setResultCallback(new ResolvingResultCallbacks<Status>(getActivity(), RC_SAVE) {
            @Override
            public void onSuccess(@NonNull Status status) {
                Log.d(TAG, "SAVE: OK");
                Toast.makeText(getActivity(), "Credentials saved", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUnresolvableFailure(@NonNull Status status) {
                Toast.makeText(getActivity(), "Save failed", Toast.LENGTH_SHORT).show();
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

    @Override
    public void registerUserFailed() {

    }

    @Override
    public void registerUserSuccess() {
        saveCredentialsGoogle();
    }
}
