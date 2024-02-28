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

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@WebServlet(value = "/create-employee")
public class EmployeeCreateController extends HttpServlet {

    private SessionFactory sessionFactory;
    public static Validator validator = null;

    public EmployeeCreateController() {
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
        req.getRequestDispatcher("employee/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeModel employee = getEmployeeModel(req);

        if(validateEmployee(employee)){
            try (Session session = sessionFactory.openSession()) {
                Employee e = new Employee(employee.getId(),employee.getName(),employee.getBirthday(),employee.getPhone(),employee.getEmail());
                session.beginTransaction();
                session.save(e);
                session.getTransaction().commit();
                resp.sendRedirect("list-employee?search="+e.getId());
            }
        }else{
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "data invalid");
        }
    }
    static boolean validateEmployee (EmployeeModel employee ){
        Set<ConstraintViolation<EmployeeModel>> violations = validator.validate(employee);
        return violations.isEmpty();
    }
    static EmployeeModel getEmployeeModel (HttpServletRequest req){
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        EmployeeModel employee = new EmployeeModel();
        // Tạo một UUID
        UUID uuid = UUID.randomUUID();
        // Chuyển UUID thành chuỗi
        String uniqueId = uuid.toString();
        employee.setId(uniqueId);
        employee.setName(req.getParameter("name"));
        employee.setBirthday(formatter.parseDateTime(req.getParameter("birthday")).toDate());
        employee.setPhone(req.getParameter("phone"));
        employee.setEmail(req.getParameter("email"));
        return employee;
    }
}
