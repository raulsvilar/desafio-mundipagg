package raulsvilar.desafiomundipagg.views.fragments;


import android.app.Activity;
import android.content.DialogInterface;
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

public class TransactionFragment extends Fragment
        implements TransactionViewModel.OnTransactionListener{

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
                    String[] exp = expiry.split("/");

                    CreditCard card = new CreditCard(cardNumber, cardHolderName,
                            Integer.parseInt(exp[0]), Integer.parseInt(exp[1]), brand,
                            Integer.parseInt(cvv));

                    mBinding.getTransactionVM().addCard(card);
                    mBinding.getTransactionVM().buy();
                }
                break;
        }
    }

    @Override
    public void onSuccess() {
        Utils.createAlert(getActivity(), getString(R.string.transaction),
                          getString(R.string.success_transaction))
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getFragmentManager().popBackStackImmediate();
                    }
                }).show();
    }

    @Override
    public void onFailed(int code) {
        switch (code) {
            case 0:
                Utils.createAlert(getActivity(), getString(R.string.transaction),
                                  getString(R.string.blank_fields))
                        .setPositiveButton(getString(R.string.ok), null).show();
                break;
            default:
                Utils.createAlert(getActivity(), getString(R.string.transaction),
                                  getString(R.string.fail_transaction)+code)
                        .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getFragmentManager().popBackStackImmediate();
                            }
                        }).show();
        }
    }
}
