package pers.dao;

import java.util.HashMap;

import pers.table.Manager;
/**
 * @ClassName ManagerDao
 * @Description 对管理员的数据操作的接口
 * @author ZWW
 * @date   2018年3月30日下午10:47:01
 */
public interface ManagerDao {
	/**
	 * @Description:校验登录信息并返回登录人员是否能登录以及其权限
	 * @param tf
	 * @param pf
	 * @return HashMap<String,Object>
	 */
	public HashMap<String, Object> check(String tf, String pf);
	/**
	 * @Description 判断权限
	 * @param tf
	 * @return int
	 */
	public int judgeP(String tf);
	/**
	 * @Description 增加员工
	 * @param manager
	 * @return boolean
	 */
	public boolean add(Manager manager);
	/**
	 * @Description 删除员工
	 * @param id
	 * @return boolean
	 */
	public boolean delManager(String id);
}
