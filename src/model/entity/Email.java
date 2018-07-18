
package model.entity;

import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Renato Alonso Mendizabal Alpaca
 */
public class Email {
    
    public static boolean enviarCorreo(String de, List<String> para, String mensaje, String asunto){
        boolean enviado = false;
            try{
            
           
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        	
          Message msg = new MimeMessage(session);
          msg.setFrom(new InternetAddress(de));
          InternetAddress[] direcciones = new InternetAddress[para.size()];
          for(int i=0;i<para.size();i++){
              direcciones[i] = new InternetAddress(para.get(i));
          }
      
          for(int i=0;i<direcciones.length;i++){
              msg.addRecipient(Message.RecipientType.TO, direcciones[i]);
          }
      
          msg.setSubject(asunto);
          msg.setText(mensaje);
          Transport.send(msg);
        
            }catch(Exception e){
                e.printStackTrace();
            }
        
        return enviado;
    }
    
}
