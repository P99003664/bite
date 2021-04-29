package com.ltts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltts.model.UploadFiles;
@Repository
public interface UploadFileRepo extends JpaRepository<UploadFiles,Long>{
	 
}
