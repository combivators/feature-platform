package net.tiny.feature.assess;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;

public class MatrixTest {

    @Test
    public void testSwap() {
        Integer a [][] = new Integer [3][3];
        a[0][0] = 1;
        a[0][1] = 2;
        a[0][2] = 3;
        a[1][0] = 4;
        a[1][1] = 5;
        a[1][2] = 6;
        a[2][0] = 7;
        a[2][1] = 8;
        a[2][2] = 9;
        Matrix<Integer> matrix = Matrix.of(a);
        matrix.print(System.out);
        assertEquals(1, matrix.get(0L,0L));
        assertEquals(9, matrix.get(2L,2L));
        System.out.println();
        matrix.swapColumns(0,2);
        matrix.print(System.out);
        assertEquals(3, matrix.get(0L,0L));
        assertEquals(7, matrix.get(2L,2L));

        System.out.println();
        matrix.swapRows(0,2);
        matrix.print(System.out);
        assertEquals(9, matrix.get(0L,0L));
        assertEquals(1, matrix.get(2L,2L));
    }

    @Test
    public void testDoubleMatrix() throws Exception {
        Double[][] array = new Double[10][2];
        array[0][0] = 0.07d; array[0][1] = 0.31d;
        array[1][0] = 0.01d; array[1][1] = 0.17d;
        array[2][0] = 0.69d; array[2][1] = 0.03d;
        array[3][0] = 0.62d; array[3][1] = 0.03d;
        array[4][0] = 0.50d; array[4][1] = 0.38d;
        array[5][0] = 0.77d; array[5][1] = 0.44d;
        array[6][0] = 0.28d; array[6][1] = 0.42d;
        array[7][0] = 0.69d; array[7][1] = 0.15d;
        array[8][0] = 0.38d; array[8][1] = 0.65d;
        array[9][0] = 0.41d; array[9][1] = 0.09d;

        Matrix<Double> matrix = Matrix.of(array);
        assertEquals(0.42d, matrix.get(6L,1L));
        assertEquals(0.77d, matrix.get(5L,0L));

        Double[] addtion = new Double[2];
        addtion[0] = 0.12d; addtion[1] = 0.34d;
        matrix.set(5, addtion);
        assertEquals(0.12d, matrix.get(5L,0L));
        assertEquals(0.34d, matrix.get(5L,1L));
    }


    @Test
    public void testRotate() {
        Float a [][] = new Float [4][4];
        a[0][0] = 0.0f;
        a[0][1] = 0.1f;
        a[0][2] = 0.2f;
        a[0][3] = 0.3f;
        a[1][0] = 1.0f;
        a[1][1] = 1.1f;
        a[1][2] = 1.2f;
        a[1][3] = 1.3f;
        a[2][0] = 2.0f;
        a[2][1] = 2.1f;
        a[2][2] = 2.2f;
        a[2][3] = 2.3f;
        a[3][0] = 3.0f;
        a[3][1] = 3.1f;
        a[3][2] = 3.2f;
        a[3][3] = 3.3f;

        Matrix<Float> matrix = Matrix.of(a);
        assertEquals(0.0f, matrix.get(0L,0L));
        assertEquals(0.3f, matrix.get(0L,3L));
        assertEquals(3.3f, matrix.get(3L,3L));
        assertEquals(3.0f, matrix.get(3L,0L));
        matrix.print(System.out);
        System.out.println();

        matrix.rotate();
        matrix.print(System.out);
        assertEquals(3.0f, matrix.get(0L,0L));
        assertEquals(0.0f, matrix.get(0L,3L));
        assertEquals(0.3f, matrix.get(3L,3L));
        assertEquals(3.3f, matrix.get(3L,0L));
    }

    @Test
    public void testSubMatrix() {
        Float a [][] = new Float [4][4];
        a[0][0] = 0.0f;
        a[0][1] = 0.1f;
        a[0][2] = 0.2f;
        a[0][3] = 0.3f;
        a[1][0] = 1.0f;
        a[1][1] = 1.1f;
        a[1][2] = 1.2f;
        a[1][3] = 1.3f;
        a[2][0] = 2.0f;
        a[2][1] = 2.1f;
        a[2][2] = 2.2f;
        a[2][3] = 2.3f;
        a[3][0] = 3.0f;
        a[3][1] = 3.1f;
        a[3][2] = 3.2f;
        a[3][3] = 3.3f;

        Matrix<Float> matrix = Matrix.of(a);
        matrix.print(System.out);
        System.out.println();
        Matrix<Float> sub = matrix.sub(1, 1);
        sub.print(System.out);
        assertEquals(1.1f, sub.get(0L,0L));
        assertEquals(3.3f, sub.get(2L,2L));

        System.out.println();
        sub = matrix.sub(0, 1, 4, 3);
        sub.print(System.out);
        assertEquals(0.1f, sub.get(0L,0L));
        assertEquals(3.2f, sub.get(3L,1L));
    }

