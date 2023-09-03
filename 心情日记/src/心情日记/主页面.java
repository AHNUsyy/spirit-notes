package 心情日记;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Homepage implements ActionListener {// 建立主页面
	JFrame frame;
//	JLabel label;
	/// 分别表示写日记，查日记，系统设置按钮
	JButton writer, reseacher, setting;

	Homepage() {
		frame = new JFrame("主页面");
		frame.setBounds(500, 200, 400, 300);
//		label=new JLabel("主页面");
		writer = new JButton("写日记");
		reseacher = new JButton("查找日记");
		setting = new JButton("系统设置");
		//// 为每一个按钮设置一个监听器
		writer.addActionListener(this);
		reseacher.addActionListener(this);
		setting.addActionListener(this);
		JPanel panel = new JPanel();
		panel.setBounds(100, 200, 20, 20);
		panel.setLayout(new GridLayout());
		panel.add(writer);
		panel.add(reseacher);
		panel.add(setting);
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand().toString();
		if (s.equals("写日记")) {
			//// 在这里点击写日记之后会跳转到创建日记界面
//			ForgetI f=new ForgetI();
			/// 在跳到另一个界面时关闭原界面
			write w = new write();
			w.initUL();
			frame.dispose();
		} else {
			if (s.equals("查找日记")) {
				//// 在这里点击写日记之后会跳转到查找日记界面
				Find r = new Find();
				/// 在跳到另一个界面时关闭原界面
				frame.dispose();
			} else {
				Manage manage = new Manage();
				frame.dispose();
			}
		}
	}
}
