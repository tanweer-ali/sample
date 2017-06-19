package com.splendo.sample.utils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

/**
 * Created by Ali on 17/06/2017.
 */

public class Permissions {

    private static final int PERMISSIONS_REQUEST_READ_SDCARD = 100;

    private Fragment fragment;
    private Callback callback;

    public interface Callback{
        void onPermissionGranted();
        void onPermissionDenied();
        void showPermissionRationale();
    }

    public Permissions(Fragment fragment, Callback view) {
        this.fragment = fragment;
        this.callback = view;
    }

    public void checkPermissions() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            callback.onPermissionGranted();
            return;
        }

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(fragment.getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (fragment.shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                callback.showPermissionRationale();
            } else {
                fragment.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSIONS_REQUEST_READ_SDCARD);
            }
        } else {
            callback.onPermissionGranted();
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_READ_SDCARD: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callback.onPermissionGranted();
                } else {
                    callback.onPermissionDenied();
                }
                return;
            }
        }
    }

}
