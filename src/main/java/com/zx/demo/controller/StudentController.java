package com.zx.demo.controller;

import com.zx.demo.entity.Student;
import com.zx.demo.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @RequestMapping("/add")
    public String add(@Validated Student student, BindingResult bindingResult){
        System.out.println("忘不了你的爱");
        if(bindingResult.hasErrors()){
            return bindingResult.getFieldError().getDefaultMessage();
        }else {
            studentService.add(student);
            return "添加成功";
        }

    }
}
