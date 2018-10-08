
public class TextRecord {
	
	private String addressLocation;
	private String objectCode;
	private String intiAddress;

	public TextRecord(String addressLocation, String objectCode){
		this.addressLocation = addressLocation;
		this.objectCode = objectCode;
		if(objectCode.length() == 8){
			int x = Integer.parseInt(addressLocation, 16)+1;
			intiAddress = Integer.toHexString(x);
		}
		else{
			this.intiAddress = this.addressLocation;
		}
		
	}
	
	public void putAddressLocation(String addressLocation){
		this.addressLocation = addressLocation;
	}
	
	public void putObjectCode(String objectCode){
		this.objectCode = objectCode;
	}
	
	public String getAddressLocation(){
		return addressLocation;
	}
	
	public String getObjectCode(){
		return objectCode;
	}
	
	public String getIntiAddress(){
		
		return intiAddress;
	}
	
}
