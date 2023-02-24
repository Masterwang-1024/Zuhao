package com.whl.zuhaowan.controller;


import com.whl.zuhaowan.aop.annotation.MyLog;
import com.whl.zuhaowan.contants.Constant;
import com.whl.zuhaowan.exception.BusinessException;
import com.whl.zuhaowan.exception.code.BaseResponseCode;
import com.whl.zuhaowan.service.FileService;
import com.whl.zuhaowan.utils.DataResult;
import com.whl.zuhaowan.utils.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * @ModifyTime 2021/09/26
 * fyj
 **/
@RestController
@RequestMapping("/api/file")
@Api(tags = "系统管理-文件管理",description = "文件操作相关接口")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping(value ="/fileUpload",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ApiOperation(value="上传文件接口")
    @MyLog(title = "系统管理-文件管理",action = "上传文件接口")
    public DataResult uploadFile(@RequestParam(value = "file") MultipartFile [] multipartFile, @RequestParam int module, @RequestParam boolean isDir, HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        if( module == 0){
            throw new BusinessException(BaseResponseCode.METHOD_IDENTITY_ERROR);
        }
        String userId= JwtTokenUtil.getUserId(accessToken);
        DataResult result = DataResult.success(fileService.uploadFile(multipartFile,module,isDir,userId));
        return result;
    }

    @PostMapping("/upload")
    @ApiOperation(value="上传文件到指定路径接口")
    @MyLog(title = "系统管理-文件管理",action = "上传文件到指定路径接口")
    public DataResult uploadFileWithPath(@RequestParam(value = "file") MultipartFile [] multipartFile,@RequestParam String path, HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        String userId= JwtTokenUtil.getUserId(accessToken);
        DataResult result = DataResult.success(fileService.uploadFileWithPath(multipartFile,path,userId));
        return result;
    }


    @GetMapping("/download/file/{fileId}")
    @ApiOperation(value="下载文件接口")
    @MyLog(title = "系统管理-文件管理",action = "下载文件接口")
    public void downloadFile(@PathVariable String fileId, HttpServletRequest request, HttpServletResponse response){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        String userId= JwtTokenUtil.getUserId(accessToken);
        fileService.downloadFile(fileId,response,request);
    }

    @GetMapping("/download/{pahtName}")
    @ApiOperation(value="下载文件接口")
    @MyLog(title = "系统管理-文件管理",action = "下载文件接口")
    public void downloadFileByPath(@RequestParam String pathName,HttpServletRequest request, HttpServletResponse response){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        fileService.downloadFileByPath(pathName,request,response);
    }

    @PostMapping("/getUrl")
    @ApiOperation(value="获取文件链接接口")
    @MyLog(title = "系统管理-文件管理",action = "获取文件链接接口")
    public DataResult getFileUrl(@RequestParam String fileId, HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        DataResult result = DataResult.success(fileService.getFileUrl(fileId));
        return result;
    }

    @PostMapping("/delFile")
    @ApiOperation(value="删除文件目录接口")
    @MyLog(title = "系统管理-文件管理",action = "删除文件目录接口")
    public DataResult delFile(@PathVariable String fileId,HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        ArrayList<String> list = new ArrayList<>();
        list.add(fileId);
        DataResult result = DataResult.success(fileService.delFile(list));
        return result;
    }

    @PostMapping("/delFileWithPath")
    @ApiOperation(value="删除文件接口")
    @MyLog(title = "系统管理-文件管理",action = "删除文件接口")
    public DataResult delFileWithPath(@RequestParam String filePath,HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        ArrayList<String> list = new ArrayList<>();
        list.add(filePath);
        DataResult result = DataResult.success(fileService.delFileWithPath(list));
        return result;
    }



    @PostMapping("/addDir")
    @ApiOperation(value="添加文件目录接口")
    @MyLog(title = "系统管理-文件管理",action = "添加文件目录接口")
    public DataResult addDir(@RequestParam int module,HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        String userId= JwtTokenUtil.getUserId(accessToken);
        DataResult result = DataResult.success(fileService.addDir(module,userId));
        return result;
    }

    @PostMapping("/folder")
    @ApiOperation(value = "获取文件夹详情")
    @MyLog(title = "系统管理-文件管理",action = "获取文件夹详情")
    public DataResult folder(@RequestParam String fileId){
        DataResult result = DataResult.success(fileService.getFolderContent(fileId));
        return result;
    }

    @PostMapping("/folderByPath")
    @ApiOperation(value = "获取文件夹详情")
    @MyLog(title = "系统管理-文件管理",action = "获取文件夹详情")
    public DataResult folderByPath(@RequestParam String path,@RequestParam String name,HttpServletRequest request){
        DataResult result = DataResult.success(fileService.getFolderContentByPath(path,name));
        return result;
    }
}
