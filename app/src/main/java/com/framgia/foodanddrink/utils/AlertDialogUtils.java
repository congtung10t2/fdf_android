package com.framgia.foodanddrink.utils;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by framgia on 11/11/2016.
 */
public class AlertDialogUtils {
    public static void  show(Context context, String title, String content) {
        new AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(content)
            .setPositiveButton(android.R.string.yes, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show();
    }

    public static void  show(Context context, int titleId, int contentId) {
        show(context, context.getString(titleId), context.getString(contentId));
    }
}
