package com.example.srirang.letsjims;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by HAKIKI PC on 30/11/2015.
 */
public class ActorsAdapter extends RecyclerView.Adapter<ActorsAdapter.ActorViewHolder> {

    ArrayList<Actors> items;
    private  RadioGroup radioGroup;

    public ActorsAdapter(ArrayList<Actors> items) {
        this.items = items;
    }

    @Override
    public ActorViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rows,viewGroup,false);

        ActorViewHolder Actionview = new ActorViewHolder(v);
        return Actionview;
    }

    @Override
    public void onBindViewHolder(ActorViewHolder holder, int position) {

        holder.tvNamaProduk.setText(items.get(position).getNamaproduk());
        holder.tvHargaProduk.setText(items.get(position).getHargaproduk());
        holder.imageProduk.setImageResource(items.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public  class ActorViewHolder extends RecyclerView.ViewHolder{
        TextView tvNamaProduk, tvHargaProduk;
        ImageView imageProduk;

        public ActorViewHolder(View itemView) {
            super(itemView);

            tvNamaProduk = (TextView) itemView.findViewById(R.id.tvJudul);
            tvHargaProduk = (TextView) itemView.findViewById(R.id.tvHarga);
            imageProduk = (ImageView) itemView.findViewById(R.id.ivProduk);

            radioGroup = (RadioGroup) itemView.findViewById(R.id.radioGroup);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                    RadioButton rb= (RadioButton) group.findViewById(checkedId);
                    if (null != rb && checkedId > -1) {
                        String temp = rb.getText().toString();
                        Random r = new Random();
                        int i1 = r.nextInt(300 - 30) + 30;
                        temp+="      + "+i1;
                        rb.setText(temp);
                        rb.setTextColor(Color.RED);
                    }

                }
            });

        }
    }




}