package appDesktop;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class FileUtil {

       private static final String filePath = "C:/temp/eventos.log";

       private static String getConteudo(){
           StringBuilder conteudoExistente = new StringBuilder();
           String linhaAtual;
           BufferedReader br;
           
           try {
                  br = new BufferedReader(new FileReader(filePath));
                  while ((linhaAtual = br.readLine()) != null) {              	  
                         //conteudoExistente.append(linhaAtual);
                         
                         conteudoExistente.append(linhaAtual != null ? linhaAtual : "").append(System.getProperty("line.separator"));
                  }
                  br.close();
                  return conteudoExistente.toString();
           } catch (FileNotFoundException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
           } catch (IOException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
           }
           return null;


       }
       
       public static void encerrarLog() {
           //shutdown event
           Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
               @Override
               public void run() {
                 
               }
           }));
		};

       public static void escreverTexto(String texto){
             try {

                    File file = new File(filePath);
                    String conteudoAntigo="";
                    String cabecalho = "EVENTO;ID;DATA;USUARIO";
                    
                    if (file.exists()){
                           conteudoAntigo = getConteudo();
                    }else{
                           file.createNewFile();                                     
                    }

                    Date data = new Date();
                    FileWriter filewt = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(filewt);
                    String textoComData = texto;
                    
                   if (conteudoAntigo == "") {
                    	   bw.write(cabecalho + "\n");
					}
                    
                    bw.write(conteudoAntigo);
                    bw.write(textoComData);
                    bw.close();
             } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
             }

       }

}