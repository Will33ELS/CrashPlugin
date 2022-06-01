package fr.will33.crashplugin.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class ItemUtil {

    private ItemStack itemStack;
    private ItemMeta itemMeta;

    public ItemUtil(Material material, int amount){
        this.itemStack = new ItemStack(material, amount);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    public ItemUtil(Material material, int amount, String displayName, List<String> lore){
        this(material, amount, (byte) 0, displayName, lore);
    }

    public ItemUtil(Material material, int amount, byte data, String displayName, List<String> lore){
        this.itemStack = new ItemStack(material, amount, data);
        this.itemMeta = this.itemStack.getItemMeta();
        this.itemMeta.setDisplayName(displayName);
        this.itemMeta.setLore(lore);
        this.itemStack.setItemMeta(this.itemMeta);
    }

    public ItemUtil byUserName(String username){
        if(!(this.itemMeta instanceof SkullMeta skullMeta)){
            throw new RuntimeException("ItemMeta is not a SkullMeta");
        }
        skullMeta.setOwner(username);
        this.itemStack.setItemMeta(skullMeta);
        return this;
    }

    public ItemUtil hideItemFlags(){
        this.itemMeta.addItemFlags(ItemFlag.values());
        this.itemStack.setItemMeta(this.itemMeta);
        return this;
    }

    /**
     * Add enchantment on item
     * @param enchantment Type of the enchantment
     * @param level Level of the enchantment
     * @return
     */
    public ItemUtil addEnchant(Enchantment enchantment, int level){
        this.itemMeta.addEnchant(enchantment, level, true);
        this.itemStack.setItemMeta(this.itemMeta);
        return this;
    }

    /**
     * Define the displayName
     * @param displayName Display name of the item
     * @return Item instance
     */
    public ItemUtil setDisplayName(String displayName){
        this.itemMeta.setDisplayName(displayName);
        this.itemStack.setItemMeta(this.itemMeta);
        return this;
    }

    /**
     * Define the lore
     * @param lore Lore of the item
     * @return Item instance
     */
    public ItemUtil setLore(List<String> lore){
        this.itemMeta.setLore(lore);
        this.itemStack.setItemMeta(this.itemMeta);
        return this;
    }

    /**
     * Return the ItemStack instance
     * @return
     */
    public ItemStack toItemStack() {
        return itemStack;
    }
}
