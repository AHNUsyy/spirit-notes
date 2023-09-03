package 心情日记;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

class Manage extends JFrame implements ActionListener {/// 建立系统设置界面类
	///// 以下分别对个人账户，密码修改，注销账号，退出登录，返回按钮
	JButton userbutton, passwordbutton, cancelbutton, exitbutton, backbutton;
	AccessDatabase data = new AccessDatabase();

//	JPanel panel=new JPanel();
	public Manage() {
		setTitle("系统设置");
		setSize(300, 300);
		// 设置界面居中
		setLocationRelativeTo(null);
		init();
		setLayout(new FlowLayout());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void init() {
//		给按钮创建对象
		userbutton = new JButton("个人账户");
		passwordbutton = new JButton("修改密码");
		cancelbutton = new JButton("注销账号");
		exitbutton = new JButton("退出登录");
		backbutton = new JButton("返回");
//		设置按钮的大小
		userbutton.setPreferredSize(new Dimension(200, 40));
		passwordbutton.setPreferredSize(new Dimension(200, 40));
		cancelbutton.setPreferredSize(new Dimension(200, 40));
		exitbutton.setPreferredSize(new Dimension(200, 40));
		backbutton.setPreferredSize(new Dimension(100, 40));
//		给每个按钮注册监听器 
		userbutton.addActionListener(this);
		passwordbutton.addActionListener(this);
		cancelbutton.addActionListener(this);
		exitbutton.addActionListener(this);
		backbutton.addActionListener(this);
//		将组件添加进页面中
		add(userbutton);
		add(passwordbutton);
		add(cancelbutton);
		add(exitbutton);
		add(backbutton);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand().toString();
		if (s.equals("个人账户")) {
			dispose();
//			跳转到个人账户的页面
			User user = new User();
		} else if (s.equals("修改密码")) {
			dispose();
//			跳转到修改密码的页面
			Password password = new Password();
		} else if (s.equals("注销账号")) {
//			连接数据库
			data.connectionTo();
//			注销该账户
			String sql1 = "delete from 用户管理 where userID='" + Main.userID + "'";
			String sql2 = "delete from 日记管理 where userID='" + Main.userID + "'";
//			进行删除
			if (data.Update(sql1) > 0) {
				if (data.Update(sql2) > 0)
					JOptionPane.showMessageDialog(null, "注销成功");
			} else {
				JOptionPane.showMessageDialog(null, "数据删除失败");
			}
//			自动返回登录页面
			dispose();
			Login login = new Login();
			login.initUL();
		} else if (s.equals("退出登录")) {
//			退出该用户的账号
			dispose();
//			自动返回登录页面
			Login login = new Login();
			login.initUL();
		} else if (s.equals("返回")) {
//			返回到主页面
			dispose();
			Homepage homepage = new Homepage();
		}
	}
}