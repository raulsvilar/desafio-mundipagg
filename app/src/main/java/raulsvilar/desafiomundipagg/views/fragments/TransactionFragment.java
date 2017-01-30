package raulsvilar.desafiomundipagg.views.fragments;


import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cooltechworks.creditcarddesign.CardEditActivity;
import com.cooltechworks.creditcarddesign.CreditCardUtils;

import raulsvilar.desafiomundipagg.R;
import raulsvilar.desafiomundipagg.Utils;
import raulsvilar.desafiomundipagg.data.models.CreditCard;
import raulsvilar.desafiomundipagg.databinding.FragmentTransactionBinding;
import raulsvilar.desafiomundipagg.viewmodels.TransactionViewModel;

public class TransactionFragment extends Fragment implements TransactionViewModel.OnTransactionListener{

    private static final String MERCHANT_KEY = "merchant_key";
    private String merchantKey;
    FragmentTransactionBinding mBinding;
    private static final int RC_CARD = 905;

    public TransactionFragment() {
        // Required empty public constructor
    }

    public static TransactionFragment newInstance(@NonNull String merchantKey) {

        TransactionFragment fragment = new TransactionFragment();
        Bundle args = new Bundle();
        args.putString(MERCHANT_KEY, merchantKey);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            merchantKey = getArguments().getString(MERCHANT_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_transaction,
                container, false);

        mBinding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CardEditActivity.class);
                startActivityForResult(i, RC_CARD);
            }
        });

        mBinding.setTransactionVM(new TransactionViewModel(merchantKey));
        mBinding.getTransactionVM().setOnTransactionListener(this);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RC_CARD:
                if (resultCode == Activity.RESULT_OK) {
                    String brand = mBinding.spinner.getSelectedItem().toString();
                    String cardHolderName = data.getStringExtra(CreditCardUtils.EXTRA_CARD_HOLDER_NAME);
                    String cardNumber = data.getStringExtra(CreditCardUtils.EXTRA_CARD_NUMBER);
                    String expiry = data.getStringExtra(CreditCardUtils.EXTRA_CARD_EXPIRY);
                    String cvv = data.getStringExtra(CreditCardUtils.EXTRA_CARD_CVV);
                    CreditCard card = new CreditCard();
                    card.setNumber(cardNumber);
                    card.setHolderName(cardHolderName);
                    String[] exp = expiry.split("/");
                    card.setExpMonth(Integer.parseInt(exp[0]));
                    card.setExpYear(Integer.parseInt(exp[1]));
                    card.setCvv(Integer.parseInt(cvv));
                    card.setBrand(brand);
                    mBinding.getTransactionVM().addCard(card);
                    mBinding.getTransactionVM().buy();
                }
                break;
        }
    }

    @Override
    public void onSuccess() {
        Utils.showAlert(getActivity(),"Tranção","Transação efetuada com sucesso!");
    }

    @Override
    public void onFailed() {
        Utils.showAlert(getActivity(),"Tranção","Transação falhou!");
    }
}
