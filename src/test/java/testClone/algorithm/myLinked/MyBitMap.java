package testClone.algorithm.myLinked;


/**
 * 位图
 */
public class MyBitMap {
    private long[] bitMap;

    public MyBitMap() {
    }

    public MyBitMap(int max) {
        bitMap = new long[(max + 64) >> 6];
    }

    public void add(int num) {
        bitMap[num >> 6] |= (1L << (num & 63));
    }

    public void delete(int num) {
        bitMap[num >> 6] &= ~(1L << (num & 63));
    }

    public boolean contains(int num) {
        return (bitMap[num >> 6] & (1L << (num & 63))) != 0;
    }

    public static void main(String[] args) {
        MyBitMap myBitMap = new MyBitMap(63);
        int num = 62;
        myBitMap.add(num);
        for (long l : myBitMap.bitMap) {
            System.out.println(l);
        }
        System.out.println(myBitMap.contains(num));
        myBitMap.delete(num);
        for (long l : myBitMap.bitMap) {
            System.out.println(l);
        }
        System.out.println(myBitMap.contains(num));
    }
}
