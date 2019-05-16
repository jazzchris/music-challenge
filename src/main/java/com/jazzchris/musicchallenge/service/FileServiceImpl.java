package com.jazzchris.musicchallenge.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DeleteErrorException;
import com.dropbox.core.v2.files.DeleteResult;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.GetTemporaryLinkResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.RelocationResult;
import com.dropbox.core.v2.files.RestoreErrorException;
import com.dropbox.core.v2.files.UploadErrorException;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private DbxClientV2 dbxClientV2;
	
	public DbxClientV2 getDbxClientV2() {
		return dbxClientV2;
	}
	@Override
	public void uploadFile(MultipartFile file, String title) throws UploadErrorException, DbxException, IOException {
		
		if (file.isEmpty()) {
			return;
		}
		
		String path = "/".concat(title).concat(".mp3");
		
		FileMetadata metadata = dbxClientV2.files().uploadBuilder(path)
				.uploadAndFinish(file.getInputStream());
		
		System.out.println("==> Uploaded file: " + metadata.getName() + " size: " + metadata.getSize());
	}

	@Override
	public void deleteFile(String title) throws DeleteErrorException, DbxException {

		String path = "/".concat(title).concat(".mp3");
		
		DeleteResult result = dbxClientV2.files().deleteV2(path);
		
		System.out.println("==> Deleted file: " + result.getMetadata().getName());
	}

	@Override
	public void renameFile(String oldTitle, String newTitle) throws RestoreErrorException, DbxException {
		String oldPath = "/".concat(oldTitle).concat(".mp3");
		String newPath = "/".concat(newTitle).concat(".mp3");
		
		RelocationResult result = dbxClientV2.files().moveV2(oldPath, newPath);
		
		System.out.println("==> Renamed file: " + oldTitle + " new title: " + result.getMetadata().getName());
	}

	@Override
	public InputStream streamFile(String title) {
		
		String path = "/".concat(title).concat(".mp3");
		GetTemporaryLinkResult temp = null;
		try {
			temp = dbxClientV2.files().getTemporaryLink(path);
		} catch (DbxException e) {
			e.printStackTrace();
			System.out.println("Cannot access to file");
		}
		String link = temp.getLink();
		InputStream stream = null;
		try {
			stream = new URL(link).openStream();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Cannot stream the file");
		}
		return stream;
	}
	

	@Override
	public List<String> listFile() {
		List<Metadata> list = null;
		try {
			list = dbxClientV2.files().listFolder("").getEntries();
		} catch (DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list.stream()
				.map(m -> m.getPathLower())
				.filter(n -> n.endsWith("mp3"))
				.collect(Collectors.toList());
	}
	
	public List<String> listFile(String path) {
		List<Metadata> list = null;
		try {
			list = dbxClientV2.files().listFolder(path).getEntries();
		} catch (DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list.stream()
				.map(m -> m.getPathLower())
				.filter(n -> n.endsWith("mp3"))
				.collect(Collectors.toList());		
	}
	
	public List<String> deepList() {
		List<Metadata> metaList =  null;
		try {
			metaList = dbxClientV2.files().listFolder("").getEntries();
		} catch (DbxException e) {
			e.printStackTrace();
		}
		List<String> list = metaList.stream()
				.map(m -> m.getPathLower())
				.filter(n -> n.endsWith("mp3"))
				.collect(Collectors.toList());
		List<String> sublist = metaList.stream()
				.map(m -> m.getPathLower())
				.filter(n -> !n.contains("."))
				.map(o -> listFile(o))
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
		list.addAll(sublist);
		return list;
		
	}
	
	public List<String> randomList(int limit) {
		List<String> list = deepList();
		Collections.shuffle(list);
		return list.subList(0, limit);
	}
}
