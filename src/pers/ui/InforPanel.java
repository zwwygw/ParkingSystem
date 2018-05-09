package pers.ui;

import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;

public class InforPanel extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected  static JLabel label_3;
	private static JLabel label_5;
	private String DEFAULT_TIME_FORMAT = "HH:mm:ss";
	private int ONE_SECOND = 1000;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*
		 * EventQueue.invokeLater(new Runnable() { public void run() { try { InforPanel
		 * frame = new InforPanel("dd", 55);
		 * 
		 * frame.setVisible(true); // kaishi(); } catch (Exception e) {
		 * e.printStackTrace(); } } });
		 */
		InforPanel frame = new InforPanel("dd", 55);
		frame.setVisible(true);

		Thread thread1 = new Thread(frame);
		thread1.start();
	}

	/**
	 * Create the frame.
	 */
	public InforPanel(String id, int num) {
		try {
			UIManager.setLookAndFeel(javax.swing.plaf.nimbus.NimbusLookAndFeel.class.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 493, 322);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("收费员  ：");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(14, 30, 137, 33);
		contentPane.add(label);

		JLabel label_1 = new JLabel(id);
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(125, 31, 116, 31);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("剩余车位：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		label_2.setBounds(14, 92, 137, 33);
		contentPane.add(label_2);
		label_3 = new JLabel(Integer.toString(num));
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		label_3.setBounds(125, 101, 72, 18);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("当前时间：");
		label_4.setFont(new Font("宋体", Font.PLAIN, 20));
		label_4.setBounds(14, 158, 116, 18);
		contentPane.add(label_4);

		label_5 = new JLabel(" ");
		label_5.setFont(new Font("宋体", Font.PLAIN, 18));
		label_5.setBounds(125, 158, 129, 20);
		contentPane.add(label_5);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(InforPanel.class.getResource("/pers/2.jpg")));
		lblNewLabel.setBounds(74, 0, 401, 275);
		contentPane.add(lblNewLabel);

	}


	protected void processWindowEvent(WindowEvent e) {
		// 这里需要对进来的WindowEvent进行判断，因为，不仅会有窗口关闭的WindowEvent进来，还可能有其他的WindowEvent进来
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			int option = JOptionPane.showConfirmDialog(null, "是否关闭本窗口？", "窗口关闭提示", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				super.dispose();
				
				ManagerUI.setIPFlag(false);
			} else {
				// 用户选择不退出本程序，因此可以继续留在本窗口
			}
		} else {
			super.processWindowEvent(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				SimpleDateFormat dateFormatter = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
				label_5.setText(dateFormatter.format(Calendar.getInstance().getTime()));
				Thread.sleep(ONE_SECOND);
			}
		} catch (Exception e) {
		//	label_5.setText("Error!!!");
			e.printStackTrace();
		}
	}

}
