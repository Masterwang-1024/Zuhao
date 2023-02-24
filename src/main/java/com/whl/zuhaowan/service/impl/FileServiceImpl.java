package com.whl.zuhaowan.service.impl;

import com.whl.zuhaowan.entity.FileClusterStore;
import com.whl.zuhaowan.exception.BusinessException;
import com.whl.zuhaowan.exception.code.BaseResponseCode;
import com.whl.zuhaowan.mapper.FileClusterStoreMapper;
import com.whl.zuhaowan.service.FileService;
import com.whl.zuhaowan.utils.MinioClientUtils;
import com.whl.zuhaowan.vo.resp.FileUploadVO;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.FileStore;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Slf4j
@Service
public class FileServiceImpl implements FileService {
    @Resource
    private MinioClientUtils minioClientUtils;

    @Value("${minio.bucketName}")
    private String bucket;

    @Resource
    private FileClusterStoreMapper fileClusterStoreMapper;



    @Override
    public List<FileUploadVO> uploadFile(MultipartFile[] multipartFile, int module, boolean isDir, String userId) {
        ArrayList<FileUploadVO> res = new ArrayList<>();

        int time = (int) (System.currentTimeMillis() / 1000);
        String path="/user/"+userId+"/"+time;
        try {

            for (MultipartFile mf:multipartFile){
                FileClusterStore input = new FileClusterStore();
                FileUploadVO file = new FileUploadVO();
//                String fileName = URLDecoder.decode(mf.getOriginalFilename(), "UTF-8");
                String fileName = mf.getOriginalFilename();
                String upPath = path+"/"+fileName;
                minioClientUtils.putObject(bucket,mf,upPath);
                file.setBucket(bucket);
                file.setFileName(fileName.replace("/", ""));
                file.setPath(path);

                if(!isDir) {
                    BeanUtils.copyProperties(file,input);
                    input.setFilePath(path);
                    input.setCreateId(userId);
                    input.setId(UUID.randomUUID().toString());
                    input.setCreateTime(new Date());
                    input.setBusinessModule(module);
                    input.setIsDir(0);
                    int i = fileClusterStoreMapper.insertSelective(input);
                    if(i!=1){
                        throw new BusinessException(BaseResponseCode.DATA_ERROR);
                    }
                    file.setId(input.getId());
                    file.setIsDir(0);
                }

                res.add(file);
            }
            if(isDir){
                FileClusterStore input = new FileClusterStore();
                input.setBucket(bucket);
                input.setFilePath(path);
                input.setIsDir(1);
                input.setCreateId(userId);
                input.setId(UUID.randomUUID().toString());
                input.setCreateTime(new Date());
                input.setBusinessModule(module);
                int i = fileClusterStoreMapper.insertSelective(input);
                if(i!=1){
                    throw new BusinessException(BaseResponseCode.DATA_ERROR);
                }
                res.forEach(obj->{
                    obj.setId(input.getId());
                    obj.setIsDir(1);
                });
            }

        } catch (Exception e) {
            log.error("uploadFile error:{}",e);
        }
        return res;
    }

