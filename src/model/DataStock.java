package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataStock implements Serializable,Cloneable{

	private static final long serialVersionUID = 1L;
	public static final int ARRAY_START_SIZE = 10;
	public static final int OPEN_ANSWER_INDEX = 0;
	private static final int Question = 0;

	protected ArrayList<Question> questions; 

	public DataStock() {
		questions = new ArrayList<>();
	}
	public ArrayList getQuestions() {
		return questions;
	}

	public int getNumOfQuestion(){
		return questions.size();
	}

	public void addQuestion(Question question) throws QuestionAlreadyExistException, CloneNotSupportedException {
		if(questions.contains(question))
			throw new QuestionAlreadyExistException();


		questions.add(question.clone());
		questions.sort(new CompareQuestioinByAnswerLegnthText());

	}

	public void updateQuestion(int index, String newQuestionText) throws QuestionAlreadyExistException,IndexOutOfBoundsException {
		if(index < 0 || index > questions.size())
			throw new IndexOutOfBoundsException();
		Question tmp=null;
		if(this.questions.get(index) instanceof AmericanQuestion)
			tmp = new AmericanQuestion(newQuestionText);
		else if(this.questions.get(index) instanceof OpenQuestion) {
			tmp = new OpenQuestion(newQuestionText);
		}

		if(questions.contains(tmp))
			throw new QuestionAlreadyExistException();

		this.questions.get(index).changeQues(newQuestionText);

	}
	public Question getQuestion(int index) throws CloneNotSupportedException {
		if(index < 0 || index > questions.size())
			throw new IndexOutOfBoundsException();

		if(this.questions.get(index) instanceof AmericanQuestion)
			return (AmericanQuestion)questions.get(index);
		else {
			return new OpenQuestion((OpenQuestion)this.questions.get(index));
		}
	}
	public Question getQuestionByRef(int index) {
		if(index < 0 || index > this.questions.size())
			throw new IndexOutOfBoundsException();

		if(this.questions.get(index) instanceof AmericanQuestion)
			return this.questions.get(index);
		else {
			return this.questions.get(index);
		}
	}
	public void updateAnswer(Question quest, int answerIndex, String newAnswerText) throws CloneNotSupportedException {
		if(quest instanceof AmericanQuestion) {
			AmericanQuestion tmp = (AmericanQuestion) quest;
			if(answerIndex < 0 || answerIndex >= tmp.getNumOfAnswers()) {
				throw new IndexOutOfBoundsException("American answer wrong index");
			}
			tmp.getAnswers().get(answerIndex).changeAnswerText(newAnswerText);
		}
		else if(quest instanceof OpenQuestion) {
			if(answerIndex != this.OPEN_ANSWER_INDEX)
				throw new IndexOutOfBoundsException("open answer wrong index");
			OpenQuestion tmp = (OpenQuestion) quest;
			tmp.getAnswer().changeAnswerText(newAnswerText);
		}
		questions.sort(new CompareQuestioinByAnswerLegnthText());
	}
	public void deleteAnswer(Question quest, int answerIndex) {
		if(quest instanceof AmericanQuestion) {
			AmericanQuestion tmp = (AmericanQuestion) quest;
			if(answerIndex < 0 || answerIndex >= tmp.getNumOfAnswers())
				throw new IndexOutOfBoundsException("American answer wrong index");
			tmp.deleteAnswer(answerIndex);
		}
		else if(quest instanceof OpenQuestion) {
			if(answerIndex != this.OPEN_ANSWER_INDEX)
				throw new IndexOutOfBoundsException("open answer wrong index");
			OpenQuestion tmp = (OpenQuestion) quest;
			tmp.deleteAnswer();
		}

	}
	@Override
	public String toString() {
		String back="All Questions: \n";
		if(questions.size() == 0) {
			back +="there is no data";
			return back;
		}
		for (int i = 0; i < questions.size(); i++) {
			back+= (i+1)+"."+ questions.get(i);
		}
		return back;
	}
	public void createHardCoded() throws QuestionAlreadyExistException, CloneNotSupportedException, FileNotFoundException, IOException, ClassNotFoundException {

		try {
			ObjectInputStream inFile= new ObjectInputStream(new FileInputStream("questions.dat"));
			ArrayList<Question> allQuestionsFromFile=((ArrayList<Question>)inFile.readObject());
			questions=allQuestionsFromFile;

		}catch (FileNotFoundException e) {
			ArrayList<AmericanAnswer> americanAnswerSugar = new ArrayList<AmericanAnswer>();

			americanAnswerSugar.add(new AmericanAnswer( "500gr", false));
			americanAnswerSugar.add(new AmericanAnswer("1kg", true));
			americanAnswerSugar.add(new AmericanAnswer("4kg", false));
			americanAnswerSugar.add(new AmericanAnswer("7gr", false));

			ArrayList<AmericanAnswer> americanAnswerCake =new ArrayList<AmericanAnswer>();

			americanAnswerCake.add(new AmericanAnswer( "Halva cake", true));
			americanAnswerCake.add(new AmericanAnswer("Chooclate cake", true));
			americanAnswerCake.add(new AmericanAnswer("bisli", false));

			ArrayList<AmericanAnswer> americanAnswerRest = new ArrayList<AmericanAnswer>();
			americanAnswerRest.add(new AmericanAnswer( "Cafe italy", false));
			americanAnswerRest.add(new AmericanAnswer("Romano", true));
			americanAnswerRest.add(new AmericanAnswer("midal", false));
			americanAnswerRest.add(new AmericanAnswer( "alfredo", false));
			americanAnswerRest.add(new AmericanAnswer("macdonald", true));
			americanAnswerRest.add(new AmericanAnswer("soso", false));
			americanAnswerRest.add(new AmericanAnswer("papi cholo", false));

			ArrayList<AmericanAnswer> americanAnswerDessert = new ArrayList<AmericanAnswer>();
			americanAnswerDessert.add(new AmericanAnswer( "Cafe", false));
			americanAnswerDessert.add(new AmericanAnswer("chooclate", false));
			americanAnswerDessert.add(new AmericanAnswer("banana", false));
			americanAnswerDessert.add(new AmericanAnswer( "cake", false));
			americanAnswerDessert.add(new AmericanAnswer("bisli", false));
			americanAnswerDessert.add(new AmericanAnswer("spageti", false));
			americanAnswerDessert.add(new AmericanAnswer("gomi gam", false));

			ArrayList<Question> ques =new ArrayList<Question>();
			ques.add(new AmericanQuestion("How much sugar is in a Pillsbury cake?").addAnswers(americanAnswerSugar));
			ques.add(new AmericanQuestion("What is the popolar cake in israel?").addAnswers(americanAnswerCake));
			ques.add(new AmericanQuestion("What is the most successful restaurant in Israel?").addAnswers(americanAnswerRest));
			ques.add(new AmericanQuestion("What is the best dessert?").addAnswers(americanAnswerDessert));
			ques.add(new OpenQuestion("What is the best junkFood?","Pizza"));
			ques.add(new OpenQuestion("What is the best cake?", "Chooclate"));
			ques.add(new OpenQuestion("What is most poplar junkFood?", "Shwarma"));
			ques.add(new OpenQuestion("What is the best burger?","Vitrina"));


			for (int i = 0; i < ques.size(); i++) 
				this.addQuestion(ques.get(i));

		}

	}

	public void deleteQuestion(int index) {
		questions.remove(index);
	}
	
	@Override
	public DataStock clone() throws CloneNotSupportedException{
		DataStock tmp= (DataStock)super.clone();
		tmp.questions=new ArrayList<Question>();

		for (int i = 0; i < this.questions.size(); i++) {
			tmp.questions.add(this.questions.get(i).clone());
		}
	
		return tmp;
	}




}
