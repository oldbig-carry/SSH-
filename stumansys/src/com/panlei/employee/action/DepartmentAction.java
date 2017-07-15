package com.panlei.employee.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.panlei.employee.domain.Department;
import com.panlei.employee.domain.PageBean;
import com.panlei.employee.service.DepartmentService;

/**
 * ���Ź����Action��
 * ����ģ������ModelDriven<Department>����ҳ��Ĳ���,�����Ǳ��ύ�����ݣ�Ҳ������ĳ������ֵ
 * Ȼ�����service��
 */
public class DepartmentAction extends ActionSupport implements ModelDriven<Department>{

	//ģ������ʹ�õĶ���
	private Department department = new Department();
	@Override
	public Department getModel() {
		return department;
	}
	//ע�벿�Ź����service
	private DepartmentService departmentService;
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	//��ҳ����
	private Integer currPage=1;
	
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	//�ṩ��ѯ�ķ���
	public String findAll() {
		PageBean<Department> pageBean = departmentService.findByPage(currPage);
		//��pageBean����ֵջ��
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	//��Ӳ��ŵķ���
	public String saveUI() {
		return "saveUI";
	}
	
	//��Ӳ���ִ�еķ���
	public String save() {
		departmentService.save(department);
		return "saveSuccess";
	}
	
	//�༭���ŵķ���
	public String edit() {
		department = departmentService.findById(department.getDid());
		return "editSuccess";
	}
	
	//�޸Ĳ���ִ�еķ���
	public String update() {
		departmentService.update(department);
		return "updateSuccess";
	}
	
	//ɾ�����ŵķ���
	public String delete() {
		//ɾ��֮ǰ�Ȳ�ѯ�Ƿ��иò���
		department = departmentService.findById(department.getDid());
		departmentService.delete(department);
		return "deleteSuccess";
	}
	
}
