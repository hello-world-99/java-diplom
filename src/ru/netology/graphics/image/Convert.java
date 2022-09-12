package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;

public class Convert implements TextGraphicsConverter {
     int maxWidth;
     int maxHeight;
     double maxPngRatio;
    int color;
    StringBuilder stringBuilder=new StringBuilder();
    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        /**
         * Конвертация изображения, переданного как урл, в текстовую графику.
         * @param url урл изображения
         * @return текст, представляющий собой текстовую графику переданного изображения
         * @throws IOException
         * @throws BadImageSizeException Если соотношение сторон изображения слишком большое
         */
        //url= URLEncoder.encode(url,"UTF-8");
        Color schema = new Color();
        setTextColorSchema(schema);
        BufferedImage img = ImageIO.read(new URL(url));

         stringBuilder=new StringBuilder();

        setMaxWidth(img.getWidth());
        setMaxHeight(img.getHeight());

        int newWidth = img.getWidth();
        int newHeight = img.getHeight();

        if (img.getHeight() > maxHeight || img.getWidth() > maxWidth) {
            double res = (double) newWidth / (double) newHeight;
            if (res > maxPngRatio) {
                throw new BadImageSizeException(res, maxPngRatio);
            }
            res = (double) newHeight / (double) newWidth;
            if (res > maxPngRatio) {
                throw new BadImageSizeException(res, maxPngRatio);
            }
            //throw new BadImageSizeException(img.getHeight()/img.getWidth(),maxPngRatio);
        }
        double scale =1;
        if (newHeight > maxHeight&&newHeight>=newWidth) {
             scale = img.getHeight() / maxHeight;
        }
          else if (newWidth > maxWidth&&newWidth>newHeight) {
             scale = img.getWidth() / maxWidth;
        }
        newWidth = (int) (newWidth / scale);
        newHeight = (int) (newHeight / scale);


        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_AREA_AVERAGING);
        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        WritableRaster bwRaster = bwImg.getRaster();


        for (int h = 0; h < newHeight; h++) {
            for (int w = 0; w < newWidth; w++) {
                 color = bwRaster.getPixel(w, h, new int[3])[0];


                char[] c = new char[] {schema.convert(color),schema.convert(color),schema.convert(color)};
                stringBuilder.append(c);

            }
            stringBuilder.append("\n");

        }

        return stringBuilder.toString();
    }

    @Override
    public void setMaxWidth(int width) {
        maxWidth = width;
    }

    @Override
    public void setMaxHeight(int height) {
        maxHeight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        maxPngRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {

    }
}



