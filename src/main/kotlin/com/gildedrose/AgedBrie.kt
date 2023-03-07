package com.gildedrose

class AgedBrie(item: Item) : UpdatableItem(item) {
    override fun updateQuality() {
        item.sellIn--
        modifyQualityBy(baseQualityVariation * -1)
    }

}
