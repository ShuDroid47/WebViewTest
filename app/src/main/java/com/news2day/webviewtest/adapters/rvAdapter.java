package com.news2day.webviewtest.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.news2day.webviewtest.R;
import com.news2day.webviewtest.databinding.RvItemCategoryBinding;

public class rvAdapter extends RecyclerView.Adapter<rvAdapter.MainViewHolder> {
    @NonNull
    @Override
    public rvAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.rv_item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull rvAdapter.MainViewHolder holder, int position) {
        //holder.view.setCatData();
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder {

        RvItemCategoryBinding view;
        public MainViewHolder(@NonNull RvItemCategoryBinding binding) {
            super(binding.getRoot());
            view = binding;
        }
    }
}
