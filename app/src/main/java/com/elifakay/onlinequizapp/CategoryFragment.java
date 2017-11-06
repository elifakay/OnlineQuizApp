package com.elifakay.onlinequizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.elifakay.onlinequizapp.Common.Common;
import com.elifakay.onlinequizapp.Interface.ItemClickListener;
import com.elifakay.onlinequizapp.Model.Category;
import com.elifakay.onlinequizapp.ViewHolder.CategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class CategoryFragment extends Fragment {

    View myFragment;

    RecyclerView categoryList;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Category,CategoryViewHolder> adapter;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseRefCategory;

    public static CategoryFragment newInstance() {
        CategoryFragment categoryFragment = new CategoryFragment();
        return categoryFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseRefCategory=firebaseDatabase.getReference("Category");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        myFragment = inflater.inflate(R.layout.fragment_category, container, false);

        categoryList=(RecyclerView)myFragment.findViewById(R.id.listCategory);
        categoryList.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(container.getContext());
        categoryList.setLayoutManager(layoutManager);

        loadCategories();

        return myFragment;
    }

    private void loadCategories() {
        adapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(
                Category.class,
                R.layout.category_layout,
                CategoryViewHolder.class,
                databaseRefCategory
        ) {
            @Override
            protected void populateViewHolder(CategoryViewHolder viewHolder, final Category model, int position) {
                viewHolder.txtCategoryName.setText(model.getName());
                Picasso.with(getActivity())
                        .load(model.getImage())
                        .into(viewHolder.imgCategory);

                viewHolder.setItItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Toast.makeText(getActivity(), String.format("%s|%s",adapter.getRef(position).getKey(),model.getName()),Toast.LENGTH_SHORT).show();
                        Intent startGame=new Intent(getActivity(),StartActivity.class);
                        Common.categoryId=adapter.getRef(position).getKey();
                        startActivity(startGame);
                    }
                });
            }
        };
        adapter.notifyDataSetChanged();
        categoryList.setAdapter(adapter);
    }
}
