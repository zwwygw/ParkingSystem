import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
* <p>Title: WindowListenerKnow.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2018年3月30日<／p>
* <p>Company: CSU<／p>
* @author ZWW
* @date 2018年3月30日
* @version 1.0
*/

/**
 * @ClassName WindowListenerKnow
 * @Description TODO
 * @author ZWW
 * @date   2018年3月30日下午11:43:40
 */
public class WindowListenerKnow extends JFrame
{
    public WindowListenerKnow()
    {
        this.setBounds(300, 100, 400, 400);
        this.setTitle("我是测试【x】按钮关闭方法的");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void init()
    {
        
    }
    public static void main(String[] args)
    {
        WindowListenerKnow windowListenerKnow = new WindowListenerKnow();
        windowListenerKnow.setVisible(true);
    }
    
    /**
     * 这里需要重写窗口的事件中转方法，而不是实现WindowListener接口，因为程序时从这个方法processWindowEvent进入到窗口关闭事件的
     */
    protected void processWindowEvent(WindowEvent e)
    {
        //这里需要对进来的WindowEvent进行判断，因为，不仅会有窗口关闭的WindowEvent进来，还可能有其他的WindowEvent进来
        if (e.getID() == WindowEvent.WINDOW_CLOSING)
        {
            int option = JOptionPane.showConfirmDialog(null, "是否关闭程序？", "程序退出提示", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION)
            {
                super.processWindowEvent(e);
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
