package com.uni.julio.supertv.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.uni.julio.supertv.LiveTvApplication;
import com.uni.julio.supertv.R;
import com.uni.julio.supertv.listeners.DialogListener;
import com.uni.julio.supertv.utils.Connectivity;
import com.uni.julio.supertv.utils.DataManager;
import com.uni.julio.supertv.utils.Device;
import com.uni.julio.supertv.utils.Dialogs;
import com.uni.julio.supertv.utils.networing.HttpRequest;
import com.uni.julio.supertv.viewmodel.Lifecycle;
import com.uni.julio.supertv.viewmodel.SplashViewModel;
import com.uni.julio.supertv.viewmodel.SplashViewModelContract;

import java.io.File;

public class SplashForTV extends BaseActivity implements SplashViewModelContract.View {
    private boolean isInit = false;
    private SplashViewModel splashViewModel;
    boolean denyAll=false;
    public ProgressDialog downloadProgress;
    private String updateLocation;
    protected Lifecycle.ViewModel getViewModel() {
        return splashViewModel;
    }

    @Override
    protected Lifecycle.View getLifecycleView() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Device.setHDMIStatus();
        HttpRequest.getInstance().trustAllHosts();//trust all HTTPS hosts
        splashViewModel = new SplashViewModel(this);
        Device.getInstance().getIP();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(this.getBaseContext(), "Can not go back!", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!isInit){
            splashViewModel.checkForUpdate();
            isInit = true;
        }
    }

    @Override
    public void onCheckForUpdateCompleted(boolean hasNewVersion, String location) {
        this.updateLocation = location;
        if (hasNewVersion) {
            Resources res = getResources();
            Dialogs.showTwoButtonsDialog(this,R.string.download , R.string.cancel, R.string.new_version_available, (DialogListener) new DialogListener() {
                public void onAccept() {
                    if (getPermissionStatus("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
                        requestStoragePermission();
                    } else if (Connectivity.isConnected()) {
                        downloadUpdate(updateLocation);
                    } else {
                        goToNoConnectionError();
                    }
                }

                public void onCancel() {
                    splashViewModel.login();
                }

                @Override
                public void onDismiss() {
                    splashViewModel.login();

                }
            });
            return;
        }else {
            splashViewModel.login();
        }
    }
    private void downloadUpdate(String location) {
        if (Connectivity.isConnected()) {
            downloadProgress = new ProgressDialog(getActivity(),ProgressDialog.THEME_HOLO_LIGHT);
            downloadProgress.setProgressStyle(1);
            downloadProgress.setMessage("Downloading");
            downloadProgress.setIndeterminate(false);
            downloadProgress.setCancelable(false);
            downloadProgress.show();
            splashViewModel.downloadUpdate(location, this.downloadProgress);
            return;
        }
        goToNoConnectionError();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent Data) {
        super.onActivityResult(requestCode, resultCode, Data);

        if (requestCode != 4168) {
            return;
        }
        if (getPermissionStatus("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            downloadUpdate(this.updateLocation);

        } else {
            requestStoragePermission();
        }
    }
    public int getPermissionStatus(String androidPermissionName) {
        if (ContextCompat.checkSelfPermission(getActivity(), androidPermissionName) == 0) {
            return 0;
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), androidPermissionName) || !DataManager.getInstance().getBoolean("storagePermissionRequested", false)) {
            return 1;
        }
        return 2;
    }
    public boolean requestStoragePermission() {
        if (Build.VERSION.SDK_INT < 23 || getPermissionStatus("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            return true;
        }
        this.denyAll = false;
        int accept = R.string.accept;
        int message = R.string.permission_storage;
        if (getPermissionStatus("android.permission.WRITE_EXTERNAL_STORAGE") == 2) {
            this.denyAll = true;
            accept = R.string.config;
            message = R.string.permission_storage_config;
        }
        Dialogs.showTwoButtonsDialog( this, accept, (int) R.string.cancel, message, (DialogListener) new DialogListener() {
            @TargetApi(23)
            public void onAccept() {
                if (!denyAll) {
                    DataManager.getInstance().saveData("storagePermissionRequested", Boolean.valueOf(true));
                    requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
                    return;
                }
                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", getPackageName(), null));
                startActivityForResult(intent, 4168);
            }

            public void onCancel() {
                splashViewModel.login();
            }

            @Override
            public void onDismiss() {

            }
        });
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != 1) {
            return;
        }
        if (getPermissionStatus("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            downloadUpdate(this.updateLocation);
        } else {
            requestStoragePermission();
        }
    }
    public void goToNoConnectionError() {
        noInternetConnection(new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                launchActivity(LoginActivity.class);
                getActivity().finish();
            }
        });
    }
    @Override
    public void onDownloadUpdateCompleted(String location) {
        this.downloadProgress.dismiss();
        try
        {
            File file = new File(location);
            Intent intent;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Uri downloaded_apk = getFileUri(this, file);
                intent = new Intent(Intent.ACTION_VIEW).setDataAndType(downloaded_apk,
                        "application/vnd.android.package-archive");
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            } else {
                intent = new Intent("android.intent.action.INSTALL_PACKAGE");
                intent.setDataAndType(Uri.fromFile(file),
                        "application/vnd.android.package-archive");
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            intent.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true);
            intent.putExtra("android.intent.extra.RETURN_RESULT", false);
            intent.putExtra("android.intent.extra.INSTALLER_PACKAGE_NAME", getPackageName());
            finishActivity();
            startActivity(intent);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    Uri getFileUri(Context context, File file) {
        return FileProvider.getUriForFile(context,
                "com.uni.julio.supertv.fileprovider"
                , file);
    }
    @Override
    public void onDownloadUpdateError(int error) {
        downloadProgress.dismiss();

        switch (error) {
            case 1:
                Dialogs.showOneButtonDialog(  getActivity(),   R.string.verify_unknown_sources, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        splashViewModel.login();
                    }
                });
                return;
            default:
                Dialogs.showOneButtonDialog(  getActivity(),   R.string.new_version_generic_error_message, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        splashViewModel.login();
                    }
                });
                return;
        }
    }

    @Override
    public void onLoginCompleted(boolean success) {
        if(success){
            launchActivity(MainActivity.class);
            finishActivity();
        }
        else{
            if(Connectivity.isConnected()){
                launchActivity(LoginActivity.class);
                finishActivity();
            }
            else{
                noInternetConnection(new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishActivity();
                    }
                });
            }
        }
    }

}
