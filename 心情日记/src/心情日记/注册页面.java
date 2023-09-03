package �����ռ�;

import java.awt.BorderLayout;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Register extends JFrame implements ActionListener {/// ����ע��ҳ��
	/// ���±�ʾ��ǩ���û������û�ID�������룬ȷ������
	JLabel username, userID, newpassword, confirmedpassword;
	////
	Box boxH1, boxH2, boxH3, boxH4;
	Box boxV;
	//// ���±�ʾ�ı����û������û�ID�������룬ȷ������
	JTextField text1, text2, text3, text4;
	// ���±�ʾע�ᣬ���ذ�ť
	JButton registerbutton, backbutton;
	AccessDatabase data = new AccessDatabase();

//	************************************************************************************************************************************
	public Register() {
		setTitle("�û�ע��");
		setLayout(new FlowLayout());
		init();
		setSize(300, 300);
		// ���ý������
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

//	************************************************************************************************************************************
	void init() {/// �ڴ����������ķ���
// 		����Ա����ʵ����
		username = new JLabel("��    ��    ����");
		userID = new JLabel("��    ��    ID��");
		newpassword = new JLabel("��    ��    �룺");
		confirmedpassword = new JLabel("ȷ�������룺");
		registerbutton = new JButton("ע��");
		backbutton = new JButton("����");
//		���������ͺ�ʽ����
		boxH1 = Box.createHorizontalBox();
		boxH2 = Box.createHorizontalBox();
		boxH3 = Box.createHorizontalBox();
		boxH4 = Box.createHorizontalBox();
//		���һ�����ͺ�ʽ����
		boxV = Box.createVerticalBox();
//		���ı��򴴽�����
		text1 = new JTextField(20);
		text2 = new JTextField(20);
		text3 = new JTextField(20);
		text4 = new JTextField(20);
//		����ǩ���Ӧ���ı���������ͺ�ʽ������
		boxH1.add(username);
		boxH1.add(text1);
		boxH2.add(userID);
		boxH2.add(text2);
		boxH3.add(newpassword);
		boxH3.add(text3);
		boxH4.add(confirmedpassword);
		boxH4.add(text4);
//		��������ͺ�ʽ��������һ�����ͺ�ʽ����
		boxV.add(boxH1);
		boxV.add(Box.createVerticalStrut(20));
		boxV.add(boxH2);
		boxV.add(Box.createVerticalStrut(20));
		boxV.add(boxH3);
		boxV.add(Box.createVerticalStrut(20));
		boxV.add(boxH4);
		boxV.add(Box.createVerticalStrut(20));
		add(boxV);
//		����ťע�������
		registerbutton.addActionListener(this);
		backbutton.addActionListener(this);
		add(backbutton);
		add(registerbutton);
	}

	public void actionPerformed(ActionEvent e) {
//		��ȡ�¼�Դ
		String s = e.getActionCommand().toString();
		if (s.equals("����")) {
//			diapose�رյ�ǰҳ��
			this.dispose();
			Login login = new Login();
			login.initUL();
		} else if (s.equals("ע��")) {
//			�ж��û��Ƿ��ע����Ϣ��д����
			if (text2.getText().equals("") || text3.getText().equals("") || text4.getText().equals("")) {
//				����Ϣδ��д�������򵯳�һ���Ի�����ʾ�û�
				JOptionPane.showMessageDialog(this, "��Ϣδ��д����!", "��Ϣ�Ի���", JOptionPane.ERROR_MESSAGE);
			}
//			�ж��û���д��ID�Ƿ�Ϸ�
			else if (!IDcheck()) {
//				���û�ID���Ϸ����򵯳�һ���Ի�����ʾ���޸�ID,ͬʱ�������ID���
				text2.setText(null);
				JOptionPane.showMessageDialog(this, "ID��д����!", "��Ϣ�Ի���", JOptionPane.ERROR_MESSAGE);
			}
//			�ж��û���д��ID�Ƿ��Ѿ�ע����˻���
			else if (IDLegal()) {
//				�����ݿ��в��ҵ���ID���򽫳��ֹ���ID���
				text2.setText(null);
				JOptionPane.showMessageDialog(this, "���û��Ѵ���!", "��Ϣ�Ի���", JOptionPane.ERROR_MESSAGE);
			}
//			�ж���д�������������Ƿ���ͬ
			else if (!SomePassword(text3, text4)) {
				text4.setText(null);
				JOptionPane.showMessageDialog(this, "������д�����벻��ͬ!", "��Ϣ�Ի���", JOptionPane.ERROR_MESSAGE);
			} else {
				data.connectionTo();
				String sql = "insert into �û�����(username,userID,password) values ('" + text1.getText() + "','"
						+ text2.getText() + "','" + text3.getText() + "')";
//				System.out.println(sql);
				if (data.Update(sql) > 0) {
					JOptionPane.showMessageDialog(null, "ע��ɹ�");
				} else {
					JOptionPane.showMessageDialog(this, "���ݿ����ʧ��", "��Ϣ�Ի���", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

//	**************************************�ж��û������ID�Ƿ�Ϸ�**************************************************************************
	int year, month, day, yu, sum;

	boolean IDcheck() {
		String ID = text2.getText();
		if (!ID.matches("[1-9][0-9]{5}(19|20)[0-9]{9}[0-9Xx]")) {// һ�����׵�������ʽ���ж����֤��ǰ17λ�Ƿ�Ϊ���֣���һλ������0����7/8λ��19��20�����һλ�����ֻ�X��x����������ֱ��ʹ�á�
			return false;
		}
		year = Integer.parseInt(ID.substring(6, 10)); // ��ȡ���֤�����еĳ������
		month = Integer.parseInt(ID.substring(10, 12)); // ��ȡ���֤�����еĳ����·�
		day = Integer.parseInt(ID.substring(12, 14)); // ��ȡ���֤�����еĳ�����
		if (!dateLegal()) {// �жϳ��������Ƿ�Ϸ�����Ҫ���¡��յĺϷ���
			return false;
		}
		char ch[] = ID.toCharArray();// ���ַ���ת�����ַ�����
		// ���ݶ��壬�����Ȩ�ܺ�sum
		sum = 7 * (ch[0] - '0') + 9 * (ch[1] - '0') + 10 * (ch[2] - '0') + 5 * (ch[3] - '0') + 8 * (ch[4] - '0')
				+ 4 * (ch[5] - '0') + 2 * (ch[6] - '0') + 1 * (ch[7] - '0') + 6 * (ch[8] - '0') + 3 * (ch[9] - '0')
				+ 7 * (ch[10] - '0') + 9 * (ch[11] - '0') + 10 * (ch[12] - '0') + 5 * (ch[13] - '0')
				+ 8 * (ch[14] - '0') + 4 * (ch[15] - '0') + 2 * (ch[16] - '0');
		yu = sum % 11;
		// ����������Ӧ��ϵ���ж����֤�����һλ�Ƿ���ȷ
		if (yu == 0 && ch[17] == '1')
			return true;
		else if (yu == 1 && ch[17] == '0')
			return true;
		else if (yu == 2 && (ch[17] == 'X' || ch[17] == 'x'))
			return true;
		else if (yu == 3 && ch[17] == '9')
			return true;
		else if (yu == 4 && ch[17] == '8')
			return true;
		else if (yu == 5 && ch[17] == '7')
			return true;
		else if (yu == 6 && ch[17] == '6')
			return true;
		else if (yu == 7 && ch[17] == '5')
			return true;
		else if (yu == 8 && ch[17] == '4')
			return true;
		else if (yu == 9 && ch[17] == '3')
			return true;
		else if (yu == 10 && ch[17] == '2')
			return true;
		else
			return false;
	}

	boolean dateLegal() {
		// �жϳ��������Ƿ�Ϸ�
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			if (day >= 0 && day <= 31) {
				return true;
			} else
				return false;
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			if (day >= 0 && day <= 30) {
				return true;
			} else
				return false;
		} else if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			if (month == 2 && day >= 0 && day <= 29) {
				return true;
			} else
				return false;
		} else if (month == 2 && day >= 0 && day <= 28) {
			return true;
		} else
			return false;
	}

	// ************************************�жϸ�ID�Ƿ�ע����˻���*******************************************
	boolean IDLegal() {
//			�������ݿ�
		data.connectionTo();
		//// "select userID from �����ռ�-�û����� where='"+text.getText()+"'"Ϊ��ѯ��sql����
		String sfind;
		sfind = "select userID from �û����� ";
//			System.out.println(sfind);
		if (data.Query(sfind)) {/// ���鵽,����true
			try {
				while (data.result.next()) {
					if (text2.getText().equals(data.result.getString("userID"))) {
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

//		***********************************�ж�������д�������Ƿ���ͬ**********************************************************
	boolean SomePassword(JTextField field1, JTextField field2) {
		String s1 = field1.getText();
		String s2 = field2.getText();
		if (s1.equals(s2))
			return true;
		else
			return false;
	}
}
