package org.edu.nju.service;

import java.util.List;
import java.util.Map;

import org.edu.nju.model.Commodity;
import org.edu.nju.vo.CommentVO;
import org.edu.nju.vo.CommodityVO;



public interface UserService {
	public Map<Commodity, Integer> search(String searchSentence);
	public CommodityVO showCommodityInfo(String commodityId);
	public List<CommentVO> showCommodityComment(String commodityId);//无评论则返回null
	public boolean comment(String content,String commodityId,String userId);

}
