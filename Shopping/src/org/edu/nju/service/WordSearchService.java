package org.edu.nju.service;

import java.util.Map;

import org.edu.nju.model.Commodity;



public interface WordSearchService {
	public Map<Commodity, Integer> getCommodityRank(String searchSentence);
	
}
