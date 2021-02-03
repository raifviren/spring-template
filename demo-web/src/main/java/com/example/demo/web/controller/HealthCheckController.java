package com.example.demo.web.controller;

import com.example.demo.commons.constants.AppConstants.ResultStatus;
import com.example.demo.commons.utils.ResponseGenerator;
import com.example.demo.commons.vo.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@RestController
public class HealthCheckController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HealthCheckController.class);
    @Autowired
    ResponseGenerator responseGenerator;

    @GetMapping(value = "_status")
    public ResponseEntity<String> doHealthCheck(HttpServletRequest request) {
        StringBuilder apiLog = new StringBuilder();
        apiLog.append("Rest API: ").append(request.getRequestURL().toString()).append("\n");
        apiLog.append(" HEADERS ").append(" :: ");
        for (String header : Collections.list(request.getHeaderNames())) {
            apiLog.append(header).append(":").append(request.getHeader(header))
                    .append("\n");
        }
        LOGGER.info(" REQUEST  " + apiLog.toString());
        LOGGER.info("I'm perfectly alright! You can take my services :)");
        ResultInfo<String> successResponse = new ResultInfo<String>();
        successResponse.setStatus(ResultStatus.SUCCESS);
        return responseGenerator.generateSuccessResponse(successResponse, HttpStatus.OK);
    }
}
