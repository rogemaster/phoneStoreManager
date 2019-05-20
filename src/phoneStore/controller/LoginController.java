package phoneStore.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import phoneStore.StoreMain;
import phoneStore.dao.PhoneStoreDAO;

public class LoginController {

	StoreMain sm;
	PhoneStoreDAO psDao = new PhoneStoreDAO();
	Map<String, String> saveData = new HashMap<>();		// 데이터 유지 저장소
	
	public LoginController(StoreMain sm) {
		this.sm = sm;
		sm.sw.loginBtn.addActionListener(btnAction());
		sm.sw.storeBtn.addActionListener(storeUpdate());
	}
	
	// 로그인 액션 리스트
	public ActionListener btnAction() {
		
		ActionListener actionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				int chk = psDao.login(sm.sw.textId.getText(), sm.sw.textPw.getText());
				saveData.put("id", sm.sw.textId.getText());
				
				if(chk == 2) {
					String storeLevel = psDao.getLevel(saveData.get("id"));
					System.out.println("storeLv = " + storeLevel);
					
					if(storeLevel == null) {
						sm.sw.noPassStoreView();
					}else if(storeLevel.equals("L1")) {		// 본사
						sm.sw.headOfficeView();
					}else if(storeLevel.equals("L2")) {		// 대리점
						sm.sw.agencyStoreView();
					}else if(storeLevel.equals("L3")) {			// 판매점
						sm.sw.saleStoreView();
					}
					
				}else if(chk == 1) {
					JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 다시 확인하세요.");
				}else {
					// 예 :: 0	//	아니요 :: 1	// 취소 :: 2
					int joinChk = JOptionPane.showConfirmDialog(null, "가입된 내역이 없습니다. 회원가입 하시겠습니까?");
					System.out.println("신규가입 유무 = " + joinChk);
					
					if(joinChk == 0) {
						sm.sw.joinBtnChange();
					}else {
						sm.sw.textId.setText("");
						sm.sw.textPw.setText("");
					}
				}
			}
		};
		return actionListener;
	}
	
	// 가입 액션리스너
	public ActionListener joinAction() {
		ActionListener actionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				int chk = JOptionPane.showConfirmDialog(null, "가입하시겠습니까?");
				System.out.println("가입동의 유무 = " + chk);
				
				if(chk == 0) {
					int joinChk = psDao.join(sm.sw.textId.getText(), sm.sw.textPw.getText());
					System.out.println("가입 유무 = " + joinChk);
					
					if(joinChk != 0) {
						JOptionPane.showMessageDialog(null, "가입되었습니다.");
						saveData.put("id", sm.sw.textId.getText());
						sm.sw.storePanel.setLayout(null);
						sm.sw.setContentPane(sm.sw.storePanel);
						sm.sw.setVisible(true);
						
					}else {
						JOptionPane.showMessageDialog(null, "가입에 실패 했습니다.");
					}
				}
			}
		};
		return actionListener;
	}
	
	// 스토어 업데이트 액션 리스너
	public ActionListener storeUpdate() {
		ActionListener actionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String sale = (String)sm.sw.saletype_cbbox.getSelectedItem();
				String addr = sm.sw.textAddr.getText();
				String type = (String)sm.sw.storetype_cbbox.getSelectedItem();
				String id = saveData.get("id");
				
				if(type.equals("대리점")) {
					type = "L2";
				}else if(type.equals("판매점")) {
					type = "L3";
				}
				
				int updateChk = psDao.storeUpdate(sale, addr, type, id);
				
				if(updateChk != 0) {
					JOptionPane.showMessageDialog(null, "업데이트 되었습니다.");
				}else {
					JOptionPane.showMessageDialog(null, "업데이트가 실패 되었습니다.");
				}
			}
		};
		return actionListener;
	}
	
	
}
