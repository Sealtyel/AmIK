/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete1;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author sealtyel
 */
public class AmIDJ {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException 
    {
        //desde aqui se debe abrir el archivo del historial
        File history = new File("historial.txt");
        history.delete();
        String[] artistas,generos, canciones;
        
        Person p1=new Person("212");
        Person p2=new Person("212");
        //Person p3=new Person("209");
        Person p4=new Person("212");
        Person p5=new Person("212");
        //Person p6=new Person("212");
        //Person p7=new Person("212");
        
        
        artistas=p1.getartistas();
        generos=p1.getgeneros();
        canciones=p1.getcanciones();
        
        
        System.out.println(artistas[0]);
        System.out.println(generos[0]);
        System.out.println(canciones[2]);
        //JOptionPane.showMessageDialog(null,"Hello World");
        



    }
}
