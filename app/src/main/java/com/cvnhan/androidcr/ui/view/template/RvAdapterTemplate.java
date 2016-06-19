package com.cvnhan.androidcr.ui.view.template;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cvnhan.androidcr.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nhancao on 6/19/16.
 */
public class RvAdapterTemplate extends RecyclerView.Adapter<RvAdapterTemplate.ListsHolder> {

    List<Object> listsItems;

    public RvAdapterTemplate() {
        this.listsItems = new ArrayList<>();
    }

    public void setListsItems(List<Object> listsItems) {
        this.listsItems = listsItems;
        notifyDataSetChanged();
    }

    @Override
    public RvAdapterTemplate.ListsHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_view_rv_item, parent, false);
        return new ListsHolder(view);

    }

    @Override
    public void onBindViewHolder(RvAdapterTemplate.ListsHolder holder, int position) {
        Object item = listsItems.get(position);
        holder.vNav.setOnClickListener(v -> {

        });
    }

    @Override
    public int getItemCount() {
        return listsItems.size();
    }

    public static final class ListsHolder extends RecyclerView.ViewHolder {

        private static final String TAG = ListsHolder.class.getSimpleName();

        @BindView(R.id.vNav)
        View vNav;
        @BindView(R.id.tvName)
        TextView tvName;

        public ListsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
