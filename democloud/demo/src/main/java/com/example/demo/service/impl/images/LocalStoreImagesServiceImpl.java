package com.example.demo.service.impl.images;

import cn.hutool.core.io.FileTypeUtil;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.impl.utils.UserDetailsImpl;
import com.example.demo.service.user.images.LocalStoreImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.*;

/**
 * @项目名: democloud
 * @文件名: LocalStoreImagesServiceImpl
 * @作者: LuoTianYu
 * @日期:2022/12/7 22:45
 */

// 上传头像
@Service
public class LocalStoreImagesServiceImpl implements LocalStoreImagesService {


    @Autowired
    UserMapper userMapper;

    @Override
    public Map<String, String> setImages(MultipartFile file) {
        // 存储到对应用户的Token里面去
        // 把当前用户从本地存储中的Token取出来 对应当前的用户
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        //System.out.println("当前用户: " + user);

        Map<String,String> map = new HashMap<>();

        if(file==null){
            System.out.println(1);
        }

        String pic = Jude(file);// 对文件取地址
        if(pic == null){
            pic = user.getPhoto(); // 原先的图片
        }
        System.out.println(pic);

        User user1 = new User(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                pic,
                user.getRating(),
                user.getSchool(),
                user.getEmail(),
                user.getPersonal(),
                user.getSex()
        );
        userMapper.updateById(user1); // 更新
        map.put("msg","success");
        return map;
    }

    public void test(){
        // 文件名
        //String filename = file.getOriginalFilename();
        //System.out.println(filename);
        //// 读取图片字节数组
        //String fileString = "";
        //BASE64Encoder encoder = new BASE64Encoder();
        //try {
        //    fileString = encoder.encode(file.getBytes()); // 转为base64
        //} catch (IOException e) {
        //    throw new RuntimeException(e);
        //}
        //// - 这里分割是  base 64 - 图片
        //BASE64Decoder decoder = new BASE64Decoder(); // 解码
        //try {
        //    byte[] b = decoder.decodeBuffer(fileString);
        //    for (int i = 0; i < b.length; i++) {
        //        if(b[i]<0){
        //            b[i]+=256;
        //        }
        //    }
        //    // 新的文件名字
        //    String newFileName = System.currentTimeMillis() + UUID.randomUUID().toString()
        //            .replace(".", "").substring(0, 6) +".jpg";
        //    // 生成jpeg图片
        //    String imgFilePath = "e:\\img\\"+newFileName;
        //    OutputStream out = new FileOutputStream(imgFilePath);
        //    out.write(b);
        //    out.flush();
        //    out.close();
        //}catch (Exception e){
        //    e.printStackTrace();
        //}

    }

    public String Jude(MultipartFile file){
        String fileName = file.getOriginalFilename();
        String extension = fileName.substring(fileName.indexOf("."));
        String uploadFolder = "E://web/img/";//获取图片路径
        fileName = UUID.randomUUID() + extension;
        File dest = new File(uploadFolder + fileName);
        if (!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();// 检测文件目录是否存在 不存在则创建
        }
        try {
            file.transferTo(dest);
            String picURL = "http://localhost:4000/img/" + fileName;
            return picURL;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }



}
