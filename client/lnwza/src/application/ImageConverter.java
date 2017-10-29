package application;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Base64;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author SE-lnwza
 */
public class ImageConverter {

    public static String toByte(String imgLocation) {
        String base64 = "";
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedImage img = ImageIO.read(new File(imgLocation));
            ImageIO.write(img, "jpg", baos);
            baos.flush();

            base64 = new String(Base64.getEncoder().encode(baos.toByteArray()));
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return base64;
    }
    
    public static Image toImage(String base64) {
        BufferedImage buffImg = null;
        try {
            byte[] byteArr = Base64.getDecoder().decode(base64);
            buffImg = ImageIO.read(new ByteArrayInputStream(byteArr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SwingFXUtils.toFXImage(buffImg, null);
    }

}
