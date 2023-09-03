package 心情日记;

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

class AccessDatabase {// 建立数据库类连接数据库
	Connection con = null;
	Statement statement = null;
	ResultSet result = null;
	String f = "C:\\Users\\邵园shao\\Documents\\Database3.accdb";

	public void connectionTo() {
		try {
			Class.forName("com.hxtt.sql.access.AccessDriver");// 加载驱动
			con = DriverManager.getConnection("jdbc:Access:///" + f);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "数据库连接失败");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (con == null)
			JOptionPane.showMessageDialog(null, "数据库连接失败！");
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

	public boolean Query(String sql) {///// 用来查询数据
		if (con != null) {
			try {
				result = statement.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "SQL语句语法错误");
				e.printStackTrace();
			}
			if (result != null)
				return true;
			else
				return false;
		} else
			return false;
	}

	public int Update(String sql) {/// 用来对数据库中的进行增添修改删除
		int r = -1;
		if (con != null) {

			try {
				r = statement.executeUpdate(sql);// 若查询到则r大于0

			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "SQL语句语法错误");
				e.printStackTrace();
			}
		}
		return r;
	}
}
