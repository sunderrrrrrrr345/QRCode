package sunder.com.a360degreeinfodynamics.qrcode;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;



public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
private ZXingScannerView mScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if( ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.CAMERA},
                        5);



             //   Toast.makeText(ScanActivity.this,"Hello",Toast.LENGTH_SHORT).show();


                // Set the scanner view as the content view
            }

        }else{
          //  mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
          //  setContentView(mScannerView);
          //  Toast.makeText(ScanActivity.this,"Hello1",Toast.LENGTH_SHORT).show();// Set the scanner view as the content view
        }
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);
    }

    @Override
    public void handleResult(Result result) {
        // Do something with the result here
        // Log.v("tag", rawResult.getText()); // Prints scan results
        // Log.v("tag", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

        MainActivity.button_scan.setText(result.getText());
        onBackPressed();
        Toast.makeText(ScanActivity.this,"Hello"+result.getText(),Toast.LENGTH_SHORT).show();


        // If you would like to resume scanning, call this method below:
      //  mScannerView.resumeCameraPreview(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {

        switch (requestCode) {

            case 5: {
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {

                        //start flashlight
                        Log.d("Permissions CAMERA", "Permission Granted: " + permissions[i]);


                    } else if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        Log.d("Permissions CAMERA", "Permission Denied: " + permissions[i]);


                    }
                }
                break;
            }
            default: {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
            break;
        }
    }
}
