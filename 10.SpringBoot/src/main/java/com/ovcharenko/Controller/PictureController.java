package com.ovcharenko.Controller;

import com.ovcharenko.DAO.PictureDAO;
import com.ovcharenko.Entity.Picture;
import com.ovcharenko.Properties.GlobalProperties;
import com.ovcharenko.Service.PictureService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

@RestController
//@RequestMapping("/pictures")
public class PictureController implements InitializingBean {

    @Autowired
    private PictureService pictureService;
    @Autowired
    private GlobalProperties globalProperties;

    @RequestMapping(value="/pictures",method = RequestMethod.GET)
    public Collection<Picture> getAllPictures() {
        return this.pictureService.getAllPictures();
    }

    @RequestMapping(value = "/pictures/{id}", method = RequestMethod.DELETE)

    public String deletePictureById(@PathVariable("id") int id) throws IOException {
        return this.pictureService.deletePictureById(id);
    }

    @RequestMapping(value = "/pictures/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] showPictureById(@PathVariable("id") int id) throws IOException {
        return this.pictureService.showPictureById(id);
    }

    @RequestMapping(value = "/pictures/upload", method = RequestMethod.POST, consumes = {"multipart/form-data"}
    )
    public String uploadPicture(@RequestParam("file") MultipartFile file) {
        return this.pictureService.uploadPicture(file);
    }


    @Override
    public void afterPropertiesSet() {
        Picture.setGalleryPath(globalProperties.getGalleryPath());
        PictureDAO.fillPictureMap();
    }

}
