package raulsvilar.desafiomundipagg.views.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import raulsvilar.desafiomundipagg.R;
import raulsvilar.desafiomundipagg.Utils;
import raulsvilar.desafiomundipagg.databinding.FragmentSignInBinding;
import raulsvilar.desafiomundipagg.viewmodels.UserViewModel;


public class SignInFragment extends Fragment implements UserViewModel.OnUserListener,
        View.OnClickListener {

    private final String TAG = getClass().getSimpleName();
    private FragmentSignInBinding mBinding;

    public SignInFragment() {
        // Required empty public constructor
    }

    public static SignInFragment newInstance() {
        SignInFragment fragment = new SignInFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false);
        mBinding.button3.setOnClickListener(this);
        mBinding.setUserVM(new UserViewModel());
        mBinding.getUserVM().setOnUserListener(this);
        return mBinding.getRoot();
    }

    @Override
    public void onFailed(int code) {
        switch (code) {

            case 400:
                Utils.createAlert(getActivity(), "Falha no login", "Usuário e senha são campos obrigatórios.")
                        .setPositiveButton("OK", null).show();
                break;
            case 401:
                Utils.createAlert(getActivity(), "Falha no login", "Usuário e/ou senha inválidos.")
                        .setPositiveButton("OK", null).show();
                break;
            default:
                Utils.createAlert(getActivity(), "Falha no login", "Ocorreu um erro inesperado, por favor tente" +
                        " novamente mais tarde ou entre em contato com o suporte.\nCódigo "+code)
                        .setPositiveButton("OK", null).show();
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