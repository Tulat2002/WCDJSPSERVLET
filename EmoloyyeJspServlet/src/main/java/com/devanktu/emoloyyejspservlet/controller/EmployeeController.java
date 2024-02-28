package com.devanktu.emoloyyejspservlet.controller;

import com.devanktu.emoloyyejspservlet.entities.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/list-employee")
public class EmployeeController extends HttpServlet {

    private SessionFactory sessionFactory;

    @Override
    public void init() throws ServletException {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchValue = req.getParameter("search");
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            String sql = "SELECT s FROM Employee s" +
                    (searchValue!= null ? " WHERE s.name LIKE '%"+searchValue+"%'  OR s.id LIKE  '"+searchValue+"'" : "");
            List<Employee> eList = session.createQuery(sql,Employee.class).list();
            session.getTransaction().commit();
            req.setAttribute("employee",eList);
            req.getRequestDispatcher("employee/list.jsp").forward(req,resp);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(!id.isEmpty()){
            try(Session session = sessionFactory.openSession()){
                session.beginTransaction();
                Employee e = session.get(Employee.class,id);
                if(e!=null){
                    session.delete(e);
                    session.getTransaction().commit();
                }else {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Not Found");
                }
                resp.sendRedirect("list-employee");
            }
        }else{
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Not Found");
        }
    }
}