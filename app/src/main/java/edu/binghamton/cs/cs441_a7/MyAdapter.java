package edu.binghamton.cs.cs441_a7;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private HiScoreEntry mData;
    private LayoutInflater mInflater;
    //private ItemClickListener mClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView scoreNumber;
        public TextView scoreName;
        public ViewHolder(View v) {
            super(v);
            scoreNumber = v.findViewById(R.id.hiScore_score);
            scoreName = v.findViewById(R.id.hiScore_name);
        }
    }

    MyAdapter(Context context, HiScoreEntry data) {
        this.mInflater = LayoutInflater.from(context);
        mData = data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = "Name: " + mData.getName(position);
        String score = "Score: " + mData.getScore(position);
        holder.scoreName.setText(name);
        holder.scoreNumber.setText(score);
    }

    @Override
    public int getItemCount() {
        return mData.getSize();
    }
}
