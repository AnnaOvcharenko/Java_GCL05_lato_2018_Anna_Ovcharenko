package com.ovcharenko.Service;

import com.ovcharenko.DAO.PictureDAO;
import com.ovcharenko.Entity.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
@Service
public class PictureService {
    @Autowired
    private PictureDAO pictureDAO;

    public Collection<Picture> getAllPictures() {
        return this.pictureDAO.getAllPictures();
    }

    public String deletePictureById(int id){
        return this.pictureDAO.deletePictureById(id);
    }

    public byte[] showPictureById(int id) throws IOException {
        return this.pictureDAO.showPictureById(id);
    }

    public String uploadPicture( MultipartFile file) {
        return this.pictureDAO.uploadPicture(file);
    }

}
