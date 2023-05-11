package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;


import Listeners.ListenersOfModel;
import model.Management.eChangesUserNeedToKnow;


public class Management{
	public static enum eChangesUserNeedToKnow {
		Succeeded, QuestionAlreadyExists
	};
	private DataStock myData;
	private ListenersOfModel listener;
	private ArrayList <DataStock> tests = new ArrayList <DataStock>();

	public Management() throws QuestionAlreadyExistException, CloneNotSupportedException, FileNotFoundException, ClassNotFoundException, IOException {
		myData = new DataStock();
		myData.createHardCoded();
	}


	public String showAllQuestions() {
		return myData.toString();
	}
	public ArrayList<Question> getData() {
		return myData.getQuestions();
	}


	public void addQuestionToData(Question question){
		try {
			myData.addQuestion(question);
			listener.modelUpdateUiQuestionActionStatus( eChangesUserNeedToKnow.Succeeded);
			basicInfoForListener();
		} catch (QuestionAlreadyExistException e) {
			listener.modelUpdateUiQuestionActionStatus( eChangesUserNeedToKnow.QuestionAlreadyExists);
		} catch (CloneNotSupportedException e) {
		}
	}


	public void updateQuestionTxt(int index, String newQuestionText)  {
		try {
			myData.updateQuestion(index , newQuestionText);
			listener.modelUpdateUiQuestionActionStatus( eChangesUserNeedToKnow.Succeeded);
			listener.modelUpdateUiQuestionChangedFullShow(newQuestionText, index);
			basicInfoForListener();
		} catch (QuestionAlreadyExistException e) {
			listener.modelUpdateUiQuestionActionStatus( eChangesUserNeedToKnow.QuestionAlreadyExists);
		} catch (IndexOutOfBoundsException e) {
		}
	}


	public Question getQuestion(int index) throws CloneNotSupportedException {
		return myData.getQuestion(index);
	}
	public Question getQuestionByRef(int index) {
		return myData.getQuestionByRef(index);
	}


	public void updateAnswer(Question quest, int answerIndex, String newAnswerText) throws CloneNotSupportedException {
		myData.updateAnswer(quest,answerIndex,newAnswerText);

	}

	public void deleteAnswer(Question quest, int anwerIndex) {
		myData.deleteAnswer(quest,anwerIndex);
	}

	public int getNumOfQuestions() {
		return myData.getNumOfQuestion();
	}
	public void deleteQuestion(int index) {
		myData.deleteQuestion(index);
		listener.modelUpdateUiQuestionActionStatus( eChangesUserNeedToKnow.Succeeded);
		allQuestions();
		basicInfoForListener();

	}


	public void addListener(ListenersOfModel listener) {
		this.listener = listener;
		basicInfoForListener();
	}


	private void basicInfoForListener() {
		this.listener.modelUpdateUiQuestionsFullShow(this.showAllQuestions());
	}


	public void allQuestions() {
		listener.modelUpdateUiQuestionsList(this.myData.getQuestions());

	}


	public void updateAnsTxt(String newText, int questIndex, int ansIndex) {
		try {
			Question question = myData.getQuestion(questIndex);
			this.updateAnswer(question, ansIndex, newText);
			listener.modelUpdateUiQuestionActionStatus( eChangesUserNeedToKnow.Succeeded);
			allQuestions();
			basicInfoForListener();
		} catch (CloneNotSupportedException e) {
		}
	}

	public void updateAnsDelete(int questIndex, int ansIndex) {
		Question question = myData.getQuestionByRef(questIndex);
		this.deleteAnswer(question, ansIndex);
		listener.modelUpdateUiQuestionActionStatus( eChangesUserNeedToKnow.Succeeded);
		allQuestions();
		basicInfoForListener();
	}


