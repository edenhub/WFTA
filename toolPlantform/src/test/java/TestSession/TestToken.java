package TestSession;

import com.sysu.toolPlantform.util.TokenValidateUtil;
import org.junit.Test;

/**
 * Created by adam on 2016/1/14.
 */
public class TestToken {

    @Test
    public void generateYAWL(){
        long timestamp = System.currentTimeMillis();
        String name = "formengine";

        String token = TokenValidateUtil.generateToken(name+"_"+timestamp);
        System.out.println(name+"_"+timestamp);
        System.out.println(token);
    }

    @Test
    public void validateYAWL(){
        assert TokenValidateUtil.isWFMSTokenValidate("YAWL","09ad17ac-a7d5-3148-8bdd-88394a9f89eb");
    }
}
