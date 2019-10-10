package com.rifqit.footballlab.League.DetailLeague.Search.Team;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rifqit.footballlab.League.DetailLeague.Team.DetailTeam.DetailTeam;
import com.rifqit.footballlab.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterSearchTeam extends RecyclerView.Adapter<AdapterSearchTeam.CustomViewHolder> {

    private ArrayList<SearchTeamObj> searchTeamObjs;
    private Context context;

    public AdapterSearchTeam(Context context, ArrayList<SearchTeamObj> datalist){
        this.context = context;
        this.searchTeamObjs = datalist;
    }
    class CustomViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        ImageView imageView;
        TextView textView,web,stadium,liga;
        LinearLayout linearLayout;
//        CardView cardView;

        CustomViewHolder(View itemView){
            super(itemView);
            mView = itemView;

            imageView = mView.findViewById(R.id.iconTeam);
            textView = mView.findViewById(R.id.namaTeam);
            web = mView.findViewById(R.id.webSiteTeam);
            stadium = mView.findViewById(R.id.stadiumTeam);
//            liga = mView.findViewById(R.id.leagueTeam);
            linearLayout = mView.findViewById(R.id.linearTeam);
//            cardView = mView.findViewById(R.id.cardView1);
        }
    }
    @Override
    public AdapterSearchTeam.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.team_lyt,parent, false);
        return new AdapterSearchTeam.CustomViewHolder(view);
    }
    @Override
    public void onBindViewHolder(AdapterSearchTeam.CustomViewHolder holder, final int position){
        final SearchTeamObj model = searchTeamObjs.get(position);
        holder.textView.setText(searchTeamObjs.get(position).getStrTeam());
        holder.stadium.setText(searchTeamObjs.get(position).getStrStadium());
        holder.web.setText(searchTeamObjs.get(position).getStrWebsite());
//        holder.liga.setText(teamObjs.get(position).getStrLeague());
        Picasso.with(context).load(searchTeamObjs.get(position).getStrTeamBadge()).into(holder.imageView);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(context, DetailTeam.class);
                o.putExtra("t1",model.getIdTeam());
                o.putExtra("t2",model.getIdSoccerXML());
                o.putExtra("t3",model.getIntLoved().toString());
                o.putExtra("t4",model.getStrTeam());
                o.putExtra("t5",model.getStrTeamShort().toString());
                o.putExtra("t6",model.getStrAlternate());
                o.putExtra("t7",model.getIntFormedYear());
                o.putExtra("t8",model.getStrSport());
                o.putExtra("t9",model.getStrLeague());
                o.putExtra("t10",model.getIdLeague());
                o.putExtra("t12",model.getStrDivision().toString());
                o.putExtra("t13",model.getStrManager());
                o.putExtra("t14",model.getStrStadium());
                o.putExtra("t15",model.getStrKeywords());
                o.putExtra("t16",model.getStrRSS());
                o.putExtra("t17",model.getStrStadiumThumb().toString());
                o.putExtra("t18",model.getStrStadiumDescription());
                o.putExtra("t19",model.getStrStadiumLocation());
                o.putExtra("t20",model.getIntStadiumCapacity());
                o.putExtra("t21",model.getStrWebsite());
                o.putExtra("t22",model.getStrFacebook());
                o.putExtra("t23",model.getStrTwitter());
                o.putExtra("t24",model.getStrInstagram());
                o.putExtra("t25",model.getStrDescriptionEN());
                o.putExtra("t26",model.getStrGender());
                o.putExtra("t27",model.getStrCountry());
                o.putExtra("t28",model.getStrTeamJersey());
                o.putExtra("t29",model.getStrTeamLogo().toString());
                o.putExtra("t30",model.getStrTeamFanart1().toString());
                o.putExtra("t31",model.getStrTeamFanart2().toString());
                o.putExtra("t32",model.getStrTeamFanart3().toString());
                o.putExtra("t33",model.getStrTeamFanart4().toString());
                o.putExtra("t34",model.getStrTeamBanner().toString());
                o.putExtra("t35",model.getStrTeamBadge());
                o.putExtra("t36",model.getStrYoutube());
                o.putExtra("t37",model.getStrLocked());
                context.startActivity(o);
            }
        });
    }

    @Override
    public int getItemCount(){
        return searchTeamObjs.size();
    }}