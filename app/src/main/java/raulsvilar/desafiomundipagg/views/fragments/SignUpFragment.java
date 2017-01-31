package raulsvilar.desafiomundipagg.views.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import raulsvilar.desafiomundipagg.R;
import raulsvilar.desafiomundipagg.Utils;
import raulsvilar.desafiomundipagg.databinding.FragmentSignUpBinding;
import raulsvilar.desafiomundipagg.viewmodels.UserViewModel;

public class SignUpFragment extends Fragment implements UserViewModel.OnUserListener {

    private static final int RC_SAVE = 903;
    private final String TAG = getClass().getSimpleName();

    private FragmentSignUpBinding mBinding;

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
                Utils.createAlert(getActivity(), "Falha ao criar", "Email já registrado ou campos em branco")
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
}
