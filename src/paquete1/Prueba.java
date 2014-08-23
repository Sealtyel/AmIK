package paquete1;

import java.io.File;
import java.io.IOException;

public class Prueba {

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		int indicador=2;
		String idusr="3";
		int idCancion=47;
		File playlist = new File("playlist.m3u");
 	    File playlist2 = new File("playlistK.m3u");
		Person person=new Person(idusr,indicador,idCancion);
		Thread.sleep(5000);
		Person person2=new Person(idusr,indicador,3);
		String cmd = "vlc -f playlistK.m3u";
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);
		
		

	}

}
