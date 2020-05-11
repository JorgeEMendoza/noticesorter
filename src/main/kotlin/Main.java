import com.jorge.UI.ConfigImp;
import com.jorge.UI.IConfig;

public class Main {
    public static void main(String args[]){
        IConfig config = new ConfigImp();
        config.init();
    }
}
