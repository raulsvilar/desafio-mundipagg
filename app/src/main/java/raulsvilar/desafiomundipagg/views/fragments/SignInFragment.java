package raulsvilar.desafiomundipagg.views.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import raulsvilar.desafiomundipagg.App;
import raulsvilar.desafiomundipagg.R;
import raulsvilar.desafiomundipagg.Utils;
import raulsvilar.desafiomundipagg.databinding.FragmentSignInBinding;
import raulsvilar.desafiomundipagg.viewmodels.UserViewModel;


public class SignInFragment extends Fragment implements UserViewModel.OnUserListener,
        View.OnClickListener {

    private final String TAG = getClass().getSimpleName();
    private FragmentSignInBinding mBinding;
    @Inject UserViewModel userViewModel;

    public SignInFragment() {
        // Required empty public constructor
    }

    public static SignInFragment newInstance() {
        SignInFragment fragment = new SignInFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.logout).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false);
        mBinding.button3.setOnClickListener(this);
        mBinding.setUserVM(userViewModel);
        mBinding.getUserVM().setOnUserListener(this);
        mBinding.getUserVM().restoreUser();
        return mBinding.getRoot();
    }

    @Override
    public void onFailed(int code) {
        switch (code) {

            case 400:
                Utils.createAlert(getActivity(), getString(R.string.login_fail), getString(R.string.required_user_password))
                        .setPositiveButton(getString(R.string.ok), null).show();
                break;
            case 401:
                Utils.createAlert(getActivity(), getString(R.string.login_fail), getString(R.string.invalid_user_password))
                        .setPositiveButton(getString(R.string.ok), null).show();
                break;
            default:
                Utils.createAlert(getActivity(), getString(R.string.login_fail), getString(R.string.service_error)+code)
                        .setPositiveButton(getString(R.string.ok), null).show();
                break;
        }
    }

    @Override
    public void onSuccess(String customerKey, String accessToken) {
        Utils.changeFragment(getFragmentManager(), R.id.container,
                ListMerchantsFragment.newInstance(customerKey, accessToken), false, null);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button3:
                Utils.changeFragment(getFragmentManager(), R.id.container,
                        SignUpFragment.newInstance(), true, "SignInFragment");
                break;
        }
    }
}