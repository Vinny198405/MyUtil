package Measure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LengthTest {
    private Length length1=new Length(10.f,LengthUnit.M);
    private Length length2=new Length(1000.f,LengthUnit.CM);
    private Length length3=new Length(20.f,LengthUnit.M);

    @Test
    void plusLengthTest() {
        Length res1=new Length(20.f,LengthUnit.M);
        Length res2=new Length(2000.f,LengthUnit.CM);
        assertEquals(res1,length1.plus(length2));
        assertEquals(res2,length2.plus(length1));
    }

    @Test
    void minusLengthTest() {
        Length res1=new Length(0f,LengthUnit.M);
        Length res2=new Length(0f,LengthUnit.CM);
        assertEquals(res1,length1.minus(length2));
        assertEquals(res2,length2.minus(length1));
    }

    @Test
    void toStringTest() {
        assertEquals("10.0M",length1.toString());
        assertEquals("1000.0CM",length2.toString());
    }

    @Test
    void convertTest() {
        assertEquals(length2,length1.convert(LengthUnit.CM));
        assertEquals(length1,length2.convert(LengthUnit.M));
    }

    @Test
    void betweenTest() {
        assertEquals(-10000.0f,
                LengthUnit.MM.between(length3,length2),0.00001f);
        assertEquals(10f,LengthUnit.M.
                between(length2,length3),0.00001f);
        assertEquals(-1000f,LengthUnit.CM.
                between(length3,length2),0.00001f);
    }
}
