package �����ռ�;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

class Password extends JFrame implements ActionListener {/// �����޸��������
	JLabel oldpassword, newpassword;//// ��ǩ�����룬������
	JTextField oldfield, newfield;/// �ı�������룬������
	JButton confirmed, cancel;/// �ֱ�Ϊȷ�ϣ�ȡ����ť
	AccessDatabase data = new AccessDatabase();

	public Password() {
		setTitle("�޸�����");
		setSize(300, 200);
		// ���ý������
		setLocationRelativeTo(null);
		init();
		setLayout(new FlowLayout());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void init() {
//		����Ա������������
		oldpassword = new JLabel("ԭ���룺");
		newpassword = new JLabel("�����룺");
		oldfield = new JTextField(20);
		newfield = new JTextField(20);
		confirmed = new JButton("ȷ��");
		cancel = new JButton("ȡ��");
//		����ť���ü�����
		confirmed.addActionListener(this);
		cancel.addActionListener(this);
//		��������뵽ҳ����
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
		if (s.equals("ȷ��")) {
			if (IDLegal()) {
				String sql = "update �û����� set password='" + newfield.getText() + "'where userID='" + Main.userID + "'";
				JOptionPane.showMessageDialog(null,"�޸ĳɹ�");
			} else {
				oldfield.setText(null);
				JOptionPane.showMessageDialog(this, "ԭ������д����!", "��Ϣ�Ի���", JOptionPane.ERROR_MESSAGE);
			}
		} else if (s.equals("ȡ��")) {
//			�رմ�ҳ�沢���ء�ϵͳ���á�ҳ��
			dispose();
			Manage manage = new Manage();
		}
	}

	boolean IDLegal() {
//		�������ݿ�
		data.connectionTo();
		//// "select userID from �����ռ�-�û����� where='"+text.getText()+"'"Ϊ��ѯ��sql����
		String sfind;
		sfind = "select password from �û�����";
//		System.out.println(sfind);
		if (data.Query(sfind)) {/// ���鵽,����true
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