package com.lefer.bed;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author fang
 * @creatdate 17-7-31
 * @modify 使用jib封装docker时，可以直接将资源目录映射出来，此时该类就是多余的，故注释
 */
//@Configuration
//public class ImageBedConfig extends WebMvcConfigurerAdapter {
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/img/**").addResourceLocations("file:../img/");
//        registry.addResourceHandler("/mp3/**").addResourceLocations("file:../mp3/");
//        super.addResourceHandlers(registry);
//    }
//}
