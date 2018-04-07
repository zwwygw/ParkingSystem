/**
* <p>Title: MemberFeeDao.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2018年4月4日<／p>
* <p>Company: CSU<／p>
* @author ZWW
* @date 2018年4月4日
* @version 1.0
*/
package dao;

/**
 * @ClassName MemberFeeDao
 * @Description 会员收费表的数据操纵接口
 * @author ZWW
 * @date   2018年4月4日下午2:57:53
 */
public interface MemberFeeDao {
	
	/**
	 * @Description 增加流水
	 * @param pnum //车牌号
	 * @param id //车位编号
	 * @return boolean //增加成功与否
	 */
	public boolean add(String pnum,String id);
	
}
