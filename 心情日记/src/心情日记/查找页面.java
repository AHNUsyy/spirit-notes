package �����ռ�;

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

class Find implements ActionListener, ListSelectionListener {//// ��������ҳ����
///////ListSelectionListener��list�йصļ������ӿ�
	JFrame frame;/// frameΪ�������ڶ���
	JTextArea area2;/// area2Ϊ��ʾ�ռǵ��ı���
	JComboBox<String> combox;//// comboxΪ�����б�
	JTextField text;//// text�����û����ҵĹؼ��ֵ��ı���
	///// bFindΪ���Ұ�ť��bDeleteΪɾ����ť��bEditΪ�༭��ť��bSaveΪ���水ť��bReturnΪ���ذ�ť
	JButton bFind, bDelete, bEdit, bSave, bReturn;
	JPanel panel = new JPanel();//// panel�������area2
	JPanel panelI = new JPanel();//// ���panelI������������б������ı��򣬲��Ұ�ť
	JPanel panelII = new JPanel();/// ���panelII������ŷ��ذ�ť��ɾ����ť���༭��ť�����水ť��
	JPanel panelIII = new JPanel();// ���panelIII�������panelI,panelII,area2��

	//// sΪ�����б���ѡ���ݣ�ssΪ�����û���ǰ��ѡ��ť��֮ǰ��ѡ��ť�Ƿ���ͬ��
	String s, sql, ss;
	AccessDatabase data = new AccessDatabase();//// �������ݿ����
	/// �����ı����б�
	DefaultListModel<String> l1 = new DefaultListModel<>();///ʵ��List�ӿڵ�Ĭ���б�ģ��
	JList<String> list = new JList<>(l1);///�����б�����
	JLabel la = new JLabel("");// ���ͼƬ��ǩ
	ImageIcon image;

	Find() {
		frame = new JFrame("����ҳ��");
		combox = new JComboBox<String>();
		// Ϊ�����б�����Ԫ��
		combox.addItem("�������");
		combox.addItem("�������");
		combox.addItem("��������");
		combox.addItem("��ʱ���");
		text = new JTextField(10);
		area2 = new JTextArea(13, 28);
		area2.setEditable(false);

		panel.setSize(100, 80);
		panel.add(area2);
		panel.add(la);
		bFind = new JButton("����");
		bDelete = new JButton("ɾ��");
		bEdit = new JButton("�༭");
		bSave = new JButton("����");
		bReturn = new JButton("����");

		/// ���ý��汳����ɫ
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
		///// Ϊlist����һ��������
		list.addListSelectionListener(this);
		list.setSize(5, 5);
		/// ʹ��һ���ı����б���й�������
		JScrollPane scroll = new JScrollPane(list);
		// �������б��ı��򣬰�ť�������panelI�У����д���
		panelI.add(combox);
		panelI.add(text);
		panelI.add(bFind);
		// ���ĸ���ť�������panelI�У����д���
		panelII.add(bDelete);
		panelII.add(bEdit);
		panelII.add(bSave);
		panelII.add(bReturn);
		// ���panelIII������Ϊ��panelI,panelII��area2����һ������
		panelIII.setLayout(new BorderLayout());
		/// �ֱ�panelIII�е��������λ������Ϊ������
		panelIII.add(panelI, BorderLayout.NORTH);
		panelIII.add(panel, BorderLayout.CENTER);
		panelIII.add(panelII, BorderLayout.SOUTH);
		/// ���ò�ִ��񣬽�panelIII��area1���ҷֿ�
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, panelIII);
		frame.add(split);
		frame.setLayout(new FlowLayout());
		/// ���ý����С
		frame.setSize(550, 400);
		// ���ý������
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {//// �¼�����ķ���
		String f = e.getActionCommand().toString();
		data.connectionTo();
		if (f.equals("����")) {
			
			area2.setEditable(false);
			l1.removeAllElements();///����б�����ѡ��
			area2.setText(null);//����ռ��ı�
			la.setIcon(null);//�����Ƭ
			////// ��������б�ĵ�ǰ��ѡ
			s = combox.getSelectedItem().toString();
			/////
			switch (s) {
			///// ��sת��Ϊ���ݱ��еı�ͷ
			case "�������":
				s = "topic";
				break;
			case "�������":
				s = "mood";
				break;
			case "��������":
				s = "weather";
				break;
			case "��ʱ���":
				s = "time";
				break;

			}
			
			//// �������֮��Ҫ�������ݿ���в��ң����ڽ�������ʾ
			sql = "select title from �ռǹ��� where " + s + "='" + text.getText()+"' and userID='" + Main.userID + "'";
			System.out.println(sql);
			if (data.Query(sql)) {
//				list.setSelectedIndex(-1);
				
				try {
					
//************************������ѯ�б�****************************//					
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
			if (f.equals("ɾ��")) {
				sql = "delete from �ռǹ��� where " + s + "='" + text.getText() + "' and title='" + list.getSelectedValue()
						+ "' and userID='" + Main.userID + "'";
				if (data.Update(sql) > 0)
					JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
				area2.setText(null);
				l1.remove(list.getSelectedIndex());
				area2.setEditable(false);
				////// ���û����ɾ��ʱ���ݱ��е�����ҲӦɾ��
				la.setIcon(null);

			} 
			else {
				if (f.equals("�༭")) {
					/// ������༭ʱ���ı�area2��Ϊ�ɱ༭
					area2.setEditable(true);
				} 
				else {
					if (f.equals("����")) {
						/// ���û��༭�����ı���ӵ�

						sql = "update �ռǹ��� set text='" + area2.getText() + "' where " + s + "='" + text.getText()
								+ "' and title='" + list.getSelectedValue() + "' and userID='" + Main.userID + "'";
						if (data.Update(sql) > 0) {
							JOptionPane.showMessageDialog(null, "����ɹ�");
						} else {
							JOptionPane.showMessageDialog(null, "���ݴ������ݿ�ʧ��");
						}
					}
					if (f.equals("����")) {
						Homepage home = new Homepage();
						frame.dispose();
					}
				}
			}
		}
	}

	

	public void valueChanged(ListSelectionEvent e) {
		// ************************���ı�������ʾ��ѡ�б���ı��ռ�********************///
		///// ������ѯ���
		data.connectionTo();
//				System.out.println(list.getSelectedValue());
		String sql1 = "select text,photo from �ռǹ��� where " + s + "='" + text.getText() + "' and title='"
				+ list.getSelectedValue() + "' and userID='" + Main.userID + "'";
//			System.out.println(sql1);
		if (data.Query(sql1)) {
			try {
				while (data.result.next()) {
					//// ���ռ��ı���ʾ���ı�����
					area2.setText(data.result.getString("text"));
					//// �ж��û��Ƿ������ͼƬ
//					if (!data.result.getString("photo").equals("")) {
						///// ����ͼƬ����
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
