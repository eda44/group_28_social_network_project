package ru.skillbox.service;

import cn.apiclub.captcha.Captcha;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.skillbox.config.CaptchaConfig;
import ru.skillbox.config.CloudinaryConfig;
import ru.skillbox.model.CaptchaFile;
import ru.skillbox.repository.CaptchaFileRepository;
import ru.skillbox.response.CaptchaResponse;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CaptchaFileService {

    private final CaptchaFileRepository captchaFileRepository;
    private final CloudinaryConfig cloudinaryConfig;
    private static final Logger logger = LogManager.getLogger(CaptchaFileService.class);


    public Optional<CaptchaFile> getCaptchaFileByName(String name) {
        return captchaFileRepository.findByName(name);
    }
    public CaptchaResponse generateCaptchaResponse() {
        CaptchaResponse response = new CaptchaResponse();
        CaptchaFile captchaFile = makeCaptchaFile();
        response.setImage(captchaFile.getPath());
        response.setSecret(captchaFile.getName());
        return response;
    }

    private CaptchaFile makeCaptchaFile() {
        Captcha captcha = CaptchaConfig.generateCaptcha(120, 50);
        logger.info("uploading captcha file");
        CaptchaFile captchaFile = new CaptchaFile();
        byte[] data =  getBytesFrom(captcha.getImage());
        captchaFile.setPath(getCloudinaryUrl(data));
        captchaFile.setName(captcha.getAnswer());
        return captchaFileRepository.save(captchaFile);
    }

    private byte[] getBytesFrom(BufferedImage image){
        byte[] bytes;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            baos.flush();
            bytes = baos.toByteArray();
            baos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bytes;
    }

    private String getCloudinaryUrl(byte[] bytes) {
        String url = "";
        try {
            url = cloudinaryConfig
                    .getCloudinary()
                    .uploader()
                    .upload(bytes, new HashMap())
                    .get("url")
                    .toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }
}
