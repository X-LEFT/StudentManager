package com.xcx;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import javax.management.StringValueExp;

import org.junit.Test;
import org.omg.CORBA.portable.ValueOutputStream;

import com.jdbc.util.JdbcUtil;

public class StudentManager {
	private static Scanner sc = new Scanner(System.in);// 扫描器 设置为静态 供全局使用

	public static void main(String[] args) {
		while (true) {
			System.out.println("a:查询学生信息   b:添加学生信息  c:修改学生信息 d:删除学生信息");
			System.out.println("请输入操作,abcd任选一项:");
			char in = sc.next().charAt(0);// 接收字符串的第一个字符
			// String.equals("a") || char=='a'
			switch (in) {
			case 'a':
				FindStu();
				break;
			case 'b':
				AddStu();
				break;
			case 'c':
				UpdateStu();
				break;
			case 'd':
				DeleteStu();
				break;
			default:
				System.out.println("没有此功能,请重新输入");
				break;
			}
		}
	}

	// ---------------------------------------------------------
	@Test
	private static void AddStu() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConn();
			stat = conn.createStatement();
			sc.nextLine();// 吸收上次输入的回车符
			System.out.println("请输入要添加的学生编号");
			String stuid = sc.nextLine();
			System.out.println("请输入姓名");
			String name = sc.nextLine();
			System.out.println("请输入性别");
			String gender = sc.nextLine();
			System.out.println("请输入地址");
			String addr = sc.nextLine();
			System.out.println("请输入成绩");
			double score = sc.nextDouble();
			// String sql="insert into stu"
			// +" values('"+stuid+"','"+name+"','"+gender+"','"+addr+"',"+score;//少了一个括号
			String sql = "insert into stu values (" + "'" + stuid + "','" + name + "','" + gender + "','" + addr + "',"
					+ score + ")";
			int rows = stat.executeUpdate(sql);
			System.out.println("添加了" + rows + "条信息");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, stat, conn);
		}
	}

	// ----------------------------------------------------------
	private static void DeleteStu() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConn();
			stat = conn.createStatement();
			System.out.println("请输入要删除的学生编号");
			String in = new Scanner(System.in).nextLine();
			String sql = "delete from stu " + "where stuid='" + in + "'";// delete记录没有*
																			// 拼接时记得加引号
			int rows = stat.executeUpdate(sql);
			System.out.println("删除了" + rows + "条记录!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, stat, conn);
		}
	}

	// ----------------------------------------------------------
	private static void UpdateStu() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn=JdbcUtil.getConn();
			stat=conn.createStatement();
			sc.nextLine();
			System.out.println("请输入要修改的学生编号");
			String stuid=sc.nextLine();
			System.out.println("请输入要修改的姓名");
			String name=sc.nextLine();
			System.out.println("请输入要修改的性别");
			String gender=sc.nextLine();
			System.out.println("请输入要修改的地址");
			String addr=sc.nextLine();
			System.out.println("请输入要修改的成绩");
			double score=sc.nextDouble();
			String sql="update stu set name='"+name+"',gender='"+gender+"',addr='"+addr+"',score="+score+" where stuid='"+stuid+"'";
			int rows=stat.executeUpdate(sql);
			System.out.println("修改了"+rows+"条数据");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs, stat, conn);
		}

	}

	// ----------------------------------------------------------
	private static void FindStu() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConn();
			stat = conn.createStatement();
			String sql = "select * from stu";
			rs = stat.executeQuery(sql);
			while(rs.next()) {
				String stuid = rs.getString("stuid");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				String addr = rs.getString("addr");
				double score = rs.getDouble("score");
				System.out.println(stuid + ":" + name + ":" + gender + ":" + addr + ":" + score);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, stat, conn);
		}

	}
}
