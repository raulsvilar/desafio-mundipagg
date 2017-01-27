package raulsvilar.desafiomundipagg.view.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import raulsvilar.desafiomundipagg.R;
import raulsvilar.desafiomundipagg.databinding.FragmentSignInBinding;
import raulsvilar.desafiomundipagg.viewmodel.UserViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment implements UserViewModel.OnAuthenticateUserListener{

    private FragmentSignInBinding mBinding;

    public SignInFragment() {
        // Required empty public constructor
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
}
