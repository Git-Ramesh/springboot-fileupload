package com.rs.app.service;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	CompletableFuture<Collection<String>> upload(MultipartFile[] multipartFiles);
}
