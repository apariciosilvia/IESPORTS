package com.iesports.dao.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.iesports.model.Person;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMailForgotPassword(String toEmail, String code, String userName) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(toEmail);
            helper.setSubject("Contraseña temporal IESPORTS");

            String htmlContent = "<html>\n" +
            		"  <body style=\"font-family: Arial, sans-serif; background-color: #f5f6fa; padding: 20px;\">\n" +
            		"    <div style=\"max-width: 600px; margin: auto; background-color: #ffffff; padding: 30px; border-radius: 12px; box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);\">\n" +
            		"      <!-- Cabecera -->\n" +
            		"      <div style=\"background-color: #002f3d; padding: 25px; border-radius: 10px 10px 0 0; text-align: center;\">\n" +
            		"        <h1 style=\"color: #ffffff; margin: 0;\">Recuperación de Contraseña</h1>\n" +
            		"      </div>\n" +
            		"      <!-- Cuerpo del mensaje -->\n" +
            		"      <div style=\"padding: 20px;\">\n" +
            		"        <p style=\"font-size: 16px; color: #333;\">Hola <strong>"+userName+"</strong>,</p>\n" +
            		"        <p style=\"font-size: 16px; color: #333;\">\n" +
            		"          Hemos generado una <b>contraseña temporal</b> para ti:\n" +
            		"        </p>\n" +
            		"        <div style=\"background-color: #fcebea; padding: 15px; border-left: 5px solid #ff0800; margin: 20px 0; border-radius: 5px;\">\n" +
            		"          <p style=\"font-size: 18px; color: #ff0800; font-weight: bold; margin: 0;\">Tu nueva contraseña temporal es: <span>"+code+"</span></p>\n" +
            		"        </div>\n" +
            		"        <p style=\"font-size: 16px; color: #333;\">\n" +
            		"          Te recomendamos cambiar esta contraseña lo antes posible por una de tu preferencia. Podrás hacerlo mediante la solicitud de cambio de contraseña que te saltará iniciando sesión con esta contraseña temporal.\n" +
            		"        </p>  \n" +
            		"      </div>\n" +
            		"      <!-- Pie -->\n" +
            		"      <div style=\"text-align: center; font-size: 12px; color: #aaa;  border-top: 1px solid #eee;\">\n" +
            		"        <p style=\"font-size: 14px; color: #777; margin-bottom: 20px;\">\n" +
            		"          Este mensaje ha sido enviado automáticamente. Por favor, no respondas a este correo.\n" +
            		"        </p>\n" +
            		"      </div>\n" +
            		"      <div style=\"text-align: center; font-size: 12px; color: #ffffff; padding: 20px; border-top: 1px solid #eee; background-color: #002f3d; border-radius:0 0 10px 10px;\">\n" +
            		"       IESPORTS - Tu instituto, tu torneo, tu victoria\n" +
            		"      </div>\n" +
            		"    </div>\n" +
            		"  </body>\n" +
            		"</html>";


            helper.setText(htmlContent, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    public void sendWelcomeEmail(String toEmail, String userName) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(toEmail);
            helper.setSubject("¡Bienvenido a nuestra plataforma!");

            // Your image path (adjust if needed)
            FileSystemResource image = new FileSystemResource(
                new File("src/main/resources/static/images/logo.png")
            );

            String htmlContent = "<!DOCTYPE html>\n" +
                "<html lang=\"es\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Bienvenido a IESPORTS</title>\n" +
                "</head>\n" +
                "<body style=\"margin: 0; padding: 0; background-color: #f4f4f4; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "    <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"background-color: #f4f4f4; padding: 40px 0;\">\n" +
                "        <tr>\n" +
                "            <td align=\"center\">\n" +
                "                <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" style=\"background-color: #ffffff; border-radius: 10px; overflow: hidden; box-shadow: 0 4px 16px rgba(0, 0, 0, 0.1);\">\n" +
                "                    <tr>\n" +
                "                        <td style=\"background-color: #002f3d; padding: 30px; text-align: center;\">\n" +
                "                            <h1 style=\"color: #ffffff; margin: 0; font-size: 26px;\">Bienvenido a <span style=\"color: #ff0800;\">IESPORTS</span></h1>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td style=\"text-align: center; padding: 20px;\">\n" +
                "                            <img src=\"cid:logoImage\" alt=\"Logo IESPORTS\" style=\"max-width: 100px;\">\n" + // Reference by CID
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td style=\"padding: 0 40px 30px 40px; text-align: left;\">\n" +
                "                            <h2 style=\"color: #002f3d;\">¡Hola <b style=\"color: #ff0800;\">" + userName + "</b>!</h2>\n" +
                "                            <p style=\"font-size: 16px; color: #333;\">Gracias por unirte a nuestra plataforma. Tu cuenta ha sido creada correctamente y ya puedes comenzar a explorar todo lo que IESPORTS tiene para ofrecerte.</p>\n" +
                "                            <p style=\"font-size: 16px; color: #333;\">Si tienes dudas o necesitas ayuda, nuestro equipo está disponible para asistirte.</p>\n" +
                "                            <div style=\"text-align: center; margin: 30px 0;\">\n" +
                "                                <a href=\"https://tusitio.com/login\" style=\"background-color: #ff0800; color: #ffffff; padding: 12px 24px; text-decoration: none; font-weight: bold; border-radius: 5px; display: inline-block;\">Acceder a la plataforma</a>\n" +
                "                            </div>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td style=\"background-color: #f0f0f0; padding: 20px; text-align: center;\">\n" +
                "                            <p style=\"font-size: 12px; color: #888;\">Este mensaje fue enviado automáticamente. No respondas a este correo.</p>\n" +
                "                            <p style=\"font-size: 12px; color: #aaa;\">&copy; 2025 IESPORTS. Todos los derechos reservados.</p>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</body>\n" +
                "</html>";

            helper.setText(htmlContent, true);
            helper.addInline("logoImage", image); // CID must match src="cid:logoImage"

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace(); // Manejo de errores
        }
    }

    public void sendAdminRegistrationEmail(Person person, String passwordTemp) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(person.getEmail());
            helper.setSubject("Tu cuenta ha sido creada por un administrador");

            // Ruta de la imagen (ajusta si es necesario)
            FileSystemResource image = new FileSystemResource(
                new File("src/main/resources/static/images/logo.png")
            );

            String htmlContent = "<!DOCTYPE html>\n" +
            		"<html lang=\"es\">\n" +
            		"<head>\n" +
            		"    <meta charset=\"UTF-8\">\n" +
            		"    <title>Credenciales de acceso</title>\n" +
            		"</head>\n" +
            		"<body style=\"margin:0;padding:0;background-color:#f4f4f4;font-family:'Segoe UI',Tahoma,Geneva,Verdana,sans-serif;\">\n" +
            		"    <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"background-color:#f4f4f4;padding:40px 0;\">\n" +
            		"        <tr>\n" +
            		"            <td align=\"center\">\n" +
            		"                <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" style=\"background-color:#ffffff;border-radius:10px;overflow:hidden;box-shadow:0 4px 16px rgba(0,0,0,0.1);\">\n" +
            		"                    <tr>\n" +
            		"                        <td style=\"background-color:#002f3d;padding:30px;text-align:center;\">\n" +
            		"                            <h1 style=\"color:#ffffff;margin:0;font-size:26px;\">Cuenta creada en <span style=\"color:#ff0800;\">IESPORTS</span></h1>\n" +
            		"                        </td>\n" +
            		"                    </tr>\n" +
            		"                    <tr>\n" +
            		"                        <td style=\"text-align:center;padding:20px;\">\n" +
            		"                            <img src=\"cid:logoImage\" alt=\"Logo IESPORTS\" style=\"max-width:100px;\">\n" +
            		"                        </td>\n" +
            		"                    </tr>\n" +
            		"                    <tr>\n" +
            		"                        <td style=\"padding:20px;text-align:left;\">\n" +
            		"                            <p style=\"font-size:16px;color:#333;\">¡Hola <b style=\"color:#ff0800;\">" + person.getName() + "</b>!</p>\n" +
            		"                            <p style=\"font-size:16px;color:#333;\">Tu cuenta ha sido creada por un administrador. A continuación tus credenciales:</p>\n" +
            		"                        </td>\n" +
            		"                    </tr>\n" +
            		"                    <tr>\n" +
            		"                        <td style=\"padding:0 40px 20px 40px;\">\n" +
            		"                            <div style=\"background-color:#ff0800;color:#ffffff;padding:15px;border-radius:5px;margin-bottom:10px;\">\n" +
            		"                                <p style=\"margin:0;font-size:16px; text-decoration: none !important;\"><b>Correo:</b> " + person.getEmail() + "</p>\n" +
            		"                            </div>\n" +
            		"                            <div style=\"background-color:#ff0800;color:#ffffff;padding:15px;border-radius:5px;\">\n" +
            		"                                <p style=\"margin:0;font-size:16px;\"><b>Contraseña temporal:</b> " + passwordTemp + "</p>\n" +
            		"                            </div>\n" +
            		"                        </td>\n" +
            		"                    </tr>\n" +
            		"                    <tr>\n" +
            		"                        <td style=\"padding:0 40px 30px 40px;text-align:left;\">\n" +
            		"                            <p style=\"font-size:14px;color:#888;\">Por seguridad, al iniciar sesión deberás restablecer tu contraseña.</p>\n" +
            		"                            <h3 style=\"color:#002f3d;margin-top:20px;\">Con IESPORTS podrás:</h3>\n" +
            		"                            <ul style=\"list-style:none;padding:0;\">\n" +
            		"                                <li style=\"background-color:#e8f5f8;color:#002f3d;padding:10px;border-radius:5px;margin-bottom:8px;\">Consultar todos los torneos con información detallada.</li>\n" +
            		"                                <li style=\"background-color:#e8f5f8;color:#002f3d;padding:10px;border-radius:5px;margin-bottom:8px;\">Ver equipos y sus participantes.</li>\n" +
            		"                                <li style=\"background-color:#e8f5f8;color:#002f3d;padding:10px;border-radius:5px;\">Visualizar el calendario de partidos.</li>\n" +
            		"                            </ul>\n" +
            		"                            <div style=\"text-align:center;margin:30px 0;\">\n" +
            		"                                <a href=\"https://tusitio.com/login\" style=\"background-color:#ff0800;color:#ffffff;padding:12px 24px;text-decoration:none;font-weight:bold;border-radius:5px;\">Iniciar sesión</a>\n" +
            		"                            </div>\n" +
            		"                        </td>\n" +
            		"                    </tr>\n" +
            		"                    <tr>\n" +
            		"                        <td style=\"background-color:#f0f0f0;padding:20px;text-align:center;\">\n" +
            		"                            <p style=\"font-size:12px;color:#888;\">Este mensaje fue enviado automáticamente. No respondas a este correo.</p>\n" +
            		"                            <p style=\"font-size:12px;color:#aaa;\">&copy; 2025 IESPORTS. Todos los derechos reservados.</p>\n" +
            		"                        </td>\n" +
            		"                    </tr>\n" +
            		"                </table>\n" +
            		"            </td>\n" +
            		"        </tr>\n" +
            		"    </table>\n" +
            		"</body>\n" +
            		"</html>";




            helper.setText(htmlContent, true);
            helper.addInline("logoImage", image);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    
    public void sendUserQuestion(String userEmail, String userMessage) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            // Este es el correo del TFG al que se enviará el mensaje
            helper.setTo("noreply.iesports@gmail.com"); // ← Cambia esto por el correo real de vuestro TFG
            // El usuario parecerá ser el remitente
            helper.setFrom(userEmail);
            helper.setReplyTo(userEmail); // Importante: si alguien responde al correo, le responde al usuario
            helper.setSubject("Nueva consulta desde el formulario de contacto");

            String htmlContent = "<html>" +
            		"<body style='margin:0; padding:0; background-color:#eef2f5; font-family:Helvetica, Arial, sans-serif;'>" +
            		"<table width='100%' cellpadding='0' cellspacing='0' style='background-color:#eef2f5; padding:20px 0;'>" +
            		"<tr>" +
            		"<td align='center'>" +
            		"<table width='600' cellpadding='0' cellspacing='0' style='background-color:#ffffff; border-radius:8px; overflow:hidden; box-shadow:0 2px 12px rgba(0,0,0,0.1);'>" +
            		"<tr>" +
            		"<td style='background-color:#002f3d; padding:20px; text-align:center;'>" +
            		"<h2 style='color:#ffffff; margin:0; font-size:24px;'>Nueva consulta recibida</h2>" +
            		"</td>" +
            		"</tr>" +
            		"<tr>" +
            		"<td style='padding:20px;'>" +
            		"<p style='font-size:16px; color:#333333; margin:0 0 10px;'><strong>Correo del usuario:</strong> " + userEmail + "</p>" +
            		"<p style='font-size:16px; color:#333333; margin:0 0 10px;'><strong>Mensaje:</strong></p>" +
            		"<div style='background-color:#fafafa; padding:15px; border-left:4px solid #cf4444; border-radius:4px;'>" +
            		"<p style='font-size:15px; color:#333333; margin:0; white-space:pre-line;'>" + userMessage + "</p>" +
            		"</div>" +
            		"</td>" +
            		"</tr>" +
            		"<tr>" +
            		"<td style='background-color:#f9f9f9; padding:15px; text-align:center; font-size:12px; color:#888888;'>" +
            		"Este mensaje ha sido generado automáticamente desde el formulario de contacto." +
            		"</td>" +
            		"</tr>" +
            		"</table>" +
            		"</td>" +
            		"</tr>" +
            		"</table>" +
            		"</body>" +
            		"</html>";

            helper.setText(htmlContent, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


}