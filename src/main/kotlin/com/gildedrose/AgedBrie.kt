package com.gildedrose

class AgedBrie(item: Item) : UpdatableItem(item) {
    override fun updateQuality() {
        updateSellIn()
        modifyQualityBy(baseQualityVariation * -1)
    }
}
