package phoneStore;

import phoneStore.controller.HeadOfficeController;
import phoneStore.controller.LoginController;
import phoneStore.dao.HeadOfficeDAO;
import phoneStore.dao.PhoneStoreDAO;
import phoneStore.view.StoreWindow;

public class StoreMain {
	public StoreWindow sw;
	public LoginController lc;
	public HeadOfficeController hoc;
	
	public StoreMain() {
		sw = new StoreWindow(this);
		lc = new LoginController(this);
		hoc = new HeadOfficeController(this);
		hoc = new HeadOfficeController(this);
	
	}
	
	public static void main(String[] args) {
		StoreMain sm = new StoreMain();
	}
}
