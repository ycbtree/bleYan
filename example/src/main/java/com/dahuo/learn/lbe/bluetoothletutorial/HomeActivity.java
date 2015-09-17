package com.dahuo.learn.lbe.bluetoothletutorial;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.dahuo.learn.lbe.bluetoothletutorial.fragment.AboutFragment;
import com.dahuo.learn.lbe.bluetoothletutorial.fragment.BleDevicesFragment;
import com.mikepenz.aboutlibraries.LibsBuilder;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialAccountListener;

public class HomeActivity extends MaterialNavigationDrawer implements MaterialAccountListener {
    MaterialAccount account;

    @Override
    public void init(Bundle bundle) {
        //KitKat translucent modes
        setTranslucentStatus(this, true);


        //账号处理
        account = new MaterialAccount(this.getResources(),
                getString(R.string.about_me_name), getString(R.string.about_me_email),
                R.drawable.profile, R.drawable.header);
        this.addAccount(account);

        // set listener
        this.setAccountListener(this);

        MaterialSection homeSection =
                newSection(getString(R.string.app_home),
                new IconicsDrawable(this)
                    .icon(FontAwesome.Icon.faw_home)
                    .color(Color.WHITE)
                    .sizeDp(24),
                BleDevicesFragment.newInstance(getString(R.string.app_home)));

        MaterialSection openSourceSection =
                newSection(getString(R.string.app_open_source),
                new IconicsDrawable(this)
                    .icon(FontAwesome.Icon.faw_github)
                    .color(Color.WHITE)
                    .sizeDp(24),
                new LibsBuilder()
                        //get the fragment
                        .fragment());
        MaterialSection aboutSection =
                newSection(getString(R.string.app_about),
                new IconicsDrawable(this)
                    .icon(GoogleMaterial.Icon.gmd_email)
                    .color(Color.WHITE)
                    .sizeDp(24),
                AboutFragment.newInstance(getString(R.string.app_about)));


        addSection(homeSection);
        addSection(openSourceSection);
        addSection(aboutSection);

        disableLearningPattern();
        // add pattern
        this.setBackPattern(MaterialNavigationDrawer.BACKPATTERN_BACK_TO_FIRST);
        //allowArrowAnimation();
        enableToolbarElevation();
    }


    @TargetApi(19)
    public void setTranslucentStatus(Activity activity, boolean on) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            Window win = activity.getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);

            SystemBarTintManager tintManager = new SystemBarTintManager(activity);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setNavigationBarTintEnabled(false);
            tintManager.setStatusBarTintColor(activity.getResources().getColor(R.color.colorPrimary));
            //tintManager.setNavigationBarTintColor(activity.getResources().getColor(R.color.colorPrimary));
            //			tintManager.setStatusBarTintResource(R.color.colorPrimary);
        }
    }

    @Override
    public void onAccountOpening(MaterialAccount materialAccount) {

    }

    @Override
    public void onChangeAccount(MaterialAccount materialAccount) {

    }
}
