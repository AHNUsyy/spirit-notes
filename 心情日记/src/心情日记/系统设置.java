package �����ռ�;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

class Manage extends JFrame implements ActionListener {/// ����ϵͳ���ý�����
	///// ���·ֱ�Ը����˻��������޸ģ�ע���˺ţ��˳���¼�����ذ�ť
	JButton userbutton, passwordbutton, cancelbutton, exitbutton, backbutton;
	AccessDatabase data = new AccessDatabase();

//	JPanel panel=new JPanel();
	public Manage() {
		setTitle("ϵͳ����");
		setSize(300, 300);
		// ���ý������
		setLocationRelativeTo(null);
		init();
		setLayout(new FlowLayout());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void init() {
//		����ť��������
		userbutton = new JButton("�����˻�");
		passwordbutton = new JButton("�޸�����");
		cancelbutton = new JButton("ע���˺�");
		exitbutton = new JButton("�˳���¼");
		backbutton = new JButton("����");
//		���ð�ť�Ĵ�С
		userbutton.setPreferredSize(new Dimension(200, 40));
		passwordbutton.setPreferredSize(new Dimension(200, 40));
		cancelbutton.setPreferredSize(new Dimension(200, 40));
		exitbutton.setPreferredSize(new Dimension(200, 40));
		backbutton.setPreferredSize(new Dimension(100, 40));
//		��ÿ����ťע������� 
		userbutton.addActionListener(this);
		passwordbutton.addActionListener(this);
		cancelbutton.addActionListener(this);
		exitbutton.addActionListener(this);
		backbutton.addActionListener(this);
//		�������ӽ�ҳ����
		add(userbutton);
		add(passwordbutton);
		add(cancelbutton);
		add(exitbutton);
		add(backbutton);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand().toString();
		if (s.equals("�����˻�")) {
			dispose();
//			��ת�������˻���ҳ��
			User user = new User();
		} else if (s.equals("�޸�����")) {
			dispose();
//			��ת���޸������ҳ��
			Password password = new Password();
		} else if (s.equals("ע���˺�")) {
//			�������ݿ�
			data.connectionTo();
//			ע�����˻�
			String sql1 = "delete from �û����� where userID='" + Main.userID + "'";
			String sql2 = "delete from �ռǹ��� where userID='" + Main.userID + "'";
//			����ɾ��
			if (data.Update(sql1) > 0) {
				if (data.Update(sql2) > 0)
					JOptionPane.showMessageDialog(null, "ע���ɹ�");
			} else {
				JOptionPane.showMessageDialog(null, "����ɾ��ʧ��");
			}
//			�Զ����ص�¼ҳ��
			dispose();
			Login login = new Login();
			login.initUL();
		} else if (s.equals("�˳���¼")) {
//			�˳����û����˺�
			dispose();
//			�Զ����ص�¼ҳ��
			Login login = new Login();
			login.initUL();
		} else if (s.equals("����")) {
//			���ص���ҳ��
			dispose();
			Homepage homepage = new Homepage();
		}
	}
}