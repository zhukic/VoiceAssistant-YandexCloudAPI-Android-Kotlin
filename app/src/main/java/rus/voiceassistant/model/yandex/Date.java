package rus.voiceassistant.model.yandex;

/**
 * Created by RUS on 04.05.2016.
 */
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Date {

    private Integer Hour;
    private Integer Min;
    private Integer Day;
    private Integer Month;
    private Integer Year;
    private Boolean RelativeDay;
    private Tokens Tokens;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
     * The Month
     */
    public Integer getMonth() {
        return Month;
    }

    /**
     *
     * @param Month
     * The Month
     */
    public void setMonth(Integer Month) {
        this.Month = Month;
    }

    /**
     *
     * @return
     * The Year
     */
    public Integer getYear() {
        return Year;
    }

    /**
     *
     * @param Year
     * The Year
     */
    public void setYear(Integer Year) {
        this.Year = Year;
    }

    /**
     *
     * @return
     * The RelativeDay
     */
    public Boolean getRelativeDay() {
        return RelativeDay;
    }

    /**
     *
     * @param RelativeDay
     * The RelativeDay
     */
    public void setRelativeDay(Boolean RelativeDay) {
        this.RelativeDay = RelativeDay;
    }

    /**
     *
     * @return
     * The Tokens
     */
    public Tokens getTokens() {
        return Tokens;
    }

    /**
     *
     * @param Tokens
     * The Tokens
     */
    public void setTokens(Tokens Tokens) {
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
        return "Date{" +
                "Tokens=" + Tokens +
                ", RelativeDay=" + RelativeDay +
                ", Year=" + Year +
                ", Month=" + Month +
                ", Day=" + Day +
                ", Min=" + Min +
                ", Hour=" + Hour +
                '}';
    }
}
