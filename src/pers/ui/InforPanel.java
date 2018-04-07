package ui;

import java.awt.EventQueue;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class InforPanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JLabel label_3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InforPanel frame = new InforPanel("dd",55);
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
	public InforPanel(String id,int num) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("收费员  ：");
		label.setBounds(14, 13, 98, 18);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel(id);
		label_1.setBounds(90, 13, 72, 18);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("剩余车位：");
		label_2.setBounds(14, 44, 98, 18);
		contentPane.add(label_2);
		label_3 = new JLabel(Integer.toString(num));
		label_3.setBounds(90, 44, 72, 18);
		contentPane.add(label_3);
	}
	 protected void processWindowEvent(WindowEvent e)
	    {
	        //这里需要对进来的WindowEvent进行判断，因为，不仅会有窗口关闭的WindowEvent进来，还可能有其他的WindowEvent进来
	        if (e.getID() == WindowEvent.WINDOW_CLOSING)
	        {
	            int option = JOptionPane.showConfirmDialog(null, "是否关闭本窗口？", "窗口关闭提示", JOptionPane.OK_CANCEL_OPTION);
	            if (option == JOptionPane.OK_OPTION)
	            {
	                super.dispose();
	                ManagerUI.setFlag(false);
	            }
	            else {
	                //用户选择不退出本程序，因此可以继续留在本窗口
	            }
	        }
	        else {
	            super.processWindowEvent(e);
	        }
	    }

}
