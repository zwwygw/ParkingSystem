/**
* <p>Title: MemberDao.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2018年5月9日<／p>
* <p>Company: CSU<／p>
* @author ZWW
* @date 2018年5月9日
* @version 1.0
*/
package pers.dao;

import pers.table.Member;

/**
 * @ClassName MemberDao
 * @Description TODO
 * @author ZWW
 * @date   2018年5月9日上午2:11:33
 */
public interface MemberDao {
       public boolean add(Member member);
       public String checkM(String pnum);
       public String getId();
}
