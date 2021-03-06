package com.igteam.immersivegeology.common.materials.metals;

import com.igteam.immersivegeology.ImmersiveGeology;
import com.igteam.immersivegeology.api.materials.PeriodicTableElement;
import com.igteam.immersivegeology.api.materials.PeriodicTableElement.ElementProportion;
import com.igteam.immersivegeology.api.materials.material_bases.MaterialMetalBase;
import net.minecraft.item.Rarity;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.LinkedHashSet;

/**
 * Created by Pabilo8 on 25-03-2020.
 */
public class MaterialMetalUnobtanium extends MaterialMetalBase
{

	public MaterialMetalUnobtanium() {
		isNativeMetal = true;
	}
	
	@Override
	public String getName()
	{
		return "unobtanium";
	}

	@Nonnull
	@Override
	public String getModID()
	{
		return ImmersiveGeology.MODID;
	}

	@Override
	public LinkedHashSet<ElementProportion> getElements()
	{
		return new LinkedHashSet<>(Arrays.asList(
				new ElementProportion(PeriodicTableElement.UNOBTANIUM)
		));
	}

	@Override
	public Rarity getRarity()
	{
		return Rarity.EPIC;
	}

	@Override
	public int getBoilingPoint()
	{
		return 6287;
	}

	@Override
	public int getMeltingPoint()
	{
		return 4761;
	}

	public static int baseColor = 0x878681;

	@Override
	public int getColor(int temperature)
	{
		return baseColor;
	}

	//Needs to be changed in code for subtypes, such as sheetmetal
	@Override
	public float getHardness()
	{
		return 25.0F;
	}

	@Override
	public float getMiningResistance()
	{
		return 25.0F;
	}

	@Override
	public float getBlastResistance()
	{
		return 30;
	}

	//Copied from Immersive Intelligence (steel has i think 1.65, leaves 0.35)
	@Override
	public float getDensity()
	{
		return 0.65f;
	}

	//Stone pickaxe level
	@Override
	public int getBlockHarvestLevel()
	{
		return 4;
	}

	@Override
	public EnumMetalType getMetalType()
	{
		return EnumMetalType.METAL;
	}


	/*@Nullable
	@Override
	public IItemTier getToolTier()
	{
		return IGContent.;
	}*/
}
