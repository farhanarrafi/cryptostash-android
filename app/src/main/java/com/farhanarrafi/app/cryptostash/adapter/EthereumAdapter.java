package com.farhanarrafi.app.cryptostash.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.farhanarrafi.app.cryptostash.BR;
import com.farhanarrafi.app.cryptostash.R;
import com.farhanarrafi.app.cryptostash.listener.OnItemClickListener;
import com.farhanarrafi.app.cryptostash.model.Ethereum;

import java.util.ArrayList;
import java.util.List;

public class EthereumAdapter extends RecyclerView.Adapter<EthereumAdapter.EthereumListViewHolder> {

    private List<Ethereum> ethereumList;
    private OnItemClickListener listener;

    public EthereumAdapter() {
    }

    public EthereumAdapter(List<Ethereum> ethereumList, OnItemClickListener listener) {
        this.ethereumList = ethereumList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EthereumListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding dataBinding = DataBindingUtil.inflate(inflater,R.layout.ethereum_item, parent, false);
        return new EthereumListViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull EthereumListViewHolder holder, int position) {
        final Ethereum ethereum = ethereumList.get(position);
        holder.bind(ethereum, listener);
    }

    @Override
    public int getItemCount() {
        return ethereumList.size();
    }

    static class EthereumListViewHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding ethereumItemDataBinding;

        public EthereumListViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            ethereumItemDataBinding = binding;
        }

        public void bind(Ethereum ethereum, OnItemClickListener listener) {
            ethereumItemDataBinding.setVariable(BR.ethereum,ethereum);
            ethereumItemDataBinding.executePendingBindings();
            ethereumItemDataBinding.getRoot().setOnClickListener(v -> listener.onItemClick(ethereum));
        }
    }
}
