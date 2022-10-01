package asset;

public class ImageGetter {

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

    private final String[] chars = {"srcImg/char/char1.png",
                                    "srcImg/char/char2.png",
                                    "srcImg/char/char3.png"};

    private final String[] charDes = {"srcImg/char/charDes1.png",
                                    "srcImg/char/charDes2.png",
                                    "srcImg/char/charDes3.png"};

    private final String[] bomb = {"srcImg/bomb/big_demon_idle_anim_f0.png",
                                    "srcImg/bomb/big_demon_idle_anim_f1.png",
                                    "srcImg/bomb/big_demon_idle_anim_f2.png",
                                    "srcImg/bomb/big_demon_idle_anim_f3.png"};
    private final String[] explosion = {"srcImg/explosion/bomb_exploded.png",
                                        "srcImg/explosion/explosion_vertical.png",
                                        "srcImg/explosion/explosion_horizontal.png"};

    private final String[] expl = {"srcImg/explosion/1.png",
                                    "srcImg/explosion/2.png",
                                    "srcImg/explosion/3.png",
                                    "srcImg/explosion/4.png"};

    private final String timelock = "srcImg/item/timelock.png";

    public String[] getBlock() {
        return block;
    }

    public String[] getBomb() {
        return bomb;
    }

    public String[] getExplosion() {
        return explosion;
    }

    public String[] getExpl() {
        return expl;
    }

    public String getTimelock() {
        return timelock;
    }

    public String[] getChars() {
        return chars;
    }

    public String[] getCharDes() {
        return charDes;
    }
}
