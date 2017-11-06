package com.elifakay.onlinequizapp.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.elifakay.onlinequizapp.Interface.ItemClickListener;
import com.elifakay.onlinequizapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

/**
 * Created by elf_4 on 19.10.2017.
 */

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtCategoryName;
    public ImageView imgCategory;

    private ItemClickListener itemClickListener;

    public CategoryViewHolder(View itemView) {
        super(itemView);

        imgCategory = (ImageView) itemView.findViewById(R.id.imgCategory);
        txtCategoryName = (TextView) itemView.findViewById(R.id.txtCategoryName);

        itemView.setOnClickListener(this);
    }

    public void setItItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }
}
