package ru.skillbox.service;

import cn.apiclub.captcha.Captcha;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.skillbox.config.CaptchaConfig;
import ru.skillbox.config.CloudinaryConfig;
import ru.skillbox.model.CaptchaFile;
import ru.skillbox.repository.CaptchaFileRepository;
import ru.skillbox.response.CaptchaResponse;

import java.io.IOException;
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

    public CaptchaFile uploadCaptchaFile() {
        Captcha captcha = CaptchaConfig.generateCaptcha(120, 50);
        logger.info("uploading captcha file");
        CaptchaFile captchaFile = new CaptchaFile();
        try {
            captchaFile.setPath((String) cloudinaryConfig.getCloudinary().uploader().upload(CaptchaConfig
                    .convertCaptchaToFile(captcha), ObjectUtils.emptyMap()).get("url"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        captchaFile.setName(captcha.getAnswer());
        return captchaFileRepository.save(captchaFile);
    }

    public CaptchaResponse generateCaptchaResponse() {
        CaptchaResponse response = new CaptchaResponse();
        CaptchaFile captchaFile = uploadCaptchaFile();
        response.setImage(captchaFile.getPath());
        response.setSecret(captchaFile.getName());
        return response;
    }


}
