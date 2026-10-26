package es.upm.miw.bantumi.ui.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.color.utilities.Score;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import es.upm.miw.bantumi.R;
import es.upm.miw.bantumi.datos.FinalScore;

public class FinalScoreListAdapter extends RecyclerView.Adapter<FinalScoreListAdapter.ScoreViewHolder> {

    private final LayoutInflater mInflater;
    private List<FinalScore> scores = new ArrayList<>();

    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    class ScoreViewHolder extends RecyclerView.ViewHolder{
        final TextView tvName, tvScore, tvDate;

        ScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName  = itemView.findViewById(R.id.tvScoreName);
            tvScore = itemView.findViewById(R.id.tvScore);
            tvDate  = itemView.findViewById(R.id.tvScoreDate);
        }
    }

    /**
     * Constructor
     *
     * @param context context
     */
    public FinalScoreListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item_score, parent, false);
        return new ScoreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        if (scores != null) {
            FinalScore current = scores.get(position);
            holder.tvName.setText(current.getPlayerName());
            holder.tvDate.setText(current.getTimeStamp().format(FMT));
            holder.tvScore.setText(current.getSeedsPlayer1() + " - " + current.getSeedsPlayer2());
        } else {

        }
    }

    public void setScores(List<FinalScore> scores) {
        this.scores = scores;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (scores.isEmpty())
                ? 0
                : scores.size();
    }
}
