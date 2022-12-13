/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.weisboys.randomsurvival;

import io.netty.util.internal.ThreadLocalRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;


/**
 *
 * @author weisb
 */

public class MobDrops implements Listener{
    
     Map<EntityType,Material> dropID = new HashMap<>();
    
     private ToggleCommand tcmd;
     public MobDrops (ToggleCommand tc) {
         tcmd = tc;
     }
     
     
    @EventHandler 
    public void mobDrop (EntityDeathEvent e) {
        
        if (tcmd.isEnabled()){
              e.getDrops().clear();
            EntityType entityType = e.getEntity().getType();
        
            if (!dropID.containsKey(entityType))
                {

                    Random random = ThreadLocalRandom.current();
                    dropID.put(entityType, Material.values()[random.nextInt(Material.values().length)]); 
                }

            Material material = dropID.get(entityType);
            ItemStack droppedItem = new ItemStack(material);
            e.getDrops().add(droppedItem);
        }
        
      
    }
    
}