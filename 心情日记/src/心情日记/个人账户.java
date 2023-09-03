package �����ռ�;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

class User extends JFrame implements ActionListener {/// ���������˻�����
	JTextField namefield, IDfield;/// �ֱ��ʾ�û������û�ID
	JLabel username, userID;// �ֱ��ʾ�û������û�ID��ǩ
	/// �ֱ��ʾ���أ��޸ģ�����İ�ť
	JButton backbutton, alterbutton, savebutton;
	AccessDatabase data = new AccessDatabase();
	String s;// ������Ÿ��û����û���

	public User() {
		setTitle("������Ϣ");
		setSize(300, 200);
//		setBounds(500,100,300,200);
		// ���ý������
		setLocationRelativeTo(null);
		init();
		setLayout(new FlowLayout());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void init() {// ������������
//		��ø�ID�µ��û���
		String sql = "select username from �û����� where userID='" + Main.userID + "'";
//		�������ݿ�
		data.connectionTo();
		if (data.Query(sql)) {// �Ƿ��ѯ����������鵽����true����֮����false
			try {
				while (data.result.next()) {
					s = data.result.getString("username");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		Ϊ��Ա������������
		username = new JLabel("�û�����");
		userID = new JLabel("�û�ID��");
		namefield = new JTextField(20);
		IDfield = new JTextField(20);
		namefield.setText(s);
		IDfield.setText(Main.userID);
		backbutton = new JButton("����");
		alterbutton = new JButton("�޸�");
		savebutton = new JButton("����");
//		�����û�����ID���ɸ���
		namefield.setEditable(false);
		IDfield.setEditable(false);
//		����ť��Ӽ�����
		alterbutton.addActionListener(this);
		savebutton.addActionListener(this);
		backbutton.addActionListener(this);
//		���齨��ӽ�ҳ����
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
		if (s.equals("�޸�")) {
			namefield.setEditable(true);
		} else if (s.equals("����")) {
			s = namefield.getText();
			String sql = "update �û����� set username='" + s + "' where userID='" + Main.userID + "'";
			if (data.Update(sql) > 0) {
				JOptionPane.showMessageDialog(null, "����ɹ�!");
			}
//			System.out.println(sql);
			namefield.setEditable(false);
		} else if (s.equals("����")) {
			dispose();
			Manage manage = new Manage();
		}
	}
}