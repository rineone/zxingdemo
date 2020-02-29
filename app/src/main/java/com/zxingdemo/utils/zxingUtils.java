package com.zxingdemo.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.HashMap;
import java.util.Map;

public class zxingUtils {

    public static Bitmap getErWeiMa(String name){
        Bitmap bit = null;
        int width = 200;
        int height = 200;
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            Map<EncodeHintType, String> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); //记得要自定义长宽
            BitMatrix encode = null;
            try {
                encode = qrCodeWriter.encode(name, BarcodeFormat.QR_CODE, width, height, hints);
            } catch (WriterException e) {
                e.printStackTrace();
            }
            int[] colors = new int[width * height];
            //利用for循环将要表示的信息写出来
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if (encode.get(i, j)) {
                        colors[i * width + j] = Color.BLACK;
                    } else {
                        colors[i * width + j] = Color.WHITE;
                    }
                }
            }

            bit = Bitmap.createBitmap(colors, width, height, Bitmap.Config.RGB_565);
        }catch (Exception e){

        }
        return bit;
    }

    public static void toErWeiMa(){

    }

//    /**
//     * 在二维码中间添加Logo图案
//     */
//    private static Bitmap Logo(Bitmap src, Bitmap logo) {
//        if (src == null) {
//            return null;
//        }
//
//        if (logo == null) {
//            return src;
//        }
//
//        //获取图片的宽高
//        int srcWidth = src.getWidth();
//        int srcHeight = src.getHeight();
//        int logoWidth = logo.getWidth();
//        int logoHeight = logo.getHeight();
//
//        if (srcWidth == 0 || srcHeight == 0) {
//            return null;
//        }
//
//        if (logoWidth == 0 || logoHeight == 0) {
//            return src;
//        }
//
//        //logo大小为二维码整体大小的1/5
//        float scaleFactor = srcWidth * 1.0f / 5 / logoWidth;
//        Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
//        try {
//            Canvas canvas = new Canvas(bitmap);
//            canvas.drawBitmap(src, 0, 0, null);
//            canvas.scale(scaleFactor, scaleFactor, srcWidth / 2, srcHeight / 2);
//            canvas.drawBitmap(logo, (srcWidth - logoWidth) / 2, (srcHeight - logoHeight) / 2, null);
//
//            canvas.save(Canvas.ALL_SAVE_FLAG)
//            canvas.restore();
//        } catch (Exception e) {
//            bitmap = null;
//            e.getStackTrace();
//        }
//
//        return bitmap;
//    }
}
