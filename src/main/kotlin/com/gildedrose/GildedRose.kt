package com.gildedrose

class GildedRose(var items: Array<Item>) {
    private fun decreaseQualityBy(item: Item, amount: Int) {
        item.quality = if (item.quality - amount >= 0) item.quality - amount else 0
    }

    private fun increaseQualityBy(item: Item, amount: Int) {
        item.quality = if (item.quality + amount <= 50) item.quality + amount else 50
    }

    fun updateQuality() {
        for (item in items) {
            if (item.name == "Sulfuras, Hand of Ragnaros") continue

            item.sellIn--

            var qualityVariationAmount = if (item.sellIn < 0) 2 else 1

            if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                increaseQualityBy(item, 1)

                if (item.sellIn < 11) {
                    increaseQualityBy(item, 1)
                }

                if (item.sellIn < 6) {
                    increaseQualityBy(item, 1)
                }
            } else if (item.name == "Aged Brie") {
                increaseQualityBy(item, qualityVariationAmount)
            } else {
                decreaseQualityBy(item, qualityVariationAmount)
            }

            if (item.sellIn < 0 && item.name == "Backstage passes to a TAFKAL80ETC concert") {
                decreaseQualityBy(item, item.quality)
            }
        }
    }

}

