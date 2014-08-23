/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete1;

import com.mysql.jdbc.Statement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author sealtyel
 */
public class Person 
{
    //int id;
    
    int [] idartistas,idgeneros,idcanciones,idcancioneselegidas;
    private String [] artistas,generos,canciones;
    File lista,historial;
    ResultSet artistasdb,generosdb,cancionesdb,rs;
    File history = new File("historial.txt");
    boolean repetida=false;
    private static String [] karaokesongs={"","","","",""};
    static int nkaraoke;//numero de canciones actuales de karaoke puestas
   
    
    int i=0;
    int m=0;
    int control=0;
    
    //Connection con=null;
    
    
    public Person(String id) throws IOException, InterruptedException
    {
       idcancioneselegidas= new int[5];
      try {
       File playlist = new File("playlist.m3u");
       
       // creates the file
       playlist.createNewFile();
       // creates a FileWriter Object
       FileWriter writer = new FileWriter(playlist); 
      
       
       // Writes the content to the file
       writer.write("#EXTM3U"); 
       //writer2.write("");
       //writer2.flush();
       writer.flush();
             
        int randomnumber=0,k=0, randomnumber2=0;
        String direccion;
        
                        
        Connection con=ConnectToDatabase();

        rs=TotalRecords(con, id, "idUsuario","Usuario_Artista");
        generosdb=TotalRecords(con, id, "idUsuario","Usuario_Genero");//generos de un usuario
        //rs.close();
        
        while(generosdb.next())//conteo de registros
        {
          if(generosdb.last())
              m=generosdb.getRow();
        }
        
        while(rs.next())//conteo de registros
        {
          if(rs.last())
              i=rs.getRow();
        }
        rs.beforeFirst();//Regresar el cursor del resultset
        generosdb.beforeFirst();
        idartistas=new int[i];
        idgeneros=new int[m];
        m=0;
        i=0;

        while(generosdb.next())//generos de un usuario
        {
            idgeneros[m]=generosdb.getInt("idGenero");
            m++;
        }
        
        while(rs.next())//Artistas de un usuario
        {
            idartistas[i]=rs.getInt("idArtista");
            i++;
        }
        artistas(con,idartistas);//Para el get de los artistas
        generos(con,idgeneros);
        
        if(i>0)//Si tiene artistas
        {
            System.out.println("El usuario "+id+" tiene "+i+" artistas "+idartistas.length);
            for(int j=0;j<5;j++)//j es el indice de cada artista
            {
                
                control++;
 
                repetida=false;
                randomnumber=(int)Math.floor(Math.random()*idartistas.length);
                
                rs=TotalRecords(con, idartistas[randomnumber]+"", "idArtista","Artista_Cancion");//id de artistas seleccionados

                while(rs.next())
                {
                  if(rs.last())
                     k=rs.getRow();
                }
                rs.beforeFirst();

                

                idcanciones=new int[k];
                
                
                
                k=0;

                while(rs.next())
                {

                    idcanciones[k]=rs.getInt("idCancion");
                    k++;
                }
                

                randomnumber=(int)Math.floor(Math.random()*idcanciones.length);
                randomnumber2=randomnumber;
                idcancioneselegidas[j]=idcanciones[randomnumber2];
                rs=TotalRecords(con, idcanciones[randomnumber]+"", "idCancion","Cancion");
                rs.next();
                direccion=rs.getString("locacion");

                System.out.println(direccion);
                FileWriter writer2 = new FileWriter(history,true); 
                
                
                randomnumber2=randomnumber;
                
                if(isInHistory(idcanciones[randomnumber]))
                {
                    j--;
                    System.out.println("Se repite");
                    repetida=true;
                    control--;
                    continue;
                }
                
               
                
                writer2.append("\n"+idcanciones[randomnumber]+"");
                writer.append("\n"+direccion);
                writer2.close();
                

            }


        }
        else//no tiene artistas
        {

        }

        canciones(con,idcancioneselegidas);
        writer.close();
        
        rs.close();
        con.close();
        generosdb.close();
        

        //String cmd = "clementine -l playlist.m3u";
        //Runtime run = Runtime.getRuntime();
        //Process pr = run.exec(cmd);


    } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
                          
    public Person(String idusr, int indicador) 

    {
		
	}

    public Person(String idusr, int indicador, int idcancion) throws IOException, InterruptedException
    {
    	try {
    		   nkaraoke++;
    		   idcancioneselegidas= new int[5];
    		   File playlist = new File("playlistK.m3u");
    	       
			   // creates the file
			   playlist.createNewFile();
			   // creates a FileWriter Object
			   FileWriter writer = new FileWriter(playlist); 
			  
			   
			   // Writes the content to the file
			   writer.write("#EXTM3U\n"); 
			   //writer2.write("");
			   //writer2.flush();
			   writer.flush();
			         
			    int randomnumber=0,k=0, randomnumber2=0;
			    String direccion;
			    
			                    
			    Connection con=ConnectToDatabase();
			    
			    rs=getSong(con,idcancion);
			    int l=0;
			    while(rs.next())
		        {
			    	nkaraoke++;
			    	karaokesongs[nkaraoke]=rs.getString("locacion");
			    	l++;
		        }
			    writer.flush();
			    //playlist.createNewFile();
			    for(int i=0;i<karaokesongs.length;i++)
			    {
			    	writer.append(karaokesongs[i]+"\n");
			    }
			    
			    rs.close();
			    writer.close();
			    
			   
    		} 
    	
    		catch (SQLException ex) 
    		{
                Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
    		}
    }

    void artistas(Connection con, int id[]) throws SQLException
    {
        ResultSet rs = null;
        //String artistas[];
        artistas = new String [id.length];
        for(int i=0;i<id.length;i++)
        {
            rs=TotalRecords(con, id[i]+"", "idArtista","Artista");
            rs.next();
            artistas[i]=rs.getString("nomArtista");
        }
        
    }
    
    void generos(Connection con, int id[]) throws SQLException
    {
        ResultSet rs = null;
        //String artistas[];
        generos = new String [id.length];
        for(int i=0;i<id.length;i++)
        {
            rs=TotalRecords(con, id[i]+"", "idGenero","Genero");
            rs.next();
            generos[i]=rs.getString("nomGenero");
        }
        
    }
    
    void canciones(Connection con, int id[]) throws SQLException
    {
        ResultSet rs = null;
        //String artistas[];
        canciones = new String [id.length];
        for(int i=0;i<id.length;i++)
        {
            rs=TotalRecords(con, id[i]+"", "idCancion","Cancion");
            rs.next();
            canciones[i]=rs.getString("nomCancion");
        }
        
    }
    
    String[] getartistas()
    {
        return artistas;
    }
    
    String[] getgeneros()
    {
        return generos;
    }
    
    String[] getcanciones()
    {
        return canciones;
    }
    
    ResultSet getidgeneros()
    {
        ResultSet idgenre = null;
        
        return idgenre; 
    }
    
    ResultSet TotalRecords(Connection con, String id, String campo, String tabla) 
    throws SQLException 
    {
    
    ResultSet rs=null;
    Statement stmt = null;
    String query =
        "select * from "+tabla+" where "+campo+" = "+id;

    try {
        stmt = (Statement) con.createStatement();
        rs = stmt.executeQuery(query);
        /*while (rs.next()) 
        {
            //System.out.println(rs.getInt("idArtista"));   
        }*/
    } catch (SQLException e ) 
    {
        e.printStackTrace();
    } /*finally 
     * 
    {
        if (stmt != null) 
        {
            stmt.close(); 
        }
    }*/
    
    return rs;
    
}
    
    public Connection ConnectToDatabase() throws SQLException 
    {

        Connection conn = null;
        Properties connectionProps = new Properties();
        

        conn = (Connection) DriverManager.getConnection(
                   "jdbc:mysql://localhost:3306/AMIDJDB","root","1234");
        System.out.println("Connected to database");
        
        return conn;
    }
    
    ResultSet getSong(Connection con, int id)
    {
    	ResultSet rs=null;
        Statement stmt = null;
        String query =
            "select * from Cancion where idCancion = "+id;

        try {
            stmt = (Statement) con.createStatement();
            rs = stmt.executeQuery(query);
          
        } catch (SQLException e ) 
        {
            e.printStackTrace();
        }
        return rs;
    }
    Boolean isInHistory(int id)
    {
        try {
            int idarchivo=0;
            File history = new File("historial.txt");
            Scanner sc=new Scanner(history);
            
            while(sc.hasNext())
            {
                idarchivo=sc.nextInt();
                if(idarchivo==id)
                {
                    return true;
                }
            }
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

  
}
    
    

