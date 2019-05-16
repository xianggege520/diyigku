package com.zx.demo.controller;

import com.zx.demo.dao.BookDao;
import com.zx.demo.entity.Book;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BoolController {

    @Resource
    private BookDao bookDao;

    @RequestMapping("/list")
    public ModelAndView list(){
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa");
        ModelAndView mav = new ModelAndView();
        mav.addObject("bookList",bookDao.findAll());
        mav.setViewName("bookList");
        return mav;
    }

    @RequestMapping("/list2")
    public ModelAndView list2(Book book){
        ModelAndView mav = new ModelAndView();
        List<Book> bookList = bookDao.findAll(new Specification<Book>() {
            @Override       //动态拼接条件
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if(book !=null){
                    if(book.getName()!=null && !"".equals(book.getName())){
                        predicate.getExpressions().add(cb.like(root.get("name"),"%"+book.getName()+"%"));
                    }
                    if(book.getAuthor()!=null && !"".equals(book.getAuthor())){
                        predicate.getExpressions().add(cb.like(root.get("author"),"%"+book.getAuthor()+"%"));
                    }
                }
                return predicate;
            }
        });
        mav.addObject("bookList",bookList);
        mav.setViewName("bookList");
        return mav;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(Book book){
        System.out.println("添加页面");
        bookDao.save(book);
        return "forward:/book/list";
    }
    @RequestMapping("/preUpdate/{id}")
    public ModelAndView preUpdate(@PathVariable("id")String id){
        System.out.println("进入了修改页面");
        ModelAndView mav = new ModelAndView();
        int num=Integer.valueOf(id);
        System.out.println(bookDao.getOne(num)+"ssss");
        mav.addObject("book",bookDao.getOne(num));
        mav.setViewName("bookUpdate");
        return mav;
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(Book book){
        System.out.println("修改页面");
        bookDao.save(book);
        return "forward:/book/list";
    }

    @GetMapping("/delete")
    public String delete(Book book){
        bookDao.delete(book);
        return "forward:/book/list";
    }

    @ResponseBody
    @GetMapping("/queryByName")
    public List<Book> queryByName(){
        System.out.println("asd");
        return bookDao.findByName("编程");
    }

    @ResponseBody
    @GetMapping("/randomList")
    public List<Book> randomList(){
        return bookDao.randomList(2);
    }

}
