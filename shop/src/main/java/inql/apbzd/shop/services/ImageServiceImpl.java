package inql.apbzd.shop.services;

import inql.apbzd.shop.domain.Subcategory;
import inql.apbzd.shop.repositories.SubcategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final SubcategoryRepository subcategoryRepository;

    public ImageServiceImpl(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long subcategoryId, MultipartFile file) {
        try{
            Optional<Subcategory> subcategoryOptional = subcategoryRepository.findById(subcategoryId);
            if(subcategoryOptional.isPresent()){
                Byte[] byteObjects = new Byte[file.getBytes().length];
                int i = 0;

                for (byte b :
                        file.getBytes()) {
                    byteObjects[i++]=b;
                }

                subcategoryOptional.get().setImage(byteObjects);
                subcategoryRepository.save(subcategoryOptional.get());

            }
            else{
                throw new IOException("Error occurred");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
