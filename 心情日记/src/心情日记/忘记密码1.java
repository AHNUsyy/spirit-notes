package 心情日记;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.*;

class ForgetI implements ActionListener {/// 忘记密码界面
	JFrame frame;/// 建立窗口
	JLabel labelI;/// 提醒用户输入ID的标签
	JTextField textI;// 用户输入ID的文本框
	JLabel labelII;// 提醒用户修改密码
	JTextField textII;/// 用户输入修改密码的文本框
	/// button1为返回按钮，button2为确认按钮
	JButton button1, button2;
	AccessDatabase data = new AccessDatabase();
	/// sFind为查询的sql语言，supdate为修改的sql语言
	String sfind, supdate;

	ForgetI() {
		frame = new JFrame("忘记密码");
		frame.setBounds(500, 200, 350, 200);
		labelI = new JLabel("输入ID:");
		textI = new JTextField(25);
		labelII = new JLabel("修改密码为：");
		textII = new JTextField(23);
		button1 = new JButton("返回");
		button2 = new JButton("确认");

		frame.setLayout(new FlowLayout());
		frame.add(labelI);
		frame.add(textI);
		frame.add(labelII);
		frame.add(textII);
		frame.add(button1);
		frame.add(button2);
		button1.addActionListener(this);
		button2.addActionListener(this);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand().toString();// 获取事件源
		if (s.equals("返回")) {
			//// dispose用来关闭当前页面
			frame.dispose();
			Login login = new Login();
			login.initUL();
		} 
		else {
//			System.out.println("diu");
			boolean b;///判断是否跳出循环
			/// 首先要连接数据库
			data.connectionTo();
			//// "select number from 心情日记-用户管理 where='"+text.getText()+"'"为查询的sql语言
			sfind = "select userID from 用户管理";//// 查询所有用户id
//			System.out.println(sql);
			if (data.Query(sfind)) {/// 若返回true,则查询到
				try {
					//// 在所有id中找到文本框中
					while (b=data.result.next()) {
						if (textI.getText().equals(data.result.getString("userID"))) {
							break;///// 找到了就跳出循环
						}
					}
					/// 如果提前跳出循环，data.result.next()为true
					if (b) {
						supdate = "update 用户管理 set password='" + textII.getText() + "'" + "where userID='"
								+ textI.getText() + "'";
						/// 修改信息
						if (data.Update(supdate) > 0)
							JOptionPane.showMessageDialog(null, "修改成功");
					}
					else {
						JOptionPane.showMessageDialog(null, "用户ID无法查询", "警告", JOptionPane.WARNING_MESSAGE);
				textII.setText(null);
					}

				} catch (SQLException m) {
					// TODO Auto-generated catch block
					m.printStackTrace();
				}
			} 
			
		}
	}

}