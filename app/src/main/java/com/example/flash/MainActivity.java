package com.example.flash;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.flash.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.button.getText().toString().equals("Turn On")){
                binding.button.setText(R.string.off);
                binding.flashimage.setImageResource(R.drawable.torchoff);
                changedLightState(true);
                }else{
                    binding.button.setText(R.string.on);
                    binding.flashimage.setImageResource(R.drawable.torchoff1);
                    changedLightState(false);
                }
            }
        });
    }

    private void changedLightState(boolean state) {
        CameraManager cameraManager=(CameraManager)getSystemService(CAMERA_SERVICE);
        String camId=null;
        try {
            camId=cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(camId,state);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.button.setText(R.string.on);
    }
}