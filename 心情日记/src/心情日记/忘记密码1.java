package �����ռ�;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.*;

class ForgetI implements ActionListener {/// �����������
	JFrame frame;/// ��������
	JLabel labelI;/// �����û�����ID�ı�ǩ
	JTextField textI;// �û�����ID���ı���
	JLabel labelII;// �����û��޸�����
	JTextField textII;/// �û������޸�������ı���
	/// button1Ϊ���ذ�ť��button2Ϊȷ�ϰ�ť
	JButton button1, button2;
	AccessDatabase data = new AccessDatabase();
	/// sFindΪ��ѯ��sql���ԣ�supdateΪ�޸ĵ�sql����
	String sfind, supdate;

	ForgetI() {
		frame = new JFrame("��������");
		frame.setBounds(500, 200, 350, 200);
		labelI = new JLabel("����ID:");
		textI = new JTextField(25);
		labelII = new JLabel("�޸�����Ϊ��");
		textII = new JTextField(23);
		button1 = new JButton("����");
		button2 = new JButton("ȷ��");

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
		String s = e.getActionCommand().toString();// ��ȡ�¼�Դ
		if (s.equals("����")) {
			//// dispose�����رյ�ǰҳ��
			frame.dispose();
			Login login = new Login();
			login.initUL();
		} 
		else {
//			System.out.println("diu");
			boolean b;///�ж��Ƿ�����ѭ��
			/// ����Ҫ�������ݿ�
			data.connectionTo();
			//// "select number from �����ռ�-�û����� where='"+text.getText()+"'"Ϊ��ѯ��sql����
			sfind = "select userID from �û�����";//// ��ѯ�����û�id
//			System.out.println(sql);
			if (data.Query(sfind)) {/// ������true,���ѯ��
				try {
					//// ������id���ҵ��ı�����
					while (b=data.result.next()) {
						if (textI.getText().equals(data.result.getString("userID"))) {
							break;///// �ҵ��˾�����ѭ��
						}
					}
					/// �����ǰ����ѭ����data.result.next()Ϊtrue
					if (b) {
						supdate = "update �û����� set password='" + textII.getText() + "'" + "where userID='"
								+ textI.getText() + "'";
						/// �޸���Ϣ
						if (data.Update(supdate) > 0)
							JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
					}
					else {
						JOptionPane.showMessageDialog(null, "�û�ID�޷���ѯ", "����", JOptionPane.WARNING_MESSAGE);
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