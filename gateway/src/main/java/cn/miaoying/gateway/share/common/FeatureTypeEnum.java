package cn.miaoying.gateway.share.common;

public enum FeatureTypeEnum {
    MENU("菜单", 0), BUTTOM("按钮", 1);
    private String name;
    private Integer value;

    FeatureTypeEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static FeatureTypeEnum nameOf(String name) {
        for (FeatureTypeEnum f : FeatureTypeEnum.values()) {
            if (f.name.equals(name)) {
                return f;
            }
        }
        return null;
    }

    public static FeatureTypeEnum valueOf(Integer value) {
        for (FeatureTypeEnum f : FeatureTypeEnum.values()) {
            if (f.value.equals(value)) {
                return f;
            }
        }
        return null;
    }
}