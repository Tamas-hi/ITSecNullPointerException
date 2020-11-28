package com.webshop.controller;

import com.webshop.model.CaffPost;
import com.webshop.repository.UserRepository;
import com.webshop.service.CaffPostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
	public ResponseEntity<CaffPost> searchCaffByTitle(@RequestParam String title) {
	    return new ResponseEntity<>(caffPostServiceImpl.findCaffByTitle(title), HttpStatus.OK);
	}


	@RequestMapping("/getAll")
	public ResponseEntity<List<CaffPost>> getAllCaff(){
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
	
	@RequestMapping("/upload")
	public ResponseEntity<HttpStatus> uploadCaff() {
		// path-t valahogy meg kell kapni a user-től amikor betallózza a fájlt
		Path path = Paths.get("C:\\Users\\Tomi\\1.caff");
        try {
            byte[] data = Files.readAllBytes(path);
            CaffData result = readData(data);
            /*System.out.println("---Java Main--");
            System.out.println(result.caption);
            System.out.println(result.creator_name);
            System.out.println(result.image_width);
            System.out.println(result.image_height);
            System.out.println(result.pixels.length);
            System.out.println(result.tags.length);
            System.out.println(Arrays.toString(result.tags));*/
            
            BufferedImage image = new BufferedImage((int)result.image_width, (int)result.image_height, BufferedImage.TYPE_INT_RGB);

            int z = 0;
            for (int y = 0; y < (int)result.image_height; y++) {
                for (int x = 0; x < (int)result.image_width; x++) {
                    image.setRGB(x, y, result.pixels[z]);
                    z++;
                }
            }
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            File outputFile = new File("output.bmp");
            ImageIO.write(image, "bmp", baos);
            byte[] bytes = baos.toByteArray();
            
            String title = "Test";
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date posted = new Date();
            
            // ezeket frontendről kapom meg, RequestBody kell és onnan jön
            CaffPost caffPost = new CaffPost();
            caffPost.setTitle(title);
            caffPost.setContent(bytes);
            caffPost.setPosted(posted);
            caffPost.setUser(userRepository.findFirstByEmail("asd@asd.com"));
            caffPost.setCreator_name(result.creator_name);
            caffPost.setCaption(result.caption);
            caffPost.setTags(result.tags);
            
            caffPostServiceImpl.uploadCaff(caffPost);

        } catch (IOException ioException) {
            System.out.println(ioException.getLocalizedMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
	}
	
	private native CaffData readData(byte[] file);
	
}
