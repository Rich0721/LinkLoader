
public class SYMTABLE {
	
	private String programSymbolName;
	private String address;
	
	public SYMTABLE(){
		programSymbolName=null;
		address = null;
	}
	
	public SYMTABLE(String programSymbolName,String address){
		this.programSymbolName = programSymbolName;
		this.address= address;
	}
	
	public void putProgamSymbolName(String progamSymbolName){
		this.programSymbolName = progamSymbolName;
	}
	
	
	public void putAddress(String address){
		this.address = address;
	}
	
	public String getProgamSymbolName(){
		return programSymbolName;
	}
	
	public String getAddress(){
		return address;
	}

}
