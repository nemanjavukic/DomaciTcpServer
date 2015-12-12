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
BufferedReader ulazniTokOdKlijenta2=null;
PrintStream izlazniTokKaKlijentu2=null;
Socket soketZaKom=null;
ServerSocket soket=null;
Socket soketZaKom2=null;
String operacija=null;
int rbr=0;
String brojevi[]= new String[20];
double rezultat=0;
String ulaz;
LinkedList<ServerNit> klijenti;


public ServerNit(Socket soketKom, LinkedList<ServerNit> klijenti){
	this.soketZaKom=soketKom;
	this.klijenti=klijenti;
}
	public void run(){
		try {
			ulazniTokOdKlijenta=new BufferedReader(new InputStreamReader(soketZaKom.getInputStream()));
			izlazniTokKaKlijentu=new PrintStream(soketZaKom.getOutputStream());
			while(true){
			operacija=ulazniTokOdKlijenta.readLine();
			System.out.println(operacija);
			izlazniTokKaKlijentu.println("ok");
			soket= new ServerSocket(3333);
			soketZaKom2=soket.accept();
			ulazniTokOdKlijenta2=new BufferedReader(new InputStreamReader(soketZaKom2.getInputStream()));
			izlazniTokKaKlijentu2=new PrintStream(soketZaKom2.getOutputStream());
			while(true){
				ulaz=ulazniTokOdKlijenta2.readLine();
				System.out.println(ulaz);
				if(ulaz.startsWith("0000")){
					break;
				}
				brojevi[rbr]=ulaz;
				rbr=rbr+1;
			}
			if(operacija.equals("plus")){
				for (int i = 0; i <=rbr; i++) {
					if(brojevi[i]!=null){
					rezultat=rezultat+Double.parseDouble(brojevi[i]);
					}
				}
				izlazniTokKaKlijentu2.println(rezultat+"");
				rezultat=0;
				for (int i = 0; i <=rbr; i++) {
					brojevi[i]=null;
				}
				rbr=0;
				soketZaKom2.close();
				soket.close();
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
				izlazniTokKaKlijentu2.println(rezultat+"");
				rezultat=0;
				for (int i = 0; i <=rbr; i++) {
					brojevi[i]=null;
				}
				rbr=0;
				soketZaKom2.close();
				soket.close();
			}
			if(operacija.equals("mult")){
				rezultat=1;
				for (int i = 0; i <=rbr; i++) {
					if(brojevi[i]!=null){
					rezultat=rezultat*Double.parseDouble(brojevi[i]);
					}
				}
				izlazniTokKaKlijentu2.println(rezultat+"");
				rezultat=0;
				for (int i = 0; i <=rbr; i++) {
					brojevi[i]=null;
				}
				rbr=0;
				soketZaKom2.close();
				soket.close();
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
				izlazniTokKaKlijentu2.println(rezultat+"");
				rezultat=0;
				for (int i = 0; i <=rbr; i++) {
					brojevi[i]=null;
				}
				rbr=0;
				soketZaKom2.close();
				soket.close();
			}
				
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	
	
}
