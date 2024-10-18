package com.product_service.service;

import com.product_service.dtos.ImageDto;
import com.product_service.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageSerivce {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDto> saveImages(Long productId, List<MultipartFile> files);
    void updateImage(MultipartFile file, Long imageId);
}
