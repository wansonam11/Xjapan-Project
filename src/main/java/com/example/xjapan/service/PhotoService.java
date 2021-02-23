package com.example.xjapan.service;

import com.example.xjapan.dto.PageRequestDTO;
import com.example.xjapan.dto.PageResultDTO;
import com.example.xjapan.dto.PhotoDTO;
import com.example.xjapan.dto.PhotoImageDTO;
import com.example.xjapan.entity.Photo;
import com.example.xjapan.entity.PhotoImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface PhotoService {

    Long register(PhotoDTO photoDTO);

    PageResultDTO<PhotoDTO, Object[]> getList(PageRequestDTO requestDTO);

    PhotoDTO getPhoto(Long pno);



    default Map<String, Object> dtoToEntity(PhotoDTO photoDTO){

        Map<String, Object> entityMap = new HashMap<>();

        Photo photo = Photo.builder()
                .pno(photoDTO.getPno())
                .title(photoDTO.getTitle())
                .build();

        entityMap.put("photo", photo);

        List<PhotoImageDTO> imageDTOList = photoDTO.getImageDTOList();

        //PhotoImageDTO
        if(imageDTOList != null && imageDTOList.size() > 0){
            List<PhotoImage> photoImageList = imageDTOList.stream().map(photoImageDTO -> {

                PhotoImage photoImage = PhotoImage.builder()
                        .path(photoImageDTO.getPath())
                        .imgName(photoImageDTO.getImgName())
                        .uuid(photoImageDTO.getUuid())
                        .photo(photo)
                        .build();
                return photoImage;
            }).collect(Collectors.toList());

            entityMap.put("imgList", photoImageList);
        }
        return entityMap;
    }

    default PhotoDTO entitiesToDTO(Photo photo, List<PhotoImage> photoImages,
                                   Double avg, Long reviewCnt){

        PhotoDTO photoDTO = PhotoDTO.builder()
                .pno(photo.getPno())
                .title(photo.getTitle())
                .regDate(photo.getRegDate())
                .modDate(photo.getModDate())
                .build();

        List<PhotoImageDTO> photoImageDTOList = photoImages.stream().map(photoImage -> {
            return PhotoImageDTO.builder()
                    .imgName(photoImage.getImgName())
                    .path(photoImage.getPath())
                    .uuid(photoImage.getUuid())
                    .build();
        }).collect(Collectors.toList());

        photoDTO.setImageDTOList(photoImageDTOList);
        photoDTO.setAvg(avg);
        photoDTO.setReviewCnt(reviewCnt.intValue());

        return photoDTO;
    }
}