    @Override
    public void downloadFile(String fileId, HttpServletResponse response, HttpServletRequest request) {
        FileClusterStore file = fileClusterStoreMapper.selectByPrimaryKey(fileId);
        if(null == file){
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        ServletOutputStream out = null;
        InputStream content = null;
        try {
            String agent = request.getHeader("user-agent");
            String name = file.getFileName();
            if (agent.contains("FireFox")) {
                name = new String(name.getBytes("UTF-8"), "iso-8859-1");
            } else {
                name = URLEncoder.encode(name, "UTF-8");
            }
            response.setContentType(request.getServletContext().getMimeType(name));
            response.setHeader("Content-disposition", "attachment; filename=" + name);
            out = response.getOutputStream();
            content = minioClientUtils.getObject(file.getBucket(), file.getFilePath()+"/"+file.getFileName());
            int len = 0;
            byte[] buffer = new byte[1024*1024];
            while ((len = content.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            log.error("downloadFile error:{}",e);
        }finally {
            try {
                if(null != out){
                    out.close();
                }
                if(null != content){
                    content.close();
                }
            } catch (IOException e) {
                log.error("downloadFile IO error:{}",e);
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getFileUrl(String fileId) {
        FileClusterStore fileInfo = fileClusterStoreMapper.selectByPrimaryKey(fileId);
        if(null == fileInfo){
            return null;
        }
        String objectUrl=null;
        try {
            objectUrl = minioClientUtils.getObjectUrl(fileInfo.getBucket(), fileInfo.getFilePath()+"/"+fileInfo.getFileName());
        } catch (Exception e) {
            log.error("****getFileUrl error:{}",e);
        }
        return objectUrl;
    }

    @Override
    public boolean delFile(List<String> fileIds) {
        log.info("批量删除文件，入参：{}",fileIds.toString());
        boolean res =false;
        if(fileIds.size()<=0){
            return res;
        }
        List<FileClusterStore> fileList = fileClusterStoreMapper.getListByIds(fileIds);
        if(null == fileList || fileList.size()<=0){
            return res;
        }
        List<DeleteObject> objectNames = new ArrayList<>();

        String bucketName = fileList.get(0).getBucket();
        fileList.forEach(fileInfo->{
            DeleteObject del = new DeleteObject(fileInfo.getFilePath()+"/"+fileInfo.getFileName());
            objectNames.add(del);
        });
        try {
            List<String> errors = minioClientUtils.removeObjects(bucketName,objectNames);
            if(errors.isEmpty()){
                res = true;
            }
        } catch (Exception e) {
            log.error("****delFile error:{}",e);
        }
        return res;
    }

    @Override
    public List<FileUploadVO> uploadFileWithPath(MultipartFile[] multipartFile, String path, String userId) {
        ArrayList<FileUploadVO> res = new ArrayList<>();
        try {
            for (MultipartFile mf:multipartFile){
                FileClusterStore input = new FileClusterStore();
                FileUploadVO file = new FileUploadVO();
                String fileName = mf.getOriginalFilename();
                String upPath = path;
                minioClientUtils.putObject(bucket,mf,upPath);
                file.setBucket(bucket);
                file.setFileName(fileName.replace("/", ""));
                file.setPath(path);
                file.setId(input.getId());
                file.setIsDir(0);
                res.add(file);
            }
        }catch (Exception e){
            log.error("uploadFileWithPath error:{}",e);
        }
        return res;
    }

    @Override
    public void downloadFileByPath(String pathName, HttpServletRequest request, HttpServletResponse response) {
        ServletOutputStream out = null;
        InputStream content = null;
        String[] split = pathName.split("/");
        String name = split[split.length - 1];
        String path = pathName.substring(0, pathName.lastIndexOf("/"));
        try {
            String agent = request.getHeader("user-agent");
            if (agent.contains("FireFox")) {
                name = new String(name.getBytes("UTF-8"), "iso-8859-1");
            } else {
                name = URLEncoder.encode(name, "UTF-8");
            }
            response.setContentType(request.getServletContext().getMimeType(name));
            response.setHeader("Content-disposition", "attachment; filename=" + name);
            out = response.getOutputStream();
            content = minioClientUtils.getObject(bucket, path+"/"+name);
            int len = 0;
            byte[] buffer = new byte[1024*1024];
            while ((len = content.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            log.error("downloadFile error:{}",e);
        }finally {
            try {
                if(null != out){
                    out.close();
                }
                if(null != content){
                    content.close();
                }
            } catch (IOException e) {
                log.error("downloadFile IO error:{}",e);
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean delFileWithPath(ArrayList<String> list) {
        boolean res =false;
        if(list.size()<=0){
            return res;
        }
        List<DeleteObject> objectNames = new ArrayList<>();
        list.forEach(item->{
            DeleteObject del = new DeleteObject(item);
            objectNames.add(del);
        });
        try {
            List<String> errors = minioClientUtils.removeObjects(bucket,objectNames);
            if(errors.isEmpty()){
                res = true;
            }
        } catch (Exception e) {
            log.error("****delFile error:{}",e);
        }
        return res;
    }

    @Override
    public FileClusterStore addDir(int module, String userId) {
        int time = (int) (System.currentTimeMillis() / 1000);
        String path="/user/"+userId+"/"+time;
        FileClusterStore input = new FileClusterStore();
        input.setBucket(bucket);
        input.setFilePath(path);
        input.setIsDir(1);
        input.setCreateId(userId);
        input.setId(UUID.randomUUID().toString());
        input.setCreateTime(new Date());
        input.setBusinessModule(module);
        boolean b = false;
        try {
            b = minioClientUtils.makeBucket("shop/123");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidResponseException e) {
            e.printStackTrace();
        } catch (RegionConflictException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (XmlParserException e) {
            e.printStackTrace();
        } catch (InvalidBucketNameException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        }
        if (b) {
            int i = fileClusterStoreMapper.insertSelective(input);
            if (i != 1) {
                throw new BusinessException(BaseResponseCode.DATA_ERROR);
            }
        }
        return input;
    }

    @Override
    public List<FileClusterStore> getFolderContent(String fileId) {
        FileClusterStore fileStore = fileClusterStoreMapper.selectByPrimaryKey(fileId);
        List<FileClusterStore> list = new ArrayList<>();
        try {
            Iterable<Result<Item>> results = minioClientUtils.listObjects(bucket,fileStore.getFilePath()+"/");
            Iterator<Result<Item>> iterator = results.iterator();
            while (iterator.hasNext()){
                FileClusterStore fileStore1 = new FileClusterStore();
                Result<Item> next = iterator.next();
//                fileStore1.setSize(next.get().size());
                fileStore1.setIsDir(next.get().isDir()?1:0);
                if (next.get().isDir()){
                    String b = next.get().objectName();
//                    StringBuffer a = new StringBuffer(b);
                    String s = b.substring(0, b.length() - 1);
                    fileStore1.setFilePath(s.substring(0,s.lastIndexOf("/")));
                    fileStore1.setBucket(bucket);
                    fileStore1.setId(UUID.randomUUID().toString());
                    fileStore1.setFileName(s.substring(s.lastIndexOf("/")+1));
                    list.add(fileStore1);
                }else{
                    String s = next.get().objectName();
                    fileStore1.setFilePath(s.substring(0,s.lastIndexOf("/")));
                    fileStore1.setBucket(bucket);
                    fileStore1.setId(UUID.randomUUID().toString());
                    fileStore1.setFileName(s.substring(s.lastIndexOf("/")+1));
                    list.add(fileStore1);
                }

            }

        }catch (Exception e){
            log.error("err");
        }finally {

        }


        return list;
    }

    @Override
    public List<FileClusterStore> getFolderContentByPath(String path, String name) {
        List<FileClusterStore> list = new ArrayList<>();
        try {
            Iterable<Result<Item>> results = minioClientUtils.listObjects(bucket,path+"/"+name+"/");
            Iterator<Result<Item>> iterator = results.iterator();
            while (iterator.hasNext()){
                FileClusterStore fileStore1 = new FileClusterStore();
                Result<Item> next = iterator.next();
                System.out.println(next.get().objectName());
//                fileStore1.setSize(next.get().size());
                fileStore1.setIsDir(next.get().isDir()?1:0);
                if (next.get().isDir()){
                    String b = next.get().objectName();
                    String s = b.substring(0, b.length() - 1);
                    fileStore1.setFilePath(s.substring(0,s.lastIndexOf("/")));
                    fileStore1.setBucket(bucket);
                    fileStore1.setId(UUID.randomUUID().toString());
                    fileStore1.setFileName(s.substring(s.lastIndexOf("/")+1));
                    list.add(fileStore1);
                }else{
                    String s = next.get().objectName();
                    fileStore1.setFilePath(s.substring(0,s.lastIndexOf("/")));
                    fileStore1.setBucket(bucket);
                    fileStore1.setId(UUID.randomUUID().toString());
                    fileStore1.setFileName(s.substring(s.lastIndexOf("/")+1));
                    list.add(fileStore1);
                }
            }

        }catch (Exception e){
            log.error("err");
        }finally {

        }

        return list;
    }
}
