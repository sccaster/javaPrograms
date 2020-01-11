package edu.waketech.csc251.collection;

import java.io.Serializable;
import java.util.ArrayList;

import edu.waketech.csc251.tools.Screener;


@SuppressWarnings("serial")
public class DataSetGeneric<E extends Measurable> extends java.util.ArrayList<E> implements Serializable {
	

	private ArrayList<E> data;

	public DataSetGeneric() {
		}
	
	public String getList(ArrayList<E> dataStore) {
		if (dataStore.isEmpty()) {
			return null;
		}
		String mEle = "[1] " + dataStore.get(0) + "\n";
		for (int i = 1; i < dataStore.size(); i++) {
			mEle = mEle + "[" + (i + 1) + "] " + dataStore.get(i) + "\n";	
		}
		return mEle;
	}
	
	public String getList(ArrayList<E> dataStore, Screener<E> elementScreener) {
		int t = 0;
		if (dataStore.isEmpty()) {
			return null;
		}
		String mEle = "";
		for (int i = 0; i < dataStore.size(); i++) {
			if (elementScreener.test(dataStore.get(i)) == true) {
				mEle = mEle + "[" + (t) + "] " + dataStore.get(i) + "\n";
				t++;
			}
		}
		return mEle;
	}

	

	public E getMin() {
		if (data.isEmpty()) {
			return null;
		}
		E mEle = data.get(0);
		for (int i = 1; i < data.size(); i++) {
			if ((mEle).getMeasure() > ((data.get(i)).getMeasure())) {
				mEle = data.get(i);
			}
		}
		return mEle;
	}


	public E getMax(ArrayList<E> dataStore) {
		if (dataStore.isEmpty()) {
			return null;
		}
		E mEle = dataStore.get(0);
		for (int i = 1; i < dataStore.size(); i++) {
			if ((mEle).getMeasure() < ((dataStore.get(i)).getMeasure())) {
				mEle = dataStore.get(i);
			}
		}
		return mEle;
	}


	@Override
	public String toString() {
		return "DataSetGeneric [\n size()=" + size() + "\n getMin()=" + getMin() 
				+ " Generic=\n" + data.toString() + "]";
	}


}

