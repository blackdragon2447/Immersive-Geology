package com.igteam.immersivegeology.common.world.biome.biomes;

import com.igteam.immersivegeology.common.world.biome.IGBiome;
import com.igteam.immersivegeology.common.world.biome.IGDefaultBiomeFeatures;
import com.igteam.immersivegeology.common.world.gen.surface.util.SurfaceBlockType;
import com.igteam.immersivegeology.common.world.noise.INoise2D;
import com.igteam.immersivegeology.common.world.noise.SimplexNoise2D;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.DefaultBiomeFeatures;

import javax.annotation.Nonnull;

import static com.igteam.immersivegeology.common.world.gen.config.ImmersiveGenerationSettings.SEA_LEVEL;


public class LakeBiome extends IGBiome
{
	public LakeBiome()
	{
		super(new Builder().category(Category.RIVER).precipitation(RainType.RAIN).downfall(0.65f).temperature(0.45f), .45f, .65f);

		IGDefaultBiomeFeatures.addCarvers(this);
		DefaultBiomeFeatures.addKelp(this);
	}

	@Nonnull
	@Override
	public INoise2D createNoiseLayer(long seed)
	{
		return new SimplexNoise2D(seed).octaves(4).spread(0.2f).scaled(SEA_LEVEL-8, SEA_LEVEL-3);
	}

	@Override
	public BlockState returnBlockType(SurfaceBlockType part, float chunkTemp, float chunkRain)
	{
		// TODO Auto-generated method stub
		switch(part)
		{
			case grass:
				return Blocks.SAND.getDefaultState();
			case dirt:
				return Blocks.SAND.getDefaultState();
			default:
				return Blocks.CLAY.getDefaultState();
		}
	}
}