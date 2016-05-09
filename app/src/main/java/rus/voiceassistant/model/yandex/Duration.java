package rus.voiceassistant.model.yandex;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

/**
 * Created by RUS on 06.05.2016.
 */
@Generated("org.jsonschema2pojo")
public class Duration {

    private Integer Year;
    private Integer Month;
    private Integer Day;
    private Integer Hour;
    private Integer Min;
    private String Type;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The Month
     */
    public Integer getYear() {
        return Year;
    }
    /**
     *
     * @return
     * The Month
     */
    public Integer getMonth() {
        return Month;
    }

    /**
     *
     * @return
     * The Day
     */
    public Integer getDay() {
        return Day;
    }

    /**
     *
     * @param Day
     * The Day
     */
    public void setDay(Integer Day) {
        this.Day = Day;
    }

    /**
     *
     * @return
     * The Hour
     */
    public Integer getHour() {
        return Hour;
    }

    /**
     *
     * @param Hour
     * The Hour
     */
    public void setHour(Integer Hour) {
        this.Hour = Hour;
    }

    /**
     *
     * @return
     * The Min
     */
    public Integer getMin() {
        return Min;
    }

    /**
     *
     * @param Min
     * The Min
     */
    public void setMin(Integer Min) {
        this.Min = Min;
    }

    /**
     *
     * @return
     * The Type
     */
    public String getType() {
        return Type;
    }

    /**
     *
     * @param Type
     * The Type
     */
    public void setType(String Type) {
        this.Type = Type;
    }

    @Override
    public String toString() {
        return "Duration{" +
                "Type='" + Type + '\'' +
                ", Min=" + Min +
                ", Hour=" + Hour +
                ", Day=" + Day +
                ", Month=" + Month +
                ", Year=" + Year +
                '}';
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
