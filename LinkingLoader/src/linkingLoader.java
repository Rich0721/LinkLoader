import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class linkingLoader {
	
	private static HeaderRecord[] headerRecord = new HeaderRecord[10];
	private static ModificationRecord[] modificationRecord = new ModificationRecord[20];
	private static TextRecord[] textRecord = new TextRecord[100];
	private static SYMTABLE[] symtable = new SYMTABLE[10];
	private static int h=0,m=0,t=0,s=0;

	public static void main(String arg[]) throws IOException{
		FileReader file = new FileReader("Linking.txt");
		FileWriter SYMBOLTABLE = new FileWriter("SymbolTable.txt");
		BufferedReader br = new BufferedReader(file);
		String line;
		int i = 0,j=0,tIn=0,stove;
		String[] strove = new String[3];
		String CSADDR = "2000";
		
		while(br.ready()){
			line = br.readLine();
			String [] substrs = line.split(";");
			CSADDR = Compass(substrs,CSADDR);
		}
		SYMBOLTABLE.write("SymbolName\t"+"Address \r\n");
		for(i=0;i<s;i++){
			SYMBOLTABLE.write(symtable[i].getProgamSymbolName()+"\t"+"\t"+
					symtable[i].getAddress().toUpperCase()+ "\r\n");
			symtable[i].putProgamSymbolName(symtable[i].getProgamSymbolName().trim());
			
		}
		Compass2();
		String Head = headerRecord[0].getInitialAddress();
		String prinHead = headerRecord[0].getInitialAddress();
		String programEnd = headerRecord[2].getInitialAddress();
		int programSize = Integer.parseInt(programEnd, 16)+ Integer.parseInt(headerRecord[2].getProgamSize(), 16)
							-Integer.parseInt(Head, 16);
		int space = 0;
		//System.out.println(programIntiAddress + "  " + programSize);
		for(i=0;i<programSize;i=i+16){
			System.out.print(prinHead.toUpperCase()+ "\t");
			j=0;
			while(j<16){
				if(strove[0] != null){
					System.out.print(strove[0].toUpperCase());
					j++;
					space++;
					if(space >3){
						System.out.print(" ");
						space =0;
					}
					if(strove[1] != null){
						System.out.print(strove[1].toUpperCase());
						j++;
						space++;
						if(space >3){
							System.out.print(" ");
							space =0;
						}
						if(strove[2] != null){
							System.out.print(strove[2].toUpperCase());
							j++;
							space++;
							if(space >3){
								System.out.print(" ");
								space =0;
							}
						}
					}
					strove[0] = null;
					strove[1] = null;
					strove[2] = null;
				}
				if(tIn<t && textRecord[tIn].getAddressLocation().equalsIgnoreCase(Head)){
					if(textRecord[tIn].getObjectCode().length()==2){
						j++;
						if(j<=16){
							System.out.print(textRecord[tIn].getObjectCode().toUpperCase());
							Head = Integer.toHexString(Integer.parseInt(Head, 16)+1);
							prinHead = Head;
							space++;
							if(space >=4){
								System.out.print(" ");
								space =0;
							}
						}
					}
					else if(textRecord[tIn].getObjectCode().length()==4){
						j=j+2;
						
						if(j<=16){
							space = PrintSpace(space,tIn,0);
							Head = Integer.toHexString(Integer.parseInt(Head, 16)+2);
							prinHead = Head;
						}
					}
					else if(textRecord[tIn].getObjectCode().length()==6){
						j=j+3;
						if(j<=16){
							space = PrintSpace(space,tIn,0);
							Head = Integer.toHexString(Integer.parseInt(Head, 16)+3);
							prinHead = Head;
						}
						
					}
					else{
						j=j+4;
						if(j<=16){
							space = PrintSpace(space,tIn,0);
							Head = Integer.toHexString(Integer.parseInt(Head, 16)+4);
							prinHead = Head;
						}
						
					}
					if(j>16){
						stove = j-16;
						if(stove == 1){
							if(textRecord[tIn].getObjectCode().length()==6){
								space = PrintSpace(space,tIn,2);
								Head = Integer.toHexString(Integer.parseInt(Head, 16)+2);
							}
							else if(textRecord[tIn].getObjectCode().length()==4){
								space = PrintSpace(space,tIn,2);
								Head = Integer.toHexString(Integer.parseInt(Head, 16)+1);
							}
							else{
								space = PrintSpace(space,tIn,2);
								Head = Integer.toHexString(Integer.parseInt(Head, 16)+3);
							}
							prinHead = Head;
							strove[0] = textRecord[tIn].getObjectCode().
									substring(textRecord[tIn].getObjectCode().length()-2);
							Head = Integer.toHexString(Integer.parseInt(Head, 16)+1);
						}
						else if(stove==2){
							if(textRecord[tIn].getObjectCode().length()==6){
								space = PrintSpace(space,tIn,4);
								Head = Integer.toHexString(Integer.parseInt(Head, 16)+1);
							}
							else{
								space = PrintSpace(space,tIn,4);
								Head = Integer.toHexString(Integer.parseInt(Head, 16)+2);
							}
							prinHead = Head;
							strove[0] = textRecord[tIn].getObjectCode().
									substring(textRecord[tIn].getObjectCode().length()-4,
											textRecord[tIn].getObjectCode().length()-2);
							strove[1] = textRecord[tIn].getObjectCode().
									substring(textRecord[tIn].getObjectCode().length()-2);
							Head = Integer.toHexString(Integer.parseInt(Head, 16)+2);
						}
						else if(stove==3){
							space = PrintSpace(space,tIn,6);
							Head = Integer.toHexString(Integer.parseInt(Head, 16)+1);
							prinHead = Head;
							strove[0] = textRecord[tIn].getObjectCode().
									substring(textRecord[tIn].getObjectCode().length()-6,
											textRecord[tIn].getObjectCode().length()-4);
							strove[1] = textRecord[tIn].getObjectCode().
									substring(textRecord[tIn].getObjectCode().length()-4,
											textRecord[tIn].getObjectCode().length()-2);
							strove[2] = textRecord[tIn].getObjectCode().
									substring(textRecord[tIn].getObjectCode().length()-2);
							Head = Integer.toHexString(Integer.parseInt(Head, 16)+3);
						}
					}
					tIn++;
				}
				else{
					if(tIn<t){
						System.out.print("--");
					}
					else{
						System.out.print("xx");
					}
					
					j++;
					space = PrintSpace(space,t,i);
					Head =Integer.toHexString(Integer.parseInt(Head, 16)+1);
					prinHead = Head;
				}
			}
			System.out.println("");
		}
		SYMBOLTABLE.close();
		file.close();
		
		
		

	}
	
	public static String Compass(String[] subStrs , String CSADDR){
		
		if(subStrs[0].equalsIgnoreCase("H")){
			int initialAddress = Integer.parseInt(subStrs[2], 16);
			initialAddress = initialAddress+Integer.parseInt(CSADDR, 16);
			int progamSize = Integer.parseInt(subStrs[3], 16);
			headerRecord[h] = new HeaderRecord(subStrs[1],Integer.toHexString(initialAddress),
					Integer.toHexString(progamSize));
			symtable[s] = new SYMTABLE(headerRecord[h].getName(),
					headerRecord[h].getInitialAddress());
			s++;
			h++;
		}
		else if(subStrs[0].equalsIgnoreCase("D")){
			int i = 1;
			while(i<subStrs.length){
				String symName = subStrs[i];
				int innerAddress = Integer.parseInt(subStrs[i+1],16);
				innerAddress = Integer.parseInt(CSADDR, 16) + innerAddress;
				symtable[s] = new SYMTABLE(symName,Integer.toHexString(innerAddress));
				s++;
				i = i+2;
			}
		}
		else if(subStrs[0].equalsIgnoreCase("T")){
			
			int intialTextAddress = Integer.parseInt(subStrs[1], 16);
			intialTextAddress = Integer.parseInt(CSADDR, 16) + intialTextAddress;
			int textSize = Integer.parseInt(subStrs[2], 16);
			int count = 0;
			int i = 3;
			while(count < textSize){
				if(subStrs[i].length() == 6){
					textRecord[t] = new TextRecord(Integer.toHexString(intialTextAddress),
							subStrs[i]);
					intialTextAddress = intialTextAddress +3;
					count =count+3;
					i++;
					t++;
				}
				else if(subStrs[i].length()==8){
					textRecord[t] = new TextRecord(Integer.toHexString(intialTextAddress),
							subStrs[i]);
					intialTextAddress = intialTextAddress +4;
					count = count+4;
					i++;
					t++;
				}
				else if(subStrs[i].length()==2){
					textRecord[t] = new TextRecord(Integer.toHexString(intialTextAddress),
							subStrs[i]);
					intialTextAddress = intialTextAddress + 1;
					count = count+1;
					i++;
					t++;
				}
				else if(subStrs[i].length()==4){
					textRecord[t] = new TextRecord(Integer.toHexString(intialTextAddress),
							subStrs[i]);
					intialTextAddress = intialTextAddress +2;
					count = count+2;
					i++;
					t++;
				}
			}
		}
		else if(subStrs[0].equalsIgnoreCase("M")){
			int modificationTextAddress = Integer.parseInt(subStrs[1], 16);
			modificationTextAddress = Integer.parseInt(CSADDR, 16) + modificationTextAddress;
			
			modificationRecord[m] = new ModificationRecord(Integer.toHexString(modificationTextAddress),
					subStrs[3]);
			m++;
		}
		else if(subStrs[0].equalsIgnoreCase("E")){
			int change = Integer.parseInt(CSADDR, 16);
			int size = Integer.parseInt(headerRecord[h-1].getProgamSize(),16);
			change = change+size;
			CSADDR = Integer.toHexString(change);
		}
		return CSADDR;
	}
	
	public static void Compass2(){
		int i = 0,tIn=0,mIn=0,sIn=0;
		int change=0,flag;
		String add_zero= null;
		String Head = null,Spilt;
		for(tIn=0;tIn<t;tIn++){
			add_zero= "";
			if(mIn<m && textRecord[tIn].getIntiAddress().equalsIgnoreCase
					(modificationRecord[mIn].getModificationAddress())){
				for(sIn=0;sIn<s;sIn++){
					flag =symtable[sIn].getProgamSymbolName().compareTo
							(modificationRecord[mIn].getAddOrSubName());
					if(flag==0){
						if(textRecord[tIn].getObjectCode().length()==8){
							Head = textRecord[tIn].getObjectCode().substring(0,3);
							Spilt = textRecord[tIn].getObjectCode().substring(3);
						}
						else{
							Spilt = textRecord[tIn].getObjectCode();
						}
						
						if(modificationRecord[mIn].getAdd() == 1){
							change = Integer.parseInt(Spilt, 16) +
									Integer.parseInt(symtable[sIn].getAddress(),16);
						}
						else{
							change = Integer.parseInt(Spilt, 16) -
									Integer.parseInt(symtable[sIn].getAddress(),16);
						}
						if(textRecord[tIn].getObjectCode().length()==8){
							for(i=0;i<5-Integer.toHexString(change).length();i++){
								add_zero ="0" + Integer.toHexString(change);
							}
							Head = Head +add_zero;
						}
						else{
							Head=Integer.toHexString(change);
						}
						
						textRecord[tIn].putObjectCode(Head);
					}
				}
				mIn++;
				if(mIn<m){
					if(textRecord[tIn].getIntiAddress().equalsIgnoreCase
							(modificationRecord[mIn].getModificationAddress())){
						for(sIn=0;sIn<s;sIn++){
							flag =symtable[sIn].getProgamSymbolName().compareTo
									(modificationRecord[mIn].getAddOrSubName());
							if(flag==0){
								//add_zero = null;
								if(textRecord[tIn].getObjectCode().length()==8){
									Head = textRecord[tIn].getObjectCode().substring(0,3);
									Spilt = textRecord[tIn].getObjectCode().substring(3);
								}
								else{
									Spilt = textRecord[tIn].getObjectCode();
								}
								if(modificationRecord[mIn].getAdd() == 1){
									
									change = Integer.parseInt(Spilt, 16) +
											Integer.parseInt(symtable[sIn].getAddress(),16);
									//System.out.println(Integer.toHexString(change));
								}
								else{
									change = Integer.parseInt(Spilt, 16) -
											Integer.parseInt(symtable[sIn].getAddress(),16);
								}
								if(Integer.toHexString(change).length()<6){
									for(i=0;i<6-Integer.toHexString(change).length();i++){
										add_zero =add_zero+"0";
									}
								}
								Head = add_zero+ Integer.toHexString(change);
								textRecord[tIn].putObjectCode(Head);
								
							}
						}
						mIn++;
				}
				}
			}
	}
		}
	
	public static int PrintSpace(int space,int tIn,int i){
		
		if(tIn == t){
			space++;
			if(space >3){
				System.out.print(" ");
				space =0;
			}
		}
		else{
			for(int k=0;k<(textRecord[tIn].getObjectCode().length())-i;k=k+2){
			System.out.print(textRecord[tIn].getObjectCode().substring(k, k+2).toUpperCase());
			space++;
			if(space >3){
				System.out.print(" ");
				space =0;
			}
		}
		
		}
		
		return space;
	}
}
