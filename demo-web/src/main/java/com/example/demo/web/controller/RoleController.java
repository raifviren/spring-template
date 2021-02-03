package com.example.demo.web.controller;

import com.example.demo.commons.constants.AppConstants;
import com.example.demo.commons.utils.ResponseGenerator;
import com.example.demo.commons.vo.ResultInfo;
import com.example.demo.dao.entities.Role;
import com.example.demo.dao.enums.RoleType;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

/**
 * @author Virender Bhargav
 */
@RequestMapping("/api/v1/role")
@RestController
public class RoleController {
    @Autowired
    ResponseGenerator responseGenerator;

    @Autowired
    RoleService roleService;

    @GetMapping()
    public ResponseEntity<String> getByType(@RequestParam("roleType") RoleType roleType,
                                            HttpServletRequest request) {
        Role role = roleService.findByType(roleType);
        ResultInfo<Role> successResponse = new ResultInfo<Role>(role);
        successResponse.setStatus(AppConstants.ResultStatus.SUCCESS);
        return responseGenerator.generateSuccessResponse(successResponse, HttpStatus.OK);
    }
}
