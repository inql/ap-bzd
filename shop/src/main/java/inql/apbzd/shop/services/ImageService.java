package inql.apbzd.shop.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void saveImageFile(Long subcategoryId, MultipartFile file);

}
