package TcpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.LinkedList;

public class ServerNit2 extends Thread {
	BufferedReader ulazniTokOdKlijenta=null;
	PrintStream izlazniTokKaKlijentu=null;
	Socket soketZaKom=null;
	String operacija=null;
	int rbr=0;
	String brojevi[]= new String[20];
	double rezultat=0;
	String ulaz;
	LinkedList<ServerNit> klijenti;
	LinkedList<ServerNit2> klijenti2;
	int id;
	
	
	public ServerNit2(Socket soketKom, LinkedList<ServerNit> klijenti,LinkedList<ServerNit2> klijenti2){
		this.soketZaKom=soketKom;
		this.klijenti=klijenti;
		this.klijenti2=klijenti2;
	}
	
	
	
	public void run() {
		try {
		ulazniTokOdKlijenta=new BufferedReader(new InputStreamReader(soketZaKom.getInputStream()));
		izlazniTokKaKlijentu=new PrintStream(soketZaKom.getOutputStream());
		
		id=Integer.parseInt(ulazniTokOdKlijenta.readLine());

		while(true){
			ulaz=ulazniTokOdKlijenta.readLine();
			System.out.println(ulaz);
			if(ulaz.startsWith("0000")){
				break;
			}
			brojevi[rbr]=ulaz;
			rbr=rbr+1;
		}
		operacija=klijenti.get(id).getOperacija();
		if(operacija.equals("plus")){
			for (int i = 0; i <=rbr; i++) {
				if(brojevi[i]!=null){
				rezultat=rezultat+Double.parseDouble(brojevi[i]);
				}
			}
			izlazniTokKaKlijentu.println(rezultat+"");
			rezultat=0;
			for (int i = 0; i <=rbr; i++) {
				brojevi[i]=null;
			}
			rbr=0;
			soketZaKom.close();
		
		}
		if(operacija.equals("subt")){
			
			for (int i = 0; i <=rbr; i++) {
				if(brojevi[i]!=null){
					if(i==0){
						rezultat=Double.parseDouble(brojevi[i]);
						continue;
					}
				rezultat=rezultat-Double.parseDouble(brojevi[i]);
				}
			}
			izlazniTokKaKlijentu.println(rezultat+"");
			rezultat=0;
			for (int i = 0; i <=rbr; i++) {
				brojevi[i]=null;
			}
			rbr=0;
			soketZaKom.close();
			
		}
		if(operacija.equals("mult")){
			rezultat=1;
			for (int i = 0; i <=rbr; i++) {
				if(brojevi[i]!=null){
				rezultat=rezultat*Double.parseDouble(brojevi[i]);
				}
			}
			izlazniTokKaKlijentu.println(rezultat+"");
			rezultat=0;
			for (int i = 0; i <=rbr; i++) {
				brojevi[i]=null;
			}
			rbr=0;
			soketZaKom.close();
			
		}
		if(operacija.equals("devi")){
			for (int i = 0; i <=rbr; i++) {
				if(brojevi[i]!=null){
					if(i==0){
						rezultat=Double.parseDouble(brojevi[i]);
						continue;
					}
				rezultat=rezultat/Double.parseDouble(brojevi[i]);
				}
			}
			izlazniTokKaKlijentu.println(rezultat+"");
			rezultat=0;
			for (int i = 0; i <=rbr; i++) {
				brojevi[i]=null;
			}
			rbr=0;
			soketZaKom.close();
		
		}
		klijenti2.remove(this);
		}
			
		 catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
}
}