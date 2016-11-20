package pl.molasym.photoGrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.molasym.photoGrade.repository.PhotoRepository;
import pl.molasym.photoGrade.service.PhotoService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by moliq on 20.11.16.
 */
@Controller
public class ImageReadFile{

    @Autowired
    private PhotoService photoService;

    @RequestMapping(value="/data/{userId}/photos/{photoId}")
    public void readImage(@PathVariable String photoId,@PathVariable String userId, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext sc = request.getServletContext();

        byte[] imageBytes = photoService.getPhotoById(Long.valueOf(photoId)).getImage();

        File image = new File(userId+"__"+photoId);

        FileOutputStream outputStream = new FileOutputStream(image);
        outputStream.write(imageBytes);  //write the bytes and your done.


        if(image == null){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String contentType = sc.getMimeType(image.getName());
        response.reset();
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(image.length()));
        Files.copy(image.toPath(), response.getOutputStream());
    }

}
