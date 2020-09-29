package tech.uom.demo.web.spark;

import com.google.gson.Gson;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import spark.Spark;
import spark.utils.IOUtils;
import tech.units.indriya.format.SimpleUnitFormat;
import tech.units.indriya.unit.Units;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Disabled("Broken under Java 11 in CI")
public class MeasurementControllerIntegrationTest {
    private static final String ENDPOINT = "sensors";

    @BeforeAll
    public static void beforeClass() {
	SimpleUnitFormat.getInstance().label(Units.CELSIUS, "Celsius");
	Main.main(null);
    }

    @AfterAll
    public static void afterClass() {
	Spark.stop();
    }

    @Test
    public void aNewMeasureShouldBeCreated() {
		TestResponse res = request("POST", String.format("/%s?name=temp1&value=10&unit=Cel", ENDPOINT));
		Map<String, String> json = res.json();
		assertEquals(200, res.status);
		assertEquals("temp1", json.get("name"));
		assertEquals(Double.valueOf(10d), json.get("value"));
		assertNotNull(json.get("id"));
    }

    private TestResponse request(String method, String path) {
	try {
	    // FIXME this should work in a CI environment if possible
	    URL url = new URL("http://localhost:4567" + path);
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    connection.setRequestMethod(method);
	    connection.setDoOutput(true);
	    connection.connect();
	    String body = IOUtils.toString(connection.getInputStream());
	    return new TestResponse(connection.getResponseCode(), body);
	} catch (IOException e) {
	    e.printStackTrace();
	    fail("Sending request failed: " + e.getMessage());
	    return null;
	}
    }

    private static class TestResponse {
	public final String body;
	public final int status;

	public TestResponse(int status, String body) {
	    this.status = status;
	    this.body = body;
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> json() {
	    return new Gson().fromJson(body, HashMap.class);
	}
    }
}
