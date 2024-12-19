package com.example.controller;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Images;
import com.example.repo.ImageRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class ImageController {

	@Autowired
	private ImageRepository repo;
	
	@PostMapping("/add")
	public Images postImage(@RequestParam("file") MultipartFile file) throws IOException {
		Images image = new Images();
		//converting that Multipart(I) file to bytes
		image.setImage(file.getBytes());
		return repo.save(image);
	}
	
	//get individual images
	@GetMapping(value ="/get/{id}", produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImageId(@PathVariable int id) {
		Optional<Images> img = repo.findById(id);
		if(img.isPresent()) {
			Images image = img.get();
			return image.getImage();
		}
		return null;
	
	}
	
	@GetMapping(value = "getAll", produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
	public List<byte[]> getAllImages(){
	List<Images> list =	repo.findAll();
	return list.stream()
			   .map(Images::getImage)
			   .collect(Collectors.toList());
	}
	
	
}
