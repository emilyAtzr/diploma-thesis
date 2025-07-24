package com.cgm.cmlfilemanagement.controller;

import com.cgm.cmlfilemanagement.api.FileUploadApi;
import com.cgm.cmlfilemanagement.dto.FileMetadataDto;
import com.cgm.cmlfilemanagement.serivce.FileUploadService;
import com.cgm.cmlfilemanagement.utils.requestBody.FileUploadRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ais/desktop/domain/files/files/FileUploadComponent")
public class FileUploadController implements FileUploadApi {

    private final FileUploadService fileUploadService;

    @Autowired
    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @Override
    @PostMapping("/saveUpload")
    public FileMetadataDto uploadFile(@ModelAttribute FileUploadRequestBody requestBody) {
        return this.fileUploadService.uploadFile(requestBody.patientId(), requestBody.file());
    }

}
