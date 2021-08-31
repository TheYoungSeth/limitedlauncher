package de.theyoungseth.limitedlauncher;

import static de.theyoungseth.limitedlauncher.MainActivity.apps;
import static de.theyoungseth.limitedlauncher.MainActivity.chosenApps;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class AppList extends AppCompatDialogFragment {

    public List<String> appNames = new ArrayList<>();
    public static TextView label;
    public static ImageView icon;
    public static int currentEdit;
    public static MainActivity.App chosenApp;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //get all apps
        PackageManager pm = getContext().getPackageManager();

        if(apps.isEmpty()) {
            for(ApplicationInfo appInfo : pm.getInstalledApplications(PackageManager.GET_META_DATA)) {
                if(appInfo != null) {
                    MainActivity.App temp2 = new MainActivity.App(pm.getApplicationLabel(appInfo).toString(), pm.getApplicationIcon(appInfo), appInfo.packageName);
                    if(pm.getLaunchIntentForPackage(appInfo.packageName) != null) {
                        apps.add(temp2);
                    } else {
                        Log.d("bruh", temp2.name);
                    }
                }
            }
        }

        if(appNames.isEmpty()) {
            for(MainActivity.App app : apps) {
                appNames.add(app.name);
            }
        }

        String[] actualAppNames = new String[appNames.size()];
        actualAppNames = appNames.toArray(actualAppNames);

        builder.setTitle("Wähle deine App")
                .setSingleChoiceItems(actualAppNames, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        chosenApp = apps.get(i);
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(chosenApp != null) {
                            Log.d("bruh", chosenApp.name);
                            label.setText(chosenApp.name);
                            icon.setImageDrawable(chosenApp.icon);
                            MainActivity.chosenApps.put(currentEdit, chosenApp);
                            MainActivity.saveListData(getContext());
                        } else {
                            chosenApp = apps.get(0);
                            Log.d("bruh", chosenApp.name);
                            label.setText(chosenApp.name);
                            icon.setImageDrawable(chosenApp.icon);
                            MainActivity.chosenApps.put(currentEdit, chosenApp);
                            MainActivity.saveListData(getContext());
                        }
                    }
                })
                .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        return builder.create();
    }
}
