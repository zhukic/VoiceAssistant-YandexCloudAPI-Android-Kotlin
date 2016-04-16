package rus.voiceassistant.utils

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by RUS on 16.04.2016.
 */
class DateUtilsTest {

    @Test
    fun testIsDateComesToday() {
        assertEquals(true, DateUtils.isDateComesToday(23, 59))
        assertEquals(false, DateUtils.isDateComesToday(0, 0))
        assertEquals(false, DateUtils.isDateComesToday(5, 40))
        assertEquals(false, DateUtils.isDateComesToday(3, 40))
        assertEquals(false, DateUtils.isDateComesToday(17, 40))
        assertEquals(false, DateUtils.isDateComesToday(16, 45))
        assertEquals(false, DateUtils.isDateComesToday(16, 41))
        assertEquals(false, DateUtils.isDateComesToday(15, 40))
    }

}