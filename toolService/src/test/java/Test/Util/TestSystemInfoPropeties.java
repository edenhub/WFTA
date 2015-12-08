package Test.Util;

import com.sysu.toolService.util.SystemInfoProperties;
import org.junit.Test;

/**
 * Created by adam on 2015/12/8.
 */
public class TestSystemInfoPropeties {

    @Test
    public void testConf(){
        SystemInfoProperties sysInfo = SystemInfoProperties.getInstance();
        System.out.println(sysInfo.getPlantFormHost());
        System.out.println(sysInfo.getPlantFormRSPath());
    }
}
