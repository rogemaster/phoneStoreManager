package phoneStore.controller;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import phoneStore.StoreMain;
import phoneStore.dao.HeadOfficeDAO;
import phoneStore.dto.StoreDTO;

public class HeadOfficeController {

	StoreMain sm;
	
	HeadOfficeDAO hodao = new HeadOfficeDAO();
	
	List<StoreDTO> hodto = new ArrayList<>();
	
	public HeadOfficeController(StoreMain sm) {
		this.sm = sm;
		sm.sw.menuItem1_HO_storeApproval.addActionListener(storeApprovalViewAction());
		sm.sw.menuItem2_HO_phoneApproval.addActionListener(phoneApprovalViewAction());
	}
	
	public void storeApprovaView() {
		hodto = hodao.unauthorizedStore();
		
		if(hodto.size() == 0) {
			sm.sw.ho_regiapprovalArea.setText("승인 요청 리스트가 없습니다.");
		}else if(hodto.size() == 1) {
			sm.sw.headOfficePanel.add(sm.sw.ho_regiapprovalBtn1, null);
		
		}else if(hodto.size() == 2) {
			sm.sw.headOfficePanel.add(sm.sw.ho_regiapprovalBtn1, null);
			sm.sw.headOfficePanel.add(sm.sw.ho_regiapprovalBtn2, null);
			
		}else if(hodto.size() == 3) {
			sm.sw.headOfficePanel.add(sm.sw.ho_regiapprovalBtn1, null);
			sm.sw.headOfficePanel.add(sm.sw.ho_regiapprovalBtn2, null);
			sm.sw.headOfficePanel.add(sm.sw.ho_regiapprovalBtn3, null);
			
		}else if(hodto.size() == 4) {
			sm.sw.headOfficePanel.add(sm.sw.ho_regiapprovalBtn1, null);
			sm.sw.headOfficePanel.add(sm.sw.ho_regiapprovalBtn2, null);
			sm.sw.headOfficePanel.add(sm.sw.ho_regiapprovalBtn3, null);
			sm.sw.headOfficePanel.add(sm.sw.ho_regiapprovalBtn4, null);
			
		}else if(hodto.size() == 5) {
			sm.sw.headOfficePanel.add(sm.sw.ho_regiapprovalBtn1, null);
			sm.sw.headOfficePanel.add(sm.sw.ho_regiapprovalBtn2, null);
			sm.sw.headOfficePanel.add(sm.sw.ho_regiapprovalBtn3, null);
			sm.sw.headOfficePanel.add(sm.sw.ho_regiapprovalBtn4, null);
			sm.sw.headOfficePanel.add(sm.sw.ho_regiapprovalBtn5, null);
			
		}
		sm.sw.headOfficePanel.add(sm.sw.ho_regiapprovalBtnSubmit, null);
		sm.sw.headOfficePanel.add(sm.sw.ho_regiapproval_RefreshBtn, null);
		
		String unauthStore = "";
		int cnt = 1;
		
		for(int i = 0; i < hodto.size(); i++) {
			unauthStore = "NO. " + cnt + " ▷▷" + "【  ID : " + hodto.get(i).getStoreId() + " / Type : " + hodto.get(i).getStoreType()
								+ " / Level : " + hodto.get(i).getStoreLevel() + " / Addr : " + hodto.get(i).getStoreAddr() + " 】" + "\n";
			
			sm.sw.ho_regiapprovalArea.append(unauthStore);
			cnt++;
		}
	}
	
	// 새로고침
	public ActionListener refreshBtnAction() {
		// TODO Auto-generated method stub
		
		ActionListener actionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				hodto = hodao.unauthorizedStore();
				sm.sw.ho_regiapprovalArea.setText("");
				
				String unauthStore = "";
				int cnt = 1;
				
				for(int i = 0; i < hodto.size(); i++) {
					unauthStore = "NO. " + cnt + " ▷▷" + "【  ID : " + hodto.get(i).getStoreId() + " / Type : " + hodto.get(i).getStoreType()
										+ " / Level : " + hodto.get(i).getStoreLevel() + " / Addr : " + hodto.get(i).getStoreAddr() + " 】" + "\n";
					
					sm.sw.ho_regiapprovalArea.append(unauthStore);
					cnt++;
				}
				sm.sw.ho_regiapprovalBtn1.setSelected(false);
				sm.sw.ho_regiapprovalBtn2.setSelected(false);
				sm.sw.ho_regiapprovalBtn3.setSelected(false);
				sm.sw.ho_regiapprovalBtn4.setSelected(false);
				sm.sw.ho_regiapprovalBtn5.setSelected(false);
			}
		};
		return actionListener;
	}

	// 승인 업데이트
	public ActionListener approvalSubmitAction() {
		// TODO Auto-generated method stub
		
		ActionListener actionListener = new ActionListener() {
		List<StoreDTO> uphodto = new ArrayList<>();
		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			
				if(sm.sw.ho_regiapprovalBtn1.isSelected()) {
					uphodto.add(hodto.get(0));
				}
				if(sm.sw.ho_regiapprovalBtn2.isSelected()) {
					uphodto.add(hodto.get(1));
				}
				if(sm.sw.ho_regiapprovalBtn3.isSelected()) {
					uphodto.add(hodto.get(2));
				}
				if(sm.sw.ho_regiapprovalBtn4.isSelected()) {
					uphodto.add(hodto.get(3));
				}
				if(sm.sw.ho_regiapprovalBtn5.isSelected()) {
					uphodto.add(hodto.get(4));
				}
				
				int rst = hodao.approvalUpdateStore(uphodto);
				System.out.println("updata result = " + rst);
				
				if(rst != 0) {
					JOptionPane.showMessageDialog(null, "수정 되었습니다.");
					autoRefresh();
				}else {
					JOptionPane.showMessageDialog(null, "수정에 오류가 발생 했습니다. 관리자에게 문의 바랍니다.");
				}
			}
		};
		return actionListener;
	}
	
	// 자동 새로고침
	public void autoRefresh() {
		
		hodto = hodao.unauthorizedStore();
		sm.sw.ho_regiapprovalArea.setText("");
		
		String unauthStore = "";
		int cnt = 1;
		
		for(int i = 0; i < hodto.size(); i++) {
			unauthStore = "NO. " + cnt + " ▷▷" + "【  ID : " + hodto.get(i).getStoreId() + " / Type : " + hodto.get(i).getStoreType()
								+ " / Level : " + hodto.get(i).getStoreLevel() + " / Addr : " + hodto.get(i).getStoreAddr() + " 】" + "\n";
			
			sm.sw.ho_regiapprovalArea.append(unauthStore);
			cnt++;
		}
		sm.sw.ho_regiapprovalBtn1.setSelected(false);
		sm.sw.ho_regiapprovalBtn2.setSelected(false);
		sm.sw.ho_regiapprovalBtn3.setSelected(false);
		sm.sw.ho_regiapprovalBtn4.setSelected(false);
		sm.sw.ho_regiapprovalBtn5.setSelected(false);
	}
	
	public void phoneApprovalView() {
		
	}
	
	// 매장등록승인 화면
	public ActionListener storeApprovalViewAction() {
		ActionListener actionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					sm.sw.ho_regiapprovalArea.setText("");
					sm.sw.headOfficeView();
			}
		};
		return actionListener;
	}
	
	// 휴대폰 등록 화면
	public ActionListener phoneApprovalViewAction() {
		ActionListener actionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sm.sw.phoneApprovalView();
			}
		};
		return actionListener;
	}
	
}
