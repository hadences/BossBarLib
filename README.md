# BossBarLib (Fabric)
Dynamic boss bar library system for Minecraft!

## Features
- Dynamic boss bar 
- Customizable boss bar (Custom Overlay, Animated Overlay, Color, Style, Progress, etc)

## Adding BossBarLib to your project
you can add the library by inserting the following your ```build.gradle``` file
```ruby
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}


dependencies {
    modImplementation "com.github.hadences:BossBarLib:${bossbar_version}"
    include "com.github.hadences:BossBarLib:${bossbar_version}"
}
```

add the version of the library ```bossbar_version``` in the ```gradle.properties``` file

```ruby
# Bossbar library
bossbar_version=vx.y.z
```

you can find the recent version in the [releases](https://github.com/hadences/BossBarLib/releases) tab

## Setup
BossBarLib creates one instance of a bossbar at initialization and requires you to call the init functions in both Client and Server init methods.

### Server Side
```ruby
public class TestMod implements ModInitializer {
  @Override
  	public void onInitialize() {
  		CustomBossBarManager.initServer();
    }
}
```

### Client Side
```ruby
public class TestModClient implements ClientModInitializer {
  @Override
  	public void onInitialize() {
        CustomBossBarManager.initClient();
    }
}
```

## Registering a custom bossbar
Custom bossbars must be registered at initialization and cannot be created during runtime. You may create a helper function in the server side class to register them. Here is an example where I apply a bossbar to a pig with a custom overlay
```ruby
public class TestMod implements ModInitializer {

  public static final String MOD_ID ="testmod";

  @Override
  public void onInitialize() {
      CustomBossBarManager.initServer();\
      registerCustomBossBars();
  }
}

private void registerCustomBossBars() {
    CustomBossBarRegistry bossBarRegistry = CustomBossBarRegistry.getInstance();
    bossBarRegistry.registerBossBar(EntityType.PIG,
      new CustomBossBar(0xffffff, BossBar.Style.PROGRESS, false, false, false, 30.0, new Identifier(TestMod.MOD_ID, "textures/boss_bars/template"), 2));
}
```
