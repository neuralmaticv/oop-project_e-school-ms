package com.college.oop_project.view;

import com.college.oop_project.model.*;
import com.college.oop_project.sql.DBUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.util.*;

public class LoggedInController implements Initializable {
    @FXML
    private Label welcomeLabel, firstName, lastName, email, school, schoolLabel, place, placeLabel;
    @FXML
    private Label grade, gradeLabel, subjectLabel;
    @FXML
    private TextArea schoolList, subjectList;
    @FXML
    private Button btnLogout;

    @FXML
    private Pane mainPane;

    // --------------------------------------------------
    // add student pane
    // --------------------------------------------------
    @FXML
    private Pane addStudentPane;
    @FXML
    private TextField studentNameInputField, studentSurnameInputField, studentMailInputField;
    @FXML
    private ChoiceBox<String> cboxSelectStudentSex;
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
    private TextField professorNameInputField, professorSurnameInputField, professorMailInputField;
    @FXML
    private ChoiceBox<String> cboxSelectProfessorSex;
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
    private PasswordField newPasswordField, confirmPasswordField;
    @FXML
    private TextField newPasswordText, confirmPasswordText;
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
    private TextField addSchoolNameInputField, addSchoolPlaceInputField, addSchoolCityInputField, addSchoolCountryInputField;
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
    private TextField addSubjectNameInputField, addSubjectClassInputField;
    @FXML
    private Label addSubjectMsgLbl;
    @FXML
    private Button addSubjectBtn;


    // --------------------------------------------------
    // student grades pane
    // --------------------------------------------------
    @FXML
    private Pane showGradesPane;
    @FXML
    private RadioButton rbtnAllGrades, rbtnSortGradesForSubjectByDate;
    @FXML
    private ToggleGroup tgGradesSort;
    @FXML
    private TextField subjectNameInput;
    @FXML
    private Label subjectNameInputErrMsg;
    @FXML
    private Button btnSubmitChoice;
    @FXML
    private TableView gradesTable;
    @FXML
    private TableColumn tbGradesDate, tbGradesSubjectName, tbGradesSubjectGrade;


    // --------------------------------------------------
    // student absences pane
    // --------------------------------------------------
    @FXML
    private Pane showAbsencesPane;
    @FXML
    private TableView absencesTable;
    @FXML
    private TableColumn tbAbsencesDate, tbAbsencesSubjectName;


    // --------------------------------------------------
    // student absences pane
    // --------------------------------------------------
    @FXML
    private Pane showRankProfessorPane;
    @FXML
    private Text txtInfo;
    @FXML
    private ChoiceBox<String> cboxSelectProfessor;
    @FXML
    private Button btnSelectProfessor, btnSubmitAnswers;
    @FXML
    private Pane paneForQuestions;
    @FXML
    private Label lblQuestion1, lblQuestion2, lblQuestion3, lblQuestion4, rankedSuccessful;
    @FXML
    private RadioButton rbtnQ1A1, rbtnQ1A2, rbtnQ1A3, rbtnQ1A4, rbtnQ1A5;
    @FXML
    private RadioButton rbtnQ2A1, rbtnQ2A2, rbtnQ2A3, rbtnQ2A4, rbtnQ2A5;
    @FXML
    private RadioButton rbtnQ3A1, rbtnQ3A2, rbtnQ3A3, rbtnQ3A4, rbtnQ3A5;
    @FXML
    private RadioButton rbtnQ4A1, rbtnQ4A2, rbtnQ4A3, rbtnQ4A4, rbtnQ4A5;
    @FXML
    private ToggleGroup tgAnswerQ1, tgAnswerQ2, tgAnswerQ3, tgAnswerQ4;


    // --------------------------------------------------
    // menu item bar
    // --------------------------------------------------
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menu1, menu2, menu3, menu4;
    @FXML
    private MenuItem miShowGrades, miShowAbsences, miShowRankProfessor, miShowAddNewProfessor, miShowAddNewSubject, miShowAddNewSchool;


