package org.zhaoxuan.common.utils;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;
import java.util.Random;

@Slf4j
@SuppressWarnings("unused")
public class ImageUtils implements Serializable {

    private final static Random RANDOM = new Random();
    private final static String RAND_STRING = "0123456789AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
    private final static int WIDTH = 95;
    private static final int HEIGHT = 25;
    private final static int LINE_SIZE = 40;
    private final static int STRING_NUM = 4;
    private final static int COLOR_MAX = 255;
    private final static String BASE64_PREFIX = "data:image/JPEG;base64,";
    private final static String FONT_FIXEDSYS = "Fixedsys";
    private final static String IMAGE_TYPE_JPEG = "JPEG";

    private static Font getFont() {
        return new Font(FONT_FIXEDSYS, Font.BOLD, 18);
    }

    private static Color getRandColor(int frontColor, int backgroundColor) {

        frontColor = Math.min(frontColor, COLOR_MAX);
        backgroundColor = Math.min(backgroundColor, COLOR_MAX);

        int r = frontColor + RANDOM.nextInt(backgroundColor - frontColor - 16);
        int g = frontColor + RANDOM.nextInt(backgroundColor - frontColor - 14);
        int b = frontColor + RANDOM.nextInt(backgroundColor - frontColor - 18);

        return new Color(r, g, b);

    }

    public static String getVerifyCodeImage(String randStr) {

        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);

        Graphics g = image.getGraphics();
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        g.setColor(getRandColor(110, 133));

        for (int lineNo = 0; lineNo <= LINE_SIZE; lineNo++) {
            drawLine(g);
        }

        drawString(g, randStr);

        g.dispose();

        return getBase64ImageInfo(image);

    }

    private static String getBase64ImageInfo(BufferedImage codeImg) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(codeImg, IMAGE_TYPE_JPEG, out);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        String imgString = Base64.getEncoder().encodeToString(out.toByteArray());
        return BASE64_PREFIX + imgString;
    }

    private static void drawString(Graphics g, String randStr) {

        g.setFont(getFont());
        g.setColor(new Color(RANDOM.nextInt(101),
                RANDOM.nextInt(111),
                RANDOM.nextInt(121)));

        for (int index = 0; index < randStr.length(); index++) {
            char ch = randStr.charAt(index);
            g.drawString(String.valueOf(ch), 13 * index, 16);

        }

    }

    private static void drawLine(Graphics g) {
        int x = RANDOM.nextInt(WIDTH);
        int y = RANDOM.nextInt(HEIGHT);
        int xl = RANDOM.nextInt(13);
        int yl = RANDOM.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    public static String getRandomString(int num) {
        return String.valueOf(RAND_STRING.charAt(num));
    }

}
