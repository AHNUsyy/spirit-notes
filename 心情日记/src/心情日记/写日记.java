package �����ռ�;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

class write implements ActionListener { // ����д�ռǽ���
	JFrame y = new JFrame();/// �������ڶ���
	AccessDatabase data = new AccessDatabase();// �������ݿ����
	String sinsert;// �����sql���
	JComboBox weather;// ��ʾ���������б�
	JComboBox mood;// ��ʾ���������б�
	JComboBox topic;// ��ʾ���������б�
	JComboBox year;// ��ʾ�������б�
	JComboBox month;// ��ʾ�µ������б�
	JComboBox day;// ��ʾ��������б�
	String f[] = new String[3], sql;// f�ַ����飬����������������������������б�ǰ��ѡ����
	String syear, smonth, sday, time;// syear,smonth,sday�ֱ���������꣬�£��������б�ǰ��ѡ���ݣ�time��ʾ�������ձ�ʾ��ʽ��ʽ��
	JTextField text;// ��ʾ�û������������
	JTextArea area;// ��ʾ�û������ռ�����
	JPanel panel = new JPanel();// ����һ�������������ռ��ı���ͼƬ
	JLabel la = new JLabel();// �ñ�ǩ�������ͼƬ
	ImageIcon image;// ����δ��ʼ����ͼ��ͼ��

	public void initUL() {// ������������������ķ���
		// ��������

		// �������
		y.setTitle("д�ռ�");

		y.setBounds(400, 150, 500, 450);

		// ������
		y.add(new JLabel("����"));
		weather = new JComboBox();
		weather.addItem("����");
		weather.addItem("����");
		weather.addItem("С��");
		weather.addItem("����");
		weather.addItem("���");
		weather.addItem("���ѩ");
		weather.addItem("ѩ��");
		y.add(weather);

		y.add(new JLabel("����"));
		mood = new JComboBox();
		mood.addItem("����");
		mood.addItem("����");
		mood.addItem("��ŭ");
		mood.addItem("����");
		mood.addItem("ƽ��");
		mood.addItem("����");
		mood.addItem("����");
		mood.addItem("�־�");
		y.add(mood);

		y.add(new JLabel("����"));
		topic = new JComboBox();
		topic.addItem("����");
		topic.addItem("ѧϰ");
		topic.addItem("����");
		topic.addItem("�²�");
		topic.addItem("�ƻ�");
		y.add(topic);

		y.add(new JLabel("����"));
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

		y.add(new JLabel("����"));

		text = new JTextField(30);
		y.add(text);
		//
		area = new JTextArea(15, 35);

		panel.setSize(40, 40);
		panel.add(area);
		panel.add(la);
		y.add(panel);

		JButton add = new JButton("���ͼƬ");
		JButton save = new JButton("����");
		JButton empty = new JButton("����½�");
		JButton retur = new JButton("����");

		y.add(add);
		y.add(save);
		y.add(empty);
		y.add(retur);

		// ��Ʋ���
		FlowLayout f = new FlowLayout();
		y.setLayout(f);

		// ��Ӽ�������������������������������ť//�¼�Դ
		add.addActionListener(this);
		save.addActionListener(this);
		empty.addActionListener(this);
		retur.addActionListener(this);
		/// ���ý��汳����ɫ
		Color col = new Color(173, 216, 230);
		y.getContentPane().setBackground(col);
		///
		y.setVisible(true);
		y.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	// ��Ӧ�¼�

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand().toString();
		if (s.equals("���ͼƬ")) {
			image = new ImageIcon("photo/QQͼƬ20230826130913.jpg");
//			System.out.println(image.getDescription());
			
//					System.out.println(write.class.getResource("photo/΢��ͼƬ_20220905180639.jpg"));
			/// ����ͼƬ��С
			image.setImage(image.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
			///��ͼƬ�ŵ�laͼƬ��
			la.setIcon(image);
			la.setText(null);
//					System.out.println("aaa");

		}

		else if (s.equals("����")) {

			//// �����������б����ѡ����ȡ����
			f[0] = weather.getSelectedItem().toString();
			f[1] = mood.getSelectedItem().toString();
			f[2] = topic.getSelectedItem().toString();
			syear = year.getSelectedItem().toString();
			smonth = month.getSelectedItem().toString();
			sday = day.getSelectedItem().toString();
			////
			time = syear + "-" + smonth + "-" + sday;
			data.connectionTo();
			/// ��дsql��佫�ռ����ݲ������ݿ�
			/// �ڽ����ݲ������ݿ���Ҫ�ж��û��Ƿ�ѡ�����ͼƬ
			if (la.getIcon() == null)
				sql = "insert into �ռǹ���(userID,time,text,weather,topic,mood,title) values('" + Main.userID + "','"
						+ time + "','" + area.getText() + "','" + f[0] + "','" + f[2] + "','" + f[1] + "','"
						+ text.getText() + "')";

			else
				sql = "insert into �ռǹ���(userID,time,text,weather,topic,mood,title,photo) values('" + Main.userID + "','"
						+ time + "','" + area.getText() + "','" + f[0] + "','" + f[2] + "','" + f[1] + "','"
						+ text.getText() + "','photo/QQͼƬ20230826130913.jpg')";////���tu'pian'lu
//			System.out.println(sql);

			if (data.Update(sql) > 0) {
				JOptionPane.showMessageDialog(null, "����ɹ���");
			} else {
				JOptionPane.showMessageDialog(null, "���ݿ����ʧ��");
			}
		}

		else if (s.equals("����½�")) {/// Ҫ���ı�ȫ����գ���ͼƬɾ��
			la.setIcon(null);
			area.setText(null);
			text.setText(null);
		}

		else if (s.equals("����")) {
			/// ������������֮�����ת������������
			Homepage home = new Homepage();
			y.dispose();
		}

	}

}
