package TcpServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class TCPServer implements Runnable{
	static LinkedList<ServerNit> klijenti=new LinkedList<ServerNit>();
	static ServerNit klijent;
	
	public static void main(String[] args) {
		Socket klijentSoket=null;
		
		try {
			ServerSocket serverSoket=new ServerSocket(2222);
			
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
		// TODO Auto-generated method stub
		
	}

}
