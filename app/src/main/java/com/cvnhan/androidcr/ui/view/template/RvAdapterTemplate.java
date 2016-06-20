package com.cvnhan.androidcr.ui.view.template;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.cvnhan.androidcr.R;
import com.cvnhan.androidcr.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nhancao on 6/19/16.
 */
public class RvAdapterTemplate extends RecyclerView.Adapter<RvAdapterTemplate.ListsHolder> implements Filterable {

    private List<Object> listsItems;
    private List<Object> listFiltered;
    private String searchText;

    public RvAdapterTemplate() {
        this.listsItems = new ArrayList<>();
        this.listFiltered = new ArrayList<>();
    }

    public void setListsItems(List<Object> listsItems) {
        this.listsItems = listsItems;
        this.listFiltered = listsItems;
        notifyDataSetChanged();
    }

    @Override
    public RvAdapterTemplate.ListsHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_view_rv_item, parent, false);
        return new ListsHolder(view);

    }

    @Override
    public void onBindViewHolder(RvAdapterTemplate.ListsHolder holder, int position) {
        Object item = listFiltered.get(position);
        holder.tvName.setText(Utils.highlightText(searchText, (String) item));
        holder.vNav.setOnClickListener(v -> {

        });
    }

    @Override
    public int getItemCount() {
        return listFiltered.size();
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
        getFilter().filter(searchText);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (constraint == null || constraint.toString().length() == 0) {
                    results.count = listsItems.size();
                    results.values = listsItems;
                } else {
                    List<Object> resultsData = new ArrayList<>();
                    String searchStr = constraint.toString();
                    for (Object item : listsItems) {
                        if (Utils.isContainText(searchStr, (String) item)) {
                            resultsData.add(item);
                        }
                    }
                    results.count = resultsData.size();
                    results.values = resultsData;
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (searchText == null || searchText.length() == 0) {
                    listFiltered = listsItems;
                } else {
                    listFiltered = (List<Object>) results.values;
                }
                notifyDataSetChanged();
            }
        };
    }

    public static final class ListsHolder extends RecyclerView.ViewHolder {

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
