/**
* <p>Title: MemberFeeDao.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2018年4月4日<／p>
* <p>Company: CSU<／p>
* @author ZWW
* @date 2018年4月4日
* @version 1.0
*/
package pers.dao;

import pers.table.MemberFee;

/**
 * @ClassName MemberFeeDao
 * @Description 会员收费表的数据操纵接口
 * @author ZWW
 * @date   2018年4月4日下午2:57:53
 */
public interface MemberFeeDao {
	/**
	 * @Description 增加会员流水
	 * @param mFee
	 * @return boolean
	 */
	public int add(MemberFee mFee);

}
