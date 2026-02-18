package a.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "a", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCapabilities {

    /**
     * This is a placeholder capability. 
     * In a real mod, you would likely split the interface and implementation into their own files,
     * but for the purpose of a single-file generation, they are included here as nested classes.
     */
    public static final Capability<IExampleData> EXAMPLE_DATA = CapabilityManager.get(new CapabilityToken<>() {});

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(IExampleData.class);
    }

    /**
     * Interface for the capability data.
     */
    public interface IExampleData {
        int getTicks();
        void setTicks(int ticks);
        void addTick();
    }

    /**
     * Default implementation of the capability data.
     */
    public static class ExampleDataImpl implements IExampleData {
        private int ticks = 0;

        @Override
        public int getTicks() {
            return this.ticks;
        }

        @Override
        public void setTicks(int ticks) {
            this.ticks = ticks;
        }

        @Override
        public void addTick() {
            this.ticks++;
        }
    }
}