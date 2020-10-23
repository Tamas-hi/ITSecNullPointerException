package com.nullpointerexception;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class ParserJNI {
    static {
        System.loadLibrary("parser");
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/Users/norbert/1.caff");
        try {
            byte[] data = Files.readAllBytes(path);
            CaffData result = new ParserJNI().readData(data);
            System.out.println("---Java Main--");
            System.out.println(result.caption);
            System.out.println(result.creator_name);
            System.out.println(result.image_width);
            System.out.println(result.image_height);
            System.out.println(result.pixels.length);
            System.out.println(result.tags.length);
            System.out.println(Arrays.toString(result.tags));

            BufferedImage image = new BufferedImage((int)result.image_width, (int)result.image_height, BufferedImage.TYPE_INT_RGB);
            int[] rgb = new int[result.pixels.length / 3];

            int c = 0;
            for(int i = 0; i < result.pixels.length; i+=3) {
                int k = result.pixels[i];
                k = (k << 8) + result.pixels[i + 1];
                k = (k << 8) + result.pixels[i + 2];
                rgb[c] = k;
                c++;
            }

            int z = 0;
            for (int y = 0; y < (int)result.image_height; y++) {
                for (int x = 0; x < (int)result.image_width; x++) {
                    image.setRGB(x, y, rgb[z]);
                    z++;
                }
            }

            File outputFile = new File("output.bmp");
            ImageIO.write(image, "bmp", outputFile);

        } catch (IOException ioException) {
            System.out.println(ioException.getLocalizedMessage());
        }
    }

    private native CaffData readData(byte[] file);
}
