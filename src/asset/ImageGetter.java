package asset;

public class ImageGetter {

    private final String playerImg = "srcImg/char/char.png";
    private final String[] block = {"srcImg/block/floor/floor_2.png",
                                    "srcImg/block/obstacle/crate3.png",
                                    "srcImg/block/obstacle/stone2.png",
                                    "srcImg/block/wall/wall_side_mid_left.png",
                                    "srcImg/block/wall/wall_side_mid_right.png",
                                    "srcImg/block/wall/wall.png",
                                    "srcImg/block/wall/corner_right.png",
                                    "srcImg/block/wall/corner_left.png",
                                    "srcImg/block/wall/bot_corner_right.png",
                                    "srcImg/block/wall/bot_corner_left.png",
                                    "srcImg/block/wall/mid_right_filled.png",
                                    "srcImg/block/wall/wall_left2.png",
                                    "srcImg/block/floor/way_out.png"};

    private final String[] bomb = {"srcImg/bomb/big_demon_idle_anim_f0.png",
                                    "srcImg/bomb/big_demon_idle_anim_f1.png",
                                    "srcImg/bomb/big_demon_idle_anim_f2.png",
                                    "srcImg/bomb/big_demon_idle_anim_f3.png"};
    private final String[] explosion = {"srcImg/explosion/bomb_exploded.png",
                                        "srcImg/explosion/explosion_vertical.png",
                                        "srcImg/explosion/explosion_horizontal.png"};


    public String getPlayerImg() {
        return playerImg;
    }

    public String[] getBlock() {
        return block;
    }

    public String[] getBomb() {
        return bomb;
    }

    public String[] getExplosion() {
        return explosion;
    }
}
