package com.normal;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ResourceMgr {
    public static BufferedImage goodTankL, goodTankU, goodTankR, goodTankD;
    public static BufferedImage badTankL, badTankU, badTankR, badTankD;
    public static BufferedImage bulletL, bulletU, bulletR, bulletD;
    public static BufferedImage[] explodes = new BufferedImage[16];


    static {
        try {
            InputStream goodTankImageInputStream = ResourceMgr.class.getResourceAsStream("/image/GoodTank1.png");
            if (goodTankImageInputStream == null) {
                throw new RuntimeException("图片不存在！");
            }
            goodTankU = ImageIO.read(goodTankImageInputStream);
            goodTankL = ImageUtil.rotateImage(goodTankU, -90);
            goodTankR = ImageUtil.rotateImage(goodTankU, 90);
            goodTankD = ImageUtil.rotateImage(goodTankU, 180);

            InputStream badTankImageInputStream = ResourceMgr.class.getResourceAsStream("/image/BadTank1.png");
            if (badTankImageInputStream == null) {
                throw new RuntimeException("图片不存在！");
            }
            badTankU = ImageIO.read(badTankImageInputStream);
            badTankL = ImageUtil.rotateImage(badTankU, -90);
            badTankR = ImageUtil.rotateImage(badTankU, 90);
            badTankD = ImageUtil.rotateImage(badTankU, 180);

            InputStream bulletImageInputStream = ResourceMgr.class.getResourceAsStream("/image/bulletU.png");
            if (bulletImageInputStream == null) {
                throw new RuntimeException("图片不存在！");
            }
            bulletU = ImageIO.read(bulletImageInputStream);
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            bulletR = ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);
//
//            if (resource == null) {
//                throw new RuntimeException("图片不存在！");
//            }
//            for (int i = 0; i < 16; i++)
//                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
