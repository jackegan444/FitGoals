package com.jegan.fitgoals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RunRecycleAdap extends RecyclerView.Adapter<RunRecycleAdap.RunViewHolder> {
    private OnItemCLickListener mListener;
    private List<Run> runs;
    private final LayoutInflater inflater;

    public interface OnItemCLickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemCLickListener listener) {
        mListener = listener;
    }

    public static class RunViewHolder extends RecyclerView.ViewHolder {
        private final TextView runItemView;

        public RunViewHolder(View v, OnItemCLickListener listener) {
            super(v);
            runItemView = v.findViewById(R.id.rowRunName);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }

                }
            });
        }
    }

    public RunRecycleAdap(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RunRecycleAdap.RunViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = inflater.inflate(R.layout.run_recyclerview_row, parent, false);
        RunViewHolder vh = new RunViewHolder(itemView, mListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(RunViewHolder holder, int position) {

        Run current = runs.get(position);
        holder.runItemView.setText(current.getRunName());
    }

    public void setRuns(List<Run> runs) {
        this.runs = runs;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (runs != null)
            return runs.size();
        else return 0;
    }

    public Run getRunAtPosition (int position) {
        return runs.get(position);
    }
}
