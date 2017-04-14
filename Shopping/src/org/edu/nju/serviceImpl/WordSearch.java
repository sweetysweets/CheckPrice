package org.edu.nju.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.edu.nju.dao.*;
import org.edu.nju.dao.StoreDao;
import org.edu.nju.model.Commodity;
import org.edu.nju.service.WordSearchService;

import org.edu.nju.util.WordSplit;



public class WordSearch implements WordSearchService {
	WordSplit wordSplit = new WordSplit();
	CommodityDao commodityDao = new CommodityDao();
	StoreDao storeDao = new StoreDao();
	SynonymTableDao synonymTabledao = new SynonymTableDao();

	@Override
	public Map<Commodity, Integer> getCommodityRank(String searchSentence) {

		List<String> wordList = wordSplit.resultSpilt(searchSentence);

		List<Commodity> matchCommodityList = new ArrayList<Commodity>();
		for (String s : wordList) {
			// 根据wordList里面用户的输入的分词得到该分词对应的同义词列表
			List<String> synomList = synonymTabledao.getSynomList(s);
			if (synomList != null) {
				for (String keyword : synomList) {
					// 对每个同义词查询商品描述，如果描述包含该同义词则商品与用户的输入匹配
					List<Commodity> keyCommodityList = getCommodityByKeyWord(keyword);
					if (keyCommodityList.size() != 0) {
						matchCommodityList.addAll(keyCommodityList);
					}
				}

			}
		}
		removeDuplicate(matchCommodityList);
		return rankCommodity(matchCommodityList);
	}

	private List<Commodity> getCommodityByKeyWord(String keyword) {
		List<Commodity> keyWordcommodityList = new ArrayList<Commodity>();
		List<Commodity> commodityList = commodityDao.getAllCommodity();
		for (Commodity c : commodityList) {
			String description = c.getDescription();
			if (description.contains(keyword)) {
				keyWordcommodityList.add(c);
			}
		}
		return keyWordcommodityList;
	}

	private Map<Commodity, Integer> rankCommodity(List<Commodity> commodityList) {
		Map<Commodity, Integer> map = new HashMap<Commodity, Integer>();
		for (Commodity c : commodityList) {
			String storeId = c.getStoreId();
			int priority = storeDao.getStorePriority(storeId);
			map.put(c, priority);
		}
		return map;
	}

	public static void removeDuplicate(List<Commodity> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(j).equals(list.get(i))) {
					list.remove(j);
				}
			}
		}
		

	}

	public static void main(String[] args) {
//		List<Commodity> commodityList = new ArrayList<Commodity>();
//		Commodity c1 = new Commodity();
//		Commodity c2 = new Commodity();
//		Commodity c3 = new Commodity();
//		c1.setId(1);
//		c2.setId(1);
//		c3.setId(3);
//		commodityList.add(c1);
//		commodityList.add(c2);
//		commodityList.add(c3);
//		removeDuplicate(commodityList);
//		for (Commodity c : commodityList) {
//			System.out.println(c.getId());
//		}
	}

}
