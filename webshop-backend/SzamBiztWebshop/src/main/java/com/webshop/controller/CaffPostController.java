package com.webshop.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webshop.model.CaffPost;
import com.webshop.repository.UserRepository;
import com.webshop.service.CaffPostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.imageio.ImageIO;

@RestController
@RequestMapping("/api")
public class CaffPostController {

    CaffPostServiceImpl caffPostServiceImpl;

    UserRepository userRepository;

    @Autowired
    public void setCaffPostService(CaffPostServiceImpl caffPostServiceImpl) {
        this.caffPostServiceImpl = caffPostServiceImpl;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/search")
    public ResponseEntity<List<CaffPost>> searchCaffByTitle(@RequestParam String title) {
        return new ResponseEntity<>(caffPostServiceImpl.findCaffByTitle(title), HttpStatus.OK);
    }

    @RequestMapping("/getAll")
    public ResponseEntity<List<CaffPost>> getAllCaff() {
        return new ResponseEntity<>(caffPostServiceImpl.getPosts(), HttpStatus.OK);
    }

    @RequestMapping("/get/{id}")
    public ResponseEntity<CaffPost> findCaffById(@PathVariable long id) {
        return new ResponseEntity<>(caffPostServiceImpl.findCaffById(id), HttpStatus.OK);
    }

    @RequestMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCaffById(@PathVariable long id) {
        caffPostServiceImpl.deleteCaffById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/upload/{userId}", method = RequestMethod.POST)
    public ResponseEntity<Long> uploadCaff(
            @PathVariable long userId,
            @RequestBody byte[] uploadedByteArray
    ) throws IOException {
        CaffData result = readData(uploadedByteArray);

        BufferedImage image = new BufferedImage(
                (int) result.image_width,
                (int) result.image_height,
                BufferedImage.TYPE_INT_RGB
        );

        int z = 0;
        for (int y = 0; y < (int) result.image_height; y++) {
            for (int x = 0; x < (int) result.image_width; x++) {
                image.setRGB(x, y, result.pixels[z]);
                z++;
            }
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "bmp", baos);
        byte[] bytes = baos.toByteArray();

        CaffPost caffPost = new CaffPost();
        caffPost.setContent(bytes);
        caffPost.setPosted(new Date());
        caffPost.setUser(userRepository.findById(userId).get());
        caffPost.setCaption(result.caption);
        caffPost.setTags(result.tags);

        CaffPost savedCaffPost = caffPostServiceImpl.uploadCaff(caffPost);

        return new ResponseEntity<>(savedCaffPost.getId(), HttpStatus.OK);
    }

    @RequestMapping(value = "/upload-details/{caffPostId}", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> uploadCaff(
            @PathVariable long caffPostId,
            @RequestBody String title
    ) {
        CaffPost editableCaffPost = caffPostServiceImpl.findCaffById(caffPostId);
        editableCaffPost.setTitle(title);
        caffPostServiceImpl.updateTitle(editableCaffPost);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private native CaffData readData(byte[] file);
}
