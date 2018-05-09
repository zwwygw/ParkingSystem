/**  
 *                    _ooOoo_  
 *                   o8888888o  
 *                   88" . "88  
 *                   (| -_- |)  
 *                    O\ = /O  
 *                ____/`---'\____  
 *              .   ' \\| |// `.  
 *               / \\||| : |||// \  
 *             / _||||| -:- |||||- \  
 *               | | \\\ - /// | |  
 *             | \_| ''\---/'' | |  
 *              \ .-\__ `-` ___/-. /  
 *           ___`. .' /--.--\ `. . __  
 *        ."" '< `.___\_<|>_/___.' >'"".  
 *       | | : `- \`.;`\ _ /`;.`/ - ` : | |  
 *         \ \ `-. \_ __\ /__ _/ .-` / /  
 * ======`-.____`-.___\_____/___.-`____.-'======  
 *                    `=---='  
 *  
 * .............................................  
 *          佛祖保佑             永无BUG  
 */  
package pers.ui;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import pers.dao.InforParkingDao;
import pers.dao.ManagerDao;
import pers.dao.imp.InforParkingDaoImp;
import pers.dao.imp.ManagerDaoImp;
import pers.table.InforParking;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login {

	private JFrame frame;
	private JPasswordField passwordField1_1;
	private JTextField textField1_1;
	private static Login window;
	/**
	 * Launch the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(javax.swing.plaf.nimbus.NimbusLookAndFeel.class.getName());
					window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		frame = new JFrame();
		frame.setTitle("停车场管理系统登录");
		frame.setBounds(100, 100, 448, 343);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		passwordField1_1 = new JPasswordField();
		passwordField1_1.setFont(new Font("黑体", Font.PLAIN, 20));
		passwordField1_1.setBounds(138, 157, 150, 39);
		frame.getContentPane().add(passwordField1_1);

		textField1_1 = new JTextField();
		textField1_1.setFont(new Font("黑体", Font.PLAIN, 18));
		textField1_1.setBounds(138, 93, 150, 39);
		frame.getContentPane().add(textField1_1);
		textField1_1.setColumns(10);

		JButton button1_1 = new JButton("登录");
		button1_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()== KeyEvent.VK_ENTER) {
					loginF();
				}
			}
		});
		button1_1.setFont(new Font("黑体", Font.PLAIN, 20));
		button1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loginF();
			}
		});
		button1_1.setBounds(152, 224, 112, 39);
		frame.getContentPane().add(button1_1);
		
		JLabel label1_1 = new JLabel("工号：");
		label1_1.setFont(new Font("黑体", Font.PLAIN, 20));
		label1_1.setBounds(65, 94, 72, 36);
		frame.getContentPane().add(label1_1);
		
		JLabel label1_2 = new JLabel("密码：");
		label1_2.setFont(new Font("黑体", Font.PLAIN, 20));
		label1_2.setBounds(65, 157, 72, 39);
		frame.getContentPane().add(label1_2);
	}
	
	private void loginF() {
		boolean flag  = false;
		int     power = 0;
		String inputPassword = String.valueOf(passwordField1_1.getPassword());
	    // System.out.println(passwordField.getPassword().toString());
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ManagerDao mDao = null;
		mDao = new ManagerDaoImp();
		hashMap = mDao.check(textField1_1.getText(), inputPassword);
		flag = (boolean) hashMap.get("flag");
		power = (int) hashMap.get("power");
		if (flag == true) {
			window.frame.dispose();
			String mid = textField1_1.getText();
			ManagerUI mUi = null;
			mUi = new ManagerUI(mid,power);
			
			InforParking iParking0 = null;
			InforParkingDao iDao = new InforParkingDaoImp();
			java.util.Date  date0=new java.util.Date();
			java.sql.Date  data1=new java.sql.Date(date0.getTime());
			iParking0 = iDao.getIOP(data1);
			ManagerUI.ennum = iParking0.getEn_num();
			ManagerUI.exnum = iParking0.getEx_num();
			ManagerUI.fee   = iParking0.getFee();
			
			System.out.println(Integer.toString(ManagerUI.exnum)); 
			InforParking iParking = new InforParking(ManagerUI.ennum,ManagerUI.exnum,ManagerUI.fee,mid);
		//	System.out.println(ManagerUI.fee); 
			iDao.inUpInfor(iParking);
			System.out.println("初始化成功!"); 
			mUi.setVisible(true);
		}else {
			JOptionPane.showMessageDialog(null, "密码或账号错误！", "提示", JOptionPane.ERROR_MESSAGE);
			passwordField1_1.setText("");
		}
	}
}
