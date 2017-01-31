package raulsvilar.desafiomundipagg.views.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false);
        mBinding.setUserVM(new UserViewModel());
        mBinding.getUserVM().setOnUserListener(this);
        return mBinding.getRoot();
    }

    @Override
    public void onFailed(int code) {
        switch (code) {
            case 400:
                Utils.createAlert(getActivity(), getString(R.string.fail_create_user),
                        getString(R.string.registered_email_blank))
                        .setPositiveButton(getString(R.string.ok), null).show();
                break;
            default:
                Utils.createAlert(getActivity(), getString(R.string.fail_create_user),
                        getString(R.string.service_error)+code)
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
