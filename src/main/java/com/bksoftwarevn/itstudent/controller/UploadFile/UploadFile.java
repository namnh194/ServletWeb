package com.bksoftwarevn.itstudent.controller.UploadFile;

import com.bksoftwarevn.itstudent.model.JsonResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.concurrent.Executor;

@WebServlet(name = "UploadFile", value="/upload-file/*")
@MultipartConfig(fileSizeThreshold = 1024*1024*2, maxRequestSize = 1024*1024*50, maxFileSize = 1024*1024*50)
//fileSizeThreshold nếu kích thước của file upload lên lớn hơn định nghĩa thì hệ thống tự ghi file vào trực tiếp ổ cứng
//ko thông qua cache
//maxRequestSize: kích thước tối đa của 1 request
//maxFileSize: kích thước tối của 1 file

public class UploadFile extends HttpServlet {

    private JsonResult jsonResult = new JsonResult();

    final static private String SAVE_DIRECTORY = "file-upload";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rs = "";
        //chứa các file mình upload lên
        try {
            Collection<Part> partConnection = request.getParts();
            List<String> list = new ArrayList<>();
            Long time = new Date().getTime();
            for (Part part: partConnection) {
                String fileName = getFileName(part);
                if(fileName.length() > 0) {
                    String filePath = getFolderUpload(time).getAbsolutePath() + File.separator + fileName;
                    System.out.println("Write file: "+filePath);
                    part.write(filePath);
                    list.add(SAVE_DIRECTORY+"/"+time+"\\"+fileName);
                }
            }
            rs = jsonResult.jsonSuccess(list);
        } catch (Exception e) {
            e.printStackTrace();
            rs = jsonResult.jsonFail("Upload file");
        }
        response.getWriter().write(rs);
    }

    private File getFolderUpload(Long time) {
        String appPath = "F:\\Sever\\apache-tomee-plume-8.0.3\\webapps\\" + SAVE_DIRECTORY + "\\" + time;
        File folderUpload = new File(appPath);
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }

    private String getFileName(Part part) {
        String fileNameRs = "";
        //thuộc tính header của một object part tương ứng với key contetn-disposition thì sẽ chứa 1 chuỗi
        //có định dạng tương tự
        //form-data; name="file"; filename="C:\file1.zip
        //từ chuỗi trên lấy ra tên + phần mở rộng của file
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s: items) {
            if (s.trim().startsWith("filename")) {
                fileNameRs = s.substring(s.indexOf("=") + 2, s.length()-1);
                fileNameRs = fileNameRs.replace("\\","/");
                int i = fileNameRs.lastIndexOf("/");
                fileNameRs = fileNameRs.substring(i+1);
            }
        }
        return fileNameRs;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
