package com.ltts.documentupload;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.ltts.model.UploadFiles;
import com.ltts.repository.UploadFileRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class DocumentUploadApplicationTests {
	@Autowired
	private UploadFileRepo repo;
	@Autowired
	private TestEntityManager entityManager;
	@Test
	@Rollback(false)
	void testInsertDocument() throws IOException {
		File file=new File("Documents\\BITE LOGS.docx");
		UploadFiles uf=new UploadFiles();
		uf.setName(file.getName());
		byte[] bytes=Files.readAllBytes(file.toPath());
		uf.setContent(bytes);
		long filesize=bytes.length;
		uf.setSize(filesize);
		uf.setUploadTime(new Date(filesize));
		
		UploadFiles savedfiles=repo.save(uf);
		UploadFiles existfiles=entityManager.find(UploadFiles.class, savedfiles.getId());
		assertThat(existfiles.getSize()).isEqualTo(filesize);
		
	}
}


