package com.rifqit.footballlab.Favorite.TeamFavorite;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rifqit.footballlab.Favorite.RealmHelper;
import com.rifqit.footballlab.League.DetailLeague.Team.DetailTeam.DetailTeam;
import com.rifqit.footballlab.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AdapterFavoriteTeam extends RecyclerView.Adapter<AdapterFavoriteTeam.MyViewHolder> {
    private List<FavoriteTeamObj> favoriteTeamObjs;
    Context context;
//    FavoriteTeamObj favoriteTeamObj;
    RealmHelper realmHelper;
    Realm realm;

    public AdapterFavoriteTeam(Context context, List<FavoriteTeamObj> favObjs) {
        this.context = context;
        this.favoriteTeamObjs = favObjs;
    }

    @Override
    public int getItemCount() {
        return favoriteTeamObjs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView,web,stadium,liga;
        LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.iconTeam);
            textView = itemView.findViewById(R.id.namaTeam);
            web = itemView.findViewById(R.id.webSiteTeam);
            stadium = itemView.findViewById(R.id.stadiumTeam);
            linearLayout = itemView.findViewById(R.id.linearTeam);



            Realm.init(context);
            RealmConfiguration configuration = new RealmConfiguration.Builder().build();
            realm = Realm.getInstance(configuration);
            realmHelper = new RealmHelper(realm);
        }
    }

    @Override
    public AdapterFavoriteTeam.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_lyt, parent, false);
        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final AdapterFavoriteTeam.MyViewHolder holder, final int position) {
        final FavoriteTeamObj model = favoriteTeamObjs.get(position);
        holder.textView.setText(favoriteTeamObjs.get(position).getStrTeam());
        holder.stadium.setText(favoriteTeamObjs.get(position).getStrStadium());
        holder.web.setText(favoriteTeamObjs.get(position).getStrWebsite());
//        holder.liga.setText(teamObjs.get(position).getStrLeague());
        Picasso.with(context).load(favoriteTeamObjs.get(position).getStrTeamBadge()).placeholder(R.drawable.ic_placeholder).into(holder.imageView);
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
                o.putExtra("t17",model.getStrStadiumThumb());
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
                o.putExtra("t29",model.getStrTeamLogo());
                o.putExtra("t30",model.getStrTeamFanart1());
                o.putExtra("t31",model.getStrTeamFanart2());
                o.putExtra("t32",model.getStrTeamFanart3());
                o.putExtra("t33",model.getStrTeamFanart4());
                o.putExtra("t34",model.getStrTeamBanner());
                o.putExtra("t35",model.getStrTeamBadge());
                o.putExtra("t36",model.getStrYoutube());
                o.putExtra("t37",model.getStrLocked());
                ((Activity) context).startActivityForResult(o,2);
            }
        });


    }
}
