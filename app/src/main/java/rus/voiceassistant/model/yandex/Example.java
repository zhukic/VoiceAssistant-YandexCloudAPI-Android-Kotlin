package rus.voiceassistant.model.yandex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

/**
 * Created by RUS on 04.05.2016.
 */

@Generated("org.jsonschema2pojo")
public class Example {

    private List<Date> Date = new ArrayList<Date>();
    private String OriginalRequest;
    private String ProcessedRequest;
    private List<Token> Tokens = new ArrayList<Token>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The Date
     */
    public List<Date> getDate() {
        return Date;
    }

    /**
     *
     * @param Date
     * The Date
     */
    public void setDate(List<Date> Date) {
        this.Date = Date;
    }

    /**
     *
     * @return
     * The OriginalRequest
     */
    public String getOriginalRequest() {
        return OriginalRequest;
    }

    /**
     *
     * @param OriginalRequest
     * The OriginalRequest
     */
    public void setOriginalRequest(String OriginalRequest) {
        this.OriginalRequest = OriginalRequest;
    }

    /**
     *
     * @return
     * The ProcessedRequest
     */
    public String getProcessedRequest() {
        return ProcessedRequest;
    }

    /**
     *
     * @param ProcessedRequest
     * The ProcessedRequest
     */
    public void setProcessedRequest(String ProcessedRequest) {
        this.ProcessedRequest = ProcessedRequest;
    }

    /**
     *
     * @return
     * The Tokens
     */
    public List<Token> getTokens() {
        return Tokens;
    }

    /**
     *
     * @param Tokens
     * The Tokens
     */
    public void setTokens(List<Token> Tokens) {
        this.Tokens = Tokens;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Example{" +
                "Tokens=" + Tokens +
                ", ProcessedRequest='" + ProcessedRequest + '\'' +
                ", OriginalRequest='" + OriginalRequest + '\'' +
                ", Date=" + Date +
                '}';
    }
}