    private Student s;
    private Professor p;

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
        s = user;

        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getAccessData().getUserMail());
        school.setText("OŠ \"" + user.getSchool().getSchoolName() + "\"");
        place.setText(user.getSchool().getPlace());
        grade.setText(user.getSchoolGrade());

        menu1.setText("Ocjene");
        menu2.setText("Izostanci");
        menu4.setVisible(false);
        miShowGrades.setVisible(true);
        miShowAbsences.setVisible(true);
        miShowRankProfessor.setVisible(true);
        miShowAddNewProfessor.setVisible(false);
        miShowAddNewSubject.setVisible(false);
        miShowAddNewSchool.setVisible(false);
    }

    public void setProfessorInfo(Professor user) {
        p = user;
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getAccessData().getUserMail());
        schoolLabel.setText("Škole:");
        school.setVisible(false);
        placeLabel.setVisible(false);
        gradeLabel.setVisible(false);
        schoolList.setVisible(true);

        StringBuilder sb = new StringBuilder();
        for (School s : user.schools) {
            sb.append("OŠ \"").append(s.getSchoolName()).append("\"").append(" - ").append(s.getPlace()).append("\n");
        }
        schoolList.setText(sb.toString());

        sb.setLength(0);

        subjectLabel.setVisible(true);
        subjectList.setVisible(true);
        for (Subject s : user.subjects) {
            sb.append(s.getName()).append(" - ").append(s.getSchoolGrade()).append("\n");
        }
        subjectList.setText(sb.toString());
    }

    public void backToMainPane() {
        hideVisiblePane();
        mainPane.setVisible(true);
    }

    public void showGradesPane() {
        hideVisiblePane();
        showGradesPane.setVisible(true);
        tgGradesSort.selectToggle(rbtnAllGrades);

        tbGradesDate.setCellValueFactory(new PropertyValueFactory("date"));
        tbGradesSubjectName.setCellValueFactory(new PropertyValueFactory("subjectName"));
        tbGradesSubjectGrade.setCellValueFactory(new PropertyValueFactory("subjectGrade"));

        handleUserInputForStudentGrades();
    }

    private void handleUserInputForStudentGrades() {
        gradesTable.getItems().clear();
        subjectNameInput.setText("");
        subjectNameInputErrMsg.setVisible(false);

        if (rbtnAllGrades.isSelected()) {
            Collections.sort(s.listOfGrades);
            for (Grade g : s.listOfGrades) {
                gradesTable.getItems().add(g);
            }
        }

        btnSubmitChoice.setOnAction(event -> {
            subjectNameInputErrMsg.setVisible(false);
            gradesTable.getItems().clear();

            if (rbtnSortGradesForSubjectByDate.isSelected() && !subjectNameInput.getText().isBlank()) {
                String subjectName = subjectNameInput.getText();

                ArrayList<Grade> listOfGrades = s.getListOfGradesForSubject(subjectName);
                for (Grade g : listOfGrades) {
                    if (g.getSubject().getName().equals(subjectName)) {
                        gradesTable.getItems().add(g);
                    }
                }

                if (listOfGrades.isEmpty()) {
                    subjectNameInput.setText("");
                    subjectNameInputErrMsg.setVisible(true);
                    subjectNameInputErrMsg.setText("Pogrešan unos ili učenik nema ocjena iz datog predmeta.");
                }
            } else if (rbtnSortGradesForSubjectByDate.isSelected() && subjectNameInput.getText().isBlank()) {
                subjectNameInputErrMsg.setVisible(true);
                subjectNameInputErrMsg.setTextFill(Color.RED);
                subjectNameInputErrMsg.setText("Unesite naziv predmeta!");
            }
        });
    }

    public void showAbsencesPane() {
        hideVisiblePane();
        showAbsencesPane.setVisible(true);

        tbAbsencesDate.setCellValueFactory(new PropertyValueFactory("date"));
        tbAbsencesSubjectName.setCellValueFactory(new PropertyValueFactory("subjectName"));

        absencesTable.getItems().clear();
        for (Absences a : s.listOfAbsences) {
            absencesTable.getItems().add(a);
        }
    }

    public void showRankProfessorPane() {
        hideVisiblePane();
        showRankProfessorPane.setVisible(true);

        ArrayList<Label> lblQuestions = new ArrayList<>();
        Collections.addAll(lblQuestions, lblQuestion1, lblQuestion2, lblQuestion3, lblQuestion4);
        List<RadioButton> allRadioBtns = Arrays.asList(
                rbtnQ1A1, rbtnQ1A2, rbtnQ1A3, rbtnQ1A4, rbtnQ1A5,
                rbtnQ2A1, rbtnQ2A2, rbtnQ2A3, rbtnQ2A4, rbtnQ2A5,
                rbtnQ3A1, rbtnQ3A2, rbtnQ3A3, rbtnQ3A4, rbtnQ3A5,
                rbtnQ4A1, rbtnQ4A2, rbtnQ4A3, rbtnQ4A4, rbtnQ4A5);

        paneForQuestions.setVisible(false);
        cboxSelectProfessor.getItems().clear();
        setAllPossibleOptions();


        btnSelectProfessor.setOnAction(event -> {
            String professorFullName = cboxSelectProfessor.getSelectionModel().getSelectedItem();
            paneForQuestions.setVisible(true);
            rankedSuccessful.setVisible(false);
            displayQuestions(lblQuestions);

            if (!s.getQuestionsAndAnswersForProfessor(professorFullName).isEmpty()) {
                unselectOrHideRbtns(false, allRadioBtns);
                btnSubmitAnswers.setVisible(false);
                txtInfo.setText("Već ste ocjenili izabranog profesora:");
                txtInfo.setFill(Color.BLUE);
                Map<Question, String> QA = s.getQuestionsAndAnswersForProfessor(professorFullName);

                int i = 0;
                for (Map.Entry<Question, String> qs : QA.entrySet()) {
                    lblQuestions.get(i).setText(qs.getKey().getQuestion() + " | odgovor: " + qs.getValue());
                    i++;
                }
            } else {
                txtInfo.setText("Potrebno je odgovoriti na svako ponuđeno pitanje.\n" +
                        "1 - najgora ocjena, 5 - najbolja ocjena");
                txtInfo.setFill(Color.DARKRED);
                unselectOrHideRbtns(true, allRadioBtns);
                btnSubmitAnswers.setVisible(true);

                btnSubmitAnswers.setOnAction(e -> {
                    ArrayList<String> answers = new ArrayList<>();
                    answers.add(((RadioButton) tgAnswerQ1.getSelectedToggle()).getText());
                    answers.add(((RadioButton) tgAnswerQ2.getSelectedToggle()).getText());
                    answers.add(((RadioButton) tgAnswerQ3.getSelectedToggle()).getText());
                    answers.add(((RadioButton) tgAnswerQ4.getSelectedToggle()).getText());

                    for (Map.Entry<Professor, Map<Question, String>> pq : s.listOfProfessors.entrySet()) {
                        if (pq.getKey().getFullName().equals(professorFullName)) {
                            for (int i = 0; i < 4; i++) {
                                pq.getValue().put(Question.questions.get(i), answers.get(i));
                            }
                        }
                    }

                    paneForQuestions.setVisible(false);
                    rankedSuccessful.setVisible(true);
                    rankedSuccessful.setText("Ocijenili ste profesora!");
                });
            }
        });
    }

    private void setAllPossibleOptions() {
        for (Professor p : s.listOfProfessors.keySet()) {
            cboxSelectProfessor.getItems().add(p.getFullName());
        }
    }

    private void displayQuestions(ArrayList<Label> lblQuestions) {
        for (int i = 0; i < lblQuestions.size(); i++) {
            lblQuestions.get(i).setText(Question.getQuestionText(i));
        }
    }

    private void unselectOrHideRbtns(boolean flag, List<RadioButton> rbtns) {
        for (RadioButton rb : rbtns) {
            if (flag) {
                rb.setVisible(true);

                if (rb.isSelected()) {
                    rb.setSelected(false);
                }
            } else {
                rb.setVisible(false);
            }
        }
    }

    public void showAddStudentPane() {
        hideVisiblePane();
        addStudentPane.setVisible(true);
        cboxSelectStudentSex.getItems().add("Ženski");
        cboxSelectStudentSex.getItems().add("Muški");


        String name = studentNameInputField.getText();
        String surname = studentSurnameInputField.getText();
        String mail = studentMailInputField.getText();
        String sex = cboxSelectStudentSex.getSelectionModel().getSelectedItem();
        int sexID;
        if (sex.equals("Ženski")) {
            sexID = 0;
        } else if (sex.equals("Muški")) {
            sexID = 1;
        }

        addStudentBtn.setOnAction(event -> {
            addStudentMsgLbl.setVisible(false);
            addStudentMsgLbl.setText("");

            if (!name.isBlank() && !surname.isBlank() && !mail.isBlank() && !sex.isBlank()) {
                //int accessDataID = ;

                addStudentMsgLbl.setText("Dodali ste novog učenika.");
            } else {
                addStudentMsgLbl.setVisible(true);
                addStudentMsgLbl.setText("Molimo Vas da unesete sve potrebne podatke");
            }
        });



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
        } else if (showGradesPane.isVisible()) {
            showGradesPane.setVisible(false);
        } else if (showAbsencesPane.isVisible()) {
            showAbsencesPane.setVisible(false);
        } else if (showRankProfessorPane.isVisible()) {
            showRankProfessorPane.setVisible(false);
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
        } else {
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
