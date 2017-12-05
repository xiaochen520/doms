package test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.SimpleFormatter;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.echo.dao.SubcoolDao;
import com.echo.dto.PcCdStationT;
import com.echo.dto.PcCdSubcoolCalParamsInfoT;
import com.echo.dto.PcCdSubcoolDefaultParam;
import com.echo.dto.PcRdSubcoolCalResultsT;
import com.echo.service.AreaInfoService;
import com.echo.service.OilDrillingService;
import com.echo.service.StationTService;
import com.echo.service.SubcoolService;
import com.echo.service.UserService;

public class StationTActionTest {

	@Test
	public void testAddSta() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//		UserService userService = (UserService)ctx.getBean("userService");
//		StationTService stationTService = (StationTService)ctx.getBean("stationTService");
//		AreaInfoService areaInfoService = (AreaInfoService)ctx.getBean("areaInfoService");
//		OilDrillingService oilDriService = (OilDrillingService)ctx.getBean("oilDriService");
		
		
//		PcCdStationT stationT = new PcCdStationT();
	
		
		//Assert.assertTrue(addflg == true );
		
		
		
	}
	
	@Test
	public void testSubcool() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//		SubcoolService service = (SubcoolService) ctx.getBean("subcoolService");
//		
//		PcCdSubcoolDefaultParam subcool = new PcCdSubcoolDefaultParam();
//		subcool.setSystemName("subcool");
//		subcool.setCalculateMethods("I井套压");
//		subcool.setCalculateMethodsId(1);
//		subcool.setCalculateRate(20);
//		subcool.setMaxSubcool(new BigDecimal(15));
//		subcool.setMinSubcool(new BigDecimal(5));
//		subcool.setFlowMethods(1);
//
//		service.setSubcoolDefaultParam(subcool); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		SubcoolDao subcoolDao = (SubcoolDao) ctx.getBean("subcoolDao");
		
		Random random = new Random();
		//for (int i = 0; i < 30; i++) {
			PcRdSubcoolCalResultsT calResults = new PcRdSubcoolCalResultsT();
			calResults.setSagdId("402894874dd778ab014e1ed0f95e0ff0");
			cal.add(Calendar.MINUTE, 1);
			calResults.setCalculateTime(cal.getTime());
			calResults.setCalculateMethod(1);
			calResults.setCurrentSubcool(new BigDecimal(random.nextInt(20)));
			calResults.setOffsetSubcool(new BigDecimal(1));
			calResults.setMaxSubcool(new BigDecimal(15));
			calResults.setMinSubcool(new BigDecimal(5));
			calResults.setCurrentFlow(new BigDecimal(10));
			calResults.setCurrentJigFrequency(new BigDecimal(8));
			calResults.setCurrentFlowNipple(new BigDecimal(9));
			calResults.setSuggestFlow(new BigDecimal(10));
			calResults.setSuggestJigFrequency(new BigDecimal(8));
			calResults.setSuggestFlowNipple(new BigDecimal(9));
			calResults.setSagdrdId("976CDBD989F345A9B7778B7BE1CFFFBB");
			calResults.setOperatePress(new BigDecimal(random.nextInt(20)));
			subcoolDao.addSubcoolCalResults(calResults);
		//}
		
//		PcCdSubcoolCalParamsInfoT calParams = subcoolDao.findCalParsmsBySagd("aaaaaaaaaaaaaaaaaaddddddddddddddddcc");
//		PcCdSubcoolCalParamsInfoT calParams = subcoolDao.getCalParamsById("4028810f4f6dd037014f6df5f49a006e");
//		subcoolDao.removeSubcoolCalParams(calParams);
		//PcCdSubcoolCalParamsInfoT calParams = subcoolDao.getCalParamsById("4028810f4f6caaf9014f6cab1e140001");
//		System.out.println(calParams);
		//calParams.setCalparamId("4028810f4f6caaf9014f6cab1e140001");
		//calParams.setSagdId("aaaaaaaaaaaaaaaaaaddddddddddddddddcc");
//		calParams.setCalculateMethodsid(1);
//		calParams.setCalculateMethods("I井套压 ");
//		calParams.setCalculateRate(21);
//		calParams.setMinSubcool(new BigDecimal(5));
//		calParams.setMaxSubcool(new BigDecimal(21));
//		calParams.setFlowMethods(3);
//		System.out.println(calParams);
//		subcoolDao.updataSubcoolCalParams(calParams);
	}
}
