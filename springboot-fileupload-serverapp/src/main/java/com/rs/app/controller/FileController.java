package com.rs.app.controller;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rs.app.service.FileService;

@RestController
@RequestMapping("/file")
public class FileController {
	@Autowired
	private FileService fileService;

	@PostMapping("/upload")
	public Collection<String> upload(@RequestParam("file") MultipartFile[] multipartFiles)
			throws InterruptedException, ExecutionException {
		CompletableFuture<Collection<String>> futute = this.fileService.upload(multipartFiles);
		System.out.println("Doing other operations...");
		return futute.get();
	}
}
