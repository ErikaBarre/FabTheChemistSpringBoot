package net.fab.the.chemist.springbootrestfullws.user;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	//filtre static
		@GetMapping("/filtering")
		public SomeBean someBean() {
			return new SomeBean("value1","value2","value3");
		}
		
		@GetMapping("/filtering-list")
		public List<SomeBean> someBeanL() {
			return Arrays.asList(
					new SomeBean("value1","value2","value3"),
					new SomeBean("value4","value2","value3"),
					new SomeBean("value7","value2","value3")
					);
		}
		
		
	// filtre dynamic	
		@GetMapping("/filteringd")
		public MappingJacksonValue someBeand() {
			SomeBean someBean = new SomeBean("value1","value2","value3");
			MappingJacksonValue mapping = new MappingJacksonValue(someBean) ;
			SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
			FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter", filter);
			mapping.setFilters(filters);
			return mapping;
		}
		
		@GetMapping("/filtering-listd")
		public MappingJacksonValue someBeanLd() {
			List<SomeBean> list = Arrays.asList(
					new SomeBean("value1","value2","value3"),
					new SomeBean("value4","value2","value3"),
					new SomeBean("value7","value2","value3")
					);
			
			
			SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
			FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter", filter);
			MappingJacksonValue mapping = new MappingJacksonValue(list) ;
			mapping.setFilters(filters);
			
			return mapping;
			
			
			
		}
}
