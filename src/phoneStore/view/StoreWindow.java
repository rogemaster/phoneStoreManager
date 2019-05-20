package phoneStore.view;

import java.awt.Checkbox;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

import phoneStore.StoreMain;
import phoneStore.dao.PhoneStoreDAO;
import java.awt.Toolkit;

public class StoreWindow extends JFrame {
	
	public StoreMain sm;
	
	public JPanel loginPanel = new JPanel();			//	로그인 화면 페널
	public JTextField textId = new JTextField();			// 아이디 입력필드
	public JPasswordField textPw = new JPasswordField();			// 패스워드 입력 필드
	public JButton loginBtn = new JButton();				// 로그인 버튼
	public JButton joinBtn = new JButton();				// 회원가입 버튼
	public ImageIcon img;
	public JLabel ptlb1 = new JLabel("아이디");
	public JLabel ptlb2 = new JLabel("비밀번호");
	
	public JPanel storePanel = new JPanel();				// 스토어 화면 패널
	public JTextField textAddr = new JTextField();		// 주소 입력필드
	public JButton storeBtn = new JButton();				// 스토어 등록(업데이트) 버튼
	public JComboBox<String> storetype_cbbox;		// 매장 타입 토글박스
	public JComboBox<String> saletype_cbbox;		// 매장 구분 토글 박스
	String[] storetype = {"대리점","판매점"};
	String[] saletype = {"ALL","SKT","KT","LGT"};
	
	// 메뉴 바
	public JMenuBar menubar_HO = new JMenuBar();
	public JMenu menu_HO = new JMenu("등록관리");
	public JMenuItem menuItem1_HO_storeApproval = new JMenuItem();
	public JMenuItem menuItem2_HO_phoneApproval = new JMenuItem();
	
	// 휴대폰 등록 관리 패널
	public JPanel phoneregApproval_panel = new JPanel();
	public JTextField phoneregApproval_phoneId = new JTextField();
	public JTextField phoneregApproval_phoneName = new JTextField();
	public JTextField phoneregApproval_phonePrice = new JTextField();
	public JTextField phoneregApproval_phoneSearch = new JTextField();
	public JButton phoneregApproval_regBtn = new JButton();
	public JButton phoneregApproval_modifyBtn = new JButton();
	public JButton phoneregApproval_searchBtn = new JButton();
	
	// 본사화면 구성
	public JPanel headOfficePanel = new JPanel();
	public JTextArea ho_regiapprovalArea = new JTextArea();
	public JButton ho_regiapprovalBtnSubmit = new JButton();
	public JToggleButton ho_regiapprovalBtn1 = new JToggleButton();
	public JToggleButton ho_regiapprovalBtn2 = new JToggleButton();
	public JToggleButton ho_regiapprovalBtn3 = new JToggleButton();
	public JToggleButton ho_regiapprovalBtn4 = new JToggleButton();
	public JToggleButton ho_regiapprovalBtn5 = new JToggleButton();
	public JButton ho_regiapproval_RefreshBtn = new JButton();
	
	// 대리점 구성
	public JPanel agencyPanel = new JPanel();			// 대리점
	
	// 판매점 구성
	public JPanel salePanel = new JPanel();				// 판매점
	
	public StoreWindow(StoreMain sm) {
		this.sm = sm;
		mainView();
	}	
		
