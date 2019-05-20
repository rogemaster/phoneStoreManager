package phoneStore.dto;

public class StoreDTO {

	String storeId;
	String storeType;
	String storeAddr;
	String storeLevel;
	String con_yn;
	
	public StoreDTO() {
		
	}
	
	public StoreDTO(String storeid, String storetype, String storeaddr, String storelevel, String con_yn) {
		super();
		this.storeId = storeid;
		this.storeType = storetype;
		this.storeAddr = storeaddr;
		this.storeLevel = storelevel;
		this.con_yn = con_yn;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	public String getStoreAddr() {
		return storeAddr;
	}

	public void setStoreAddr(String storeAddr) {
		this.storeAddr = storeAddr;
	}

	public String getStoreLevel() {
		return storeLevel;
	}

	public void setStoreLevel(String storeLevel) {
		this.storeLevel = storeLevel;
	}

	public String getCon_yn() {
		return con_yn;
	}

	public void setCon_yn(String con_yn) {
		this.con_yn = con_yn;
	}

	@Override
	public String toString() {
		return "HeadOfficeDTO [storeId=" + storeId + ", storeType=" + storeType + ", storeAddr=" + storeAddr
				+ ", storeLevel=" + storeLevel + ", con_yn=" + con_yn + "]";
	}
	
	
	
}
