package phoneStore.dao;

import java.awt.Dialog;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import phoneStore.StoreMain;

public class PhoneStoreDAO {
	
	StoreMain sm;

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public PhoneStoreDAO() {
		
		try {
			
			Class.forName("org.mariadb.jdbc.Driver");
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
									// 프로토콜 	|| url~~
			String jdbcUrl = "jdbc:mariadb://114.199.144.81:3306/gongbubang";
			String userId = "gongbu1";
			String userPw = "2019gong1";
			con = DriverManager.getConnection(jdbcUrl, userId, userPw);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 아이디 체크( 입력된 아이디가 있으면 1, 없으면 0 )
	public int chkId(String id) {
		
		String sql = "select storeid, storepw from phone_store where storeid = ?";
		int idChk = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			String dbID = "";
			
			if(rs.next()) {
				dbID = rs.getString("storeid");
				if(id.equals(dbID)) {
					idChk = 1;
				}
			}else {
				idChk = 0;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		// 0 :: ID 없음 / 1 :: ID 있음
		System.out.println("id 체크 = " + idChk);
		return idChk;
	}
	
	// 패스워드 추출
	public int chkPw(String id, String pw) {
		
		String sql = "select storepw from phone_store where storeid = ?";
		int chkPw = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			String dbPW = "";
			
			if(rs.next()) {
				dbPW = rs.getString("storepw");
				if(pw.equals(dbPW)) {
					chkPw = 1;
				}
			}else {
				chkPw = 0;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("pw 체크 = " + chkPw);
		return chkPw;
	}
	
	// 로그인 체크
	public int login(String id, String pw) {
		int logInChk = 0;
		int chkid = chkId(id);
		int chkpw = chkPw(id, pw);
		
		//0 => 가입계정 없음 | 1 => ID만 맞음, PW 틀림 | 2 => ID,PW 둘다 맞음
		if(chkid == 1 && chkpw == 1) {		
			logInChk = 2;
		}else if(chkid == 1 && chkpw == 0) {
			logInChk = 1;
		}
		
		System.out.println("계정 체크 = " + logInChk);
		return logInChk;
	}
	
	// 새로운 계정 입력
	public int join(String id, String pw) {
		int result = 0;

		String sql = "insert into phone_store(storeid,storepw,update_date,regi_date) values(?,?,now(),now())";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			result = pstmt.executeUpdate();
			System.out.println("결과 = " + result);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 매장 업데이트
	public int storeUpdate(String sale, String addr, String type, String id) {
		int result = 0;
		
		String sql = "update phone_store set storetype=?, storeaddr=?, storelevel=?, con_yn='n', update_date = now() where storeid = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sale);
			pstmt.setString(2, addr);
			pstmt.setString(3, type);
			pstmt.setString(4, id);
			result = pstmt.executeUpdate();
			System.out.println("결과 = " + result);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 매장 레벨 추출
	public String getLevel(String id) {
		String storeLevel = "";
		
		String sql = "select storelevel from phone_store where storeid = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				storeLevel = rs.getString("storelevel");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return storeLevel;
	}
	
}
