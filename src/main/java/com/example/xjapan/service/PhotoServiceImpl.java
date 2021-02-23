package com.example.xjapan.service;

import com.example.xjapan.dto.PageRequestDTO;
import com.example.xjapan.dto.PageResultDTO;
import com.example.xjapan.dto.PhotoDTO;
import com.example.xjapan.entity.Photo;
import com.example.xjapan.entity.PhotoImage;
import com.example.xjapan.repository.PhotoImageRepository;
import com.example.xjapan.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final PhotoImageRepository photoImageRepository;

    @Transactional
    @Override
    public Long register(PhotoDTO photoDTO) {

        Map<String, Object> entityMap = dtoToEntity(photoDTO);
        Photo photo = (Photo)entityMap.get("photo");
        List<PhotoImage> photoImageList = (List<PhotoImage>) entityMap.get("imgList");

        photoRepository.save(photo);
        photoImageList.forEach(photoImage -> {
            photoImageRepository.save(photoImage);
        });
        return photo.getPno();
    }

    @Override
    public PageResultDTO<PhotoDTO, Object[]> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("pno").descending());
        Page<Object[]> result = photoRepository.getListPage(pageable);

        Function<Object[], PhotoDTO> fn = (arr -> entitiesToDTO(
                (Photo) arr[0],
                (List<PhotoImage>)(Arrays.asList((PhotoImage) arr[1])),
                (Double) arr[2],
                (Long) arr[3])
        );
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PhotoDTO getPhoto(Long pno) {

        List<Object[]> result = photoRepository.getPhotoWithAll(pno);

        Photo photo = (Photo) result.get(0)[0];

        List<PhotoImage> photoImageList = new ArrayList<>();

        result.forEach(arr -> {
            PhotoImage photoImage = (PhotoImage)arr[1];
            photoImageList.add(photoImage);
        });

        Double avg = (Double) result.get(0)[2];
        Long reviewCnt = (Long) result.get(0)[3];

        return entitiesToDTO(photo, photoImageList, avg, reviewCnt);
    }


}
