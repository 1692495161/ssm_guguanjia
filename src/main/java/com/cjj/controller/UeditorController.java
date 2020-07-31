package com.cjj.controller;

import com.baidu.ueditor.ActionEnter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author cjj
 * @date 2020/7/24
 * @description
 */
@RestController
public class UeditorController {
    @Value("${path1}")
    private String path;
    @Value("${realPath}")
    private String realPath;

    @RequestMapping(value = "doUeditor", produces = "text/html;charset:utf-8")
    public String doUeditor(String action, HttpServletRequest request, MultipartFile upfile) {
        String result = null;
        if ("config".equals(action)) {
//            System.out.println("前端请求获取后台配置json对象啦......");

            //获取config配置文件,并通过exec将其封装成json字符串对象,通过request请求返回给前端
            result = new ActionEnter(request, request.getContextPath()).exec();
        }
        if ("uploadimage".equals(action)) {
//            System.out.println("前端请求ajax图片上传处理啦.......");
            /*
              富文本编辑器的图片上传处理功能:
              1.开启springmvc的文件上传解析器
              2.统一接口方法参数添加MultipartFile对象接收,参数名与config.json中的
              imageFieldName的值对应
              3.springmvc图片上传处理
              4.组装响应结果json对象
             */
            //处理上传文件名，上传到指定的虚拟路径对应的真实文件夹位置   /uploads
            System.out.println(upfile.getOriginalFilename());
            String originalFilename = upfile.getOriginalFilename();
            //切割获取图片后缀
            String type = originalFilename.substring(originalFilename.lastIndexOf("."));
            //组装图片名称
            String filename = UUID.randomUUID() + type;

            try {
                //设置文件上传的真实路径
                upfile.transferTo(new File(realPath, filename));
                //分析ueditor.all.js 23705 行，可以看出响应json格式包含key规则
                Map<String, Object> resultMap = resultMap("SUCCESS",originalFilename,upfile.getSize(),filename,type,path+filename);
                result=new JSONObject(resultMap).toString();
            } catch (IOException e) {
                result = new JSONObject(resultMap("FAIL",null,0,null,null,null)).toString();
                e.printStackTrace();
            }
        }
        if ("uploadfile".equals(action)) {
            System.out.println("前端请求ajax文件上传处理啦......");
        }
        return result;
    }

    //{"state": "SUCCESS","original": "111.jpg","size": "124147","title": "1535961757878095151.jpg","type": ".jpg","url": "/1535961757878095151.jpg"}
    private Map<String, Object> resultMap(String state, String original, long size, String title, String type, String url) {
        Map<String, Object> result = new HashMap<>();
        result.put("state", state);
        result.put("original", original);
        result.put("size", size);
        result.put("title", title);
        result.put("type", type);
        result.put("url", url);
        return result;
    }
}
