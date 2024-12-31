package com.dh.dhpan.service;

import com.dh.dhpan.model.entity.Files;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileService {
    //File getFile(int id);
    Files saveFileInf(MultipartFile file,String url);

    Files getFileByfilename(String filename);

    //获取文件列表
    List<Files> getAllFiles();

    boolean renameFile(String oldName, String newName);

    boolean deleteFile(String fileName);

    Files getFileAsResource(String fileName);

}
