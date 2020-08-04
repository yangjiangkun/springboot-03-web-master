package com.sac.dao;

import com.sac.pojo.Department;
import com.sac.pojo.Employee;
import org.springframework.aop.aspectj.DeclareParentsAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//员工dao层
@Repository
public class EmployeeDao {
    //模拟数据库中的数据
    private static Map<Integer, Employee> employeeMap = null;

    //员工所属部门
    @Autowired
    private DepartmentDao departmentDao;

    static {
        employeeMap = new HashMap<Integer, Employee>();//创建一个部门表
        employeeMap.put(1001, new Employee(1001, "AA", "240293002@qq.com", 1, new Department(101, "后勤部")));
        employeeMap.put(1002, new Employee(1002, "BB", "240s24402@qq.com", 0, new Department(102, "体育部")));
        employeeMap.put(1003, new Employee(1003, "CC", "240293002@qq.com", 1, new Department(103, "宣传部")));
        employeeMap.put(1004, new Employee(1004, "DD", "746781345@qq.com", 0, new Department(104, "文艺部")));
        employeeMap.put(1005, new Employee(1005, "EE", "sfgwt3456@qq.com", 1, new Department(105, "广播部")));
    }

    //主键自增
    private static Integer initId = 1006;

    //增加一个员工
    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employeeMap.put(employee.getId(), employee);
    }

    //查询全部员工信息
    public Collection<Employee> getAll() {
        return employeeMap.values();
    }

    //通过id查询员工
    public Employee getEmployeeById(Integer id) {
        return employeeMap.get(id);
    }

    //通过id删除员工
    public Employee deleteEmployeeById(Integer id) {
        return employeeMap.remove(id);
    }
}
