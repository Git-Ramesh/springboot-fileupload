package com.rs.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = -8913904946690045875L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final PrintWriter out = resp.getWriter();
		ServletConfig config = null;
		resp.setContentType("text/html;charset='utf-8'");
		if (!ServletFileUpload.isMultipartContent(req)) {
			out.write("<h3 style='color: red;'>Error: Not a <i>multipart/form-data</i> request</h3>");
			out.flush();
			return;
		}
		config = getServletConfig();
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		diskFileItemFactory.setSizeThreshold(Integer.parseInt(config.getInitParameter("fileSizeThreshold")));
		System.out.println("Temp dir: " + System.getProperty("java.io.tmpdir"));
		// Sets the directory used to temporarily store files that are larger than the
		// configured size threshold.
		diskFileItemFactory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		// Sets the maximum allowed size of a single uploaded file
		servletFileUpload.setFileSizeMax(Long.parseLong(config.getInitParameter("maxFileSize")));
		// Sets the maximum allowed size of a complete request
		servletFileUpload.setSizeMax(Long.parseLong(config.getInitParameter("maxRequestSize")));
		File uploadDir = new File(config.getInitParameter("location"));
		if (!uploadDir.exists() || (uploadDir.exists() && !uploadDir.isDirectory())) {
			uploadDir.mkdirs();
		}
		try {
			// FileItem represents a file or form item that was received within a
			// multipart/form-data POST request.
			List<FileItem> fileItems = servletFileUpload.parseRequest(req);
			for (FileItem fileItem : fileItems) {
				// process only files
				if (!fileItem.isFormField()) {
					try {
						InputStream inStream = fileItem.getInputStream();
						OutputStream outStream = new FileOutputStream(new File(uploadDir, fileItem.getName()));
						IOUtils.copy(inStream, outStream);
						//fileItem.write(new File(uploadDir, fileItem.getName()));
						//It automatically forward the request to form page you don not need to do it explicitly.
						req.setAttribute("status", "File(s) uploaded.");
						if(!resp.isCommitted()) {
							getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
						}
						System.out.println(fileItem.getName() +" uploaded.");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
