package com.cvnhan.androidcr.ui.view.template;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cvnhan.androidcr.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nhancao on 6/20/16.
 */
public class RvSessionAdapterTemplate extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SECTION_TYPE = 0;
    private final Context context;
    private boolean valid = true;
    private ContentAdapter contentAdapter;
    private SparseArray<Section> sections = new SparseArray<>();


    public RvSessionAdapterTemplate(Context context) {
        this.context = context;
        this.contentAdapter = new ContentAdapter(context);
        this.contentAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                valid = contentAdapter.getItemCount() > 0;
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                valid = contentAdapter.getItemCount() > 0;
                notifyItemRangeChanged(positionStart, itemCount);
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                valid = contentAdapter.getItemCount() > 0;
                notifyItemRangeInserted(positionStart, itemCount);
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                valid = contentAdapter.getItemCount() > 0;
                notifyItemRangeRemoved(positionStart, itemCount);
            }
        });
    }


    public void setListsItems(List<String> listItem) {
        this.contentAdapter.setListItem(listItem);
        setSections(sessionGenerator());
    }

    private List<Section> sessionGenerator() {
        List<Section> sections =
                new ArrayList<>();
        Map<String, Boolean> sessionKey = new HashMap<>();
        for (int i = 0; i < this.contentAdapter.getItemCount(); i++) {
            String item = this.contentAdapter.getListItem().get(i);
            String[] s = item.split(" \\[-\\] ");
            if (s.length > 0) {
                if (!sessionKey.containsKey(s[s.length - 1])) {
                    sessionKey.put(s[s.length - 1], true);
                    sections.add(new Section(i, s[s.length - 1]));
                }
            }
        }
        return sections;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int typeView) {
        if (typeView == SECTION_TYPE) {
            final View view = LayoutInflater.from(context).inflate(R.layout.template_view_rvsession_header, parent, false);
            return new SectionViewHolder(view);
        } else {
            return contentAdapter.onCreateViewHolder(parent, typeView - 1);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder sectionViewHolder, int position) {
        if (isSectionHeaderPosition(position)) {
            ((SectionViewHolder) sectionViewHolder).tvHeader.setText(sections.get(position).title);
        } else {
            contentAdapter.onBindViewHolder((ContentAdapter.ContentViewHolder) sectionViewHolder, sectionedPositionToPosition(position));
        }

    }

    @Override
    public int getItemViewType(int position) {
        return isSectionHeaderPosition(position)
                ? SECTION_TYPE
                : contentAdapter.getItemViewType(sectionedPositionToPosition(position)) + 1;
    }

    public void setSections(List<Section> sections) {
        this.sections.clear();
        Collections.sort(sections, (lhs, rhs) -> (lhs.firstPosition == rhs.firstPosition) ? 0
                : ((lhs.firstPosition < rhs.firstPosition) ? -1 : 1));
        int offset = 0;
        for (Section section : sections) {
            section.sectionedPosition = section.firstPosition + offset;
            this.sections.append(section.sectionedPosition, section);
            ++offset;
        }

        notifyDataSetChanged();
    }

    public int positionToSectionedPosition(int position) {
        int offset = 0;
        for (int i = 0; i < sections.size(); i++) {
            if (sections.valueAt(i).firstPosition > position) {
                break;
            }
            ++offset;
        }
        return position + offset;
    }

    public int sectionedPositionToPosition(int sectionedPosition) {
        if (isSectionHeaderPosition(sectionedPosition)) {
            return RecyclerView.NO_POSITION;
        }

        int offset = 0;
        for (int i = 0; i < sections.size(); i++) {
            if (sections.valueAt(i).sectionedPosition > sectionedPosition) {
                break;
            }
            --offset;
        }
        return sectionedPosition + offset;
    }

    public boolean isSectionHeaderPosition(int position) {
        return sections.get(position) != null;
    }

    @Override
    public long getItemId(int position) {
        return isSectionHeaderPosition(position)
                ? Integer.MAX_VALUE - sections.indexOfKey(position)
                : contentAdapter.getItemId(sectionedPositionToPosition(position));
    }

    @Override
    public int getItemCount() {
        return (valid ? contentAdapter.getItemCount() + sections.size() : 0);
    }

    public static class Section {
        int firstPosition;
        int sectionedPosition;
        CharSequence title;

        public Section(int firstPosition, CharSequence title) {
            this.firstPosition = firstPosition;
            this.title = title;
        }

        public CharSequence getTitle() {
            return title;
        }
    }

    public static class SectionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvHeader)
        TextView tvHeader;

        public SectionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentViewHolder> {

        private final Context context;
        private List<String> listItem;

        public ContentAdapter(Context context) {
            this.context = context;
            this.listItem = new ArrayList<>();
        }

        public void add(String s, int position) {
            position = position == -1 ? getItemCount() : position;
            listItem.add(position, s);
            notifyItemInserted(position);
        }

        public void remove(int position) {
            if (position < getItemCount()) {
                listItem.remove(position);
                notifyItemRemoved(position);
            }
        }

        public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View view = LayoutInflater.from(context).inflate(R.layout.template_view_rvsession_content, parent, false);
            return new ContentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ContentViewHolder holder, final int position) {
            holder.tvContent.setText(listItem.get(position));
            holder.tvContent.setOnClickListener(view -> Toast.makeText(context, "Position =" + position, Toast.LENGTH_SHORT).show());
        }

        public List<String> getListItem() {
            return listItem;
        }

        public void setListItem(List<String> listItem) {
            this.listItem = listItem;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return listItem.size();
        }

        public static class ContentViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.tvContent)
            TextView tvContent;

            public ContentViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }
}

