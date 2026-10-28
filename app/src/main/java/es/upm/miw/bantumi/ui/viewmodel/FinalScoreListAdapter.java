package es.upm.miw.bantumi.ui.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.ColorUtils;
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
        final TextView tvPosition, tvName, tvScore, tvDate;

        ScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPosition = itemView.findViewById(R.id.tvScorePosition);
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
            Context ctx = holder.itemView.getContext();

            int s1 = current.getSeedsPlayer1();
            int s2 = current.getSeedsPlayer2();
            int total = s1 + s2;
            int percent = (total == 0) ? 0 : Math.round(100f * Math.max(s1, s2) / total);
            int color, bgAlpha;

            holder.tvPosition.setText(String.valueOf(position + 1));
            holder.tvName.setText(current.getPlayerName());
            holder.tvDate.setText(current.getTimeStamp().format(FMT));
            holder.tvScore.setText(s1 + " - " + s2 + " (" + percent + "%)");

            switch (position) {
                case 0:  color = R.color.gold;     bgAlpha = 50;    break;
                case 1:  color = R.color.silver;   bgAlpha = 50;    break;
                case 2:  color = R.color.bronze;   bgAlpha = 50;    break;
                default: color = R.color.black;    bgAlpha = 10;    break;
            }
            holder.tvPosition.setTextColor(ctx.getColor(color));
            holder.itemView.setBackgroundColor(ColorUtils.setAlphaComponent(ctx.getColor(color), bgAlpha));
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
