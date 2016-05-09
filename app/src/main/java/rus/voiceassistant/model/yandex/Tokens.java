package rus.voiceassistant.model.yandex;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

/**
 * Created by RUS on 04.05.2016.
 */
@Generated("org.jsonschema2pojo")
public class Tokens {

    private Integer Begin;
    private Integer End;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The Begin
     */
    public Integer getBegin() {
        return Begin;
    }

    /**
     *
     * @param Begin
     * The Begin
     */
    public void setBegin(Integer Begin) {
        this.Begin = Begin;
    }

    /**
     *
     * @return
     * The End
     */
    public Integer getEnd() {
        return End;
    }

    /**
     *
     * @param End
     * The End
     */
    public void setEnd(Integer End) {
        this.End = End;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Tokens{" +
                "End=" + End +
                ", Begin=" + Begin +
                '}';
    }
}
