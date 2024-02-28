package com.devanktu.emoloyyejspservlet.controller;

import com.devanktu.emoloyyejspservlet.entities.Employee;
import com.devanktu.emoloyyejspservlet.entities.model.EmployeeModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Set;

import static com.controller.EmployeeCreateController.getEmployeeModel;
import static com.controller.EmployeeCreateController.validateEmployee;

@WebServlet(value = "/edit-employee")
public class EmployeeEditController extends HttpServlet {
    private SessionFactory sessionFactory;
    private final Validator validator;

    public EmployeeEditController() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


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
        String ID = req.getParameter("id");
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            //        find and EDIT or resp err if failed ( resp.setStatus(404);)
            Employee e = session.get(Employee.class,ID);
            session.getTransaction().commit();
            if(e!=null){
                req.setAttribute("editEmployee",e);
                req.getRequestDispatcher("employee/edit.jsp").forward(req, resp);
            }else{
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Not Found");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeModel employee = getEmployeeModel(req);
        if(validateEmployee(employee)){
            try(Session session = sessionFactory.openSession()){
                Employee e = new Employee(employee.getId(),employee.getName(),employee.getBirthday(),employee.getPhone(),employee.getEmail());
                session.beginTransaction();
                session.update(e);
                session.getTransaction().commit();
                resp.sendRedirect("list-employee?search="+e.getId());
            }
        }else{
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "data invalid");
        }
    }
}}
