package ui;

import data.ConnOra;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

import dao.ManagerDao;
import dao.imp.ManagerDaoImp;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

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
		frame.setBounds(100, 100, 416, 320);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		passwordField1_1 = new JPasswordField();
		passwordField1_1.setFont(new Font("黑体", Font.PLAIN, 10));
		passwordField1_1.setBounds(138, 146, 150, 24);
		frame.getContentPane().add(passwordField1_1);

		textField1_1 = new JTextField();
		textField1_1.setFont(new Font("黑体", Font.PLAIN, 18));
		textField1_1.setBounds(138, 84, 150, 24);
		frame.getContentPane().add(textField1_1);
		textField1_1.setColumns(10);

		JButton button1_1 = new JButton("登录");
		button1_1.setFont(new Font("黑体", Font.PLAIN, 17));
		button1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
					new ManagerUI(textField1_1.getText(),power).setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "密码或账号错误！", "提示", JOptionPane.ERROR_MESSAGE);
					passwordField1_1.setText("");
				}
			}
		});
		button1_1.setBounds(157, 206, 97, 27);
		frame.getContentPane().add(button1_1);
		
		JLabel label1_1 = new JLabel("工号：");
		label1_1.setFont(new Font("黑体", Font.PLAIN, 18));
		label1_1.setBounds(65, 85, 72, 18);
		frame.getContentPane().add(label1_1);
		
		JLabel label1_2 = new JLabel("密码：");
		label1_2.setFont(new Font("黑体", Font.PLAIN, 18));
		label1_2.setBounds(65, 147, 72, 18);
		frame.getContentPane().add(label1_2);
	}
}
