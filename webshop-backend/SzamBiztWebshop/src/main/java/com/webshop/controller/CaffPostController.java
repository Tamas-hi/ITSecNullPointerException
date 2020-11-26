package com.webshop.controller;

import com.webshop.model.CaffPost;
import com.webshop.service.CaffPostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

@RestController
@RequestMapping("/caffposts")
public class CaffPostController {

	CaffPostServiceImpl caffPostServiceImpl;
	
	@Autowired
	public void setCaffPostService(CaffPostServiceImpl caffPostServiceImpl) {
		this.caffPostServiceImpl = caffPostServiceImpl;
	}

	@RequestMapping("/all")
	public List<CaffPost> getAllCaff(){
		return caffPostServiceImpl.getPosts();
	}
	
	@RequestMapping("/get/{id}")
	public CaffPost findCaffById(@PathVariable long id) {
		return caffPostServiceImpl.findCaffById(id);
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteCaffById(@PathVariable long id) {
		return "deleteee";
	}
	
	@RequestMapping("/upload/{id}")
	public String uploadCaffById(@PathVariable long id) {
		String s = "empty";
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
            
            s = caffPostServiceImpl.uploadCaff(id);

            File outputFile = new File("output.bmp");
            ImageIO.write(image, "bmp", outputFile);

        } catch (IOException ioException) {
            System.out.println(ioException.getLocalizedMessage());
        }
		return s;
	}
	
	private native CaffData readData(byte[] file);
	
}
