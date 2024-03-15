package com.identity.detection.controller;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.identity.detection.dto.Product;
import com.identity.detection.service.IProductService;
@RestController
public class ProductController 
{
	private static final org.apache.commons.logging.Log log = LogFactory.getLog(ProductController.class);
	@Autowired
	private IProductService productService;
	//mapping the getProduct() method to /product
	@GetMapping(value = "/product")
	public List<Product> getProduct() 
	{
		//finds all the products
		List<Product> products = productService.findAll();
		//returns the product list
		return products;
	}
	
	
	@GetMapping(value = "/ping")
	public ResponseEntity<Long> getPing() 
	{
		return new ResponseEntity<Long>(System.currentTimeMillis(),HttpStatus.OK);
	}
	

	 @PostMapping("/voice/analyze")
			public ResponseEntity<Object> handleFileUpload(@RequestParam("file") MultipartFile file,
					RedirectAttributes redirectAttributes) {
		 log.info("Inside handleFileUpload ");
				redirectAttributes.addFlashAttribute("message",
						"You successfully uploaded " + file.getOriginalFilename() + "!");
				
				HashMap<String,Object> m = new HashMap<String,Object>();
				HashMap<String,Object> analysisMap = new HashMap<String,Object>();
				
				HashMap<String,Object> confidenceScoreMap = new HashMap<String,Object>();
				
				HashMap<String,Object> additionalInfoMap = new HashMap<String,Object>();
					m.put("satus","success");
					
					analysisMap.put("detectedVoice",true);
					analysisMap.put("voiceType","human");
					m.put("analysis", analysisMap);
					
					confidenceScoreMap.put("aiProbability", 5);
					confidenceScoreMap.put("humanProbability", 95);
					
					m.put("confidenceScore", confidenceScoreMap);
					
					
					additionalInfoMap.put("emotionalTone", "netural");
					additionalInfoMap.put("backgroundNoiseLevel", "low");
					m.put("additionalInfo", additionalInfoMap);
					
				return new ResponseEntity<Object>(m,HttpStatus.OK);
			}
	 
}
