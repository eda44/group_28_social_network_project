package ru.skillbox.config;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.noise.CurvedLineNoiseProducer;
import cn.apiclub.captcha.text.producer.DefaultTextProducer;
import cn.apiclub.captcha.text.renderer.DefaultWordRenderer;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.io.File;

@Component
public class CaptchaConfig {

    public static Captcha generateCaptcha(Integer width, Integer height) {
        return new Captcha.Builder(width, height).addBackground(new GradiatedBackgroundProducer())
                .addText(new DefaultTextProducer(), new DefaultWordRenderer()).addNoise(new CurvedLineNoiseProducer())
                .build();
    }

    public static File convertCaptchaToFile(Captcha captcha) {
        File outputFile = new File("image.jpg");
        try {
            ImageIO.write(captcha.getImage(), "jpg", outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputFile;
    }
}