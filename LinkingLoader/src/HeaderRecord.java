
public class HeaderRecord {
	
	private String name;
	private String initialAddress,progamSize;
	
	public HeaderRecord(){
		name = null;
		initialAddress = null;
		progamSize = null;
	}
	
	public HeaderRecord(String name ,
			String initialAddress, String progamSize){
		
		this.name = name;
		this.initialAddress =initialAddress;
		this.progamSize = progamSize;
	}
	
	
	public void putName(String name){
		this.name = name;
	}
	
	public void putInitialAddress(String initialAddress){
		this.initialAddress = initialAddress;
	}
	
	public void putProgamSize(String progamSize){
		this.progamSize = progamSize;
	}
	
	
	public String getName(){
		return name;
	}
	
	public String getInitialAddress(){
		return initialAddress;
	}
	
	public String getProgamSize(){
		return progamSize;
	}

}