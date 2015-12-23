package TcpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class ServerNit extends Thread {
BufferedReader ulazniTokOdKlijenta=null;
PrintStream izlazniTokKaKlijentu=null;
Socket soketZaKom=null;
ServerSocket soket=null;
String operacija=null;
LinkedList<ServerNit> klijenti;
String ulaz;




public String getOperacija() {
	return operacija;
}

public ServerNit(Socket soketKom, LinkedList<ServerNit> klijenti){
	this.soketZaKom=soketKom;
	this.klijenti=klijenti;
}
	public void run(){
		try {
			ulazniTokOdKlijenta=new BufferedReader(new InputStreamReader(soketZaKom.getInputStream()));
			izlazniTokKaKlijentu=new PrintStream(soketZaKom.getOutputStream());
			while(true){
				if((ulaz=ulazniTokOdKlijenta.readLine()).equals("end")){
			soketZaKom.close();
			break;
				}
				
				operacija=ulaz;
			System.out.println(operacija);
			izlazniTokKaKlijentu.println("ok");
			for (int i = 0; i <klijenti.size(); i++) {
				if(klijenti.get(i).equals(this)){
					izlazniTokKaKlijentu.println(i);
					break;
				}
			}
			
			}
		klijenti.remove(this);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
		
		
		
		
		
	
	
	

