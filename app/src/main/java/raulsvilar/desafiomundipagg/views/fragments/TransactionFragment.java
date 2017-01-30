package raulsvilar.desafiomundipagg.views.fragments;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cooltechworks.creditcarddesign.CardEditActivity;
import com.cooltechworks.creditcarddesign.CreditCardView;

import raulsvilar.desafiomundipagg.R;
import raulsvilar.desafiomundipagg.databinding.FragmentTransactionBinding;

public class TransactionFragment extends Fragment {

    FragmentTransactionBinding mBinding;

    public TransactionFragment() {
        // Required empty public constructor
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
                startActivity(i);
            }
        });
        return mBinding.getRoot();
    }

}
