package raulsvilar.desafiomundipagg.views.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import raulsvilar.desafiomundipagg.App;
import raulsvilar.desafiomundipagg.R;
import raulsvilar.desafiomundipagg.databinding.MerchantAdapterBinding;
import raulsvilar.desafiomundipagg.data.models.Merchant;

public class MerchantAdapter extends RecyclerView.Adapter<MerchantAdapter.ViewHolder> {

    public interface OnMerchantSelected {
        void onSelected(String merchantKey);
    }

    @Inject
    List<Merchant> dataset;
    OnMerchantSelected mCallback;

    public MerchantAdapter() {
        App.getComponent().inject(this);
    }

    public void setOnMerchantSelected(OnMerchantSelected listener) {
        mCallback = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.merchant_adapter, parent, false));
    }

    public void addMerchants(List<Merchant> merchants) {
        int i = dataset.size();
        dataset.addAll(merchants);
        notifyItemRangeInserted(i, merchants.size());
    }

    public void setDataset(List<Merchant> newDataset) {
        dataset = newDataset;
        notifyDataSetChanged();
    }

    public List<Merchant> getMerchants() {
        return dataset;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mBinding.setMerchant(dataset.get(position));
        holder.mBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        MerchantAdapterBinding mBinding;

        public ViewHolder(ViewDataBinding bindingView) {
            super(bindingView.getRoot());
            mBinding = (MerchantAdapterBinding) bindingView;
            mBinding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mCallback.onSelected(mBinding.getMerchant().getMerchantKey());
        }
    }
}
