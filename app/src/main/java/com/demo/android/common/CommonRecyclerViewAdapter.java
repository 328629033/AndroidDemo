package com.demo.android.common;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by herr.wang on 2017/3/15.
 *
 */
public class CommonRecyclerViewAdapter extends RecyclerView.Adapter<CommonRecyclerViewAdapter.ItemViewHolder> {

    /**
     * for recyclerview, if your model used as an item in collection, implement it.
     */
    public interface IItem{
        int getLayout();
        int getVariableId();
    }

    //prepared for child.
    protected List<IItem> list;
    protected Context context;

    public CommonRecyclerViewAdapter(List<IItem> list, Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ItemViewHolder.create(parent, viewType);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bindTo(list.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getLayout();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder{
        private final ViewDataBinding vdb;

        public static ItemViewHolder create(ViewGroup parent, int viewType){
            ViewDataBinding vdb = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false);
            return new ItemViewHolder(vdb);
        }

        private ItemViewHolder(ViewDataBinding vdb){
            super(vdb.getRoot());
            this.vdb = vdb;
        }

        public void bindTo(IItem item){
            vdb.setVariable(item.getVariableId(), item);
            vdb.executePendingBindings();
        }
    }

    public void setData(List<IItem> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public void addData(IItem item){
        list.add(item);
        notifyDataSetChanged();
    }

}
