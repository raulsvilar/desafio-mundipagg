package raulsvilar.desafiomundipagg.view.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import raulsvilar.desafiomundipagg.R;
import raulsvilar.desafiomundipagg.adapters.MerchantAdapter;
import raulsvilar.desafiomundipagg.databinding.FragmentListMerchantsBinding;
import raulsvilar.desafiomundipagg.model.Merchant;
import raulsvilar.desafiomundipagg.viewmodel.MerchantViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListMerchantsFragment extends Fragment
        implements MerchantViewModel.OnMerchantsListener{

    private static final String CUSTOMER_KEY = "customer_key";
    private static final String ACCESS_TOKEN = "access_token";

    private FragmentListMerchantsBinding mBinding;
    private MerchantAdapter mAdapter;
    private final String TAG = getClass().getSimpleName();

    public ListMerchantsFragment() {
        // Required empty public constructor
    }

    public static ListMerchantsFragment newInstance(@NonNull String customerKey,
                                                    @NonNull String accessToken) {

        ListMerchantsFragment fragment = new ListMerchantsFragment();
        Bundle args = new Bundle();
        args.putString(CUSTOMER_KEY, customerKey);
        args.putString(ACCESS_TOKEN, accessToken);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_merchants,
                container, false);

        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.recyclerView.setAdapter(mAdapter);
        MerchantViewModel merchantViewModel = new MerchantViewModel();
        merchantViewModel.setOnMerchantsListener(this);
        merchantViewModel.searchMerchants(getArguments().getString(CUSTOMER_KEY), getArguments().getString(ACCESS_TOKEN), "");
        return mBinding.getRoot();
    }

    @Override
    public void onGetMerchantsSuccess(List<Merchant> merchants) {
        for (Merchant m : merchants) {
            Log.d(TAG, m.getCorporateName());
        }
    }

    @Override
    public void onGetMerchantsFailed(int code) {
        Log.e(TAG, "Erro: "+code);
//        switch (code) {
//            case 201:
//                break;
//        }
    }
}
