package com.example.demo2.repository.xframe;

import com.example.demo2.model.xframe.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module, String> {

    List<Module> findByProjectIdAndModuleId(String projectId, String moduleId);

}
