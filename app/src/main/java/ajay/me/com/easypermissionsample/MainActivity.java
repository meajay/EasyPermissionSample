package ajay.me.com.easypermissionsample;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private static final int CAMERA_RESULT_CODE = 101;
    private static final int SMS_RESULT_CODE = 102;
    private static final int STORAGE_RESULT_CODE = 103;
    private static final int LOCATION_RESULT_CODE = 104;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions,
                grantResults, this);
    }


    private boolean hasCameraPermission() {
        return EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA);
    }

    private boolean hasSmsPermission() {
        return EasyPermissions.hasPermissions(this, Manifest.permission.READ_SMS);
    }

    private boolean hasStoragePermission() {
        return EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    private boolean hasLocationPermission() {
        return EasyPermissions.hasPermissions(this, Manifest.permission.ACCESS_FINE_LOCATION);
    }


    @AfterPermissionGranted(CAMERA_RESULT_CODE)
    public void processCameraPermission(View view) {
        if (hasCameraPermission()) {
            Toast.makeText(this, getString(R.string.camera_permission_enabled),
                    Toast.LENGTH_LONG).show();
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.camera_rationale),
                    CAMERA_RESULT_CODE, Manifest.permission.CAMERA);
        }
    }

    @AfterPermissionGranted(SMS_RESULT_CODE)
    public void processSmsPermission(View view) {
        if (hasSmsPermission()) {
            Toast.makeText(this, getString(R.string.sms_permission_enabled),
                    Toast.LENGTH_LONG).show();
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.sms_rationale),
                    SMS_RESULT_CODE, Manifest.permission.READ_SMS);
        }
    }

    @AfterPermissionGranted(STORAGE_RESULT_CODE)
    public void processStoragePermission(View view) {
        if (hasStoragePermission()) {
            Toast.makeText(this, getString(R.string.storage_permission_enabled),
                    Toast.LENGTH_LONG).show();
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.storage_rationable),
                    STORAGE_RESULT_CODE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }

    @AfterPermissionGranted(LOCATION_RESULT_CODE)
    public void processLocationPermission(View view) {
        if (hasLocationPermission()) {
            Toast.makeText(this, getString(R.string.location_permission_enabled),
                    Toast.LENGTH_LONG).show();
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.location_rationale),
                    LOCATION_RESULT_CODE, Manifest.permission.ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "Permission granted : " + String.valueOf(requestCode),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "Permissions denied " + String.valueOf(requestCode),
                Toast.LENGTH_LONG).show();
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }
}
