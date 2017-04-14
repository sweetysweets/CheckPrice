package org.edu.nju.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.edu.nju.service.UserService;
import org.edu.nju.serviceImpl.UserServiceImpl;
import org.edu.nju.vo.CommodityVO;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String keyword = request.getParameter("keyword");
		UserService userService = new UserServiceImpl();
		Map<CommodityVO, Integer> resultMap =  userService.search(keyword);
		List<Map.Entry<CommodityVO,Integer>> list = new ArrayList<Map.Entry<CommodityVO,Integer>>(resultMap.entrySet());
		Collections.sort(list,new Comparator<Map.Entry<CommodityVO,Integer>>() {
            //升序排序
            public int compare(Entry<CommodityVO, Integer> o1,
                    Entry<CommodityVO, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
            
        });
		ArrayList<CommodityVO> commodityVOs = new ArrayList<>();
		for(Map.Entry<CommodityVO,Integer> mapping:list){ 
            commodityVOs.add(mapping.getKey());
       }
		JSONArray array = JSONArray.fromObject(commodityVOs);
	    String jsonstr = array.toString();
		response.getWriter().append(jsonstr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
