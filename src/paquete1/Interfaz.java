package paquete1;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.bluetooth.BluetoothStateException;
import javax.swing.*;
/**
 * @author Andres
 */
public class Interfaz extends javax.swing.JFrame {
    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane5 = new javax.swing.JScrollPane();
        lista = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        monitoreo = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Canciones Seleccionadas", "ID Usuario"
            }
        ));
        lista.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(lista);

        jLabel3.setIcon(new javax.swing.ImageIcon("/home/mayra/workspace/AmIDJRegional/src/paquete1/logoS.png"));

        monitoreo.setEditable(false);
        monitoreo.setColumns(40);
        monitoreo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        monitoreo.setRows(5);
        jScrollPane1.setViewportView(monitoreo);

        jLabel1.setText("Monitoreo:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * @param args the command line arguments
     */
     static Socket socket = null;
	static PrintWriter out = null;
	static String [] argstemp=new String[1];
	static String identra;
        public static int count;
	int idsale;
	int personaslugar;
	private static final String SERVICE_NAME = "client";
	
	public JLabel logo= new JLabel();
    public static void main(String args[]) {
         //Interfaz frame = new Interfaz(); 
         //frame.setVisible(true);
    	File history = new File("historial.txt");
        history.delete();
        count=0;
         Servidor conn=null;
        try {
           
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>
             UUID uuid = new UUID("1101", true);
               // try {
                  Interfaz  frame = new Interfaz(); 
                    frame.setVisible(true);
                    LocalDevice localDevice = null;
            try {
                localDevice = LocalDevice.getLocalDevice();
            } catch (BluetoothStateException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
                    System.out.println("Address: "+localDevice.getBluetoothAddress());
                    System.out.println("Name: "+localDevice.getFriendlyName());
                    String connectionString = "btspp://localhost:" + uuid +";name="+SERVICE_NAME;
                    //open server url
                    StreamConnectionNotifier streamConnNotifier = (StreamConnectionNotifier)Connector.open( connectionString );
                   // Wait for client connection
                    System.out.println("\nServer Started. Waiting for clients to connect...");    
                    do{
                        System.out.println("\nServer Started. Waiting for clients to connect...");
                        StreamConnection connection=streamConnNotifier.acceptAndOpen();
                        conn=new Servidor(connection,streamConnNotifier);
                        conn.start();
                        
                        try {
							Thread.currentThread().sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                      Person tmp=conn.person;
                      //Person tmp=new Person("209");
					tmp=conn.person;
					identra=conn.idusr;
					              
					nuevoUsuario(tmp,identra);
					             // nuevoUsuario(tmp,"209");
                   }while(true);
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public static void nuevoUsuario(Person person,String id)
 {
     monitoreo.append("Nuevo usuario detectado, ID: "+id+"\n");
     monitoreo.append("Generos preferidos:\n");
     for(int i=0;i<person.getgeneros().length;i++)
     {
         monitoreo.append(person.getgeneros()[i]+"\n");
     }
     monitoreo.append("Artistas favoritos:\n");
     for(int i=0;i<person.getartistas().length;i++)
     {
         monitoreo.append(person.getartistas()[i]+"\n"); 
     }
     monitoreo.append("-----------------------------------\n");
     
     for(int i=0;i<person.getcanciones().length;i++)
     {
         lista.setValueAt(person.getcanciones()[i], i+count, 0);
         lista.setValueAt(id, i+count, 1);
     }
     count+=5;
 
 }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private static javax.swing.JTable lista;
    public static javax.swing.JTextArea monitoreo;
    // End of variables declaration//GEN-END:variables
}