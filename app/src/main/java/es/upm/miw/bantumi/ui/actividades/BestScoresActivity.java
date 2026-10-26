package es.upm.miw.bantumi.ui.actividades;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

import es.upm.miw.bantumi.R;
import es.upm.miw.bantumi.datos.FinalScore;
import es.upm.miw.bantumi.datos.FinalScoreRepository;
import es.upm.miw.bantumi.ui.viewmodel.FinalScoreListAdapter;

public class BestScoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_best_scores);

        FinalScoreRepository db = Room.databaseBuilder(
                        getApplicationContext(),
                        FinalScoreRepository.class,
                        FinalScoreRepository.DATA_BASE)
                .allowMainThreadQueries()
                .build();

        // Establece las inserciones de recortes de pantalla
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            androidx.core.graphics.Insets systemBars = insets.getInsets(
                    WindowInsetsCompat.Type.systemBars()
                            | WindowInsetsCompat.Type.displayCutout());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Add the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        TextView tvEmpty = findViewById(R.id.tvEmpty);

        final FinalScoreListAdapter adapter = new FinalScoreListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<FinalScore> scores = db.finalScoreDAO().getTop10Scores();

        adapter.setScores(scores);
        tvEmpty.setVisibility(scores.isEmpty() ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}