import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws Exception {
        QuadraticProbeMap<Integer, String> QM = new QuadraticProbeMap<>(17);
        System.out.println("test");
        QM.put(4, "A"); // index 12 i^2=0   h=12
        QM.put(5, "B"); // index 13 i^2=1   h=12
        QM.put(6, "C"); // index 16 i^2=4   h=12
        QM.put(7, "D"); // index 4  i^2=9   h=12
        QM.put(8, "E"); // index 11 i^2=16  h=12
        QM.put(9, "F"); // index 3  i^2=25  h=12

        System.out.println("======GET======");

        System.out.println(QM.get(4)); // Should print A
        System.out.println(QM.get(5)); // Should print B
        System.out.println(QM.get(6)); // Should print C
        System.out.println(QM.get(7)); // Should print D
        System.out.println(QM.get(8)); // Should print E
        System.out.println(QM.get(9)); // Should print F

        System.out.println("======UPDATE KEY======");
        QM.put(9, "G");
        System.out.println(QM.get(9)); // Should print G

        
        System.out.println("======UNKNOWN KEY======");
        System.out.println(QM.get(11)); // Should print null

        System.out.println("======DELETE-4======");
        System.out.println(QM.delete(4)); //Should print A and index 12 should be replaced with DEFUNCT
        System.out.println(QM.get(4)); // Should print null
        System.out.println(QM.get(5)); // Should print B
        System.out.println(QM.get(6)); // Should print C
        System.out.println(QM.get(7)); // Should print D
        System.out.println(QM.get(8)); // Should print E
        System.out.println(QM.get(9)); // Should print G

        System.out.println("======DELETE-8======");
        QM.delete(8); // index 11 should be DEFUNCT
        System.out.println(QM.get(4)); // Should print null
        System.out.println(QM.get(5)); // Should print B
        System.out.println(QM.get(6)); // Should print C
        System.out.println(QM.get(7)); // Should print D
        System.out.println(QM.get(8)); // Should print null
        System.out.println(QM.get(9)); // Should print G

        System.out.println("======RESIZE=======");
        System.out.println("Before adding new elements:");
        System.out.println("size:"+QM.tableSize); // Should print 4
        System.out.println("capacity:"+QM.capacity); // Should print 17

        IntStream.range(10, 20).forEach(n->QM.put(n, Integer.toString(n)));

        System.out.println("After adding new elements:");
        System.out.println("size:"+QM.tableSize); //Should print 14
        System.out.println("capacity:"+QM.capacity); // Should print 37
        
        IntStream.range(4, 20).forEach(n->System.out.println(QM.get(n)));
        /*Should print
        null
        B
        C
        D
        null
        G
        10
        11
        12
        13
        14
        15
        16
        17
        18
        19
        */







    }
}
