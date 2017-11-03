package android.anative.com.demoadvance.utilities;

/**
 * Created by Neeraj VijayVargiya on 3/11/17.
 */

public class ApplicationUtils {
    public static boolean checkValidStrin(String string){
        if (string!=null && !string.isEmpty()){
            return true;
        }
        return false;
    }
}
