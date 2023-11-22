package me.zap.captchagenerator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class CaptchaGenerator {
    private static String generateCaptchaText(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captchaText = new StringBuilder(length);
        Random rnd = new Random();
        for (int i = 0; i < length; i++) {
            captchaText.append(characters.charAt(rnd.nextInt(characters.length())));
        }
        return captchaText.toString();
    }

    private static void createCaptchaImage(String captchaText) throws IOException {
        int width = 200, height = 100;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        Font font = new Font("Arial", Font.BOLD, 40);
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.BLACK);
        g2d.drawString(captchaText, 20, 70);
        g2d.dispose();

        File outputFile = new File("captcha.png");
        ImageIO.write(bufferedImage, "png", outputFile);
    }

    public static void main(String[] args) {
        try {
            String captchaText = generateCaptchaText(6);
            createCaptchaImage(captchaText);
            System.out.println("Captcha Generated: " + captchaText);
        } catch (IOException e) {
            System.out.println("Error generating CAPTCHA: " + e.getMessage());
        }
    }
}