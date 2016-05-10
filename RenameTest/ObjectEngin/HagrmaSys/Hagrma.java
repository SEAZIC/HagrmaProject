package ObjectEngin.HagrmaSys;

import BaseSystem.Animation.Animationtime;
import ObjectEngin.ComonObject.BaseObject;

/**
 * <PRE>
 *  歯車
 * 属性：歯車
 * すべての歯車のベースとして継承してください
 * </PRE>
 *
 */
public class Hagrma {

    protected BaseObject parent;

    private Animationtime animation;

    private boolean useAnimation;

    private String name;

    public Hagrma() {
        setName("hagrma");
    }

    public void setParent(BaseObject parent) {

        this.parent = parent;
    }

    public BaseObject getParent() {
        return parent;
    }

    public void stop() {
        animation.stop();
    }

    public boolean isStop() {
        return animation.isStop();
    }

    public Animationtime getAnimation() {
        return animation;
    }

    public void setAnimetion(Animationtime animation) {
        this.animation = animation;
        setUseAnimation(true);
    }

    public boolean isUseAnimation() {
        return useAnimation;
    }

    public void setUseAnimation(boolean useAnimation) {
        this.useAnimation = useAnimation;
    }

    public boolean isAnimation() {
        return (animation != null && !animation.isStop() && !animation.isError());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
