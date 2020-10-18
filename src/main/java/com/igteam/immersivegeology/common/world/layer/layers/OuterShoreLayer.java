package com.igteam.immersivegeology.common.world.layer.layers;

import com.google.common.base.Predicate;
import com.igteam.immersivegeology.common.world.layer.IGLayerUtil;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IBishopTransformer;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

import java.util.function.IntPredicate;

public enum OuterShoreLayer implements ICastleTransformer, IBishopTransformer
{
	CASTLE,
	BISHOP;

	@Override
	public int apply(INoiseRandom context, int north, int west, int south, int east, int center)
	{
		Predicate<IntPredicate> matcher = p -> p.test(north) || p.test(west) || p.test(south) || p.test(east);
		if (!IGLayerUtil.isOcean(center))
		{
			if (matcher.test(IGLayerUtil::isShore))
			{
				return IGLayerUtil.SHORE;
			}
		}
		return center;
	}

	@Override
	public int func_215728_a(IExtendedNoiseRandom<?> context, IArea area, int x, int z)
	{
		return this == CASTLE ? ICastleTransformer.super.func_215728_a(context, area, x, z) : IBishopTransformer.super.func_215728_a(context, area, x, z);
	}
}