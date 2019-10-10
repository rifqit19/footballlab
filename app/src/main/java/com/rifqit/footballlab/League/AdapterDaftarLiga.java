package com.rifqit.footballlab.League;

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
import com.rifqit.footballlab.League.DetailLeague.DetailLiga;
import com.rifqit.footballlab.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterDaftarLiga extends RecyclerView.Adapter<AdapterDaftarLiga.CustomViewHolder> {

    private ArrayList<LeagueObj> leagueObjs;
    private Context context;

    public AdapterDaftarLiga(Context context, ArrayList<LeagueObj> datalist){
        this.context = context;
        this.leagueObjs = datalist;
    }
    class CustomViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        ImageView imageView;
        TextView textView;
        LinearLayout linearLayout;
//        CardView cardView;

        CustomViewHolder(View itemView){
            super(itemView);
            mView = itemView;

            imageView = mView.findViewById(R.id.iconLiga);
            textView = mView.findViewById(R.id.namaLiga);
            linearLayout = mView.findViewById(R.id.linearLiga);
//            cardView = mView.findViewById(R.id.cardView1);
        }
    }
    @Override
    public AdapterDaftarLiga.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.daftar_liga,parent, false);
        return new AdapterDaftarLiga.CustomViewHolder(view);
    }
    @Override
    public void onBindViewHolder(AdapterDaftarLiga.CustomViewHolder holder, final int position){
        holder.textView.setText(leagueObjs.get(position).getStrLeague());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(context, DetailLiga.class);
                p.putExtra("ligaList", new Gson().toJson(leagueObjs.get(position)));
                context.startActivity(p);
            }
        });

        String namaLiga = leagueObjs.get(position).getStrLeague();

        if (namaLiga.equals("English Premier League")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/i6o0kh1549879062.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("English League Championship")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/m7urjx1535732496.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Scottish Premier League")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/vw72bl1534096708.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("German Bundesliga")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/0j55yv1534764799.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Italian Serie A")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/mmd1de1541938367.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("French Ligue 1")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/8f5jmf1516458074.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Spanish La Liga")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/7onmyv1534768460.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Greek Superleague Greece")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/g5rxbr1517435561.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Dutch Eredivisie")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/ywoi5k1534590331.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Belgian Jupiler League")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/eil7sc1534590134.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Turkish Super Lig")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/rsgf8z1533755936.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Danish Superliga")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/qeutwx1534768349.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Portuguese Primeira Liga")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/cplp641535733210.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("American Major League Soccer")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/dqo6r91549878326.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Swedish Allsvenskan")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/9x9h091508667577.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Mexican Primera League")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/vsussv1422037333.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Brazilian Brasileirao")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/d97d4v1549879038.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Ukrainian Premier League")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/qprvpy1471773025.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Russian Football Premier League")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/fg0ad21532769374.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Australian A-League")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/sfhanl1547383730.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Eliteserien")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/owo80l1512822583.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Chinese Super League")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/c7evs21534799383.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("_No League")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/xxwwwt1421422891.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Italian Serie B")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/uhej6t1547499891.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Scottish Championship")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/6p848a1534096538.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("English League 1")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/vm1qlp1535986713.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("English League 2")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/tuyxuw1467457483.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Italian Lega Pro")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/6d16ly1510941814.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("German 2. Bundesliga")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/hl40981534764789.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Spanish Adelante")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/rrptqp1474401449.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("French Ligue 2")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/3mhiil1517699467.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Swedish Superettan")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/mjer6t1523909684.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Brazilian Brasileirao Serie B")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/0206v41534575321.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Argentinian Primera Division")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/me5dez1517699947.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Polish Ekstraklasa")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/l3jovw1516960585.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("FIFA World Cup")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/jn3m4c1533806598.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Uruguayan Primera Division")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/wursvx1425811995.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("American NASL")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/0un3ta1549878279.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Norwegian 1. Divisjon")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/cgyfwu1512823098.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Welsh Premier League")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/aeqtv31548798048.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("UEFA Champions League")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/dtp1dl1534767233.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("UEFA Europa League")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/hr7j521534767345.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("FA Cup")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/4e8atk1554029260.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Copa del Rey")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/l4qwj41534768910.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Coupe de France")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/l6fitb1546469041.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("DFB-Pokal")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/dbb5wp1534868931.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("UEFA Nations League")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/zi2u5t1536834932.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("African Cup of Nations")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/1ts0ne1549452246.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Colombia Categoría Primera A")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/i9mug61549452635.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Confederations Cup")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/a731vj1549452752.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Copa America")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/lpih261550431780.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Copa Argentina")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/mekufm1549460132.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Copa Libertadores")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/mlo2451549460358.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("European Championships")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/el53xu1551733974.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("FIFA Club World Cup")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/7acq3h1549453425.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Coupe de la Ligue")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/j8zvmz1549453366.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("International Champions Cup")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/4qff241553414228.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Coppa Italia")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/konhxs1549460516.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Supercoppa Italiana")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/94q86a1549460679.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Taca de Liga")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/rm69fl1549461765.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Taca de Portugal")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/emq3a41549462147.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Supercopa de Espana")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/pdgh6e1549460616.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("UEFA Super Cup")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/1eimvx1551123834.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Venezuela Primera Division")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/eqsl1z1549453145.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("_Defunct Soccer Teams")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/bcvsib1550155063.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Moroccan Championship")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/z3h9s11551350969.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("American NWSL")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/srpnpi1551383087.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("European Cup")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/upload-icon.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("UEFA Cup")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/media/league/badge/small/4e8atk1554029260.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("English Old 1st Division")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/upload-icon.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else if (namaLiga.equals("Football League Super Cup")){
            Picasso.with(context).load("https://www.thesportsdb.com/images/upload-icon.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);
        }else {
            Picasso.with(context).load("https://www.thesportsdb.com/images/logo_new-medium.png").placeholder(R.drawable.ic_placeholder).into(holder.imageView);

        }
    }

    @Override
    public int getItemCount(){
        return leagueObjs.size();
    }}