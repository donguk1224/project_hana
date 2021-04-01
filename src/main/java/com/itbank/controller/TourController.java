package com.itbank.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.itbank.entrepreneur.EntrepreneurDTO;
import com.itbank.local.LocalDTO;
import com.itbank.metro.MetroDTO;
import com.itbank.package_detail.Package_DetailDTO;
import com.itbank.service.TourService;
import com.itbank.tour_item.Tour_ItemDTO;
import com.itbank.tour_price.Tour_PriceDTO;

@Controller
public class TourController {
	
	@Autowired
	private TourService ts;
	
//	@RequestMapping(value="/tour/tourView", method = RequestMethod.GET)
//	public String tourView() {
//		return "/tour/tourView";
//	}
	
	@GetMapping("/tour/tourView")
	public ModelAndView tourView() {
		ModelAndView mav = new ModelAndView("tour/tourView");
		
		List<MetroDTO> metroList = ts.getMetroList();
		List<LocalDTO> localList = ts.getLocalList();
		//List<Tour_ItemDTO> tour_itemList = ts.getTour_ItemList();
		List<Tour_PriceDTO> tour_priceList = ts.getTour_PriceList();
		List<Package_DetailDTO> package_detailList = ts.getPackage_DetailList();
		List<EntrepreneurDTO> entrepreneurAll = ts.getEntrepreneurAll(); 
		
		mav.addObject("entrepreneurAll", entrepreneurAll);
		mav.addObject("metroListSize", metroList.size());
		mav.addObject("metroList", metroList);
		mav.addObject("localListSize", localList.size());
		mav.addObject("localList", localList);
		//mav.addObject("tour_itemListSize", tour_itemList.size());		
		//mav.addObject("tour_itemList", tour_itemList);
		mav.addObject("tour_priceListSize", tour_priceList.size());
		mav.addObject("tour_priceList", tour_priceList);
		mav.addObject("package_detailListSize", package_detailList.size());
		mav.addObject("package_detailList", package_detailList);	
		return mav;
	}
	
	@GetMapping("/tour/tour_gangwon/{page}")
	public ModelAndView tour_gangwon(@PathVariable int page, String type, String word) {
		ModelAndView mav = new ModelAndView("/tour/tour_gangwonList");
		HashMap<String, Object> map = ts.getTour_ItemList(page, type, word);
		mav.addObject("map", map);
		return mav;
	}
	
	@GetMapping("/tour/tour_jeju/{page}")
	public ModelAndView tour_jeju(@PathVariable int page, String type, String word) {
		ModelAndView mav = new ModelAndView("/tour/tour_jejuList");
		HashMap<String, Object> map = ts.getTour_ItemList(page, type, word);
		mav.addObject("map", map);
		return mav;
	}
	
	@GetMapping("/tour/tour_seoul/{page}")
	public ModelAndView tour_seoul(@PathVariable int page, String type, String word) {
		ModelAndView mav = new ModelAndView("/tour/tour_seoulList");
		HashMap<String, Object> map = ts.getTour_ItemList(page, type, word);
		mav.addObject("map", map);
		return mav;
	}
	
	@GetMapping("/tour/tour_gyeonggi/{page}")
	public ModelAndView tour_gyeonggi(@PathVariable int page, String type, String word) {
		ModelAndView mav = new ModelAndView("/tour/tour_gyeonggiList");
		HashMap<String, Object> map = ts.getTour_ItemList(page, type, word);
		mav.addObject("map", map);
		return mav;
	}
	
	@GetMapping("/tour/tour_gyeongsang/{page}")
	public ModelAndView tour_gyeongsang(@PathVariable int page, String type, String word) {
		ModelAndView mav = new ModelAndView("/tour/tour_gyeongsangList");
		HashMap<String, Object> map = ts.getTour_ItemList(page, type, word);
		mav.addObject("map", map);
		return mav;
	}
	
	@GetMapping("/tour/tour_jeolla/{page}")
	public ModelAndView tour_jeolla(@PathVariable int page, String type, String word) {
		ModelAndView mav = new ModelAndView("/tour/tour_jeollaList");
		HashMap<String, Object> map = ts.getTour_ItemList(page, type, word);
		mav.addObject("map", map);
		return mav;
	}
	
	@GetMapping("/tour/tour_chungcheong/{page}")
	public ModelAndView tour_chungcheong(@PathVariable int page, String type, String word) {
		ModelAndView mav = new ModelAndView("/tour/tour_chungcheongList");
		HashMap<String, Object> map = ts.getTour_ItemList(page, type, word);
		mav.addObject("map", map);
		return mav;
	}
	
	@GetMapping("/tour/tourGoods/{idx}")
	public ModelAndView tourGoods(@PathVariable int idx) {
		ModelAndView mav = new ModelAndView("/tour/tourGoods");
		Tour_ItemDTO tour_item = ts.getTour_Item(idx);
		mav.addObject("tour_item", tour_item);
		return mav;
	}
	
	@GetMapping("tour/tourInput")
	public String tourInput(HttpSession session) {
		return session.getAttribute("login") == null ? "redirect:/login/login_form" : "/tour/tourInput";
	}
	
	@PostMapping("tour/tourInput")
	public String tourInput(Tour_ItemDTO user) {
		int idx = ts.writeTour_Item(user);
		return idx != 0 ? "redirect:/tour/tourGoods/" + idx : "";
	}
	
//	@PostMapping(value="tour/tourInput", produces="application/text;charset=utf8")
//	public String tourInput(MultipartFile head_img, MultipartFile con_img) throws IllegalStateException, IOException {
//		
//		System.out.println(head_img.getOriginalFilename());
//		System.out.println(con_img.getOriginalFilename());
//		
//		int row = ts.uploadFile(head_img, con_img);
//		return "/tourInput/" + con_img.getOriginalFilename();	
//	}
//	
//	@GetMapping(value="imgList", produces="application/text;charset=utf8")
//	public String imgList() throws JsonProcessingException {
//		String[] list = ts.getFileList();
//		ObjectMapper mapper = new ObjectMapper();
//		String data = mapper.writeValueAsString(list);
//		return data;
//	}
}