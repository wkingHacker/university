import com.util.JwtToken;
import io.jsonwebtoken.Claims;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestJWT {

    @Test
    public void test(){
        JwtToken jwt = new JwtToken();
        String res =  jwt.generateToken("admin");
        System.out.println("JWTTOKEN值　为:"+res);

        Claims claims =  jwt.getClaimByToken(res);
        Date date =  claims.getExpiration();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("过期时间:"+sdf.format(date));

        String userId =  claims.getSubject();
        System.out.println(userId);
        System.out.println(date);
    }
}
