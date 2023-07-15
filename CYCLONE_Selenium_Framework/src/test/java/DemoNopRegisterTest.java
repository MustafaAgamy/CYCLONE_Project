import com.ej.drivers.CYCLONE;
import org.testng.annotations.Test;

public final class DemoNopRegisterTest extends BaseTest{

    CYCLONE.DataDriven.JsonClass jsonData;
    String JSON_FILE = "registerData.json";

    @Test
    public void registerTest() {
            jsonData = new CYCLONE.DataDriven.JsonClass(JSON_FILE);

                demoNopHomePage
                        .clickRegisterBtn()
                        .fillRegisterInfoMale(
                                jsonData.readJson("FirstName"),
                                jsonData.readJson("LastName"),
                                jsonData.readJson("BirthDay"),
                                jsonData.readJson("BirthMonth"),
                                jsonData.readJson("BirthYear"),
                                jsonData.readJson("Email"),
                                jsonData.readJson("Password"),
                                jsonData.readJson("Password"))
                        .clickOnConfRegBtn()
                        .getSuccessMsg();

    }
}
