package com.normal.utils;

import com.normal.DirType;
import com.normal.ImageUtil;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ResourceMgr {
    public static BufferedImage bulletL, bulletU, bulletR, bulletD;
    public static BufferedImage[] explodes = new BufferedImage[16];

    public static Map<DirType, BufferedImage> playerImageMap = new HashMap<>();
    public static Map<DirType, BufferedImage> enemyImageMap = new HashMap<>();

    static {
        try {
            InputStream goodTankImageInputStream = ResourceMgr.class.getResourceAsStream("/image/GoodTank1.png");
            if (goodTankImageInputStream == null) {
                throw new RuntimeException("图片不存在！");
            }
            BufferedImage goodTankU = ImageIO.read(goodTankImageInputStream);
            System.out.println(goodTankU);
            playerImageMap.put(DirType.U, goodTankU);
            playerImageMap.put(DirType.L, ImageUtil.rotateImage(goodTankU, -90));
            playerImageMap.put(DirType.R, ImageUtil.rotateImage(goodTankU, 90));
            playerImageMap.put(DirType.D, ImageUtil.rotateImage(goodTankU, 180));

            InputStream badTankImageInputStream = ResourceMgr.class.getResourceAsStream("/image/BadTank1.png");
            if (badTankImageInputStream == null) {
                throw new RuntimeException("图片不存在！");
            }
            BufferedImage badTankU = ImageIO.read(badTankImageInputStream);
            enemyImageMap.put(DirType.U, badTankU);
            enemyImageMap.put(DirType.L, ImageUtil.rotateImage(badTankU, -90));
            enemyImageMap.put(DirType.R, ImageUtil.rotateImage(badTankU, 90));
            enemyImageMap.put(DirType.D, ImageUtil.rotateImage(badTankU, 180));

            InputStream bulletImageInputStream = ResourceMgr.class.getResourceAsStream("/image/bulletU.png");
            if (bulletImageInputStream == null) {
                throw new RuntimeException("图片不存在！");
            }
            bulletU = ImageIO.read(bulletImageInputStream);
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            bulletR = ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);



            for (int i = 0; i < 16; i++)
                explodes[i] = ImageIO.read(ResourceMgr.class.getResourceAsStream("/image/e" + (i + 1) + ".gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
