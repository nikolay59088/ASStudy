package com.nz.study;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {
    private ArrayList<AccSettings> sett;
    private Context context;
    private Boolean flag = false;

    public AccountAdapter(ArrayList<AccSettings> sett, Context context){
    this.sett = sett;
    this.context = context;

    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.acc_adapter, parent, false);
        return new AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AccountViewHolder holder, final int position){
        holder.name.setText(sett.get(position).getName());
        holder.text.setText(sett.get(position).getText());
        holder.but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!flag){
                    holder.but.setImageResource(R.drawable.check);
                    holder.text.setEnabled(true);
                    flag = !flag;
                }
                else{
                    holder.but.setImageResource(R.drawable.edit);
                    holder.text.setEnabled(false);
                    flag = !flag;
                }

            }
        });

    }


    @Override
    public int getItemCount(){
        return sett.size();
    }



    public class AccountViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        EditText text;
        ImageButton but;


        public AccountViewHolder(View view) {
            super(view);
            this.but = (ImageButton) view.findViewById(R.id.acc_adapter_but);
            name = (TextView) view.findViewById(R.id.name);
            text = (EditText) view.findViewById(R.id.AccText);
        }

    }

}
