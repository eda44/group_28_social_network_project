package ru.skillbox.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skillbox.model.PostFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryConfig {

    public static Cloudinary getCloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "diie0ma4r",
                "api_key", "952226954255419",
                "api_secret", "0fbUzyBNOPBcfhDyik77QetyjrQ",
                "secure", true));
    }

    public static PostFile uploadImage(MultipartFile file) {
        Cloudinary cloudinary = getCloudinary();
        Map upload = null;
        try {
            upload = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PostFile postFile = new PostFile();
        String url = (String) upload.get("url");
        postFile.setPath(url);
        postFile.setName(file.getOriginalFilename());
        return postFile;
    }
}
