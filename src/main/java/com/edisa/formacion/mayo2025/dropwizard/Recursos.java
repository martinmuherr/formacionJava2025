package com.edisa.formacion.mayo2025.dropwizard;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import javax.imageio.ImageIO;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public class Recursos {

    @GET
    @Path("/saludo")
    public Response saludar(@QueryParam("nombre") String nombre,
                            @QueryParam("apellido") String apellido,
                            @QueryParam("edad") int edad) {

        return Response.status(404).build();
    }

    @POST
    @Path("/saludo/post")
    public String saludar_post(@QueryParam("nombre") String nombre,
                               @QueryParam("apellido") String apellido,
                               @QueryParam("edad") int edad) {

        return "Hola desde el metodo POST, " + nombre + " " + apellido + ". Tienes " + edad + " años.";
    }

    @GET
    @Path("/codabar/generar")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response generarCodigoBarras(@QueryParam("texto") String texto,
                                        @QueryParam("formato_codigo") String formatoCodigoBarras) {
        try {

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            MultiFormatWriter writer = new MultiFormatWriter();
            BitMatrix matrixBit = writer.encode(texto, BarcodeFormat.valueOf(formatoCodigoBarras.toUpperCase()), 300, 300);

            BufferedImage imageQR = MatrixToImageWriter.toBufferedImage(matrixBit);

            ImageIO.write(imageQR, "JPG", out);
            return Response.ok(out.toByteArray()).type("image/jpg").build();

        } catch (WriterException | IOException e) {
            System.out.println("Error creating the bar code");
            e.printStackTrace();
        }
        return null;
    }
}