	public void mainView() {	
		
		setSize(1200, 1000);
		setTitle("휴대폰 매장 관리 시스템");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		// 로그인 화면 구성 ---------------------------------------------------
		img = new ImageIcon("src/phoneStore/img/phonestoreMain.jpg");
		
		loginPanel = new JPanel() {
			public void printComponent(Graphics g) {
				g.drawImage(img.getImage(), 0, 0, null);
				setOpaque(false);
				super.printComponent(g);
			}
		};
		
		ptlb1.setFont(new Font("굴림", Font.BOLD, 15));
		ptlb1.setBounds(618, 417, 70, 24);
		
		ptlb2.setFont(new Font("굴림", Font.BOLD, 15));
		ptlb2.setBounds(618, 452, 76, 24);
		
		textId.setSize(100, 30);
		textId.setLocation(700, 415);
		
		textPw.setSize(100, 30);
		textPw.setLocation(700, 450);
		
		loginBtn.setText("로그인");
		loginBtn.setSize(80, 30);
		loginBtn.setLocation(820, 450);
		
		textAddr.setSize(300, 50);
		textAddr.setLocation(100, 100);
		
		// 매장등록 요청 화면 구성 ------------------------------------------
		storetype_cbbox = new JComboBox<String>(storetype);
		storetype_cbbox.setSize(300, 50);
		storetype_cbbox.setLocation(100, 200);
		
		saletype_cbbox = new JComboBox<String>(saletype);
		saletype_cbbox.setSize(300, 50);
		saletype_cbbox.setLocation(100, 300);

		storeBtn.setText("매장등록");
		storeBtn.setSize(140, 50);
		storeBtn.setLocation(450, 300);
		
		// 매장등록 화면 및 메뉴바 구성 ----------------------------------------------------
		menu_HO.setFont(new Font("돋움", Font.PLAIN, 25));
		menuItem1_HO_storeApproval.setText("매장 등록 승인");
		menuItem2_HO_phoneApproval.setText("휴대폰 등록 관리");
		menuItem1_HO_storeApproval.setFont(new Font("돋움", Font.PLAIN, 25));
		menuItem2_HO_phoneApproval.setFont(new Font("돋움", Font.PLAIN, 25));
		
		ho_regiapprovalArea.setBorder(new TitledBorder("미승인 매장"));
		ho_regiapprovalArea.setSize(780, 250);
		ho_regiapprovalArea.setLocation(20, 80);
		ho_regiapprovalArea.setEditable(false);
		Font font = new Font(null, Font.BOLD, 15);
		ho_regiapprovalArea.setFont(font);
		
		loginPanel.setLayout(null);
		loginPanel.add(textId);
		loginPanel.add(textPw);
		loginPanel.add(loginBtn);
		loginPanel.add(ptlb1);
		loginPanel.add(ptlb2);
		
		storePanel.add(textAddr);
		storePanel.add(storetype_cbbox);
		storePanel.add(saletype_cbbox);
		storePanel.add(storeBtn);
		
		menu_HO.add(menuItem1_HO_storeApproval);
		menu_HO.add(menuItem2_HO_phoneApproval);
		menubar_HO.add(menu_HO);
		
		ho_regiapprovalBtnSubmit.setSize(70, 30);
		ho_regiapprovalBtnSubmit.setLocation(950, 80);
		ho_regiapprovalBtnSubmit.setText("확인");
		
		ho_regiapprovalBtn1.setSize(70, 30);
		ho_regiapprovalBtn1.setLocation(850, 80);
		ho_regiapprovalBtn1.setText("승인1");
		
		ho_regiapprovalBtn2.setSize(70, 30);
		ho_regiapprovalBtn2.setLocation(850, 130);
		ho_regiapprovalBtn2.setText("승인2");
		
		ho_regiapprovalBtn3.setSize(70, 30);
		ho_regiapprovalBtn3.setLocation(850, 180);
		ho_regiapprovalBtn3.setText("승인3");
		
		ho_regiapprovalBtn4.setSize(70, 30);
		ho_regiapprovalBtn4.setLocation(850, 230);
		ho_regiapprovalBtn4.setText("승인4");
		
		ho_regiapprovalBtn5.setSize(70, 30);
		ho_regiapprovalBtn5.setLocation(850, 280);
		ho_regiapprovalBtn5.setText("승인5");
		
		ho_regiapproval_RefreshBtn.setSize(140, 30);
		ho_regiapproval_RefreshBtn.setLocation(950, 130);
		ho_regiapproval_RefreshBtn.setText("새로고침");
		
		headOfficePanel.add(ho_regiapprovalArea);
		
		// 휴대폰 등록 화면 구성 ----------------------------------------------------
		phoneregApproval_phoneId.setSize(300, 50);
		phoneregApproval_phoneId.setLocation(100, 200);
		phoneregApproval_phoneId.setBorder(new TitledBorder("Phone Id"));
		
		phoneregApproval_phoneName.setSize(300, 50);
		phoneregApproval_phoneName.setLocation(100, 300);
		phoneregApproval_phoneName.setBorder(new TitledBorder("Phone Name"));
		
		phoneregApproval_phonePrice.setSize(300, 50);
		phoneregApproval_phonePrice.setLocation(100, 400);
		phoneregApproval_phonePrice.setBorder(new TitledBorder("Phone Price"));
		
		phoneregApproval_regBtn.setText("등록");
		phoneregApproval_regBtn.setSize(100, 50);
		phoneregApproval_regBtn.setLocation(450, 400);
		
		phoneregApproval_modifyBtn.setText("수정");
		phoneregApproval_modifyBtn.setSize(70, 50);
		phoneregApproval_modifyBtn.setLocation(580, 400);
		
		phoneregApproval_phoneSearch.setSize(300, 50);
		phoneregApproval_phoneSearch.setLocation(100, 100);
		phoneregApproval_phoneSearch.setBorder(new TitledBorder("Phone Search"));
		
		phoneregApproval_searchBtn.setText("조회");
		phoneregApproval_searchBtn.setSize(100, 50);
		phoneregApproval_searchBtn.setLocation(450, 100);
		
		phoneregApproval_panel.add(phoneregApproval_phoneId);
		phoneregApproval_panel.add(phoneregApproval_phoneName);
		phoneregApproval_panel.add(phoneregApproval_phonePrice);
		phoneregApproval_panel.add(phoneregApproval_phoneSearch);
		phoneregApproval_panel.add(phoneregApproval_regBtn);
		phoneregApproval_panel.add(phoneregApproval_modifyBtn);
		phoneregApproval_panel.add(phoneregApproval_searchBtn);
		
		setContentPane(loginPanel);
		setVisible(true);
	}
	
