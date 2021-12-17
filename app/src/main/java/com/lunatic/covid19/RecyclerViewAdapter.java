package com.lunatic.covid19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private Context context;
    private ArrayList<CoronaItem> coronaItemArrayList;

    RecyclerViewAdapter(Context context, ArrayList<CoronaItem> coronaItemArrayList) {
        this.context = context;
        this.coronaItemArrayList = coronaItemArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.news_items, parent, false);
        return new RecyclerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        CoronaItem coronaItem = coronaItemArrayList.get(position);
        String state = coronaItem.getState();
        String death = coronaItem.getDeath();
        String active = coronaItem.getActive();
        String recovered = coronaItem.getRecovered();
        String confirmed = coronaItem.getConfirmed();
        String lastUpdated = coronaItem.getLastUpdated();
        String todayDeath = coronaItem.getTodayDeath();
        String todayRecovered = coronaItem.getTodayRecovered();
        String todayConfirmed = coronaItem.getTodayConfirmed();
        String vaccinated1 = coronaItem.getVaccinated1();
        String vaccinated2 = coronaItem.getVaccinated2();
        String tested = coronaItem.getTested();
        String todayTested = coronaItem.getTodayTested();

        holder.state.setText(state);
        holder.death.setText(death);
        holder.active.setText(active);
        holder.recovered.setText(recovered);
        holder.confirmed.setText(confirmed);
        holder.lastUpdate.setText(lastUpdated);
        holder.todayDeath.setText(String.format("(%s)", todayDeath));
        holder.todayRecovered.setText(String.format("(%s)", todayRecovered));
        holder.todayConfirmed.setText(String.format("(%s)", todayConfirmed));
        holder.vaccinated1.setText(String.format("  %s            ", vaccinated1));
        holder.vaccinated2.setText(vaccinated2);
        holder.tested.setText(tested);
        holder.todayTested.setText(String.format("(%s)", todayTested));
    }

    @Override
    public int getItemCount() {
        return coronaItemArrayList.size();
    }

    //Holding all the views of the layout chosen to display the content
    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView state, death, recovered, active, confirmed, lastUpdate, todayDeath, todayRecovered, todayConfirmed, vaccinated1, vaccinated2, tested, todayTested;

        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            state = itemView.findViewById(R.id.stateName);
            death = itemView.findViewById(R.id.death);
            recovered = itemView.findViewById(R.id.recovered);
            active = itemView.findViewById(R.id.active);
            confirmed = itemView.findViewById(R.id.confirmed);
            lastUpdate = itemView.findViewById(R.id.lastUpdated);
            todayDeath = itemView.findViewById(R.id.todayDeath);
            todayRecovered = itemView.findViewById(R.id.todayRecovered);
            todayConfirmed = itemView.findViewById(R.id.todayConfirmed);
            vaccinated1 = itemView.findViewById(R.id.vaccinated1);
            vaccinated2 = itemView.findViewById(R.id.vaccinated2);
            tested = itemView.findViewById(R.id.tested);
            todayTested = itemView.findViewById(R.id.todayTested);
        }
    }

}
