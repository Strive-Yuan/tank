package testClone;

import java.io.*;

public class User implements Cloneable, Serializable {
    public Integer id;
    public Card card;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Object deepClone() throws Exception {
        // 序列化
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);
        // 反序列化
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);

        return ois.readObject();
    }

    public static void main(String[] args) throws Exception {
        User user = new User();
        user.card = new Card(1);
        User clone = (User) user.clone();
        clone.card.id = 22;
        System.out.println("浅拷贝:" + (clone == user));
        System.out.println("浅拷贝:" + (clone.card == user.card));
        System.out.println("浅拷贝:" + user.card.id);
        System.out.println("浅拷贝:" + clone.card.id);
        User deepClone = (User) user.deepClone();
        deepClone.card.id = 32;
        System.out.println("深拷贝:" + (deepClone == user));
        System.out.println("深拷贝:" + (deepClone.card == user.card));
        System.out.println("深拷贝:" + deepClone.card.id);
        System.out.println("深拷贝:" + user.card.id);
    }
}

class Card implements Cloneable, Serializable {
    public Integer id;

    public Card() {
    }

    public Card(Integer id) {
        this.id = id;
    }

    @Override
    public Card clone() throws CloneNotSupportedException {
        return (Card) super.clone();
    }
}
