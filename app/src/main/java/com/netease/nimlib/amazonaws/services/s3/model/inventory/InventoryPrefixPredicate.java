package com.netease.nimlib.amazonaws.services.s3.model.inventory;

/* loaded from: classes.dex */
public final class InventoryPrefixPredicate extends InventoryFilterPredicate {
    private final String prefix;

    public InventoryPrefixPredicate(String str) {
        this.prefix = str;
    }

    public String getPrefix() {
        return this.prefix;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.model.inventory.InventoryFilterPredicate
    public void accept(InventoryPredicateVisitor inventoryPredicateVisitor) {
        inventoryPredicateVisitor.visit(this);
    }
}
