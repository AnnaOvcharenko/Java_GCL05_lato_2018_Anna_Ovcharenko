package com.ovcharenko.DAO;

import com.ovcharenko.Entity.Picture;
import com.ovcharenko.Exceptions.PictureNotFoundException;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Repository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class PictureDAO {
    private static TreeMap<Integer, Picture> pictures;


    public Collection<Picture> getAllPictures() {
        return pictures.values();
    }

    public String deletePictureById(int id) {

        if (!this.pictures.containsKey(id)) {
            return JSONResponse(false);
        }

        Picture pictureToDelete = pictures.get(id);

        Path filepath = Paths.get(getPictureFullPath(pictureToDelete.getName()).substring(1));

        try {
            Files.delete(filepath);
            this.pictures.remove(id);
        } catch (IOException e) {
            e.printStackTrace();
            return JSONResponse(false);
        }

        return JSONResponse(true);
    }

    public byte[] showPictureById(int id) throws IOException {

        if (!this.pictures.containsKey(id)) {
            throw new PictureNotFoundException("There is no picture whith such ID");
        }

        Picture pictureToShow = pictures.get(id);

        InputStream in = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(PictureDAO.getPicturePath(pictureToShow.getId()));

        return IOUtils.toByteArray(in);
    }

    public String uploadPicture( MultipartFile file) {
        try {
            String realPath = getGalleryFullPath();

            File transferFile = new File(realPath + "/" + file.getOriginalFilename());
            file.transferTo(transferFile);

            PictureDAO.addPicture(transferFile);

        } catch (Exception e) {

            e.printStackTrace();

            return JSONResponse(false);
        }

        return JSONResponse(true);
    }



    public static void fillPictureMap() {

        if (PictureDAO.pictures != null)
            return;

        PictureDAO.pictures = new TreeMap<>();

        File[] files = new File(
                Thread.currentThread().getContextClassLoader().getResource(Picture.getGalleryPath()).getPath()
        ).listFiles();

        if (files != null) {

            int id = 1;

            for (File file : files) {
                if (file.isFile()) {
                    PictureDAO.addPicture(id++, file);
                }
            }
        }

    }

    private static void addPicture(int id, File file) {
        BufferedImage bimg = null;

        try {
            bimg = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Path filepath = Paths.get(file.getPath());
        BasicFileAttributes attr = null;

        try {
            attr = Files.readAttributes(filepath, BasicFileAttributes.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        PictureDAO.pictures.put(
                id,
                new Picture(
                        id++,
                        file.getName(),
                        bimg.getWidth(),
                        bimg.getHeight(),
                        file.length(),
                        attr.creationTime().toMillis()
                ));

    }

    private static void addPicture(File file) {

        int id = PictureDAO.pictures.lastKey() + 1;
        PictureDAO.addPicture(id, file);

    }

    private static String getPictureFullPath(String name) {
        return PictureDAO.getGalleryFullPath() + "/" + name;
    }

    private static String getPicturePath(int id) {
        return Picture.getGalleryPath() + "/" + PictureDAO.pictures.get(id).getName();
    }

    private static String getGalleryFullPath() {
        return Thread.currentThread().getContextClassLoader().getResource(Picture.getGalleryPath()).getPath();
    }

    public static String JSONResponse(boolean result) {
        JSONObject r = new JSONObject();
        r.put("result", result);
        return r.toString();
    }


}
