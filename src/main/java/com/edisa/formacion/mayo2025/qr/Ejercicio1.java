package com.edisa.formacion.mayo2025.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class Ejercicio1 {

    public static void main(String[] args) {
        try {
            if (args.length != 2){
                System.out.println("Wrong number of arguments");
            }
            String texto = args[0];
            String rutaImagen = args[1];

            Hashtable<EncodeHintType, Object> opciones = new Hashtable<>();
            opciones.put(EncodeHintType.CHARACTER_SET, "UTF-8"); //Lo tipico de UTF-8 para que no pete con los caracteres especiales
            opciones.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);  // Nivel de corrección LOW, para que sea muy legible (es la más baja, hay movidas pila mas permisivas)

            // Crear el generador del código QR
            MultiFormatWriter escritor = new MultiFormatWriter();
            BitMatrix matrizBit = escritor.encode(texto, BarcodeFormat.QR_CODE, 300, 300, opciones); //Hace el codigo de barras del tamaño indicado

            // Convertir el BitMatrix a una imagen BufferedImage
            BufferedImage imagenQR = new BufferedImage(matrizBit.getWidth(), matrizBit.getHeight(), BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < matrizBit.getWidth(); x++) {
                for (int y = 0; y < matrizBit.getHeight(); y++) {
                    imagenQR.setRGB(x, y, matrizBit.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                }
            }

            // Guardar la imagen como archivo JPG en la ruta indicada, ta fachero
            File archivo = new File(rutaImagen);
            ImageIO.write(imagenQR, "JPG", archivo);
            System.out.println("El código QR se ha generado correctamente y guardado en: " + archivo.getAbsolutePath());

        } catch (WriterException | IOException e) {
            System.out.println("Error creating the bar code");
            e.printStackTrace();
        }
    }

}
