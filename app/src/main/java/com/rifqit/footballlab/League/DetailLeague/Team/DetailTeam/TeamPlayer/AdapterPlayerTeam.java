package com.rifqit.footballlab.League.DetailLeague.Team.DetailTeam.TeamPlayer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rifqit.footballlab.League.DetailLeague.Team.DetailTeam.TeamPlayer.DetailPlayer.DetailPlayer;
import com.rifqit.footballlab.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterPlayerTeam extends RecyclerView.Adapter<AdapterPlayerTeam.CustomViewHolder> {

    private ArrayList<PlayerTeamObj> playerTeamObjs;
    private Context context;

    public AdapterPlayerTeam(Context context, ArrayList<PlayerTeamObj> datalist){
        this.context = context;
        this.playerTeamObjs = datalist;
    }
    class CustomViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        ImageView imageView;
        TextView textView,country,pos,liga;
        LinearLayout linearLayout;
//        CardView cardView;

        CustomViewHolder(View itemView){
            super(itemView);
            mView = itemView;

            imageView = mView.findViewById(R.id.iconPlayer);
            textView = mView.findViewById(R.id.namaPlayer);
            country = mView.findViewById(R.id.countryPlayer);
            pos = mView.findViewById(R.id.positonPlayer);
            linearLayout = mView.findViewById(R.id.linearPlayer);
        }
    }
    @Override
    public AdapterPlayerTeam.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.player_lyt,parent, false);
        return new AdapterPlayerTeam.CustomViewHolder(view);
    }
    @Override
    public void onBindViewHolder(AdapterPlayerTeam.CustomViewHolder holder, final int position){
        holder.textView.setText(playerTeamObjs.get(position).getStrPlayer());
        holder.pos.setText(playerTeamObjs.get(position).getStrPosition());
        holder.country.setText(playerTeamObjs.get(position).getStrNationality());
//        holder.liga.setText(teamObjs.get(position).getStrLeague());
        if (playerTeamObjs.get(position).getStrCutout().equals("null")){
            Picasso.with(context).load("https://cdn.iconscout.com/icon/free/png-256/football-player-1426973-1208513.png").placeholder(R.drawable.placeholder_black).into(holder.imageView);
        }else {
            Picasso.with(context).load(playerTeamObjs.get(position).getStrCutout().toString()).placeholder(R.drawable.placeholder_black).into(holder.imageView);
        }
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(context, DetailPlayer.class);
                o.putExtra("playerplayer", new Gson().toJson(playerTeamObjs.get(position)));
                context.startActivity(o);
            }
        });
    }

    @Override
    public int getItemCount(){
        return playerTeamObjs.size();
    }}