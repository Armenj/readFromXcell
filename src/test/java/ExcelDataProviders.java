import org.testng.annotations.DataProvider;

public class ExcelDataProviders {

    @DataProvider
    public Object[][] productsFromSheet() throws Exception {
        String path = "src/main/resources/products.xlsx";
        ExcelReader excelReader = new ExcelReader(path);
        return excelReader.getSheetDataForTdd();
    }
}
