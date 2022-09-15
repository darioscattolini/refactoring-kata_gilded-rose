package com.gildedrose

class GildedRose(var items: Array<Item>) {
    private fun decreaseQualityBy(item: Item, amount: Int) {
        item.quality = if (item.quality - amount >= 0) item.quality - amount else 0
    }

    fun updateQuality() {
        for (item in items) {
            if (
                item.name != "Aged Brie" &&
                item.name != "Backstage passes to a TAFKAL80ETC concert" &&
                item.name != "Sulfuras, Hand of Ragnaros"
            ) {
                decreaseQualityBy(item, 1)
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1

                    if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1
                            }
                        }
                    }
                }
            }

            if (item.name != "Sulfuras, Hand of Ragnaros") {
                item.sellIn = item.sellIn - 1
            }

            if (item.sellIn < 0) {
                if (item.name != "Aged Brie") {
                    if (item.name != "Backstage passes to a TAFKAL80ETC concert") {
                        if (item.name != "Sulfuras, Hand of Ragnaros") {
                            decreaseQualityBy(item, 1)
                        }
                    } else {
                        decreaseQualityBy(item, item.quality)
                    }
                } else {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1
                    }
                }
            }
        }
    }

}

