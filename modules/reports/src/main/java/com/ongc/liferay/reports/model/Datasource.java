package com.ongc.liferay.reports.model;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 * @author Ranjeet
 * 
 * <code>Datasource</code> class provides the all datasource.
 * <p>
 *  
 *
 */
public class Datasource {

	private static Datasource instance;
	 
	final static Log LOGGER = LogFactoryUtil.getLog(Datasource.class.getName());


	private final static Map<String ,String> datasourceMap=new HashMap<String, String>();

	    private Datasource() {
	    }
	 
	    public static synchronized Datasource getInstance() {
	        if (instance == null) {
	            instance = new Datasource();
	        }
	        return instance;
	    }
		
		
	    /**
	     * This method provides the values which are set in properties file	
	     */ 
		public Map<String ,String> getDatasourceMap(){
			int i=1;
			Map<String, String> datasourceMap=new HashMap<String, String>();
			for(;;) {
				String key=PropsUtil.get("datasource."+i);
				LOGGER.info("key :- " +key);
				
				String value=PropsUtil.get("datasource."+i+".jndi");
				LOGGER.info(value);
				if(Validator.isBlank(key) || Validator.isNull(key)) {
					break;
				}
				datasourceMap.put(key, value);
				i++;
			}
			LOGGER.info(datasourceMap);
			return datasourceMap;
		}
}
