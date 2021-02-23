package com.example.xjapan.repository;

import com.example.xjapan.entity.Photo;
import com.example.xjapan.entity.PhotoImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class PhotoRepositoryTests {

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private PhotoImageRepository photoImageRepository;

    @Commit
    @Transactional
    @Test
    public void insertPhotos(){

        IntStream.rangeClosed(1, 100).forEach(i ->{

            Photo photo = Photo.builder()
                    .title("X-japan Photo..." + i)
                    .build();

            System.out.println("----------------------------------------------------");

            photoRepository.save(photo);

            int count = (int)(Math.random() * 5) + 1; //1, 2, 3, 4

            for(int j = 0; j < count; j++){
                PhotoImage photoImage = PhotoImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .photo(photo)
                        .imgName("x-member" + j + ".jpg")
                        .build();

                photoImageRepository.save(photoImage);
            }
            System.out.println("=====================================================");
        });
    }

    @Test
    public void testListPage(){

        PageRequest pageRequest = PageRequest.of(0, 7,
                Sort.by(Sort.Direction.DESC, "pno"));

        Page<Object[]> result = photoRepository.getListPage(pageRequest);

        for(Object[] objects : result.getContent()){
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void testGetPhotoWithAll(){

        List<Object[]> result = photoRepository.getPhotoWithAll(62L);

        System.out.println(result);

        for(Object[] arr : result){
            System.out.println(Arrays.toString(arr));
        }
    }
}
