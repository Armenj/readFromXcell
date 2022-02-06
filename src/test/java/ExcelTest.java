import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelTest {
    @Test(dataProvider = "productsFromSheet", dataProviderClass = ExcelDataProviders.class)
    public void test(String... params){
        System.out.println(params[0] + params[1] + params[2] + params[3] + params[4] + params[5] );
    }


}
