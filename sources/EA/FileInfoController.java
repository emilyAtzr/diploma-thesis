package com.cgm.cmlfilemanagement.controller;

import com.cgm.cmlfilemanagement.api.FileInfoApi;
import com.cgm.cmlfilemanagement.dto.FileDto;
import com.cgm.cmlfilemanagement.dto.FileMetadataDto;
import com.cgm.cmlfilemanagement.serivce.FileInfoService;
import com.cgm.cmlfilemanagement.utils.requestBody.FileSearchRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ais/desktop/filemanagement/files")
public class FileInfoController implements FileInfoApi {

    private final FileInfoService fileInfoService;

    @Autowired
    public FileInfoController(FileInfoService fileInfoService) {
        this.fileInfoService = fileInfoService;
    }

    @Override
    @GetMapping("/{fileId}")
    public FileDto findFileById(@PathVariable Integer fileId) {
        return this.fileInfoService.findFileById(fileId);
    }

    @Override
    @PostMapping
    public List<FileMetadataDto> findFilesByPatientIdAndType(@RequestBody FileSearchRequestBody requestBody) {
        return this.fileInfoService.findFilesByPatientIdAndType(requestBody.patientId(), requestBody.type());
    }

}
