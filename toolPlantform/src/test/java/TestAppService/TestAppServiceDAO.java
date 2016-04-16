package TestAppService;

import BaseClazz.TestBaseClazz;
import com.sysu.toolPlantform.appService.dao.AppServiceDAO;
import com.sysu.toolPlantform.appService.domain.AppServiceInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by adam on 2016/4/14.
 */
public class TestAppServiceDAO extends TestBaseClazz {

    @Autowired
    private AppServiceDAO appServiceDAO;

    public AppServiceDAO getAppServiceDAO() {
        return appServiceDAO;
    }

    public void setAppServiceDAO(AppServiceDAO appServiceDAO) {
        this.appServiceDAO = appServiceDAO;
    }

    @Test
    public void testInsert(){
        AppServiceInfo appServiceInfo = new AppServiceInfo();
        appServiceInfo.setAppName("appName1");
        appServiceInfo.setServiceUrl("url1");
        appServiceInfo.setAppToken("token1");

        appServiceDAO.createAppService(appServiceInfo);
    }

    @Test
    public void testSelect(){
        AppServiceInfo appServiceInfo = appServiceDAO.selectAppServiceByName("appName1");
        System.out.println(appServiceInfo);
    }

    @Test
    public void testUpdate(){
        AppServiceInfo appServiceInfo = new AppServiceInfo();
        appServiceInfo.setServiceUrl("aaaaa");
        appServiceDAO.updateAppServiceByName("appName1",appServiceInfo);
    }

    @Test
    public void testDelete(){
        appServiceDAO.deleteAppServiceByName("appName1");
    }
}
