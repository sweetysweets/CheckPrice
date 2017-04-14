package org.edu.nju.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SynonymTableDao {



	public ArrayList<String> getList() {
		ArrayList<String> totalWordList=new ArrayList<String>();
		totalWordList.add("乒乓球");
		totalWordList.add("房价");
		totalWordList.add("乒乓球拍");
		
		return totalWordList;
	}
//如果无该词所对应的同义词请直接返回null
	public List<String> getSynomList(String s) {
		// TODO Auto-generated method stub
		return null;
	}

}
