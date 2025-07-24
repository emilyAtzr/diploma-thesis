package com.cgm.patientmanagement.controller;

import com.cgm.patientmanagement.api.PatientApi;
import com.cgm.patientmanagement.dto.PatientDto;
import com.cgm.patientmanagement.dto.PatientListEntryDto;
import com.cgm.patientmanagement.exception.RequestBodyException;
import com.cgm.patientmanagement.service.PatientService;
import com.cgm.patientmanagement.utils.requestBody.PatientRequestBody;
import com.cgm.patientmanagement.utils.requestBody.PatientSearchRequestBody;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ais/desktop/domain/patient")
public class PatientController implements PatientApi {

    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    @PostMapping("/search/PatientSearchComponent/findPatients")
    public List<PatientListEntryDto> findPatients(@RequestBody(required = false) PatientSearchRequestBody requestBody) {
        if (requestBody == null) {
            logger.error("Patient search request body is null");
            throw new RequestBodyException("Patient search request body is required");
        }

        if (requestBody.searchString() == null || requestBody.searchString().isBlank()) {
            return patientService.findAll();
        }

        String trimmed = requestBody.searchString().trim();

        if(trimmed.matches("^[0-9]\\d*$|^0$")) {
            return patientService.findBySocialSecurityNumber(trimmed);
        } else {
            return patientService.findByName(trimmed);
        }
    }

    @Override
    @PostMapping("/patient/AisPatientComponent/findById")
    public PatientDto findPatientById(@RequestBody PatientRequestBody requestBody) {
        if (requestBody == null || requestBody.patientId() == null) {
            logger.error("Patient request body is null");
            throw new RequestBodyException("No patient id provided.");
        }
        return patientService.findPatientById(requestBody.patientId());
    }
}