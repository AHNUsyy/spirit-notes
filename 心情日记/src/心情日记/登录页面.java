package �����ռ�;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

class Login implements ActionListener {/// ������¼����
	JFrame x = new JFrame("�û���¼"); // �����û���¼�������
	AccessDatabase data = new AccessDatabase();// �������ݿ����
	String sql;
	JTextField nametext;// �û�ID����
	JTextField passwordtext;// �û���������
	JPanel panel = new JPanel();/// �������������һ�������

	public void initUL() {/// �������ں��������ķ���
		// ��������

//		 Container con=x.getContentPane();
//		con.setBackground(Color.pink);
		// ���ô�Сλ��
		x.setSize(350, 250);

		// ���ý������
		x.setLocationRelativeTo(null);
		// ȡ��Ĭ�ϵĲ��ַ�ʽ
		x.setLayout(null);

		// ѡ�����
		JButton login = new JButton("��¼");
		JButton enroll = new JButton("ע��");// ע���Ӣ��enroll
		JButton forget = new JButton("��������");
		JLabel username = new JLabel("�û���ID��");
		JLabel password = new JLabel("��        �룺");
		nametext = new JTextField(20);
		passwordtext = new JTextField(20);
		///
		username.setBorder(null);/// setBorder����Ϊ������ñ߿�
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
		// ������
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

		initImage();/// ���ý���ͼƬ�����ķ���
		// ��Ʋ���
//		 x.setLayout(new FlowLayout());

		// ��Ӽ�������������������������������ť//�¼�Դ
		login.addActionListener(this);
		enroll.addActionListener(this);// this��ʾ���ǵ�ǰ���еĶ���
		forget.addActionListener(this);
		x.setVisible(true);
		x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initImage() {// ����ͼƬ�ķ���
		// TODO Auto-generated method stub
		// ����һ��ͼƬImageIcon�Ķ���

		// ����һ��JLabel�Ķ���
		ImageIcon icon = new ImageIcon("photo/QQͼƬ20230825221114.jpg");
		/// �ı�ͼƬ��С
		icon.setImage(icon.getImage().getScaledInstance(395, 395, Image.SCALE_DEFAULT));
		// ��ͼƬ�ŵ���ǩ��
		JLabel jLabel = new JLabel(icon);
		jLabel.setBounds(0, 0, 395, 395);
		// this.add(jLabel);
		// ��ȡ��������1

		x.getContentPane().add(jLabel);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand().toString();// toString������������ת��ΪString����
		if (s.equals("��¼")) {
			boolean b;///�ж��Ƿ�����ѭ��
			data.connectionTo();// �������ݿ�
			sql = "select userID,password from �û�����";
			if (data.Query(sql)) {
				try {
					while (b=data.result.next()) {
						if (nametext.getText().equals(data.result.getString("userID"))&& passwordtext.getText().equals(data.result.getString("password"))) {
							Main.userID = data.result.getString("userID");
							JOptionPane.showMessageDialog(null, "��¼�ɹ���");
							Homepage homepage = new Homepage();
							x.dispose();
							break;
						}
					}
					if (!b)
						JOptionPane.showMessageDialog(null, "��¼ʧ�ܣ�");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "���ݲ�ѯʧ��");
			}
//			if() {
//				Homepage home=new Homepage();////����������¼֮�����ת������������		
//			    x.dispose();	///��������һ������ʱ�ر�ԭ����		
//			}
		} else if (s.equals("ע��")) {
			Register register = new Register();

			x.dispose();
		} else if (s.equals("��������")) {

			ForgetI forget = new ForgetI();//// ����������������֮�����ת�������������
			///// �رյ�ǰ����
			x.dispose();
		}

	}
}
