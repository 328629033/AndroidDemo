package com.demo.android.databinding.list;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by herr.wang on 2017/3/13.
 */
public class MultiTypeAdapter extends RecyclerView.Adapter<MultiTypeAdapter.ItemViewHolder> {

    public interface IItem{
        int getLayout();

        int getVariableId();
    }

    private List<IItem> itemList = new ArrayList<>();

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ItemViewHolder.create(parent, viewType);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bindTo(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return itemList.get(position).getLayout();
    }

    public void addItem(IItem item){
        itemList.add(item);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder{
        private final ViewDataBinding vdb;

        static ItemViewHolder create(ViewGroup parent, int viewType){
            ViewDataBinding vdb = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false);
            return new ItemViewHolder(vdb);
        }

        public ItemViewHolder(ViewDataBinding vdb){
            super(vdb.getRoot());
            this.vdb = vdb;
        }

        void bindTo(IItem item){
            vdb.setVariable(item.getVariableId(), item);
            vdb.executePendingBindings();
        }
    }
}
