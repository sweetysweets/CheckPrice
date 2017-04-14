package org.edu.nju.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.edu.nju.dao.*;
import org.edu.nju.dao.CommodityDao;
import org.edu.nju.model.Comment;
import org.edu.nju.model.Commodity;
import org.edu.nju.service.*;
import org.edu.nju.vo.CommentVO;
import org.edu.nju.vo.CommodityVO;

import com.sun.jmx.snmp.daemon.CommunicationException;
import com.sun.org.apache.bcel.internal.generic.NEW;



public class UserServiceImpl implements UserService{
	WordSearchService wordSerachService=new WordSearch();
	SensitiveService sensitiveService=new SensitiveServiceImpl();
	CommodityDao commodityDao=new CommodityDao();
	CommentDao commentdao=new CommentDao();
	UserDao userdao=new UserDao();
		
	@Override
	public Map<Commodity, Integer> search(String searchSentence) {
		
		return wordSerachService.getCommodityRank(searchSentence);
	}

	@Override
	public CommodityVO showCommodityInfo(String commodityId) {
		Commodity c=commodityDao.getCommodityById(commodityId);		
		return getCommodityVO(c);
	}

	private CommodityVO getCommodityVO(Commodity c) {
	
		return null;
	}

	@Override
	public boolean comment(String content, String commodityId,String userId) {
		//是在评论的时候就检查敏感词
		if(sensitiveService.isSensitive(content)){
			return false;
		}else{
			Date date=new Date();
			commentdao.save(content,date,commodityId,userId);
			return true;
		}
	}

	@Override
	public List<CommentVO> showCommodityComment(String commodityId) {
		List<Comment> commentList=commentdao.getCommentListByCommodityId(commodityId);
		if(commentList==null){
			return null;
		}
		ArrayList<CommentVO> commentVOList=new ArrayList<CommentVO>();
		for(Comment c:commentList){
			CommentVO commentVO=convertCommentToCommentVO(c);
			commentVOList.add(commentVO);
		}
		Collections.sort(commentVOList, new Comparator<CommentVO>(){  
			  
            /*  
             * 默认为升序排列
             * int compare(Student o1, Student o2) 返回一个基本类型的整型，  
             * 返回负数表示：o1 小于o2，  
             * 返回0 表示：o1和o2相等，  
             * 返回正数表示：o1大于o2。  
             */  
            public int compare(CommentVO o1, CommentVO o2) {  
              
                //按照时间进行 ,我们希望时间近的也就是Date较大的在前面。
                if(o1.getDate().getTime()<o2.getDate().getTime()){  
                    return 1;  
                }  
                if(o1.getDate().getTime() == o2.getDate().getTime()){  
                    return 0;  
                }  
                return -1;  
            }  
        });   
		return null;
	}
	
	private CommentVO convertCommentToCommentVO(Comment c ){
		CommentVO commentVO=new CommentVO();
		commentVO.setContent(c.getContent());
		commentVO.setDate(c.getTime());
		int userId=c.getUserId();
		String username=userdao.getNameById(userId);
		commentVO.setUsername(username);
		return commentVO;
	}

}
