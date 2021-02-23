package com.example.xjapan.repository.search;

import com.example.xjapan.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchBoardRepository {

    Board search();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);

}
