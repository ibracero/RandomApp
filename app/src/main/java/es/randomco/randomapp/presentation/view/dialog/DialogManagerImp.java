package es.randomco.randomapp.presentation.view.dialog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import javax.inject.Inject;

import es.randomco.randomapp.R;
import es.randomco.randomapp.presentation.di.qualifiers.PerActivity;


@PerActivity
public class DialogManagerImp implements DialogManager {

    private static final String TAG = DialogManagerImp.class.getSimpleName();
    private final Activity mActivity;
    private ProgressDialog loadingDialog;

    @Inject
    public DialogManagerImp(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void showInfoDialog(@StringRes int title, @StringRes int content, final DialogListener dialogListener) {
        AlertDialog alertDialog = new AlertDialog.Builder(mActivity)
                .setTitle(title)
                .setMessage(content)
                .setCancelable(false)
                .setPositiveButton(mActivity.getString(R.string.dialog_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dialogListener != null) {
                            dialogListener.onDialogPositiveClick(dialog);
                        } else {
                            dialog.dismiss();
                        }
                    }
                })
                .create();

        alertDialog.show();
    }

    @Override
    public void showLoading() {
        if (loadingDialog == null) {
            loadingDialog = new ProgressDialog(mActivity);
            loadingDialog.setMessage("Generando nuevos usuarios...");
        }

        if (!loadingDialog.isShowing()) {
            loadingDialog.show();
            loadingDialog.setCancelable(false);
        }
    }

    @Override
    public void hideLoading() {
        try {
            if (loadingDialog != null) {
                loadingDialog.dismiss();
            }
        } catch (Exception e) {
            Log.e(TAG, "hideLoading", e);
        }
    }
}
