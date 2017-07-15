package com.panlei.employee.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.panlei.employee.dao.EmployeeDao;
import com.panlei.employee.domain.Department;
import com.panlei.employee.domain.Employee;
import com.panlei.employee.domain.PageBean;
import com.panlei.employee.service.EmployeeService;

/**
 * Ա�������ʵ����
 */
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	//ע��DAO��Ķ���
	private EmployeeDao employeeDao;

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	//ҵ���ĵ�½����
	@Override
	public Employee login(Employee employee) {
		// TODO Auto-generated method stub
		Employee existEmployee = employeeDao.findByUsernameAndPassword(employee);
		return existEmployee;
	}

	@Override
	//ҵ���ʵ�ַ�ҳ��ѯ�Ĺ���
	public PageBean<Employee> findByPage(Integer currPage) {
		PageBean<Employee> pageBean = new PageBean<Employee>();
		//��װ��ǰҳ��
		pageBean.setCurrPage(currPage);
		//��װÿҳ��ʾ�ļ�¼��
		int pageSize = 3;	//ÿҳ��ʾ3��
		pageBean.setPageSize(pageSize);
		//��װ�ܼ�¼��
		int totalCount = employeeDao.findCount();
		pageBean.setTotalCount(totalCount);
		//��װ��ҳ��
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);	//����ȡ��
		pageBean.setTotalPage(num.intValue());
		//��װÿҳ��ʾ������
		int begin = (currPage - 1)*pageSize;
		List<Employee> list = employeeDao.findByPage(begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	//ҵ���ʵ��Ա���ı��淽��
	public void save(Employee employee) {
		// TODO Auto-generated method stub
		employeeDao.save(employee);
	}

	@Override
	//ҵ������Ա��id��ѯԱ���ķ���
	public Employee findById(Integer eid) {
		return employeeDao.findById(eid);
	}

	@Override
	public void update(Employee employee) {
		employeeDao.update(employee);
	}

	@Override
	//ҵ���ɾ��Ա���ķ���
	public void delete(Employee employee) {
		employeeDao.delete(employee);
	}

}
