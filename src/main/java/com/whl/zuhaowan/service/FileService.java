package com.whl.zuhaowan.service;


import com.whl.zuhaowan.entity.FileClusterStore;
import com.whl.zuhaowan.vo.resp.FileUploadVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.FileStore;
import java.util.ArrayList;
import java.util.List;

public interface FileService {
    List<FileUploadVO> uploadFile(MultipartFile[] multipartFile, int module, boolean isDir, String userId);

    void downloadFile(String fileId , HttpServletResponse response, HttpServletRequest request);

    String getFileUrl(String fileId);

    boolean delFile(List<String> fileIds);

    List<FileUploadVO>  uploadFileWithPath(MultipartFile[] multipartFile, String path, String userId);

    void downloadFileByPath(String pathName, HttpServletRequest request, HttpServletResponse response);

    boolean delFileWithPath(ArrayList<String> list);

    FileClusterStore addDir(int module, String userId);

    List<FileClusterStore> getFolderContent(String fileId);

    List<FileClusterStore> getFolderContentByPath(String path, String name);
}
