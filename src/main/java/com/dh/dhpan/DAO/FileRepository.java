package com.dh.dhpan.DAO;

import com.dh.dhpan.model.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<Files, Integer> {
    Files getFilesByFileName(String filename);

}
