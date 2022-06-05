import kotlin.test.Test
import kotlin.test.assertEquals

class CalculatorTest {

    @Test
    fun trim() {
        assertEquals(expected = "-2*(-3+0.4)/-0.2", actual = trim("-2 * (-3 + 0.4) / -0.2"))
    }

    @Test
    fun repMtoPM() {
        assertEquals(expected = "+-2*(+-3+0.4)/+-0.2", actual = repMtoPM("-2*(-3+0.4)/-0.2"))
    }

    @Test
    fun foldBracket() {
        assertEquals(expected = "+-2*+-2.6/+-0.2", actual = foldBracket("+-2*(+-3+0.4)/+-0.2"))
    }

    @Test
    fun foldMD() {
        assertEquals(expected = "+-26.0", actual = foldMD("+-2*+-2.6/+-0.2"))
    }

    @Test
    fun sum() {
        assertEquals(expected = -26.0, actual = sum("+-26.0"))
    }

    @Test
    fun numberSentence() {
        assertEquals(expected = -26.0, actual = numberSentence("+-2*+-2.6/+-0.2"))
    }

    @Test
    fun cal() {
        assertEquals(expected = 4.0, actual = cal("-2 * -3 + 0.4 / -0.2"))
        assertEquals(expected = -2.5, actual = cal("(-2 -3) / 2.0"))
        assertEquals(expected = 27.5, actual = cal("-2 * -3 / 2.0 + 12 * 2 + 1 / 2"))
        assertEquals(expected = 2.5, actual = cal("-2 * -3 / (-2.0 + 2 * (5 - 1)) * 2 + 1 / 2"))
        assertEquals(expected = -3.5, actual = cal("-2 * -3 / -6 * 2 * 1 * 2 + 1 / 2"))
    }

    //과제
    @Test
    fun assignment() {
        assertEquals(expected = -4.6, actual = cal("-2-3 + 0.4"))
        assertEquals(expected = -26.0, actual = cal("-2 *(-3 + 0.4)/ -0.2"))
    }
}