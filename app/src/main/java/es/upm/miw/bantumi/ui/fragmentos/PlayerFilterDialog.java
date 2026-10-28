package es.upm.miw.bantumi.ui.fragmentos;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;

import es.upm.miw.bantumi.R;
import es.upm.miw.bantumi.ui.actividades.BestScoresActivity;

public class PlayerFilterDialog extends DialogFragment {

    private static final int MAX_OPTIONS_FOR_SIZE = 8;
    private int numOptions = 0;

    @NonNull
    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        final BestScoresActivity main = (BestScoresActivity) requireActivity();

        List<String> nombres = new ArrayList<>(main.getPlayerNames());
        nombres.add(0, getString(R.string.txtTodos));
        String[] opciones = nombres.toArray(new String[0]);
        numOptions = opciones.length;

        AlertDialog.Builder builder = new AlertDialog.Builder(main);
        builder
                .setTitle(R.string.txtOpcionFiltrar)
                .setItems(opciones, (dialog, which) -> {
                    main.setFiltroJugador((which == 0) ? "%" : opciones[which]);
                    main.cargarScores();
                });

        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();

        if (dialog != null && dialog.getWindow() != null) {
            int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.8);
            int maxHeight = (numOptions > MAX_OPTIONS_FOR_SIZE)
                    ? (int) (getResources().getDisplayMetrics().heightPixels * 0.6)
                    : ViewGroup.LayoutParams.WRAP_CONTENT;

            dialog.getWindow().setLayout(width, maxHeight);

            ListView list = ((AlertDialog) dialog).getListView();
            list.setBackgroundColor(requireContext().getColor(R.color.list_background));
            list.setScrollbarFadingEnabled(false);
            list.setDivider(new ColorDrawable(requireContext().getColor(R.color.silver)));
            list.setDividerHeight(2);
            if (android.os.Build.VERSION.SDK_INT >= 29) {
                list.setVerticalScrollbarThumbDrawable(
                        requireContext().getDrawable(R.drawable.scrollbar_thumb));
            }
        }
    }
}
