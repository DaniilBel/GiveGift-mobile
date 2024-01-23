package com.example.givegiftdesign.giftidea;

import java.util.Objects;

/**
 * Данные выданного подарка
 * Само конструирование блока происходит в GiftBlockConstructor
 */
public class GiftBlock {
    private String imageUrl;
    private String description;
    private String giftUrl;

    public GiftBlock(String imageUrl, String description, String giftUrl) {
        this.imageUrl = imageUrl;
        this.description = description;
        this.giftUrl = giftUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getGiftUrl() {
        return giftUrl;
    }

    // А я переопределил))
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftBlock giftBlock = (GiftBlock) o;
        return imageUrl.equals(giftBlock.imageUrl) && description.equals(giftBlock.description) && giftUrl.equals(giftBlock.giftUrl);
    }

    // А я переопределил))
    @Override
    public int hashCode() {
        return Objects.hash(imageUrl, description, giftUrl);
    }
}
