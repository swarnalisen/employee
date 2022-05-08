package com.demo.employee.dao;


import com.demo.employee.model.Employee;
import com.demo.mongoconfig.MongoConfig;
import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeDAOImpl {

    @Autowired
    private MongoConfig mongoConfig;

    private final String DB_NAME = "empDB";
    private final String EMPLOYEE_ID = "id";
    private final String EMPLOYEE_FIRST_NAME = "firstName";
    private final String EMPLOYEE_LAST_NAME = "lastName";
    private final String EMPLOYEE_EMAIL_ID = "emailId";


    public List<Employee> getAllEmployees(){
        return mongoConfig.getMongoTemplate(DB_NAME).findAll(Employee.class);
    }

    public Employee getEmployeeById(long id){
        Criteria criteria = Criteria.where(EMPLOYEE_ID).is(id);
        Query query = new Query(criteria);
        return mongoConfig.getMongoTemplate(DB_NAME).findOne(query,Employee.class);
    }

    public void addEmployee(Employee e){
        mongoConfig.getMongoTemplate(DB_NAME).save(e);
    }

    public void updateEmployeeById(long id, Employee e){
        Criteria criteria = Criteria.where(EMPLOYEE_ID).is(id);
        Query query = new Query(criteria);
        Update update = new Update().set(EMPLOYEE_FIRST_NAME,e.getFirstName()).set(EMPLOYEE_LAST_NAME,e.getLastName()).set(EMPLOYEE_EMAIL_ID,e.getEmailId());
        mongoConfig.getMongoTemplate(DB_NAME).updateFirst(query,update,Employee.class);
    }

    public void deleteEmployeeById(long id){
        Criteria criteria = Criteria.where(EMPLOYEE_ID).is(id);
        Query query = new Query(criteria);
        mongoConfig.getMongoTemplate(DB_NAME).remove(query,Employee.class);
    }
}
