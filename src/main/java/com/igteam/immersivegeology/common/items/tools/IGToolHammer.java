package com.igteam.immersivegeology.common.items.tools;

import com.igteam.immersivegeology.api.toolsystem.Tooltypes;
import com.igteam.immersivegeology.common.items.IGModularToolItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ToolType;

import java.util.Collections;
import java.util.Set;

/**
 * @author Pabilo8
 * @since 22.08.2020
 */
public class IGToolHammer extends IGModularToolItem
{
	public static final Set<ToolType> TYPES = Collections.singleton(Tooltypes.HAMMER_TOOL);

	public IGToolHammer()
	{
		super("hammer");
	}

	@Override
	public Set<ToolType> getToolTypes(ItemStack stack)
	{
		return TYPES;
	}
}
