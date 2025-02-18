package com.immortalcrab.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;


public class QRCode {

    public static void main(String[] args) {
        try {
            var arr = new String[] {"34598foijsdof89uj34oij", "oisdf8u34okjoisjdfdf", "wepokczmn7823"};
            for (String i : arr) {
                generate(i, 1250, 1250, "/home/userd/qrcode_" + i + ".png");
            }
        } catch (Exception e) {
            System.out.println("Could not generate QR Code" + e);
        }
    }

    public static void generate(String text, int width, int height, String filePath) throws Exception {
        QRCodeWriter qcWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qcWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        // MatrixToImageWriter.writeToPath(bitMatrix, "JPEG", path);
    }
}
