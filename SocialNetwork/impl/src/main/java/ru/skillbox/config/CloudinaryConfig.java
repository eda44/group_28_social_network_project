package ru.skillbox.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Component
public class CloudinaryConfig {


    public static String uploadImage(String path) {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "diie0ma4r",
                "api_key", "952226954255419",
                "api_secret", "0fbUzyBNOPBcfhDyik77QetyjrQ",
                "secure", true));

        File file = new File(path);
        Map upload = null;
        try {
            upload = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return upload.get("url").toString();
    }
}