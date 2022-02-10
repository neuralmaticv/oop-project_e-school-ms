package com.college.oop_project.view;

import com.college.oop_project.model.*;
import com.college.oop_project.sql.DBUtils;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class LoggedInController implements Initializable {
    @FXML
    private Label firstName, lastName, email, school, schoolLabel, place, placeLabel;
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


    @FXML
    private Pane showAddSchoolSubjectPane, showAddGradeOrAbsencePane, allStudentsPane;
    @FXML
    private ChoiceBox<School> cboxSelectSchool, cboxSelectProfSchool;
    @FXML
    private ChoiceBox<Subject> cboxSelectSubject, cboxSelectProfSubject, cboxSelectSubjectToShowStudents;
    @FXML
    private ChoiceBox<Student> cboxSelectStudent;
    @FXML
    private RadioButton rbtnGrade, rbtnAbsence;
    @FXML
    private TextField addGradeInputField;
    @FXML
    private Label addSchoolSubjectMsgLbl, addGradeAbsenceMsgLbl;
    @FXML
    private Button btnSaveSchoolSubject, btnSubmitGradOrAbsence, btnDisplayStudents;
    @FXML
    private DatePicker gradeAbsenceDateInput;
    @FXML
    private TableView tableAllStudents;
    @FXML
    private TableColumn tbStudentInfo;
    @FXML
    private TextArea taStudentInfo;




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
    private TableColumn tbGradesDate,tbGradesSubjectName, tbGradesSubjectGrade;


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
    private MenuItem miShowGrades, miShowAbsences, miShowRankProfessor, miShowAddNewProfessor, miShowAddNewSubject,
            miShowAddNewSchool, miShowAddSchoolSubject, miAddGradeAbsence;


    private Student studentUser;
    private Professor professorUser;

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
                if (studentUser != null) {
                    DBUtils.updateAccessDataInDB(studentUser.getAccessData().getUserID(), studentUser.getAccessData().getUserName(), newPassword);
                } else {
                    DBUtils.updateAccessDataInDB(professorUser.getAccessData().getUserID(), professorUser.getAccessData().getUserName(), newPassword);
                }

                changePwErrLabel.setText("Pristupna šifra je uspješno promijenjena.\nUskoro očekujte email sa potvrdom.");
                String subject = "Promjena pristupne šifre za eDnevnik";
                String msg = "Obavještavamo Vas da je Vaša pristupna šifra uspješno promijenjena. Novu šifru pronađite u nastavku ovog mail-a.";
                sendMail("vladomijic14@gmail.com", confirmPassword, subject, msg);
            }
        });
    }

    public void setStudentInfo(Student user) {
        studentUser = user;

        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getAccessData().getUserMail());
        school.setText("OŠ \"" + user.getSchool().getSchoolName() + "\"");
        place.setText(user.getSchool().getPlace());
        grade.setText(user.getSchoolGrade().toString());

        menu1.setText("Ocjene");
        menu2.setText("Izostanci");
        menu4.setVisible(false);
        miShowGrades.setVisible(true);
        miShowAbsences.setVisible(true);
        miShowRankProfessor.setVisible(true);
        miShowAddNewProfessor.setVisible(false);
        miShowAddNewSubject.setVisible(false);
        miShowAddNewSchool.setVisible(false);
        miShowAddSchoolSubject.setVisible(false);
    }

    public void setProfessorInfo(Professor user) {
        professorUser = user;
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getAccessData().getUserMail());
        schoolLabel.setText("Škole:");
        school.setVisible(false);
        placeLabel.setVisible(false);
        gradeLabel.setVisible(false);
        schoolList.setVisible(true);

        StringBuilder sb = new StringBuilder();
        for (School s : user.schoolAndSubjects.keySet()) {
            sb.append("OŠ \"").append(s.getSchoolName()).append("\"").append(" - ").append(s.getPlace()).append("\n");
        }
        schoolList.setText(sb.toString());

        sb.setLength(0);

        subjectLabel.setVisible(true);
        subjectList.setVisible(true);
        for (Map.Entry<School, Set<Subject>> m : user.schoolAndSubjects.entrySet()) {
            for (Subject s : m.getValue()) {
                sb.append(s.getName()).append(" - ").append(s.getSchoolGrade()).append(" ").append(m.getKey().getSchoolName()).append("\n");
            }
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
            Collections.sort(studentUser.listOfGrades);
            for (Grade g : studentUser.listOfGrades) {
                gradesTable.getItems().add(g);
            }
        }

        btnSubmitChoice.setOnAction(event -> {
            subjectNameInputErrMsg.setVisible(false);
            gradesTable.getItems().clear();

            if (rbtnSortGradesForSubjectByDate.isSelected() && !subjectNameInput.getText().isBlank()) {
                String subjectName = subjectNameInput.getText();

                ArrayList<Grade> listOfGrades = studentUser.getListOfGradesForSubject(subjectName);
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
        tbAbsencesSubjectName.setCellValueFactory(new PropertyValueFactory("subject"));

        absencesTable.getItems().clear();
        for (Absences a : studentUser.listOfAbsences) {
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

            if (!studentUser.getQuestionsAndAnswersForProfessor(professorFullName).isEmpty()) {
                unselectOrHideRbtns(false, allRadioBtns);
                btnSubmitAnswers.setVisible(false);
                txtInfo.setText("Već ste ocjenili izabranog profesora:");
                txtInfo.setFill(Color.BLUE);
                Map<Question, String> QA = studentUser.getQuestionsAndAnswersForProfessor(professorFullName);

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

                    for (Map.Entry<Professor, Map<Question, String>> pq : studentUser.listOfProfessors.entrySet()) {
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
        for (Professor p : studentUser.listOfProfessors.keySet()) {
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
        cboxSelectProfessorSex.getItems().clear();
        cboxSelectStudentSex.getItems().add("Ženski");
        cboxSelectStudentSex.getItems().add("Muški");

        addStudentBtn.setOnAction(event -> {
            addStudentMsgLbl.setVisible(false);
            addStudentMsgLbl.setText("");

            String name = studentNameInputField.getText().toLowerCase();
            String surname = studentSurnameInputField.getText().toLowerCase();
            String mail = studentMailInputField.getText();
            String sex = cboxSelectStudentSex.getSelectionModel().getSelectedItem();

            int sexID = 0;
            if (sex.equals("Ženski")) {
                sexID = 0;
            } else if (sex.equals("Muški")) {
                sexID = 1;
            }

            if (!name.isBlank() && !surname.isBlank() && !mail.isBlank() && !sex.isBlank()) {
                String username = name + "." + surname;
                String password = username + "123";
                int accessDataID = DBUtils.addAccessDataToDB(username, mail, password);
                DBUtils.addStudentToDB(name, surname, sexID, accessDataID);

                studentNameInputField.clear();
                studentSurnameInputField.clear();
                studentMailInputField.clear();
                cboxSelectStudentSex.getSelectionModel().clearSelection();
                addStudentMsgLbl.setText("Dodali ste novog učenika.");
                String subject = "Dobro došli u sistem eDnevnik";
                String msg = "Obavještavamo Vas da ste registrovani kao učenik u sistem eDnevnik, pristupne podatke pronađite u nastavku ovog mail-a.\nKorisničko ime: " + username;
                sendMail(mail, password, subject, msg);
            } else {
                addStudentMsgLbl.setVisible(true);
                addStudentMsgLbl.setText("Molimo Vas da unesete sve potrebne podatke");
            }
        });
    }

    public void showAddProfessorPane() {
        hideVisiblePane();
        addProfessorPane.setVisible(true);
        cboxSelectProfessorSex.getItems().clear();
        cboxSelectProfessorSex.getItems().add("Ženski");
        cboxSelectProfessorSex.getItems().add("Muški");
        addProfessorMsgLbl.setVisible(false);
        addProfessorMsgLbl.setText("");

        addProfessorBtn.setOnAction(event -> {
            String name = professorNameInputField.getText().toLowerCase();
            String surname = professorSurnameInputField.getText().toLowerCase();
            String mail = professorMailInputField.getText();
            String sex = cboxSelectProfessorSex.getSelectionModel().getSelectedItem();

            int sexID = 0;
            if (sex.equals("Ženski")) {
                sexID = 0;
            } else if (sex.equals("Muški")) {
                sexID = 1;
            }

            if (!name.isBlank() && !surname.isBlank() && !mail.isBlank() && !sex.isBlank()) {
                String username = name + "." + surname;
                String password = username + "123";
                int accessDataID = DBUtils.addAccessDataToDB(username, mail, password);
                DBUtils.addProfessorToDB(name, surname, sexID, accessDataID);

                professorNameInputField.clear();
                professorSurnameInputField.clear();
                professorMailInputField.clear();
                cboxSelectProfessorSex.getSelectionModel().clearSelection();
                addProfessorMsgLbl.setVisible(true);
                addProfessorMsgLbl.setText("Dodali ste novog profesora.");
                String subject = "Dobro došli u sistem eDnevnik";
                String msg = "Obavještavamo Vas da ste registrovani kao profesor u sistem eDnevnik, pristupne podatke pronađite u nastavku ovog mail-a.\nKorisničko ime: " + username;
                sendMail(mail, password, subject, msg);
            } else {
                addProfessorMsgLbl.setVisible(true);
                addProfessorMsgLbl.setText("Molimo Vas da unesete sve potrebne podatke");
            }
        });
    }

    public void showAddSchoolPane() {
        hideVisiblePane();
        addSchoolPane.setVisible(true);

        addSchoolMsgLbl.setVisible(false);
        addSchoolMsgLbl.setText("");

        addSchoolBtn.setOnAction(event -> {
            String name = addSchoolNameInputField.getText();
            String city = addSchoolCityInputField.getText();
            String place = addSchoolPlaceInputField.getText();
            String country = addSchoolCountryInputField.getText();

            if (!name.isBlank() && !city.isBlank() && !place.isBlank() && !country.isBlank()) {
                DBUtils.addSchoolToDB(name, place, city, country);

                addSchoolNameInputField.clear();
                addSchoolCityInputField.clear();
                addSchoolPlaceInputField.clear();
                addSchoolCountryInputField.clear();
                addSchoolMsgLbl.setVisible(true);
                addSchoolMsgLbl.setText("Dodali ste novu školu.");
            } else {
                addProfessorMsgLbl.setVisible(true);
                addProfessorMsgLbl.setText("Molimo Vas da unesete sve potrebne podatke");
            }
        });
    }

    public void showAddSubjectPane() {
        hideVisiblePane();
        addSchoolSubjectPane.setVisible(true);

        addSubjectMsgLbl.setVisible(false);
        addSubjectMsgLbl.setText("");

        addSubjectBtn.setOnAction(event -> {
            String name = addSubjectNameInputField.getText();
            String subjectClass = addSubjectClassInputField.getText();

            if (!name.isBlank() && !subjectClass.isBlank()) {
                DBUtils.addSubjectToDB(name, Integer.parseInt(subjectClass));

                addSubjectNameInputField.clear();
                addSubjectClassInputField.clear();

                addSubjectMsgLbl.setVisible(true);
                addSubjectMsgLbl.setText("Dodali ste novi predmet.");
            } else {
                addSubjectMsgLbl.setVisible(true);
                addSubjectMsgLbl.setText("Molimo Vas da unesete sve potrebne podatke");
            }
        });
    }

    public void showAddSchoolSubjectPane() {
        hideVisiblePane();
        showAddSchoolSubjectPane.setVisible(true);
        cboxSelectSchool.getItems().clear();
        cboxSelectSubject.getItems().clear();

        for (School s : School.schools) {
            cboxSelectSchool.getItems().add(s);
        }
        for (Subject s : Subject.subjects) {
            cboxSelectSubject.getItems().add(s);
        }


        btnSaveSchoolSubject.setOnAction(event -> {
            School school = cboxSelectSchool.getSelectionModel().getSelectedItem();
            Subject subject = cboxSelectSubject.getSelectionModel().getSelectedItem();

            if (SchoolSubject.getSchoolSubject(school, subject, professorUser) == null) {
                addSchoolSubjectMsgLbl.setVisible(true);
                addSchoolSubjectMsgLbl.setText("Dodijeljen Vam je novi predmet.");
                DBUtils.addSchoolSubjectToDB(subject.getSubjectID(), school.getSchoolID(), professorUser.getProfessorID());
                cboxSelectSchool.setValue(null);
                cboxSelectSubject.setValue(null);
            } else {
                addSchoolSubjectMsgLbl.setVisible(true);
                addSchoolSubjectMsgLbl.setTextFill(Color.RED);
                addSchoolSubjectMsgLbl.setText("Vi vec vodite ovaj predmet!");
            }
        });
    }

    public void showAddGradeOrAbsence() {
        hideVisiblePane();
        showAddGradeOrAbsencePane.setVisible(true);
        cboxSelectProfSchool.getItems().clear();
        cboxSelectProfSubject.getItems().clear();
        cboxSelectStudent.getItems().clear();
        cboxSelectStudent.setValue(null);
        cboxSelectProfSubject.setValue(null);
        cboxSelectProfSchool.setValue(null);
        gradeAbsenceDateInput.setValue(null);
        rbtnAbsence.setSelected(false);
        rbtnGrade.setSelected(false);
        addGradeAbsenceMsgLbl.setVisible(false);

        for (School s : professorUser.schoolAndSubjects.keySet()) {
            cboxSelectProfSchool.getItems().add(s);
        }

        final School[] school = new School[1];
        cboxSelectProfSchool.setOnAction(event -> {
            cboxSelectProfSubject.getItems().clear();
            school[0] = cboxSelectProfSchool.getSelectionModel().getSelectedItem();

            for (Subject s : professorUser.schoolAndSubjects.get(school[0])) {
                cboxSelectProfSubject.getItems().add(s);
            }
        });

        final Subject[] subject = new Subject[1];
        cboxSelectProfSubject.setOnAction(event -> {
            cboxSelectStudent.getItems().clear();
            subject[0] = cboxSelectProfSubject.getSelectionModel().getSelectedItem();


            for (Student s : Student.allStudents) {
                if (s.getSchool() == null || s.getSchool().equals(school[0])) {
                    cboxSelectStudent.getItems().add(s);
                }
            }
        });

        final Student[] student = new Student[1];
        cboxSelectStudent.setOnAction(event -> {
            student[0] = cboxSelectStudent.getSelectionModel().getSelectedItem();
        });

        rbtnGrade.setOnAction(e -> {
            addGradeInputField.setVisible(true);
            addGradeInputField.setText("");
        });

        btnSubmitGradOrAbsence.setOnAction(event -> {
            SchoolSubject schoolSubject = SchoolSubject.getSchoolSubject(school[0], subject[0], professorUser);

            if (rbtnAbsence.isSelected()) {
                LocalDate localDate = gradeAbsenceDateInput.getValue();

                if (student[0].getSchool() == null) {
                    student[0].setSchool(school[0]);
                }
                if (student[0].getSchoolGrade() == null) {
                    student[0].setSchoolGrade(subject[0].getSchoolGrade());
                }

                DBUtils.addAbsenceToDB(student[0].getStudentID(), schoolSubject.getSchoolSubjectID(), String.valueOf(localDate));
                addGradeAbsenceMsgLbl.setText("Dodali ste izostanak uceniku.");
                addGradeAbsenceMsgLbl.setVisible(true);
                cboxSelectStudent.setValue(null);
                cboxSelectProfSubject.setValue(null);
                cboxSelectProfSchool.setValue(null);
                gradeAbsenceDateInput.setValue(null);
                rbtnAbsence.setSelected(false);
            } else {
                int grade = Integer.parseInt(addGradeInputField.getText());
                LocalDate localDate = gradeAbsenceDateInput.getValue();

                if (student[0].isInClass(subject[0], localDate, professorUser)) {
                    if (grade >= 1 && grade <= 5) {
                        if (!student[0].hasReceivedTwoOrMoreGrades(localDate)) {
                            if (student[0].smallestDifferenceBetweenDates(subject[0], localDate) >= 7) {
                                if (student[0].getSchool() == null) {
                                    student[0].setSchool(school[0]);
                                }
                                if (student[0].getSchoolGrade() == null) {
                                    student[0].setSchoolGrade(subject[0].getSchoolGrade());
                                }

                                DBUtils.addGradeToDB(student[0].getStudentID(), schoolSubject.getSchoolSubjectID(), grade, String.valueOf(localDate));
                                addGradeAbsenceMsgLbl.setText("Dodali ste ocjenu učeniku.");
                                addGradeAbsenceMsgLbl.setVisible(true);
                                cboxSelectStudent.setValue(null);
                                cboxSelectProfSubject.setValue(null);
                                cboxSelectProfSchool.setValue(null);
                                rbtnGrade.setSelected(false);
                                rbtnAbsence.setSelected(false);
                                gradeAbsenceDateInput.setValue(null);
                                addGradeInputField.setVisible(false);
                            } else {
                                addGradeAbsenceMsgLbl.setText("Razlika između datuma unesenih ocjena treba da iznosi 7 dana.");
                                addGradeAbsenceMsgLbl.setTextFill(Color.RED);
                                addGradeAbsenceMsgLbl.setVisible(true);
                                addGradeInputField.setText("");
                                cboxSelectStudent.setValue(null);
                                cboxSelectProfSubject.setValue(null);
                                cboxSelectProfSchool.setValue(null);
                                gradeAbsenceDateInput.setValue(null);
                            }
                        } else {
                            addGradeAbsenceMsgLbl.setText("Učenik je dobio maksimalan broj ocjena.");
                            addGradeAbsenceMsgLbl.setTextFill(Color.RED);
                            addGradeAbsenceMsgLbl.setVisible(true);
                            addGradeInputField.setText("");
                            cboxSelectStudent.setValue(null);
                            cboxSelectProfSubject.setValue(null);
                            cboxSelectProfSchool.setValue(null);
                            gradeAbsenceDateInput.setValue(null);
                        }
                    } else {
                        addGradeAbsenceMsgLbl.setText("Dozvoljene su ocjene od 1 do 5.");
                        addGradeAbsenceMsgLbl.setTextFill(Color.RED);
                        addGradeAbsenceMsgLbl.setVisible(true);
                        addGradeInputField.setText("");
                        cboxSelectStudent.setValue(null);
                        cboxSelectProfSubject.setValue(null);
                        cboxSelectProfSchool.setValue(null);
                        gradeAbsenceDateInput.setValue(null);
                    }
                } else {
                    addGradeAbsenceMsgLbl.setText("Učenik je odsutan sa časa.");
                    addGradeAbsenceMsgLbl.setTextFill(Color.RED);
                    addGradeAbsenceMsgLbl.setVisible(true);
                    addGradeInputField.setText("");
                    cboxSelectStudent.setValue(null);
                    cboxSelectProfSubject.setValue(null);
                    cboxSelectProfSchool.setValue(null);
                    gradeAbsenceDateInput.setValue(null);
                }
            }
        });
    }

    public void showAllStudentsPane() {
        hideVisiblePane();
        allStudentsPane.setVisible(true);
        tableAllStudents.getItems().clear();
        cboxSelectSubjectToShowStudents.getItems().clear();
        tbStudentInfo.setCellValueFactory(new PropertyValueFactory("fullName"));

        for (School s : professorUser.schoolAndSubjects.keySet()) {
            for (Subject sb : professorUser.schoolAndSubjects.get(s)) {
                cboxSelectSubjectToShowStudents.getItems().add(sb);
            }
        }

        btnDisplayStudents.setOnAction(event -> {
            tableAllStudents.getItems().clear();
            taStudentInfo.setVisible(false);
            Subject subject = cboxSelectSubjectToShowStudents.getSelectionModel().getSelectedItem();

            for (Student s: Student.allStudents) {
                if (s.getSchoolGrade() != null && s.getSchoolGrade().equals(subject.getSchoolGrade()) && s.hasAtLeastOneGrade(subject) || s.hasAtLeastOneAbsence(subject)) {
                    tableAllStudents.getItems().add(s);
                }
            }

            tbStudentInfo.setCellFactory(new Callback<TableColumn<Student, String>, TableCell<Student, String>>() {
                @Override
                public TableCell<Student, String> call(TableColumn<Student, String> col) {
                    final TableCell<Student, String> cell = new TableCell<>();
                    cell.textProperty().bind(cell.itemProperty());

                    cell.setOnMouseClicked(new EventHandler<Event>() {
                        @Override
                        public void handle(Event event) {
                            taStudentInfo.setVisible(true);
                            Student s = Student.getStudentWithName(cell.getItem().split(" ")[0], cell.getItem().split(" ")[1]);
                            StringBuilder sb = new StringBuilder();
                            if (s != null) {
                                sb.append(s.getFullName()).append("\n");
                                sb.append("Ocjene:").append("\n");
                                for (Grade g : s.listOfGrades) {
                                    if (g.getSubject().equals(subject) && g.getProfessor().equals(professorUser)) {
                                        sb.append(g.getSubjectGrade()).append(" ");
                                    }
                                }
                                sb.append("\n");
                                sb.append("Izostanci:").append("\n");
                                for (Absences a: s.listOfAbsences) {
                                    if (a.getSchoolSubject().getSubject().equals(subject) && a.getSchoolSubject().getProfessor().equals(professorUser)) {
                                        sb.append(a.getDate()).append(" ");
                                    }
                                }
                                sb.append("\n");
                            }
                            taStudentInfo.setText(sb.toString());
                        }
                    });
                    return cell ;
                }
            });
        });
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
        } else if (showAddSchoolSubjectPane.isVisible()) {
            showAddSchoolSubjectPane.setVisible(false);
        } else if (showAddGradeOrAbsencePane.isVisible()) {
            showAddGradeOrAbsencePane.setVisible(false);
        } else if (allStudentsPane.isVisible()) {
            allStudentsPane.setVisible(false);
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

    private void sendMail(String toUserMail, String password, String subject, String msg) {
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
            message.setSubject(subject);

            StringBuilder sb = new StringBuilder();
            sb.append("Poštovani korisniče,").append("\n\n");
            sb.append(msg).append("\n");
            sb.append("Pristupna šifra: ").append(password).append("\n\n");
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
