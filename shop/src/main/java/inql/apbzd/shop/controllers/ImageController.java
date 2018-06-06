package inql.apbzd.shop.controllers;


import inql.apbzd.shop.commands.SubcategoryCommand;
import inql.apbzd.shop.domain.Subcategory;
import inql.apbzd.shop.services.ImageService;
import inql.apbzd.shop.services.SubcategoryService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {

    private final SubcategoryService subcategoryService;

    public ImageController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @GetMapping("subcategories/{id}/subcategoryimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException{
        SubcategoryCommand subcategoryCommand = subcategoryService.findCommandById(Long.valueOf(id));

        if(subcategoryCommand.getImage()!=null){
            byte[]byteArray = new byte[subcategoryCommand.getImage().length];
            int i =0;
            for(Byte wrappedByte : subcategoryCommand.getImage()){
                byteArray[i++] = wrappedByte;
            }

            response.setContentType("image/jpeg");
            InputStream inputStream = new ByteArrayInputStream(byteArray);
            IOUtils.copy(inputStream,response.getOutputStream());
        }
    }
}
