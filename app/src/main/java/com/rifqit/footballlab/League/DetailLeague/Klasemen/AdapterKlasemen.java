package com.rifqit.footballlab.League.DetailLeague.Klasemen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rifqit.footballlab.R;

import java.util.ArrayList;

public class AdapterKlasemen extends RecyclerView.Adapter<AdapterKlasemen.CustomViewHolder> {

    private ArrayList<KlasemenObj> klasemenObjs;
    private Context context;

    public AdapterKlasemen(Context context, ArrayList<KlasemenObj> datalist){
        this.context = context;
        this.klasemenObjs = datalist;
    }
    class CustomViewHolder extends RecyclerView.ViewHolder{
        public final View mView;

        TextView r,nama,mp,w,d,l,gf,ga,gd,pts;

        CustomViewHolder(View itemView){
            super(itemView);
            mView = itemView;

            r = mView.findViewById(R.id.rank);
            nama = mView.findViewById(R.id.namaTeamKlasemen);
            mp = mView.findViewById(R.id.mp);
            w = mView.findViewById(R.id.w);
            d = mView.findViewById(R.id.d);
            l = mView.findViewById(R.id.l);
            gf = mView.findViewById(R.id.gf);
            ga = mView.findViewById(R.id.ga);
            gd = mView.findViewById(R.id.gd);
            pts = mView.findViewById(R.id.pts);

        }
    }
    @Override
    public AdapterKlasemen.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.lyt_klasemen,parent, false);
        return new AdapterKlasemen.CustomViewHolder(view);
    }
    @Override
    public void onBindViewHolder(AdapterKlasemen.CustomViewHolder holder, final int position){
        holder.r.setText(position+1+" ");
        holder.nama.setText(klasemenObjs.get(position).getName());
        holder.mp.setText(klasemenObjs.get(position).getPlayed().toString());
        holder.gf.setText(klasemenObjs.get(position).getGoalsfor().toString());
        holder.ga.setText(klasemenObjs.get(position).getGoalsagainst().toString());
        holder.gd.setText(klasemenObjs.get(position).getGoalsdifference().toString());
        holder.pts.setText(klasemenObjs.get(position).getTotal().toString());
        holder.w.setText(klasemenObjs.get(position).getWin().toString());
        holder.d.setText(klasemenObjs.get(position).getDraw().toString());
        holder.l.setText(klasemenObjs.get(position).getLoss().toString());
    }

    @Override
    public int getItemCount(){
        return klasemenObjs.size();
    }}