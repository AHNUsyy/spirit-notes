package 心情日记;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

class User extends JFrame implements ActionListener {/// 建立个人账户界面
	JTextField namefield, IDfield;/// 分别表示用户名，用户ID
	JLabel username, userID;// 分别表示用户名，用户ID标签
	/// 分别表示返回，修改，保存的按钮
	JButton backbutton, alterbutton, savebutton;
	AccessDatabase data = new AccessDatabase();
	String s;// 用来存放该用户的用户名

	public User() {
		setTitle("个人信息");
		setSize(300, 200);
//		setBounds(500,100,300,200);
		// 设置界面居中
		setLocationRelativeTo(null);
		init();
		setLayout(new FlowLayout());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void init() {// 给窗口添加组件
//		获得该ID下的用户名
		String sql = "select username from 用户管理 where userID='" + Main.userID + "'";
//		链接数据库
		data.connectionTo();
		if (data.Query(sql)) {// 是否查询到结果，若查到返回true，反之返回false
			try {
				while (data.result.next()) {
					s = data.result.getString("username");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		为成员变量创建对象
		username = new JLabel("用户名：");
		userID = new JLabel("用户ID：");
		namefield = new JTextField(20);
		IDfield = new JTextField(20);
		namefield.setText(s);
		IDfield.setText(Main.userID);
		backbutton = new JButton("返回");
		alterbutton = new JButton("修改");
		savebutton = new JButton("保存");
//		设置用户名、ID不可更改
		namefield.setEditable(false);
		IDfield.setEditable(false);
//		给按钮添加监视器
		alterbutton.addActionListener(this);
		savebutton.addActionListener(this);
		backbutton.addActionListener(this);
//		将组建添加进页面里
		add(username);
		add(namefield);
		add(userID);
		add(IDfield);
		add(alterbutton);
		add(savebutton);
		add(backbutton);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand().toString();
		if (s.equals("修改")) {
			namefield.setEditable(true);
		} else if (s.equals("保存")) {
			s = namefield.getText();
			String sql = "update 用户管理 set username='" + s + "' where userID='" + Main.userID + "'";
			if (data.Update(sql) > 0) {
				JOptionPane.showMessageDialog(null, "保存成功!");
			}
//			System.out.println(sql);
			namefield.setEditable(false);
		} else if (s.equals("返回")) {
			dispose();
			Manage manage = new Manage();
		}
	}
}