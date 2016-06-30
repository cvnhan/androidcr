package com.cvnhan.androidcr.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cvnhan.androidcr.R;
import com.cvnhan.androidcr.utils.ICollections;


/**
 * Created by nhancao on 6/27/16.
 */
public class NDialog {

    public static AlertDialog showErrorDialog(Context context, @StringRes int title, @StringRes int message, ICollections.DialogSimpleInterface callback) {
        return NDialog.showErrorDialog(context, context.getString(title), context.getString(message), callback);
    }

    public static AlertDialog showErrorDialog(Context context, String title, CharSequence message, ICollections.DialogSimpleInterface callback) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_error, null, false);
        TextView tvMessage = (TextView) view.findViewById(R.id.tvMessage);
        tvMessage.setText(message);
        final AlertDialog d = new AlertDialog.Builder(context)
                .setView(view)
                .setTitle(title)
                .setPositiveButton(android.R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, null)
                .create();

        d.setOnShowListener(dialog -> {
            Button btOk = d.getButton(AlertDialog.BUTTON_POSITIVE);
            Button btCancel = d.getButton(AlertDialog.BUTTON_NEGATIVE);
            btOk.setOnClickListener(view1 -> callback.ok(dialog, view1, view));
            btCancel.setOnClickListener(view1 -> callback.cancel(dialog, view1, view));
        });
        return d;
    }

    public static AlertDialog showSimpleDialog(Context context, String title, ICollections.DialogSimpleInterface callback) {
        return showSimpleDialog(context, title, null, callback);
    }

    public static AlertDialog showSimpleDialog(Context context, String title, CharSequence editTextHint, ICollections.DialogSimpleInterface callback) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_simple_edit_text, null, false);
        if (editTextHint != null) {
            EditText etInput = (EditText) view.findViewById(R.id.etInput);
            etInput.setText(editTextHint);
        }

        final AlertDialog d = new AlertDialog.Builder(context)
                .setView(view)
                .setTitle(title)
                .setPositiveButton(android.R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, null)
                .create();

        d.setOnShowListener(dialog -> {
            Button btOk = d.getButton(AlertDialog.BUTTON_POSITIVE);
            Button btCancel = d.getButton(AlertDialog.BUTTON_NEGATIVE);
            btOk.setOnClickListener(view1 -> callback.ok(dialog, view1, view));
            btCancel.setOnClickListener(view1 -> callback.cancel(dialog, view1, view));
        });
        return d;
    }

    public static Dialog showDialog(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_error, null, false);
        Dialog dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(view);
        return dialog;
    }


}
