package TcpServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class TCPServer implements Runnable{
	static LinkedList<ServerNit> klijenti=new LinkedList<ServerNit>();
	static LinkedList<ServerNit2> klijenti2=new LinkedList<ServerNit2>();
	static ServerNit klijent;
	
	public static void main(String[] args) {
		Socket klijentSoket=null;
		
		try {
			ServerSocket serverSoket=new ServerSocket(1908);
			new Thread(new TCPServer()).start();
			
			while(true){
			klijentSoket=serverSoket.accept();
			klijenti.add(new ServerNit(klijentSoket,klijenti));
			klijenti.getLast().start();
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		Socket klijentSoket2=null;
		
		try {
			ServerSocket serverSoket2=new ServerSocket(16913);
			while(true){

				klijentSoket2=serverSoket2.accept();
				klijenti2.add(new ServerNit2(klijentSoket2, klijenti,klijenti2));
				klijenti2.getLast().start();
				System.out.println("b");
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
