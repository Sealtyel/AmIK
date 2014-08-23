package paquete1;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.*;
import javax.microedition.io.*;
import javax.swing.JOptionPane;
import javax.bluetooth.*;
import javax.bluetooth.UUID;
public class Servidor extends Thread
{    
	private static final String SERVICE_NAME = "client";
	StreamConnection connection;
	public Socket socket = null;
	public PrintWriter out = null;
	public String lineRead;
	public Person person;
        public String idusr;
	public BufferedReader bReader;
	 StreamConnectionNotifier streamConnNotifier;
	Servidor(StreamConnection conn,StreamConnectionNotifier stream)
	{
		connection=conn;
		streamConnNotifier=stream;
	}
	public void run() {
		 UUID uuid = new UUID("1101", true);
	       try{
	    	   File playlist = new File("playlist.m3u");
	    	   File playlist2 = new File("playlistK.m3u");
	    	   System.out.println("Archivos creados");
	        RemoteDevice dev = RemoteDevice.getRemoteDevice(connection);
	        System.out.println("Remote device address: "+dev.getBluetoothAddress());
	        System.out.println("Remote device name: "+dev.getFriendlyName(true));
	        //read string from spp client
	        InputStream inStream=connection.openInputStream();
	        bReader=new BufferedReader(new InputStreamReader(inStream));
	        lineRead=bReader.readLine();
	        System.out.println("leido: "+lineRead);
	        //enviar id para visualizacion de datos en pantalla
	       
	        int indicador=0;
	        StringTokenizer tokens=new StringTokenizer(lineRead, "#");
            indicador=Integer.parseInt(tokens.nextToken());
            idusr=tokens.nextToken();
            int idcancion=0;
            if(indicador==2)
	        	idcancion=Integer.parseInt(tokens.nextToken());
            /*
             *indicador
             *0-entrada
             *1-salida
             *2-karaoke  
             * */
            
            //idusr="209";
	        person=new Person(idusr,indicador,idcancion);
	        
	        /*if(idusr.equals("213"))
	        {
	        	String cmd = "vlc -f playlistK.m3u";
	            Runtime run = Runtime.getRuntime();
	            Process pr = run.exec(cmd);
	        }*/
	      //  Thread.currentThread().sleep(2000);
	       }catch(IOException e)
	       {
	    	   System.out.println("Error en la lectura del id o al momento de enviar el id a la clase user");
	    	   e.printStackTrace();
	       
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       try{
			} catch (Exception e) 
			{
				 System.out.println("Error en la conexion con el reproductor ");
				 //out.println(lineRead+"salida");//mensaje de salida del usuario
				e.printStackTrace();
			}
	}
	/*
          public void esperaDesconexion( RemoteDevice Reader)
	{
		try 
		{
			do
			{
				sleep(1000);
				System.out.println("esperando\n"+lineRead);
			}while(Reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"termino en la excepcion de esperaDesconexion con: "+lineRead);
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
        */
} 