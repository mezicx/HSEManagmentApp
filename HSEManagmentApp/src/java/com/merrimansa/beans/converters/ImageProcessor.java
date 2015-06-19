/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.beans.converters;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.primefaces.model.UploadedFile;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Steve
 */
public class ImageProcessor {

    public String processImage(UploadedFile file) throws IOException {

        InputStream is = new BufferedInputStream(file.getInputstream());

        BufferedImage image = ImageIO.read(is);
        
        Image resizedImage =  image.getScaledInstance(500, -1, Image.SCALE_SMOOTH);

        BufferedImage bimage = new BufferedImage(resizedImage.getWidth(null), resizedImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        
        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(resizedImage, 0, 0, null);
        bGr.dispose();

        return convertToBase64(bimage);
    }

    String convertToBase64(BufferedImage theImage) throws IOException {
        BufferedImage img = theImage;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", bos);

        byte[] imageBytes = bos.toByteArray();

        Base64 b64 = new Base64();

        String imageString = b64.encodeAsString(imageBytes);

        return imageString;

    }

}
