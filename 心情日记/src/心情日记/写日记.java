package 心情日记;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

class write implements ActionListener { // 建立写日记界面
	JFrame y = new JFrame();/// 建立窗口对象
	AccessDatabase data = new AccessDatabase();// 建立数据库对象
	String sinsert;// 插入的sql语句
	JComboBox weather;// 表示天气下拉列表
	JComboBox mood;// 表示情绪下拉列表
	JComboBox topic;// 表示主题下拉列表
	JComboBox year;// 表示年下拉列表
	JComboBox month;// 表示月的下拉列表
	JComboBox day;// 表示天的下拉列表
	String f[] = new String[3], sql;// f字符数组，用来存放天气，情绪，主题下拉列表当前所选内容
	String syear, smonth, sday, time;// syear,smonth,sday分别用来存放年，月，日下拉列表当前所选内容，time表示将年月日表示形式格式化
	JTextField text;// 表示用户输入标题内容
	JTextArea area;// 表示用户输入日记正文
	JPanel panel = new JPanel();// 建立一个面板用来存放日记文本和图片
	JLabel la = new JLabel();// 该标签用来存放图片
	ImageIcon image;// 创建未初始化的图像图标

	public void initUL() {// 建立窗口与增添组件的方法
		// 创建窗口

		// 设计属性
		y.setTitle("写日记");

		y.setBounds(400, 150, 500, 450);

		// 添加组件
		y.add(new JLabel("天气"));
		weather = new JComboBox();
		weather.addItem("晴天");
		weather.addItem("大雨");
		weather.addItem("小雨");
		weather.addItem("阴天");
		weather.addItem("大风");
		weather.addItem("雨夹雪");
		weather.addItem("雪天");
		y.add(weather);

		y.add(new JLabel("情绪"));
		mood = new JComboBox();
		mood.addItem("快乐");
		mood.addItem("悲伤");
		mood.addItem("愤怒");
		mood.addItem("焦虑");
		mood.addItem("平静");
		mood.addItem("无聊");
		mood.addItem("烦躁");
		mood.addItem("恐惧");
		y.add(mood);

		y.add(new JLabel("主题"));
		topic = new JComboBox();
		topic.addItem("生活");
		topic.addItem("学习");
		topic.addItem("娱乐");
		topic.addItem("吐槽");
		topic.addItem("计划");
		y.add(topic);

		y.add(new JLabel("日期"));
		year = new JComboBox();
		int i;
		for (i = 2000; i < 2030; i++) {
			year.addItem(Integer.toString(i));
		}
		y.add(year);

		month = new JComboBox();
		for (i = 1; i <= 12; i++) {
			month.addItem(Integer.toString(i));
		}
		y.add(month);

		day = new JComboBox();
		for (i = 1; i <= 31; i++) {
			day.addItem(Integer.toString(i));
		}
		y.add(day);

		y.add(new JLabel("标题"));

		text = new JTextField(30);
		y.add(text);
		//
		area = new JTextArea(15, 35);

		panel.setSize(40, 40);
		panel.add(area);
		panel.add(la);
		y.add(panel);

		JButton add = new JButton("添加图片");
		JButton save = new JButton("保存");
		JButton empty = new JButton("清空新建");
		JButton retur = new JButton("返回");

		y.add(add);
		y.add(save);
		y.add(empty);
		y.add(retur);

		// 设计布局
		FlowLayout f = new FlowLayout();
		y.setLayout(f);

		// 添加监听器————————————按钮//事件源
		add.addActionListener(this);
		save.addActionListener(this);
		empty.addActionListener(this);
		retur.addActionListener(this);
		/// 设置界面背景颜色
		Color col = new Color(173, 216, 230);
		y.getContentPane().setBackground(col);
		///
		y.setVisible(true);
		y.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	// 响应事件

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand().toString();
		if (s.equals("添加图片")) {
			image = new ImageIcon("photo/QQ图片20230826130913.jpg");
//			System.out.println(image.getDescription());
			
//					System.out.println(write.class.getResource("photo/微信图片_20220905180639.jpg"));
			/// 设置图片大小
			image.setImage(image.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
			///将图片放到la图片上
			la.setIcon(image);
			la.setText(null);
//					System.out.println("aaa");

		}

		else if (s.equals("保存")) {

			//// 将各个下拉列表的所选项提取出来
			f[0] = weather.getSelectedItem().toString();
			f[1] = mood.getSelectedItem().toString();
			f[2] = topic.getSelectedItem().toString();
			syear = year.getSelectedItem().toString();
			smonth = month.getSelectedItem().toString();
			sday = day.getSelectedItem().toString();
			////
			time = syear + "-" + smonth + "-" + sday;
			data.connectionTo();
			/// 编写sql语句将日记内容插入数据库
			/// 在将数据插入数据库中要判断用户是否选择添加图片
			if (la.getIcon() == null)
				sql = "insert into 日记管理(userID,time,text,weather,topic,mood,title) values('" + Main.userID + "','"
						+ time + "','" + area.getText() + "','" + f[0] + "','" + f[2] + "','" + f[1] + "','"
						+ text.getText() + "')";

			else
				sql = "insert into 日记管理(userID,time,text,weather,topic,mood,title,photo) values('" + Main.userID + "','"
						+ time + "','" + area.getText() + "','" + f[0] + "','" + f[2] + "','" + f[1] + "','"
						+ text.getText() + "','photo/QQ图片20230826130913.jpg')";////获得tu'pian'lu
//			System.out.println(sql);

			if (data.Update(sql) > 0) {
				JOptionPane.showMessageDialog(null, "保存成功！");
			} else {
				JOptionPane.showMessageDialog(null, "数据库插入失败");
			}
		}

		else if (s.equals("清空新建")) {/// 要将文本全都清空，且图片删除
			la.setIcon(null);
			area.setText(null);
			text.setText(null);
		}

		else if (s.equals("返回")) {
			/// 在这里点击返回之后会跳转到创建主界面
			Homepage home = new Homepage();
			y.dispose();
		}

	}

}
