package com.rs.app.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.rs.app.service.FileService;

@Controller
public class FileController {
	@Autowired
	private FileService fileService;
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/fileUpload")
	public String uploadFile() {
		System.out.println("uploadFile...");
		return "upload";
	}

	@PostMapping("/fileUpload")
	public String uploadFile(@RequestParam("file") MultipartFile[] multipartFiles, ModelMap model) {
		System.out.println(Arrays.asList(multipartFiles));
		model.put("status", uploadFileToServer(multipartFiles));
		return "upload";
	}

	public String uploadFileToServer(@NotNull MultipartFile[] multipartFiles) {
		String status = "Files uploading failed";
		HttpHeaders headers = new HttpHeaders();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		Arrays.stream(multipartFiles)
				.filter(Objects::nonNull)
				.map(this::getByteArrayResource)
				.forEach(resource -> map.add("file", resource));
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		 HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
		ResponseEntity<String> responseEntity = this.restTemplate.exchange("http://localhost:4015/file/upload",
				HttpMethod.POST, requestEntity, String.class);
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			status = "Files uploaded successfully";
		}
		System.out.println(responseEntity.getBody());
		return status;
	}

	private ByteArrayResource getByteArrayResource(MultipartFile multipartFile) {
		ByteArrayResource byteArrayResource = null;
		try {
			byteArrayResource = new ByteArrayResource(multipartFile.getBytes()) {
				@Override
				public String getFilename() {
					return multipartFile.getOriginalFilename();
				}
			};
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byteArrayResource;
	}
}
