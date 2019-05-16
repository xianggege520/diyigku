package com.zx.demo.service.Impl;

import com.zx.demo.dao.AccountDao;
import com.zx.demo.entity.Account;
import com.zx.demo.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Transactional
    public void transfreAccounts(int fromUser, int toUser, float account) {
        Account fromaccount = accountDao.getOne(fromUser);
        fromaccount.setBalance(fromaccount.getBalance()-account);
        accountDao.save(fromaccount);

        Account toaccount = accountDao.getOne(toUser);
        toaccount.setBalance(toaccount.getBalance()+account);

        accountDao.save(toaccount);
    }
}
