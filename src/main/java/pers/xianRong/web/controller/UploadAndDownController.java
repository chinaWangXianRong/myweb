package pers.xianRong.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pers.xianRong.web.server.MailServer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by user on 2016/12/19.
 */
@Controller
public class UploadAndDownController {


    @Value("${user.picture.path}")
    private  String picturePath;
    @Autowired
    private MailServer mailServer;
    @Autowired
    private CacheManager cacheManager;

    @RequestMapping("/upload.html")
    public String goUploadPage(){
        return "upload";
    }
    /*
    * 学习地址;http://www.cnblogs.com/h--d/p/5761649.html
    * */
    @RequestMapping(value = "/upload.do",method = RequestMethod.POST)
    public @ResponseBody
    String uploadPic(HttpServletRequest request, HttpServletResponse response,@RequestParam("file") MultipartFile file) {
        InputStream in = null;
        FileOutputStream out = null;
        String newName = null;
        String result="error|上传失败";
        try {
            if (file != null) {
                String name = file.getOriginalFilename();
                String exName = name.substring(name.lastIndexOf(".") + 1).toLowerCase();//获取扩展名
                newName = UUID.randomUUID().toString() .replaceAll("-","")+ "." + exName;
                out = new FileOutputStream(picturePath + "\\" + newName);
                in = file.getInputStream();
                byte[] b = new byte[1024];
                int len = 0;
                while ((len = in.read(b)) > 0) {
                    out.write(b, 0, len);
                }

                result="/picture/"+newName;
            }


        } catch (Exception e) {
           result="error|服务器端错误";
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return result;
    }
    @RequestMapping(value = "/picture/{name}.{extName}")
    public void downPicture(HttpServletRequest request, HttpServletResponse response, @PathVariable("name")  String name,@PathVariable("extName")  String extName){
        FileInputStream fin=null;
        ServletOutputStream out=null;
        try{
            File f= new File(picturePath);//文件存放目录 将重写这段 要求检测文件路径是否存在
            if(!f.exists()){
                f.mkdir();
            }
             fin = new FileInputStream(picturePath+File.separator+name+"."+extName);
             out = response.getOutputStream();
            byte[] b= new byte[1024];
            int len=0;
            while ((len=fin.read(b))>0){
                out.write(b,0,len);
            }
            out.flush();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }

    @RequestMapping("/getEmailVerficationCode")
    public @ResponseBody  String getEmailVerficationCode(String email){
        Cache verification = cacheManager.getCache("verification");
        System.out.println(verification.get("wangxianrong@mmia.com"));
        System.out.println(verification.get(email));
        System.out.println(mailServer.getEmailCode(email));
        return mailServer.getEmailCode(email);
    }
}
