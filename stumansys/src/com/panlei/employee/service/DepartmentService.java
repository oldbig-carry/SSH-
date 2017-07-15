package com.panlei.employee.service;

import java.util.List;

import com.panlei.employee.domain.Department;
import com.panlei.employee.domain.PageBean;

/**
 * ����ҵ���Ľӿ�
 */
public interface DepartmentService {

	PageBean<Department> findByPage(Integer currPage);

	void save(Department department);

	Department findById(Integer did);

	void update(Department department);

	void delete(Department department);

	List<Department> findAll();

}
