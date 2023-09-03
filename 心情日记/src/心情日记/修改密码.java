package 心情日记;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

class Password extends JFrame implements ActionListener {/// 建立修改密码界面
	JLabel oldpassword, newpassword;//// 标签旧密码，新密码
	JTextField oldfield, newfield;/// 文本框旧密码，新密码
	JButton confirmed, cancel;/// 分别为确认，取消按钮
	AccessDatabase data = new AccessDatabase();

	public Password() {
		setTitle("修改密码");
		setSize(300, 200);
		// 设置界面居中
		setLocationRelativeTo(null);
		init();
		setLayout(new FlowLayout());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void init() {
//		给成员变量创建对象
		oldpassword = new JLabel("原密码：");
		newpassword = new JLabel("新密码：");
		oldfield = new JTextField(20);
		newfield = new JTextField(20);
		confirmed = new JButton("确认");
		cancel = new JButton("取消");
//		给按钮设置监听器
		confirmed.addActionListener(this);
		cancel.addActionListener(this);
//		将组件加入到页面里
		add(oldpassword);
		add(oldfield);
		add(newpassword);
		add(newfield);
		add(confirmed);
		add(cancel);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand().toString();
		if (s.equals("确认")) {
			if (IDLegal()) {
				String sql = "update 用户管理 set password='" + newfield.getText() + "'where userID='" + Main.userID + "'";
				JOptionPane.showMessageDialog(null,"修改成功");
			} else {
				oldfield.setText(null);
				JOptionPane.showMessageDialog(this, "原密码填写有误!", "消息对话框", JOptionPane.ERROR_MESSAGE);
			}
		} else if (s.equals("取消")) {
//			关闭此页面并返回“系统设置”页面
			dispose();
			Manage manage = new Manage();
		}
	}

	boolean IDLegal() {
//		链接数据库
		data.connectionTo();
		//// "select userID from 心情日记-用户管理 where='"+text.getText()+"'"为查询的sql语言
		String sfind;
		sfind = "select password from 用户管理";
//		System.out.println(sfind);
		if (data.Query(sfind)) {/// 若查到,返回true
			try {
				while (data.result.next()) {
					if (oldfield.getText().equals(data.result.getString("password"))) {
						return true;
					}
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		} else
			return false;
	}
}