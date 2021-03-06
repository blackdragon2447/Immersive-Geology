// Credit where credit due
// World Generation - Muddykat (Using Modified Source Code from TerraFirmaCraft and YungsBetterCaves)
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007 for YungsBetterCaves sourced assets
// European Union Public Licence V. 1.2 for for TerraFirmaCraft Sourced Assets - this includes most of the biome distribution code
//

package com.igteam.immersivegeology;

import com.igteam.immersivegeology.client.ClientProxy;
import com.igteam.immersivegeology.client.menu.IGItemGroup;
import com.igteam.immersivegeology.client.menu.handler.CreativeMenuHandler;
import com.igteam.immersivegeology.common.CommonProxy;
import com.igteam.immersivegeology.common.IGContent;
import com.igteam.immersivegeology.common.network.PacketHandler;
import com.igteam.immersivegeology.common.util.IGLogger;
import com.igteam.immersivegeology.common.world.WorldEventHandler;
import com.igteam.immersivegeology.common.world.WorldTypeImmersive;
import com.igteam.immersivegeology.common.world.chunk.WorldChunkChecker;
import com.igteam.immersivegeology.common.world.chunk.data.ChunkDataCapability;
import com.muddykat.noise.NoiseGenTester;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;

@Mod(ImmersiveGeology.MODID)
public class ImmersiveGeology
{
	public static final String MODID = "immersivegeology";
	public static final String MODNAME = "Immersive Geology";
	public static final String VERSION = "${version}";
	public static final String NETWORK_VERSION = "1";
	public static final SimpleChannel packetHandler = NetworkRegistry.ChannelBuilder
			.named(new ResourceLocation(MODID, "main"))
			.networkProtocolVersion(() -> NETWORK_VERSION)
			.serverAcceptedVersions(NETWORK_VERSION::equals)
			.clientAcceptedVersions(NETWORK_VERSION::equals)
			.simpleChannel();
	public static CommonProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
	public static final IGItemGroup IG_ITEM_GROUP = new IGItemGroup(MODID);

	public ImmersiveGeology()
	{
		//setup world type
		worldType = new WorldTypeImmersive();
		//setup logger
		IGLogger.logger = LogManager.getLogger(MODID);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::loadComplete);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(WorldChunkChecker::preInit);
		MinecraftForge.EVENT_BUS.register(new WorldChunkChecker());

		MinecraftForge.EVENT_BUS.addListener(this::serverStarting);
		MinecraftForge.EVENT_BUS.addListener(this::serverStarted);
		MinecraftForge.EVENT_BUS.register(new WorldEventHandler());
		IGContent.modConstruction();
	}

	public static WorldTypeImmersive worldType;
	
	public static WorldTypeImmersive getWorldType()
	{
		return worldType;
	}

	@SubscribeEvent
	public void clientSetup(FMLClientSetupEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new CreativeMenuHandler());
	}

	@SubscribeEvent
	public void commonSetup(FMLCommonSetupEvent event)
	{
		//Previously in PREINIT
		proxy.preInit();

		//Previously in INIT
		proxy.init();

		PacketHandler.setup();
		ChunkDataCapability.setup();
		IGContent.init();

		proxy.postInit();
		IGContent.postInit();
	}

	@SubscribeEvent
	public void loadComplete(FMLLoadCompleteEvent event)
	{
		
	}
	
	@SubscribeEvent
	public void serverStarting(FMLServerStartingEvent event)
	{
		proxy.serverStarting();
	}

	@SubscribeEvent
	public void serverStarted(FMLServerStartedEvent event)
	{

	}

}
