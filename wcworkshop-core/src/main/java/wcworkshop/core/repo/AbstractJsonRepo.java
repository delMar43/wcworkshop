package wcworkshop.core.repo;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AbstractJsonRepo<T> {
  private static final Logger logger = LoggerFactory.getLogger(AbstractJsonRepo.class);
  private static final ObjectMapper mapper = new ObjectMapper();

  protected void writeFile(String owner, String id, Object object) {
    String path = generatePath(owner, id, object.getClass());
    try {
      mapper.writeValue(new File(path), object);
    } catch (JsonGenerationException e) {
      logger.error("Exception while trying to generate json data for {}: {}", path, e.getMessage());
      throw new RuntimeException(e.getMessage(), e);
    } catch (JsonMappingException e) {
      logger.error("Exception while trying to map object to json for {}: {}", path, e.getMessage());
      throw new RuntimeException(e.getMessage(), e);
    } catch (IOException e) {
      logger.error("IOException while trying to write file {}: {}", path, e.getMessage());
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  protected T loadFile(String owner, String id, Class<T> clazz) {
    String path = generatePath(owner, id, clazz);
    try {
      T result = mapper.readValue(new File(path), clazz);
      return result;
    } catch (JsonParseException e) {
      logger.error("Exception while trying to parse json data for {}: {}", path, e.getMessage());
      throw new RuntimeException(e.getMessage(), e);
    } catch (JsonMappingException e) {
      logger.error("Exception while trying to map json to object for {}: {}", path, e.getMessage());
      throw new RuntimeException(e.getMessage(), e);
    } catch (IOException e) {
      logger.error("IOException while trying to read file {}: {}", path, e.getMessage());
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  private String generatePath(String owner, String id, Class<?> clazz) {
    return owner + File.separator + clazz.getSimpleName() + File.separator + id;
  }
}
