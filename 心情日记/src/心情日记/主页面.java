package �����ռ�;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Homepage implements ActionListener {// ������ҳ��
	JFrame frame;
//	JLabel label;
	/// �ֱ��ʾд�ռǣ����ռǣ�ϵͳ���ð�ť
	JButton writer, reseacher, setting;

	Homepage() {
		frame = new JFrame("��ҳ��");
		frame.setBounds(500, 200, 400, 300);
//		label=new JLabel("��ҳ��");
		writer = new JButton("д�ռ�");
		reseacher = new JButton("�����ռ�");
		setting = new JButton("ϵͳ����");
		//// Ϊÿһ����ť����һ��������
		writer.addActionListener(this);
		reseacher.addActionListener(this);
		setting.addActionListener(this);
		JPanel panel = new JPanel();
		panel.setBounds(100, 200, 20, 20);
		panel.setLayout(new GridLayout());
		panel.add(writer);
		panel.add(reseacher);
		panel.add(setting);
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand().toString();
		if (s.equals("д�ռ�")) {
			//// ��������д�ռ�֮�����ת�������ռǽ���
//			ForgetI f=new ForgetI();
			/// ��������һ������ʱ�ر�ԭ����
			write w = new write();
			w.initUL();
			frame.dispose();
		} else {
			if (s.equals("�����ռ�")) {
				//// ��������д�ռ�֮�����ת�������ռǽ���
				Find r = new Find();
				/// ��������һ������ʱ�ر�ԭ����
				frame.dispose();
			} else {
				Manage manage = new Manage();
				frame.dispose();
			}
		}
	}
}
