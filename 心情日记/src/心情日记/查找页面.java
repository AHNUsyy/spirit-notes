package 心情日记;

import java.awt.*;

import java.awt.Image;
import java.awt.event.*;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class Find implements ActionListener, ListSelectionListener {//// 建立查找页面类
///////ListSelectionListener与list有关的监听器接口
	JFrame frame;/// frame为建立窗口对象
	JTextArea area2;/// area2为显示日记的文本区
	JComboBox<String> combox;//// combox为下拉列表
	JTextField text;//// text用来用户查找的关键字的文本框
	///// bFind为查找按钮，bDelete为删除按钮，bEdit为编辑按钮，bSave为保存按钮，bReturn为返回按钮
	JButton bFind, bDelete, bEdit, bSave, bReturn;
	JPanel panel = new JPanel();//// panel用来存放area2
	JPanel panelI = new JPanel();//// 面板panelI用来存放下拉列表，查找文本框，查找按钮
	JPanel panelII = new JPanel();/// 面板panelII用来存放返回按钮，删除按钮，编辑按钮，保存按钮。
	JPanel panelIII = new JPanel();// 面板panelIII用来存放panelI,panelII,area2。

	//// s为下拉列表所选内容，ss为鉴别用户当前所选按钮与之前所选按钮是否相同。
	String s, sql, ss;
	AccessDatabase data = new AccessDatabase();//// 建立数据库对象
	/// 建立文本项列表
	DefaultListModel<String> l1 = new DefaultListModel<>();///实现List接口的默认列表模型
	JList<String> list = new JList<>(l1);///创建列表框对象
	JLabel la = new JLabel("");// 存放图片标签
	ImageIcon image;

	Find() {
		frame = new JFrame("查找页面");
		combox = new JComboBox<String>();
		// 为下拉列表增添元素
		combox.addItem("按主题查");
		combox.addItem("按心情查");
		combox.addItem("按天气查");
		combox.addItem("按时间查");
		text = new JTextField(10);
		area2 = new JTextArea(13, 28);
		area2.setEditable(false);

		panel.setSize(100, 80);
		panel.add(area2);
		panel.add(la);
		bFind = new JButton("查找");
		bDelete = new JButton("删除");
		bEdit = new JButton("编辑");
		bSave = new JButton("保存");
		bReturn = new JButton("返回");

		/// 设置界面背景颜色
		Color col = new Color(173, 216, 230);
		panelI.setBackground(col);
		panelII.setBackground(col);
		panel.setBackground(col);
		///
		s = combox.getSelectedItem().toString();
		ss = s;
		bFind.addActionListener(this);
		bDelete.addActionListener(this);
		bEdit.addActionListener(this);
		bSave.addActionListener(this);
		bReturn.addActionListener(this);
		///// 为list设立一个监听器
		list.addListSelectionListener(this);
		list.setSize(5, 5);
		/// 使第一个文本项列表具有滚动隔窗
		JScrollPane scroll = new JScrollPane(list);
		// 将下拉列表，文本框，按钮放入面板panelI中，集中处理
		panelI.add(combox);
		panelI.add(text);
		panelI.add(bFind);
		// 将四个按钮放入面板panelI中，集中处理
		panelII.add(bDelete);
		panelII.add(bEdit);
		panelII.add(bSave);
		panelII.add(bReturn);
		// 面板panelIII的作用为将panelI,panelII和area2看成一个整体
		panelIII.setLayout(new BorderLayout());
		/// 分别将panelIII中的三个组件位置设置为上中下
		panelIII.add(panelI, BorderLayout.NORTH);
		panelIII.add(panel, BorderLayout.CENTER);
		panelIII.add(panelII, BorderLayout.SOUTH);
		/// 运用拆分窗格，将panelIII与area1左右分开
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, panelIII);
		frame.add(split);
		frame.setLayout(new FlowLayout());
		/// 设置界面大小
		frame.setSize(550, 400);
		// 设置界面居中
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {//// 事件处理的方法
		String f = e.getActionCommand().toString();
		data.connectionTo();
		if (f.equals("查找")) {
			
			area2.setEditable(false);
			l1.removeAllElements();///清空列表所有选项
			area2.setText(null);//清楚日记文本
			la.setIcon(null);//清除照片
			////// 获得下拉列表的当前所选
			s = combox.getSelectedItem().toString();
			/////
			switch (s) {
			///// 将s转化为数据表中的表头
			case "按主题查":
				s = "topic";
				break;
			case "按心情查":
				s = "mood";
				break;
			case "按天气查":
				s = "weather";
				break;
			case "按时间查":
				s = "time";
				break;

			}
			
			//// 点击查找之后要连接数据库进行查找，并在界面中显示
			sql = "select title from 日记管理 where " + s + "='" + text.getText()+"' and userID='" + Main.userID + "'";
			System.out.println(sql);
			if (data.Query(sql)) {
//				list.setSelectedIndex(-1);
				
				try {
					
//************************创建查询列表****************************//					
//					
					while (data.result.next()) {
						System.out.println(data.result.getString("title"));
						l1.addElement(data.result.getString("title"));
					}
				}
//				}
			catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else {
			if (f.equals("删除")) {
				sql = "delete from 日记管理 where " + s + "='" + text.getText() + "' and title='" + list.getSelectedValue()
						+ "' and userID='" + Main.userID + "'";
				if (data.Update(sql) > 0)
					JOptionPane.showMessageDialog(null, "删除成功");
				area2.setText(null);
				l1.remove(list.getSelectedIndex());
				area2.setEditable(false);
				////// 在用户点击删除时数据表中的数据也应删除
				la.setIcon(null);

			} 
			else {
				if (f.equals("编辑")) {
					/// 当点击编辑时，文本area2设为可编辑
					area2.setEditable(true);
				} 
				else {
					if (f.equals("保存")) {
						/// 将用户编辑过的文本添加到

						sql = "update 日记管理 set text='" + area2.getText() + "' where " + s + "='" + text.getText()
								+ "' and title='" + list.getSelectedValue() + "' and userID='" + Main.userID + "'";
						if (data.Update(sql) > 0) {
							JOptionPane.showMessageDialog(null, "保存成功");
						} else {
							JOptionPane.showMessageDialog(null, "数据存入数据库失败");
						}
					}
					if (f.equals("返回")) {
						Homepage home = new Homepage();
						frame.dispose();
					}
				}
			}
		}
	}

	

	public void valueChanged(ListSelectionEvent e) {
		// ************************在文本框中显示所选列表的文本日记********************///
		///// 建立查询语句
		data.connectionTo();
//				System.out.println(list.getSelectedValue());
		String sql1 = "select text,photo from 日记管理 where " + s + "='" + text.getText() + "' and title='"
				+ list.getSelectedValue() + "' and userID='" + Main.userID + "'";
//			System.out.println(sql1);
		if (data.Query(sql1)) {
			try {
				while (data.result.next()) {
					//// 将日记文本显示在文本区中
					area2.setText(data.result.getString("text"));
					//// 判断用户是否插入了图片
//					if (!data.result.getString("photo").equals("")) {
						///// 插入图片过程
						image = new ImageIcon(data.result.getString("photo"));
//							
						image.setImage(image.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
						la.setIcon(image);
						la.setText(null);
//					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
