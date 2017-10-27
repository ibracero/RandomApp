package es.randomco.randomapp.presentation.view.dialog;

import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;

public interface DialogManager {

    void showInfoDialog(@StringRes int title, @StringRes int content, DialogListener dialogListener);

    void showLoading();

    void hideLoading();

    interface DialogListener {
        void onDialogPositiveClick(DialogInterface dialog);

        void onDialogNegativeClick(DialogInterface dialog);
    }
}