    @Test
    public void testExtendedMatrix() {
        Integer a [][] = new Integer [3][3];
        a[0][0] = 1;
        a[0][1] = 2;
        a[0][2] = 3;
        a[1][0] = 4;
        a[1][1] = 5;
        a[1][2] = 6;
        a[2][0] = 7;
        a[2][1] = 8;
        a[2][2] = 9;
        Matrix<Integer> ma = Matrix.of(a);
        ma.print(System.out);

        System.out.println();
        Integer b [][] = new Integer [3][2];
        b[0][0] = 4;
        b[0][1] = 5;
        b[1][0] = 7;
        b[1][1] = 8;
        b[2][0] = 0;
        b[2][1] = 0;
        Matrix<Integer> mb = Matrix.of(b);
        Matrix<Integer> me = ma.extend(mb);
        me.print(System.out);
        assertEquals(1, me.get(0L,0L));
        assertEquals(5, me.get(0L,4L));
        assertEquals(9, me.get(2L,2L));
        assertEquals(0, me.get(2L,3L));
        assertEquals(0, me.get(2L,4L));
    }


    @Test
    public void testTranspose() {
        Integer a [][] = new Integer [3][2];
        a[0][0] = 0;
        a[0][1] = 1;
        a[1][0] = 2;
        a[1][1] = 3;
        a[2][0] = 4;
        a[2][1] = 5;
        Matrix<Integer> matrix = Matrix.of(a);
        System.out.println(matrix.toString());
        matrix.print(System.out);
        System.out.println();
        assertEquals(0, matrix.get(0L,0L));
        assertEquals(2, matrix.get(1L,0L));
        Integer[] values = matrix.getRow(2L);
        assertEquals(matrix.getWidth(), (long)values.length);
        assertEquals(4, values[0].intValue());
        assertEquals(5, values[1].intValue());


        values = matrix.getColumn(1L);
        assertEquals(matrix.getHeight(), (long)values.length);
        assertEquals(1, values[0].intValue());
        assertEquals(3, values[1].intValue());


        Matrix<Integer> transposed = matrix.transpose();
        System.out.println("transposed " + transposed.toString());
        transposed.print(System.out);
        assertEquals(2L, matrix.getWidth());
        assertEquals(3L, matrix.getHeight());
        assertEquals(matrix.getHeight(), transposed.getWidth());
        assertEquals(matrix.getWidth(), transposed.getHeight());
        for (long i = 0; i < matrix.getHeight(); i++)
            for (long j = 0; j < matrix.getWidth(); j++)
                assertEquals(matrix.get(i, j), transposed.get(j, i));
    }


    @Test
    public void testSum() {
        Integer a [][] = new Integer [3][3];
        a[0][0] = 1;
        a[0][1] = 2;
        a[0][2] = 3;
        a[1][0] = 4;
        a[1][1] = 5;
        a[1][2] = 6;
        a[2][0] = 7;
        a[2][1] = 8;
        a[2][2] = 9;
        Matrix<Integer> matrix = Matrix.of(a);
        matrix.print(System.out);
        assertEquals(12.0d, matrix.sumColumn(0));
        assertEquals(15.0d, matrix.sumColumn(1));
        assertEquals(18.0d, matrix.sumColumn(2));

        assertEquals(6.0d, matrix.sumRow(0));
        assertEquals(15.0d, matrix.sumRow(1));
        assertEquals(24.0d, matrix.sumRow(2));
    }

    @Test
    public void testMultiply() {
        Integer a [][] = new Integer [3][3];
        a[0][0] = 1;
        a[0][1] = 2;
        a[0][2] = 3;
        a[1][0] = 4;
        a[1][1] = 5;
        a[1][2] = 6;
        a[2][0] = 7;
        a[2][1] = 8;
        a[2][2] = 9;
        Matrix<Integer> matrix = Matrix.of(a);
        assertEquals(1, matrix.get(0L,0L));
        assertEquals(9, matrix.get(2L,2L));
        matrix.print(System.out);

        Matrix<Integer> result = matrix.multiply(new Integer[] {2,2,2});
        assertEquals(2, result.get(0L,0L));
        assertEquals(18, result.get(2L,2L));
        System.out.println("Multiplied");
        result.print(System.out);

    }


    @Test
    public void testCompute() {
        Integer a [][] = new Integer [3][3];
        a[0][0] = 1;
        a[0][1] = 2;
        a[0][2] = 3;
        a[1][0] = 4;
        a[1][1] = 5;
        a[1][2] = 6;
        a[2][0] = 7;
        a[2][1] = 8;
        a[2][2] = 9;
        Matrix<Integer> matrix = Matrix.of(a);
        matrix.print(System.out);

        BiFunction<Integer[], Integer[], Integer> fun = new BiFunction<Integer[], Integer[], Integer>() {
            @Override
            public Integer apply(Integer[] t, Integer[] u) {
                Integer ret = 0;
                for (int i=0; i<t.length; i++) {
                    ret += t[i] * u[i];
                }
                return ret;
            }
        };
        Integer r = matrix.computeRow(fun, 1L, new Integer[] {2,2,2});
        assertEquals(30, r);

        r = matrix.computeColumn(fun, 2L, new Integer[] {2,2,2});
        assertEquals(36, r);

        Integer[] array = matrix.computeRows(fun, new Integer[] {2,2,2});
        assertEquals(3, array.length);
        assertEquals(12, array[0]);
        assertArrayEquals(new Integer[] {12,30,48}, array);

        array = matrix.computeColumns(fun, new Integer[] {2,2,2});
        assertEquals(3, array.length);
        assertEquals(24, array[0]);
        assertArrayEquals(new Integer[] {24,30,36}, array);

    }

}
