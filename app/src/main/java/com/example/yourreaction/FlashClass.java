package com.example.yourreaction;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Handler;

public class FlashClass
{
    private static Context context;
    private static boolean status = false;
    private static CameraManager cameraManager;
    private static FlashClass flashClass;

    private FlashClass(Context context)
    {
        this.context = context;
    }

    public static void getFlashClass(Context context)
    {
        if (flashClass == null)
            flashClass = new FlashClass(context);

        startFlash();
    }

    private static void flashON()
    {
        cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        try
        {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, true);
            status = true;
        }
        catch (CameraAccessException e){
            e.printStackTrace();
        }
    }

    private static void flashOFF()
    {
        cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        try
        {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, false);
            status = false;
        }
        catch (CameraAccessException e){
            e.printStackTrace();
        }
    }

    private static void startFlash()
    {
        for(int i = 0; i < 50; ++i)
        {
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    if(status)
                        flashOFF();
                    else
                        flashON();
                }
            }, 5);
        }

    }
}