	public void createManualTest(ArrayList<Question> dataForTest) {
		TestManual testManual = new TestManual();
		
			try {
				dataForTest.forEach(newQuest -> {
					try {
						testManual.addQuestion(newQuest);
					}
					catch (Exception e) {
					}
				});
				testManual.addTwoAnswersToAmericanQuests();
				tests.add(testManual);


				LocalDate date = LocalDate.now();

				File examFile = new File("exam_" + date);
				PrintWriter pwExam = new PrintWriter(examFile);

				File solutionFile = new File("solution_" + date);
				PrintWriter pwSolution = new PrintWriter(solutionFile);

				pwExam.println(testManual.getNumOfQuestion());
				pwSolution.println(testManual.getNumOfQuestion());

				int i = 0;
				int counter = testManual.getNumOfQuestion();
				while( counter > 0){
					String QuestionText = testManual.getQuestionByRef(i).getQuestionText();
					pwExam.println(QuestionText);
					Question question = testManual.getQuestionByRef(i);
					if (question instanceof AmericanQuestion) {
						String AmericanAnswerText = testManual.getCorrectAns((AmericanQuestion) question);
						pwSolution.println();
						pwSolution.println(AmericanAnswerText);
					} else {
						Answer answer = ((OpenQuestion) question).getAnswer();
						pwSolution.println(question.getQuestionText());

						answer.save(pwSolution);
					}
					i++;
					counter--;
				}

				pwExam.close();
				pwSolution.close();
				listener.modelUpdateUiQuestionActionStatus(eChangesUserNeedToKnow.Succeeded);
			}
			catch (Exception e) {
			}

	}
	public void createAutomaticTest(int numOFQuests) {

		TestAutomatic testAutomatic = new TestAutomatic();

		try {
			for (int i = 0; i < getNumOfQuestions(); i++){
				testAutomatic.addQuestion(getQuestion(i));
			}

			testAutomatic.addTwoAnswersToAmericanQuests();


			testAutomatic.createTest(numOFQuests);
			tests.add(testAutomatic);

			LocalDate date = LocalDate.now();

			File examFile = new File("exam" + date);
			PrintWriter pwExam = new PrintWriter(examFile);

			File f1 = new File("solution" + date);
			PrintWriter pwSolution = new PrintWriter(f1);

			pwExam.println(numOFQuests);
			pwSolution.println(numOFQuests);

			int i = 0;
			int counter = numOFQuests;
			while( counter > 0){
				String QuestionText = testAutomatic.getQuestionByRef(i).getQuestionText();
				pwExam.println(QuestionText);
				Question automaticTestQuestion = testAutomatic.getQuestionByRef(i);
				if (automaticTestQuestion instanceof AmericanQuestion) {
					String AmericanAnswerText = testAutomatic.getCorrectAns((AmericanQuestion) automaticTestQuestion);
					pwSolution.println();
					pwSolution.println(AmericanAnswerText);
				} 
				else {
					Answer automaticTestAnswer = ((OpenQuestion) automaticTestQuestion).getAnswer();
					pwSolution.println(automaticTestQuestion.getQuestionText());

					automaticTestAnswer.save(pwSolution);
				}
				i++;
				counter--;
			}

			pwExam.close();
			pwSolution.close();
			listener.modelUpdateUiQuestionActionStatus(eChangesUserNeedToKnow.Succeeded);
		}
		catch (Exception e) {
		}

	}


	public void allTests() {
		listener.modelUpdateUiTestsList(tests);
		
	}


	public void CopyTest(int userTestIndex) {
		try {
			tests.add(tests.get(userTestIndex).clone());
			listener.modelUpdateUiTestsList(tests);
			listener.modelUpdateUiQuestionActionStatus(eChangesUserNeedToKnow.Succeeded);

		} catch (CloneNotSupportedException e) {
		}
		
	}
	public void saveBeforeExit() {
		ObjectOutputStream outFile;
		try {
			outFile = new ObjectOutputStream(new FileOutputStream("questions.dat"));
			outFile.writeObject(getData());
			outFile.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}

		
	}

}
