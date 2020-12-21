package com.zx.dao;

import com.zx.bean.Photo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wking
 * @create 2020-08-20 12:11
 */
@Repository
public interface PhotoMapper {
  
    int insert(Photo photo);

    List<Photo> delUserPhotoInfo(Photo photo);

    List<Photo> getUserPhotoInfo(String loginname);
}
