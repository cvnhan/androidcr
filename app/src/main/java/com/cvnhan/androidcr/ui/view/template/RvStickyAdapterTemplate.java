package com.cvnhan.androidcr.ui.view.template;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.cvnhan.androidcr.R;
import com.cvnhan.androidcr.utils.ICollections;
import com.cvnhan.androidcr.utils.Utils;
import com.cvnhan.androidcr.utils.rvsticky.StickyHeaderAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nhancao on 6/19/16.
 */
public class RvStickyAdapterTemplate extends RecyclerView.Adapter<RvStickyAdapterTemplate.ContentViewHolder> implements Filterable, StickyHeaderAdapter<RvStickyAdapterTemplate.HeaderHolder> {

    private List<Object> listsItems;
    private List<Object> listFiltered;
    private String searchText;
    private List<Section> listSection;
    private ICollections.CallbackListener updateSecsion;

    public RvStickyAdapterTemplate(ICollections.CallbackListener updateSecsion) {
        this.listsItems = new ArrayList<>();
        this.listFiltered = new ArrayList<>();
        this.listSection = new ArrayList<>();
        this.updateSecsion = updateSecsion;
    }


    public void addItem(Object item) {
        add(item, -1);
    }

    public void generateSection() {
        listSection.clear();
        Section index = new Section(-1, "-1");
        for (int i = 0; i < listFiltered.size(); i++) {
            Object item = listFiltered.get(i);
            String category = (String) item;
            if (!TextUtils.isEmpty(category) && !index.getTitle().equals(category)) {
                index.setHeaderPosition(index.getHeaderPosition() + 1);
                index.setTitle(category);
            }
            listSection.add(new Section(index.getHeaderPosition(), index.getTitle()));
        }
        updateSecsion.callback();
    }

    public void add(Object item, int position) {
        position = position == -1 ? getItemCount() : position;
        listFiltered.add(position, item);
        sortListsItems();
    }

    public void remove(int position) {
        if (position < getItemCount()) {
            listFiltered.remove(position);
            sortListsItems();
        }
    }

    public void setListsItems(List<Object> listsItems) {
        this.listsItems = listsItems;
        this.listFiltered = listsItems;
        sortListsItems();
    }

    public void sortListsItems() {
        Collections.sort(listFiltered, (lhs, rhs) -> {
            if (lhs.equals(rhs)) {
                return 0;
            }
            if (lhs == null) {
                return -1;
            }
            if (rhs == null) {
                return 1;
            }
            return 1;
        });
        generateSection();
        notifyDataSetChanged();
    }

    @Override
    public RvStickyAdapterTemplate.ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_view_rv_item, parent, false);
        return new ContentViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RvStickyAdapterTemplate.ContentViewHolder holder, int position) {
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
                sortListsItems();
            }
        };
    }

    @Override
    public long getHeaderId(int position) {
        return listSection.get(position).getHeaderPosition();
    }

    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_view_rvsession_header, parent, false);
        return new HeaderHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderHolder viewholder, int position) {
        viewholder.tvHeader.setText("Header " + getHeaderId(position) + " " + listSection.get(position).getTitle());
    }

    public static final class ContentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.vNav)
        View vNav;
        @BindView(R.id.tvName)
        TextView tvName;

        public ContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class HeaderHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvHeader)
        TextView tvHeader;

        public HeaderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class Section {
        int headerPosition;
        String title;

        public Section(int headerPosition, String title) {
            this.headerPosition = headerPosition;
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getHeaderPosition() {
            return headerPosition;
        }

        public void setHeaderPosition(int headerPosition) {
            this.headerPosition = headerPosition;
        }
    }

}
