package 心情日记;

import java.awt.BorderLayout;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Register extends JFrame implements ActionListener {/// 建立注册页面
	/// 以下表示标签：用户名，用户ID，新密码，确认密码
	JLabel username, userID, newpassword, confirmedpassword;
	////
	Box boxH1, boxH2, boxH3, boxH4;
	Box boxV;
	//// 以下表示文本框：用户名，用户ID，新密码，确认密码
	JTextField text1, text2, text3, text4;
	// 以下表示注册，返回按钮
	JButton registerbutton, backbutton;
	AccessDatabase data = new AccessDatabase();

//	************************************************************************************************************************************
	public Register() {
		setTitle("用户注册");
		setLayout(new FlowLayout());
		init();
		setSize(300, 300);
		// 设置界面居中
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

//	************************************************************************************************************************************
	void init() {/// 在窗口添加组件的方法
// 		将成员变量实例化
		username = new JLabel("用    户    名：");
		userID = new JLabel("用    户    ID：");
		newpassword = new JLabel("新    密    码：");
		confirmedpassword = new JLabel("确认新密码：");
		registerbutton = new JButton("注册");
		backbutton = new JButton("返回");
//		获得五个行型盒式容器
		boxH1 = Box.createHorizontalBox();
		boxH2 = Box.createHorizontalBox();
		boxH3 = Box.createHorizontalBox();
		boxH4 = Box.createHorizontalBox();
//		获得一个列型盒式容器
		boxV = Box.createVerticalBox();
//		将文本框创建对象
		text1 = new JTextField(20);
		text2 = new JTextField(20);
		text3 = new JTextField(20);
		text4 = new JTextField(20);
//		将标签与对应的文本框放入行型盒式容器中
		boxH1.add(username);
		boxH1.add(text1);
		boxH2.add(userID);
		boxH2.add(text2);
		boxH3.add(newpassword);
		boxH3.add(text3);
		boxH4.add(confirmedpassword);
		boxH4.add(text4);
//		将五个行型盒式容器放入一个列型盒式容器
		boxV.add(boxH1);
		boxV.add(Box.createVerticalStrut(20));
		boxV.add(boxH2);
		boxV.add(Box.createVerticalStrut(20));
		boxV.add(boxH3);
		boxV.add(Box.createVerticalStrut(20));
		boxV.add(boxH4);
		boxV.add(Box.createVerticalStrut(20));
		add(boxV);
//		给按钮注册监听器
		registerbutton.addActionListener(this);
		backbutton.addActionListener(this);
		add(backbutton);
		add(registerbutton);
	}

	public void actionPerformed(ActionEvent e) {
//		获取事件源
		String s = e.getActionCommand().toString();
		if (s.equals("返回")) {
//			diapose关闭当前页面
			this.dispose();
			Login login = new Login();
			login.initUL();
		} else if (s.equals("注册")) {
//			判断用户是否把注册信息填写完整
			if (text2.getText().equals("") || text3.getText().equals("") || text4.getText().equals("")) {
//				若信息未填写完整，则弹出一个对话框提示用户
				JOptionPane.showMessageDialog(this, "信息未填写完整!", "消息对话框", JOptionPane.ERROR_MESSAGE);
			}
//			判断用户填写的ID是否合法
			else if (!IDcheck()) {
//				若用户ID不合法，则弹出一个对话框提示用修改ID,同时将错误的ID清空
				text2.setText(null);
				JOptionPane.showMessageDialog(this, "ID填写错误!", "消息对话框", JOptionPane.ERROR_MESSAGE);
			}
//			判断用户填写的ID是否已经注册过账户了
			else if (IDLegal()) {
//				在数据库中查找到了ID，则将出现过的ID清空
				text2.setText(null);
				JOptionPane.showMessageDialog(this, "该用户已存在!", "消息对话框", JOptionPane.ERROR_MESSAGE);
			}
//			判断填写的两次新密码是否相同
			else if (!SomePassword(text3, text4)) {
				text4.setText(null);
				JOptionPane.showMessageDialog(this, "两次填写的密码不相同!", "消息对话框", JOptionPane.ERROR_MESSAGE);
			} else {
				data.connectionTo();
				String sql = "insert into 用户管理(username,userID,password) values ('" + text1.getText() + "','"
						+ text2.getText() + "','" + text3.getText() + "')";
//				System.out.println(sql);
				if (data.Update(sql) > 0) {
					JOptionPane.showMessageDialog(null, "注册成功");
				} else {
					JOptionPane.showMessageDialog(this, "数据库插入失败", "消息对话框", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

//	**************************************判断用户输入的ID是否合法**************************************************************************
	int year, month, day, yu, sum;

	boolean IDcheck() {
		String ID = text2.getText();
		if (!ID.matches("[1-9][0-9]{5}(19|20)[0-9]{9}[0-9Xx]")) {// 一个简易的正则表达式，判断身份证号前17位是否为数字，第一位不能是0，第7/8位是19或20，最后一位是数字或X、x，本语句可以直接使用。
			return false;
		}
		year = Integer.parseInt(ID.substring(6, 10)); // 获取身份证号码中的出生年份
		month = Integer.parseInt(ID.substring(10, 12)); // 获取身份证号码中的出生月份
		day = Integer.parseInt(ID.substring(12, 14)); // 获取身份证号码中的出生日
		if (!dateLegal()) {// 判断出生日期是否合法，主要是月、日的合法性
			return false;
		}
		char ch[] = ID.toCharArray();// 将字符串转换成字符数组
		// 根据定义，计算加权总和sum
		sum = 7 * (ch[0] - '0') + 9 * (ch[1] - '0') + 10 * (ch[2] - '0') + 5 * (ch[3] - '0') + 8 * (ch[4] - '0')
				+ 4 * (ch[5] - '0') + 2 * (ch[6] - '0') + 1 * (ch[7] - '0') + 6 * (ch[8] - '0') + 3 * (ch[9] - '0')
				+ 7 * (ch[10] - '0') + 9 * (ch[11] - '0') + 10 * (ch[12] - '0') + 5 * (ch[13] - '0')
				+ 8 * (ch[14] - '0') + 4 * (ch[15] - '0') + 2 * (ch[16] - '0');
		yu = sum % 11;
		// 根据余数对应关系，判断身份证号最后一位是否正确
		if (yu == 0 && ch[17] == '1')
			return true;
		else if (yu == 1 && ch[17] == '0')
			return true;
		else if (yu == 2 && (ch[17] == 'X' || ch[17] == 'x'))
			return true;
		else if (yu == 3 && ch[17] == '9')
			return true;
		else if (yu == 4 && ch[17] == '8')
			return true;
		else if (yu == 5 && ch[17] == '7')
			return true;
		else if (yu == 6 && ch[17] == '6')
			return true;
		else if (yu == 7 && ch[17] == '5')
			return true;
		else if (yu == 8 && ch[17] == '4')
			return true;
		else if (yu == 9 && ch[17] == '3')
			return true;
		else if (yu == 10 && ch[17] == '2')
			return true;
		else
			return false;
	}

	boolean dateLegal() {
		// 判断出生日期是否合法
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			if (day >= 0 && day <= 31) {
				return true;
			} else
				return false;
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			if (day >= 0 && day <= 30) {
				return true;
			} else
				return false;
		} else if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			if (month == 2 && day >= 0 && day <= 29) {
				return true;
			} else
				return false;
		} else if (month == 2 && day >= 0 && day <= 28) {
			return true;
		} else
			return false;
	}

	// ************************************判断该ID是否注册过账户了*******************************************
	boolean IDLegal() {
//			链接数据库
		data.connectionTo();
		//// "select userID from 心情日记-用户管理 where='"+text.getText()+"'"为查询的sql语言
		String sfind;
		sfind = "select userID from 用户管理 ";
//			System.out.println(sfind);
		if (data.Query(sfind)) {/// 若查到,返回true
			try {
				while (data.result.next()) {
					if (text2.getText().equals(data.result.getString("userID"))) {
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

//		***********************************判断两次填写的密码是否相同**********************************************************
	boolean SomePassword(JTextField field1, JTextField field2) {
		String s1 = field1.getText();
		String s2 = field2.getText();
		if (s1.equals(s2))
			return true;
		else
			return false;
	}
}
