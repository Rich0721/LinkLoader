
public class ModificationRecord {
	
	private int add,sub;
	private String modificationAddress;
	private String addOrSubName;
	
	public ModificationRecord(){
		modificationAddress = null;
		addOrSubName =null;
		add = 0;
		sub = 0;
	}
	
	public ModificationRecord(String modificationAddress,
			String addOrSubName){
		char x;
		this.modificationAddress = modificationAddress;
		this.addOrSubName = addOrSubName.substring(1, addOrSubName.length());
		x = addOrSubName.charAt(0);
		if(x == '+'){
			this.add = 1;
			this.sub = 0;
		}
		else{
			this.add = 0;
			this.sub = 1;
		}
	}
	
	
	public void putModificationAddress(String modificationAddress){
		this.modificationAddress = modificationAddress;
	}
	
	public void putAddOrSubName(String addOrSubName){
		this.addOrSubName = addOrSubName;
	}
	
	public String getModificationAddress(){
		return modificationAddress;
	}
	
	public String getAddOrSubName(){
		return addOrSubName;
	}
	
	public int getAdd(){
		return add;
	}
	
	public int getSub(){
		return sub;
	}
}
