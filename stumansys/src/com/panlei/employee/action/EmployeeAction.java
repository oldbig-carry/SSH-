package com.panlei.employee.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.panlei.employee.domain.Department;
import com.panlei.employee.domain.Employee;
import com.panlei.employee.domain.PageBean;
import com.panlei.employee.service.DepartmentService;
import com.panlei.employee.service.EmployeeService;

/**
 * Ա�������Action��
 * ����ģ����������ҳ��Ĳ���
 * Ȼ�����service��
 */
public class EmployeeAction extends ActionSupport implements ModelDriven<Employee>{
	//�����employee��ֱ�ӱ�ҳ�洫������ֵ��ֵ
	private Employee employee = new Employee();
	@Override
	public Employee getModel() {
		return employee;
	}
	
	//ע��ҵ�����
	private EmployeeService employeeService;
	private DepartmentService departmentService;
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	/**
	 * ��½ִ�еķ���
	 */
	public String login() {
		System.out.println("loginִ����...");
		//����ҵ������
		Employee existEmployee = employeeService.login(employee);
		if(existEmployee == null) {
			//��½ʧ��
			this.addActionError("�û������������");
			return INPUT;
		} else{
			//��½�ɹ��ͽ��û���Ϣ����session����
			ActionContext.getContext().getSession().put("existEmployee", existEmployee);
			return SUCCESS;
		}
	}
	
	//��ҳ����
	private Integer currPage=1;
		
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	//�ṩ��ѯ�ķ���
	public String findAll() {
		PageBean<Employee> pageBean = employeeService.findByPage(currPage);
		//��pageBean����ֵջ��   ������push
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	//���Ա���ķ���
	//������Ҫչʾ���еĲ�������Ա��ѡ��������Ҫ����DepartmentService
	public String saveUI() {
		//��ѯ���в���
		List<Department> list = departmentService.findAll();
		//��list����ֵջ�У�������set
		ActionContext.getContext().getValueStack().set("list",list);
		return "saveUI";
	}
	
	//���Ա���ķ���
	public String save() {
		employeeService.save(employee);
		return "saveSuccess";
	}

	//�༭Ա���ķ���
	public String edit() {
		//����Ա��id��ѯԱ��
		employee = employeeService.findById(employee.getEid());
		//��ѯ���в��ż���
		List<Department> list = departmentService.findAll();
		//��list����ֵջ�У�������set
		ActionContext.getContext().getValueStack().set("list",list);
		return "editSuccess";
	}
	
	//�޸�Ա����Ϣ�ķ���
	public String update() {
		employeeService.update(employee);
		return "updateSuccess";
	}
	
	//ɾ��Ա���ķ���
	public String delete() {
		//ɾ��֮ǰ�Ȳ�ѯ�Ƿ��иò���
		employee = employeeService.findById(employee.getEid());
		employeeService.delete(employee);
		return "deleteSuccess";
	}
}
