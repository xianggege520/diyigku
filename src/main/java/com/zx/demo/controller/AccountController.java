package com.zx.demo.controller;

import com.zx.demo.service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @RequestMapping("transfer")
    public String transferAccount(){
        try {
            accountService.transfreAccounts(1,2,200);
            return "OK";
        }catch (Exception e){
            return "NO";
        }
    }
}
