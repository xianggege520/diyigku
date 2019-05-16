package com.zx.demo.service.Impl;

import com.zx.demo.dao.StudentDao;
import com.zx.demo.entity.Student;
import com.zx.demo.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("studentService")
public class StudenServiceImpl implements StudentService {

    @Resource
    private StudentDao studentDao;

    public void add(Student student){
        studentDao.save(student);
    }
}
