package com.rs.app.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public CompletableFuture<Collection<String>> upload(@NotNull MultipartFile[] multipartFiles) {
		return CompletableFuture.supplyAsync(() -> 
									Arrays.stream(multipartFiles)
									.filter(Objects::nonNull)
									.map(FileServiceImpl::uploadFile).collect(Collectors.toList()));
	}

	private static String uploadFile(@NotNull MultipartFile multipartFile) {
		String originalFileName = multipartFile.getOriginalFilename();
		File targetDir = new File("/tmp/uploads/");
		File parentDir = targetDir.getParentFile();
		System.out.println("ParentDir: " + parentDir);
		if(!targetDir.exists() ||(targetDir.exists() && !targetDir.isDirectory())) {
			System.out.println("targetDir not exists..");
			targetDir.mkdirs();
		}
		try (InputStream inStream = multipartFile.getInputStream();
				OutputStream outStream = new FileOutputStream(targetDir + File.separator + originalFileName)) {
			int bytesCopied = IOUtils.copy(inStream, outStream);
			System.out.println("Bytes Copied: " + bytesCopied);
		} catch (IOException io) {
			System.err.println("Error in uploading file. " + io.getMessage());
			io.printStackTrace();
		}
		return originalFileName;
	}

}
