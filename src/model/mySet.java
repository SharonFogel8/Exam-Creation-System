package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class mySet<T extends AmericanAnswer> implements Cloneable, Serializable{

	private final int START_SIZE = 10;	
	private T[] myArray;
	private int numOfObject;

	public mySet(){
		myArray = (T[]) new AmericanAnswer[START_SIZE]; 
		numOfObject = 0;
	}
	public T get(int index) throws ArrayIndexOutOfBoundsException{
		if(index < 0 || index >= numOfObject) {
			throw new ArrayIndexOutOfBoundsException("American answer wrong index");
		}
		T tmp = (T) new AmericanAnswer();
		tmp = myArray[index];
		return tmp;
	}

	public int size() {
		return numOfObject;
	}
	public boolean isEmpty() {
		return numOfObject == 0;
	}

	public boolean contains(T object) {
		for (int i = 0; i < this.numOfObject; i++) {
			if(myArray[i].equals(object))
				return true;
		}
		return false;

	}


	public boolean add(T newObject) {
		if(contains(newObject))
			return false;
		
		myArray[numOfObject]= newObject;
		this.numOfObject++;
		return true;
	}
	public void remove(int index) throws ArrayIndexOutOfBoundsException{
		if(index < 0 || index >= numOfObject) {
			throw new ArrayIndexOutOfBoundsException("wrong index");
		}
		myArray[index]=null;
		numOfObject--;
		for (int i = index+1; i < myArray.length; i++) {
			if(myArray[i] != null && i>0) {
				T tmp = myArray[i];
				myArray[i]=null;
				myArray[i-1] = tmp;
			}
			else {
				return;
			}
		}
	}


	public void clear() {
		for (int i = 0; i < numOfObject; i++) {
			myArray[i] = null;

		}
		numOfObject=0;

	}
	
	@Override
	public mySet<T> clone() throws CloneNotSupportedException  {
		mySet<T> tmp =(mySet<T>)super.clone();
		ArrayList<T> tmp_values =  (ArrayList<T>) new ArrayList<T>(Arrays.asList(myArray)).clone();
		tmp.myArray = (T[]) tmp_values.toArray(new AmericanAnswer[0]);

		return tmp;
	}
}
