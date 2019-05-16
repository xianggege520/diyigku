package com.zx.demo.dao;

import com.zx.demo.entity.Account;
import com.zx.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDao extends JpaRepository<Account,Integer> {
}
