package com.example.demo2.repository.xframe;

import com.example.demo2.model.xframe.Screen;
import com.example.demo2.model.xframe.ScreenPk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScreenRepository extends JpaRepository<Screen, ScreenPk> {
    List<Screen> findByTitle(String title);
    List<Screen> findByProjectIdAndScreenId(String projectId, String screenId);
    List<Screen> findByScreenId(String screenId);
}
