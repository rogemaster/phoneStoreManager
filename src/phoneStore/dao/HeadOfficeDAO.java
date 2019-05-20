package phoneStore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import phoneStore.StoreMain;
import phoneStore.dto.StoreDTO;

public class HeadOfficeDAO {

	StoreMain sm;
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public HeadOfficeDAO() {
		
		try {
			
			Class.forName("org.mariadb.jdbc.Driver");
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			
			String jdbcUrl = "jdbc:mariadb://114.199.144.81:3306/gongbubang";
			String userId = "gongbu1";
			String userPw = "2019gong1";
			con = DriverManager.getConnection(jdbcUrl, userId, userPw);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 승인요청 들어온 미승인 스토어 추출
	public List unauthorizedStore() {
		List<StoreDTO> list = new ArrayList<StoreDTO>();
		
		String sql =  "select storeid, storetype, storeaddr, storelevel, con_yn from phone_store where storelevel is not null and con_yn = 'n' limit 5";
		
		try {
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String storeid = rs.getString("storeid");
				String storetype = rs.getString("storetype");
				String storeaddr = rs.getString("storeaddr");
				String storelevel = rs.getString("storelevel");
				String con_yn = rs.getString("con_yn");
				
				list.add(new StoreDTO(storeid, storetype, storeaddr, storelevel, con_yn));
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int approvalUpdateStore(List<StoreDTO> holist) {
		
		String sql = "update phone_store set con_yn = ?, update_date = now() where storeid = ?";
		
		int  rst = 0;
		try {
			
			pstmt = con.prepareStatement(sql);
			
			for(int i = 0; i < holist.size(); i++) {
				
				if(holist.get(i).getCon_yn().equals("n")) {
					pstmt.setString(1, "y");
					pstmt.setString(2, holist.get(i).getStoreId());
				}else {
					pstmt.setString(1, "n");
					pstmt.setString(2, holist.get(i).getStoreId());
				}
				rst = pstmt.executeUpdate();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return rst;
	}
	
	
	
}
