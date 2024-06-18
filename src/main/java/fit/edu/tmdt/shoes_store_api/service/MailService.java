package fit.edu.tmdt.shoes_store_api.service;

import java.util.Map;

public interface MailService {
    void sendMail(String to, String subject, String text);

    void sendVerificationToken(String toEmail, String emailSubjectRegister, Map<String, Object> attributes);

    void sendAppointmentID(String toEmail, String emailSubjectRegister, Map<String, Object> attributes);


}
