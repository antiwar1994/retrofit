package com.andreipiletsky.retrofit.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andreipiletsky.retrofit.R;
import com.andreipiletsky.retrofit.pojo.Contacts;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private List<Contacts> contactsList;
    private Context context;
    private LayoutInflater layoutInflater;

    public RecyclerAdapter(List<Contacts> contactsList) {
        this.contactsList = contactsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Contacts currentContacts = contactsList.get(position);
        holder.id.setText(currentContacts.getCategoryId());
        holder.name.setText(currentContacts.getCategoryName());
        holder.count.setText(String.valueOf(currentContacts.getCategoryCount()));
    }

    @Override
    public int getItemCount() {
        if (contactsList != null) {
            return contactsList.size();
        }
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView id, name, count;

        public MyViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_field_tv);
            name = itemView.findViewById(R.id.name_field_tv);
            count = itemView.findViewById(R.id.count_field_tv);
        }
    }
}