	public void joinBtnChange() {
		joinBtn.setText("가입");
		joinBtn.setSize(80, 30);
		joinBtn.setLocation(820, 450);
		loginPanel.add(joinBtn);
		loginPanel.remove(loginBtn);
		setContentPane(loginPanel);
		joinBtn.addActionListener(sm.lc.joinAction());
	}
	
	// 본사 화면
	public void headOfficeView() {
		headOfficePanel.setLayout(null);
		setJMenuBar(menubar_HO);
		setContentPane(headOfficePanel);
		setVisible(true);
		sm.hoc.storeApprovaView();
		ho_regiapprovalBtnSubmit.addActionListener(sm.hoc.approvalSubmitAction());
		ho_regiapproval_RefreshBtn.addActionListener(sm.hoc.refreshBtnAction());
	}
	
	// 대리점 화면
	public void agencyStoreView() {
		agencyPanel.setLayout(null);
		setContentPane(agencyPanel);
		setVisible(true);
		JOptionPane.showMessageDialog(null, "대리점 전용 화면 입니다.");
	}
	
	// 판매점 화면
	public void saleStoreView() {
		salePanel.setLayout(null);
		setContentPane(salePanel);
		setVisible(true);
		JOptionPane.showMessageDialog(null, "판매점 전용 화면 입니다.");
	}
	
	// 스토어 레벨 NULL 인 경우
	public void noPassStoreView() {
		storePanel.setLayout(null);
		setContentPane(storePanel);
		setVisible(true);
		JOptionPane.showMessageDialog(null, "아직 등록되지 않은 스토어 입니다. 등록 화면으로 이동합니다.");
	}
	
	// 핸드폰 등록 관리 화면
	public void phoneApprovalView() {
		phoneregApproval_panel.setLayout(null);
		setContentPane(phoneregApproval_panel);
		setVisible(true);
		phoneregApproval_regBtn.addActionListener(null);
		phoneregApproval_modifyBtn.addActionListener(null);
		phoneregApproval_searchBtn.addActionListener(null);
	}
}
