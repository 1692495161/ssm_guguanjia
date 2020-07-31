package com.cjj.web;

import com.google.code.kaptcha.servlet.KaptchaServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * 1.页面设置图片标签   src设置请求地址访问后台servlet
 * 2.servlet生成随机验证码，放入session，并且将验证码字符串放入图片流写出
 * 3.页面接收图片流
 */

/**
 * kaptcha.border 图片边框，合法值：yes , no yes
 * kaptcha.border.color 边框颜色，合法值： r,g,b (and optional alpha) 或者 white,black,blue. black
 * kaptcha.border.thickness 边框厚度，合法值：>0 1
 * kaptcha.image.width 图片宽 200
 * kaptcha.image.height 图片高 50
 * kaptcha.producer.impl 图片实现类 com.google.code.kaptcha.impl.DefaultKaptcha
 * kaptcha.textproducer.impl 文本实现类 com.google.code.kaptcha.text.impl.DefaultTextCreator
 * kaptcha.textproducer.char.string 文本集合，验证码值从此集合中获取 abcde2345678gfynmnpwx
 * kaptcha.textproducer.char.length 验证码长度 5
 * kaptcha.textproducer.font.names 字体 Arial, Courier
 * kaptcha.textproducer.font.size 字体大小 40
 * kaptcha.textproducer.font.color 字体颜色，合法值： r,g,b 或者 white,black,blue. black
 * kaptcha.textproducer.char.space 文字间隔 2
 * kaptcha.noise.impl 干扰实现类 com.google.code.kaptcha.impl.DefaultNoise
 * kaptcha.noise.color 干扰 颜色，合法值： r,g,b 或者 white,black,blue. black
 * kaptcha.obscurificator.impl 图片样式： 水纹com.google.code.kaptcha.impl.WaterRipple 鱼眼com.google.code.kaptcha.impl.FishEyeGimpy阴影com.google.code.kaptcha.impl.ShadowGimpy com.google.code.kaptcha.impl.WaterRipple
 * kaptcha.background.impl 背景实现类 com.google.code.kaptcha.impl.DefaultBackground
 * kaptcha.background.clear.from 背景颜色渐变，开始颜色 light grey
 * kaptcha.background.clear.to 背景颜色渐变， 结束颜色 white
 * kaptcha.word.impl 文字渲染器 com.google.code.kaptcha.text.impl.DefaultWordRenderer
 * kaptcha.session.key session key KAPTCHA_SESSION_KEY
 * kaptcha.session.date session date KAPTCHA_SESSION_DATE
 */
@WebServlet(value = "/code.png",initParams = {
        @WebInitParam(name = "kaptcha.textproducer.char.length",value = "4"),
        @WebInitParam(name = "kaptcha.image.width",value = "120"),
        @WebInitParam(name = "kaptcha.image.height",value = "40"),
        @WebInitParam(name = "kaptcha.textproducer.font.color",value = "black"),
        @WebInitParam(name = "kaptcha.background.clear.from",value = "yellow"),
        @WebInitParam(name = "kaptcha.background.clear.to",value = "green"),
        @WebInitParam(name = "kaptcha.noise.color",value = "white"),
        @WebInitParam(name = "kaptcha.session.key",value = "checkCode"),
        @WebInitParam(name = "kaptcha.textproducer.font.size",value = "28"),
        @WebInitParam(name = "kaptcha.textproducer.char.string",value = "1234567890"),
        @WebInitParam(name = "kaptcha.textproducer.font.names",value = "Monospaced"),
        @WebInitParam(name = "kaptcha.obscurificator.impl",value = "com.google.code.kaptcha.impl.ShadowGimpy")
})
public class CodeServlet extends KaptchaServlet {
}
