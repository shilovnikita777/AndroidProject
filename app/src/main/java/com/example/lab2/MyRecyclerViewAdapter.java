package com.example.lab2;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2.ui.test.TestFragment;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    //private List<String> mDataset;
    //private List<String> mDataset1;
    private List<Example> listOfJsonBooks;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public TextView mTextView1;

        public MyViewHolder(View v) {
            super(v);

            mTextView = v.findViewById(R.id.my_text_view);
            mTextView1 = v.findViewById(R.id.my_text_view1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos = getAdapterPosition();
                    Example book = listOfJsonBooks.get(pos);
                    Log.i("Tag", listOfJsonBooks.get(pos).getName()+" "+listOfJsonBooks.get(pos).getAuthor());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("oneBook",book);

                    Navigation.findNavController(itemView).navigate(R.id.from_list_to_recipe,bundle);
                    //
    
                }
            });
        }
    }
    public MyRecyclerViewAdapter(/*List<String> myDataset, List<String> myDataset1,*/List<Example> allBooks) {
        //mDataset = myDataset;
        //mDataset1 = myDataset1;
        listOfJsonBooks = allBooks;
    }
    @Override
    public MyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_element, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //holder.mTextView.setText(mDataset.get(position));
        //holder.mTextView1.setText(mDataset1.get(position));
        holder.mTextView.setText(listOfJsonBooks.get(position).getName());
        holder.mTextView1.setText(listOfJsonBooks.get(position).getAuthor());
    }
    @Override
    public int getItemCount() {
        return listOfJsonBooks.size();
    }
}