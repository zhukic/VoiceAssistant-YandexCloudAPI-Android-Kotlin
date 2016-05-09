package rus.voiceassistant.model.yandex;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

/**
 * Created by RUS on 04.05.2016.
 */
@Generated("org.jsonschema2pojo")
public class Token {

    private Integer BeginChar;
    private Integer EndChar;
    private String Text;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The BeginChar
     */
    public Integer getBeginChar() {
        return BeginChar;
    }

    /**
     *
     * @param BeginChar
     * The BeginChar
     */
    public void setBeginChar(Integer BeginChar) {
        this.BeginChar = BeginChar;
    }

    /**
     *
     * @return
     * The EndChar
     */
    public Integer getEndChar() {
        return EndChar;
    }

    /**
     *
     * @param EndChar
     * The EndChar
     */
    public void setEndChar(Integer EndChar) {
        this.EndChar = EndChar;
    }

    /**
     *
     * @return
     * The Text
     */
    public String getText() {
        return Text;
    }

    /**
     *
     * @param Text
     * The Text
     */
    public void setText(String Text) {
        this.Text = Text;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Token{" +
                "Text='" + Text + '\'' +
                ", EndChar=" + EndChar +
                ", BeginChar=" + BeginChar +
                '}';
    }
}
