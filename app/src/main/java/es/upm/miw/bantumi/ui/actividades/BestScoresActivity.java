package es.upm.miw.bantumi.ui.actividades;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import es.upm.miw.bantumi.R;
import es.upm.miw.bantumi.datos.FinalScore;
import es.upm.miw.bantumi.datos.FinalScoreRepository;
import es.upm.miw.bantumi.ui.fragmentos.DeleteScoresAlertDialog;
import es.upm.miw.bantumi.ui.viewmodel.FinalScoreListAdapter;

public class BestScoresActivity extends AppCompatActivity
        implements DeleteScoresAlertDialog.OnDeleteConfirmedListener {

    protected final String LOG_TAG = "MiW";

    private FinalScoreRepository db;
    private FinalScoreListAdapter adapter;
    private TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_best_scores);

        db = Room.databaseBuilder(
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
        tvEmpty = findViewById(R.id.tvEmpty);

        adapter = new FinalScoreListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<FinalScore> scores = db.finalScoreDAO().getTop10Scores();

        adapter.setScores(scores);
        tvEmpty.setVisibility(scores.isEmpty() ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.best_scores_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.opcBorrarResultados) {
            new DeleteScoresAlertDialog().show(getSupportFragmentManager(), "ALERT_DIALOG");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDeleteConfirmed() {
        db.finalScoreDAO().deleteAll();
        adapter.setScores(db.finalScoreDAO().getTop10Scores());
        tvEmpty.setVisibility(View.VISIBLE);

        Log.i(LOG_TAG, "DELETE: Se han borrado las puntuaciones");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}