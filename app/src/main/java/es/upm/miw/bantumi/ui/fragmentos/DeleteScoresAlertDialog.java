package es.upm.miw.bantumi.ui.fragmentos;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import androidx.fragment.app.DialogFragment;

import es.upm.miw.bantumi.R;
import es.upm.miw.bantumi.datos.FinalScore;
import es.upm.miw.bantumi.dominio.logica.JuegoBantumi;
import es.upm.miw.bantumi.ui.actividades.BestScoresActivity;
import es.upm.miw.bantumi.ui.actividades.MainActivity;

public class DeleteScoresAlertDialog extends DialogFragment {

    public interface OnDeleteConfirmedListener {
        void onDeleteConfirmed();
    }

    @NonNull
    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState){
        final OnDeleteConfirmedListener listener =
                (OnDeleteConfirmedListener) requireActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder
                .setTitle(R.string.txtDialogoDeleteScores)
                .setMessage(R.string.txtDialogoDeleteScoresPregunta)
                .setPositiveButton(
                        getString(android.R.string.ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listener.onDeleteConfirmed();
                            }
                        }
                )
                .setNegativeButton(getString(android.R.string.cancel),null);

        return builder.create();
    }
}
