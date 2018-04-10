package pers.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import pers.dao.ParkingDao;
import pers.dao.TemporaryFeeDao;
import pers.dao.imp.ParkingDaoImp;
import pers.dao.imp.TemporaryDaoImp;
import pers.table.InforParking;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import java.awt.CardLayout;


import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JScrollPane;


public class ManagerUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int ennum = 1;
	public static int exnum = 1;
	public static float fee = 0;
	public InforParking iParking = null;
	private JPanel contentPane;
	private static InforPanel inforPanel;
	private static boolean flag = false;//判断客户面板是否打开
	//控制车辆进出界面
	private JTextField textField1_1;//获取车牌号
	private JTextField textField1_2;//获取车位
	//增加会员
	private JTextField textField2_1;//得到会员名
	private JTextField textField2_2;//得到车牌号
	private JTextField textField2_3;//联系方式
	private JTextField textField2_4;//车位编号
	//增删员工
	private JTextField textField3_1;//工号
	private JPasswordField passwordField3_1;//密码
	private JPasswordField passwordField3_2;//确认密码
	private JTextField textField3_2;//姓名
	//修改员工权限
	private JTextField textField4_1;//员工号
	private DefaultTableModel dtm;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerUI frame = new ManagerUI("fds",0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ManagerUI(String string, int power) {
		try {
			UIManager.setLookAndFeel(javax.swing.plaf.nimbus.NimbusLookAndFeel.class.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
        
	//	iParking = new InforParking(power, power, power, m_id)
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 536, 26);
		contentPane.add(menuBar);

		JMenu menu1 = new JMenu("菜单");
		menuBar.add(menu1);
        
		JMenuItem menuItem1_1 = new JMenuItem("打开客户面板");
		menuItem1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (!isFlag()) {
					ParkingDao pDao = null;
					pDao = new ParkingDaoImp();
					inforPanel = new InforPanel(string, pDao.parkingNum("临时"));
					setFlag(true);
					inforPanel.setVisible(isFlag());
					JOptionPane.showMessageDialog(null, "客户面板已打开", "提示", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "客户面板已打开", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		menu1.add(menuItem1_1);
		
		JMenuItem mntmItem1_2 = new JMenuItem("车辆进出");	
		menu1.add(mntmItem1_2);
		
		JMenuItem menuItem1_3 = new JMenuItem("增加会员");		
		menu1.add(menuItem1_3);
		
		JMenuItem menuItem1_4 = new JMenuItem("增减车位");
		menu1.add(menuItem1_4);
			
		Panel panel1 = new Panel(); //
		panel1.setBounds(0, 26, 536, 298);
		contentPane.add(panel1);
		
		CardLayout cl_panel1 = new CardLayout();
		panel1.setLayout(cl_panel1);
		
		JPanel panel1_1 = new JPanel();//处理增加会员的界面
		panel1.add(panel1_1, "p1_1");
		panel1_1.setLayout(null);
		
		textField1_1 = new JTextField();
		textField1_1.setFont(new Font("宋体", Font.PLAIN, 30));
		textField1_1.setBounds(170, 63, 257, 54);
		panel1_1.add(textField1_1);
		textField1_1.setColumns(10);
		
		textField1_2 = new JTextField();
		textField1_2.setFont(new Font("宋体", Font.PLAIN, 30));
		textField1_2.setColumns(10);
		textField1_2.setBounds(170, 130, 257, 54);
		panel1_1.add(textField1_2);
		
		JLabel label1_1 = new JLabel("车牌号：");
		label1_1.setFont(new Font("宋体", Font.PLAIN, 30));
		label1_1.setBounds(25, 63, 144, 54);
		panel1_1.add(label1_1);
		
		JLabel label1_2 = new JLabel("车  位：");
		label1_2.setFont(new Font("宋体", Font.PLAIN, 30));
		label1_2.setBounds(25, 130, 144, 54);
		panel1_1.add(label1_2);
		
		JButton button1_1 = new JButton("进  入");
		button1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TemporaryFeeDao tDao = new TemporaryDaoImp();
				tDao.add(textField1_1.getText(), textField1_2.getText());
			}
		});
		button1_1.setFont(new Font("宋体", Font.PLAIN, 30));
		button1_1.setBounds(63, 232, 144, 37);
		panel1_1.add(button1_1);
		
		JButton button1_2 = new JButton("离  开");
		button1_2.setFont(new Font("宋体", Font.PLAIN, 30));
		button1_2.setBounds(332, 232, 144, 37);
		panel1_1.add(button1_2);
		
		JPanel panel1_2 = new JPanel();
		panel1_2.setLayout(null);
		panel1.add(panel1_2, "p1_2");
		
		JLabel label_2 = new JLabel("车位编号");
		label_2.setBounds(94, 136, 72, 18);
		panel1_2.add(label_2);
		
		JLabel label_3 = new JLabel("车牌号");
		label_3.setBounds(94, 68, 72, 18);
		panel1_2.add(label_3);
		
		JLabel label_4 = new JLabel("会员名");
		label_4.setBounds(94, 37, 72, 18);
		panel1_2.add(label_4);
		
		JLabel label_5 = new JLabel("联系方式");
		label_5.setBounds(94, 99, 72, 18);
		panel1_2.add(label_5);
		
		JLabel label_6 = new JLabel("有效期");
		label_6.setBounds(94, 179, 72, 18);
		panel1_2.add(label_6);
		
		textField2_1 = new JTextField();
		textField2_1.setColumns(10);
		textField2_1.setBounds(190, 34, 86, 24);
		panel1_2.add(textField2_1);
		
		textField2_2 = new JTextField();
		textField2_2.setColumns(10);
		textField2_2.setBounds(190, 65, 86, 24);
		panel1_2.add(textField2_2);
		
		textField2_3 = new JTextField();
		textField2_3.setColumns(10);
		textField2_3.setBounds(190, 96, 86, 24);
		panel1_2.add(textField2_3);
		
		textField2_4 = new JTextField();
		textField2_4.setColumns(10);
		textField2_4.setBounds(190, 133, 86, 24);
		panel1_2.add(textField2_4);
		
		JComboBox comboBox2_1 = new JComboBox();
		comboBox2_1.setEditable(true);
		comboBox2_1.setBounds(190, 176, 86, 24);
		panel1_2.add(comboBox2_1);
		
		JButton button2_1 = new JButton("添加");
		button2_1.setBounds(151, 231, 113, 27);
		panel1_2.add(button2_1);
		
		//if(power == 0) {
			JMenu menu2 = new JMenu("管理员工");
			menuBar.add(menu2);
			
			JMenuItem menuItem2_1 = new JMenuItem("增删员工");
			menu2.add(menuItem2_1);
			JPanel panel1_3 = new JPanel();
			
			panel1.add(panel1_3, "p1_3");
			panel1_3.setLayout(null);
			
			JLabel label3_1 = new JLabel("添加工号：");
			label3_1.setBounds(89, 47, 87, 18);
			panel1_3.add(label3_1);
			
			JLabel label3_2 = new JLabel("输入密码：");
			label3_2.setBounds(89, 86, 87, 18);
			panel1_3.add(label3_2);
			
			JLabel label3_3 = new JLabel("确认密码：");
			label3_3.setBounds(89, 117, 87, 18);
			panel1_3.add(label3_3);
			
			JLabel label3_4 = new JLabel("员工姓名：");
			label3_4.setBounds(89, 155, 87, 18);
			panel1_3.add(label3_4);
			
			JLabel label3_5 = new JLabel("设置权限：");
			label3_5.setBounds(89, 198, 87, 18);
			panel1_3.add(label3_5);
			
			textField3_1 = new JTextField();
			textField3_1.setBounds(190, 44, 86, 24);
			panel1_3.add(textField3_1);
			textField3_1.setColumns(10);
			
			passwordField3_1 = new JPasswordField();
			passwordField3_1.setBounds(190, 83, 86, 24);
			panel1_3.add(passwordField3_1);
			
			passwordField3_2 = new JPasswordField();
			passwordField3_2.setBounds(190, 117, 86, 24);
			panel1_3.add(passwordField3_2);
			
			textField3_2 = new JTextField();
			textField3_2.setBounds(190, 155, 86, 24);
			panel1_3.add(textField3_2);
			textField3_2.setColumns(10);
			
			JComboBox comboBox3_1 = new JComboBox();
			comboBox3_1.setBounds(190, 195, 86, 24);
			panel1_3.add(comboBox3_1);
			
			JButton button3_1 = new JButton("添加");
			button3_1.setBounds(89, 246, 113, 27);
			panel1_3.add(button3_1);
			
			JButton button3_2 = new JButton("删除");
			button3_2.setBounds(216, 246, 113, 27);
			panel1_3.add(button3_2);
			
	//	}
		
		JPanel panel1_4 = new JPanel();
		panel1.add(panel1_4, "p1_4");
		panel1_4.setLayout(null);
		
		JLabel label4_1 = new JLabel("员工编号：");
		label4_1.setBounds(80, 63, 87, 18);
		panel1_4.add(label4_1);
		
		JLabel label4_2 = new JLabel("员工权限：");
		label4_2.setBounds(80, 108, 87, 18);
		panel1_4.add(label4_2);
		
		textField4_1 = new JTextField();
		textField4_1.setBounds(170, 60, 86, 24);
		panel1_4.add(textField4_1);
		textField4_1.setColumns(10);
		
		JComboBox comboBox4_1 = new JComboBox();
		comboBox4_1.setBounds(170, 105, 86, 24);
		panel1_4.add(comboBox4_1);
		
		JButton button4_1 = new JButton("修改");
		button4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button4_1.setBounds(80, 167, 113, 27);
		panel1_4.add(button4_1);
		
		JPanel panel1_5 = new JPanel();
		panel1.add(panel1_5, "p1_5");
		panel1_5.setLayout(null);
		
		JLabel label = new JLabel("车位编号：");
		label.setBounds(102, 53, 101, 18);
		panel1_5.add(label);
		
		JLabel lblNewLabel = new JLabel("临时单价：");
		lblNewLabel.setBounds(102, 97, 85, 18);
		panel1_5.add(lblNewLabel);
		
		JLabel label_1 = new JLabel("会员单价：");
		label_1.setBounds(102, 140, 84, 18);
		panel1_5.add(label_1);
		
		JPanel panel1_6 = new JPanel();
		panel1.add(panel1_6, "p1_6");
		panel1_6.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 534, 249);
		panel1_6.add(scrollPane);
		
		Object[] title = { "车位编号", "状态", "类型", "临时单价", "会员单价","修改价格" };
        dtm = new DefaultTableModel(title, 0);
		
		table = new JTable(dtm);
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("上一页");
		button.setBounds(93, 262, 113, 27);
		panel1_6.add(button);
		
		JButton button_1 = new JButton("下一页");
		button_1.setBounds(289, 262, 113, 27);
		panel1_6.add(button_1);
		
				//切换处理车辆进出
		mntmItem1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl_panel1.show(panel1, "p1_1");
			}
		});
		//切换处理增加会员
		menuItem1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_panel1.show(panel1, "p1_2");
			}
		});
		//切换处理增减车位
		menuItem1_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_panel1.show(panel1, "p1_4");
			}
		});
		
	}

	public static boolean isFlag() {
		return flag;
	}

	public static void setFlag(boolean flag) {
		ManagerUI.flag = flag;
	}

	public static int getEnnum() {
		return ennum;
	}

	public static void setEnnum(int ennum) {
		ManagerUI.ennum = ennum;
	}

	public static int getExnum() {
		return exnum;
	}

	public static void setExnum(int exnum) {
		ManagerUI.exnum = exnum;
	}

	public static float getFee() {
		return fee;
	}

	public static void setFee(float fee) {
		ManagerUI.fee = fee;
	}
}
