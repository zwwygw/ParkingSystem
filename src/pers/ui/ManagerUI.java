package pers.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import pers.dao.ManagerDao;
import pers.dao.MemberDao;
import pers.dao.MemberFeeDao;
import pers.dao.ParkingDao;
import pers.dao.TemporaryFeeDao;
import pers.dao.imp.ManagerDaoImp;
import pers.dao.imp.MemberDaoImp;
import pers.dao.imp.MemberFeeDaoImp;
import pers.dao.imp.ParkingDaoImp;
import pers.dao.imp.TemporaryDaoImp;
import pers.table.InforParking;
import pers.table.Manager;
import pers.table.Parking;
import pers.table.TemporaryFee;
import pers.table.Member;
import pers.table.MemberFee;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

	private static final long serialVersionUID = 1L;
	public static int ennum = 1;
	public static int exnum = 1;
	public static float fee = 0;
	public static String mid = null;
	public InforParking iParking = null;
	private JPanel contentPane;
	private String zhan = "占";
	private String kong = "空";
	private int page = 1;
	private static InforPanel inforPanel;
	private static boolean IPflag = false;// 判断客户面板是否打开
	// 控制车辆进出界面
	private JTextField textField1_1;// 获取车牌号
	private JTextField textField1_2;// 获取车位
	// 增加会员
	private JTextField textField2_1;// 得到会员名
	private JTextField textField2_2;// 得到车牌号
	private JTextField textField2_3;// 联系方式
	private JTextField textField2_4;// 车位编号
	// 增删员工
	private JTextField textField3_1;// 工号
	private JPasswordField passwordField3_1;// 密码
	private JPasswordField passwordField3_2;// 确认密码
	private JTextField textField3_2;// 姓名
	// 修改员工权限
	private JTextField textField4_1;// 员工号
	private DefaultTableModel dtm;
	private JTable table;
	private JTextField textField5_1;
	private JTextField textField5_2;
	private JTextField textField5_3;
	private JTextField textField2_5;
	private JTextField textField2_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerUI frame = new ManagerUI("fds", 0);
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
	public ManagerUI(String m_id, int power) {
		mid = m_id;
		try {
			UIManager.setLookAndFeel(javax.swing.plaf.nimbus.NimbusLookAndFeel.class.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// iParking = new InforParking(power, power, power, m_id)
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 655, 26);
		contentPane.add(menuBar);

		JMenu menu1 = new JMenu("菜单");
		menuBar.add(menu1);

		JMenuItem menuItem1_1 = new JMenuItem("打开客户面板");
		menuItem1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!isIPFlag()) {
					int num = 0;
					ParkingDao pDao = null;
					pDao = new ParkingDaoImp();
					num = pDao.parkingNullNum("临时");
					// System.out.println(num);
					inforPanel = new InforPanel(mid, num);
					setIPFlag(true);
					inforPanel.setVisible(isIPFlag());
					Thread thread1 = new Thread(inforPanel);
					thread1.start();
					JOptionPane.showMessageDialog(null, "客户面板已打开", "提示", JOptionPane.INFORMATION_MESSAGE);
				} else {
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

		JMenuItem menuItem1_5 = new JMenuItem("查看流水");
		menu1.add(menuItem1_5);

		Panel panel1 = new Panel(); //
		panel1.setBounds(0, 26, 655, 317);
		contentPane.add(panel1);

		CardLayout cl_panel1 = new CardLayout();
		panel1.setLayout(cl_panel1);

		JPanel panel1_1 = new JPanel();// 处理增加会员的界面
		panel1.add(panel1_1, "p1_1");
		panel1_1.setLayout(null);

		textField1_1 = new JTextField();
		textField1_1.setFont(new Font("宋体", Font.PLAIN, 30));
		textField1_1.setBounds(274, 64, 257, 54);
		panel1_1.add(textField1_1);
		textField1_1.setColumns(10);

		textField1_2 = new JTextField();
		textField1_2.setFont(new Font("宋体", Font.PLAIN, 30));
		textField1_2.setColumns(10);
		textField1_2.setBounds(274, 130, 257, 54);
		panel1_1.add(textField1_2);

		JLabel label1_1 = new JLabel("车牌号：");
		label1_1.setFont(new Font("宋体", Font.PLAIN, 30));
		label1_1.setBounds(120, 63, 144, 54);
		panel1_1.add(label1_1);

		JLabel label1_2 = new JLabel("车  位：");
		label1_2.setFont(new Font("宋体", Font.PLAIN, 30));
		label1_2.setBounds(120, 129, 144, 54);
		panel1_1.add(label1_2);

		JButton button1_1 = new JButton("进  入");
		button1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TemporaryFeeDao tDao = new TemporaryDaoImp();
				MemberDao mDao = new MemberDaoImp();
				ParkingDao pDao = new ParkingDaoImp();
				String pnum = textField1_1.getText();
				String id0 = mDao.checkM(pnum);
				if (!id0.equals("")) {
					if (tDao.add(pnum, id0)) {
						pDao.upTPS(zhan, id0);
						// inforPanel.label_3.setText(Integer.toString(pDao.parkingNullNum("all")));
						if (IPflag == true) {
							InforPanel.label_3.setText(Integer.toString(pDao.parkingNullNum("all")));
							JOptionPane.showMessageDialog(null, "车辆允许进入！", "提示", JOptionPane.INFORMATION_MESSAGE);
							textField1_1.setText("");
							textField1_2.setText("");

						}
					} else {
						JOptionPane.showMessageDialog(null, "错误！", "警告", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					if (!textField1_2.getText().equals("")) {
						String id = textField1_2.getText();
						if (pDao.checkState(id)) {
							if (tDao.add(pnum, id)) {
								pDao.upTPS(zhan, id);
								// inforPanel.label_3.setText(Integer.toString(pDao.parkingNullNum("all")));
								if (IPflag == true) {
									InforPanel.label_3.setText(Integer.toString(pDao.parkingNullNum("all")));
									JOptionPane.showMessageDialog(null, "车辆允许进入！", "提示",
											JOptionPane.INFORMATION_MESSAGE);
									textField1_1.setText("");
									textField1_2.setText("");

								}
							} else {
								JOptionPane.showMessageDialog(null, "错误！", "警告", JOptionPane.WARNING_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "车位被占！", "警告", JOptionPane.WARNING_MESSAGE);
						}

					} else {
						String id = pDao.getPId(kong);
						System.out.println("id " + id);
						if (tDao.add(pnum, id)) {
							pDao.upTPS(zhan, id);

							// inforPanel.label_3.setText(Integer.toString(pDao.parkingNullNum("all")));
							if (IPflag == true) {
								InforPanel.label_3.setText(Integer.toString(pDao.parkingNullNum("临时")));
								JOptionPane.showMessageDialog(null, "车辆运行进入！", "提示", JOptionPane.INFORMATION_MESSAGE);
								textField1_1.setText("");
								textField1_2.setText("");
							}
						} else {
							JOptionPane.showMessageDialog(null, "错误", "警告", JOptionPane.WARNING_MESSAGE);
						}
					}
				}

			}
		});
		button1_1.setFont(new Font("宋体", Font.PLAIN, 30));
		button1_1.setBounds(120, 232, 144, 37);
		panel1_1.add(button1_1);

		JButton button1_2 = new JButton("离  开");
		button1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MemberDao mDao = new MemberDaoImp();
				TemporaryFeeDao tDao = new TemporaryDaoImp();
				ParkingDao pDao = new ParkingDaoImp();
				String pnum = textField1_1.getText();
				String id0 = mDao.checkM(pnum);
				float price = 0;
				boolean flag = false;
				String id = tDao.getPId(pnum);
				if (!id0.equals("")) {
					price = 0;
				} else {
					price = pDao.getPrice(id, 0);
				}
				float tfee = tDao.comp(pnum, mid, price);
				if (tfee>-1) {
					pDao.upTPS(kong, id);
					InforPanel.label_3.setText(Integer.toString(pDao.parkingNullNum("临时")));
					JOptionPane.showMessageDialog(null, "收费"+tfee+"元", "警告", JOptionPane.INFORMATION_MESSAGE);
					textField1_1.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "错误！", "警告", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		button1_2.setFont(new Font("宋体", Font.PLAIN, 30));
		button1_2.setBounds(355, 232, 144, 37);
		panel1_1.add(button1_2);

		JPanel panel1_2 = new JPanel();
		panel1_2.setLayout(null);
		panel1.add(panel1_2, "p1_2");

		JLabel label_2 = new JLabel("车位编号:");
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		label_2.setBounds(284, 43, 97, 18);
		panel1_2.add(label_2);

		JLabel label_3 = new JLabel("车牌号:");
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		label_3.setBounds(29, 111, 72, 18);
		panel1_2.add(label_3);

		JLabel label_4 = new JLabel("会员名:");
		label_4.setFont(new Font("宋体", Font.PLAIN, 20));
		label_4.setBounds(29, 37, 113, 30);
		panel1_2.add(label_4);

		JLabel label_5 = new JLabel("联系方式:");
		label_5.setFont(new Font("宋体", Font.PLAIN, 20));
		label_5.setBounds(29, 172, 102, 18);
		panel1_2.add(label_5);

		JLabel label_6 = new JLabel("生效期:");
		label_6.setFont(new Font("宋体", Font.PLAIN, 20));
		label_6.setBounds(284, 111, 72, 18);
		panel1_2.add(label_6);

		textField2_1 = new JTextField();
		textField2_1.setFont(new Font("宋体", Font.PLAIN, 20));
		textField2_1.setColumns(10);
		textField2_1.setBounds(122, 40, 86, 24);
		panel1_2.add(textField2_1);

		textField2_2 = new JTextField();
		textField2_2.setFont(new Font("宋体", Font.PLAIN, 20));
		textField2_2.setColumns(10);
		textField2_2.setBounds(122, 108, 86, 24);
		panel1_2.add(textField2_2);

		textField2_3 = new JTextField();
		textField2_3.setFont(new Font("宋体", Font.PLAIN, 20));
		textField2_3.setColumns(10);
		textField2_3.setBounds(122, 171, 86, 24);
		panel1_2.add(textField2_3);

		textField2_4 = new JTextField();
		textField2_4.setFont(new Font("宋体", Font.PLAIN, 20));
		textField2_4.setColumns(10);
		textField2_4.setBounds(388, 37, 86, 24);
		panel1_2.add(textField2_4);

		JButton button2_1 = new JButton("添加");
		button2_1.setFont(new Font("宋体", Font.PLAIN, 20));
		button2_1.setBounds(201, 242, 113, 27);
		panel1_2.add(button2_1);

		JLabel label_1 = new JLabel("有效期:");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(284, 174, 72, 18);
		panel1_2.add(label_1);

		textField2_5 = new JTextField();
		textField2_5.setFont(new Font("宋体", Font.PLAIN, 20));
		textField2_5.setBounds(388, 108, 86, 24);

		textField2_6 = new JTextField();
		textField2_6.setFont(new Font("宋体", Font.PLAIN, 20));
		textField2_6.setBounds(388, 172, 86, 24);

		DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
		DateChooser dateChooser2 = DateChooser.getInstance("yyyy-MM-dd");

		dateChooser1.register(textField2_6);
		dateChooser2.register(textField2_5);
		panel1_2.add(textField2_5);
		textField2_5.setColumns(10);
		panel1_2.add(textField2_6);
		textField2_6.setColumns(10);

		button2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField2_1.getText();
				String id = textField2_4.getText();
				String pnum = textField2_2.getText();
				String tel = textField2_3.getText();
				String eff = textField2_5.getText();
				String ex = textField2_6.getText();
				if (name.equals("") || pnum.equals("") || tel.equals("") || eff.equals("") || ex.equals("")) {
					JOptionPane.showMessageDialog(null, "错误！", "警告", JOptionPane.WARNING_MESSAGE);
				} else {
					MemberDao mDao = new MemberDaoImp();
					ParkingDao pDao = new ParkingDaoImp();
					if (id.equals("")) {
						id = mDao.getId();
					} else {
						boolean flag = pDao.checkM(id);
						if (flag) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							try {
								java.util.Date date0;
								date0 = sdf.parse(eff);
								java.sql.Date effDate = new java.sql.Date(date0.getTime());
								java.util.Date date1 = sdf.parse(ex);
								java.sql.Date exDate = new java.sql.Date(date1.getTime());
								Member member = new Member(name, tel, id, pnum);
								MemberFeeDao mFeeDao = new MemberFeeDaoImp();
								MemberFee mFee = new MemberFee(id, mid, pnum, effDate, exDate);
								if (mDao.add(member)) {
									int mfee = mFeeDao.add(mFee);
									if (mfee > 0) {
										JOptionPane.showMessageDialog(null, "申请成功请缴费" + mfee + "元", "提示",
												JOptionPane.INFORMATION_MESSAGE);
										textField2_1.setText("");
										textField2_2.setText("");
										textField2_3.setText("");
										textField2_4.setText("");
										textField2_5.setText("");
										textField2_6.setText("");
									} else {
										JOptionPane.showMessageDialog(null, "错误！", "警告", JOptionPane.WARNING_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null, "错误！", "警告", JOptionPane.WARNING_MESSAGE);
								}
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(null, "错误！", "警告", JOptionPane.WARNING_MESSAGE);
						}
					}

				}
			}
		});

		if (power == 0) {
			JMenu menu2 = new JMenu("管理员工");
			menuBar.add(menu2);

			JMenuItem menuItem2_1 = new JMenuItem("增删员工");
			menu2.add(menuItem2_1);

			JMenuItem menuItem2_2 = new JMenuItem("更改权限");
			menu2.add(menuItem2_2);
			JPanel panel1_3 = new JPanel();

			panel1.add(panel1_3, "p1_3");
			panel1_3.setLayout(null);

			JLabel label3_1 = new JLabel("添加工号：");
			label3_1.setFont(new Font("宋体", Font.PLAIN, 20));
			label3_1.setBounds(45, 42, 101, 18);
			panel1_3.add(label3_1);

			JLabel label3_2 = new JLabel("输入密码：");
			label3_2.setFont(new Font("宋体", Font.PLAIN, 20));
			label3_2.setBounds(45, 100, 101, 18);
			panel1_3.add(label3_2);

			JLabel label3_3 = new JLabel("确认密码：");
			label3_3.setFont(new Font("宋体", Font.PLAIN, 20));
			label3_3.setBounds(45, 158, 101, 18);
			panel1_3.add(label3_3);

			JLabel label3_4 = new JLabel("员工姓名：");
			label3_4.setFont(new Font("宋体", Font.PLAIN, 20));
			label3_4.setBounds(341, 42, 108, 18);
			panel1_3.add(label3_4);

			JLabel label3_5 = new JLabel("设置权限：");
			label3_5.setFont(new Font("宋体", Font.PLAIN, 20));
			label3_5.setBounds(341, 100, 108, 18);
			panel1_3.add(label3_5);

			textField3_1 = new JTextField();
			textField3_1.setFont(new Font("宋体", Font.PLAIN, 18));
			textField3_1.setBounds(160, 34, 119, 35);
			panel1_3.add(textField3_1);
			textField3_1.setColumns(10);

			passwordField3_1 = new JPasswordField();
			passwordField3_1.setFont(new Font("宋体", Font.PLAIN, 18));
			passwordField3_1.setBounds(160, 92, 119, 35);
			panel1_3.add(passwordField3_1);

			passwordField3_2 = new JPasswordField();
			passwordField3_2.setFont(new Font("宋体", Font.PLAIN, 18));
			passwordField3_2.setBounds(160, 150, 119, 35);
			panel1_3.add(passwordField3_2);

			textField3_2 = new JTextField();
			textField3_2.setFont(new Font("宋体", Font.PLAIN, 18));
			textField3_2.setBounds(463, 34, 149, 35);
			panel1_3.add(textField3_2);
			textField3_2.setColumns(10);

			JComboBox<Object> comboBox3_1 = new JComboBox<Object>();
			comboBox3_1.setFont(new Font("宋体", Font.PLAIN, 18));
			comboBox3_1.setBounds(463, 92, 149, 35);
			panel1_3.add(comboBox3_1);
			String[] p = { "超级管理员", "普通管理员" };
			for (String i : p) {
				comboBox3_1.addItem(i);
			}

			JButton button3_1 = new JButton("添加");
			button3_1.setFont(new Font("宋体", Font.PLAIN, 23));
			button3_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String id = textField3_1.getText();
					String pwd1 = String.valueOf(passwordField3_1.getPassword());
					String pwd2 = String.valueOf(passwordField3_2.getPassword());
					String name = textField3_2.getText();
					String power = (String) comboBox3_1.getSelectedItem();
					int p = 0;
					if (power.equals("超级管理员")) {
						p = 0;
					} else {
						p = 1;
					}

					if (pwd1.equals(pwd2)) {
						Manager manager = new Manager(p, id, pwd1, name);
						ManagerDao mDao = new ManagerDaoImp();
						mDao.add(manager);
					} else {
						JOptionPane.showMessageDialog(null, "密码不一致！", "警告", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			button3_1.setBounds(88, 231, 160, 42);
			panel1_3.add(button3_1);

			JButton button3_2 = new JButton("删除");
			button3_2.setFont(new Font("宋体", Font.PLAIN, 23));
			button3_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String id = textField3_1.getText();
					int jp = JOptionPane.showConfirmDialog(null, "是否删除" + id + "号员工？", "删除提醒",
							JOptionPane.OK_CANCEL_OPTION);
					if (jp == JOptionPane.OK_OPTION) {
						ManagerDao mDao = new ManagerDaoImp();
						if (mDao.delManager(id)) {
							JOptionPane.showMessageDialog(null, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "错误", "警告", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			});
			button3_2.setBounds(344, 231, 160, 42);
			panel1_3.add(button3_2);
			menuItem2_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cl_panel1.show(panel1, "p1_3");
				}
			});

			menuItem2_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cl_panel1.show(panel1, "p1_4");
				}
			});
		}

		JPanel panel1_4 = new JPanel();
		panel1.add(panel1_4, "p1_4");
		panel1_4.setLayout(null);

		JLabel label4_1 = new JLabel("员工编号：");
		label4_1.setFont(new Font("宋体", Font.PLAIN, 23));
		label4_1.setBounds(91, 42, 125, 48);
		panel1_4.add(label4_1);

		JLabel label4_2 = new JLabel("员工权限：");
		label4_2.setFont(new Font("宋体", Font.PLAIN, 23));
		label4_2.setBounds(92, 116, 138, 48);
		panel1_4.add(label4_2);

		textField4_1 = new JTextField();
		textField4_1.setBounds(230, 46, 214, 48);
		panel1_4.add(textField4_1);
		textField4_1.setColumns(10);

		JComboBox<Object> comboBox4_1 = new JComboBox<Object>();
		comboBox4_1.setBounds(230, 120, 214, 48);
		panel1_4.add(comboBox4_1);
		String[] p1 = { "超级管理员", "普通管理员" };
		for (String i : p1) {
			comboBox4_1.addItem(i);
		}
		JButton button4_1 = new JButton("修改");
		button4_1.setFont(new Font("宋体", Font.PLAIN, 23));
		button4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField4_1.getText();
				String power = (String) comboBox4_1.getSelectedItem();
				int p = 0;
				if (power.equals("超级管理员")) {
					p = 0;
				} else {
					p = 1;
				}
				ManagerDao mDao = new ManagerDaoImp();
				mDao.upMP(id, p);
			}
		});
		button4_1.setBounds(235, 228, 209, 48);
		panel1_4.add(button4_1);

		JPanel panel1_5 = new JPanel();
		panel1.add(panel1_5, "p1_5");
		panel1_5.setLayout(null);

		JLabel label5_1 = new JLabel("车位编号：");
		label5_1.setFont(new Font("宋体", Font.PLAIN, 23));
		label5_1.setBounds(14, 26, 127, 48);
		panel1_5.add(label5_1);

		JLabel label5_2 = new JLabel("临时单价：");
		label5_2.setFont(new Font("宋体", Font.PLAIN, 23));
		label5_2.setBounds(366, 34, 115, 33);
		panel1_5.add(label5_2);

		JLabel label5_3 = new JLabel("会员单价：");
		label5_3.setFont(new Font("宋体", Font.PLAIN, 23));
		label5_3.setBounds(366, 120, 115, 33);
		panel1_5.add(label5_3);

		textField5_1 = new JTextField();
		textField5_1.setBounds(133, 30, 129, 48);
		panel1_5.add(textField5_1);
		textField5_1.setColumns(10);

		textField5_2 = new JTextField();
		textField5_2.setBounds(498, 30, 129, 48);
		panel1_5.add(textField5_2);
		textField5_2.setColumns(10);

		textField5_3 = new JTextField();
		textField5_3.setBounds(498, 116, 129, 48);
		panel1_5.add(textField5_3);
		textField5_3.setColumns(10);

		JComboBox<Object> comboBox5_1 = new JComboBox<Object>();
		comboBox5_1.setBounds(133, 116, 129, 48);
		panel1_5.add(comboBox5_1);
		String[] p2 = { "添加", "删除" };
		for (String i : p2) {
			comboBox5_1.addItem(i);
		}
		JLabel label = new JLabel("功能选择：");
		label.setFont(new Font("宋体", Font.PLAIN, 23));
		label.setBounds(14, 119, 115, 34);
		panel1_5.add(label);

		JButton button5_1 = new JButton("确定");
		button5_1.setFont(new Font("宋体", Font.PLAIN, 23));
		button5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = textField5_1.getText();
				ParkingDao pDao = new ParkingDaoImp();
				String power = (String) comboBox5_1.getSelectedItem();
				int p = 0;
				if (power.equals("添加")) {
					p = 0;
				} else {
					p = 1;
				}
				if (p == 0) {
					String tp = textField5_2.getText();
					String mp = textField5_3.getText();
					float tprice = 0;
					float mprice = 0;
					Pattern pattern0 = Pattern.compile("(\\d+)(\\.\\d+)?");
					Matcher isNum0 = pattern0.matcher(tp);
					Matcher isNum1 = pattern0.matcher(mp);
					if (!isNum0.matches() || tp.equals("") || !isNum1.matches() || mp.equals("")) {
						JOptionPane.showMessageDialog(null, "非法输入", "警告", JOptionPane.WARNING_MESSAGE);
					} else {
						tprice = Float.parseFloat(tp);
						mprice = Float.parseFloat(mp);
						Parking parking = new Parking(id, kong, "临时", tprice, mprice);
						pDao.addParking(parking);
					}
				} else if (p == 1) {
					if (!pDao.checkState(id)) {
						pDao.delParking(id);
					}
				}
			}
		});
		button5_1.setBounds(225, 228, 149, 40);
		panel1_5.add(button5_1);

		JPanel panel1_6 = new JPanel();
		panel1.add(panel1_6, "p1_6");
		panel1_6.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 655, 264);
		panel1_6.add(scrollPane);

		Object[] title = { "流水号", "车位号", "车牌号", "进入时间", "离开时间", "收费", "收费员" };
		dtm = new DefaultTableModel(title, 0);

		table = new JTable(dtm);
		scrollPane.setViewportView(table);
		table.setFont(new Font("宋体", Font.PLAIN, 15));
		JButton button = new JButton("上一页");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TemporaryFeeDao tDao = new TemporaryDaoImp();

				if (page > 1) {
					page = page - 1;
					dtm.setRowCount(0);
					System.out.println(page);
					table.clearSelection();
					Vector<TemporaryFee> tFees = tDao.queryT(page);
					Object[][] objects = new Object[(tFees.size())][];
					for (int i = 0; i < objects.length; i++) {
						objects[i] = new Object[7];
						objects[i][0] = tFees.get(i).getSerial_num();
						objects[i][1] = tFees.get(i).getId();
						objects[i][2] = tFees.get(i).getPlate_num();
						objects[i][3] = tFees.get(i).getEntry_time().toString();
						objects[i][4] = tFees.get(i).getExit_time().toString();
						objects[i][5] = tFees.get(i).getFee();
						objects[i][6] = tFees.get(i).getM_id();
						dtm.addRow(objects[i]);
					}
				}
			}
		});
		button.setBounds(118, 277, 113, 27);
		panel1_6.add(button);

		JButton button_1 = new JButton("下一页");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TemporaryFeeDao tDao = new TemporaryDaoImp();
				int tfnum = tDao.count();
				int MaxP = 0;
				if (tfnum % TemporaryFee.PageSize == 0) {
					MaxP = tfnum / TemporaryFee.PageSize;
				} else {
					MaxP = tfnum / TemporaryFee.PageSize + 1;
				}
				if (page < MaxP) {
					++page;
					dtm.setRowCount(0);
					Vector<TemporaryFee> tFees = tDao.queryT(page);
					Object[][] objects = new Object[(tFees.size())][];
					for (int i = 0; i < objects.length; i++) {
						objects[i] = new Object[7];
						objects[i][0] = tFees.get(i).getSerial_num();
						objects[i][1] = tFees.get(i).getId();
						objects[i][2] = tFees.get(i).getPlate_num();
						System.out.println(tFees.get(i).getPlate_num());
						objects[i][3] = tFees.get(i).getEntry_time().toString();
						System.out.println(tFees.get(i).getEntry_time());
						objects[i][4] = tFees.get(i).getExit_time().toString();
						objects[i][5] = tFees.get(i).getFee();
						objects[i][6] = tFees.get(i).getM_id();
						dtm.addRow(objects[i]);
					}
				}
			}
		});
		button_1.setBounds(423, 277, 113, 27);
		panel1_6.add(button_1);

		// 切换处理车辆进出
		mntmItem1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl_panel1.show(panel1, "p1_1");
			}
		});
		// 切换处理增加会员
		menuItem1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_panel1.show(panel1, "p1_2");
			}
		});
		// 切换处理增减车位
		menuItem1_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_panel1.show(panel1, "p1_5");
			}
		});
		menuItem1_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_panel1.show(panel1, "p1_6");
				dtm.setRowCount(0);
				TemporaryFeeDao tDao = new TemporaryDaoImp();
				Vector<TemporaryFee> tFees = tDao.queryT(page);
				Object[][] objects = new Object[(tFees.size())][];
				for (int i = 0; i < objects.length; i++) {
					objects[i] = new Object[7];
					objects[i][0] = tFees.get(i).getSerial_num();
					objects[i][1] = tFees.get(i).getId();
					objects[i][2] = tFees.get(i).getPlate_num();
					objects[i][3] = tFees.get(i).getEntry_time().toString();
					objects[i][4] = tFees.get(i).getExit_time().toString();
					objects[i][5] = tFees.get(i).getFee();
					objects[i][6] = tFees.get(i).getM_id();
					dtm.addRow(objects[i]);
				}
			}
		});

	}

	public static boolean isIPFlag() {
		return IPflag;
	}

	public static void setIPFlag(boolean flag) {
		ManagerUI.IPflag = flag;
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
