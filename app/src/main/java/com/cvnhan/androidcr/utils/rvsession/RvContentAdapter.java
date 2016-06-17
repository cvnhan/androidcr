package com.cvnhan.androidcr.utils.rvsession;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cvnhan.androidcr.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by nhancao on 6/17/16.
 */
public class RvContentAdapter extends RecyclerView.Adapter<RvContentAdapter.SimpleViewHolder> {

    private final Context context;
    private List<String> data;

    public void add(String s, int position) {
        position = position == -1 ? getItemCount() : position;
        data.add(position, s);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        if (position < getItemCount()) {
            data.remove(position);
            notifyItemRemoved(position);
        }
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;

        public SimpleViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.section_text);
        }
    }

    public RvContentAdapter(Context context, String[] data) {
        this.context = context;
        if (data != null)
            this.data = new ArrayList<String>(Arrays.asList(data));
        else this.data = new ArrayList<String>();
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.view_lists_rvsession, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        holder.title.setText(data.get(position));
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Position =" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}