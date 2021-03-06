/**
 * ItemArray.java - Interface to jh[] so I don't have to copy+paste this a bunch
 * of times
 * 
 * @author James
 */
public abstract class ItemArray<C extends Container<hn>> {
    protected C container;
    private int arraySize = 0;    
    
    public ItemArray(C c, int size) {
        this.container = c;
        this.arraySize = size;
    }
    
    public int getContentSize() {
        return arraySize;
    }
    
    /**
     * Adds the specified item. If the item doesn't have a slot, it will get the
     * nearest available slot. If amount is equal to 0, it will delete the item
     * if a slot is specified.
     * 
     * @param item item to add
     */
    public void addItem(Item item) {
        if (item == null) {
            return;
        }

        int slot = item.getSlot();
        if (slot < getArray().length && slot >= 0) {
            if (item.getAmount() <= 0) {
                getArray()[slot] = null;
            } else if (Item.isValidItem(item.getItemId())) {
                getArray()[slot] = new hn(item.getItemId(), item.getAmount());
            }
        } else if (slot == -1) {
            int newSlot = getEmptySlot();
            if (newSlot != -1) {
                getArray()[newSlot] = new hn(item.getItemId(), item.getAmount());
                item.setSlot(newSlot);
            }
        }
    }

    /**
     * Retrieves from the slot
     * 
     * @param slot slot to get item from
     * @return item
     */
    public Item getItemFromSlot(int slot) {
        if (slot < getArray().length && slot >= 0) {
            if (getArray()[slot] != null) {
                return new Item(getArray()[slot].c, getArray()[slot].a, slot);
            }
        }
        return null;
    }

    /**
     * Retrieves from the slot
     * 
     * @param id
     * @return item
     */
    public Item getItemFromId(int id) {
        for (int i = 0; getArray().length > i; i++) {
            if (getArray()[i] == null) {
                continue;
            }
            if (getArray()[i].c == id) {
                return new Item(getArray()[i].c, getArray()[i].a, i);
            }
        }
        return null;
    }

    /**
     * Retrieves from the slot
     * 
     * @param id
     * @param maxAmount
     * @return item
     */
    public Item getItemFromId(int id, int maxAmount) {
        for (int i = 0; getArray().length > i; i++) {
            if (getArray()[i] == null) {
                continue;
            }
            if (getArray()[i].c == id && getArray()[i].a <= maxAmount) {
                return new Item(getArray()[i].c, getArray()[i].a, i);
            }
        }
        return null;
    }

    /**
     * Gets the nearest empty slot. -1 if there's no empty slots
     * 
     * @return nearest empty slot
     */
    public int getEmptySlot() {
        for (int i = 0; getArray().length > i; i++) {
            if (getArray()[i] != null) {
                continue;
            }
            return i;
        }
        return -1;
    }

    /**
     * Removes the item from the slot
     * 
     * @param slot slot to remove item from
     */
    public void removeItem(int slot) {
        if (slot < getArray().length && slot >= 0) {
            getArray()[slot] = null;
        }
    }

    /**
     * Sets the specified slot with item
     * 
     * @param item item to set
     * @param slot slot to use
     */
    public void setSlot(Item item, int slot) {
        setSlot(item.getItemId(), item.getAmount(), slot);
    }

    /**
     * Replaces the slot with the item inputted.
     * 
     * @param itemId item id of the item to put into the slot.
     * @param amount amount of the item to put into the slot.
     * @param slot the id of the slot.
     */
    public void setSlot(int itemId, int amount, int slot) {
        if (slot < getArray().length && slot >= 0) {
            getArray()[slot] = new hn(itemId, (amount > 64 ? 64 : amount));
        }
    }

    /**
     * Removes the item. No slot needed, it will go through the inventory until
     * the amount specified is removed.
     * 
     * @param item item id and amount to remove
     */
    public void removeItem(Item item) {
        int amount = item.getAmount();
        int itemId = item.getItemId();
        for (int i = 0; getArray().length > i; i++) {
            if (getArray()[i] == null) {
                continue;
            }
            if (getArray()[i].c == itemId) {
                int tempAmount = getArray()[i].a;
                tempAmount -= amount;
                amount -= getArray()[i].a;
                if (tempAmount <= 0) {
                    getArray()[i] = null;
                } else {
                    getArray()[i].a = tempAmount;
                }
                if (amount <= 0) {
                    return;
                }
            }
        }
    }

    /**
     * Checks to see if this getArray() has one slot that has the item id and
     * equal or more to the amount.
     * 
     * @param itemId
     * @param minimum
     * @param maximum
     * @return
     */
    public boolean hasItem(int itemId, int minimum, int maximum) {
        for (int i = 0; getArray().length > i; i++) {
            if (getArray()[i] != null) {
                if (getArray()[i].c == itemId && getArray()[i].a >= minimum && getArray()[i].a <= maximum) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the contents of this chest
     * @return contents
     */
    public Item[] getContents() {
        Item[] rt = new Item[arraySize];
        for (int i = 0; i < arraySize; i++) {
            rt[i] = (getArray()[i] != null) ? new Item(getArray()[i]):null;
        }

        return rt;
    }

    /**
     * Sets the contents
     * @param contents contents to set
     */
    public void setContents(Item[] contents) {
        hn[] newcontents = new hn[arraySize];
        for (int i = 0; i < arraySize; i++) {
            newcontents[i] = (contents[i] != null) ? new hn(contents[i].getItemId(), contents[i].getAmount()):null;
        }
        setArray(newcontents);
    }
        
    public hn[] getArray() {
        return container.getContents();
    }
    
    public void setArray(hn[] values) {
        container.setContents(values);
    }
    
    public void clearContents() {
        for (int i = 0; getArray().length > i; i++) {
            getArray()[i] = null;
        }
    }
}
