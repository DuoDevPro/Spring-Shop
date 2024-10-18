package com.product_service.productMapper;

import com.product_service.dtos.ImageDto;
import com.product_service.entity.Image;
import org.springframework.stereotype.Service;

@Service
public class ImageMapper {
    public ImageDto toImageDto(Image image) {
        return ImageDto.builder()
                .id(image.getId())
                .downloadUrl(image.getDownloadUrl())
                .fileName(image.getFileName())
                .build();
    }
}
