package com.juan.estevez.app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Se encarga de cargar y almacenar imagenes de productos en el proyecto.
 * 
 * @author Juan Carlos Estevez Vargas.
 *
 */
@Service
public class UploadFileService {

	private String folder = "images//";

	/**
	 * Guarda una imagen en el directorio images del proyecto.
	 * 
	 * @param file de tipo imagen a guardar.
	 * @return nombre de la imagen almacenada en el proyecto.
	 * @throws IOException
	 */
	public String saveImage(MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(folder + file.getOriginalFilename());
			Files.write(path, bytes);
			return file.getOriginalFilename();
		}
		return "default.jpg";
	}

	/**
	 * Elimina una imagen del proyecto.
	 * 
	 * @param name de la imagen a eliminar.	
	 */
	public void deleteImage(String name) {
		String route = "images//";
		File file = new File(route + name);
		file.delete();
	}
}
