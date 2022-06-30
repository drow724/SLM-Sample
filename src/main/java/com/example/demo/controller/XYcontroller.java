package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Request;
import com.example.demo.model.GeneralAgency;
import com.example.demo.repository.GeneralAgencyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class XYcontroller {
	
	private final GeneralAgencyRepository generalAgencyRepository;
	
	@GetMapping
	@RequestMapping("/list")
	public List<GeneralAgency> getList() throws Exception {
		HashMap<String, Object> map = getDmsByLatLon("37.490515327316814");
		for(String key : map.keySet()) {
			System.out.println(map.get(key));
		}
		return generalAgencyRepository.findAll();
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/default")
	public List<GeneralAgency> getNearBy(@RequestBody Request request) {
		return generalAgencyRepository.getGaList1(request.getNe(), request.getSw());
	}
	
	@GetMapping
	@RequestMapping("/new")
	public String newString() {
		return "new";
	}
	
	//소수점 표현 위도,경도를 DMS 표현으로 변환
	public HashMap<String ,Object> getDmsByLatLon(Object data) throws Exception{
	//data = 37.397

	    HashMap<String,Object> result = new HashMap<>();
	    
	    try {
	    
	      String[] dataArray = data.toString().split("\\.");
	      //도 : 소수점 좌표 값의 정수
	      String dataDegree = dataArray[0];

	      String dataMinutesFull = String.valueOf(Double.parseDouble("0." + dataArray[1]) * 60);
	      //분
	      String dataMinutes = dataMinutesFull.split("\\.")[0];
	      
	      //초
	      String dataSeconds;
	      if(String.valueOf(Double.parseDouble("0." + dataMinutesFull.split("\\.")[1]) * 60).length()<5){
	        dataSeconds = String.valueOf(Double.parseDouble("0." + dataMinutesFull.split("\\.")[1]) * 60);
	      }else {
	        dataSeconds = String.valueOf(Double.parseDouble("0." + dataMinutesFull.split("\\.")[1]) * 60).substring(0, 5);
	      }
	      
	      result.put("degree", dataDegree);
	      result.put("minutes", dataMinutes);
	      result.put("seconds", dataSeconds);

	    }catch(Exception e){
	      e.printStackTrace();
	    }
	    
	    return result;
	}
}