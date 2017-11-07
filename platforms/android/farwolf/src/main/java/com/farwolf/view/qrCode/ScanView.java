package com.farwolf.view.qrCode;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Rect;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.farwolf.libary.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class ScanView extends FrameLayout implements Camera.PreviewCallback {
    private Camera mCamera;
    private CameraPreview mPreview;
    private View showPanel;
    private QrSize qrSize;

    public ScanView(Context context) {
        super(context);
        init();
    }

    public ScanView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        addView(mPreview = new CameraPreview(getContext()));
        showPanel = View.inflate(getContext(), R.layout.api_scan_view, null);
        addView(showPanel);
        initMultiFormatReader();
    }

    public void setContentView(int res) {
        try {
            View panelView = View.inflate(getContext(), res, null);
            removeView(showPanel);
            showPanel = panelView;
            addView(showPanel);
        } catch (Exception e) {
            return;
        }
    }

    public void setCornersColor(String color)
    {
        ViewGroup v= (ViewGroup)findViewById(R.id.scan_window);
        for(int i=0;i<v.getChildCount();i++)
        {
            View vx= v.getChildAt(i);
            vx.setBackgroundColor(Color.parseColor(color));
        }
    }

    public View getContentView() {
        return showPanel;
    }

    public interface QrSize {
        public Rect getDetectRect();
    }

    public void startCamera(int cameraId) {
        startCamera(CameraUtils.getCameraInstance(cameraId));
    }

    public void startCamera(Camera camera) {
        mCamera = camera;
        if (mCamera != null) {
            mPreview.setCamera(mCamera, this);
            mPreview.initCameraPreview();
        }

//        for (int i = 0; i < 3; i++) {
//            camera.addCallbackBuffer(new byte[((previewWidth * previewHeight) * bitsPerPixel) / 8 ]);
//        }
//        camera.setPreviewCallbackWithBuffer(this);
    }

    public void startCamera() {
        startCamera(CameraUtils.getCameraInstance());
    }

    public void stopCamera() {
        if (mCamera != null) {
            mPreview.stopCameraPreview();
            mPreview.setCamera(null, null);
            Camera temporary = mCamera;
            mCamera = null;
            temporary.setPreviewCallback(null) ;
            temporary.release();
        }
    }

    public synchronized Rect getFramingRectInPreview(int previewWidth, int previewHeight) {
        if (qrSize == null || qrSize.getDetectRect() == null) {
            return null;
        }
        Rect rect = qrSize.getDetectRect();

        int width=showPanel.getWidth();
        int height=showPanel.getHeight();
        if ((rect.right - rect.left) != 0 && (rect.top - rect.bottom) != 0) {
            rect.left = rect.left * previewWidth / width;
            rect.right = rect.right * previewWidth / width;
            rect.top = rect.top * previewHeight / height;
            rect.bottom = rect.bottom * previewHeight / height;
            return rect;
        } else {
            return null;
        }
    }

    public void setFlash(boolean flag) {
        if (mCamera != null && CameraUtils.isFlashSupported(mCamera)) {

            Camera.Parameters parameters = mCamera.getParameters();
            if (flag) {
                if (parameters.getFlashMode().equals(Camera.Parameters.FLASH_MODE_TORCH)) {
                    return;
                }
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            } else {
                if (parameters.getFlashMode().equals(Camera.Parameters.FLASH_MODE_OFF)) {
                    return;
                }
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            }
            mCamera.setParameters(parameters);
        }
    }

    public boolean getFlash() {
        if (mCamera != null && CameraUtils.isFlashSupported(mCamera)) {
            Camera.Parameters parameters = mCamera.getParameters();
            if (parameters.getFlashMode().equals(Camera.Parameters.FLASH_MODE_TORCH)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void toggleFlash() {
        if (mCamera != null && CameraUtils.isFlashSupported(mCamera)) {
            Camera.Parameters parameters = mCamera.getParameters();
            if (parameters.getFlashMode().equals(Camera.Parameters.FLASH_MODE_TORCH)) {
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            } else {
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            }
            mCamera.setParameters(parameters);
        }
    }

    public void setAutoFocus(boolean state) {
        if (mPreview != null) {
            mPreview.setAutoFocus(state);
        }
    }

    public boolean cameraAvailable() {
        return mCamera == null ? false : true;
    }

    public interface ResultHandler {
        public void handleResult(Result rawResult);
    }

    private int interval = 0;
    private MultiFormatReader mMultiFormatReader;
    public static final List<BarcodeFormat> ALL_FORMATS = new ArrayList<BarcodeFormat>();
    private List<BarcodeFormat> mFormats;
    private ResultHandler mResultHandler;

    static {
        ALL_FORMATS.add(BarcodeFormat.UPC_A);
        ALL_FORMATS.add(BarcodeFormat.UPC_E);
        ALL_FORMATS.add(BarcodeFormat.EAN_13);
        ALL_FORMATS.add(BarcodeFormat.EAN_8);
        ALL_FORMATS.add(BarcodeFormat.RSS_14);
        ALL_FORMATS.add(BarcodeFormat.CODE_39);
        ALL_FORMATS.add(BarcodeFormat.CODE_93);
        ALL_FORMATS.add(BarcodeFormat.CODE_128);
        ALL_FORMATS.add(BarcodeFormat.ITF);
        ALL_FORMATS.add(BarcodeFormat.CODABAR);
        ALL_FORMATS.add(BarcodeFormat.QR_CODE);
        ALL_FORMATS.add(BarcodeFormat.DATA_MATRIX);
        ALL_FORMATS.add(BarcodeFormat.PDF_417);
    }

    public void setFormats(List<BarcodeFormat> formats) {
        mFormats = formats;
        initMultiFormatReader();
    }

    public void setResultHandler(ResultHandler resultHandler) {
        onlyOnce = true;
        mResultHandler = resultHandler;
    }

    public Collection<BarcodeFormat> getFormats() {
        if (mFormats == null) {
            return ALL_FORMATS;
        }
        return mFormats;
    }

    private void initMultiFormatReader() {
        Map<DecodeHintType, Object> hints = new EnumMap<DecodeHintType, Object>(DecodeHintType.class);
        hints.put(DecodeHintType.POSSIBLE_FORMATS, getFormats());
        mMultiFormatReader = new MultiFormatReader();
        mMultiFormatReader.setHints(hints);
    }

    Timer timer;

    private void cancelTask() {
        timer.cancel();
    }

    private void startFreshThread(final Camera camera, final Camera.PreviewCallback callback) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Log.e("Run", "Running");
                if (camera == null || !cameraAvailable()) {
                    cancelTask();
                    return;
                }
                camera.setOneShotPreviewCallback(callback);
            }
        }, 0, 100);
    }

    private byte[] rotatedData;
    private boolean onlyOnce;

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        if (onlyOnce) {
            startFreshThread(camera, this);
            onlyOnce = false;
        }
        camera.addCallbackBuffer(data);
        Camera.Parameters parameters = camera.getParameters();
        Camera.Size size = parameters.getPreviewSize();
        int width = size.width;
        int height = size.height;
        if (DisplayUtils.getScreenOrientation(getContext()) == Configuration.ORIENTATION_PORTRAIT) {
            if (rotatedData == null) {
                rotatedData = new byte[data.length];
            }
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++)
                    rotatedData[x * height + height - y - 1] = data[x + y * width];
            }
            int tmp = width;
            width = height;
            height = tmp;
            data = rotatedData;
        }
        Result rawResult = null;
        PlanarYUVLuminanceSource source = buildLuminanceSource(data, width, height);

        if (source != null) {
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            try {
                rawResult = mMultiFormatReader.decodeWithState(bitmap);
            } catch (ReaderException re) {
                // continue
            } catch (NullPointerException npe) {
                // This is terrible
            } catch (ArrayIndexOutOfBoundsException aoe) {

            } finally {
                mMultiFormatReader.reset();
            }
        }

        if (rawResult != null) {
//            stopCamera();
            if (mResultHandler != null) {
                mResultHandler.handleResult(rawResult);
            }
        }
    }

    public PlanarYUVLuminanceSource buildLuminanceSource(byte[] data, int width, int height) {
        Rect rect = getFramingRectInPreview(width, height);
        if (rect == null) {
            return null;
        }
        // Go ahead and assume it's YUV rather than die.
        PlanarYUVLuminanceSource source = null;

        try {
            source = new PlanarYUVLuminanceSource(data, width, height, rect.left, rect.top,
                    rect.width(), rect.height(), false);
        } catch (Exception e) {
        }

        return source;
    }

    public void setQrSize(QrSize size) {
        qrSize = size;
    }
}
