package com.dh.dhpan.service;

import com.dh.dhpan.DAO.FileRepository;
import com.dh.dhpan.model.entity.Files;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionIdListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class FileService implements IFileService {
    @Autowired
    private FileRepository fileUploadRepository;
    @Autowired
    private HttpSession httpSession;

    //上传文件,保存文件信息
    public Files saveFileInf(MultipartFile file,String url) {
        Files filesInf = new Files();
        filesInf.setFileName(file.getOriginalFilename());
        filesInf.setFileType(file.getContentType());
        filesInf.setFileSize(file.getSize());
        filesInf.setFileDate(new Date());
        filesInf.setUrl(url);
        filesInf.setDelete(false);
        filesInf.setUserid(httpSession.getId());
        return fileUploadRepository.save(filesInf);
    }
    //查询文件是否已经存在
    public Files getFileByfilename(String originalFilename) {
        return fileUploadRepository.getFilesByFileName(originalFilename);
    }

    //读取文件列表

    public List<Files> getAllFiles() {
        return  fileUploadRepository.findAll();
    }


    //修改文件名称
    @Override
    public boolean renameFile(String oldName, String newName) {
        return false;
    }

    //文件删除
    @Override
    public boolean deleteFile(String fileName) {
        return false;
    }

    @Override
    public Files getFileAsResource(String fileName) {
        return null;
    }

    //文件下载
}
