package cn.edu.zjut.utils;
import cn.hutool.core.codec.Base64;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName 配置码云图床信息
 * @Description 上传Gitee图床工具类
 */
public class UploadGiteeImgBed {
    /**
     * 码云私人令牌
     */
    public static final String ACCESS_TOKEN = "1a1dc64cd9cf81f5c172c644907df293";  //这里不展示我自己的了，需要你自己补充

    /**
     * 码云个人空间名
     */
    public static final String OWNER = "Not_Found_404_114514";

    /**
     * 上传指定仓库
     */
    public static final String REPO = "picture-bed";

    /**
     * 上传时指定存放图片路径
     */
    public static final String PATH = "/uploadimg/" + getYearMonth() + "/"; //使用到了日期工具类

    /**
     * 用于提交描述
     */
    public static final String ADD_MESSAGE = "add img";
    public static final String DEL_MESSAGE = "DEL img";

    //API
    /**
     * 新建(POST)、获取(GET)、删除(DELETE)文件：()中指的是使用对应的请求方式
     * %s =>仓库所属空间地址(企业、组织或个人的地址path)  (owner)
     * %s => 仓库路径(repo)
     * %s => 文件的路径(path)
     */
    public static final String API_CREATE_POST = "https://gitee.com/api/v5/repos/%s/%s/contents/%s";

    /**
     * 生成创建(获取、删除)的指定文件路径
     * @param originalFilename
     * @return
     */
    public static String createUploadFileUrl(String originalFilename) {
        //获取文件后缀
        String suffix = getFileSuffix(originalFilename); //使用了嵌入的FileUtils工具类
        //拼接存储的图片名称
        String fileName = System.currentTimeMillis() + "_" + UUID.randomUUID().toString() + suffix;
        //填充请求路径
        String url = String.format(UploadGiteeImgBed.API_CREATE_POST,
                UploadGiteeImgBed.OWNER,
                UploadGiteeImgBed.REPO,
                UploadGiteeImgBed.PATH + fileName);
        return url;
    }

    /**
     * 获取创建文件的请求体map集合：access_token、message、content
     * @param multipartFile 文件字节数组
     * @return 封装成map的请求体集合
     */
    public static Map<String,Object> getUploadBodyMap(byte[] multipartFile) {
        HashMap<String, Object> bodyMap = new HashMap<>(3);
        bodyMap.put("access_token", UploadGiteeImgBed.ACCESS_TOKEN);
        bodyMap.put("message", UploadGiteeImgBed.ADD_MESSAGE);
        bodyMap.put("content", Base64.encode(multipartFile));
        return bodyMap;
    }

    /**
     * 返回当前的年月字符串，示例：2021-08
     * @return 年月字符串
     */
    public static String getYearMonth() {
        //yyyy-MM
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(new Date());
    }

    /**
     * 获取文件名的后缀，如：changlu.jpg => .jpg
     * @return 文件后缀名
     */
    public static String getFileSuffix(String fileName) {
        return fileName.contains(".") ? fileName.substring(fileName.indexOf('.')) : null;
    }
}
