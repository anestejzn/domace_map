package rs.ac.uns.ftn.siit.sw442019.graduate.service.implementation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.ItemEmail;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.OrderEmail;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.MailCannotBeSentException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static rs.ac.uns.ftn.siit.sw442019.graduate.util.Constants.*;

@Service
public class EmailService {

    private final Environment env;

    public EmailService(final Environment env) {
        this.env = env;
    }

    @Async
    public void sendVerificationMail(int verificationCode, String verificationUrl, String email)
            throws MailException, IOException, MailCannotBeSentException {
        String pathToHTMLFile = TEMPLATE_FILE_PATH + "sendVerificationMailTemplate.html";
        String emailTemplate_PartOne = setEmailTemplate(pathToHTMLFile, "_verificationUrl_", verificationUrl);
        String emailTemplate = splitAndConcatenate(emailTemplate_PartOne, "_verificationCode_", Integer.toString(verificationCode));
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
        HTMLEmailService mm = (HTMLEmailService) context.getBean("htmlMail");
        mm.sendMail(EMAIL, email, SUBJECT_VERIFY_USER, emailTemplate);
    }

    @Async
    public void sendOrderMail(String title, String message, OrderEmail orderEmail, String email) throws MailCannotBeSentException {
        System.out.println(orderEmail.getAddress() + "   " + orderEmail.getPhoneNumber());
        StringBuilder html = new StringBuilder("<html xmlns:th='http://www.thymeleaf.org'>\n" +
                "<head>\n" +
                "    <title th:remove='all'>" + title + "</title>\n" +
                "    <meta content='text/html; charset='utf-8' />\n" +
                "    <style type='text/css'>\n" +
                "        body {\n" +
                "            font-family: calibri, 'DejaVu Sans', Arial, Helvetica, sans-serif;\n" +
                "            width: 70%;\n" +
                "        }\n" +
                "        a.link {\n" +
                "            border: solid 1px #000;\n" +
                "            background-color: #000;\n" +
                "            text-decoration: none;\n" +
                "            color: #fff;\n" +
                "            font-weight: bold;\n" +
                "            display: block;\n" +
                "            width: 360px;\n" +
                "            text-align: center;\n" +
                "            padding: 10px 0;\n" +
                "        }\n" +
                "        .body {\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        .header {\n" +
                "            background-color: #003399;\n" +
                "        }\n" +
                "        .text {\n" +
                "            color: #FFFFFF;\n" +
                "        }\n" +
                "\n" +
                "        .title {\n" +
                "            font-weight: bold;\n" +
                "            font-size: 20px;\n" +
                "        }\n" +
                "\n" +
                "        hr.solid {\n" +
                "            border-top: 3px solid #bbb;\n" +
                "            margin-top: 20px;\n" +
                "        }\n" +
                "        hr.solid-black {\n" +
                "            border-top: 1px solid black;\n" +
                "        }\n" +
                "        .order tr td {\n" +
                "            border-bottom: 1px solid black;\n" +
                "            text-align: left;\n" +
                "            padding: 5px 20px 5px 10px;\n" +
                "        }\n" +
                "\n" +
                "        table {\n" +
                "            border-collapse: collapse;\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "\n" +
                "        table tr td {\n" +
                "            margin-right: 10px;\n" +
                "        }\n" +
                "\n" +
                "        .number-order {\n" +
                "            margin-top: 20px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "    <div class='body'>\n" +
                "        <div class='header'>\n" +
                "            <h1 class='text'>D O M A C E &nbsp; M A P</h1>\n" +
                "            <p class='text'>" + title + "</p>\n" +
                "        </div>\n" +
                "        <div>\n" +
                "            <p style='text-align: left'> Postovani, </p>\n" +
                "            <p style='text-align: left'>"+message+"</p>\n" +
                "            <br/>\n" +
                "            <p class='title'>Pregled porudzbine</p>\n" +
                "            <table>\n" +
                "                <tr>\n" +
                "                    <td><b>Ime i prezime: </b></td>\n" +
                "                    <td>" + orderEmail.getUserName() + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td><b>Adresa: </b></td>\n" +
                "                    <td>" + orderEmail.getAddress() + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td><b>Grad: </b></td>\n" +
                "                    <td>" + orderEmail.getCity() + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td><b>Kontakt telefon: </b></td>\n" +
                "                    <td>" + orderEmail.getPhoneNumber() + "</td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "            <p style='text-align: left'>Broj porudzbine: " + orderEmail.getNumberOfOrder() + "</p>\n" +
                "            <br/>\n" +
                "            <hr class='solid'>\n" +
                "            <br/>\n" +
                "            <br/>\n" +
                "            <table class='order' style='margin-left: auto; margin-right: auto;'>\n");
                for(ItemEmail item : orderEmail.getItems()){
                    html.append("<tr>\n" + "<td><td>"+item.getName()+"</td><td>"+ item.getCol()+"</td>\n" + "<td>"+ item.getPrice() + "din.</td>\n" + "</tr>\n" + "\n");
                }
                html.append("</table>\n" + "<b>Ukupno: ").append(orderEmail.getTotalPrice()).append("</b>\n").append("</div>\n").append("<br/>\n").append("<br/>\n").append("<br/>\n").append("<br/>\n").append("<div class='header'>\n").append("<p class='text'>Copyrights &copy; DomaceMap All Rights Reserved</p>\n").append("</div>\n").append("</div>\n").append("\n").append("</body>\n").append("</html>");

        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
        HTMLEmailService mm = (HTMLEmailService) context.getBean("htmlMail");
        mm.sendMail(EMAIL, email, title.toUpperCase(), String.valueOf(html));
    }


    private String readHtmlFile(String filePath) throws IOException {

        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    private String setEmailTemplate(String pathToHTMLFile, String regexForSplit, String parameter) throws IOException {
        String readHtmlString = readHtmlFile(pathToHTMLFile);

        return splitAndConcatenate(readHtmlString, regexForSplit, parameter);
    }


    private String splitAndConcatenate(String stringForSplit, String regexForSplit, String parameterForConcatenate) {
        String[] splittedString = stringForSplit.split(regexForSplit);

        return splittedString[0] + parameterForConcatenate + splittedString[1];
    }

}
