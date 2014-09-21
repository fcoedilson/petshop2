package br.com.sample.bean;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.sample.entity.Produto;
import br.com.sample.service.ProdutoService;

@Scope("session")
@Component("produtoBean")
public class ProdutoBean extends EntityBean<Long, Produto> {

	@Autowired
	private ProdutoService service;

	private UploadedFile file;


	protected Long retrieveEntityId(Produto entity) {
		return entity.getId();
	}

	protected ProdutoService retrieveEntityService() {
		return this.service;
	}

	protected Produto createNewEntity() {
		Produto produto = new Produto();
		return produto;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String save(){
		return super.save();
	}


	public void handleFileUpload(FileUploadEvent event) {  

		try {

			if(event != null){

				UploadedFile file = event.getFile();
				String fileName = file.getFileName();
				long fileSize = file.getSize();
				InputStream inputStream = file.getInputstream();

				ByteArrayOutputStream bos = new ByteArrayOutputStream();

				byte[] buf = new byte[1024];
				try {
					for (int readNum; (readNum = inputStream.read(buf)) != -1;) {
						bos.write(buf, 0, readNum);      
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				byte[] bytes = bos.toByteArray();

				this.entity.setImage(bytes);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}