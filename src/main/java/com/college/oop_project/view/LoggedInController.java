package com.college.oop_project.view;

import com.college.oop_project.model.Professor;
import com.college.oop_project.model.Student;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {
    @FXML
    private Label welcomeLabel;
    @FXML
    private Label firstName;
    @FXML
    private Label lastName;
    @FXML
    private Label email;
    @FXML
    private Label school;
    @FXML
    private Button btnLogout;
    @FXML
    private MenuBar professorMenuBar;


    @FXML
    private Pane mainPane;


    // --------------------------------------------------
    // add student pane
    // --------------------------------------------------
    @FXML
    private Pane addStudentPane;

    @FXML
    private TextField studentNameInputField;

    @FXML
    private TextField studentSurnameInputField;

    @FXML
    private TextField studentMailInputField;

    // add sex option - dropdown button
    // studentSexInputField

    @FXML
    private Label addStudentMsgLbl;

    @FXML
    private Button addStudentBtn;


    // --------------------------------------------------
    // add professor pane
    // --------------------------------------------------
    @FXML
    private Pane addProfessorPane;

    @FXML
    private TextField professorNameInputField;

    @FXML
    private TextField professorSurnameInputField;

    @FXML
    private TextField professorMailInputField;

    // add sex option - dropdown button
    // professorSexInputField

    @FXML
    private Label addProfessorMsgLbl;

    @FXML
    private Button addProfessorBtn;


    // --------------------------------------------------
    // change password pane
    // --------------------------------------------------
    @FXML
    private Pane changePasswordPane;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField newPasswordText;

    @FXML
    private TextField confirmPasswordText;

    @FXML
    private CheckBox showPasswordsBtn;

    @FXML
    private Label changePwErrLabel;

    @FXML
    private Button changePasswordBtn;


    // --------------------------------------------------
    // add school pane
    // --------------------------------------------------
    @FXML
    private Pane addSchoolPane;

    @FXML
    private TextField addSchoolNameInputField;

    @FXML
    private TextField addSchoolPlaceInputField;

    @FXML
    private TextField addSchoolCityInputField;

    @FXML
    private TextField addSchoolCountryInputField;

    @FXML
    private Label addSchoolMsgLbl;

    @FXML
    private Button addSchoolBtn;


    // --------------------------------------------------
    // add school subject pane
    // --------------------------------------------------
    @FXML
    private Pane addSchoolSubjectPane;

    @FXML
    private TextField addSubjectNameInputField;

    @FXML
    private TextField addSubjectClassInputField;

    @FXML
    private Label addSubjectMsgLbl;

    @FXML
    private Button addSubjectBtn;

    // --------------------------------------------------
    // menu item bar
    // --------------------------------------------------
    @FXML
    private MenuItem showAddStudentPane;

    @FXML
    private MenuItem showAddProfessorPane;

    @FXML
    private MenuItem showAddSchoolPane;

    @FXML
    private MenuItem showAddSubjectPane;



    // --------------------------------------------------
    // Methods
    // --------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnLogout.setOnAction(event -> {
            Controller.changeScene(event, "log-in.fxml", "eDnevnik | Prijava", null, null);
        });

        changePasswordBtn.setOnAction(event -> {
            String newPassword = newPasswordField.getText();
            String confirmPassword = confirmPasswordText.getText();

            if (validatePasswords(newPassword, confirmPassword)) {
                newPasswordField.setText("");
                confirmPasswordField.setText("");
                changePwErrLabel.setText("Pristupna šifra je uspješno promijenjena.\nUskoro očekujte email sa potvrdom.");
                sendMail("vladomijic14@gmail.com", confirmPassword);
            }
        });
    }

    public void setStudentInfo(Student user) {
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getAccessData().getUserMail());
    }

    public void setProfessorInfo(Professor user) {
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getAccessData().getUserMail());
    }

    public void backToMainPane() {
        hideVisiblePane();
        mainPane.setVisible(true);
    }

    public void showAddStudentPane() {
        hideVisiblePane();
        addStudentPane.setVisible(true);
    }

    public void showAddProfessorPane() {
        hideVisiblePane();
        addProfessorPane.setVisible(true);
    }

    public void showAddSchoolPane() {
        hideVisiblePane();
        addSchoolPane.setVisible(true);
    }

    public void showAddSubjectPane() {
        hideVisiblePane();
        addSchoolSubjectPane.setVisible(true);
    }

    public void showChangePasswordPane() {
        hideVisiblePane();
        changePasswordPane.setVisible(true);
    }

    public void showNewPasswordInput() {
        if (showPasswordsBtn.isSelected()) {
            newPasswordText.setText(newPasswordField.getText());
            confirmPasswordText.setText(confirmPasswordField.getText());
            newPasswordField.setVisible(false);
            confirmPasswordField.setVisible(false);
            newPasswordText.setVisible(true);
            confirmPasswordText.setVisible(true);
        } else {
            newPasswordField.setText(newPasswordText.getText());
            confirmPasswordField.setText(confirmPasswordText.getText());
            newPasswordField.setVisible(true);
            confirmPasswordField.setVisible(true);
            newPasswordText.setVisible(false);
            confirmPasswordText.setVisible(false);
        }
    }

    private void hideVisiblePane() {
        if (mainPane.isVisible()) {
            mainPane.setVisible(false);
        } else if (addSchoolPane.isVisible()) {
            addSchoolPane.setVisible(false);
        } else if (addProfessorPane.isVisible()) {
            addProfessorPane.setVisible(false);
        } else if (addStudentPane.isVisible()) {
            addStudentPane.setVisible(false);
        } else if (addSchoolSubjectPane.isVisible()) {
            addSchoolSubjectPane.setVisible(false);
        } else if (changePasswordPane.isVisible()) {
            changePasswordPane.setVisible(false);
        }
    }

    private boolean validatePasswords(String newPassword, String confirmPassword) {
        boolean validInput = false;

        if (newPassword.isBlank() && confirmPassword.isBlank()) {
            changePwErrLabel.setText("Unesite šifre, šifra treba da sadrži minimum 8 karaktera.");
        } else if (newPassword.length() < 8) {
            changePwErrLabel.setText("Dužina šifre treba da bude minimum 8 karaktera.");
        } else if (!newPassword.equals(confirmPassword)) {
            changePwErrLabel.setText("Šifre se ne podudaraju.");
        } else if (newPassword.equals(confirmPassword)) {
            validInput = true;
        }

        return validInput;
    }

    private void sendMail(String toUserMail, String password) {
        String host = "smtp.gmail.com";
        String port = "587";
        String fromMail = "vladocodes@gmail.com";
        String appPassword = System.getenv("$EDNEVNIK_PW");

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromMail, appPassword);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromMail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toUserMail));
            message.setSubject("Promjena pristupne šifre za eDnevnik");

            StringBuilder sb = new StringBuilder();
            sb.append("Poštovani korisniče,").append("\n\n");
            sb.append("Obavještavamo Vas da je Vaša pristupna šifra uspješno promijenjena.").append("\n");
            sb.append("Nova šifra: ").append(password).append("\n\n");
            sb.append("Ukoliko budete imali problema prilikom prijavljivanja na naš sistem molimo Vas da kontaktirate " +
                    "korisničku podršku na email: kpodrska@ednevnik.ba").append("\n\n");
            sb.append("Stojimo Vam na raspolaganju").append("\n");

            message.setText(sb.toString());
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
