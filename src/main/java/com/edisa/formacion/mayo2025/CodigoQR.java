package com.edisa.formacion.mayo2025;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;

public class CodigoQR {

    public static void main(String[] args) {
        try {
            if(args.length != 3) {
                System.out.println("Wrong number argument");
            }
            String texto = args[0];
            String rutaImagen = args[1];
            String tipoCodigoBarras = args[2];
            Path ruta = Paths.get(tipoCodigoBarras);

            Hashtable<EncodeHintType, Object> opciones = new Hashtable<>();
            opciones.put(EncodeHintType.CHARACTER_SET, "UTF-8"); //Lo tipico de UTF-8 para que no pete con los caracteres especiales
            opciones.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L); // Nivel de corrección LOW, para que sea muy legible (es la más baja, hay movidas pila mas permisivas)

            //Pillamos el tipo de codigo de barras en funcion de cual sea el que se haya pasado por argumentos
            BarcodeFormat format = null;

            switch (tipoCodigoBarras.toUpperCase()) {
                case "QR":
                    format = BarcodeFormat.QR_CODE;
                    break;
                case "EAN-13":
                    format = BarcodeFormat.EAN_13;
                    break;
                case "EAN-8":
                    format = BarcodeFormat.EAN_8;
                    break;
                case "AZTEC":
                    format = BarcodeFormat.AZTEC;
                    break;
                case "DATA-MATRIX":
                    format = BarcodeFormat.DATA_MATRIX;
                    break;
                default:
                    System.out.println("Wrong bar code, try a different one");
                    return;
            }
            Files.createDirectories(ruta);

            // Crear el generador del código QR
            MultiFormatWriter writer = new MultiFormatWriter();
            BitMatrix matrixBit = writer.encode(texto, format, 300, 300, opciones);

            // Convertir el BitMatrix a una imagen BufferedImage
            BufferedImage imageQR = new BufferedImage(matrixBit.getWidth(), matrixBit.getHeight(), BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < matrixBit.getWidth(); x++) {
                for (int y = 0; y < matrixBit.getHeight(); y++) {
                    imageQR.setRGB(x, y, matrixBit.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                }
            }

            // Guardar la imagen como archivo JPG en la ruta indicada, ta fachero
            File archivo = new File(tipoCodigoBarras + "/" + rutaImagen);
            ImageIO.write(imageQR, "JPG", archivo);
            System.out.println("El código QR se ha generado correctamente y guardado en: " + archivo.getAbsolutePath());

        } catch (WriterException | IOException e) {
            System.out.println("Error creating the bar code");
            e.printStackTrace();
        }
    }
}
