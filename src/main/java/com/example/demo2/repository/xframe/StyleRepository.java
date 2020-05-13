package com.example.demo2.repository.xframe;

import com.example.demo2.model.xframe.Style;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StyleRepository extends JpaRepository<Style, String> {

    List<Style> findByStyleId(String id);
    void deleteByStyleId(String id);
}
