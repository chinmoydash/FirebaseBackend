package com.example.chinmoydash.firebasebackend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by chinmoydash on 24/08/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder> {
    List<DataModel> list;
    Context context;

    public RecyclerAdapter(List<DataModel> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.card,parent,false);
        MyHolder myHolder = new MyHolder(view);

        return myHolder;

    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        DataModel mylist = list.get(position);
        holder.name.setText(mylist.getName());
        holder.email.setText(mylist.getEmail());

    }

        @Override
            public int getItemCount(){

                int arr = 0;
                try {
                    if(list.size()==0){
                        arr=0;
                    }
                    else {
                        arr = list.size();
                    }
                } catch (Exception e ){

                }
                return arr;

        }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView name,email;


        public MyHolder(View itemView) {
                super(itemView);
                name = (TextView) itemView.findViewById(R.id.vname);
                email= (TextView) itemView.findViewById(R.id.vemail);
            }
        }


}
