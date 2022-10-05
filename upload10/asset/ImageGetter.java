package asset;

public class ImageGetter {

    private final String[] block = {"srcImg/block/floor/floor_2.png",// 0
                                    "srcImg/block/obstacle/crate3.png",// 1
                                    "srcImg/block/obstacle/stone2.png",// 2
                                    "srcImg/block/wall/wall_side_mid_left.png",// 3
                                    "srcImg/block/wall/wall_side_mid_right.png",// 4
                                    "srcImg/block/wall/wall.png",// 5
                                    "srcImg/block/wall/corner_right.png",// 6
                                    "srcImg/block/wall/corner_left.png",// 7
                                    "srcImg/block/wall/bot_corner_right.png",// 8
                                    "srcImg/block/wall/bot_corner_left.png",// 9
                                    "srcImg/block/wall/left_l.png",// 10
                                    "srcImg/block/wall/wall_left2.png",// 11
                                    "srcImg/block/floor/way_out.png"};// 12

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

    private final String[] items = {"srcImg/item/timelock.png",
                                    "srcImg/item/heart.png",
                                    "srcImg/item/boots.png",
                                    "srcImg/item/bomb.png"};

    private final String menuBackground = "srcImg/menu/background.png";

    private final String menuTutorial = "srcImg/menu/tutorial.png";

    private final String cyclop = "srcImg/monster/monster1.png";

    private final String bamboo = "srcImg/monster/monster2.png";

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

    public String[] getItems() {
        return items;
    }

    public String[] getChars() {
        return chars;
    }

    public String[] getCharDes() {
        return charDes;
    }

    public String getMenuBackground() {
        return menuBackground;
    }

    public String getMenuTutorial() {
        return menuTutorial;
    }

    public String getCyclop() {
        return cyclop;
    }

    public String getBamboo() {
        return bamboo;
    }
}
