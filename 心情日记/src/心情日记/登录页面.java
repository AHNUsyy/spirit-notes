package 心情日记;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

class Login implements ActionListener {/// 建立登录界面
	JFrame x = new JFrame("用户登录"); // 建立用户登录界面对象
	AccessDatabase data = new AccessDatabase();// 建立数据库对象
	String sql;
	JTextField nametext;// 用户ID输入
	JTextField passwordtext;// 用户密码输入
	JPanel panel = new JPanel();/// 将所有组件放入一个面板中

	public void initUL() {/// 建立窗口和添加组件的方法
		// 创建窗口

//		 Container con=x.getContentPane();
//		con.setBackground(Color.pink);
		// 设置大小位置
		x.setSize(350, 250);

		// 设置界面居中
		x.setLocationRelativeTo(null);
		// 取消默认的布局方式
		x.setLayout(null);

		// 选择组件
		JButton login = new JButton("登录");
		JButton enroll = new JButton("注册");// 注册的英文enroll
		JButton forget = new JButton("忘记密码");
		JLabel username = new JLabel("用户名ID：");
		JLabel password = new JLabel("密        码：");
		nametext = new JTextField(20);
		passwordtext = new JTextField(20);
		///
		username.setBorder(null);/// setBorder可以为组件设置边框
		////
		nametext.setBorder(null);
//		  /////
		password.setBorder(null);
//		  ////
		passwordtext.setBorder(null);

//		  ////
		login.setBorder(null);
		//////
		enroll.setBorder(null);
		forget.setBorder(null);
		// 添加组件
		panel.add(username);
		panel.add(nametext);
		panel.add(password);
		panel.add(passwordtext);
		panel.add(login);
		panel.add(enroll);
		panel.add(forget);
		panel.setBorder(null);
		panel.setBounds(36, 60, 280, 250);
		panel.setOpaque(false);
		x.add(panel);

		initImage();/// 调用建立图片背景的方法
		// 设计布局
//		 x.setLayout(new FlowLayout());

		// 添加监听器————————————按钮//事件源
		login.addActionListener(this);
		enroll.addActionListener(this);// this表示的是当前类中的对象
		forget.addActionListener(this);
		x.setVisible(true);
		x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initImage() {// 设置图片的方法
		// TODO Auto-generated method stub
		// 创建一个图片ImageIcon的对象

		// 创建一个JLabel的对象
		ImageIcon icon = new ImageIcon("photo/QQ图片20230825221114.jpg");
		/// 改变图片大小
		icon.setImage(icon.getImage().getScaledInstance(395, 395, Image.SCALE_DEFAULT));
		// 将图片放到标签上
		JLabel jLabel = new JLabel(icon);
		jLabel.setBounds(0, 0, 395, 395);
		// this.add(jLabel);
		// 获取隐藏容器1

		x.getContentPane().add(jLabel);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand().toString();// toString基本数据类型转换为String类型
		if (s.equals("登录")) {
			boolean b;///判断是否跳出循环
			data.connectionTo();// 连接数据库
			sql = "select userID,password from 用户管理";
			if (data.Query(sql)) {
				try {
					while (b=data.result.next()) {
						if (nametext.getText().equals(data.result.getString("userID"))&& passwordtext.getText().equals(data.result.getString("password"))) {
							Main.userID = data.result.getString("userID");
							JOptionPane.showMessageDialog(null, "登录成功！");
							Homepage homepage = new Homepage();
							x.dispose();
							break;
						}
					}
					if (!b)
						JOptionPane.showMessageDialog(null, "登录失败！");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "数据查询失败");
			}
//			if() {
//				Homepage home=new Homepage();////在这里点击登录之后会跳转到创建主界面		
//			    x.dispose();	///在跳到另一个界面时关闭原界面		
//			}
		} else if (s.equals("注册")) {
			Register register = new Register();

			x.dispose();
		} else if (s.equals("忘记密码")) {

			ForgetI forget = new ForgetI();//// 在这里点击忘记密码之后会跳转到忘记密码界面
			///// 关闭当前界面
			x.dispose();
		}

	}
}
