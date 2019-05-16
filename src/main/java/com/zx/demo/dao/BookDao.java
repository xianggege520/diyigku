package com.zx.demo.dao;

import com.zx.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookDao extends JpaRepository<Book,Integer>,JpaSpecificationExecutor<Book>{

    @Query("select b from Book b where b.name like %?1%")
    public List<Book> findByName(String name);

    @Query(value = "select * from t_book order by RAND() limit ?1",nativeQuery=true)
    public List<Book> randomList(Integer n);




}