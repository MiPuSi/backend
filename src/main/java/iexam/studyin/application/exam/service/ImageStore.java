package iexam.studyin.application.exam.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ImageStore {

    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    public List<String> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<String> results = new ArrayList<>();
        if (multipartFiles == null || multipartFiles.isEmpty()) return results;
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty())
                results.add(storeFile(multipartFile));
        }
        return results;
    }

    private String storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) return null;

        System.out.println("multipartFile = " + multipartFile.getOriginalFilename());


        String storeFileName = createStoreFileName(multipartFile.getOriginalFilename());
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return storeFileName;
    }

    private String createStoreFileName(String originalFilename) {
        String ext = extractedExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractedExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
