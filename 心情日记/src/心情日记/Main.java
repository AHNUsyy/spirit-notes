package �����ռ�;

import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.IOException;
import java.awt.*;
import java.sql.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Main {
	static String userID;

	public static void main(String args[]) {
		Login login = new Login();
		login.initUL();
//		Find f=new Find();
//		write w=new write();
//		w.initUL();
	}
}

class AccessDatabase {// �������ݿ����������ݿ�
	Connection con = null;
	Statement statement = null;
	ResultSet result = null;
	String f = "C:\\Users\\��԰shao\\Documents\\Database3.accdb";

	public void connectionTo() {
		try {
			Class.forName("com.hxtt.sql.access.AccessDriver");// ��������
			con = DriverManager.getConnection("jdbc:Access:///" + f);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "���ݿ�����ʧ��");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (con == null)
			JOptionPane.showMessageDialog(null, "���ݿ�����ʧ�ܣ�");
		setState();
	}

	public void setState() {
		try {
			statement = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean Query(String sql) {///// ������ѯ����
		if (con != null) {
			try {
				result = statement.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "SQL����﷨����");
				e.printStackTrace();
			}
			if (result != null)
				return true;
			else
				return false;
		} else
			return false;
	}

	public int Update(String sql) {/// ���������ݿ��еĽ��������޸�ɾ��
		int r = -1;
		if (con != null) {

			try {
				r = statement.executeUpdate(sql);// ����ѯ����r����0

			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "SQL����﷨����");
				e.printStackTrace();
			}
		}
		return r;
	}
}
