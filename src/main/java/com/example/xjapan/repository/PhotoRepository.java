package com.example.xjapan.repository;

import com.example.xjapan.entity.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Query("select p, pi, avg(coalesce(r.grade, 0)), count(r) from Photo p " +
            "left outer join PhotoImage pi on pi.photo = p " +
            "left outer join Review r on r.photo = p group by p")
    Page<Object[]> getListPage(Pageable pageable);

    @Query("select p, pi, avg(coalesce(r.grade, 0)), count(r) from Photo p " +
            "left outer join PhotoImage pi on pi.photo = p " +
            "left outer join Review r on r.photo = p " +
            "where p.pno = :pno group by pi")
    List<Object[]> getPhotoWithAll(Long pno);
}
