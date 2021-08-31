package de.theyoungseth.limitedlauncher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static ArrayMap<Integer, App> chosenApps = new ArrayMap(3);
    public static List<App> apps = new ArrayList<>();
    public static String bruh = "bruh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            loadListData(this);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        //buttons and shit
        final Button edit1 = findViewById(R.id.edit1);
        edit1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AppList.currentEdit = 0;
                AppList.label = findViewById(R.id.label1);
                AppList.icon = findViewById(R.id.icon1);
                DialogFragment appList = new AppList();
                appList.setCancelable(true);
                appList.show(getSupportFragmentManager(), "sgc1");
            }
        });

        final TextView label1 = findViewById(R.id.label1);
        if(chosenApps.get(0) != null) label1.setText(chosenApps.get(0).name);
        final ImageView icon1 = findViewById(R.id.icon1);
        if(chosenApps.get(0) != null) icon1.setImageDrawable(chosenApps.get(0).icon);
        icon1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("bruh", chosenApps.get(0).packageName);
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage(chosenApps.get(0).packageName);
                startActivity(launchIntent);
            }
        });

        final Button edit2 = findViewById(R.id.edit2);
        edit2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AppList.currentEdit = 1;
                AppList.label = findViewById(R.id.label2);
                AppList.icon = findViewById(R.id.icon2);
                DialogFragment appList = new AppList();
                appList.setCancelable(true);
                appList.show(getSupportFragmentManager(), "sgc2");
            }
        });

        final TextView label2 = findViewById(R.id.label2);
        if(chosenApps.get(1) != null) label2.setText(chosenApps.get(1).name);
        final ImageView icon2 = findViewById(R.id.icon2);
        if(chosenApps.get(1) != null) icon2.setImageDrawable(chosenApps.get(1).icon);
        icon2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("bruh", chosenApps.get(1).packageName);
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage(chosenApps.get(1).packageName);
                startActivity(launchIntent);
            }
        });

        final Button edit3 = findViewById(R.id.edit3);
        edit3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AppList.currentEdit = 2;
                AppList.label = findViewById(R.id.label3);
                AppList.icon = findViewById(R.id.icon3);
                DialogFragment appList = new AppList();
                appList.setCancelable(true);
                appList.show(getSupportFragmentManager(), "sgc3");
            }
        });

        final TextView label3 = findViewById(R.id.label3);
        if(chosenApps.get(2) != null) label3.setText(chosenApps.get(2).name);
        final ImageView icon3 = findViewById(R.id.icon3);
        if(chosenApps.get(2) != null) icon3.setImageDrawable(chosenApps.get(2).icon);
        icon3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("bruh", chosenApps.get(2).packageName);
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage(chosenApps.get(2).packageName);
                startActivity(launchIntent);
            }
        });

        final Button edit4 = findViewById(R.id.edit4);
        edit4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AppList.currentEdit = 3;
                AppList.label = findViewById(R.id.label4);
                AppList.icon = findViewById(R.id.icon4);
                DialogFragment appList = new AppList();
                appList.setCancelable(true);
                appList.show(getSupportFragmentManager(), "sgc4");
            }
        });

        final TextView label4 = findViewById(R.id.label4);
        if(chosenApps.get(3) != null) label4.setText(chosenApps.get(3).name);
        final ImageView icon4 = findViewById(R.id.icon4);
        if(chosenApps.get(3) != null) icon4.setImageDrawable(chosenApps.get(3).icon);
        icon4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("bruh", chosenApps.get(3).packageName);
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage(chosenApps.get(3).packageName);
                startActivity(launchIntent);
            }
        });
    }

    public static void saveListData(Context ctx) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences("bruh", MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();


        ArrayMap<Integer, String> chosenAppNames = new ArrayMap<>();

        if(chosenApps.get(0) != null) chosenAppNames.put(0, chosenApps.get(0).packageName);
        if(chosenApps.get(1) != null) chosenAppNames.put(1, chosenApps.get(1).packageName);
        if(chosenApps.get(2) != null) chosenAppNames.put(2, chosenApps.get(2).packageName);
        if(chosenApps.get(3) != null) chosenAppNames.put(3, chosenApps.get(3).packageName);

        String json = gson.toJson(chosenAppNames);
        editor.putString("chosenAppNames", json);
        editor.apply();
    }

    public static void loadListData(Context ctx) throws PackageManager.NameNotFoundException {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences("bruh", MODE_MULTI_PROCESS);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("chosenAppNames", null);
        Type type = new TypeToken<ArrayMap<Integer, String>>() {}.getType();
        ArrayMap<Integer, String> chosenAppNames = gson.fromJson(json, type);
        PackageManager pm = ctx.getPackageManager();

        if(chosenAppNames != null) {
            if(chosenAppNames.get(0) != null) Log.d("bruh", chosenAppNames.get(0));

            if(chosenAppNames.get(0) != null) chosenApps.put(0, new App(pm.getApplicationLabel(pm.getApplicationInfo(chosenAppNames.get(0), PackageManager.GET_META_DATA)).toString(), pm.getApplicationIcon(pm.getApplicationInfo(chosenAppNames.get(0), PackageManager.GET_META_DATA)), chosenAppNames.get(0)));
            if(chosenAppNames.get(1) != null) chosenApps.put(1, new App(pm.getApplicationLabel(pm.getApplicationInfo(chosenAppNames.get(1), PackageManager.GET_META_DATA)).toString(), pm.getApplicationIcon(pm.getApplicationInfo(chosenAppNames.get(1), PackageManager.GET_META_DATA)), chosenAppNames.get(1)));
            if(chosenAppNames.get(2) != null) chosenApps.put(2, new App(pm.getApplicationLabel(pm.getApplicationInfo(chosenAppNames.get(2), PackageManager.GET_META_DATA)).toString(), pm.getApplicationIcon(pm.getApplicationInfo(chosenAppNames.get(2), PackageManager.GET_META_DATA)), chosenAppNames.get(2)));
            if(chosenAppNames.get(3) != null) chosenApps.put(3, new App(pm.getApplicationLabel(pm.getApplicationInfo(chosenAppNames.get(3), PackageManager.GET_META_DATA)).toString(), pm.getApplicationIcon(pm.getApplicationInfo(chosenAppNames.get(3), PackageManager.GET_META_DATA)), chosenAppNames.get(3)));

            if(chosenApps == null) {
                chosenApps = new ArrayMap<Integer, App>();
            }
        }
    }

    @Override
    public void onBackPressed() {

    }

    /*@Override
    public void onPause() {
        super.onPause();
        if(chosenApps != null) saveData();
    }*/

    public static class App {
        public String name;
        public Drawable icon;
        public String packageName;

        public App(String appName, Drawable icon, String packageName) {
            this.icon = icon;
            this.name = appName;
            this.packageName = packageName;
        }
    }
}

