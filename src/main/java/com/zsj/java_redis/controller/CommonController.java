package com.zsj.java_redis.controller;


import com.zsj.java_redis.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传与下载
 */
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {
    
    //@Value("${reggin.path}")
    private String basePath = "D:\\Desktop\\java_redis\\img";
    
    /**
     * 文件上传
     *
     * @param file
     * @return
     * @RequestPart("file") MultipartFile file2 如果需要将形参改名依然能接受到传输的图片
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) {
        //file是一个临时文件，需要转存到指定位置，否则本次请求完成后临时文件会删除
        log.info("fileName:{}", file.getName());
        
        //原始文件名
        String originalFilename = file.getOriginalFilename();
        //获取后缀名 .jpg
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        
        //使用UUID重新生成文件名，防止文件名称重复造成覆盖
        String fileName = UUID.randomUUID().toString() + suffix;
        
        //创建一共目录对象
        File dir = new File(basePath);
        //判断当前目录是否存在
        if (!dir.exists()) {
            //    不存在，就创建目录
            dir.mkdir();
        }
        
        try {
            //降临文件转存到指定位置
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(fileName);
    }
    
    /**
     * 文件下载
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {
        //    输入流，通过输入流读取文件内容
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));
            
            //    输出流，通过输出流将文件写回浏览器，在浏览器展示图片
            ServletOutputStream outputStream = response.getOutputStream();
            //设置响应类型
            response.setContentType("image/jpeg");
            
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }
            //关闭资源
            fileInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    




}
