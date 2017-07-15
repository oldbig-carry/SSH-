package com.panlei.employee.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.panlei.employee.dao.EmployeeDao;
import com.panlei.employee.domain.Department;
import com.panlei.employee.domain.Employee;
import com.panlei.employee.util.HibernateUtil;
/**
 * Ա�������DAO��ʵ��
 */
public class EmployeeDaoImpl implements EmployeeDao {
	
	private HibernateUtil hibernateUtil;
	private Session session;
	
	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}

	//DAO�и����û����������ѯ�û��ķ���
	@Override
	public Employee findByUsernameAndPassword(Employee employee) {
		String hql = "from Employee where username=? and password=?";
		session = hibernateUtil.getSession();
		Query query = session.createQuery(hql);
		//������λ�ð󶨲�ѯ����
		query.setParameter(0, employee.getUsername());
		query.setParameter(1, employee.getPassword());
		List<Employee> lists = query.list(); 
		if(lists.size() > 0) {
			return lists.get(0);
		}
		return null;
	}

	@Override
	//��ѯ�ܵ���Ŀ
	public int findCount() {
		String hql = "select count(*) from Employee";
		session = hibernateUtil.getSession();
		Query query = session.createQuery(hql);
		int count = ((Long) query.setCacheable(true).uniqueResult()).intValue();
		if(count > 0 ) {
			//System.out.println(count);
			return count;
		}
		hibernateUtil.closeSession(session);
		return 0;
	}

	@Override
	//��ҳ��ѯÿҳ��ʾ������
	public List<Employee> findByPage(int begin, int pageSize) {
		String hql = "from Employee";
		session = hibernateUtil.getSession();
		Query query = session.createQuery(hql);
		//������λ�ð󶨲�ѯ����
		System.out.println(begin);
		System.out.println(pageSize);
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Employee> list = query.list(); 
		hibernateUtil.closeSession(session);
		return list;
	}

	@Override
	//DAO��ʵ�ֱ���Ա���ķ���
	public void save(Employee employee) {
		// TODO Auto-generated method stub
		session = hibernateUtil.getSession();
		session.save(employee);
		hibernateUtil.closeSession(session);
	}

	@Override
	//DAO�и���Ա��id��ѯԱ��
	public Employee findById(Integer eid) {
		session = hibernateUtil.getSession();
		Employee employee = (Employee) session.get(Employee.class, eid);
		hibernateUtil.closeSession(session);
		return employee;
	}

	@Override
	//DAO���޸�Ա����Ϣ�ķ���
	public void update(Employee employee) {
		session = hibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.update(employee);
		transaction.commit();
		hibernateUtil.closeSession(session);
	}

	@Override
	//DAO��ɾ��Ա���ķ���
	public void delete(Employee employee) {
		session = hibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(employee);
		transaction.commit(); 
		hibernateUtil.closeSession(session);
	}
	
}
