package RQAFramwork.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		// Read json to String

		String jsonCotent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// Convert String HashMap Jackson Databind
		// Add Dependency to the project (Jackson Databind)

		ObjectMapper mapper = new ObjectMapper();

		// Convert JSON string to a List of HashMaps
		List<HashMap<String, String>> dataList = mapper.readValue(jsonCotent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return dataList;

	}

}
