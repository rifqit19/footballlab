package com.rifqit.footballlab.League.DetailLeague.Search.Match;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.rifqit.footballlab.League.DetailLeague.Match.DetailMatch.DetailMatch;
import com.rifqit.footballlab.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AdapterSearchMatch extends RecyclerView.Adapter<AdapterSearchMatch.CustomViewHolder> {

    private ArrayList<SearchMatchObj> searchMatchObjs;
    private Context context;

    public AdapterSearchMatch(Context context, ArrayList<SearchMatchObj> datalist){
        this.context = context;
        this.searchMatchObjs = datalist;
    }
    class CustomViewHolder extends RecyclerView.ViewHolder{
        public final View mView;

        TextView team,time,homeScore,awayScore;
        RoundedImageView imgTeam;
        CardView cardView;
        RelativeLayout today;

        CustomViewHolder(View itemView){
            super(itemView);
            mView = itemView;

            team = mView.findViewById(R.id.teamMatch);
            time = mView.findViewById(R.id.timematch);
            imgTeam = mView.findViewById(R.id.imageMatch);
            homeScore = mView.findViewById(R.id.homeScore);
            awayScore = mView.findViewById(R.id.awayScore);
            cardView = mView.findViewById(R.id.cardViewMatch);
            today = mView.findViewById(R.id.today);

        }
    }
    @Override
    public AdapterSearchMatch.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.match_lyt,parent, false);
        return new AdapterSearchMatch.CustomViewHolder(view);
    }
    @Override
    public void onBindViewHolder(AdapterSearchMatch.CustomViewHolder holder, final int position){
        holder.team.setText(searchMatchObjs.get(position).getStrEvent());
        holder.time.setText(searchMatchObjs.get(position).getDateEvent());
        Picasso.with(context).load(searchMatchObjs.get(position).getStrThumb().toString()).placeholder(R.drawable.ic_placeholder).into(holder.imgTeam);
        holder.homeScore.setText(searchMatchObjs.get(position).getIntHomeScore().toString());
        holder.awayScore.setText(searchMatchObjs.get(position).getIntAwayScore().toString());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lm = new Intent(context, DetailMatch.class);
                lm.putExtra("t1",searchMatchObjs.get(position).getIdEvent());
                context.startActivity(lm);

            }
        });
        String tanggal = searchMatchObjs.get(position).getDateEvent();
        String dateCurrent = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        if (tanggal.equals(dateCurrent)){
            holder.today.setVisibility(View.VISIBLE);
        }else {
            holder.today.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount(){
        return searchMatchObjs.size();
    }}