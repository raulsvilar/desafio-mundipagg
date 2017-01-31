package raulsvilar.desafiomundipagg.views.fragments;


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

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import raulsvilar.desafiomundipagg.App;
import raulsvilar.desafiomundipagg.R;
import raulsvilar.desafiomundipagg.Utils;
import raulsvilar.desafiomundipagg.views.adapters.MerchantAdapter;
import raulsvilar.desafiomundipagg.databinding.FragmentListMerchantsBinding;
import raulsvilar.desafiomundipagg.data.models.Merchant;
import raulsvilar.desafiomundipagg.viewmodels.MerchantViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListMerchantsFragment extends Fragment
        implements MerchantViewModel.OnMerchantsListener, MerchantAdapter.OnMerchantSelected{

    private static final String CUSTOMER_KEY = "customer_key";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String MERCHANTS_SAVED = "merchants_saved";

    private String accessToken;
    private String customerKey;

    private FragmentListMerchantsBinding mBinding;
    @Inject MerchantAdapter mAdapter;
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

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
        if (getArguments() != null) {
            accessToken = getArguments().getString(ACCESS_TOKEN);
            customerKey = getArguments().getString(CUSTOMER_KEY);
        }
        if (savedInstanceState != null) {
            mAdapter.setDataset(
                    (List<Merchant>) Parcels.unwrap(
                            savedInstanceState.getParcelable(MERCHANTS_SAVED)));
        }
        mAdapter.setOnMerchantSelected(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_merchants,
                container, false);

        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.setMerchantVM(new MerchantViewModel());
        mBinding.getMerchantVM().setOnMerchantsListener(this);
        if (savedInstanceState == null && mAdapter.getItemCount() == 0) {
            mBinding.getMerchantVM().searchMerchants(customerKey, accessToken, "");
        }
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.recyclerView.setAdapter(mAdapter);
        return mBinding.getRoot();
    }

    @Override
    public void onGetMerchantsSuccess(List<Merchant> merchants) {
        mAdapter.addMerchants(merchants);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(MERCHANTS_SAVED, Parcels.wrap(mAdapter.getMerchants()));
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onGetMerchantsFailed(int code) {
        Log.e(TAG, "Erro: "+code);
    }

    @Override
    public void onSelected(String merchantKey) {
        Utils.changeFragment(getFragmentManager(), R.id.container,
                TransactionFragment.newInstance(merchantKey), true,
                "ListMerchantsFragment");
    }
}
