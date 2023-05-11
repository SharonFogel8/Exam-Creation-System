package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public interface Menuable {
	
	void optionMakeAutomaticTest(Management manager, Scanner src)
			throws QuestionAlreadyExistException, FileNotFoundException, CloneNotSupportedException;
	void optionMakeManulTest(Management manager, Scanner src) throws QuestionAlreadyExistException, FileNotFoundException, CloneNotSupportedException;

	
	void addQuestionAnswers(Scanner src, AmericanQuestion originalQuest, Question newQuest) throws QuestionAlreadyExistException, ArrayIndexOutOfBoundsException, CloneNotSupportedException;

	void optionDeleteAnswer(Management manager, Scanner src);
	void showAllTests();

	void optionUpdateAnswer(Management manager, Scanner src) throws CloneNotSupportedException;
	void optionUpdateQuestion(Management manager, Scanner src) throws ArrayIndexOutOfBoundsException, QuestionAlreadyExistException;

	void optionAddQuestions(Management manager, Scanner src) throws QuestionAlreadyExistException, CloneNotSupportedException;

	void optionCreateCopyOfTest(Scanner src)throws CloneNotSupportedException;



	void optionShowAllQuestions(Management manager);
	void optionDeleteQuestion(Management manager,Scanner src);

	void newAmericanQuestion(Management manager,Scanner src, String quesText) throws QuestionAlreadyExistException, CloneNotSupportedException;
	void newOpenQuestion(Management manager,Scanner src , String quesText) throws QuestionAlreadyExistException, CloneNotSupportedException;

	 int scannerGetInt(Scanner src);
		
	
}
