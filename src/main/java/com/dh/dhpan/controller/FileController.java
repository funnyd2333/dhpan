package com.dh.dhpan.controller;

import com.dh.dhpan.model.entity.Files;
import com.dh.dhpan.model.Resp.ResponseMessage;
import com.dh.dhpan.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:3000")
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;
    // 使用@Value注解注入配置文件中的属性
    @Value("${spring.servlet.multipart.location}")
    private String UPLOAD_DIR;

    //上传文件 /file/update  ,相同文件会覆盖掉，，uuid待解决
    @CrossOrigin
    @PostMapping("/upload")
    public ResponseMessage upload(@RequestParam("file") MultipartFile file) throws IOException {
        //io的File,文件存储的磁盘
        File uploadParentFile = new File(UPLOAD_DIR);
        //获取文件原始名称
        String originalFilename = file.getOriginalFilename();
        //判断目录是否存在，不存在创建
        if (!uploadParentFile.exists()) {

            uploadParentFile.mkdirs();
        }
        //文件上传，并记录文件url
        try{
            if (fileService.getFileByfilename(originalFilename)!=null ) {
                Files dbFiles = fileService.getFileByfilename(originalFilename);
                String url=dbFiles.getUrl();
            }else{
                // 上传文件到磁盘
                File uploadFile= null;
                if (originalFilename != null) {
                    uploadFile = new File(uploadParentFile,originalFilename);
                }
                if (uploadFile != null) {
                    file.transferTo(uploadFile);
                }
                //数据库若不存在重复文件，则不删除刚才上传的文件
                String url = "http://localhost:9092/file/" + originalFilename;
                fileService.saveFileInf(file,url);
            }
        } catch (IOException | IllegalStateException e) {
            throw new RuntimeException(e);
        }


        return ResponseMessage.success("上传成功");

    }


    //文件查询 /file/query
    @GetMapping("/all")
    public ResponseEntity<List<Files>> getAllFiles() {
        List<Files> files = fileService.getAllFiles();
        return ResponseEntity.ok(files);
    }
    

    //文件重命名 /file/delete
    @PostMapping("/rename/{oldName}/{newName}")
    public ResponseEntity<String> renameFile(@PathVariable String oldName, @PathVariable String newName) {
        // 假设 renameFile 方法用于重命名文件
        boolean success = fileService.renameFile(oldName, newName);
        if (success) {
            return ResponseEntity.ok("文件重命名成功");
        } else {
            return ResponseEntity.badRequest().body("文件重命名失败");
        }
    }
    //文件删除 /file/delete
    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        // 假设 deleteFile 方法用于删除文件
        boolean success = fileService.deleteFile(fileName);
        if (success) {
            return ResponseEntity.ok("文件删除成功");
        } else {
            return ResponseEntity.badRequest().body("文件删除失败");
        }
    }

    // 文件下载 文件下载
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Files> downloadFile(@PathVariable String fileName) {
        // 假设 getFileAsResource 方法用于获取文件资源
        Files resource = fileService.getFileAsResource(fileName);
        if (resource != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFileName() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
